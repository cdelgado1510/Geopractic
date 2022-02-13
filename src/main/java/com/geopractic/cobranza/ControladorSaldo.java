package com.geopractic.cobranza;

import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.cobranza.dao.SaldoDao;
import com.geopractic.cobranza.modelo.CuentaPorCobrar;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

import java.util.List;

/**
 * Clase controlador que maneja los accesos y modificaciones de las saldos de pago.
 * @author Cristhian
  */
public class ControladorSaldo extends Controlador<Saldo> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new SaldoDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Saldo.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Saldo saldo) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Saldo saldo) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Guarda los saldos dentro de la base de datos.
   * @param saldos el listado de saldos a guardar.
   * @return true o false si fueron guardados los datos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public boolean guardarSaldos(List<Saldo> saldos) throws ExcepcionControlador {
    boolean guardado = false;
    iniciarDao();
    try {
      for (Saldo saldo : saldos) {
        guardado = dao.guardar(saldo);
      } //Fin de for
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> guardarSaldos()- Error lanzado al tratar de "
        + "guardar el listado de saldos. ",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> guardarSaldos() - Error lanzada al tratar de"
        + " realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return guardado;
  } //Fin del metodo guardarSaldos

  /**
   * Lista todos los saldos pertenecientes a una cuenta.
   * @param cuenta la cuenta con los saldos buscados.
   * @return el listado completo de saldos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Saldo> listarSaldosCuenta(CuentaPorCobrar cuenta) throws ExcepcionControlador {
    iniciarDao();
    List<Saldo> lista = null;
    try {
      lista = ((SaldoDao) dao).listarSaldosCuenta(cuenta);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarSaldosCuenta() - Error lanzado al tratar de "
        + "listar todos los saldos de la cuenta. ",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarSaldosCuenta() - Error lanzada al tratar de "
        + "realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarSaldosCuenta

  /**
   * Lista todos los saldos de cuentas asignadas a un empleado.
   * @param empleado el empleado con las cuentas asignadas.
   * @return el listado completo de saldos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Saldo> listarSaldosCuentaEmpleado(Empleado empleado) throws ExcepcionControlador {
    iniciarDao();
    List<Saldo> lista = null;
    try {
      lista = ((SaldoDao) dao).listarSaldosCuentaEmpleado(empleado);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarSaldosCuentaEmpleado() - Error lanzado al"
        + " tratar de listar los saldos asignados a los empleados.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarSaldosCuentaEmpleado() - Error lanzada al"
        + " tratar de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarSaldosCuentaEmpleado

  /**
   * Lista todos los saldos de cuentas asignadas a un cliente..
   * @param cliente el cliente con los saldos asignados.
   * @return el listado completo de saldos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Saldo> listarSaldosCliente(Cliente cliente) throws ExcepcionControlador {
    iniciarDao();
    List<Saldo> lista = null;
    try {
      lista = ((SaldoDao) dao).listarSaldosCliente(cliente);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarSaldosCliente() - Error lanzado al"
        + " tratar de listar los saldos asignados al cliente.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarSaldosCliente() - Error lanzada al"
        + " tratar de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarSaldosCliente

} //Fin de la clase ControladorSaldo