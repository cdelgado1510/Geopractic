package com.geopractic.bean;

import java.util.List;

import javax.faces.event.ActionEvent;

/**
 * Interface que implementa las funciones CRUD a realizar por los Bean.
 * @author Cristhian
 * @param <T> parametro a recibir.
 */
public interface IBeanCrud<T> {

  /**
   * Devuelve la entidad utilizada en el bean.
   * @return la entidad instanciada en el bean.
   */
  T getEntidad();

  /**
  * Establece la entidad utilizada en el bean.
  * @param entidad la entidad a establecer.
  */
  void setEntidad(T entidad);

  /**
  * Devuelve el listado de objetos obtenido. 
  * @return la lista de objetos dentro del sistema.
  */
  List<T> getLista();

  /**
   * Establece la lista de objetos.
   * @param lista la lista a establecer.
   */
  void setLista(List<T> lista);

  /**
  * Devuelve el listado de objetos filtados obtenido.
  * @return la lista de objetos filtrados.
  */
  List<T> getListaFiltro();

  /**
  * Establece el listado de objetos filtrados.
  * @param listaFiltro la lista de objetos filtrados
  */
  void setListaFiltro(List<T> listaFiltro);

  /**
   * Apunta la referencia de un objeto seleccionado a la variable local
   * y establece la funcion como <b>"Modificar"</b>.
   * @param objetoSeleccionado el objeto seleccionado.
   */
  void seleccionar(T objetoSeleccionado);
  
  /**
   * Lista todos los objetos del sistema.
   */
  void generarLista();

  /**
   *  Guarda un objeto seleccionado del sistema.
   *  @param actionEvent el actionEvent del boton.
   */
  void guardar(ActionEvent actionEvent);

  /**
   *  Modifica un objeto seleccionado del sistema.
   *  @param actionEvent el actionEvent del boton.
   */
  void modificar(ActionEvent actionEvent);

  /**
  *  Elimina un objeto seleccionado del sistema.
  *  @param objetoSeleccionado el objeto a eliminar.
  */
  void eliminar(T objetoSeleccionado);

} //Fin de la interface IBeanCrud