package com.geopractic.db;

import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase abstracta que implementa las operaciones realizadas
 * por todos los Dao para la realizacion de consultas.
 * @author Cristhian
 * @param <T> parametro de objeto ingresado
 *
 */
public abstract class Dao<T> implements IDao<T> {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  protected Handle conexion = null;
  protected RowMapper<T> mapeoBean;
  protected QueryMaker queryMaker;

  /*
   * String con sentencias SQL comunes a realizar.
   */
  protected String queryBuscar;
  protected String queryBuscarId;
  protected String queryListarTodos;
  protected String queryGuardar;
  protected String queryModificar;
  protected String queryEliminar;
  
  @Override
  public void setConexion(Handle conexion) {
    this.conexion = conexion;
    iniciarMapeos();
  } //Fin del metodo pasarConexion

  @Override
  public Handle getConexion() {
    return conexion;
  } //Fin del metodo getConexion

  @Override
  public void iniciarRecursos() throws ExcepcionDao {
    try {
      //Verifica si la conexion del dao actual esta creada
      if (conexion == null) {
        setConexion(AdministradorConexion.obtenerInstancia().iniciarRecursos(log, this));
        getConexion().setSqlLogger(new SqlLoggerImp());
      } //Fin de if
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionDao("--> iniciarRecursos() - Error al obtener la conexion del"
         + " administrador de conexiones",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarRecursos

  @Override
  public void finalizarRecursos() throws ExcepcionDao {
    try {
      AdministradorConexion.obtenerInstancia().finalizarRecursos(conexion, log, this);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionDao("--> finalizarRecursos() - Error al cerrar la conexion y recursos"
          + " de la base de datos.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin  del metodo finalizarRecursos

  @Override
  public int buscarDependencias(T objeto) throws ExcepcionDao {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo buscarDependencias

  @Override
  public void iniciarMapeos() {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo iniciarMapeos

  @Override
  public T buscar(T objeto) throws ExcepcionDao {
    T resultado = null;
    try {
      Optional<T> respuesta = null; 
      respuesta = conexion.createQuery(queryBuscar)
          .bindBean(objeto)
          .map(mapeoBean)
          .findFirst();
      if (respuesta.isPresent()) {
        resultado = respuesta.get();
      } //Fin de if
    } catch (IllegalStateException  excepcion) {
      throw new ExcepcionDao("Objeto " + objeto.toString()
         + " no encontrado o existen multiples", excepcion, this.log);
    } //Fin de try-catch

    return resultado;
  } //Fin del metodo buscar

  @Override
  public T buscarId(long id) throws ExcepcionDao {
    T resultado = null;
    try {
      resultado = conexion.createQuery(queryBuscarId)
        .bind("id", id)
        .map(mapeoBean)
        .findOnly();
    } catch (IllegalStateException  excepcion) {
      throw new ExcepcionDao("Id de objeto mapeado "
           + mapeoBean.getClass().getName() + " no encontrado",
           excepcion, this.log);
    } //Fin de try-catch

    return resultado;
  } //Fin del metodo buscarId
  
  @Override
  public List<T> listarTodos() throws ExcepcionDao {
    List<T> lista = new ArrayList<T>();
    lista = conexion.createQuery(queryListarTodos)
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(T objeto) throws ExcepcionDao {
    int respuesta = 0;
    T nuevoObjeto = null;
    nuevoObjeto = conexion.createUpdate(queryGuardar)
      .bindBean(objeto)
      .executeAndReturnGeneratedKeys()
      .map(mapeoBean)
      .findOnly();
    
    //Verifica si se realizo el guardado
    if (nuevoObjeto != null) {
      obtenerClavePrimaria(objeto, nuevoObjeto);
      respuesta = 1;
    } //Fin de
    return (respuesta == 1) ? true : false;
  } //Fin del metodo guardar

  @Override
  public boolean modificar(T objeto) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate(queryModificar)
       .bindBean(objeto)
       .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo modificar 

  @Override
  public boolean eliminar(T objeto) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate(queryEliminar)
      .bindBean(objeto)
      .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminar

} //Fin de la clase Dao