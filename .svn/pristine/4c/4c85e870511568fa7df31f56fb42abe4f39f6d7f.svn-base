package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.Logs;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class LogsMapeo implements RowMapper<Logs> {

  @Override
  public Logs map(ResultSet rs, StatementContext ctx) throws SQLException {
    Logs log = new Logs();
    log.setId(rs.getLong("Id"));
    log.setFecha(rs.getTimestamp("fecha"));
    log.setLogger(rs.getString("logger"));
    log.setNivel(rs.getString("nivel"));
    log.setMensaje(rs.getString("mensaje"));
    log.setExcepcion(rs.getString("excepcion"));
    return log;
  }

} //Fin de la clase LogsMapeo