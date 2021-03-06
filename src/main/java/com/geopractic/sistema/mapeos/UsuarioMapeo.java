package com.geopractic.sistema.mapeos;

import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.RolDao;
import com.geopractic.sistema.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import org.slf4j.Logger;

/**
 * Clase para mapeo de la tabla Usuario.
 * @author Cristhian
 *
 */
public class UsuarioMapeo implements RowMapper<Usuario> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public UsuarioMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Usuario map(ResultSet rs, StatementContext contexto) throws SQLException {
    Usuario usuario = new Usuario();
    usuario.setId(rs.getLong("Id"));
    usuario.setNombre(rs.getString("Nombre"));
    usuario.setApellido(rs.getString("Apellido"));
    usuario.setUsuario(rs.getString("Usuario"));
    usuario.setContrasenha(rs.getString("Contrasenha"));
    usuario.setActivo(rs.getBoolean("Activo"));

    int rol = rs.getInt("Rol");
    if (rol != 0) {
      try {
        //Obtiene el Rol del usuario
        RolDao dao = new RolDao();
        dao.setConexion(conexion);
        usuario.setRol(dao.buscarId(rol));
      } catch (ExcepcionDao excepcion) {
        logger.error("UsuarioMapeo --> Error al mapear el rol.");
      } //Fin de try -catch
    } //Fin de if

    int empleado = rs.getInt("Empleado");
    if (empleado != 0) {
      try {
        //Obtiene el Empleado del usuario
        EmpleadoDao dao = new EmpleadoDao();
        dao.setConexion(conexion);
        usuario.setEmpleado(dao.buscarId(empleado));
      } catch (ExcepcionDao excepcion) {
        logger.error("UsuarioMapeo --> Error al mapear el empleado.");
      } //Fin de try -catch
    } //Fin de if

    return usuario;
  } //Fin de implementacion map
  
} //Fin de la clase UsuarioMapeo