package com.geopractic.cobranza.dao;

import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.cobranza.mapeos.SaldoMapeo;
import com.geopractic.cobranza.modelo.CuentaPorCobrar;
import com.geopractic.cobranza.modelo.GestionVisita;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los saldos de las cuentas.
 * @author Cristhian
 *
 */
public class SaldoDao extends Dao<Saldo> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public SaldoDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"Saldo\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Saldo\"", ":saldo", false);
    queryMaker.agregarColumna("\"Vencimiento\"", ":vencimiento", false);
    queryMaker.agregarColumna("\"Cuota\"", ":cuota", false);
    queryMaker.agregarColumna("\"TotalCuota\"", ":totalCuota", false);
    queryMaker.agregarColumna("\"Cuenta\"", ":cuenta", false);
    queryMaker.agregarColumna("\"Cliente\"", ":cliente", false);
    queryMaker.agregarColumna("\"FechaGestion\"", ":fechaGestion", false);
    queryMaker.agregarColumna("\"EmpleadoAsignado\"", ":empleadoAsignado", false);
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new SaldoMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Saldo saldo, Saldo saldoNuevo) {
    saldo.setId(saldoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Saldo buscar(Saldo saldo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Cuenta\" = :cuenta AND \"Cuota\" = :cuota ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(saldo);
  } //Fin del metodo buscar

  @Override
  public Saldo buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  /**
   * Lista los saldos pertenecientes a una cuenta específica.
   * @param cuenta la cuenta a buscar.
   * @return el listado de saldos disponible para la cuenta
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Saldo> listarSaldosCuenta(CuentaPorCobrar cuenta) throws ExcepcionDao {
    List<Saldo> lista = new ArrayList<Saldo>();
    queryMaker.setCondicion(" \"Cuenta\" = :id ");
    queryMaker.setOrden(" \"Cuota\" ASC ");
    lista = conexion.createQuery(queryMaker.crearSelect(true, true))
       .bind("id", cuenta.getId())
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarSaldosCuenta

  /**
   * Lista los saldos pertenecientes asignados a una cuenta asignados a un empleado.
   * @param empleado el empleado a buscar.
   * @return el listado de saldos disponible para la cuenta
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Saldo> listarSaldosCuentaEmpleado(Empleado empleado) throws ExcepcionDao {
    List<Saldo> lista = new ArrayList<Saldo>();
    String consulta = "SELECT * FROM \"Saldo\" WHERE "
          + "\"EmpleadoAsignado\" = :id "
          + " ORDER BY \"Cuenta\", \"Cuota\" ASC ;";
    lista = conexion.createQuery(consulta)
       .bind("id", empleado.getId())
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarSaldosCuentaEmpleado

  /**
   * Lista los saldos pertenecientes a un cliente especifico.
   * @param cliente el cliente a buscar.
   * @return el listado de saldos disponible para la cuenta
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Saldo> listarSaldosCliente(Cliente cliente) throws ExcepcionDao {
    List<Saldo> lista = new ArrayList<Saldo>();
    String consulta = "SELECT * FROM \"Saldo\" WHERE "
          + "\"Cliente\" = :id "
          + " ORDER BY \"Cuenta\", \"Cuota\" ASC ;";
    lista = conexion.createQuery(consulta)
       .bind("id", cliente.getId())
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarSaldosCliente

  @Override
  public List<Saldo> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Saldo saldo) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(saldo);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Saldo saldo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryMaker.eliminarColumna("\"Id\"");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(saldo);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Saldo saldo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true);
    return super.eliminar(saldo);
  } //Fin del metodo eliminar

  /**
   * Actualiza los datos de gestion del saldo.
   * @param saldo el saldo a actualizar.
   * @param gestion la gestion realizada.
   * @return si los datos fueron actualizados o no.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean actualizarGestion(Saldo saldo,GestionVisita gestion) throws ExcepcionDao {
    int respuesta = 0;
    respuesta = conexion.createUpdate("UPDATE \"Saldo\" SET \"FechaGestion\" = :gestion ,"
       + " \"EmpleadoAsignado\" = :empAsign WHERE \"Id\" = :saldo ;")
       .bind("saldo", saldo.getId())
       .bind("gestion", gestion.getFechaVisita())
       .bind("empAsign", gestion.getEmpleadoVisita().getId())
       .execute();
    return (respuesta == 1) ? true : false;
  } //Fin del metodo actualizarGestion

} //Fin de la clase SaldoDao