package com.geopractic.cobranza.mapeos;

import com.geopractic.cobranza.modelo.Motivo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class MotivoMapeo implements RowMapper<Motivo> {

  @Override
  public Motivo map(ResultSet rs, StatementContext ctx) throws SQLException {
    Motivo motivo = new Motivo();
    motivo.setId(rs.getLong("Id"));
    motivo.setDescripcion(rs.getString("Descripcion"));
    motivo.setActivo(rs.getBoolean("Activo"));
    motivo.setRuteo(rs.getBoolean("Ruteo"));
    return motivo;
  } //Fin del mapeo

} //Fin de la clase MotivoMapeo