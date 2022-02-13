package com.geopractic.gps.modelo;

/**
 * Clase que representa todos los estados de los GPS.
 * @author Cristhian
 *
 */
public class EstadosRastreadorGps {

  private long id;
  private String descripcion;

  /**
   * Constructor sin argumentos.
   */
  public EstadosRastreadorGps() {

  } //Fin de constructor sin argumentos.

  /**
   * Devuelve el identificador del estado.
   * @return el identificador del estado.
   */
  public long getId() {
    return id;
  } //Fin de metodo getId

  /**
   * Establece el identificador asignado al estado.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin de metodo setId

  /**
   * Devuelve la descripcion del estado.
   * @return la descripcion del estado.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin de metodo getDescripcion

  /**
   * Establece la descripcion del estado.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin de metodo setDescripcion

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
    if (!(obj instanceof EstadosRastreadorGps)) {
      return false;
    } //Fin de if
    EstadosRastreadorGps other = (EstadosRastreadorGps) obj;
    if (descripcion == null) {
      if (other.descripcion != null) {
        return false;
      } //Fin de if
    } else if (!descripcion.equals(other.descripcion)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return id + " " + descripcion;
  } //Fin de toString

} //Fin de la clase EstadosRastreadorGps