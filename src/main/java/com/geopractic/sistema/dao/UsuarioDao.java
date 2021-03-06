package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.UsuarioMapeo;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Usuario;
import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * el usuario.
 * @author Cristhian
 *
 */
public class UsuarioDao extends Dao<Usuario> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public UsuarioDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"Usuario\"");
    queryMaker.agregarColumna("\"Usuario\"", ":usuario", false);
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Nombre\"", ":nombre", false);
    queryMaker.agregarColumna("\"Apellido\"", ":apellido", false);
    queryMaker.agregarColumna("\"Contrasenha\"", ":contrasenha", false);
    queryMaker.agregarColumna("\"Activo\"", ":activo", false);
    queryMaker.agregarColumna("\"Rol\"", ":rol.id", false);
    queryMaker.agregarColumna("\"Empleado\"", ":empleado.id", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new UsuarioMapeo(this.conexion, this.log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Usuario usuario, Usuario usuarioNuevo) {
    usuario.setId(usuarioNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Usuario buscar(Usuario objeto) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Usuario\" = :usuario ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(objeto);
  } //Fin del metodo buscar

  @Override
  public Usuario buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Usuario> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Usuario usuario) throws ExcepcionDao {
    queryGuardar = queryMaker.crearInsert();
    //Verifica que el rol es nulo
    if (usuario.getRol() == null) {
      queryMaker.eliminarColumna("\"Rol\"");
    } //Fin de if
    //Verifica que el empleado es nulo
    if (usuario.getEmpleado() == null) {
      queryMaker.eliminarColumna("\"Empleado\"");
    } //Fin de if
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(usuario);
  } //Fin del metodo guardar
  
  @Override
  public boolean modificar(Usuario usuario) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    //Verifica que el rol no sea nulo
    if (usuario.getRol() == null) {
      queryMaker.eliminarColumna("\"Rol\"");
      queryModificar = queryMaker.crearUpdate();
      eliminarRol(usuario);
    } //Fin de if
    //Verifica que el empleado no sea nulo
    if (usuario.getEmpleado() == null) {
      queryMaker.eliminarColumna("\"Empleado\"");
      queryModificar = queryMaker.crearUpdate();
      eliminarEmpleado(usuario);
    } //Fin de if
    return super.modificar(usuario);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Usuario usuario) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(usuario);
  } //Fin del metodo eliminar

  /**
   * Devuelve todos los empleados con un rol especifico.
   * @param rol el rol a buscar.
   * @param estado para listar solo los empelados activos.
   * @return el listado con los empleados
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Usuario> buscarPorRol(Rol rol,boolean estado) throws ExcepcionDao {
    List<Usuario> listaUsuarios = new ArrayList<Usuario>();      
    StringBuilder consulta = new StringBuilder("SELECT * FROM \"Usuario\" ");
    
    //Verifica si es necesario que solo sean los activos
    if (estado) {
      consulta.append("WHERE \"Activo\" = true ");
    } //Fin de if
    consulta.append("ORDER BY \"Id\" ASC");
      
    listaUsuarios = conexion.createQuery(consulta.toString())
        .map(mapeoBean)
        .list();
    return listaUsuarios;
  } //Fin del metodo buscarId

  /**
   * Elimina el rol asignado al usuario.
   * @param usuario el usuario a quitar el rol.
   * @return si elimino o no el rol.
   * @throws ExcepcionDao excepcion lanzada al eliminar el rol del usuario.
   */
  public boolean eliminarRol(Usuario usuario) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("UPDATE \"Usuario\" SET \"Rol\" = :rol "
            + " WHERE \"Id\" = :usuario.id ;")
         .bindNull("rol", java.sql.Types.BIGINT)
         .bindBean("usuario",usuario)
         .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarRol

  /**
   * Elimina el empleado asignado al usuario.
   * @param usuario el usuario a quitar el empleado.
   * @return si elimino o no el rol.
   * @throws ExcepcionDao excepcion lanzada al eliminar el empleado del usuario.
   */
  public boolean eliminarEmpleado(Usuario usuario) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("UPDATE \"Usuario\" SET \"Empleado\" = :empleado "
            + " WHERE \"Id\" = :usuario.id ;")
         .bindNull("empleado", java.sql.Types.BIGINT)
         .bindBean("usuario",usuario)
         .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarEmpleado
} //Fin de la clase UsuarioDao