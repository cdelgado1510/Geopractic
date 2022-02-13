package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.UsuarioDao;
import com.geopractic.sistema.modelo.Usuario;
import com.geopractic.util.Encriptador;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

/**
 * Clase controlador que maneja los accesos y modificaciones de los usuarios.
 * @author Cristhian
 */
public class ControladorUsuario extends Controlador<Usuario> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws SQLException error lanzado desde la base de datos.
   * @throws Exception error general lanzado.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new UsuarioDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar"
          + " los recursos del Dao de Menu.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
          + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  /**
   * Encripta la contrasenha ingresada.
   * @param contrasenha la contrasenha a encriptar.
   * @return la contrasenha encriptada
   * @throws NoSuchAlgorithmException error lanzado al no encontrar el algoritmo de encryptacion.
   * @throws InvalidKeySpecException error de especificacion de llave de encryptacion.
   */
  public String encriptarContrasenha(String contrasenha)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] sal = Encriptador.salarSiguiente();
    return Encriptador.encriptarTexto(contrasenha, sal);
  } //Fin del metodo encriptarContrasenha

  /**
   * Verifica si la contraseña de 2 usuarios son iguales.
   * @param usuario el usuario ingresado por el usuario.
   * @param usuarioValido el usuario guardado dentro de la base de datos.
   * @return si las contraseñas de usuario coinciden.
   * @throws SQLException error lanzado desde la base de datos.
   * @throws Exception error general lanzado.
   */
  public boolean validarUsuario(Usuario usuario, Usuario usuarioValido)
      throws SQLException, Exception {
    return Encriptador.validarTexto(usuario.getContrasenha(),
            usuarioValido.getContrasenha());
  } //Fin del metodo validarUsuario
  
  /**
   * Modifica la contraseña de un usuario ingresado.
   * @param usuario el usuario con la contraseña cambiada.
   * @return devuelve true o false si se modifico la contraseña o no.
   * @throws ExcepcionControlador excepcion lanzada al modificar la contrasenha.
   */
  public boolean modificarContrasenha(Usuario usuario) throws ExcepcionControlador {
    iniciarDao();
    try {
      usuario.setContrasenha(encriptarContrasenha(usuario.getContrasenha()));
    } catch (NoSuchAlgorithmException | InvalidKeySpecException excepcion) {
      throw new ExcepcionControlador("--> modificarContrasenha() - Error lanzado al"
        + " modificar la contraseña del usuario.",excepcion,this.log);
    } //Fin de try-catch

    boolean modificado;
    modificado = modificar(usuario);
    return modificado;
  } //Fin del metodo modificarContrasenha

  /**
   * Metodo no utilizado por la clase.
   */
  @Override
  public boolean comprobarDependencias(Usuario usuario) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias
  
  /**
   * Metodo no utilizado por la clase.
   */
  @Override
  public boolean eliminarDependencias(Usuario usuario) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

} //Fin de la clase controlador usuario