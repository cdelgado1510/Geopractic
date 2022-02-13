package com.geopractic.cobranza.bean;

import com.geopractic.bean.BeanReportes;
import com.geopractic.sistema.modelo.ContenidoMultimedia;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bean de generacion de reporte de rendicion de Cobranza.
 * @author Cristhian
 */
@ManagedBean (name = "rptRendicionCobranza")
@ViewScoped
public class ReporteRendicionCobranza extends BeanReportes implements Serializable {

  private static final long serialVersionUID = 6914513547463844117L;
  private Date fechaBusqueda;

  /**
   * Constructor sin argumentos.
   */
  public ReporteRendicionCobranza() {

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

  @Override
  public void generarParametros() {
    parametros = new HashMap<String, Object>();
    parametros.put("FECHA", fechaBusqueda);
  } //Fin de metodo generarParametros

  /**
   * Genera un reporte con las rendiciones de una fecha dada.
   */
  public void generarReporteRendicionCobranza() {
    ContenidoMultimedia contenido = generateReporte("rendicionCobranza",
          "rendicionCobranza.pdf");
    setMultimedia(contenido.getStreamContent());

    prepararVariables();
    //Verifica si el reporte fue cargado
    if (multimedia != null) {
      contexto.getExternalContext().getSessionMap().put("reporteBytesArray",
           contenido.getByteArrayStream().toByteArray());
    } else {
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de reporte", "No se puede mostrar el reporte.",null);
    } //Fin de if
  } //Fin del generarReporteRendicionCobranza
} //Fin de la clase ReporteRendicionCobranza