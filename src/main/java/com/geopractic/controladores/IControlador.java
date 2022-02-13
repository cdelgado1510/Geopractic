package com.geopractic.controladores;

/**
 * Interfaz para modelo de implementacion de funciones
 * de controladores.
 * @author Cristhian
 * @param <T> parametro a recibir.
 */
public interface IControlador<T> extends IControladorCrud<T> {

  /**
  * Realiza el rollback de la conexion del dao utilizado.
  * @throws ExcepcionControlador error lanzado desde el controlador.
  */
  public void realizarRollback() throws ExcepcionControlador;

  /**
  * Cierra los recursos de la isntancia dao utilizada.
  * @throws ExcepcionControlador error lanzado desde el controlador.
  */
  public void cerrarRecursosControlador() throws ExcepcionControlador;

  /**
   * Verifica si existe un objeto igual ya cargado en la base de datos.
   * @param objeto el objeto a buscar.
   * @return si o no existe
   * @throws ExcepcionControlador error lanzado desde el controlador.
   */
  public boolean existeObjeto(T objeto) throws ExcepcionControlador;
  
  /**
  * Comprueba que el objeto este asociado a otros dentro de la base de datos.
  * @param objeto el objeto a comprobar en la base de datos.
  * @return si existe o no dependencias del objeto.
  * @throws ExcepcionControlador excepcion lanzada en la capa controlador.
  */
  boolean comprobarDependencias(T objeto) throws ExcepcionControlador;
  
  /**
  * Elimina un objeto de las tablas relacionadas.
  * @param objeto el objeto a eliminar en la base de datos.
  * @return si elimino o no el objeto.
  * @throws ExcepcionControlador excepcion lanzada en la capa controlador.
  */
  boolean eliminarDependencias(T objeto) throws ExcepcionControlador;
} //Fin de la interfaz controlador