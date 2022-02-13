package com.geopractic.controladores;

import java.util.List;

/**
 * Interfaz para modelo de implementacion de operaciones de modificacion
 * de controladores.
 * @author Cristhian
 *
 * @param <T> el tipo de objeto a utilizar.
 */
public interface IControladorCrud<T> {

  T buscar(T objeto) throws ExcepcionControlador;

  T buscarId(long id) throws ExcepcionControlador;

  List<T> listarTodos() throws ExcepcionControlador;
  
  boolean guardar(T objeto) throws ExcepcionControlador;
  
  boolean modificar(T objeto) throws ExcepcionControlador;
  
  boolean eliminar(T objeto) throws ExcepcionControlador;
  
} //Fin de la interface IControladorCrud