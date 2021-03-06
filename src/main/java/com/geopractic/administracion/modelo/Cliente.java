package com.geopractic.administracion.modelo;

import com.geopractic.sistema.modelo.TipoDocumento;

import java.util.Date;
import java.util.List;

/**
 * Representacion del cliente.
 * @author Cristhian
 *
 */
public class Cliente {

  private long id;
  private String primerNombre;
  private String segundoNombre;
  private String primerApellido;
  private String segundoApellido;
  private String apellidoCasada;
  private String nroDocumento;
  private String ruc;
  private Date fechaNacimiento;
  private Date vencimientoDocumento;
  private Date fechaCreacion;
  private TipoDocumento tipoDocumento;
  private boolean bloqueado;
  private List<Telefono> numerosTelefonicos;

  /**
   * Constructor sin argumentos.
   */
  public Cliente() {
    this.bloqueado = false;
  } //Fin de constructor

  /**
   * Devuelve el id del cliente.
   * @return el id del cliente.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el id del cliente.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el primer nombre del cliente.
  * @return el primer nombre del cliente.
  */
  public String getPrimerNombre() {
    return primerNombre;
  } //Fin del metodo getPrimerNombre

  /**
  * Establece el primer nombre del cliente.
  * @param primerNombre el primer nombre a establecer.
  */
  public void setPrimerNombre(String primerNombre) {
    this.primerNombre = primerNombre;
  } //Fin del metodo setPrimerNombre

  /**
  * Devuelve el segundo nombre del cliente. 
  * @return el segundo nombre del cliente.
  */
  public String getSegundoNombre() {
    return segundoNombre;
  } //Fin del metodo getSegundoNombre

  /**
  * Establece el segundo nombre del cliente.
  * @param segundoNombre el segundo nombre a establecer.
  */
  public void setSegundoNombre(String segundoNombre) {
    this.segundoNombre = segundoNombre;
  } //Fin del metodo setSegundoNombre

  /**
  * Devuelve el primer apellido del cliente. 
  * @return el primer apellido del cliente.
  */
  public String getPrimerApellido() {
    return primerApellido;
  } //Fin del metodo getPrimerApellido

  /**
  * Establece el primer apellido del cliente. 
  * @param primerApellido el primer apellido a establecer.
  */
  public void setPrimerApellido(String primerApellido) {
    this.primerApellido = primerApellido;
  } //Fin del metodo setPrimerApellido

  /**
  * Devuelve el segundo apellido del cliente.
  * @return el segundo apellido del cliente.
  */
  public String getSegundoApellido() {
    return segundoApellido;
  } //Fin del metodo getSegundoApellido

  /**
  * Establece el segundo apellido del cliente. 
  * @param segundoApellido el segundo apellido a establecer.
  */
  public void setSegundoApellido(String segundoApellido) {
    this.segundoApellido = segundoApellido;
  } //Fin del metodo setSegundoApellido

  /**
  * Devuelve el apellido de casada del cliente.
  * @return el apellido de casada.
  */
  public String getApellidoCasada() {
    return apellidoCasada;
  } //Fin del metodo getApellidoCasada

  /**
  * Establece el apellido de casada del cliente.
  * @param apellidoCasada el apellido de casada a establecer.
  */
  public void setApellidoCasada(String apellidoCasada) {
    this.apellidoCasada = apellidoCasada;
  } //Fin del metodo setApellidoCasada

  /**
  * Devuelve el nro de documento del cliente. 
  * @return el numero de documento del cliente.
  */
  public String getNroDocumento() {
    return nroDocumento;
  } //Fin del metodo getNroDocumento

  /**
  * Establece el nro de documento del cliente. 
  * @param nroDocumento el numero de documento a establecer.
  */
  public void setNroDocumento(String nroDocumento) {
    this.nroDocumento = nroDocumento;
  } //Fin del metodo setNroDocumento

  /**
  * Devuelve el ruc del cliente.
  * @return el ruc del cliente.
  */
  public String getRuc() {
    return ruc;
  } //Fin del metodo getRuc

  /**
  * Establece el ruc del cliente. 
  * @param ruc el ruc a establecer.
  */
  public void setRuc(String ruc) {
    this.ruc = ruc;
  } //Fin del metodo setRuc

