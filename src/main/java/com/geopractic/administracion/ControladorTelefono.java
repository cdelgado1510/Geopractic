package com.geopractic.administracion;

import com.geopractic.administracion.dao.TelefonoDao;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.administracion.modelo.Telefono;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

import java.util.List;

/**
 * Clase controlador que maneja los accesos y modificaciones de los telefonos.
 * @author Cristhian
  */
public class ControladorTelefono extends Controlador<Telefono> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new TelefonoDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar"
         + " los recursos del Dao de Tipo Documento.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
         + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Telefono objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Telefono objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /*
   * Funciones de telefonos relacionados con clientes. 
   */
  
  /**
   * Lista todos los usuarios del sistema.
   * @param cliente el cliente el cual buscar
   * @return el listado completo de telefonos
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Telefono> listarTelefonosCliente(Cliente cliente) throws ExcepcionControlador {
    iniciarDao();
    List<Telefono> lista = null;
    try {
      lista = ((TelefonoDao) this.dao).listarTelefonosCliente(cliente);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarTelefonosCliente() - Error de dao lanzado al "
        + "tratar de listar todos los telefonos de cliente.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarTelefonosCliente() - Error de controlador "
        + "lanzada al tratar de listar todos los telefonos del cliente.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarTelefonosCliente
  
  /**
   * Asigna un telefono a un cliente seleccionado.
   * @param cliente el cliente al cual asociar el telefono.
   * @param telefono el telefono al cual asocial el cliente.
   * @return si asigno o no las ventanas.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean asociarTelefonoCliente(Cliente cliente, Telefono telefono)
      throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      resultado = ((TelefonoDao) this.dao).asociarTelefonoCliente(cliente, telefono);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> asociarTelefonoCliente() - Error en Dao lanzado al"
          + "tratar al asignar el telefono al cliente.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> asociarTelefonoCliente() - Error de conexion"
          + " lanzado al asignar el telefono al cliente.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo asociarTelefonoCliente

  /**
   * Elimina un numero de telefono de un cliente seleccionado.
   * @param cliente el cliente asociado el telefono.
   * @param telefono el telefono asociado al cliente.
   * @return si elimino o no las ventanas.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean eliminarTelefonoCliente(Cliente cliente, Telefono telefono)
      throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      resultado = ((TelefonoDao) this.dao).eliminarTelefonoCliente(cliente, telefono);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarTelefonoCliente() - Error en Dao lanzado al"
         + " tratar al eliminar ventanas de un rol",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> eliminarTelefonoCliente() - Error de conexion lanzado"
         + " al tratar de eliminar ventanas de un rol",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo eliminarTelefonoCliente

  /*
   * Funciones de telefonos relacionados con empleados. 
   */
  
  /**
   * Lista todos los usuarios del sistema.
   * @param empleado el empleado al cual buscar.
   * @return el listado completo de telefonos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Telefono> listarTelefonosEmpleado(Empleado empleado) throws ExcepcionControlador {
    iniciarDao();
    List<Telefono> lista = null;
    try {
      lista = ((TelefonoDao) this.dao).listarTelefonosEmpleado(empleado);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarTelefonosEmpleado() - Error de Dao lanzado"
        + " al tratar de listar todos los telefonos del empleado.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarTelefonosEmpleado() - Error de Controlador"
        + " lanzado al tratar de al tratar de listar todos los telefonos del empleado.",
        excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarTelefonosEmpleado
  
  /**
   * Asigna un telefono a un empleado seleccionado.
   * @param empleado el empleado al cual asociar el telefono.
   * @param telefono el telefono al cual asociar el empleado.
   * @return si asigno o no las ventanas.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean asociarTelefonoEmpleado(Empleado empleado, Telefono telefono)
      throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      resultado = ((TelefonoDao) this.dao).asociarTelefonoEmpleado(empleado, telefono);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> asociarTelefonoEmpleado() - Error en Dao lanzado al"
          + "tratar al asignar el telefono al empleado.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> asociarTelefonoEmpleado() - Error de conexion"
          + " lanzado al tratar de asignar el telefono al empleado.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo asociarTelefonoEmpleado

  /**
   * Elimina un numero de telefono de un cliente seleccionado.
   * @param empleado el empleado asociado el telefono.
   * @param telefono el telefono asociado al empleado.
   * @return si elimino o no las ventanas.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean eliminarTelefonoEmpleado(Empleado empleado, Telefono telefono)
      throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      resultado = ((TelefonoDao) this.dao).eliminarTelefonoEmpleado(empleado, telefono);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarTelefonoEmpleado() - Error en Dao lanzado al"
         + " tratar al eliminar el telefono de un empleado",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> eliminarTelefonoEmpleado() - Error de conexion lanzado"
         + " al tratar de eliminar el telefono de un empleado",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo eliminarTelefonoEmpleado

} //Fin de la clase ControladorTelefono