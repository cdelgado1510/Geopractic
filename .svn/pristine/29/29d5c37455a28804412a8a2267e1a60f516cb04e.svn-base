package com.geopractic.sistema;

import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.RolDao;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Usuario;
import com.geopractic.sistema.modelo.Ventana;

import java.util.List;

/**
 * Clase controladora para el manejo de accesos a las opciones de
 * leer, crear, modificar y eliminar.
 * @author Cristhian
 *
 */
public class ControladorAccesos extends Controlador<Rol> {

  /**
   * Inicia el dao y los recursos del mismo.
   * @throws ExcepcionControlador excepcion lanzada al iniciar la conexion.
   */
  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new RolDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los recursos"
          + " del Dao de Accesos.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao
  
  /**
   * Devuelve el permiso de la ventana.
   * @param rol el rol a buscar
   * @param ventanaBuscada la ventana a buscar
   * @param permiso el permiso a buscar
   * @return el resultado si esta o no habilitado el rol.
   */
  public boolean estaHabilitado(Rol rol, Ventana ventanaBuscada, String permiso) {
    boolean respuesta = false;
    ventanaBuscada = rol.buscarVentana(ventanaBuscada);

    //Verifica si se obtuvo una ventana
    if (ventanaBuscada != null) {
      switch (permiso) {
        case "leer":
          respuesta = ventanaBuscada.isLeer();
          break;
        case "crear":
          respuesta = ventanaBuscada.isCrear();
          break;
        case "actualizar":
          respuesta = ventanaBuscada.isActualizar();
          break;
        case "borrar":
          respuesta = ventanaBuscada.isEliminar();
          break;
        default:
          break;
      } //Fin de switch case
    } //Fin de if
    return respuesta;
  } //Fin del metodo estaHabilitado

  /**
   * Actualiza el rol del usuario para obtener los nuevos permisos de la base de datos.
   * @param usuario el usuario a actualizar.
   * @return el usuario con el rol actualizado.
   */
  public Usuario comprobarRol(Usuario usuario)  {
    try {
      iniciarDao();
      Rol rolActualizado = dao.buscarId(usuario.getRol().getId());
      usuario.setRol(rolActualizado);
    } catch (Exception excepcion) {
      log.error("--> comprobarRol() - Error al busacr el rol ",excepcion);
    } finally {
      if (dao != null) {
        try {
          dao.finalizarRecursos();
        } catch (Exception excepcion) {
          log.error("--> comprobarRol() - Error al finalizarRecursos ",excepcion);
        } //Fin de try-catch interno
      } //Fin de if
    } //Fin de try-catch
    return usuario;
  } //Fin del metodo comprobarRol

  /**
   * Metodo no soportado por la clase.
   */
  @Override
  public Rol buscar(Rol objeto) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo buscar

  /**
   * Metodo no soportado por la clase.
   */
  @Override
  public List<Rol> listarTodos() throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo listarTodos

  /**
   * Metodo no soportado por la clase.
   */
  @Override
  public boolean guardar(Rol rol) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo guardar

  /**
   * Metodo no soportado por la clase.
   */
  @Override
  public boolean modificar(Rol rol) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo modificar

  /**
   * Metodo no soportado por la clase.
   */
  @Override
  public boolean eliminar(Rol rol) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo eliminar

  @Override
  public boolean comprobarDependencias(Rol rol) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo comprobarDependencias
  
  /**
   * Metodo no utilizado por la clase.
   */
  @Override
  public boolean eliminarDependencias(Rol rol) throws ExcepcionControlador {
    throw new UnsupportedOperationException("Metodo no soportado");
  } //Fin del metodo eliminarDependencias

} //Fin de la clase accesos