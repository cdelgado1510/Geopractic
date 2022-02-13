package com.geopractic.georrefencia.mapeos;

import com.geopractic.georrefencia.modelo.TipoGeorreferencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class TipoGeorreferenciaMapeo implements RowMapper<TipoGeorreferencia> {

  @Override
  public TipoGeorreferencia map(ResultSet rs, StatementContext ctx) throws SQLException {
    TipoGeorreferencia tipo = new TipoGeorreferencia();
    tipo.setId(rs.getLong("Id"));
    tipo.setDescripcion(rs.getString("Descripcion"));
    tipo.setClase(rs.getString("Clase"));
    tipo.setIcono(rs.getString("Icono"));
    tipo.setColorRelleno(rs.getString("ColorRelleno"));
    tipo.setColorContorno(rs.getString("ColorContorno"));
    tipo.setActivo(rs.getBoolean("Activo"));
    return tipo;
  } //Fin del mapeo

} //Fin de la clase TipoGeorreferenciaMapeo