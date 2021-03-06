package com.geopractic.cobranza;

import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.cobranza.dao.RuteoCobradorDao;
import com.geopractic.cobranza.modelo.RuteoCobrador;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

import java.util.Date;
import java.util.List;

/**
 * Clase controlador que maneja los accesos y modificaciones de los ruteos del cobrador.
 * @author Cristhian
  */
public class ControladorRuteoCobrador extends Controlador<RuteoCobrador> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new RuteoCobradorDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Ruteo de Cobrador.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(RuteoCobrador ruteo) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(RuteoCobrador ruteo) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Lista los ruteos relacionados de un saldo.
   * @param saldo el saldo a buscar.
   * @return el listado de ruteos relacionados con el saldo
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<RuteoCobrador> listarRuteoGestion(Saldo saldo)
      throws ExcepcionControlador {
    iniciarDao();
    List<RuteoCobrador> lista = null;
    try {
      lista = ((RuteoCobradorDao) dao).listarRuteoGestion(saldo);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarRuteoGestion() - Error lanzado al"
        + " tratar de listar los ruteos del saldo.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarRuteoGestion() - Error lanzada al"
        + " tratar de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarSaldosCuentaEmpleado

  /**
   * Lista los ruteos asignados al dia del cobrador.
   * @param cobrador el cobrador a buscar
   * @param fechaInicio la fecha de inicio del rango a buscar.
   * @param fechaFin la fecha de inicio del rango a buscar.
   * @return el listado de ruteos del dia del cobrador
   * @throws ExcepcionControlador error lanzado desde la conexion
   */
  public List<RuteoCobrador> listarRuteoCobradorDia(Empleado cobrador,
      Date fechaInicio,Date fechaFin) throws ExcepcionControlador {
    iniciarDao();
    List<RuteoCobrador> lista = null;
    try {
      lista = ((RuteoCobradorDao) dao).listarRuteoCobradorDia(cobrador, fechaInicio, fechaFin);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarRuteoCobradorDia() - Error lanzado al"
        + " tratar de listar los ruteos del dia del cobrador.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarRuteoCobradorDia() - Error lanzada al"
        + " tratar de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarRuteoCobradorDia

  /**
   * Lista los ruteos asignados al dia.
   * @param fechaInicio la fecha de inicio del rango a buscar.
   * @param fechaFin la fecha de inicio del rango a buscar.
   * @return el listado de ruteos del dia
   * @throws ExcepcionControlador error lanzado desde la conexion
   */
  public List<RuteoCobrador> listarRuteoDia(
      Date fechaInicio,Date fechaFin) throws ExcepcionControlador {
    iniciarDao();
    List<RuteoCobrador> lista = null;
    try {
      lista = ((RuteoCobradorDao) dao).listarRuteoDia(fechaInicio, fechaFin);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarRuteoCobradorDia() - Error lanzado al"
        + " tratar de listar los ruteos del dia del cobrador.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarRuteoCobradorDia() - Error lanzada al"
        + " tratar de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarRuteoDia

} //Fin de la clase ControladorRuteoCobrador