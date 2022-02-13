package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.RolDao;
import com.geopractic.sistema.modelo.Menu;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Ventana;

import java.util.List;

public class ControladorRol extends Controlador<Rol> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new RolDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos del"
           + " Dao de Rol.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
           + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Rol rol) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Rol rol) throws ExcepcionControlador {
    boolean eliminado = false;
    try {
      eliminado = ((RolDao) dao).eliminarMenuRol(rol);
      eliminado = ((RolDao) dao).eliminarVentanaRol(rol);
      eliminado = ((RolDao) dao).eliminarRolUsuario(rol);
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> comprobarDependencias() - Error lanzado al tratar"
          + " al eliminar las dependencias asociadas al rol.",excepcion,this.log);
    } //Fin de try-catch
    return eliminado;
  } //Fin del metodo eliminarDependencias

  /**
   * Asigna un listado de menus un rol seleccionado.
   * @param rol el rol al cual asignar los menus.
   * @param menusAsignados el listado de menus a asignar.
   * @return si asigno o no los menus.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean agregarRolMenu(Rol rol,List<Menu> menusAsignados) throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      //Agrega todos los menus seleccionados
      for (Menu menu : menusAsignados) {
        resultado = ((RolDao) this.dao).guardarMenuRol(rol, menu);
      } //Fin de for-each
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> agregarRolMenu() - Error en Dao lanzado al tratar"
          + " al asignar menus a un rol ",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> agregarRolMenu() - Error de conexion lanzado al tratar"
          + " al asignar menus a un rol ",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo agregarRolMenu
  
  /**
   * Elimina un listado de asignaciones de menus de un rol seleccionado.
   * @param rol el rol al cual pertenecen los menus.
   * @param menusAsignados el listado de menus a eliminar.
   * @return si elimino o no los menus.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean eliminarRolMenu(Rol rol,List<Menu> menusAsignados) throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      //Elimina todos los menus seleccionados
      for (Menu menu : menusAsignados) {
        resultado = ((RolDao) this.dao).eliminarMenuRol(rol, menu);
      } //Fin de for-each
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarRolMenu() - Error en Dao lanzado al tratar"
          + " al eliminar menus de un rol",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> eliminarRolMenu() - Error de conexion lanzado al"
          + " tratar de eliminar menus de un rol",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo eliminarRolMenu
  
  /**
   * Asigna un listado de ventanas un rol seleccionado.
   * @param rol el rol al cual asignar las ventanas.
   * @param ventanasAsignadas el listado de ventanas a asignar.
   * @return si asigno o no las ventanas.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean agregarRolVentana(Rol rol,List<Ventana> ventanasAsignadas)
      throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      //Agrega todas las ventanas seleccionadas
      for (Ventana ventana : ventanasAsignadas) {
        resultado = ((RolDao) this.dao).guardarVentanaRol(rol, ventana);
      } //Fin de for-each
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> agregarRolVentana() - Error en Dao lanzado al"
          + " tratar al asignar ventanas a un rol",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> agregarRolVentana() - Error de conexion lanzado al"
          + " tratar al asignar  ventanas a un rol",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo agregarRolVentana

  /**
   * Elimina un listado asignaciones de ventanas de un rol seleccionado.
   * @param rol el rol al cual pertenecen las ventanas.
   * @param ventanasAsignadas el listado de ventanas a eliminar.
   * @return si elimino o no las ventanas.
   * @throws ExcepcionControlador excepcion lanzada desde el controlador.
   */
  public boolean eliminarRolVentana(Rol rol,List<Ventana> ventanasAsignadas)
      throws ExcepcionControlador {
    boolean resultado = false;
    try {
      iniciarDao();
      //Elimina todas las ventanas seleccionados
      for (Ventana ventana : ventanasAsignadas) {
        resultado = ((RolDao) this.dao).eliminarVentanaRol(rol, ventana);
      } //Fin de for-each
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarRolVentana() - Error en Dao lanzado al tratar"
          + " al eliminar ventanas de un rol",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> eliminarRolVentana() - Error de conexion lanzado al"
          + " tratar de eliminar ventanas de un rol",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo eliminarRolVentana

} //Fin de la clase ControladorRol