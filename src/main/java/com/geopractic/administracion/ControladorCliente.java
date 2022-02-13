package com.geopractic.administracion;

import com.geopractic.administracion.dao.ClienteDao;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

/**
 * Clase controlador que maneja los accesos y modificaciones de los clientes.
 * @author Cristhian
 *
 */
public class ControladorCliente extends Controlador<Cliente> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new ClienteDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
        + " recursos del Dao de Cliente.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
          + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Cliente cliente) throws ExcepcionControlador {
    iniciarDao();
    boolean resultado = false;
    try {
      int cantidadDependencias = dao.buscarDependencias(cliente);
      resultado = (cantidadDependencias > 0) ?  true : false;
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> comprobarDependencias() - Error lanzado al buscar"
          + " las dependencias del cliente.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Cliente cliente) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Busca si existe un cliente con el mismo numero de Ruc.
   * @param cliente el cliente con el ruc a buscar.
   * @return si existe o no el cliente.
   * @throws ExcepcionControlador excepcion lanzada en la capa controlador.
   */
  public boolean existeRuc(Cliente cliente) throws ExcepcionControlador {
    boolean existe = false;
    try {
      iniciarDao();
      Cliente clienteBuscado = ((ClienteDao) dao).buscarRuc(cliente);
      if (cliente.getRuc().equals(clienteBuscado.getRuc())) {
        existe = true;
      } //Fin de if
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> existeRuc() - Error lanzado al tratar de buscar ruc.",
         excepcion,this.log);
    } //Fin de try-catch
    return existe;
  } //Fin del metodo existeRuc

} //Fin de la clase ControladorCliente