package com.geopractic.cobranza.modelo;

import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.georrefencia.modelo.Georreferencia;

import java.util.Date;

public class GestionVisita {

  private long id;
  private Date fecha;
  private Date fechaVisita;
  private Motivo motivo;
  private long monto;
  private Empleado empleadoCarga;
  private Empleado empleadoVisita;
  private FormaDePago formaDePago;
  private Georreferencia lugarVisita;
  private String comentario;
  private Saldo saldo;

  /**
   * Constructor sin argumentos.
   */
  public GestionVisita() {

  } //Fin de constructor

  /**
   * Devuelve el identificador de la gestion.
   * @return el identificador de la gestion.
   */
  public long getId() {
    return id;
  } //Fin del metodo 

  /**
   * Establece el identificador de la gestion.
   * @param id el identificador de la gestion.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la fecha de la gestion.
   * @return la fecha de la gestion.
   */
  public Date getFecha() {
    return fecha;
  } //Fin del metodo getFecha

  /**
   * Establece la fecha de la gestion.
   * @param fecha la fecha a establecer.
   */
  public void setFecha(Date fecha) {
    this.fecha = fecha;
  } //Fin del metodo setFecha

  /**
   * Devuelve la fecha de visita agendada.
   * @return la fecha de visita agendada
   */
  public Date getFechaVisita() {
    return fechaVisita;
  } //Fin del metodo getFechaVisita

  /**
   * Establece la fecha de visita de la gestion.
   * @param fechaVisita the fechaVisita to set
   */
  public void setFechaVisita(Date fechaVisita) {
    this.fechaVisita = fechaVisita;
  } //Fin del metodo setFechaVisita

  /**
   * Devuelve el motivo de la gestion.
   * @return el motivo de la gestion.
   */
  public Motivo getMotivo() {
    return motivo;
  } //Fin del metodo getMotivo

  /**
   * Establece el motivo de la gestion.
   * @param motivo el motivo a establecer.
   */
  public void setMotivo(Motivo motivo) {
    this.motivo = motivo;
  } //Fin del metodo setMotivo

  /**
   * Devuelve el monto a cobrar.
   * @return el monto a cobrar
   */
  public long getMonto() {
    return monto;
  } //Fin del metodo getMonto

  /**
   * Establece el monto a cobrar.
   * @param monto el monto a cobrar.
   */
  public void setMonto(long monto) {
    this.monto = monto;
  } //Fin del metodo setMonto

  /**
   * Devuelve el empleado que carga la gestion.
   * @return el empleado que carga la gestion.
   */
  public Empleado getEmpleadoCarga() {
    return empleadoCarga;
  } //Fin del metodo getEmpleadoCarga

  /**
   * Establece el empleado que carga la gestion.
   * @param empleadoCarga el empleado de carga a establecer.
   */
  public void setEmpleadoCarga(Empleado empleadoCarga) {
    this.empleadoCarga = empleadoCarga;
  } //Fin del metodo setEmpleadoCarga

  /**
   * Devuelve el empleado que realizara la visita.
   * @return el empleado que estara realizando la visita.
   */
  public Empleado getEmpleadoVisita() {
    return empleadoVisita;
  } //Fin del metodo getEmpleadoVisita

  /**
   * Establece el empleado que realizara la visita.
   * @param empleadoVisita el empleado a establecer.
   */
  public void setEmpleadoVisita(Empleado empleadoVisita) {
    this.empleadoVisita = empleadoVisita;
  } //Fin del metodo setEmpleadoVisita

  /**
   * Devuelve la forma de pago de la gestion.
   * @return la forma de pago de la gestion.
   */
  public FormaDePago getFormaDePago() {
    return formaDePago;
  } //Fin del metodogetFormaDePago

  /**
   * Establece la forma de pago de la gestion.
   * @param formaDePago la forma de pago de la gestion.
   */
  public void setFormaDePago(FormaDePago formaDePago) {
    this.formaDePago = formaDePago;
  } //Fin del metodo setFormaDePago

  /**
   * Devuelve el comentario de gestion.
   * @return el comentario de la gestion.
   */
  public String getComentario() {
    return comentario;
  } //Fin del metodo getComentario

  /**
   * Establece el comentario de la gestion.
   * @param comentario el comentario a establecer.
   */
  public void setComentario(String comentario) {
    this.comentario = comentario;
  } //Fin del metodo setComentario

  /**
   * Devuelve el lugar de visita del ruteo.
   * @return el lugar de visita de la gestion.
   */
  public Georreferencia getLugarVisita() {
    return lugarVisita;
  } //Fin del metodo getLugarVisita

  /**
   * Establece el lugar de visita del ruteo.
   * @param lugarVisita el lugar de visita a establecer.
   */
  public void setLugarVisita(Georreferencia lugarVisita) {
    this.lugarVisita = lugarVisita;
  } //Fin del metodo setLugarVisita

  /**
   * Devuelve el saldo asignado a la gestion.
   * @return el saldo de la gestion.
   */
  public Saldo getSaldo() {
    return saldo;
  } //Fin del metodo getSaldo

  /**
   * Establece el saldo asignado a la gestion.
   * @param saldo el saldo a establecer.
   */
  public void setSaldo(Saldo saldo) {
    this.saldo = saldo;
  } //Fin del metodo setSaldo

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fechaVisita == null) ? 0 : fechaVisita.hashCode());
    result = prime * result + ((lugarVisita == null) ? 0 : lugarVisita.hashCode());
    result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
    return result;
  } //Fin del hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof GestionVisita)) {
      return false;
    } //Fin de if
    GestionVisita other = (GestionVisita) obj;
    if (fechaVisita == null) {
      if (other.fechaVisita != null) {
        return false;
      } //Fin de if
    } else if (!fechaVisita.equals(other.fechaVisita)) {
      return false;
    } //Fin de if-else
    if (lugarVisita == null) {
      if (other.lugarVisita != null) {
        return false;
      } //Fin de if
    } else if (!lugarVisita.equals(other.lugarVisita)) {
      return false;
    } //Fin de if-else
    if (saldo == null) {
      if (other.saldo != null) {
        return false;
      } //Fin de if
    } else if (!saldo.equals(other.saldo)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin de equals 

  @Override
  public String toString() {
    return "Gestion de fecha " + fechaVisita + " por monto de " + monto + " para empleado "
           + empleadoVisita.getId() + " - " + empleadoVisita.getNombre() + " "
           + empleadoVisita.getApellido();
  } //Fin de toString 

} //Fin de la clase GestionVisita