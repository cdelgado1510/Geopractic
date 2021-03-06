package com.geopractic.sistema.mapeos;

import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.MenuDao;
import com.geopractic.sistema.dao.VentanaDao;
import com.geopractic.sistema.modelo.Rol;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import org.slf4j.Logger;

/**
 * Clase de mapeado para los objetos rol del sistema.
 * @author Cristhian
 */
public class RolMapeo implements RowMapper<Rol> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public RolMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Rol map(ResultSet rs, StatementContext ctx) throws SQLException {
    Rol rol = new Rol();
    rol.setId(rs.getLong("Id"));
    rol.setDescripcion(rs.getString("Descripcion"));

    try {
      //Obtiene las ventanas del rol
      VentanaDao ventanaDao = new VentanaDao();
      ventanaDao.setConexion(conexion);
      rol.setVentanas(ventanaDao.listarVentanasDeRol(rol.getId()));
      
      //Obtiene los menus del rol
      MenuDao menuDao = new MenuDao();
      menuDao.setConexion(conexion);
      rol.setMenus(menuDao.listarMenusRol(rol));
      
    } catch (ExcepcionDao excepcion) {
      logger.error("Error al mapear el rol.");
    } //Fin de try -catch

    return rol;
  } //Fin del mapeo

} //Fin de la clase RolMapeo