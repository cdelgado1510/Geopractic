package com.geopractic.georrefencia;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.MapaDao;
import com.geopractic.georrefencia.modelo.Mapa;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;

import java.util.List;

/**
 * Clase controladora para los mapas del sistema.
 * @author Cristhian
 *
 */
public class ControladorMapa extends Controlador<Mapa> {

  /**
  * Inicia el dao y los recursos del mismo.
  * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
  */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new MapaDao();
    } //Fin de if
    
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al"
         + " iniciar los recursos del Dao de Mapa.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Mapa objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Mapa objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Guarda un listado de mapas.
   * @param listaMapas el lsitado a guardar.
   * @param panel el panel al cual estan asignados los mapas.
   * @return true o false si fue guardado el listado.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public boolean guardarMapasPanel(List<Mapa> listaMapas,PanelVisualizacion panel)
        throws ExcepcionControlador {
    boolean guardado = false;
    try {
      iniciarDao();
      //Agrega todos los mapas seleccionados
      for (Mapa mapa : listaMapas) {
        mapa.setPanel(panel.getId());
        guardado = this.dao.guardar(mapa);
      } //Fin de for-each
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> guardarMapas()- Error lanzado al tratar de "
        + "guardar el listado de mapas.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> guardarMapas() - Error lanzada al tratar de"
        + " realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return guardado;
  } //Fin del metodo guardarMapasPanel

} //Fin de la clase ControladorMapa