package com.geopractic.controladores;

import com.geopractic.db.AdministradorConexion;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.db.IDao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase controlador que maneja los accesos y modificaciones de los usuarios.
 * @author Cristhian
 * @param <T> objeto a utilizar en los Data Access Object.
 */
public abstract class Controlador<T> implements IControlador<T> {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  protected AdministradorConexion administradorConexion = AdministradorConexion.obtenerInstancia();
  protected IDao<T> dao;

  /**
  * Realiza el rollback de la conexion del dao utilizado.
  * @throws ExcepcionControlador error lanzado desde la conexion. 
  */
  @Override
  public void realizarRollback() throws ExcepcionControlador {
    if (dao != null) {
      try {
        administradorConexion.realizarRollback(dao.getConexion());
      } catch (ExcepcionConexion excepcion) {
        throw new ExcepcionControlador("<-- realizarRollback() - Error lanzado al realizar "
          + "el rollback de la conexion.",excepcion,this.log);
      } //Fin de try-catch
    } //Fin de if
  } //Fin del metodo realizarRollback

  /**
   * Cierra los recursos de la instancia dao utilizada.
   * @throws ExcepcionControlador error lanzado al fallar el cerrado de recursos.
   */
  @Override
  public void cerrarRecursosControlador() throws ExcepcionControlador {
    if (dao != null) {
      try {
        dao.finalizarRecursos();
      } catch (ExcepcionDao excepcion) {
        throw new ExcepcionControlador("<-- cerrarRecursosControlador() - Error lanzado al "
            + "realizar el rollback de la conexion.",excepcion,this.log);
      } //Fin de try-catch
    } //Fin de if
  } //Fin del metodo cerrarRecursosControlador

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionConexion error lanzado desde la conexion.
   */
  protected void iniciarDao() throws ExcepcionControlador {

  } //Fin del metodo iniciarDao
  
  /**
   * Busca un objeto ingresado dentro de la base de datos.
   * @param objeto el objeto a buscar.
   * @return el usuario encontrado o Null.
   * @throws ExcepcionControlador error al no poder buscar el objeto.
   */
  @Override
  public T buscar(T objeto) throws ExcepcionControlador {
    iniciarDao();
    T objetoValido;
    try {
      objetoValido = dao.buscar(objeto);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> buscar() - Error lanzado al buscar el objeto.",
         excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> buscar() - Error lanzada al tratar de"
         + " realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return objetoValido;
  } //Fin del metodo buscar

  /**
   * Busca un objeto ingresado dentro de la base de datos.
   * @param id el id para buscar el objeto.
   * @return el objeto encontrado o Null.
   * @throws ExcepcionControlador error al no poder buscar el objeto.
   */
  @Override
  public T buscarId(long id) throws ExcepcionControlador {
    iniciarDao();
    T objetoValido;
    try {
      objetoValido = dao.buscarId(id);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> buscarId() - Error lanzado al buscar el objeto.",
         excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> buscarId() - Error lanzada al tratar de"
         + " realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return objetoValido;
  } //Fin del metodo buscarId

  /**
   * Lista todos los objetos del sistema.
   * @return el listado completo de objetos.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  @Override
  public List<T> listarTodos() throws ExcepcionControlador {
    iniciarDao();
    List<T> lista = null;
    try {
      lista = dao.listarTodos();
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> listarTodos() - Error lanzado al tratar de "
        + "listar todos los objetos.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> listarTodos() - Error lanzada al tratar de "
        + "realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return lista;
  } //Fin del metodo listarTodos

  /**
   * Guarda un objeto dentro de la base de datos.
   * @param objeto el objeto a guardar.
   * @return true o false si fue guardado el usuario.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  @Override
  public boolean guardar(T objeto) throws ExcepcionControlador {
    boolean guardado = false;
    iniciarDao();
    try {
      guardado = dao.guardar(objeto);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> guardar()- Error lanzado al tratar de "
        + "guardar el objeto.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> guardar() - Error lanzada al tratar de"
        + " realizar el commit.",excepcion,this.log);
    } //Fin de try-catch
    return guardado;
  } //Fin del metodo guardar

  /**
   * Modifica un objeto dentro de la base de datos.
   * @param objeto el objeto a modificar.
   * @return true o false si fue modificado el usuario.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  @Override
  public boolean modificar(T objeto) throws ExcepcionControlador {
    boolean modificado = false;
    try {
      iniciarDao();
      modificado = dao.modificar(objeto);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> modificar () - Error lanzado al tratar de modificar"
        + " el objeto.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> modificar () - Error lanzada al tratar de realizar el"
        + " commit.",excepcion,this.log);
    } //Fin de try-catch
    return modificado;
  } //Fin del metodo modificar
  
  /**
   * Elimina un objeto dentro de la base de datos.
   * @param objeto el objeto a eliminar.
   * @return true o false si fue eliminado el usuario.
   * @throws ExcepcionControlador error lanzado desde la conexion.
   */
  @Override
  public boolean eliminar(T objeto) throws ExcepcionControlador {
    boolean eliminado = false;
    try {
      iniciarDao();
      eliminarDependencias(objeto);
      eliminado = dao.eliminar(objeto);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> eliminar() - Error lanzado al tratar de eliminar"
          + " el objeto.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> eliminar() - Error lanzada al tratar de realizar"
          + " el commit.",excepcion,this.log);
    } catch (ExcepcionControlador excepcion) {
      throw new ExcepcionControlador("--> eliminar() - Error lanzado al tratar de eliminar"
          + " el objeto.",excepcion,this.log);
    } //Fin de try-catch
    return eliminado;
  } //Fin del metodo eliminar

  @Override
  public boolean existeObjeto(T objeto) throws ExcepcionControlador {
    boolean existe = false;
    try {
      iniciarDao();
      T objetoBuscado = dao.buscar(objeto);
      if (objeto.equals(objetoBuscado)) {
        existe = true;
      } //Fin de if
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> existeObjeto() - Error lanzado al tratar de"
         + " buscar el objeto.",excepcion,this.log);
    } //Fin de try-catch
    return existe;
  } //Fin del metodo existeObjeto
  
} //Fin de la clase controlador