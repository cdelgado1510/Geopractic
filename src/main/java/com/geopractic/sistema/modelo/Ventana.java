package com.geopractic.sistema.modelo;


/**
 * Clase que representa las interfaces graficas de usuario 
 * del sistema dentro de los roles y el acceso que tienen
 * dentro del rol especifico.
 * @author Cristhian
 *
 */
public class Ventana {

  private long id;
  private String nombre;
  private String ubicacion;
  private boolean leer;
  private boolean crear;
  private boolean actualizar;
  private boolean eliminar;

  public Ventana() {
    
  } //Fin del constructor
  
  /**
   * Devuelve el id de la ventana de interfaz grafica.
   * @return el id de la ventana.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el id de la ventana de interfaz grafica.
   * @param id el id a establecer
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el nombre de la ventana de interfaz grafica
   * a la cual pertenecen los servicios.
   * @return la descripcion de la ventana afectada.
   */
  public String getNombre() {
    return nombre;
  } //Fin del metodo getNombre

  /**
   * Establece el nombre de la ventana de interfaz grafica.
   * @param nombre el nombre a establecer.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  } //Fin del metodo setNombre

  /**
   * Devuelve la ubicacion del archivo de la ventana
   * a la cual pertenecen los servicios.
   * @return la ubicacion de la ventana afectada.
   */
  public String getUbicacion() {
    return ubicacion;
  } //Fin del metodo getUbicacion

  /**
   * Establece la ubicacion del archivo de la ventana.
   * @param ubicacion la ubicacion a establecer.
   */
  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  } //Fin del metodo setUbicacion
  
  /**
   * Devuelve si posee permiso de lectura.
   *  @return el valor del permiso de lectura.
   */
  public boolean isLeer() {
    return leer;
  } //Fin de metodo isLeer

  /**
   * Establece el permiso de lectura de la ventana. 
   * @param leer el permiso a establecer.
   */
  public void setLeer(boolean leer) {
    this.leer = leer;
  }  //Fin de metodo  setLeer

  /**
   * Devuelve si posee el permiso de crear.
   * @return el valor del permiso de insercion.
   */
  public boolean isCrear() {
    return crear;
  }  //Fin de metodo isCrear()

  /**
   * Establece el permiso de escritura de la ventana.
   * @param crear el permiso a establecer.
   */
  public void setCrear(boolean crear) {
    this.crear = crear;
  }  //Fin de metodo setCrear

  /**
   * Devuelve si posee el permiso de actualizar.
   * @return el valor del permiso de modificacion.
   */
  public boolean isActualizar() {
    return actualizar;
  }  //Fin de metodo isActualizar

  /**
   * Establece el permiso de modificacion de la ventana.
   * @param actualizar el permiso a establecer.
   */
  public void setActualizar(boolean actualizar) {
    this.actualizar = actualizar;
  }  //Fin de metodo setActualizar

  /**
   * Devuelve si posee el permiso de borrar. 
   * @return el valor del permiso de borrado.
   */
  public boolean isEliminar() {
    return eliminar;
  }  //Fin de metodo isEliminar

  /**
   * Establece el permiso de borrado de la ventana.
   * @param eliminar el permiso a establecer.
   */
  public void setEliminar(boolean eliminar) {
    this.eliminar = eliminar;
  }  //Fin de metodo setEliminar

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Interfaz = " + this.nombre + " [Lectura= " + leer + " - Escritura= " + crear
      + " - Actualizar= " + actualizar + " - Eliminar= " + eliminar + "] " + ubicacion;
  }  //Fin de metodo toString

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((ubicacion == null) ? 0 : ubicacion.hashCode());
    return result;
  } //Fin del metodo hashCode

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof Ventana)) {
      return false;
    } //Fin de if
    Ventana other = (Ventana) obj;
    if (ubicacion == null) {
      if (other.ubicacion != null) {
        return false;
      }
    } else if (!ubicacion.equals(other.ubicacion)) {
      return false;
    } //Fin de if else
    return true;
  } //Fin del metodo equals

  
} //Fin de la clase Ventana