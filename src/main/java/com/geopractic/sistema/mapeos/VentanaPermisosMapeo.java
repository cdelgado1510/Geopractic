package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.Ventana;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeo de las ventanas de permisos de la ventana.
 * @author Cristhian
 *
 */
public class VentanaPermisosMapeo implements RowMapper<Ventana> {

  private Ventana ventana;

  /**
   * Constructor con argumentos.
   * @param ventana la ventana a mapear.
   */
  public VentanaPermisosMapeo(Ventana ventana) {
    this.ventana = ventana;
  } //Fin de constructor con argumentos


  @Override
  public Ventana map(ResultSet rs, StatementContext ctx) throws SQLException {
    ventana.setId(rs.getLong("Ventana"));
    ventana.setCrear(rs.getBoolean("Crear"));
    ventana.setLeer(rs.getBoolean("Leer"));
    ventana.setActualizar(rs.getBoolean("Actualizar"));
    ventana.setEliminar(rs.getBoolean("Eliminar"));
    return ventana;
  } //Fin de mapeo

} //Fin de la clase VentanaPermisosMapeo