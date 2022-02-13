package com.geopractic.cobranza.dao;

import com.geopractic.cobranza.mapeos.MotivoMapeo;
import com.geopractic.cobranza.modelo.Motivo;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los motivos de la clase.
 * @author Cristhian
 *
 */
public class MotivoDao extends Dao<Motivo> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public MotivoDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"GestionMotivo\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);
    queryMaker.agregarColumna("\"Activo\"", ":activo", false);
    queryMaker.agregarColumna("\"Ruteo\"", ":ruteo", false);
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new MotivoMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Motivo motivo, Motivo motivoNuevo) {
    motivo.setId(motivoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Motivo buscar(Motivo motivo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Descripcion\" = :descripcion ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(motivo);
  } //Fin del metodo buscar

  @Override
  public Motivo buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Motivo> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Motivo motivo) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(motivo);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Motivo motivo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(motivo);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Motivo motivo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true);
    return super.eliminar(motivo);
  } //Fin del metodo eliminar

} //Fin de la clase MotivoDao