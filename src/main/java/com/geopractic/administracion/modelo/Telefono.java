package com.geopractic.administracion.modelo;

import com.geopractic.sistema.modelo.TipoTelefono;

/**
 * Clase que representa un numero de telefono.
 * @author Cristhian
 *
 */
public class Telefono {

  private long id;
  private String numero;
  private TipoTelefono tipoTelefono;

  public Telefono() {

  } //Fin de constructor sin argumentos

  public long getId() {
    return id;
  } //Fin del metodo getId

  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el numero de telefono.
   * @return el numero de telefono.
   */
  public String getNumero() {
    return numero;
  } //Fin del metodo getNumero

  /**
   * Establece el numero de telefono.
   * @param numero el numero a establecer.
   */
  public void setNumero(String numero) {
    this.numero = numero;
  } //Fin del metodo setNumero

  /**
   * Devuelve el tipo de numero de telefono.
   * @return el tipo del numero de telefono.
   */
  public TipoTelefono getTipoTelefono() {
    return tipoTelefono;
  } //Fin del metodo getTipoTelefono

  /**
   * Establece el tipo de numero telefonico.
   * @param tipoTelefono el tipo de numero telefonico a establecer.
   */
  public void setTipoTelefono(TipoTelefono tipoTelefono) {
    this.tipoTelefono = tipoTelefono;
  } //Fin del metodo setTipoTelefono

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
    if (!(obj instanceof Telefono)) {
      return false;
    }
    Telefono other = (Telefono) obj;
    if (numero == null) {
      if (other.numero != null) {
        return false;
      }
    } else if (!numero.equals(other.numero)) {
      return false;
    }
    return true;
  } //Fin del metodo equals

  @Override
  public String toString() {
    return tipoTelefono + ": " + numero;
  } //Fin del metodo toString

} //Fin de la clase Telefono.