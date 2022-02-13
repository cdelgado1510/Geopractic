package com.geopractic.georrefencia;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.MapaDao;
import com.geopractic.georrefencia.dao.PanelVisualizacionDao;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;

/**
 * Clase controladora para los paneles de visualizacion del sistema.
 * @author Cristhian
 *
 */
public class ControladorPanelVisualizacion extends Controlador<PanelVisualizacion> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new PanelVisualizacionDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos"
        + " del Dao de PanelVisualizacion.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(PanelVisualizacion objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  @Override
  public boolean eliminarDependencias(PanelVisualizacion panel) throws ExcepcionControlador {
    boolean eliminado = false;
    try {
      iniciarDao();
      MapaDao mapaDao = new MapaDao();
      mapaDao.setConexion(dao.getConexion());
      eliminado = mapaDao.eliminarMapasPanel(panel);
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminarDependencias() - Error lanzado al tratar "
          + "de eliminar mapas del panel. ",excepcion,this.log);
    } catch (ExcepcionControlador excepcion) {
      throw new ExcepcionControlador("--> eliminarDependencias() - Error lanzado al tratar "
          + "de eliminar mapas del panel. ",excepcion,this.log);
    } //Fin de try-catch
    return eliminado;
  } //Fin del metodo eliminarDependencias

} //Fin de la clase ControladorPanelVisualizacion