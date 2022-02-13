package com.geopractic.georrefencia.mapeos;

import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.MapaDao;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;
import com.geopractic.sistema.dao.TurnosDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class PanelVisualizacionMapeo implements RowMapper<PanelVisualizacion> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public PanelVisualizacionMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public PanelVisualizacion map(ResultSet rs, StatementContext ctx) throws SQLException {
    PanelVisualizacion panel = new PanelVisualizacion();
    panel.setId(rs.getLong("Id"));
    panel.setDescripcion(rs.getString("Descripcion"));
    panel.setIntervaloRefresco(rs.getInt("IntervaloRefresco"));

    try {
      //Obtiene el rastreador del mapa.
      EmpleadoDao daoEmpleado = new EmpleadoDao();
      daoEmpleado.setConexion(conexion);
      panel.setEmpleadoCreador(daoEmpleado.buscarId(rs.getLong("EmpleadoCreador")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> PanelVisualizacionMapeo - Error al mapear el empleado creador.",
              excepcion);
    } //Fin de try -catch

    try {
      //Obtiene los mapas del visor.
      MapaDao daoMapa = new MapaDao();
      daoMapa.setConexion(conexion);
      panel.setListaMapas(daoMapa.listarMapasPanel(panel));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> PanelVisualizacionMapeo - Error al mapear los mapas del panel.",
                excepcion);
    } //Fin de try -catch

    try {
      //Obtiene el turno del visor.
      TurnosDao daoTurno = new TurnosDao();
      daoTurno.setConexion(conexion);
      panel.setTurno(daoTurno.buscarId(rs.getLong("Turno")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> PanelVisualizacionMapeo - Error al mapear el turno del panel.",
                  excepcion);
    } //Fin de try -catch

    return panel;
  } //Fin de mapeo

} //Fin de clase PanelVisualizacionMapeo