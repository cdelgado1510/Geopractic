package com.geopractic.db;

import java.util.List;

/**
 * Interfaz para modelo de implementacion de operaciones Crud.
 * @author Cristhian
 * @param <T> objeto a establecer
 */
public interface IDaoCrud<T> {

  /**
  * Busca un objecto definido.
  * @param id el identificador del objeto a buscar.
  * @return el objecto encontrado.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  T buscarId(long id) throws ExcepcionDao;

  /**
  * Busca un objecto definido.
  * @param objeto el objeto a buscar.
  * @return el objecto encontrado.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  T buscar(T objeto) throws ExcepcionDao;

  /**
  * Devuelve el listado de todos los objetos.
  * @return un listado contodos los objetos.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  List<T> listarTodos() throws ExcepcionDao;

  /**
  * Guarda un objeto establecido dentro de la base de datos.
  * @param objeto el objeto a guardar en la base de datos.
  * @return si guardo o no el objeto.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  boolean guardar(T objeto) throws ExcepcionDao;

  /**
  * Modifica un objeto dentro de la base de datos.
  * @param objeto el objeto a modificar en la base de datos.
  * @return si modifico o no el objeto.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  boolean modificar(T objeto) throws ExcepcionDao;

  /**
  * Elimina un objeto de la base de datos.
  * @param objeto el objeto a eliminar en la base de datos.
  * @return si elimino o no el objeto.
  * @throws ExcepcionDao excepcion lanzada en la capa dao.
  */
  boolean eliminar(T objeto) throws ExcepcionDao;
  
} //Fin de la interface IDaoCrud