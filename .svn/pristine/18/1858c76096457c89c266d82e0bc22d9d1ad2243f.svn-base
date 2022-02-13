package com.geopractic.cobranza.modelo;

import java.util.Date;

/**
 * Clase que representa los saldos de cuotas generados por las cuentas.
 * @author Cristhian
 *
 */
public class Saldo {

  private long id;
  private long saldo;
  private Date vencimiento;
  private int cuota;
  private int totalCuota;
  private long cuenta;
  private long cliente;
  private Date fechaGestion;
  private long empleadoAsignado;

  /**
   * Constructor sin argumentos.
   */
  public Saldo() {

  } //Fin de constructor

  /**
   * Devuelve el identificador del saldo.
   * @return el identificador del saldo.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del saldo.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el monto de saldo de la cuota.
   * @return el monto de saldo de la cuota.
   */
  public long getSaldo() {
    return saldo;
  } //Fin del metodo getSaldo

  /**
   * Establece el monto del saldo de la cuota.
   * @param saldo el monto saldo a establecer.
   */
  public void setSaldo(long saldo) {
    this.saldo = saldo;
  } //Fin del metodo setSaldo

  /**
   * Devuelve la fecha de vencimiento de la cuota.
   * @return la fecha de vencimiento de la cuota.
   */
  public Date getVencimiento() {
    return vencimiento;
  } //Fin del metodo getVencimiento

  /**
   * Establece la fecha de vencimiento de la cuota.
   * @param vencimiento la fecha de vencimiento a establecer.
   */
  public void setVencimiento(Date vencimiento) {
    this.vencimiento = vencimiento;
  } //Fin del metodo setVencimiento

  /**
   * Devuelve el numero de cuota que corresponde al saldo.
   * @return el numero de cuota que corresponde al saldo.
   */
  public int getCuota() {
    return cuota;
  } //Fin del metodo getCuota

  /**
   * Establece el numero de cuota que corresponde al saldo.
   * @param cuota el numero de cuota a establecer.
   */
  public void setCuota(int cuota) {
    this.cuota = cuota;
  } //Fin del metodo setCuota

  /**
   * Devuelve el total de cuotas de la cuota del saldo.
   * @return el total de cuotas del saldo.
   */
  public int getTotalCuota() {
    return totalCuota;
  } //Fin del metodo getTotalCuota

  /**
   * Establece el total de cuotas de la cuenta del saldo.
   * @param totalCuota el total de cuotas a establecer.
   */
  public void setTotalCuota(int totalCuota) {
    this.totalCuota = totalCuota;
  } //Fin del metodo setTotalCuota

  /**
   * Devuelve el identificador de cuenta asociada al saldo.
   * @return el identificador de la cuenta.
   */
  public long getCuenta() {
    return cuenta;
  } //Fin del metodo getCuenta

  /**
   * Establece el identificador de la cuenta asociada al saldo.
   * @param cuenta el identificador de la cuenta a establecer.
   */
  public void setCuenta(long cuenta) {
    this.cuenta = cuenta;
  } //Fin del metodo setCuenta

  /**
   * Devuelve el cliente asociado al saldo.
   * @return el identificador del cliente del saldo.
   */
  public long getCliente() {
    return cliente;
  } //Fin del metodo getCliente

  /**
   * Establece el cliente asociado al saldo.
   * @param cliente el identificador del cliente a establecer.
   */
  public void setCliente(long cliente) {
    this.cliente = cliente;
  } //Fin del metodo setCliente

  
  /**
   * Devuelve la fecha a realizar la gestion.
   * @return la fecha de gestion a realizar.
   */
  public Date getFechaGestion() {
    return fechaGestion;
  } //Fin del metodo getFechaGestion

  /**
   * Establece la fecha a realizar la gestion.
   * @param fechaGestion la fecha a establecer.
   */
  public void setFechaGestion(Date fechaGestion) {
    this.fechaGestion = fechaGestion;
  } //Fin del metodo setFechaGestion

  /**
   * Devuelve el empleado con el saldo asignado.
   * @return el codigo del empleado con el saldo asignado.
   */
  public long getEmpleadoAsignado() {
    return empleadoAsignado;
  } //Fin del metodo getEmpleadoAsignado

  /**
   * Establece el codigo del empleado al cual se asigna la gestion.
   * @param empleadoAsignado el empleado a establecer. 
   */
  public void setEmpleadoAsignado(long empleadoAsignado) {
    this.empleadoAsignado = empleadoAsignado;
  } //Fin del metodo setEmpleadoAsignado

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (cuenta ^ (cuenta >>> 32));
    result = prime * result + cuota;
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
    if (!(obj instanceof Saldo)) {
      return false;
    } //Fin de if
    Saldo other = (Saldo) obj;
    if (cuenta != other.cuenta) {
      return false;
    } //Fin de if
    if (cuota != other.cuota) {
      return false;
    } //Fin de if
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return "Cuenta: " + cuenta + " Saldo: " + saldo + " Vencimiento: " + vencimiento + " Cuota:"
       + cuota + "/" + totalCuota;
  } //Fin de toString 

} //Fin de la clase Saldo