package com.geopractic.cobranza.mapeos;

import com.geopractic.cobranza.dao.GestionVisitaDao;
import com.geopractic.cobranza.modelo.RuteoCobrador;
import com.geopractic.db.ExcepcionDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class RuteoCobradorMapeo implements RowMapper<RuteoCobrador> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public RuteoCobradorMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public RuteoCobrador map(ResultSet rs, StatementContext ctx) throws SQLException {
    RuteoCobrador ruteo = new RuteoCobrador();
    ruteo.setId(rs.getLong("Id"));
    ruteo.setFechaVisita(rs.getDate("FechaVisita"));
    ruteo.setVisitado(rs.getBoolean("Visitado"));
    ruteo.setVerificado(rs.getBoolean("Cumplido"));
    ruteo.setFechaCreacion(rs.getDate("FechaCreacion"));
    ruteo.setFechaCreacion(rs.getDate("FechaCumplido"));

    try {
      //Obtiene la gestion del ruteo
      GestionVisitaDao dao = new GestionVisitaDao();
      dao.setConexion(conexion);
      ruteo.setGestion(dao.buscarId(rs.getLong("Gestion")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> RuteoCobradorMapeo - Error al mapear la gestion del ruteo. ",
           excepcion);
    } //Fin de try -catch

    return ruteo;
  } //Fin del mapeo

} //Fin de la clase RuteoCobradorMapeo