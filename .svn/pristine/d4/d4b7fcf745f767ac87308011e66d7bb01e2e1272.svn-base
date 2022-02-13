package com.geopractic.cobranza.mapeos;

import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.cobranza.dao.FormaDePagoDao;
import com.geopractic.cobranza.dao.MotivoDao;
import com.geopractic.cobranza.dao.SaldoDao;
import com.geopractic.cobranza.modelo.GestionVisita;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.GeorreferenciaDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class GestionVisitaMapeo implements RowMapper<GestionVisita> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public GestionVisitaMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public GestionVisita map(ResultSet rs, StatementContext ctx) throws SQLException {
    GestionVisita gestion = new GestionVisita();
    gestion.setId(rs.getLong("Id"));
    gestion.setFecha(rs.getDate("Fecha"));
    gestion.setFechaVisita(rs.getDate("FechaVisita"));
    gestion.setMonto(rs.getLong("Monto"));
    gestion.setComentario(rs.getString("Comentario"));

    try {
      //Obtiene el Motivo de la Gestion
      MotivoDao dao = new MotivoDao();
      dao.setConexion(conexion);
      gestion.setMotivo(dao.buscarId(rs.getLong("Motivo")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> GestionVisitaMapeo - Error al mapear el motivo de la gestion.",
            excepcion);
    } //Fin de try -catch

    try {
      EmpleadoDao dao = new EmpleadoDao();
      dao.setConexion(conexion);

      //Obtiene el Empleado que cargo la gestion
      gestion.setEmpleadoCarga(dao.buscarId(rs.getLong("EmpleadoCarga")));
      
      //Obtiene el Empleado al cual se asigno la visita
      gestion.setEmpleadoCarga(dao.buscarId(rs.getLong("EmpleadoVisita")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> GestionVisitaMapeo - Error al mapear los empleados de la gestion. ",
              excepcion);
    } //Fin de try -catch

    try {
      //Obtiene la Forma de pago de la gestion
      FormaDePagoDao dao = new FormaDePagoDao();
      dao.setConexion(conexion);
      gestion.setFormaDePago(dao.buscarId(rs.getLong("FormaDePago")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> GestionVisitaMapeo - Error al mapear la forma de pago de la gestion. ",
            excepcion);
    } //Fin de try -catch
    
    try {
      //Obtiene el Lugar de visita de la gestion
      GeorreferenciaDao dao = new GeorreferenciaDao();
      dao.setConexion(conexion);
      gestion.setLugarVisita(dao.buscarId(rs.getLong("LugarVisita")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> GestionVisitaMapeo - Error al mapear el lugar de visita.",
              excepcion);
    } //Fin de try -catch
    
    try {
      //Obtiene el saldo asociado a la gestion
      SaldoDao dao = new SaldoDao();
      dao.setConexion(conexion);
      gestion.setSaldo(dao.buscarId(rs.getLong("Saldo")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> GestionVisitaMapeo - Error al mapear el saldo de la gestion. ",
              excepcion);
    } //Fin de try -catch

    return gestion;
  } //Fin del mapeo

} //Fin de la clase GestionVisitaMapeo