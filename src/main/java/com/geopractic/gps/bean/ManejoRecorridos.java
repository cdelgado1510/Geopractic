package com.geopractic.gps.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.geopractic.cobranza.ControladorRuteoCobrador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.georrefencia.modelo.GeneradorJson;
import com.geopractic.gps.ConsultasOpenGts;
import com.geopractic.gps.modelo.Cuenta;
import com.geopractic.gps.modelo.Rastreador;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean de administracion y consulta de recorridos de dispositivos.
 * @author Cristhian
 *
 */
@ManagedBean (name = "controlRecorridos")
@ViewScoped
public class ManejoRecorridos {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  private FacesMessage mensaje;
  private FacesContext contexto;
  private PrimeFaces contextoPeticion;
  private Date fechaInicio;
  private Date fechaFin;
  private Rastreador rastreador;
  private String recorridoGps;
  private String ruteoDia;

  /**
   * Constructor sin argumentos.
   */
  public ManejoRecorridos() {

  } //Fin de constructor

  
  /**
   * Devuelve la fecha de inicio de la busqueda.
   * @return la fecha de inicio de la busqueda.
   */
  public Date getFechaInicio() {
    return fechaInicio;
  } //Fin del metodo getFechaInicio

  /**
   * Establece la fecha de inicio de la busqueda.
   * @param fechaInicio la fecha a establecer.
   */
  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  } //Fin del metodo setFechaInicio

  /**
   * Devuelve la fecha de fin de la busqueda.
   * @return la fecha de fin de la busqueda.
   */
  public Date getFechaFin() {
    return fechaFin;
  } //Fin del metodo getFechaFin

  /**
   * Establece la fecha de fin de la busqueda.
   * @param fechaFin la fecha a establecer.
   */
  public void setFechaFin(Date fechaFin) {
    this.fechaFin = fechaFin;
  } //Fin del metodo setFechaFin

  /**
   * Devuelve el rastreador a buscar.
   * @return el rastreador a buscar.
   */
  public Rastreador getRastreador() {
    return rastreador;
  } //Fin del metodo getRastreador

  /**
   * Establece el rastreador a buscar.
   * @param rastreador el rastreador a establecer.
   */
  public void setRastreador(Rastreador rastreador) {
    this.rastreador = rastreador;
  } //Fin del metodo setRastreador

  /**
   * Devuelve el GeoJson con el recorrido del GPS.
   * @return el recorrido en formato GeoJson
   */
  public String getRecorridoGps() {
    return recorridoGps;
  } //Fin del metodo getRecorridoGps

  /**
   * Establece el GeoJson del recorrido del GPS.
   * @param recorridoGps el recorrido a estabecer.
   */
  public void setRecorridoGps(String recorridoGps) {
    this.recorridoGps = recorridoGps;
  } //Fin del metodo setRecorridoGps

  /**
   * Devuelve el GeoJson con el ruteo asignado al dia.
   * @return el ruteo asignado en formato GeoJson.
   */
  public String getRuteoDia() {
    return ruteoDia;
  } //Fin del metodo getRuteoDia

  /**
   * Establece el GeoJson del ruteo del dia.
   * @param ruteoDia el ruteo a establecer.
   */
  public void setRuteoDia(String ruteoDia) {
    this.ruteoDia = ruteoDia;
  } //Fin del metodo setRuteoDia

  @PostConstruct
  public void init() {

  } //Fin del metodo init

  /**
   * Inicia las variables a utilizar.
   */
  public void prepararVariables() {
    mensaje = null;
    contexto = FacesContext.getCurrentInstance();
    contextoPeticion = PrimeFaces.current();
  } //Fin del metodo prepararVariables

  /**
   * Agrega un mensaje al contexto.
   * @param severidad la severidad del mensaje.
   * @param resumen el resumen o titulo del mensaje.
   * @param detalle el detalle del mensaje.
   * @param componente el componente al mostrar el mensaje , en caso de ser nulo es "mensajes".
   */
  public void agregarMensaje(Severity severidad, String resumen, String detalle,String componente) {
    mensaje = new FacesMessage(severidad,resumen,detalle);
    if (componente == null) {
      contexto.addMessage(componente, mensaje);
    } else {
      contexto.addMessage("mensajes", mensaje);
    } //Fin de if-else
  } //Fin del metodo agregarMensaje

  public void resetear(String objeto) {
    contextoPeticion.resetInputs(objeto);
  } //Fin del metodo cerrarDialogo

  public void abrirDialogo(String dialogo) {
    contextoPeticion.executeScript("PF('" + dialogo + "').show();");
  } //Fin del metodo abrirDialogo

  public void cerrarDialogo(String dialogo) {
    contextoPeticion.executeScript("PF('" + dialogo + "').hide();");
  } //Fin del metodo cerrarDialogo

  /**
   * Busca el recorrido del rastreador seleccionado.
   * @param actionEvent el actionEvent del boton.
   */
  public void buscarRecorrido(ActionEvent actionEvent) {
    prepararVariables();
    ConsultasOpenGts consulta = new ConsultasOpenGts();
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    log.debug("--> buscarRecorrido() - Consultando recorrido de rastreo");
    try {
      log.debug("Obteniendo recorrido de rastreador y ruteo.");
      Cuenta recorrido = consulta.obtenerRecorrido(rastreador, fechaInicio, fechaFin);
      log.debug("Generando GeoJson de ruteos y recorrido.");
      recorridoGps = GeneradorJson.generarGeoJson(recorrido);
      ruteoDia = GeneradorJson.generarGeoJson(controladorRuteo.listarRuteoCobradorDia(
            this.rastreador.getEmpleadoAsignado(),
            this.fechaInicio, this.fechaFin));
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

    log.debug("Recorrido en GeoJson" + recorridoGps);
    log.debug("Ruteo en GeoJson" + ruteoDia);
    contextoPeticion.executeScript("cargarVisor(" + recorridoGps + "," + ruteoDia + ");");
  } //Fin del metodo buscarRecorrido

} //Fin de la clase ManejoRecorridos