package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.TipoTelefonoDao;
import com.geopractic.sistema.modelo.TipoTelefono;

/**
 * Clase controlador que maneja los accesos y modificaciones de los tipos de telefono.
 * @author Cristhian
  */
public class ControladorTipoTelefono extends Controlador<TipoTelefono> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new TipoTelefonoDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Tipo Documento.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(TipoTelefono tipoTelefono) throws ExcepcionControlador {
    iniciarDao();
    boolean resultado = false;
    try {
      int cantidadDependencias = dao.buscarDependencias(tipoTelefono);
      resultado = (cantidadDependencias > 0) ?  true : false;
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> comprobarDependencias() - Error lanzado al buscar"
            + " las dependencias del tipo telefono.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(TipoTelefono objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

} //Fin de la clase ControladorTipoTelefono