package com.geopractic.cobranza.bean;

import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.bean.BeanReportes;
import com.geopractic.sistema.modelo.ContenidoMultimedia;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bean de generacion de reporte de gestiones de empleado.
 * @author Cristhian
 */
@ManagedBean (name = "rptListGestionEmp")
@ViewScoped
public class ReporteListGestionEmp extends BeanReportes implements Serializable {

  private static final long serialVersionUID = 6066451665126506047L;
  private Date fechaBusqueda;
  private Empleado empleado;

  /**
   * Constructor sin argumentos.
   */
  public ReporteListGestionEmp() {

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

  @Override
  public void generarParametros() {
    parametros = new HashMap<String, Object>();
    parametros.put("FECHA", fechaBusqueda);
    parametros.put("CODIGO_EMP", empleado.getId());
  } //Fin de metodo generarParametros

  /**
   * Genera un reporte con las rendiciones de una fecha dada.
   */
  public void generarReporteGestionEmpleado() {
    ContenidoMultimedia contenido = generateReporte("listaGestionEmpleado",
          "listaGestionEmpleado.pdf");
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
  } //Fin del generarReporteGestionEmpleado
} //Fin de la clase ReporteListGestionEmp