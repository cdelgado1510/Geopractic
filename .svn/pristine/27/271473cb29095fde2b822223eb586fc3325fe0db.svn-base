package com.geopractic.georrefencia.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.mapeos.TipoGeorreferenciaMapeo;
import com.geopractic.georrefencia.modelo.TipoGeorreferencia;

import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los tipos de georreferencias posibles.
 * @author Cristhian
 *
 */
public class TipoGeorreferenciaDao extends Dao<TipoGeorreferencia> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public TipoGeorreferenciaDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"TipoGeorreferencia\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);
    queryMaker.agregarColumna("\"Clase\"", ":clase", false);
    queryMaker.agregarColumna("\"Icono\"", ":icono", false);
    queryMaker.agregarColumna("\"ColorContorno\"", ":colorContorno", false);
    queryMaker.agregarColumna("\"ColorRelleno\"", ":colorRelleno", false);
    queryMaker.agregarColumna("\"Activo\"", ":activo", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new TipoGeorreferenciaMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(TipoGeorreferencia tipo, TipoGeorreferencia tipoNuevo) {
    tipo.setId(tipoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public TipoGeorreferencia buscar(TipoGeorreferencia tipo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Descripcion\" = :descripcion ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(tipo);
  } //Fin del metodo buscar

  @Override
  public TipoGeorreferencia buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<TipoGeorreferencia> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(TipoGeorreferencia tipo) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(tipo);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(TipoGeorreferencia tipo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(tipo);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(TipoGeorreferencia tipo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(tipo);
  } //Fin del metodo eliminar

} //Fin de la clase TipoGeorreferenciaDao