package com.geopractic.gps.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.gps.mapeos.RastreadorMapeo;
import com.geopractic.gps.modelo.Rastreador;

import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los rastreadores del sistema.
 * @author Cristhian
 *
 */
public class RastreadorDao extends Dao<Rastreador> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public RastreadorDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"RastreadorGPS\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"IMEI\"", ":imei", false);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);
    queryMaker.agregarColumna("\"Estado\"", ":estado.id", false);
    queryMaker.agregarColumna("\"Empleado\"", ":empleadoAsignado.id", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new RastreadorMapeo(this.conexion, this.log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Rastreador rastreador, Rastreador rastreadorNuevo) {
    rastreador.setId(rastreadorNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Rastreador buscar(Rastreador rastreador) throws ExcepcionDao {
    queryMaker.setCondicion(" \"IMEI\" = :imei ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(rastreador);
  } //Fin del metodo buscar

  @Override
  public Rastreador buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Rastreador> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Rastreador rastreador) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    //Verifica que el empleado es nulo
    if (rastreador.getEmpleadoAsignado() == null) {
      queryMaker.eliminarColumna("\"Empleado\"");
    } //Fin de if
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(rastreador);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Rastreador rastreador) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(rastreador);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Rastreador rastreador) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(rastreador);
  } //Fin del metodo eliminar

} //Fin de la clase RastreadorDao