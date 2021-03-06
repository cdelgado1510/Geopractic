package com.geopractic.sistema.modelo;

/**
 * Tipos de documentos asignados al sistema.
 * @author Cristhian
 *
 */
public class TipoDocumento {

  private long id;
  private String descripcion;

  /**
   * Constructor sin argumentos.
   */
  public TipoDocumento() {

  } //Fin del constructor

  /**
   * Devuelve el codigo de identificador del tipo de documento.
   * @return el identificador del tipo de documento.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del tipo de documento.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId 

  /**
   * Devuelve la descripcion del tipo de documento.
   * @return la descripcion del tipo de documento.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Devuelve la descripcion del tipo de documento.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  @Override
  public String toString() {
    return id + " " + descripcion;
  } //Fin del metodo toString

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
    return result;
  } //Fin del metodo hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof TipoDocumento)) {
      return false;
    }
    TipoDocumento other = (TipoDocumento) obj;
    if (descripcion == null) {
      if (other.descripcion != null) {
        return false;
      }
    } else if (!descripcion.equals(other.descripcion)) {
      return false;
    }
    return true;
  } //Fin del metodo equals

} //Fin de la clase TipoDocumento