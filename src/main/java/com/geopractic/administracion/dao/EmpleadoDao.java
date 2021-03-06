package com.geopractic.administracion.dao;

import com.geopractic.administracion.mapeos.EmpleadoMapeo;
import com.geopractic.administracion.modelo.Cargo;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao extends Dao<Empleado> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public EmpleadoDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"Empleado\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Nombre\"", ":nombre", false);
    queryMaker.agregarColumna("\"Apellido\"", ":apellido", false);
    queryMaker.agregarColumna("\"Cedula\"", ":cedula", false);
    queryMaker.agregarColumna("\"FechaNacimiento\"", ":fechaNacimiento", false);
    queryMaker.agregarColumna("\"Estado\"", ":estado", false);
    queryMaker.agregarColumna("\"Cargo\"", ":cargo.id", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new EmpleadoMapeo(this.conexion, this.log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Empleado empleado, Empleado empleadoNuevo) {
    empleado.setId(empleadoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Empleado buscar(Empleado empleado) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Cedula\" = :cedula ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(empleado);
  } //Fin del metodo buscar

  @Override
  public Empleado buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Empleado> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  /**
   * Devuelve el listado de empleados por cargo.
   * @param cargo el cargo a buscar en los empelados.
   * @return el listado de empleados con el cargo ingresado
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Empleado> listarPorCargo(Cargo cargo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Cargo\" = :cargo ");
    queryMaker.setOrden(" \"Id\" ASC ");
    List<Empleado> lista = new ArrayList<Empleado>();
    lista = conexion.createQuery(queryMaker.crearSelect(true, true))
       .bind("cargo", cargo.getId())
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarPorCargo

  /**
   * Devuelve el listado de empleados por cargos.
   * @param cargos lista de cargos a buscar en los empelados.
   * @return el listado de empleados con los cargos ingresadso
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Empleado> listarPorCargos(String[] cargos) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Cargo\" in (:cargos) ");
    queryMaker.setOrden(" \"Id\" ASC ");
    List<Empleado> lista = new ArrayList<Empleado>();
    lista = conexion.createQuery(queryMaker.crearSelect(true, true))
       .bind("cargos", cargos)
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarPorCargos

  @Override
  public boolean guardar(Empleado empleado) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    //Verifica que el cargo no sea nulo
    if (empleado.getCargo() == null) {
      queryMaker.eliminarColumna("\"Cargo\"");
    } //Fin de if
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(empleado);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Empleado empleado) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    //Verifica que el cargo no sea nulo
    if (empleado.getCargo() == null) {
      queryMaker.eliminarColumna("\"Cargo\"");
    } //Fin de if
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(empleado);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Empleado empleado) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(empleado);
  } //Fin del metodo eliminar

} //Fin de la clase EmpleadoDao