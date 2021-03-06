package com.geopractic.sistema.modelo;

/**
 * Clase que representa los tipos de telefono.
 * @author Cristhian
 *
 */
public class TipoTelefono {

  private long id;
  private String nombre;
  private String patron;

  /**
   * Constructor sin argumentos.
   */
  public TipoTelefono() {

  } //Fin de constructor sin argumentos
  
  public long getId() {
    return id;
  } //Fin del metodo getId

  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el nombre del tipo de telefono.
   * @return el nombre del tipo de telefono.
   */
  public String getNombre() {
    return nombre;
  } //Fin del metodo getNombre

  /**
   * Establece el nombre del tipo de telefono.
   * @param nombre el nombre a asignar.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  } //Fin del metodo setNombre

  /**
   * Devuelve el patron utilizado para mostrar para este tipo de telefono.
   * @return el patro del tipo de telefono.
   */
  public String getPatron() {
    return patron;
  } //Fin del metodo getPatron

  /**
   * Establece el patron a utilizar para mostrar este tipo de telefono.
   * @param patron el patron a establecer.
   */
  public void setPatron(String patron) {
    this.patron = patron;
  } //Fin del metodo setPatron

  @Override
  public String toString() {
    return "Tipo Telefono " + nombre + "  patron [" + patron + "]";
  } //Fin del metodo toString

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((patron == null) ? 0 : patron.hashCode());
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
    if (!(obj instanceof TipoTelefono)) {
      return false;
    }
    TipoTelefono other = (TipoTelefono) obj;
    if (nombre == null) {
      if (other.nombre != null) {
        return false;
      }
    } else if (!nombre.equals(other.nombre)) {
      return false;
    }
    if (patron == null) {
      if (other.patron != null) {
        return false;
      }
    } else if (!patron.equals(other.patron)) {
      return false;
    }
    return true;
  } //Fin del metodo equals
  
} //Fin de la clase TipoTelefono