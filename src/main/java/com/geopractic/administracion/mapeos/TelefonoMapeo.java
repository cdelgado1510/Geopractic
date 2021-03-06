package com.geopractic.administracion.mapeos;

import com.geopractic.administracion.modelo.Telefono;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.TipoTelefonoDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import org.slf4j.Logger;

public class TelefonoMapeo implements RowMapper<Telefono> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public TelefonoMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Telefono map(ResultSet rs, StatementContext ctx) throws SQLException {
    Telefono telefono = new Telefono();
    telefono.setId(rs.getLong("Id"));
    telefono.setNumero(rs.getString("Numero"));
    
    try {
      //Obtiene el tipo de telefono.
      TipoTelefonoDao dao = new TipoTelefonoDao();
      dao.setConexion(conexion);
      telefono.setTipoTelefono(dao.buscarId(rs.getLong("TipoTelefono")));
    } catch (ExcepcionDao excepcion) {
      logger.error("Error al mapear el tipo de telefono.");
    } //Fin de try -catch
    return telefono;
  } //Fin del mapeo

} //Fin del clase TelefonoMapeo