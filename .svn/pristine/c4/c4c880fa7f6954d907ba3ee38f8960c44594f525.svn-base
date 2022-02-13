package com.geopractic.georrefencia.modelo;

import com.geopractic.administracion.modelo.Cliente;

/**
 * Clase que representa una georreferencia dentro de la base de datos.
 * @author Cristhian
 *
 */
public class Georreferencia {

  private long id;
  private String descripcion;
  private TipoGeorreferencia tipo;
  private Punto punto;
  private Zona zona;
  private Cliente cliente;

  /**
   * Constructor sin argumentos.
   */
  public Georreferencia() {

  } //Fin de constructor

  /**
   * Devuelve el identificador de la georreferencia.
   * @return el identificador de la georreferencia.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador de la georreferencia.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la descripcion de la georreferencia.
   * @return la descripcion de la georreferencia.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion de la georreferencia.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  /**
   * Devuelve el tipo de georreferencia asignado.
   * @return el tipo de georreferencia asignado.
   */
  public TipoGeorreferencia getTipo() {
    return tipo;
  } //Fin del metodo getTipo

  /**
   * Establece el tipo de georreferencia asignado.
   * @param tipo el tipo a asignar.
   */
  public void setTipo(TipoGeorreferencia tipo) {
    this.tipo = tipo;
  } //Fin del metodo setTipo

  /**
   * Devuelve el punto de la georreferencia.
   * @return el punto de la georreferencia.
   */
  public Punto getPunto() {
    return punto;
  } //Fin del metodo getPunto

  /**
   * Establece el punto de la georreferencia.
   * @param punto el punto a establecer.
   */
  public void setPunto(Punto punto) {
    this.punto = punto;
  } //Fin del metodo setPunto

  /**
   * Devuelve la zona asignada a la georreferencia.
   * @return la zona asignada a la georreferencia.
   */
  public Zona getZona() {
    return zona;
  } //Fin del metodo getZona

  /**
   * Establece la zona de la georreferencia.
   * @param zona la zona a establecer.
   */
  public void setZona(Zona zona) {
    this.zona = zona;
  } //Fin del metodo setZona

  /**
   * Devuelve el cliente asignado a la georreferncia.
   * @return el cliente asignado a al georreferencia.
   */
  public Cliente getCliente() {
    return cliente;
  } //Fin del metodo getCliente

  /**
   * Establece el cliente asignado a la georreferencia.
   * @param cliente el cliente a asignar.
   */
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  } //Fin del metodo setCliente

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
    result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
    if (!(obj instanceof Georreferencia)) {
      return false;
    } //Fin de if
    Georreferencia other = (Georreferencia) obj;
    if (cliente == null) {
      if (other.cliente != null) {
        return false;
      } //Fin de if
    } else if (!cliente.equals(other.cliente)) {
      return false;
    } //Fin de if-else
    if (descripcion == null) {
      if (other.descripcion != null) {
        return false;
      } //Fin de if
    } else if (!descripcion.equals(other.descripcion)) {
      return false;
    } //Fin de if-else
    if (tipo == null) {
      if (other.tipo != null) {
        return false;
      } //Fin de if
    } else if (!tipo.equals(other.tipo)) {
      return false;
    } //Fin de if
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return id + " - " + descripcion + " Tipo: " + tipo.getDescripcion();
  } //Fin de toString

} //Fin de la clase Georreferencia