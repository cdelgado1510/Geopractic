package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.VentanaDao;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Ventana;

/**
 * Clase controladora para la ventana.
 * @author Cristhian
 *
 */
public class ControladorVentana extends Controlador<Ventana> {

  /**
  * Inicia el dao y los recursos del mismo.
  * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
  */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new VentanaDao();
    } //Fin de if
    
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al"
         + " iniciar los recursos del Dao de ventana.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Ventana ventana) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias
  
  @Override
  public boolean eliminarDependencias(Ventana ventana) throws ExcepcionControlador {
    boolean eliminado = false;
    try {
      eliminado = ((VentanaDao) dao).eliminarVentanaRol(ventana);
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarDependencias() - Error lanzado al tratar"
            + " al eliminar las dependencias asociadas a la ventana.",excepcion,this.log);
    } //Fin de try-catch
    return eliminado;
  } //Fin del metodo eliminarDependencias

  /**
   * Modifica los permisos de una ventana dentro de un rol.
   * @param ventana la ventana a modificar.
   * @param rol el rol asociado a la ventana.
   * @return true o false si fue modificado la ventana.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public boolean modificarPermisosVentana(Ventana ventana,Rol rol) throws ExcepcionControlador {
    boolean modificado = false;
    try {
      iniciarDao();
      modificado = ((VentanaDao) dao).modificarRolVentana(ventana, rol);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> modificarPermisosVentana() - Error lanzado al"
        + " tratar de modificar el objeto.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> modificarPermisosVentana() - Error lanzada al"
        + " tratar de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return modificado;
  } //Fin del metodo modificarPermisosVentana

} //Fin de la clase ControladorVentana