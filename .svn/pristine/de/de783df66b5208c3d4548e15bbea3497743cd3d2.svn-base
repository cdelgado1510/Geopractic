package com.geopractic.cobranza.dao;

import com.geopractic.cobranza.mapeos.FormaDePagoMapeo;
import com.geopractic.cobranza.modelo.FormaDePago;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * las formas de pago.
 * @author Cristhian
 *
 */
public class FormaDePagoDao extends Dao<FormaDePago> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public FormaDePagoDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"FormaDePago\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new FormaDePagoMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(FormaDePago forma, FormaDePago formaNueva) {
    forma.setId(formaNueva.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public FormaDePago buscar(FormaDePago formaDePago) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Descripcion\" = :descripcion ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(formaDePago);
  } //Fin del metodo buscar

  @Override
  public FormaDePago buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<FormaDePago> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(FormaDePago formaDePago) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(formaDePago);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(FormaDePago formaDePago) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(formaDePago);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(FormaDePago formaDePago) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(formaDePago);
  } //Fin del metodo eliminar

} //Fin de clase FormaDePagoDao