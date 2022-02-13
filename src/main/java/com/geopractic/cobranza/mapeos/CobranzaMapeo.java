package com.geopractic.cobranza.mapeos;

import com.geopractic.administracion.dao.ClienteDao;
import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.cobranza.dao.SaldoDao;
import com.geopractic.cobranza.modelo.Cobranza;
import com.geopractic.db.ExcepcionDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class CobranzaMapeo implements RowMapper<Cobranza> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public CobranzaMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Cobranza map(ResultSet rs, StatementContext ctx) throws SQLException {
    Cobranza cobranza = new Cobranza();
    cobranza.setId(rs.getLong("Id"));
    cobranza.setFecha(rs.getDate("Fecha"));
    cobranza.setRecibo(rs.getString("Recibo"));
    cobranza.setFactura(rs.getString("Factura"));
    cobranza.setMonto(rs.getLong("Monto"));
    cobranza.setInteres(rs.getLong("Interes"));

    try {
      //Obtiene el cliente del pago
      ClienteDao dao = new ClienteDao();
      dao.setConexion(conexion);
      cobranza.setCliente(dao.buscarId(rs.getLong("Cliente")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> CobranzaMapeo - Error al mapear cliente del cobro. ",
            excepcion);
    } //Fin de try -catch

    try {
      //Obtiene el empleado que cobro el pago
      EmpleadoDao dao = new EmpleadoDao();
      dao.setConexion(conexion);
      cobranza.setCobrador(dao.buscarId(rs.getLong("Cobrador")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> CobranzaMapeo - Error al mapear el cobrador del cobro.",
            excepcion);
    } //Fin de try -catch

    try {
      //Obtiene el saldo relacionado al cobro.
      SaldoDao dao = new SaldoDao();
      dao.setConexion(conexion);
      cobranza.setSaldo(dao.buscarId(rs.getLong("Saldo")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> CobranzaMapeo - Error al mapear el saldo del cobro. ",
            excepcion);
    } //Fin de try -catch
    return cobranza;
  } //Fin de mapeo

} //Fin de la clase 