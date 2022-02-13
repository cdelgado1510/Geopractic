package com.geopractic.propiedades;

/**
 * Clase singleton para la obtencion de los datos de conexión.
 * la base de datos postgresSQL
 * @author Cristhian
 *
 */
public class PropiedadesPostgres extends Propiedades {

  private static PropiedadesPostgres instancia;

  /////////////////////////////
  //Campos de las propiedades//
  /////////////////////////////
  private String servidor;//Servidor al cual conectarse
  private String puerto;//Nro de puerto en el que corre el servidor
  private String baseDeDatos;//Nombre de la base datos a conectarse
  private String userAdmin;//Usuario administrador de la base de datos
  private String senhaAdmin;//Contraseña de administracion de base de datos
  private String ssl;//Contraseña de administracion de base de datos

  /**
   * Constructor privado de la clase.
   * @param nombreArchivo el archivo de propiedades seleccionado
   * @throws ExcepcionPropiedad excepcion lanzada en la lectura del archivo de propiedades. 
   */
  private PropiedadesPostgres(String nombreArchivo) throws ExcepcionPropiedad  {
    log.debug("PropiedadesPostgres() - Generando instancia de propiedades Postgres");
    leerPropiedades(nombreArchivo);
    obtenerPropiedades();
  } //Fin de constructor privado

  /**
   * Metodo para la obtención de la instanca.
   * @return la instancia a utilizar.
   * @throws ExcepcionPropiedad excepcion lanzada en la lectura del archivo de propiedades. 
   */
  public static PropiedadesPostgres obtenerInstancia() throws ExcepcionPropiedad  {
    if (instancia == null) {
      synchronized (PropiedadesPostgres.class) {
        if (instancia == null) {
          instancia = new PropiedadesPostgres("postgres.properties");
        } //Fin de if interno de instancia
      } //Fin de synchronized
    } //Fin de if
    return instancia;
  } //Fin del metodo obtenerInstancia

  /**
   * Extrae los valores del archivo de propiedades y los guarda en su
   * respectiva variable.  
   */
  private void obtenerPropiedades() {
    log.debug("obtenerPropiedades() - Leyendo propiedades de acceso a postgres.");
    servidor = propiedades.getProperty("servidor");
    puerto = propiedades.getProperty("puerto");
    baseDeDatos = propiedades.getProperty("basededatos");
    userAdmin = propiedades.getProperty("userAdmin");
    senhaAdmin = propiedades.getProperty("senhaAdmin");
    ssl = propiedades.getProperty("ssl");
  } //Fin del metodo obtenerPropiedades

  /**
   * Devuelve el servidor de conexión de la base de datos.
   * @return el servidor al cual se conectara el sistema.
   */
  public String getServidor() {
    return servidor;
  } //Fin de metodo getServidor

  /**
  * Devuelve el puerto de conexión de la base de datos.
  * @return el puerto de conexion a la base de datos.
  */
  public String getPuerto() {
    return puerto;
  } //Fin de metodo getPuerto

  /**
   * Devuelve el nombre de la base de datos.
   * @return el nombre de la base de datos.
   */
  public String getBaseDeDatos() {
    return baseDeDatos;
  } //Fin de metodo getBaseDeDatos

  /**
   * Devuelve el usuario de conexión de la base de datos.
   * @return el usuario de conexión.
   */
  public String getUserAdmin() {
    return userAdmin;
  } //Fin de metodo getUserAdmin

  /**
   * Devuelve la contrasenha de conexión de la base de datos.
   * @return la contrasenha de conexión.
   */
  public String getSenhaAdmin() {
    return senhaAdmin;
  } //Fin de metodo getSenhaAdmin

  /**
   * Devuelve si la conexión de la base de datos usa SSL.
   * @return the ssl
   */
  public String getSsl() {
    return ssl;
  } //Fin de metodo getSsl

} //Fin de la clase PropiedadesPostgres