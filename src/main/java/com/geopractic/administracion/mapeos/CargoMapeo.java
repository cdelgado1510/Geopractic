package com.geopractic.administracion.mapeos;

import com.geopractic.administracion.modelo.Cargo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class CargoMapeo implements RowMapper<Cargo> {

  @Override
  public Cargo map(ResultSet rs, StatementContext ctx) throws SQLException {
    Cargo cargo = new Cargo();
    cargo.setId(rs.getLong("Id"));
    cargo.setDescripcion(rs.getString("Descripcion"));
    return cargo;
  } //Fin de mapeo

} //Fin de clase CargoMapeo