package com.geopractic.gps;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.gps.dao.RastreadorDao;
import com.geopractic.gps.modelo.Rastreador;

public class ControladorRastreador extends Controlador<Rastreador> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new RastreadorDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos"
        + " del Dao de rastreador.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Rastreador rastreador) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Rastreador rastreador) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo eliminarDependencias

} //Fin de la clase ControladorRastreador