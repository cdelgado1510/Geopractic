package com.geopractic.cobranza.modelo;

/**
 * Clase que representa los motivos de las gestiones de visitas realizadas.
 * @author Cristhian
 *
 */
public class Motivo {

  private long id;
  private String descripcion;
  private boolean activo;
  private boolean ruteo;

  /**
   * Constructor sin argumentos.
   */
  public Motivo() {

  } //Fin del constructor

  /**
   * Devuelve el identificador del motivo.
   * @return el identificador del motivo.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del motivo.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la descripcion del motivo.
   * @return la descripcion del motivo.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion del motivo.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  /**
   * Devuelve si el motivo se encuentra activo.
   * @return si el motivo esta activo o no.
   */
  public boolean isActivo() {
    return activo;
  } //Fin del metodo isActivo

  /**
   * Establece si el motivo esta activo.
   * @param activo si o no esta activo.
   */
  public void setActivo(boolean activo) {
    this.activo = activo;
  } //Fin del metodo setActivo

  /**
   * Devuelve si el motivo genera un ruteo.
   * @return si es un motivo de ruteo.
   */
  public boolean isRuteo() {
    return ruteo;
  } //Fin del metodo isRuteo

  /**
   * Establece si el motivo genera un ruteo.
   * @param ruteo si el ruteo esta activo o no
   */
  public void setRuteo(boolean ruteo) {
    this.ruteo = ruteo;
  } //Fin del metodo setRuteo

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
    if (!(obj instanceof Motivo)) {
      return false;
    } //Fin de if
    Motivo other = (Motivo) obj;
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
    return id + " - " + descripcion;
  } //Fin de toString

} //Fin de la clase Motivo