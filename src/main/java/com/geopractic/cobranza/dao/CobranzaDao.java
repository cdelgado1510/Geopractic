package com.geopractic.cobranza.dao;

import com.geopractic.cobranza.mapeos.CobranzaMapeo;
import com.geopractic.cobranza.modelo.Cobranza;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * las cobranzas pagadas.
 * @author Cristhian
 *
 */
public class CobranzaDao extends Dao<Cobranza> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public CobranzaDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"Cobranza\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Fecha\"", ":fecha", false);
    queryMaker.agregarColumna("\"Recibo\"", ":recibo", false);
    queryMaker.agregarColumna("\"Factura\"", ":factura", false);
    queryMaker.agregarColumna("\"Monto\"", ":monto", false);
    queryMaker.agregarColumna("\"Interes\"", ":interes", false);
    queryMaker.agregarColumna("\"Cliente\"", ":cliente.id", false);
    queryMaker.agregarColumna("\"Cobrador\"", ":cobrador.id", false);
    queryMaker.agregarColumna("\"Saldo\"", ":saldo.id", false);
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new CobranzaMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Cobranza cobranza, Cobranza cobranzaNuevo) {
    cobranza.setId(cobranzaNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Cobranza buscar(Cobranza cobranza) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Descripcion\" = :descripcion ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(cobranza);
  } //Fin del metodo buscar

  @Override
  public Cobranza buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Cobranza> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Cobranza cobranza) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(cobranza);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Cobranza cobranza) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(cobranza);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Cobranza cobranza) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true);
    return super.eliminar(cobranza);
  } //Fin del metodo eliminar

} //Fin de la clase SaldoDao