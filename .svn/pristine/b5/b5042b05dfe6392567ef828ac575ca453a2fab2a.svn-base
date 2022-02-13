package com.geopractic.db;

import org.jdbi.v3.core.Handle;

/**
 * Interfaz para modelo de implementacion de Data Access Objects.
 * @author Cristhian
 * @param <T> parametro a recibir.
 */
public interface IDao<T> extends IDaoCrud<T> {

  /**
  * Inicia los recursos utilizados para la conexion con la base de datos.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  void iniciarRecursos() throws ExcepcionDao;

  /**
  * Cierra los recursos utilizados en la conexión de la base de datos.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  void finalizarRecursos() throws ExcepcionDao;

  /**
   * Inicia todas las clases de mapeos utilizadas dentro del Dao.
   */
  void iniciarMapeos();
  
  /**
   * Busca dependencias dentro en las cuales se encuentra el objeto.
   * @param objeto el objeto a buscar.
   * @return la cantidad de dependencias encontradas.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  int buscarDependencias(T objeto) throws ExcepcionDao;

  /**
   * Pasa la conexion generada en otro objeto.
   * @param conexion el objeto Handle a utilizar.
   */
  void setConexion(Handle conexion);
 
  /**
   * Devuelve la conexion utilizada por la clase.
   * @return la conexion utilizada.
   */
  Handle getConexion();

  /**
   * Establece la clave primaria de los nuevos objetos insertados.
   * @param objeto el objeto a establecer la clave primaria.
   * @param objetoNuevo el objeto con la clave primaria generada.
   */
  void obtenerClavePrimaria(T objeto, T objetoNuevo);
} //Fin de interfaz IDao