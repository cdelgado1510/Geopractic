package com.geopractic.administracion;

import com.geopractic.administracion.dao.CargoDao;
import com.geopractic.administracion.modelo.Cargo;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

/**
 * Clase controlador que maneja los accesos y modificaciones de los cargos.
 * @author Cristhian
  */
public class ControladorCargo extends Controlador<Cargo> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new CargoDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Cargo.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Cargo cargo) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Cargo argo) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

} //Fin de clase ControladorCargo