  /**
  * Devuelve la fecha de nacimiento del cliente.
  * @return la fecha de nacimiento del cliente.
  */
  public Date getFechaNacimiento() {
    return fechaNacimiento;
  } //Fin del metodo fechaNacimiento

  /**
  * Establece la fecha de nacimiento del cliente. 
  * @param fechaNacimiento la fecha de nacimiento a establecer.
  */
  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  } //Fin del metodo setFechaNacimiento

  /**
  * Devuelve la fecha de vencimiento del documento. 
  * @return la fecha de vencimiento del documento
  */
  public Date getVencimientoDocumento() {
    return vencimientoDocumento;
  } //Fin del metodo getVencimientoDocumento

  /**
  * Establece la fecha de vencimiento del documento. 
  * @param vencimientoDocumento la fecha de vencimiento a establecer.
  */
  public void setVencimientoDocumento(Date vencimientoDocumento) {
    this.vencimientoDocumento = vencimientoDocumento;
  } //Fin del metodo setVencimientoDocumento

  /**
  * Devuelve la fecha de creacion del cliente. 
  * @return la fecha de creacion.
  */
  public Date getFechaCreacion() {
    return fechaCreacion;
  } //Fin del metodo getFechaCreacion

  /**
  * Establece la fecha de creacion del cliente. 
  * @param fechaCreacion la fecha de creacion a establecer.
  */
  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  } //Fin del metodo setFechaCreacion

  /**
   * Devuelve el tipo de documento del cliente.
   * @return el tipo de documento.
   */
  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  } //Fin del metodo getTipoDocumento

  /**
  * Establece el tipo de documento asignado al cliente. 
  * @param tipoDocumento el tipo de documento a establecer.
  */
  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  } //Fin del metodo setTipoDocumento

  /**
  * Devuelve si los datos del cliente para modificacion estan bloqueados. 
  * @return el booleano si estan o no bloqueados.
  */
  public boolean isBloqueado() {
    return bloqueado;
  } //Fin del metodo isBloqueado

  /**
  * Establece si estan bloqueado o no los datos del cliente. 
  * @param bloqueado el booleano si estan bloqueados o no los datos.
  */
  public void setBloqueado(boolean bloqueado) {
    this.bloqueado = bloqueado;
  } //Fin del metodo setBloqueado

  
  /**
   * Devuelve el listado de telefonos asignado al cliente.
   * @return el listado de numeros de telefono.
   */
  public List<Telefono> getNumerosTelefonicos() {
    return numerosTelefonicos;
  } //Fin del metodo getNumerosTelefonicos

  /**
   * Establece el listado de numeros de telefono del cliente.
   * @param numerosTelefonicos el listado de numeros a establecer.
   */
  public void setNumerosTelefonicos(List<Telefono> numerosTelefonicos) {
    this.numerosTelefonicos = numerosTelefonicos;
  } //Fin del metodo setNumerosTelefonicos

  @Override
  public String toString() {
    return primerNombre + " " + segundoNombre + " " + primerApellido
       + " " + segundoApellido;
  } //Fin del metodo toString

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nroDocumento == null) ? 0 : nroDocumento.hashCode());
    result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
    return result;
  } //Fin del metodo hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if

    if (obj == null) {
      return false;
    } //Fin de if

    if (!(obj instanceof Cliente)) {
      return false;
    } //Fin de if

    Cliente other = (Cliente) obj;
    if (nroDocumento == null) {
      if (other.nroDocumento != null) {
        return false;
      }
    } else if (!nroDocumento.equals(other.nroDocumento)) {
      return false;
    } //Fin de if-else

    return true;
  } //Fin del metodo equals

  /**
   * Devuelve el nombre completo del cliente.
   * @return el nombre completo del cliente.
   */
  public String getNombreCompleto() {
    StringBuilder builder = new StringBuilder(getPrimerNombre());
    if (getSegundoNombre() != null) {
      builder.append(" " + getSegundoNombre());
    } //Fin de if
    builder.append(" " + getPrimerApellido());
    if (getSegundoApellido() != null) {
      builder.append(" " + getSegundoApellido());
    } //Fin de if
    return builder.toString();
  } //Fin del metodo getNombreCompleto

} //Fin de la clase Cliente