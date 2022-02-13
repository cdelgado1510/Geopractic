package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.TipoDocumento;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeado para los objetos Tipo Documento del sistema.
 * @author Cristhian
 */
public class TipoDocumentoMapeo implements RowMapper<TipoDocumento> {

  @Override
  public TipoDocumento map(ResultSet rs, StatementContext ctx) throws SQLException {
    TipoDocumento tipoDocumento = new TipoDocumento();
    tipoDocumento.setId(rs.getLong("Id"));
    tipoDocumento.setDescripcion(rs.getString("Descripcion"));
    return tipoDocumento;
  } //Fin del mapeo

} //Fin de la clase TipoDocumentoMapeo