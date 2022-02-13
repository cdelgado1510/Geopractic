package com.geopractic.cobranza.modelo;

import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Empleado;

import java.util.Date;

/**
 * Clase que representa los pagos de los saldos de las cuentas
 * de los clientes. 
 * @author Cristhian
 *
 */
public class Cobranza {

  private long id;
  private Date fecha;
  private String recibo;
  private String factura;
  private long monto;
  private long interes;
  private Cliente cliente;
  private Empleado cobrador;
  private Saldo saldo;

  /**
   * Constructor sin argumentos.
   */
  public Cobranza() {

  } //Fin de constructor sin argumentos

  /**
   * Devuelve el identificador de la cobranza.
   * @return el identificador de la cobranza.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador de la cobranza.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la fecha del pago.
   * @return la fecha del pago.
   */
  public Date getFecha() {
    return fecha;
  } //Fin del metodo getFecha

  /**
   * Establece la fecha del pago.
   * @param fecha la fecha a establecer.
   */
  public void setFecha(Date fecha) {
    this.fecha = fecha;
  } //Fin del metodo setFecha

  /**
   * Devuelve el recibo usado en la cobranza.
   * @return el recibo usado en la cobranza.
   */
  public String getRecibo() {
    return recibo;
  } //Fin del metodo getRecibo

  /**
   * Establece el recibo utilizado en la cobranza.
   * @param recibo el recibo a establecer.
   */
  public void setRecibo(String recibo) {
    this.recibo = recibo;
  } //Fin del metodo setRecibo

  /**
   * Devuelve la factura correspondiente a la cuenta.
   * @return la factura correspondiente a la cuenta.
   */
  public String getFactura() {
    return factura;
  } //Fin del metodo getFactura

  /**
   * Establece la factura correspondiente a la cuenta.
   * @param factura la factura a establecer.
   */
  public void setFactura(String factura) {
    this.factura = factura;
  } //Fin del metodo setFactura

  /**
   * Devuelve el monto del saldo abonado.
   * @return el monto abonado del saldo.
   */
  public long getMonto() {
    return monto;
  } //Fin del metodo getMonto

  /**
   * Establece el monto abonado en el pago.
   * @param monto el monto a establecer.
   */
  public void setMonto(long monto) {
    this.monto = monto;
  } //Fin del metodo setMonto

  /**
   * Devuelve el monto de interes abonado.
   * @return el interes abonado en el pago.
   */
  public long getInteres() {
    return interes;
  } //Fin del metodo getInteres

  /**
   * Establece el monto de interes del pago.
   * @param interes el interes a establecer.
   */
  public void setInteres(long interes) {
    this.interes = interes;
  } //Fin del metodo setInteres

  /**
   * Devuelve el cliente que realizo el pago.
   * @return el cliente que realizo el pago
   */
  public Cliente getCliente() {
    return cliente;
  } //Fin del metodo getCliente

  /**
   * Establece el cliente que realizo el pago.
   * @param cliente el cliente a establecer.
   */
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  } //Fin del metodo setCliente

  /**
   * Devuelve el cobrador que realizo el cobro.
   * @return el cobrador que realizo el cobro.
   */
  public Empleado getCobrador() {
    return cobrador;
  } //Fin del metodo getCobrador

  /**
   * Establece el cobrador que realizo el cobro.
   * @param cobrador el cobrador a establecer.
   */
  public void setCobrador(Empleado cobrador) {
    this.cobrador = cobrador;
  } //Fin del metodo setCobrador

  /**
   * Devuelve el saldo asignado a la cobranza.
   * @return el saldo de la cobranza.
   */
  public Saldo getSaldo() {
    return saldo;
  } //Fin del metodo getSaldo

  /**
   * Establece el saldo asignado a la cobranza.
   * @param saldo el saldo a establecer.
   */
  public void setSaldo(Saldo saldo) {
    this.saldo = saldo;
  } //Fin del metodo setSaldo

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
    result = prime * result + ((cobrador == null) ? 0 : cobrador.hashCode());
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
    if (!(obj instanceof Cobranza)) {
      return false;
    } //Fin de if
    Cobranza other = (Cobranza) obj;
    if (cliente == null) {
      if (other.cliente != null) {
        return false;
      } //Fin de if
    } else if (!cliente.equals(other.cliente)) {
      return false;
    } //Fin de if-else
    if (cobrador == null) {
      if (other.cobrador != null) {
        return false;
      } //Fin de if
    } else if (!cobrador.equals(other.cobrador)) {
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
    return id + " " + fecha + " Monto: " + monto 
          + " Interes: " + interes + " Id Cliente: "
          + cliente.getId() + " Cobrador: " + cobrador.getNombre()
          + " " + cobrador.getApellido() + " de Saldo " + saldo.getId()
          + " Cuota " + saldo.getCuota() + "/" + saldo.getTotalCuota();
  } //Fin de toString 

} //Fin de la clase Cobranza