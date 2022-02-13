package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.Ventana;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeado para los objetos ventana del sistema.
 * @author Cristhian
 */
public class VentanaMapeo implements RowMapper<Ventana> {

  @Override
  public Ventana map(ResultSet rs, StatementContext ctx) throws SQLException {
    Ventana ventana = new Ventana();
    ventana.setId(rs.getLong("Id"));
    ventana.setNombre(rs.getString("Nombre"));
    ventana.setUbicacion(rs.getString("Ubicacion"));
    return ventana;
  } //Fin del mapeo

} //Fin de la clase VentanaMapeo