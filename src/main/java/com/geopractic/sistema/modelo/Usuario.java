package com.geopractic.sistema.modelo;

import com.geopractic.administracion.modelo.Empleado;

/**
 * Representacion de un usuario concreto del sistema.
 * @author Cristhian
 */
public class Usuario {

  private Long id;
  private String nombre;
  private String apellido;
  private String usuario;
  private String contrasenha;
  private boolean activo;
  private Rol rol = null;
  private Empleado empleado;

  /**
   * Constructor sin argumentos.
   */
  public Usuario() {}

  /**
   * Devuelve el valor de la variable id.
   * @return el id 
   */
  public Long getId() {
    return id;
  } //Fin de metodo getId

  /**
   * Establece el valor del id.
   * @param id el id a establecer.
   */
  public void setId(Long id) {
    this.id = id;
  } //Fin de metodo setId

  /**
   * Devuelve el nombre del usuario.
   * @return el nombre.
   */
  public String getNombre() {
    return nombre;
  } //Fin de metodo getNombre

  /**
   * Establece el nombre del usuario.
   * @param nombre el nombre a establecer.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  } //Fin de metodo setNombre

  /**
   * Devuelve el nombre del usuario.
   * @return el apellido. 
   */
  public String getApellido() {
    return apellido;
  } //Fin de metodo getApellido

  /**
   * Establece el valor de la variable apellido.
   * @param apellido el apellido a establecer.
   */
  public void setApellido(String apellido) {
    this.apellido = apellido;
  } //Fin de metodo setApellido

  /**
   * Devuelve el usuario.
   * @return el usuario.
   */
  public String getUsuario() {
    return usuario;
  } //Fin de metodo getUsuario

  /**
   * Establece el usuario.
   * @param usuario el usuario a establecer
   */
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  } //Fin de metodo setUsuario

  /**
   * Devuelve la contraseña del usuario.
   * @return la contraseña.
   */
  public String getContrasenha() {
    return contrasenha;
  } //Fin de metodo getContrasenha

  /**
   * Establece la contraseña del usuario.
   * @param contrasenha la contraseña a establecer.
   */
  public void setContrasenha(String contrasenha) {
    this.contrasenha = contrasenha;
  } //Fin de metodo setContrasenha
  
  /**
   * Devuelve si el usuario se encuentra activo o no. 
   * @return si esta activo o no.
   */
  public boolean isActivo() {
    return activo;
  } //Fin del metodo isActivo

  /**
   * Establece si el usuario es activo o no.
   * @param activo si esta inactivo o activo.
   */
  public void setActivo(boolean activo) {
    this.activo = activo;
  } //Fin del metodo setActivo

  /**
  * Devuelve el rol asignado al usuario.
  * @return el rol del usuario.
  */
  public Rol getRol() {
    return rol;
  } //Fin del metodo getRol

  /**
  * Establece el rol para el usuario.
  * @param rol el rol a establecer al usuario.
  */
  public void setRol(Rol rol) {
    this.rol = rol;
  } //Fin del metodo setRol

  /**
   * Devuelve empleado asignado al usuario.
   * @return el empleado asignado al usuario.
   */
  public Empleado getEmpleado() {
    return empleado;
  } //Fin del metodo getEmpleado

  /**
  * Establece el empleado asignado al usuario.
  * @param empleado el empleado a asignar.
  */
  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  } //Fin del metodo setEmpleado

  @Override
  public String toString() {
    return this.nombre + " " + this.apellido;
  } //Fin del metodo toString

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((rol == null) ? 0 : rol.hashCode());
    result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
    return result;
  } //Fin de metodo hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Usuario)) {
      return false;
    }
    Usuario other = (Usuario) obj;
    if (usuario == null) {
      if (other.usuario != null) {
        return false;
      }
    } else if (!usuario.equals(other.usuario)) {
      return false;
    }
    return true;
  } //Fin de metodo equals
} //Fin de la clase Usuario