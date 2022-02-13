package com.geopractic.cobranza.mapeos;

import com.geopractic.administracion.dao.ClienteDao;
import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.cobranza.dao.SaldoDao;
import com.geopractic.cobranza.modelo.CuentaPorCobrar;
import com.geopractic.db.ExcepcionDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class CuentaPorCobrarMapeo implements RowMapper<CuentaPorCobrar> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public CuentaPorCobrarMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public CuentaPorCobrar map(ResultSet rs, StatementContext ctx) throws SQLException {
    CuentaPorCobrar cuenta = new CuentaPorCobrar();
    cuenta.setId(rs.getLong("Id"));
    cuenta.setMontoTotal(rs.getLong("MontoTotal"));
    cuenta.setCuotas(rs.getInt("Cuotas"));
    cuenta.setVencimiento(rs.getDate("Vencimiento"));
    cuenta.setFactura(rs.getString("Factura"));
    cuenta.setFecha(rs.getDate("Fecha"));

    try {
      //Obtiene el Cliente de la cuenta
      ClienteDao dao = new ClienteDao();
      dao.setConexion(conexion);
      cuenta.setCliente(dao.buscarId(rs.getLong("Cliente")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> CuentaPorCobrarMapeo - Error al mapear el cliente de la cuenta.",
            excepcion);
    } //Fin de try -catch

    try {
      //Obtiene el Empleado asignado a la cuenta.
      EmpleadoDao dao = new EmpleadoDao();
      dao.setConexion(conexion);
      cuenta.setEmpleadoAsignado(dao.buscarId(rs.getLong("EmpleadoAsignado")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> CuentaPorCobrarMapeo - Error al mapear el empleado asignado a "
            + "la cuenta.",excepcion);
    } //Fin de try -catch

    try {
      //Obtiene los saldos asignados a la cuenta.
      SaldoDao dao = new SaldoDao();
      dao.setConexion(conexion);
      cuenta.setSaldos(dao.listarSaldosCuenta(cuenta));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> CuentaPorCobrarMapeo - Error al mapear el empleado asignado a "
            + "la cuenta.",excepcion);
    } //Fin de try -catch

    return cuenta;
  } //Fin del mapeo

} //Fin de la clase CuentaPorCobrarMapeo