package com.geopractic.cobranza.mapeos;

import com.geopractic.cobranza.modelo.FormaDePago;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class FormaDePagoMapeo implements RowMapper<FormaDePago> {

  @Override
  public FormaDePago map(ResultSet rs, StatementContext ctx) throws SQLException {
    FormaDePago formaDePago = new FormaDePago();
    formaDePago.setId(rs.getLong("Id"));
    formaDePago.setDescripcion(rs.getString("Descripcion"));
    return formaDePago;
  } //Fin de mapeo

} //Fin de clase FormaDePagoMapeo