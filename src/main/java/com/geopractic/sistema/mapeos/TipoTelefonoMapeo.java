package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.TipoTelefono;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeado para los objetos Tipo Telefono del sistema.
 * @author Cristhian
 */
public class TipoTelefonoMapeo implements RowMapper<TipoTelefono> {

  @Override
  public TipoTelefono map(ResultSet rs, StatementContext ctx) throws SQLException {
    TipoTelefono tipoTelefono = new TipoTelefono();
    tipoTelefono.setId(rs.getLong("Id"));
    tipoTelefono.setNombre(rs.getString("Nombre"));
    tipoTelefono.setPatron(rs.getString("Patron"));
    return tipoTelefono;
  } //Fin de mapeo

} //Fin de la clase TipoTelefonoMapeo