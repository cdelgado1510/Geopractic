package com.geopractic.cobranza.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
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
 * Bean de generacion de reporte de ruteos generales.
 * @author Cristhian
 */
@ManagedBean (name = "rptRuteoGeneral")
@ViewScoped
public class ReporteRuteoGeneral extends BeanReportes implements Serializable {

  private static final long serialVersionUID = -5140104946769367180L;
  private Date fechaBusqueda;
  private String geoJson;

  /**
   * Constructor sin argumentos.
   */
  public ReporteRuteoGeneral() {

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
  } //Fin de metodo generarParametros

  /**
   * Genera un reporte con el ruteo de una fecha dada
   * de un cobrador.
   */
  public void generarReporteRuteosGenerales() {
    ContenidoMultimedia contenido = generateReporte("ruteosGenerales",
          "ruteosGenerales.pdf");
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
   * Busca el recorrido del dia.
   */
  public void buscarRecorrido() {
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    log.debug("--> buscarRecorrido() - Consultando recorrido de rastreo");
    try {
      log.debug("Obteniendo recorrido de rastreador y ruteo.");
      log.debug("Generando GeoJson de ruteos y recorrido.");
      geoJson = GeneradorJson.generarGeoJson(controladorRuteo.listarRuteoDia(
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