package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.Turnos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeado para los objetos turnos del sistema.
 * @author Cristhian
 */
public class TurnosMapeo implements RowMapper<Turnos> {

  @Override
  public Turnos map(ResultSet rs, StatementContext ctx) throws SQLException {
    Turnos turno = new Turnos();
    turno.setId(rs.getLong("Id"));
    turno.setDescripcion(rs.getString("Descripcion"));
    turno.setHoraInicio(rs.getTime("HoraInicio"));
    turno.setHoraFin(rs.getTime("HoraFin"));
    turno.setDomingo(rs.getBoolean("Domingo"));
    turno.setLunes(rs.getBoolean("Lunes"));
    turno.setMartes(rs.getBoolean("Martes"));
    turno.setMiercoles(rs.getBoolean("Miercoles"));
    turno.setJueves(rs.getBoolean("Jueves"));
    turno.setViernes(rs.getBoolean("Viernes"));
    turno.setSabado(rs.getBoolean("Sabado"));
    return turno;
  } //Fin del metodo map

} //Fin de la clase TurnosMapeo