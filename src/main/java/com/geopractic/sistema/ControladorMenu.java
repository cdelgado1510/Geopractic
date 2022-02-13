package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.MenuDao;
import com.geopractic.sistema.modelo.Menu;

public class ControladorMenu extends Controlador<Menu> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new MenuDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos"
        + " del Dao de Menu.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Menu menu) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias
  
  @Override
  public boolean eliminarDependencias(Menu menu) throws ExcepcionControlador {
    boolean eliminado = false;
    try {
      eliminado = ((MenuDao) dao).eliminarMenuRol(menu);
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarDependencias() - Error lanzado al tratar al"
          + " eliminar las dependencias asociadas del menu.",excepcion,this.log);
    } //Fin de try-catch
    return eliminado;
  } //Fin del metodo eliminarDependencias
  
} //Fin de la clase ControladorMenu