package com.geopractic.administracion;

import com.geopractic.administracion.dao.EmpleadoDao;
import com.geopractic.administracion.modelo.Cargo;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

import java.util.List;

/**
 * Clase controlador que maneja los accesos y modificaciones de los empleados.
 * @author Cristhian
  */
public class ControladorEmpleado extends Controlador<Empleado> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new EmpleadoDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Empleado.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Empleado empleado) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Empleado empleado) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Lista todos los empelados con el cargo especificado.
   * @param cargo el cargo a buscar.
   * @return el listado completo de objetos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Empleado> listarPorCargo(Cargo cargo) throws ExcepcionControlador {
    iniciarDao();
    List<Empleado> lista = null;
    try {
      lista = ((EmpleadoDao) dao).listarPorCargo(cargo);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarPorCargo() - Error lanzado al tratar de "
        + "listar todos los empleados por cargo. ",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarPorCargo() - Error lanzada al tratar de "
        + "realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarPorCargo

  /**
   * Lista todos los empelados con el cargo especificado.
   * @param cargos el cargo a buscar.
   * @return el listado completo de objetos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Empleado> listarPorCargos(String[] cargos) throws ExcepcionControlador {
    iniciarDao();
    List<Empleado> lista = null;
    try {
      lista = ((EmpleadoDao) dao).listarPorCargos(cargos);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarPorCargos() - Error lanzado al tratar de "
        + "listar todos los empleados por cargos. ",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarPorCargos() - Error lanzada al tratar de "
        + "realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarTodos

} //Fin de la clase ControladorEmpleado