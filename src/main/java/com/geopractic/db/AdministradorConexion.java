package com.geopractic.db;

import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de administracion de conexion de base de datos.
 * @author Cristhian
 */
public class AdministradorConexion {

  private static AdministradorConexion instancia;
  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  /**
    * Constructor privado para Singleton.
    */
  private AdministradorConexion() {

  } //Fin de contructor privado

  /**
   * Metodo para la obtención de la instanca.
   * @return la instancia a utilizar.
   */
  public static AdministradorConexion obtenerInstancia() {
    if (instancia == null) {
      synchronized (AdministradorConexion.class) {
        if (instancia == null) {
          instancia = new AdministradorConexion();
        } //Fin de if interno de instancia
      } //Fin de synchronized
    } //Fin de if
    return instancia;
  } //Fin del metodo obtenerInstancia

  /**
   * Inicia los recursos utilizados para la conexion con la base de datos.
   * @param logger el logger a utilizar.
   * @param clase la clase que llama el metodo.
   * @return lel handle de conexion a la base de datos.
   * @throws ExcepcionConexion error al no tener los datos para generar la conexion.
   */
  public Handle iniciarRecursos(Logger logger, Object clase) throws ExcepcionConexion {
    return FabricaDataSource.obtenerInstancia().obtenerConexion();
  } //Fin del metodo iniciarRecursos
  
  /**
   * Cierra los recursos utilizados en la conexión de la base de datos.
   * @param controladorConexion handler jdbi a utilizar.
   * @param logger el logger a utilizar.
   * @param clase la clase que llama el metodo.
   * @throws ExcepcionConexion error al no tener los datos para generar la conexion.
   */
  public void finalizarRecursos(Handle controladorConexion,Logger logger,
       Object clase) throws ExcepcionConexion {
    
    //Comprueba y cierra el handler
    if (! controladorConexion.isClosed()) {
      logger.debug("Conexion cerrada");
      try {
        controladorConexion.getConnection().close();
      } catch (SQLException excepcion) {
        throw new ExcepcionConexion("<-- finalizarRecursos - Error en cerrando conexion",
               excepcion, this.log);
      } //Fin de try-catch
    } //Fin de if
  } //Fin del metodo finalizarRecursos

  /**
  * Desactiva el autocommit de la conexion iniciando la transaccion.
  * @param conexion el handle con la conexion desactivar el commit.
  * @throws ExcepcionConexion excepcion lanzada por la base de datos.
  */
  public void desactivarAutoCommit(Handle conexion) throws ExcepcionConexion {
    conexion.begin();
  } //Fin del metodo desactivarAutoCommit

  /**
  * Realizar el commit de la conexion.
  * @param controladorConexion handler jdbi a utilizar.
  * @throws ExcepcionConexion excepcion lanzada por la base de datos.
  */
  public void realizarCommit(Handle controladorConexion) throws ExcepcionConexion {
    controladorConexion.commit();
  } //Fin del metodo realizarCommit

  /**
   * Realizar el rollback de la conexion.
   * @param controladorConexion handler jdbi a realizar el rollback
   * @throws ExcepcionConexion excepcion lanzada por la base de datos.
   */
  public void realizarRollback(Handle controladorConexion) throws ExcepcionConexion {
    controladorConexion.rollback();
  } //Fin del metodo realizarRollback

} //Fin de la clase AdministradorConexion