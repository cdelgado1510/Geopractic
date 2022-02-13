package com.geopractic.propiedades;

/**
 * Obtiene y devuelve las propiedades a ser utilizada en el modulo de Rastreo.
 * @author Cristhian
 *
 */
public class PropiedadesRastreo extends Propiedades {

  private static PropiedadesRastreo instancia;

  /////////////////////////////
  //Campos de las propiedades//
  /////////////////////////////
  private String servidor;//Direccion del servidor de rastreo
  private String puerto;//Puerto utilizado por el servicio
  private String direccion;//Path de la direccion web
  private String cuenta;//Denominacion de la cuenta asignada a los rastreos
  private String usuario;//El usuario utilizado para cceder
  private String contrasenha;//Contraseña de acceso al servidor de Rastreo
  private String inicio; //Si la url es http o https

  /**
   * Obtiene y lee el archivo de propiedades dado.
   * @param nombreArchivo el nombre del archivo de propiedades
   * @throws ExcepcionPropiedad excepcion lanzada en la lectura del archivo de propiedades. 
   */
  private PropiedadesRastreo(String nombreArchivo) throws ExcepcionPropiedad {
    leerPropiedades(nombreArchivo);
    obtenerPropiedades();
  } //Fin de constructor

  /**
   * Metodo para la obtención de la instanca.
   * @return la instancia a utilizar.
   * @throws ExcepcionPropiedad excepcion lanzada en la lectura del archivo de propiedades. 
   */
  public static PropiedadesRastreo obtenerInstancia() throws ExcepcionPropiedad  {
    if (instancia == null) {
      synchronized (PropiedadesRastreo.class) {
        if (instancia == null) {
          instancia = new PropiedadesRastreo("rastreo.properties");
        } //Fin de if interno de instancia
      } //Fin de synchronized
    } //Fin de if
    return instancia;
  } //Fin del metodo obtenerInstancia

  /**
  * Extrae los valores de las propiedades y los guarda en su
  * respectiva variable.  
  */
  private void obtenerPropiedades() {
    //Obtención de los parametros definidos en el archivo
    servidor = propiedades.getProperty("servidor");
    puerto = propiedades.getProperty("puerto");
    direccion = propiedades.getProperty("direccion");
    cuenta = propiedades.getProperty("cuenta");
    usuario = propiedades.getProperty("usuario");
    contrasenha = propiedades.getProperty("contrasenha");
    inicio = propiedades.getProperty("inicio");
  } //Fin del metodo obtenerPropiedades

  /**
   * Devuelve la direccion del servidor de rastreo.
   * @return la direccion sin el path
   */
  public String getServidor() {
    return servidor;
  } //Fin del metodo getServidor

  /**
   * Devuelve el puerto de escucha del servicio.
   * @return el numero de puerto a utilizar
   */
  public String getPuerto() {
    return puerto;
  } //Fin del metodo getPuerto

  /**
   * Devuelve la direccion del path dentro del servidor.
   * @return el path de la URL
   */
  public String getDireccion() {
    return direccion;
  } //Fin del metodo getDireccion

  /**
   * Devuelve la cuenta de la empresa a utilizar.
   * @return el nombre de la cuenta de los dispositivos.
   */
  public String getCuenta() {
    return cuenta;
  } //Fin del metodo getCuenta

  /**
   * Devuelve el usuario de acceso a la cuenta.
   * @return el usuario a utilizar.
   */
  public String getUsuario() {
    return usuario;
  } //Fin del metodo getUsuario

  /**
   * Devuelve la contraseña del usuario a utilizar.
   * @return la contraseña del usuario a utilizar
   */
  public String getContrasenha() {
    return contrasenha;
  } //Fin del metodo getContrasenha

  /**
   * Devuelve el inicio de la URL.
   * @return si es http o https:
   */
  public String getInicio() {
    return inicio;
  } //Fin del metodo getInicio

} //Fin de la clase PropiedadesRastreo