package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.Ventana;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeo de las ventanas de un rol.
 * @author Cristhian
 *
 */
public class VentanaRolMapeo implements RowMapper<Ventana> {

  @Override
  public Ventana map(ResultSet rs, StatementContext ctx) throws SQLException {
    Ventana ventana = new Ventana();
    ventana.setId(rs.getLong("Ventana"));
    ventana.setNombre(rs.getString("Nombre"));
    ventana.setUbicacion(rs.getString("Ubicacion"));
    ventana.setCrear(rs.getBoolean("Crear"));
    ventana.setLeer(rs.getBoolean("Leer"));
    ventana.setActualizar(rs.getBoolean("Actualizar"));
    ventana.setEliminar(rs.getBoolean("Eliminar"));
    return ventana;
  } //Fin de mapeo

} //Fin de la clase VentanaRolMapeo