package com.geopractic.gps.mapeos;

import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.gps.dao.EstadosRastreadorGpsDao;
import com.geopractic.gps.modelo.Rastreador;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class RastreadorMapeo implements RowMapper<Rastreador> {

  private Handle conexion;
  private Logger logger;

  
  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public RastreadorMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor

  @Override
  public Rastreador map(ResultSet rs, StatementContext ctx) throws SQLException {
    Rastreador rastreador = new Rastreador();
    rastreador.setId(rs.getLong("Id"));
    rastreador.setImei(rs.getString("IMEI"));
    rastreador.setDescripcion(rs.getString("Descripcion"));

    try {
      //Obtiene el estado del GPS
      EstadosRastreadorGpsDao dao = new EstadosRastreadorGpsDao();
      dao.setConexion(conexion);
      rastreador.setEstado(dao.buscarId(rs.getLong("Estado")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> RastreadorMapeo - Error al mapear el estado del GPS.");
    } //Fin de try-catch

    try {
      //Obtiene el empleado del GPS
      EmpleadoDao dao = new EmpleadoDao(); 
      dao.setConexion(conexion);
      rastreador.setEmpleadoAsignado(dao.buscarId(rs.getLong("Empleado")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> RastreadorMapeo - Error al mapear el empleado.");
    } //Fin de try-catch

    return rastreador;
  } //Fin del mapeo

} //Fin de la clase RastreadorMapeo