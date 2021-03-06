package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.VentanaMapeo;
import com.geopractic.sistema.mapeos.VentanaRolMapeo;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Ventana;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas
 * las ventanas de la aplicacion.
 * @author Cristhian
 *
 */
public class VentanaDao extends Dao<Ventana> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public VentanaDao() {
    queryBuscar = "SELECT * FROM \"Ventana\" WHERE \"Nombre\" = :nombre ;";

    queryBuscarId = "SELECT \"Id\", \"Nombre\" FROM \"Ventana\" WHERE \"Id\" = :id ;";

    queryListarTodos = "SELECT * FROM \"Ventana\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"Ventana\"(\"Nombre\", \"Ubicacion\") "
      + "VALUES (:nombre,:ubicacion);";

    queryModificar = "UPDATE \"Ventana\" SET \"Nombre\" = :nombre, "
      + "\"Ubicacion\" = :ubicacion WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM public.\"Ventana\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new VentanaMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Ventana ventana, Ventana ventanaNueva) {
    ventana.setId(ventanaNueva.getId());
  } //Fin del metodo obtenerClavePrimaria

  /**
   * Busca todas las ventanas que estan asignadas a un rol especifico.
   * @param id el id del rol a buscar.
   * @return listado de ventanas graficas asociadas al Rol.
   * @throws ExcepcionDao excepcion lanzada en la capa Dao.
   */
  public List<Ventana> listarVentanasDeRol(long id) throws ExcepcionDao {
    List<Ventana> listaVentana = new ArrayList<Ventana>();  
    listaVentana = conexion.createQuery("SELECT \"RolVentana\".*,\"Ventana\".\"Nombre\" " 
            + ",\"Ventana\".\"Ubicacion\" FROM \"RolVentana\" INNER JOIN \"Ventana\"" 
            + " ON \"RolVentana\".\"Ventana\" = \"Ventana\".\"Id\"" 
            + " WHERE \"RolVentana\".\"Rol\" = :id;")
       .bind("id", id)
       .map(new VentanaRolMapeo())
       .list();
    
    return listaVentana;  
  } //Fin del metodo listarVentanasDeRol

  /**
   * Modifica los permisos del rol de la ventana seleccionados.
   * @param ventana la ventana a modificar.
   * @param rol el rol a modificar.
   * @return si actualizo o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean modificarRolVentana(Ventana ventana,Rol rol) throws ExcepcionDao {

    int respuesta = 0;
    respuesta = conexion.createUpdate("UPDATE \"RolVentana\" SET " 
            + "\"Leer\" = :vent.leer , \"Crear\" = :vent.crear , "
            + "\"Actualizar\" = :vent.actualizar , \"Eliminar\" = :vent.eliminar "
            + " WHERE \"Rol\" = :rol.id AND \"Ventana\" = :vent.id")
       .bindBean("vent",ventana)
       .bindBean("rol",rol)
       .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo modificarRolVentana

  /**
   * Elimina las relaciones de ventana asociadas a roles.
   * @param ventana la ventana a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarVentanaRol(Ventana ventana) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"RolVentana\" WHERE \"Ventana\" = :id ;")
      .bindBean(ventana)
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol
  
} //Fin de la clase VentanaDao