package com.geopractic.georrefencia.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.geopractic.bean.Bean;
import com.geopractic.georrefencia.ControladorGeorreferencia;
import com.geopractic.georrefencia.modelo.GeneradorJson;
import com.geopractic.georrefencia.modelo.Georreferencia;
import com.geopractic.georrefencia.modelo.Punto;
import com.geopractic.georrefencia.modelo.Zona;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javaxt.io.Image;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 * Bean de administracion de georreferencias del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarGeorreferencia")
@ViewScoped
public class AdministrarGeorreferencia extends Bean<Georreferencia> implements Serializable {

  private static final long serialVersionUID = -2214227292749442600L;
  private Punto punto;
  private List<Punto> listaPuntos;
  private double latitud;
  private double longitud;
  private String geoJson;
  private String iconoAbm;
  private String contornoZona;
  private String rellenoZona;
  private UploadedFile archivo;

  /**
   * Constructor sin argumentos. 
   */
  public AdministrarGeorreferencia() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Georreferencia();
    listaPuntos = new ArrayList<Punto>();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorGeorreferencia();
  } //Fin del metodo prepararControlador

  @Override
  public void seleccionar(Georreferencia objetoSeleccionado) {
    //Verifica si selecciono una zona y setea el listado de puntos.
    if (objetoSeleccionado.getTipo().getClase().equals("Zona")) {
      setListaPuntos(objetoSeleccionado.getZona().getPuntos());;
      setContornoZona(objetoSeleccionado.getTipo().getColorContorno());
      setRellenoZona(objetoSeleccionado.getTipo().getColorRelleno());
    } else {
      setIconoAbm(objetoSeleccionado.getTipo().getIcono());;
    } //Fin de if
    super.seleccionar(objetoSeleccionado);
  } //Fin del metodo seleccionar

  /**
   * Genera el GeoJson de la Geometria seleccionada.
   * @param georreferencia la georreferencia a convertir.
   */
  public void obtenerGeoJson(Georreferencia georreferencia) {
    this.entidad = georreferencia;
    try {
      this.geoJson = GeneradorJson.generarGeoJson(georreferencia);
    } catch (JsonProcessingException excepcion) {
      log.error("--> obtenerGeoJson() - Error en generando GeoJson ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "Hubo un error al generer el GeoJson.",null);
    } //Fin de try-catch
  } //Fin del metodo seleccionar

  /**
   * Devuelve la lista de puntos.
   * @return el listado de puntos.
   */
  public List<Punto> getListaPuntos() {
    return listaPuntos;
  } //Fin del metodo getListaPuntos

  /**
   * Establece el listado de puntos.
   * @param listaPuntos el listado a establecer.
   */
  public void setListaPuntos(List<Punto> listaPuntos) {
    this.listaPuntos = listaPuntos;
  } //Fin del metodo setListaPuntos

  /**
   * Devuelve el punto del listado de puntos.
   * @return el listado de puntos.
   */
  public Punto getPunto() {
    return punto;
  } //Fin del metodo getPunto

  /**
   * Establece el punto para el listado de puntos.
   * @param punto el punto a establecer.
   */
  public void setPunto(Punto punto) {
    this.punto = punto;
  } //Fin del metodo setPunto

  public double getLatitud() {
    return latitud;
  } //Fin del metodo getLatitud

  public void setLatitud(double latitud) {
    this.latitud = latitud;
  } //Fin del metodo setLatitud

  public double getLongitud() {
    return longitud;
  } //Fin del metodo getLongitud

  public void setLongitud(double longitud) {
    this.longitud = longitud;
  } //Fin del metodo setLongitud

  public String getIconoAbm() {
    return iconoAbm;
  } //Fin del metodo iconoAbm

  public void setIconoAbm(String iconoAbm) {
    this.iconoAbm = iconoAbm;
  } //Fin del metodo iconoAbm

  public String getContornoZona() {
    return contornoZona;
  } //Fin del metodo getContornoZona

  public void setContornoZona(String contornoZona) {
    this.contornoZona = contornoZona;
  } //Fin del metodo setContornoZona

  public String getRellenoZona() {
    return rellenoZona;
  } //Fin del metodo getRellenoZona

  public void setRellenoZona(String rellenoZona) {
    this.rellenoZona = rellenoZona;
  } //Fin del metodo setRellenoZona

  public String getGeoJson() {
    return geoJson;
  } //Fin del metodo setGeoJson

  public void setGeoJson(String geoJson) {
    this.geoJson = geoJson;
  } //Fin del metodo setGeoJson

  public UploadedFile getArchivo() {
    return archivo;
  } //Fin del metodo getArchivo

  public void setArchivo(UploadedFile archivo) {
    this.archivo = archivo;
  } //Fin del metodo setArchivo

  /**
   * Genera una nueva zona o punto al seleccionar el 
   * tipo de georreferencia.
   */
  public void cambiarTipo() {
    if (entidad.getTipo().getClase().equals("Punto")) {
      this.entidad.setPunto(new Punto());
      this.entidad.setZona(null);
      this.latitud = 0.0;
      this.longitud = 0.0;
      setIconoAbm(this.entidad.getTipo().getIcono());;
    } else {
      punto = new Punto();
      listaPuntos = new ArrayList<Punto>();
      this.latitud = 0.0;
      this.longitud = 0.0;
      Zona zona = new Zona();
      zona.setPuntos(listaPuntos);
      this.entidad.setZona(zona);
      this.entidad.setPunto(null);
      setContornoZona(this.entidad.getTipo().getColorContorno());
      setRellenoZona(this.entidad.getTipo().getColorRelleno());
    } //Fin de if-else
  } //Fin del metodo cambiarTipo

  /**
   * Limpia los puntos de una zona.
   */
  public void limpiarPuntosZona() {
    this.listaPuntos.clear();
  } //Fin del metodo limpiarPuntosZona

  /**
   * Carga los puntos de una zona.
   */
  public void cargarPuntosZona() {
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    
    //Agrega la latitud y longitud deseados
    Map<String, String> map = externalContext.getRequestParameterMap();
    String zonaLatitud = map.get("latitud");
    String zonaLongitud = map.get("longitud");
    this.listaPuntos.add(new Punto(Double.parseDouble(zonaLatitud),
        Double.parseDouble(zonaLongitud)));
  } //Fin del metodo cargarListaPuntos

  /**
   * Convierte a formato GeoJson el listado de puntos cargados.
   */
  public void convertirGeoJsonListaPuntos() {
    try {
      this.geoJson = GeneradorJson.generarGeoJson(this.entidad);
    } catch (JsonProcessingException excepcion) {
      log.error("--> obtenerGeoJson() - Error en generando GeoJson de lista de puntos",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "Hubo un error al generer el GeoJson de la lista de puntos.",null);
    } //Fin de try-catch
  } //Fin del metodo convertirGeoJsonListaPuntos

  /**
   * Agrega un punto a la lista de puntos.
   */
  public void agregarPunto() {
    Punto puntoNuevo = new Punto(this.latitud, this.longitud);
    listaPuntos.add(puntoNuevo);
    this.latitud = 0.0;
    this.longitud = 0.0;
  } //Fin del metodo agregarPunto

  /**
   * Metodo de procesamiento de subida de archivo.
   * @param evento el evento ocurrido.
   */
  public void manejoSubidaArchivo(FileUploadEvent evento) {
    Image imagen = new Image((File) evento.getFile());

    double[] coord = imagen.getGPSCoordinate();
    if (coord != null) {
      setLatitud(coord[0]);
      setLongitud(coord[1]);
      agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Datos obtenidos", "Se extrajeron la latitud y longitud del archivo.",null);
    } else {
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de Imagen", "El archivo no posee datos de Latitud y Longitud.",null);
    } //Fin de if-else
  } //Fin del metodo manejoSubidaArchivo

  /**
   * Elimina un punto de la lista de puntos.
   * @param evento evento del punto a eliminar.
   */
  public void eliminarPunto(RowEditEvent evento) {
    listaPuntos.remove((Punto)evento.getObject());
  } //Fin del metodo eliminarPunto

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando georreferencia nueva");
    try {
      //Verifica el tipo de georreferencia
      if (entidad.getTipo().getClase().equals("Zona")) {
        //Controla la cantidad minima de puntos de una zona
        if (entidad.getZona().getPuntos().size() < 4) {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de Zona", "La zona debe tener al menos 4 puntos.",null);
          throw new Exception("La zona debe tener al menos 4 puntos.");
        } //Fin de if
      } //Fin de if
      //Verifica si no existe la georreferencia
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - La georreferencia ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "La georreferencia ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Georreferencia guardada", "Nueva georreferencia guardada.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "La nueva georreferencia no pudo ser guardada.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Georreferencia",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La nueva georreferencia no pudo ser guardada.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase guardar

  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      //Verifica el tipo de georreferencia
      if (entidad.getTipo().getClase().equals("Zona")) {
        //Controla la cantidad minima de puntos de una zona
        if (entidad.getZona().getPuntos().size() < 4) {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de Zona", "La zona debe tener al menos 4 puntos.",null);
          throw new Exception("La zona debe tener al menos 4 puntos.");
        } //Fin de if
      } //Fin de if
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios de georreferencia guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar georreferencia. ",excepcion);
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
  } //Fin de la clase modificar

  @Override
  public void eliminar(Georreferencia seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando georreferencia");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - La georreferencia ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "La georreferencia " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Georreferencia eliminada", "La georreferencia a sido eliminada.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "La georreferencia no pudo ser eliminada.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Georreferencia",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La georreferencia no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministrarGeorreferencia