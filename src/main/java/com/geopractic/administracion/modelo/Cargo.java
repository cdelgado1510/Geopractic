package com.geopractic.administracion.modelo;

/**
 * Clase que representa cada cargo dentro del sistema.
 * @author Cristhian
 *
 */
public class Cargo {

  private long id;
  private String descripcion;

  /**
   * Constructor sin argumentos.
   */
  public Cargo() {

  } //Fin del constructor

  /**
   * Constructor que establece una descripcion pre cargada.
   * @param descripcion el cargo a cargar al objeto.
   */
  public Cargo(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del constructor

  /**
   * Devuelve el identificador del cargo.
   * @return el identificador del cargo.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del cargo.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la descripcion del cargo.
   * @return la descripcion del cargo.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion del cargo.
   * @param descripcion la descripcion a establecer.
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
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Cargo)) {
      return false;
    }
    Cargo other = (Cargo) obj;
    if (descripcion == null) {
      if (other.descripcion != null) {
        return false;
      }
    } else if (!descripcion.equals(other.descripcion)) {
      return false;
    }
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return descripcion;
  } //Fin de toString

} //Fin de la clase Cargo