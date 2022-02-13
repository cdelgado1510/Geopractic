package com.geopractic.georrefencia.mapeos;

import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.TipoGeorreferenciaDao;
import com.geopractic.georrefencia.modelo.Georreferencia;
import com.geopractic.georrefencia.modelo.Punto;
import com.geopractic.georrefencia.modelo.Zona;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class GeorreferenciaMapeo implements RowMapper<Georreferencia> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public GeorreferenciaMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Georreferencia map(ResultSet rs, StatementContext ctx) throws SQLException {
    Georreferencia georreferencia = new Georreferencia();
    georreferencia.setId(rs.getLong("Id"));
    georreferencia.setDescripcion(rs.getString("Descripcion"));
    long tipo = rs.getLong("Tipo");

    try {
      //Obtiene el Tipo de la georreferencia
      TipoGeorreferenciaDao dao = new TipoGeorreferenciaDao();
      dao.setConexion(conexion);
      georreferencia.setTipo(dao.buscarId(tipo));

      //Verifica si es un punto o una zona
      if (georreferencia.getTipo().getClase().equals("Punto")) {
        String geometria = rs.getString("Punto");
        Punto punto = new Punto();
        punto.extraerLatLon(conexion.createQuery("SELECT ST_AsText('" + geometria + "');")
             .mapTo(String.class).findOnly());
        georreferencia.setPunto(punto);
      } else {
        String geometria = rs.getString("Zona");
        Zona zona = new Zona(conexion.createQuery("SELECT ST_AsText('" + geometria + "');")
                .mapTo(String.class).findOnly());
        georreferencia.setZona(zona);
      } //Fin de if-else
    } catch (ExcepcionDao excepcion) {
      logger.error("--> mapeoGeorreferencia - Error al mapear el tipo de georreferencia.",
            excepcion);
    } //Fin de try -catch

    return georreferencia;
  } //Fin de mapeo

} //Fin de la clase GeorreferenciaMapeo