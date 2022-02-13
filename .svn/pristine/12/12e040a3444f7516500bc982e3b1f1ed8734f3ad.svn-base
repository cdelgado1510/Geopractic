package com.geopractic.cobranza.dao;

import com.geopractic.cobranza.mapeos.CuentaPorCobrarMapeo;
import com.geopractic.cobranza.modelo.CuentaPorCobrar;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.List;
import java.util.Optional;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * las cuentas por cobrar.
 * @author Cristhian
 *
 */
public class CuentaPorCobrarDao extends Dao<CuentaPorCobrar> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public CuentaPorCobrarDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"CuentaPorCobrar\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Cliente\"", ":cliente.id", false);
    queryMaker.agregarColumna("\"MontoTotal\"", ":montoTotal", false);
    queryMaker.agregarColumna("\"Cuotas\"", ":cuotas", false);
    queryMaker.agregarColumna("\"Vencimiento\"", ":vencimiento", false);
    queryMaker.agregarColumna("\"Fecha\"", ":vencimiento", false);
    queryMaker.agregarColumna("\"EmpleadoAsignado\"", ":empleadoAsignado.id", false);
    queryMaker.agregarColumna("\"Factura\"", ":factura", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new CuentaPorCobrarMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public int buscarDependencias(CuentaPorCobrar cuenta) throws ExcepcionDao {
    int cantidadDependencias = 0;
    Optional<Integer> respuesta = null;

    respuesta = conexion.createQuery("SELECT count(\"Saldo\") FROM \"GestionVisita\""
      + " WHERE \"Saldo\" = :id ;")
      .bind("id", cuenta.getId())
      .mapTo(Integer.class)
      .findFirst();
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    respuesta = conexion.createQuery("SELECT count(\"Saldo\") FROM \"Saldo\""
       + " WHERE \"Cuenta\" = :id AND \"Saldo\" = 0;")
       .bind("id", cuenta.getId())
       .mapTo(Integer.class)
       .findFirst();
    
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    return cantidadDependencias;
  } //Fin del metodo buscarDependencias

  @Override
  public void obtenerClavePrimaria(CuentaPorCobrar cuenta, CuentaPorCobrar cuentaNueva) {
    cuenta.setId(cuentaNueva.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public CuentaPorCobrar buscar(CuentaPorCobrar cuenta) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Cliente\" = :cliente.id AND \"MontoTotal\" = :montoTotal");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(cuenta);
  } //Fin del metodo buscar

  @Override
  public CuentaPorCobrar buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<CuentaPorCobrar> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(CuentaPorCobrar cuenta) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryMaker.eliminarColumna("\"Fecha\"");
    queryMaker.agregarColumna("\"Fecha\"", "now()", false);
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(cuenta);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(CuentaPorCobrar cuenta) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(cuenta);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(CuentaPorCobrar cuenta) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true);
    return super.eliminar(cuenta);
  } //Fin del metodo eliminar

} //Fin de la clase CuentaPorCobrarDao