package com.geopractic.gps.mapeos;

import com.geopractic.gps.modelo.EstadosRastreadorGps;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class EstadosRastreadorGpsMapeo implements RowMapper<EstadosRastreadorGps> {

  @Override
  public EstadosRastreadorGps map(ResultSet rs, StatementContext ctx) throws SQLException {
    EstadosRastreadorGps estado = new EstadosRastreadorGps();
    estado.setId(rs.getLong("Id"));
    estado.setDescripcion(rs.getString("Descripcion"));
    return estado;
  } //Fin de mapeo

} //Fin de la clase EstadosRastreadorGpsMapeo