package com.geopractic.georrefencia.mapeos;

import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.modelo.Mapa;
import com.geopractic.gps.dao.RastreadorDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class MapaMapeo implements RowMapper<Mapa> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public MapaMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Mapa map(ResultSet rs, StatementContext ctx) throws SQLException {
    Mapa mapa = new Mapa();
    mapa.setId(rs.getLong("Id"));
    mapa.setVerZonas(rs.getBoolean("VerZonas"));
    mapa.setPanel(rs.getLong("PanelVisualizacion"));

    try {
      //Obtiene el rastreador del mapa.
      RastreadorDao dao = new RastreadorDao();
      dao.setConexion(conexion);
      mapa.setRastreadorGps(dao.buscarId(rs.getLong("Rastreador")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> MapaMapeo - Error al mapear el rastreador del mapa.",
              excepcion);
    } //Fin de try -catch

    return mapa;
  } //Fin de mapeo

} //Fin de la clase MapaMapeo