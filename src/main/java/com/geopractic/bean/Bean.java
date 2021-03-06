package com.geopractic.bean;

import com.geopractic.controladores.IControlador;
import com.geopractic.sistema.modelo.ContenidoMultimedia;
import com.geopractic.sistema.modelo.Usuario;
import com.geopractic.util.GenerarReporte;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase abstracta que implementa las funciones y operaciones
 * implementados por todos los Bean del sistema.
 * @author Cristhian
 * @param <T> parametro a recibir.
 */
public abstract class Bean<T> implements IBean,IBeanCrud<T> {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  protected FacesMessage mensaje;
  protected FacesContext contexto;
  protected PrimeFaces contextoPeticion;
  protected String funcion;
  protected IControlador<T> controlador;
  protected T entidad;
  protected List<T> lista;
  protected List<T> listaFiltro;
  protected StreamedContent multimedia;
  protected Usuario usuario;

  /**
   * Constructor sin argumentos.
   */
  public Bean() {

  } //Fin de constructor

  @Override
  public T getEntidad() {
    return entidad;
  } //Fin del metodo getEntidad

  @Override
  public void setEntidad(T entidad) {
    this.entidad = entidad;
  } //Fin del metodo setEntidad

  @Override
  public List<T> getLista() {
    return this.lista;
  } //Fin del metodo getLista

  @Override
  public void setLista(List<T> lista) {
    this.lista = lista;
  } //Fin del metodo setLista

  @Override
  public List<T> getListaFiltro() {
    return listaFiltro;
  } //Fin del metodo getListaFiltro

  @Override
  public void setListaFiltro(List<T> listaFiltro) {
    this.listaFiltro = listaFiltro;
  } //Fin del metodo setListaFiltroCliente

  
  /**
   * Devuelve el usuario de la sesion actual.
   * @return el usuario de la sesion actual.
   */
  public Usuario getUsuario() {
    return usuario;
  } //Fin del metodo getUsuario

  /**
   * Establece el usuario de la sesion actual.
   * @param usuario el usuario a establecer.
   */
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  } //Fin del metodo setUsuario

  @Override
  public String getFuncion() {
    return funcion;
  } //Fin del metodo getFuncion

  /**
   * Devuelve el objeto multimedia del bean.
   * @return el objeto multimedia en forma de un StreamedContent.
   */
  public StreamedContent getMultimedia() {
    return multimedia;
  } //Fin del metodo getMultimedia

  /**
   * Establece un objeto multimedia.
   * @param multimedia el objeto multimedia a establecer en forma de StreamedContent
   */
  public void setMultimedia(StreamedContent multimedia) {
    this.multimedia = multimedia;
  } //Fin del metodo setMultimedia

  @Override
  public void setFuncion(String funcion) {
    this.funcion = funcion;
    //Genera una entidad nueva si se esta agregando
    if (funcion.equals("agregar")) {
      generarNuevaEntidad();
    } //Fin de if
  } //Fin del metodo setFuncion

  @Override
  public void prepararVariables() {
    mensaje = null;
    contexto = FacesContext.getCurrentInstance();
    contextoPeticion = PrimeFaces.current();
    prepararControlador();
  } //Fin del metodo prepararVariables

  @Override
  public void seleccionar(T objetoSeleccionado) {
    this.funcion = "modificar";
    this.entidad = objetoSeleccionado;
  } //Fin del metodo seleccionar

  @Override
  public void agregarMensaje(Severity severidad, String resumen, String detalle,String componente) {
    mensaje = new FacesMessage(severidad,resumen,detalle);
    if (componente == null) {
      contexto.addMessage(componente, mensaje);
    } else {
      contexto.addMessage("mensajes", mensaje);
    }
    
  } //Fin del metodo agregarMensaje

  /**
   * Obtiene el usuario de la sesion actual.
   */
  protected void obtenerUsuario() {
    contexto = FacesContext.getCurrentInstance();
    setUsuario((Usuario) contexto.getExternalContext().getSessionMap().get("usuario"));
  } //Fin del metodo obtenerUsuario

  @Override
  public void resetear(String objeto) {
    contextoPeticion.resetInputs(objeto);
  } //Fin del metodo cerrarDialogo

  @Override
  public void abrirDialogo(String dialogo) {
    contextoPeticion.executeScript("PF('" + dialogo + "').show();");
  } //Fin del metodo abrirDialogo

  @Override
  public void cerrarDialogo(String dialogo) {
    contextoPeticion.executeScript("PF('" + dialogo + "').hide();");
  } //Fin del metodo cerrarDialogo

  @Override
  public void generarLista() {
    prepararVariables();
    log.debug("--> generarLista() - Generando listado de eventos del sistema.");
    try {
      lista = controlador.listarTodos();
      agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Carga completa", "Listado cargado.",null);
    } catch (Exception excepcion) {
      log.error("--> generarLista() - Error en generando lista ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Hubo un error dentro del sistema. No se pudo "
          + "obtener el listado de eventos.",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("--> generarLista() -  Error cerrando los recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo generarLista

  /**
   * Genera un reporte en base a Jasper.
   * @param reporte el nombre del reporte en formato jasper a usar.
   * @param nombreArchivo el nombre del reporte generado.
   * @param listado el listado de objetos a usar en el reporte.
   * @return el reporte como un objeto stream multimedia.
   */
  public ContenidoMultimedia generateReporte(String reporte,String nombreArchivo,
       List<T> listado) {
    DefaultStreamedContent  streamContent = null;
    ByteArrayOutputStream streamSalida = null;
    try {
      Map<String, Object> parametros = new HashMap<String, Object>();
      GenerarReporte generador = new GenerarReporte();
      streamSalida = generador.obtenerOutputStreamDeReporte(listado,
          parametros, reporte);
      streamContent = generador.obtenerStreamContentDeOutputStream(streamSalida,
          "application/pdf", nombreArchivo);
    } catch (Exception excepcion) {
      log.error("--> generateReporte() -  Error generando reporte. ", excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de reporte", "Hubo un error al tratar de generar el reporte.",null);
    } //Fin de try-catch
    return new ContenidoMultimedia(streamContent, streamSalida);
  } //Fin del metodo generateReporte
} //Fin de la clase Bean