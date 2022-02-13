package com.geopractic.administracion.dao;

import com.geopractic.administracion.mapeos.TelefonoMapeo;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.administracion.modelo.Telefono;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los telefonos.
 * @author Cristhian
 *
 */
public class TelefonoDao extends Dao<Telefono> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public TelefonoDao() {
    queryBuscar = "SELECT * FROM \"Telefonos\" WHERE \"Numero\" = :numero ;";

    queryBuscarId = "SELECT * FROM \"Telefonos\" WHERE \"Id\" = :id";

    queryListarTodos = "SELECT * FROM \"Telefonos\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"Telefonos\"( \"Numero\", \"TipoTelefono\")"
       + " VALUES ( :numero , :tipoTelefono.id );";

    queryModificar = "UPDATE \"Telefonos\" SET \"Numero\" = :numero" 
       + " WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM \"Telefonos\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new TelefonoMapeo(this.conexion,this.log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Telefono telefono, Telefono telefonoNuevo) {
    telefono.setId(telefonoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria
  
  /**
   * Devuelve el listado de telefonos asociados un cliente.
   * @param cliente el cliente a buscar.
   * @return el listado de telefonos del cliente
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Telefono> listarTelefonosCliente(Cliente cliente) throws ExcepcionDao  {
    List<Telefono> listaTelefono = new ArrayList<Telefono>();
    listaTelefono = conexion.createQuery("SELECT * FROM \"Telefonos\""  
        + "WHERE \"ClienteAsignado\" = :cliente ;")
        .bind("cliente", cliente.getId())
        .map(mapeoBean)
        .list();
    return listaTelefono;
  } //Fin del metodo listarTelefonosCliente

  /**
   * Asocia un numero de telefono con un cliente.
   * @param cliente el cliente al cual asociar el telefono.
   * @param telefono el telefono al cual asociar el cliente.
   * @return si guardo o no el telefono.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean asociarTelefonoCliente(Cliente cliente, Telefono telefono) throws ExcepcionDao  {
    int respuesta = 0;
    respuesta = conexion.createUpdate("INSERT INTO \"Telefonos\"(" 
          + " \"Numero\", \"TipoTelefono\", \"ClienteAsignado\")"
          + " VALUES ( :telefono,:tipoTelefono , :cliente );")
          .bind("cliente", cliente.getId())
          .bind("tipoTelefono", telefono.getTipoTelefono().getId())
          .bind("telefono", telefono.getNumero())
          .execute();

    return (respuesta == 1) ? true : false;
  } //Fin del metodo asociarTelefonoCliente

  /**
  * Elimina un numero de telefono con un cliente.
  * @param cliente el cliente asociado el telefono.
  * @param telefono el telefono asociado al cliente.
  * @return si elimino o no el telefono.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  public boolean eliminarTelefonoCliente(Cliente cliente, Telefono telefono) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"Telefonos\" "
        + "WHERE \"Numero\" = :telefono AND \"ClienteAsignado\" = :cliente ;")
       .bind("cliente", cliente.getId())
       .bind("telefono", telefono.getNumero())
       .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarTelefonoCliente

  /**
   * Devuelve el listado de telefonos asociados un empleado.
   * @param empleado el empleado a buscar.
   * @return el listado de telefonos del empleado
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Telefono> listarTelefonosEmpleado(Empleado empleado) throws ExcepcionDao  {
    List<Telefono> listaTelefono = new ArrayList<Telefono>();
    listaTelefono = conexion.createQuery("SELECT * FROM \"Telefonos\""  
        + "WHERE \"EmpleadoAsignado\" = :empleado ;")
        .bind("empleado", empleado.getId())
        .map(mapeoBean)
        .list();
    return listaTelefono;
  } //Fin del metodo listarTelefonosEmpleado

  /**
   * Asocia un numero de telefono con un empleado.
   * @param empleado el empleado al cual asociar el telefono.
   * @param telefono el telefono al cual asociar el empleado.
   * @return si guardo o no el telefono.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean asociarTelefonoEmpleado(Empleado empleado, Telefono telefono) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("INSERT INTO \"Telefonos\"(" 
          + " \"Numero\", \"TipoTelefono\", \"EmpleadoAsignado\")"
          + " VALUES ( :telefono,:tipoTelefono , :empleado );")
          .bind("empleado", empleado.getId())
          .bind("tipoTelefono", telefono.getTipoTelefono().getId())
          .bind("telefono", telefono.getNumero())
          .execute();

    return (respuesta == 1) ? true : false;
  } //Fin del metodo asociarTelefonoEmpleado

  /**
  * Elimina un numero de telefono con un empleado.
  * @param empleado el empleado asociado el telefono.
  * @param telefono el telefono asociado al empleado.
  * @return si elimino o no el telefono.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  public boolean eliminarTelefonoEmpleado(Empleado empleado, Telefono telefono)
      throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("DELETE FROM \"Telefonos\" "
        + "WHERE \"Numero\" = :telefono AND \"EmpleadoAsignado\" = :empleado ;")
       .bind("empleado", empleado.getId())
       .bind("telefono", telefono.getNumero())
       .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarTelefonoEmpleado
} //Fin de la clase TelefonoDao