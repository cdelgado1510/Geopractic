package com.geopractic.gps.modelo;

import com.geopractic.administracion.modelo.Empleado;

/**
 * Clase que representa cada rastreador GPS dentro del sistema.
 * @author Cristhian
 *
 */
public class Rastreador {

  private long id;
  private String imei;
  private String descripcion;
  private EstadosRastreadorGps estado;
  private Empleado empleadoAsignado;

  /**
   * Constructor sin argumentos.
   */
  public Rastreador() {

  } //Fin de constructor

  /**
   * Devuelve el identificador del rastreador.
   * @return el identificador del rastreador.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del rastreador.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el numero de imei del rastreador.
   * @return el imei del aparato.
   */
  public String getImei() {
    return imei;
  } //Fin del metodo getImei

  /**
   * Establece el numero de imei del rastreador.
   * @param imei el imei a establecer.
   */
  public void setImei(String imei) {
    this.imei = imei;
  } //Fin del metodo setImei

  /**
   * Devuelve la descripcion del rastreador.
   * @return la descripcion del rastreador.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion del rastreador.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  /**
   * Devuelve el estado asignado al rastreador.
   * @return el estado asignado.
   */
  public EstadosRastreadorGps getEstado() {
    return estado;
  } //Fin del metodo getEstado

  /**
   * Asigna un estado al rastreador.
   * @param estado el estado a asignar.
   */
  public void setEstado(EstadosRastreadorGps estado) {
    this.estado = estado;
  } //Fin del metodo setEstado

  /**
   * Devuelve el empleado asignado al rastreador.
   * @return el empleado asignado.
   */
  public Empleado getEmpleadoAsignado() {
    return empleadoAsignado;
  } //Fin del metodo getEmpleadoAsignado

  /**
   * Asigna un empleado al rastreador. 
   * @param empleadoAsignado el empleado a asignar.
   */
  public void setEmpleadoAsignado(Empleado empleadoAsignado) {
    this.empleadoAsignado = empleadoAsignado;
  } //Fin del metodo setEmpleadoAsignado

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((imei == null) ? 0 : imei.hashCode());
    return result;
  } //Fin de hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof Rastreador)) {
      return false;
    } //Fin de if
    Rastreador other = (Rastreador) obj;
    if (imei == null) {
      if (other.imei != null) {
        return false;
      } //Fin de if
    } else if (!imei.equals(other.imei)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return id + " IMEI: " + imei + " " + descripcion;
  } //Fin de toString

} //Fin de la clase Rastreador