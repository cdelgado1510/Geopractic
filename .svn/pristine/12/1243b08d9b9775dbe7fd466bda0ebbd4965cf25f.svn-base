package com.geopractic.gps.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.gps.mapeos.EstadosRastreadorGpsMapeo;
import com.geopractic.gps.modelo.EstadosRastreadorGps;
import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los estados disponibles de los GPS.
 * @author Cristhian
 *
 */
public class EstadosRastreadorGpsDao extends Dao<EstadosRastreadorGps> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public EstadosRastreadorGpsDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"EstadosRastreadores\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);

  } //Fin de constructor sin argumentos

  @Override
  public void iniciarMapeos() {
    mapeoBean = new EstadosRastreadorGpsMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(EstadosRastreadorGps estado, EstadosRastreadorGps estadoNuevo) {
    estado.setId(estadoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public EstadosRastreadorGps buscar(EstadosRastreadorGps estado) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Descripcion\" = :descripcion ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(estado);
  } //Fin del metodo buscar

  @Override
  public EstadosRastreadorGps buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<EstadosRastreadorGps> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(EstadosRastreadorGps estado) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(estado);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(EstadosRastreadorGps estado) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(estado);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(EstadosRastreadorGps estado) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(estado);
  } //Fin del metodo eliminar

} //Fin de la clase EstadosRastreadorGpsDao