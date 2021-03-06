package com.geopractic.georrefencia.bean;

import com.geopractic.bean.Bean;
import com.geopractic.georrefencia.ControladorMapa;
import com.geopractic.georrefencia.ControladorPanelVisualizacion;
import com.geopractic.georrefencia.modelo.Mapa;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;
import com.geopractic.sistema.bean.SesionBean;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.RowEditEvent;

/**
 * Bean de administracion de paneles de visualizacion del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarPanelVisualizacion")
@ViewScoped
public class AdministrarPanelVisualizacion extends Bean<PanelVisualizacion>
    implements Serializable {

  private static final long serialVersionUID = -6995134098827714380L;
  private Mapa mapa;
  private List<Mapa> listaMapas;
  private List<Mapa> mapasAgregar;
  private List<Mapa> mapasEliminar;
  private String url;

  /**
   * Constructor sin argumentos.
   */
  public AdministrarPanelVisualizacion() {
  
  } //Fin de constructor

  public Mapa getMapa() {
    return mapa;
  } //Fin del metodo getMapa

  public void setMapa(Mapa mapa) {
    this.mapa = mapa;
  } //Fin del metodo setMapa

  public List<Mapa> getListaMapas() {
    return listaMapas;
  } //Fin del metodo getListaMapas

  public void setListaMapas(List<Mapa> listaMapas) {
    this.listaMapas = listaMapas;
  } //Fin del metodo listaMapas

  public String getUrl() {
    return url;
  } //Fin del metodo getUrl

  public void setUrl(String url) {
    this.url = url;
  } //Fin del metodo setUrl

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new PanelVisualizacion();
    this.listaMapas = new ArrayList<Mapa>();
    this.mapa = new Mapa();
    this.mapasAgregar = new ArrayList<Mapa>();
    this.mapasEliminar = new ArrayList<Mapa>();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorPanelVisualizacion();
  } //Fin del metodo prepararControlador

  @Override
  public void seleccionar(PanelVisualizacion panelSeleccionado) {
    contexto = FacesContext.getCurrentInstance();
    HttpServletRequest  peticion = (HttpServletRequest) contexto.getExternalContext().getRequest();
    try {
      setUrl(new URL(peticion.getScheme(),
                  peticion.getServerName(),
                  peticion.getServerPort(),
                  peticion.getContextPath()).toString() + "/panel.xhtml?panel="
                  + panelSeleccionado.getId());
    } catch (MalformedURLException excepcion) {
      log.error("--> seleccionar() - Error en formar la URL ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "No se pudo formar la URL del panel.",null);
    } //Fin de try-catch
    this.listaMapas = panelSeleccionado.getListaMapas();
    super.seleccionar(panelSeleccionado);
  } //Fin del metodo seleccionar

  /**
   * Agrega un mapa seleccionado al listado de mapas.
   */
  public void agregarMapa() {
    if (this.listaMapas.size() < 6) {
      listaMapas.add(mapa);
      this.mapasAgregar.add(mapa);
      this.mapa = new Mapa();
    } //Fin de if
  } //Fin del metodo agregarMapa

  /**
   * Elimina un mapa del listado de mapas.
   * @param mapa el mapa a eliminar.
   */
  public void eliminarMapa(Mapa mapa) {
    this.listaMapas.remove(mapa);
    this.mapasEliminar.add(mapa);
  } //Fin del metodo eliminarMapa

  /**
   * Guarda los cambios en el mapa editado.
   * @param evento el evento ocurrido.
   */
  public void editarMapa(RowEditEvent evento) {
    prepararVariables();
    Mapa mapaEditado = (Mapa) evento.getObject();
    ControladorMapa controladorMapa = new ControladorMapa();
    try {
      boolean resultado = controladorMapa.modificar(mapaEditado);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
               "Mapas actualizados", "Los mapas fueron actualizados",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
                "Error de modificacion", "Los mapas no pudieron se modificados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> editarMapa() - Error en editar mapa de panel ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La edicion de los mapas no puedo realizarse.","mensajeMenus");
    } finally {
      try {
        controladorMapa.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- editarMapa() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo editarMapa

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando panel nuevo");
    try {
      //Verifica si no existe el panel ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El panel ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El panel ya existe.",null);
      } else if (this.listaMapas.size() > 6) {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El panel no puede tener mas de 6 rastreadores.",null);
      } else if (this.listaMapas.size() <= 0) {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El panel debe contener al menos un rastreador.",null);
      } else {
        //Obtiene la sesion actual
        SesionBean sesion = (SesionBean) contexto.getApplication()
            .evaluateExpressionGet(contexto, "#{sesion}", SesionBean.class);
        this.entidad.setEmpleadoCreador(sesion.getUsuario().getEmpleado());
        this.entidad.setListaMapas(listaMapas);
        boolean resultado = controlador.guardar(entidad);

        ControladorMapa controladorMapa = new ControladorMapa();
        resultado = controladorMapa.guardarMapasPanel(listaMapas, entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Panel guardado", "Nuevo panel guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El nuevo panel no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Panel de Visualizacion ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El nuevo panel de visualizacion no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo guardar

  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      //Verifica si hay mapas para agregar
      if (this.mapasAgregar.size() > 0) {
        ControladorMapa controladorMapa = new ControladorMapa();
        resultado = controladorMapa.guardarMapasPanel(mapasAgregar,entidad);
      } //Fin de if
      //Verifica si hay mapas para eliminar
      if (this.mapasEliminar.size() > 0) {
        for (Mapa mapa : mapasEliminar) {
          ControladorMapa controladorMapa = new ControladorMapa();
          resultado = controladorMapa.eliminar(mapa);
        } //Fin de for
      } //Fin de if
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios del panel guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar PanelVisualizacion. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Los cambios no pudieron ser guardados.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- modificar() - Error cerrar recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo modificar

  @Override
  public void eliminar(PanelVisualizacion seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando panel visualizacion");
    this.entidad = seleccion;
    try {
      boolean resultado = controlador.eliminar(entidad);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Panel eliminado", "El panel a sido eliminado.",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "El panel no pudo ser eliminado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Panel Visualizacion",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El panel de visualizacion no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo eliminar

} //Fin de la clase AdministrarPanelVisualizacion