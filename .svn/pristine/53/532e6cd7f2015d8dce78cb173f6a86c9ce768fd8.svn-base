package com.geopractic.cobranza;

import com.geopractic.cobranza.dao.CuentaPorCobrarDao;
import com.geopractic.cobranza.dao.SaldoDao;
import com.geopractic.cobranza.modelo.CuentaPorCobrar;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

/**
 * Clase controlador que maneja los accesos y modificaciones de las cuentas de por cobrar.
 * @author Cristhian
  */
public class ControladorCuentaPorCobrar extends Controlador<CuentaPorCobrar> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new CuentaPorCobrarDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Cuenstas por Cobrar.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(CuentaPorCobrar cuenta) throws ExcepcionControlador {
    iniciarDao();
    boolean resultado = false;
    try {
      int cantidadDependencias = dao.buscarDependencias(cuenta);
      resultado = (cantidadDependencias > 0) ?  true : false;
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> comprobarDependencias() - Error lanzado al buscar"
          + " las dependencias de la cuenta.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(CuentaPorCobrar cuenta) throws ExcepcionControlador {
    iniciarDao();
    SaldoDao saldoDao = new SaldoDao();
    saldoDao.setConexion(dao.getConexion());
    boolean resultado = false;
    try {
      for (Saldo saldo : cuenta.getSaldos()) {
        resultado = saldoDao.eliminar(saldo);
      } //Fin de for
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarDependencias() - Error lanzado al tratar de "
                + "realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo eliminarDependencias

  /**
   * Cambia los empleados asignados a los saldos de la cuenta.
   * @param cuenta la cuenta a cambiar los saldos
   * @return si cambio o no el empleado asignado en los saldos
   * @throws ExcepcionControlador excepcion lanzada en la capa controlador.
   */
  public boolean cambiarEmpAsig(CuentaPorCobrar cuenta) throws ExcepcionControlador {
    SaldoDao saldoDao = new SaldoDao();
    saldoDao.setConexion(dao.getConexion());
    boolean resultado = false;
    try {
      for (Saldo saldo : cuenta.getSaldos()) {
        saldo.setEmpleadoAsignado(cuenta.getEmpleadoAsignado().getId());
        resultado = saldoDao.modificar(saldo);
      } //Fin de for
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> cambiarEmpAsig() - Error lanzado al tratar de "
                + "cambiar el empleado del saldo.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo cambiarEmpAsig

} //Fin de la clase ControladorCuentaPorCobrar