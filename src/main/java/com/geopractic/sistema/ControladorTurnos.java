package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.TurnosDao;
import com.geopractic.sistema.modelo.Turnos;

/**
 * Clase controlador que maneja los accesos y modificaciones de los turnos.
 * @author Cristhian
  */
public class ControladorTurnos extends Controlador<Turnos> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new TurnosDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Turnos.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Turnos tipoTelefono) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Turnos objeto) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

} //Fin de la clase ControladorTurnos