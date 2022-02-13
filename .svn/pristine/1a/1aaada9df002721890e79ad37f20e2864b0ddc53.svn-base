package com.geopractic.util;

/**
 * Clase que representa la columna dentro de la base de datos.
 * @author Cristhian
 */
public class Columna {

  private String nombre;
  private String parametro;
  private boolean esPk; 

  /**
   * Constructor sin argumentos .
   */
  public Columna() {
    this.esPk = false;
  } //Fin de constructor sin argumentos

  /**
   * Constructor con argumetnos.
   * @param nombre el nombre de la columna
   * @param parametro el parametro del valor de la columna
   */
  public Columna(String nombre, String parametro, boolean esPk) {
    this.nombre = nombre;
    this.parametro = parametro;
    this.esPk = esPk;
  } //Fin del metodo Columna

  public String getNombre() {
    return nombre;
  } //Fin del metodo getNombre

  public void setNombre(String nombre) {
    this.nombre = nombre;
  } //Fin del metodo setNombre

  public String getParametro() {
    return parametro;
  } //Fin del metodo getParametro

  public void setParametro(String parametro) {
    this.parametro = parametro;
  } //Fin del metodo setParametro

  public boolean isEsPk() {
    return esPk;
  } //Fin del metodo isEsPk

  public void setEsPk(boolean esPk) {
    this.esPk = esPk;
  } //Fin del metodo setEsPk

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    Columna other = (Columna) obj;
    if (nombre == null) {
      if (other.nombre != null) {
        return false;
      }
    } else if (!nombre.equals(other.nombre)) {
      return false;
    }
    return true;
  } //Fin de equals
  
} //Fin de la clase Columna