package com.geopractic.cobranza.modelo;

/**
 * Clase que representa cada las formas de pago disponibles.
 * @author Cristhian
 *
 */
public class FormaDePago {

  private long id;
  private String descripcion;

  /**
   * Constructor sin argumetnos.
   */
  public FormaDePago() {

  } //Fin del constructor

  /**
   * Devuelve el identificador de la forma de pago.
   * @return el identificador de la forma de pago.
   */
  public long getId() {
    return id;
  }  //Fin del metodo getId

  /**
   * Establece el identificador de la forma de pago.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la descripcion de la forma de pago.
   * @return la descripcion de la forma de pago.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion de la forma de pago.
   * @param descripcion la descripcion de la forma de pago.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

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
    if (!(obj instanceof FormaDePago)) {
      return false;
    } //Fin de if
    FormaDePago other = (FormaDePago) obj;
    if (descripcion == null) {
      if (other.descripcion != null) {
        return false;
      } //Fin de if
    } else if (!descripcion.equals(other.descripcion)) {
      return false;
    } //Fin de if
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return getId() + " " + getDescripcion();
  } //Fin de toString

  
} //Fin de clase FormaDePago