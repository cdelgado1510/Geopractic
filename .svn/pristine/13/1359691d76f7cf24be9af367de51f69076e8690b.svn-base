package com.geopractic.bean;

import com.geopractic.sistema.modelo.ContenidoMultimedia;
import com.geopractic.util.GenerarReporte;

import java.io.ByteArrayOutputStream;

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
 * implementados por todos los Bean para mostrar reportes del sistema.
 * @author Cristhian
 */
public abstract class BeanReportes implements IReporteable {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  protected FacesMessage mensaje;
  protected FacesContext contexto;
  protected PrimeFaces contextoPeticion;
  protected Map<String, Object> parametros;
  protected StreamedContent multimedia;

  /**
   * Constructor sin argumentos.
   */
  public BeanReportes() {

  } //Fin de constructor

  /**
   * Devuelve el objeto multimedia del bean.
   * @return el objeto multimedia en forma de un StreamedContent.
   */
  public StreamedContent getMultimedia() {
    return multimedia;
  } //Fin del metodo getMultimedia

  @Override
  public void prepararVariables() {
    mensaje = null;
    contexto = FacesContext.getCurrentInstance();
    contextoPeticion = PrimeFaces.current();
  } //Fin del metodo prepararVariables

  /**
   * Establece un objeto multimedia.
   * @param multimedia el objeto multimedia a establecer en forma de StreamedContent
   */
  public void setMultimedia(StreamedContent multimedia) {
    this.multimedia = multimedia;
  } //Fin del metodo setMultimedia

  @Override
  public void agregarMensaje(Severity severidad, String resumen, String detalle,String componente) {
    mensaje = new FacesMessage(severidad,resumen,detalle);
    if (componente == null) {
      contexto.addMessage(componente, mensaje);
    } else {
      contexto.addMessage("mensajes", mensaje);
    }
    
  } //Fin del metodo agregarMensaje

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
  public ContenidoMultimedia generateReporte(String reporte,String nombreArchivo) {
    DefaultStreamedContent  streamContent = null;
    ByteArrayOutputStream streamSalida = null;
    try {
      generarParametros();
      GenerarReporte generador = new GenerarReporte();
      streamSalida = generador.obtenerOutputStreamDeReporte(parametros, reporte);
      streamContent = generador.obtenerStreamContentDeOutputStream(streamSalida,
          "application/pdf", nombreArchivo);
    } catch (Exception excepcion) {
      log.error("--> generateReporte() -  Error generando reporte. ", excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de reporte", "Hubo un error al tratar de generar el reporte.",null);
    } //Fin de try-catch
    return new ContenidoMultimedia(streamContent, streamSalida);
  } //Fin del metodo generateReporte

} //Fin de la clase BeanReportes