package com.geopractic.cobranza.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.bean.BeanReportes;
import com.geopractic.cobranza.ControladorRuteoCobrador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.georrefencia.modelo.GeneradorJson;
import com.geopractic.sistema.modelo.ContenidoMultimedia;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bean de generacion de reporte de ruteo de cobrador.
 * @author Cristhian
 */
@ManagedBean (name = "rptRuteoCobrador")
@ViewScoped
public class ReporteRuteoCobrador extends BeanReportes implements Serializable {

  private static final long serialVersionUID = -5140104946769367180L;
  private Date fechaBusqueda;
  private Empleado empleado;
  private String geoJson;

  /**
   * Constructor sin argumentos.
   */
  public ReporteRuteoCobrador() {

  } //Fin de constructor

  /**
   * Devuelve al fecha de busqueda a utilizar.
   * @return la fecha de busqueda utilizada.
   */
  public Date getFechaBusqueda() {
    return fechaBusqueda;
  } //Fin del metodo getFechaBusqueda

  /**
   * Establece la fecha de busqueda a utilizar.
   * @param fechaBusqueda la fecha a establecer.
   */
  public void setFechaBusqueda(Date fechaBusqueda) {
    this.fechaBusqueda = fechaBusqueda;
  } //Fin del metodo setFechaBusqueda

  /**
   * Devuelve el empleado buscado.
   * @return el empleado buscado.
   */
  public Empleado getEmpleado() {
    return empleado;
  } //Fin del metodo getEmpleado

  /**
   * Establece el empleado a buscar.
   * @param empleado el empleado a establecer.
   */
  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  } //Fin del metodo setEmpleado

  public String getGeoJson() {
    return geoJson;
  } //Fin del metodo getGeoJson

  public void setGeoJson(String geoJson) {
    this.geoJson = geoJson;
  } //Fin del metodo setGeoJson

  @Override
  public void generarParametros() {
    parametros = new HashMap<String, Object>();
    parametros.put("Fecha", fechaBusqueda);
    parametros.put("Empleado", empleado.getId());
  } //Fin de metodo generarParametros

  /**
   * Genera un reporte con el ruteo de una fecha dada
   * de un cobrador.
   */
  public void generarReporteRuteoCobrador() {
    ContenidoMultimedia contenido = generateReporte("ruteoCobrador",
          "ruteoCobrador.pdf");
    setMultimedia(contenido.getStreamContent());

    prepararVariables();
    //Verifica si el reporte fue cargado
    if (multimedia != null) {
      contexto.getExternalContext().getSessionMap().put("reporteBytesArray",
           contenido.getByteArrayStream().toByteArray());
      buscarRecorrido();
    } else {
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de reporte", "No se puede mostrar el reporte.",null);
    } //Fin de if
  } //Fin del generarReporteRuteoCobrador

  /**
   * Busca el recorrido del empleado seleccionado.
   */
  public void buscarRecorrido() {
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    log.debug("--> buscarRecorrido() - Consultando recorrido de rastreo");
    try {
      log.debug("Obteniendo recorrido de rastreador y ruteo.");
      log.debug("Generando GeoJson de ruteos y recorrido.");
      geoJson = GeneradorJson.generarGeoJson(controladorRuteo.listarRuteoCobradorDia(
            this.empleado,
            this.fechaBusqueda, this.fechaBusqueda));
    } catch (JsonProcessingException excepcion) {
      log.error("--> buscarRecorrido() - Error procesando JSON de la ruta. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "Error al procesar el recorrido de la ruta.",null);
    } catch (ExcepcionControlador excepcion) {
      log.error("--> buscarRecorrido() - Error en obtener la ruta. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "No es posible obtener el recorrido.",null);
    } finally {
      try {
        controladorRuteo.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- buscarRecorrido() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch

    log.debug("Ruteo en GeoJson" + geoJson);
  } //Fin del metodo buscarRecorrido
} //Fin de la clase ReporteRuteoCobrador