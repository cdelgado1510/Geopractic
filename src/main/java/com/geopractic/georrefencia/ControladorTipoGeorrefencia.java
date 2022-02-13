package com.geopractic.georrefencia;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.TipoGeorreferenciaDao;
import com.geopractic.georrefencia.modelo.TipoGeorreferencia;

public class ControladorTipoGeorrefencia extends Controlador<TipoGeorreferencia> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new TipoGeorreferenciaDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos"
        + " del Dao de Tipo de Georreferencia.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(TipoGeorreferencia tipo) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(TipoGeorreferencia tipo) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo eliminarDependencias
} //Fin de la clase ControladorTipoGeorrefencia