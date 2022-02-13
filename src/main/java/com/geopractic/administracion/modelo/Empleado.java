package com.geopractic.administracion.modelo;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa cada empleado dentro del sistema.
 * @author Cristhian
 *
 */
public class Empleado {

  private long id;
  private String nombre;
  private String apellido;
  private String cedula;
  private Date fechaNacimiento;
  private String estado;
  private Cargo cargo = null;
  private List<Telefono> numerosTelefonicos;

  /**
   * Constructor sin argumentos.
   */
  public Empleado() {

  } //Fin del constructor

  /**
   * Devuelve el identificador del empleado.
   * @return el identificador del empleado.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del empleado.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el nombre del empleado.
   * @return el nobmre del empelado.
   */
  public String getNombre() {
    return nombre;
  } //Fin del metodo getNombre

  /**
   * Establece el nombre del empleado.
   * @param nombre el nombre a establecer.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  } //Fin del metodo setNombre

  /**
   * Devuelve el apellido del empleado.
   * @return el apellido del empleado.
   */
  public String getApellido() {
    return apellido;
  } //Fin del metodo getApellido

  /**
   * Establece el apellido del empleado.
   * @param apellido el apellido a establecer.
   */
  public void setApellido(String apellido) {
    this.apellido = apellido;
  } //Fin del metodo setApellido

  /**
   * Devuelve el numero de cedula del empleado.
   * @return el numero de cedula del empleado.
   */
  public String getCedula() {
    return cedula;
  } //Fin del metodo getCedula

  /**
   * Establece el numero de cedula del empleado.
   * @param cedula el numero a establecer.
   */
  public void setCedula(String cedula) {
    this.cedula = cedula;
  } //Fin del metodo setCedula

  /**
   * Devuelve la fecha de nacimiento.
   * @return la fecha de nacimiento.
   */
  public Date getFechaNacimiento() {
    return fechaNacimiento;
  } //Fin del metodo getFechaNacimiento

  /**
   * Establece la fecha de nacimiento del empleado.
   * @param fechaNacimiento la fecha de nacimiento a ingresar.
   */
  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  } //Fin del metodo setFechaNacimiento

  /**
   * Devuelve el estado actual del empleado.
   * @return si se encuentra Activo o Inactivo
   */
  public String getEstado() {
    return estado;
  } //Fin del metodo getEstado

  /**
   * Establece el estado del empleado.
   * @param estado el estado Activo o Inactivo del empleado.
   */
  public void setEstado(String estado) {
    this.estado = estado;
  } //Fin del metodo setEstado

  /**
   * Devuelve el cargo asignado al empleado.
   * @return el cargo del empleado.
   */
  public Cargo getCargo() {
    return cargo;
  } //Fin del metodo getCargo

  /**
   * Establece el cargo del empleado.
   * @param cargo el cargo a establecer.
   */
  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  } //Fin del metodo setCargo

  /**
   * Devuelve el listado de telefonos asignado al empleado.
   * @return el listado de numeros de telefono.
   */
  public List<Telefono> getNumerosTelefonicos() {
    return numerosTelefonicos;
  } //Fin del metodo getNumerosTelefonicos

  /**
   * Establece el listado de numeros de telefono del empleado.
   * @param numerosTelefonicos el listado de numeros a establecer.
   */
  public void setNumerosTelefonicos(List<Telefono> numerosTelefonicos) {
    this.numerosTelefonicos = numerosTelefonicos;
  } //Fin del metodo setNumerosTelefonicos

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
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
    if (!(obj instanceof Empleado)) {
      return false;
    } //Fin de if
    Empleado other = (Empleado) obj;
    if (cedula == null) {
      if (other.cedula != null) {
        return false;
      } //Fin de if
    } else if (!cedula.equals(other.cedula)) {
      return false;
    } //Fin de if
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getId());
    builder.append(" - ");
    builder.append(getNombre());
    builder.append(" ");
    builder.append(getApellido());
    return builder.toString();
  } //Fin de toString

} //Fin de la clase Empleado