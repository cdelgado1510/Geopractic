package com.geopractic.administracion.mapeos;

import com.geopractic.administracion.dao.CargoDao;
import com.geopractic.administracion.dao.TelefonoDao;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.db.ExcepcionDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;

public class EmpleadoMapeo implements RowMapper<Empleado> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public EmpleadoMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Empleado map(ResultSet rs, StatementContext ctx) throws SQLException {
    Empleado empleado = new Empleado();
    empleado.setId(rs.getLong("Id"));
    empleado.setNombre(rs.getString("Nombre"));
    empleado.setApellido(rs.getString("Apellido"));
    empleado.setCedula(rs.getString("Cedula"));
    empleado.setFechaNacimiento(rs.getDate("FechaNacimiento"));
    empleado.setEstado(rs.getString("Estado"));
    
    int cargo = rs.getInt("Cargo");
    if (cargo != 0) {
      try {
        //Obtiene el Cargo del Empleado
        CargoDao dao = new CargoDao();
        dao.setConexion(conexion);
        empleado.setCargo(dao.buscarId(cargo));
      } catch (ExcepcionDao excepcion) {
        logger.error("--> EmpleadoMapeo - Error al mapear el cargo del empleado.");
      } //Fin de try -catch
    } //Fin de if

    try {
      //Obtiene los numeros de telefono asociados al empleado.
      TelefonoDao daoTelefono = new TelefonoDao();
      daoTelefono.setConexion(conexion);
      empleado.setNumerosTelefonicos(daoTelefono.listarTelefonosEmpleado(empleado));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> EmpleadoMapeo - Error al mapear los telefonos del empleado.");
    } //Fin de try -catch
    return empleado;
  } //Fin de mapeo

} //Fin de clase EmpleadoMapeo