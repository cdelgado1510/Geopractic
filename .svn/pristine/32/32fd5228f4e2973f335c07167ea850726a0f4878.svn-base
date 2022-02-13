package com.geopractic.georrefencia;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.dao.GeorreferenciaDao;
import com.geopractic.georrefencia.modelo.Georreferencia;

import java.util.List;

/**
 * Clase controladora para las georreferencias del sistema.
 * @author Cristhian
 *
 */
public class ControladorGeorreferencia extends Controlador<Georreferencia> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new GeorreferenciaDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos"
        + " del Dao de Georreferencia.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar el autocommit"
            + " de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Georreferencia georreferencia) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Georreferencia georreferencia) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Lista todas las georreferencias del cliente.
   * @param cliente el identificador del cliente a buscar.
   * @return el listado de georreferencias del cliente.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  public List<Georreferencia> listarGeorreferenciaCliente(long cliente)
         throws ExcepcionControlador {
    iniciarDao();
    List<Georreferencia> lista = null;
    try {
      lista = ((GeorreferenciaDao) dao).listarGeorreferenciasClientes(cliente);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarGeorreferenciaCliente() - Error lanzado al tratar"
        + " de listar las georreferencias del cliente.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarGeorreferenciaCliente() - Error lanzada al tratar"
        + " de realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarGeorreferenciaCliente

} //Fin de la clase ControladorGeorreferencia