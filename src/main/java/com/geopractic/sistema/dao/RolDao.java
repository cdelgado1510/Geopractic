package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.RolMapeo;
import com.geopractic.sistema.modelo.Menu;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Ventana;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * el usuario.
 * @author Cristhian
 *
 */
public class RolDao extends Dao<Rol> {
  
  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public RolDao() {
    queryBuscar = "SELECT * FROM \"Rol\" WHERE \"Descripcion\" = :descripcion ;";

    queryBuscarId = "SELECT * FROM \"Rol\" WHERE \"Id\" = :id ";

    queryListarTodos = "SELECT * FROM \"Rol\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"Rol\"(\"Descripcion\") VALUES (:descripcion);";

    queryModificar = "UPDATE \"Rol\" SET \"Descripcion\" = :descripcion WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM \"Rol\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new RolMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Rol rol, Rol rolNuevo) {
    rol.setId(rolNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  /**
   * Guarda la relacion de un menu a un rol.
   * @param rol el rol a asociar al menu.
   * @param menu el menu a asociar al rol.
   * @return si guardo o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean guardarMenuRol(Rol rol,Menu menu) throws ExcepcionDao {    
    int respuesta = 0;
    respuesta = conexion.createUpdate("INSERT INTO \"RolMenu\"(\"Rol\", \"Menu\")"
           + " VALUES (:rol.id, :menu.id); ")
      .bindBean("rol", rol)
      .bindBean("menu", menu)
      .execute();
    
    return (respuesta == 1) ? true : false;
  } //Fin del metodo guardarMenuRol

  /**
   * Guarda la relacion de una ventana a un rol.
   * @param rol el rol a asociar a la ventana.
   * @param ventana la ventana a asociar al rol.
   * @return si guardo o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean guardarVentanaRol(Rol rol, Ventana ventana) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("INSERT INTO \"RolVentana\"(\"Leer\", \"Crear\", "
            + "\"Actualizar\", \"Eliminar\", \"Rol\", \"Ventana\") "
            + "VALUES (:vent.leer ,:vent.crear ,:vent.actualizar ,:vent.eliminar ,"
            + ":rol ,:vent.id);")
      .bind("rol", rol.getId())
      .bindBean("vent", ventana)
      .execute();
    
    return (respuesta == 1) ? true : false;
  } //Fin del metodo guardarVentanaRol

  /**
   * Modifica la relacion de una ventana a un rol.
   * @param rol el rol asociado a la ventana.
   * @param ventana la ventana asociada al rol.
   * @return si guardo o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean modificarVentanaRol(Rol rol, Ventana ventana) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("UPDATE \"RolVentana\" SET \"Leer\" = :vent.leer"
              + ", \"Crear\" = :vent.crear , \"Actualizar\" = :vent.actualizar "
              + ", \"Eliminar\" = :vent.eliminar WHERE \"Rol\" = :rol "
              + "AND \"Ventana\" = :vent.id ;")
      .bind("rol", rol.getId())
      .bindBean("vent", ventana)
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo modificarVentanaRol
  
  /**
   * Elimina las relaciones de menu asociadas a roles.
   * @param rol el rol a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarMenuRol(Rol rol) throws ExcepcionDao {    
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"RolMenu\" WHERE \"Rol\" = :rol ;")
      .bind("rol", rol.getId())
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol

  /**
   * Elimina las relaciones de menu asociadas a roles.
   * @param rol el rol a eliminar.
   * @param menu el menu a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarMenuRol(Rol rol,Menu menu) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"RolMenu\" WHERE \"Rol\" = :rol"
            + " AND \"Menu\" = :menu ;")
      .bind("rol", rol.getId())
      .bind("menu", menu.getId())
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol
  
  /**
   * Elimina las relaciones de ventana asociadas a roles.
   * @param rol el rol a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarVentanaRol(Rol rol) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"RolVentana\" WHERE \"Rol\" = :id ;")
      .bind("id", rol.getId())
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol
  
  /**
   * Elimina las relaciones de ventana asociadas a roles.
   * @param rol el rol a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarVentanaRol(Rol rol, Ventana ventana) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"RolVentana\" WHERE \"Rol\" = :rol"
            + " AND \"Ventana\" = :vent ;")
      .bind("rol", rol.getId())
      .bind("vent", ventana.getId())
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol

  /**
   * Elimina el rol de sus usuarios asignados.
   * @param rol el rol a eliminar.
   * @return si elimino o no la relacion con usuario.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarRolUsuario(Rol rol) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("UPDATE \"Usuario\" SET "
       + "\"Rol\" = :rolUsuario WHERE \"Rol\" = :rol ;")
      .bindNull("rolUsuario", java.sql.Types.BIGINT)
      .bind("rol", rol.getId())
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarRolUsuario
} //Fin de la clase RolDao