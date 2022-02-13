package com.geopractic.cobranza.modelo;

import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Empleado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa las cuentas generadas para el cliente.
 * @author Cristhian
 *
 */
public class CuentaPorCobrar {

  private long id;
  private Cliente cliente;
  private long montoTotal;
  private int cuotas;
  private Date vencimiento;
  private Empleado empleadoAsignado;
  private String factura;
  private Date fecha;
  private List<Saldo> saldos;

  /**
   * Construtor sin argumentos.
   */
  public CuentaPorCobrar() {

  } //Fin del constructor sin argumentos

  /**
   * Devuelve el identificador de la cuenta.
   * @return el identificador unico de la cuenta
   */
  public long getId() {
    return id;
  } //Fin del metodo 

  /**
   * Establece el identificador unico de la cuenta.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el cliente asignado a la cuenta.
   * @return el cliente asignado a la cuenta.
   */
  public Cliente getCliente() {
    return cliente;
  } //Fin del metodo getCliente

  /**
   * Establece el cliente que posee la cuenta.
   * @param cliente el cliente a establecer.
   */
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  } //Fin del metodo setCliente

  /**
   * Establece el monto total adeudado de la cuenta.
   * @return the montoTotal
   */
  public long getMontoTotal() {
    return montoTotal;
  } //Fin del metodo getMontoTotal

  /**
   * Establece el monto total adeudado de la cuenta.
   * @param montoTotal el monto total a establecer.t
   */
  public void setMontoTotal(long montoTotal) {
    this.montoTotal = montoTotal;
  } //Fin del metodo setMontoTotal

  /**
   * Devuelve la cantidad total de cuotas de la cuenta.
   * @return la cantidad total de cuotas de la cuenta.
   */
  public int getCuotas() {
    return cuotas;
  } //Fin del metodo getCuotas

  /**
   * Establece el total de cuotas de la cuenta.
   * @param cuotas el total de cuotas a establecer.
   */
  public void setCuotas(int cuotas) {
    this.cuotas = cuotas;
  } //Fin del metodo setCuotas

  /**
   * Devuelve la fecha del primer vencimiento de la cuenta.
   * @return la fecha del primer vencimiento de la cuenta.
   */
  public Date getVencimiento() {
    return vencimiento;
  } //Fin del metodo getVencimiento

  /**
   * Estalbece la fecha del primer vencimiento de la cuenta.
   * @param vencimiento la fecha de vencimiento a establecer.
   */
  public void setVencimiento(Date vencimiento) {
    this.vencimiento = vencimiento;
  } //Fin del metodo setVencimiento

  /**
   * Devuelve el empleado asignado a la cuenta.
   * @return el empleado asignado a la cuenta.
   */
  public Empleado getEmpleadoAsignado() {
    return empleadoAsignado;
  } //Fin del metodo getEmpleadoAsignado

  /**
   * Establece el empleado asignado a la cuenta.
   * @param empleadoAsignado el empleado a establecer.
   */
  public void setEmpleadoAsignado(Empleado empleadoAsignado) {
    this.empleadoAsignado = empleadoAsignado;
  } //Fin del metodo empleadoAsignado

  /**
   * Devuelve la factura asignada a la cuenta.
   * @return the factura
   */
  public String getFactura() {
    return factura;
  } //Fin del metodo getFactura

  /**
   * Establece la factura asiganada a la cuenta.
   * @param factura la factura a establecer.
   */
  public void setFactura(String factura) {
    this.factura = factura;
  } //Fin del metodo setFactura

  
  /**
   * Devuelve la fecha de carga de la cuenta.
   * @return la fecha de carga de la cuenta.
   */
  public Date getFecha() {
    return fecha;
  } //Fin del metodo getFecha

  /**
   * Establece la fecha de carga de la venta.
   * @param fecha la fecha a establecer
   */
  public void setFecha(Date fecha) {
    this.fecha = fecha;
  } //Fin del metodo setFecha

  /**
   * Devuelve el listado de saldos asociados con saldos.
   * @return el listado de saldos.
   */
  public List<Saldo> getSaldos() {
    return saldos;
  } //Fin del metodo getSaldos

  /**
   * Establece el listado de saldos de la cuenta.
   * @param saldos el listado de saldos a establecer.
   */
  public void setSaldos(List<Saldo> saldos) {
    this.saldos = saldos;
  } //Fin del metodo setSaldos

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
    result = prime * result + cuotas;
    result = prime * result + ((empleadoAsignado == null) ? 0 : empleadoAsignado.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + (int) (montoTotal ^ (montoTotal >>> 32));
    result = prime * result + ((vencimiento == null) ? 0 : vencimiento.hashCode());
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
    if (!(obj instanceof CuentaPorCobrar)) {
      return false;
    } //Fin de if
    CuentaPorCobrar other = (CuentaPorCobrar) obj;
    if (cliente == null) {
      if (other.cliente != null) {
        return false;
      } //Fin de if
    } else if (!cliente.equals(other.cliente)) {
      return false;
    } //Fin de if-else
    if (cuotas != other.cuotas) {
      return false;
    } //Fin de if
    if (empleadoAsignado == null) {
      if (other.empleadoAsignado != null) {
        return false;
      } //Fin de if
    } else if (!empleadoAsignado.equals(other.empleadoAsignado)) {
      return false;
    } //Fin de if-else
    if (id != other.id) {
      return false;
    } //Fin de if
    if (montoTotal != other.montoTotal) {
      return false;
    } //Fin de if
    if (vencimiento == null) {
      if (other.vencimiento != null) {
        return false;
      } //Fin de if
    } else if (!vencimiento.equals(other.vencimiento)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin de equals 

  @Override
  public String toString() {
    return cliente + " Total = " + montoTotal + " Cuotas = " + cuotas
           + " Vencimiento =" + vencimiento;
  } //Fin de toString

  /**
   * Genera los saldos a partir de los datos de la cuenta.
   */
  public void generarSaldos() {
    saldos = new ArrayList<Saldo>();
    long montoSaldo = montoTotal / cuotas;
    Calendar calendario = Calendar.getInstance();
    calendario.setTime(getVencimiento());

    //Genera los saldos obtenidos
    for (int i = 0; i < cuotas;i++) {
      Saldo saldo = new Saldo();
      saldo.setSaldo(montoSaldo);
      saldo.setCuota(i + 1);
      saldo.setTotalCuota(cuotas);
      saldo.setCuenta(getId());
      saldo.setVencimiento(calendario.getTime());
      saldo.setCliente(getCliente().getId());
      saldo.setFechaGestion(saldo.getVencimiento());
      saldo.setEmpleadoAsignado(getEmpleadoAsignado().getId());
      saldos.add(saldo);
      calendario.add(Calendar.MONTH, 1);
    } //Fin de for

  } //Fin del metodo generarSaldos

} //Fin de la clase CuentaPorCobrar