package com.geopractic.cobranza.dao;

import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.cobranza.mapeos.RuteoCobradorMapeo;
import com.geopractic.cobranza.modelo.RuteoCobrador;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los ruteos de cobrador.
 * @author Cristhian
 *
 */
public class RuteoCobradorDao extends Dao<RuteoCobrador> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public RuteoCobradorDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"RuteoCobrador\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"FechaVisita\"", ":fechaVisita", false);
    queryMaker.agregarColumna("\"Visitado\"", ":visitado", false);
    queryMaker.agregarColumna("\"Cumplido\"", ":verificado", false);
    queryMaker.agregarColumna("\"FechaCreacion\"", ":fechaCreacion", false);
    queryMaker.agregarColumna("\"FechaCumplido\"", ":fechaCumplido", false);
    queryMaker.agregarColumna("\"Gestion\"", ":gestion.id", false);
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new RuteoCobradorMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(RuteoCobrador ruteo, RuteoCobrador ruteoNuevo) {
    ruteo.setId(ruteoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public RuteoCobrador buscar(RuteoCobrador ruteo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Gestion\" = :gestion.id ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(ruteo);
  } //Fin del metodo buscar

  @Override
  public RuteoCobrador buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<RuteoCobrador> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  /**
   * Lista el ultimo ruteo asignado al saldo.
   * @param saldo el saldo a buscar.
   * @return el listado con el ultimo ruteo
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<RuteoCobrador> listarRuteoGestion(Saldo saldo) throws ExcepcionDao {
    List<RuteoCobrador> lista = new ArrayList<RuteoCobrador>();
    lista = conexion.createQuery("SELECT \"RuteoCobrador\".* FROM "
      + "public.\"RuteoCobrador\" JOIN public.\"GestionVisita\" ON \"GestionVisita\".\"Id\" = "
      + "\"RuteoCobrador\".\"Gestion\" WHERE \"GestionVisita\".\"Saldo\" = :saldo"
      + " ORDER BY  \"GestionVisita\".\"FechaVisita\" DESC LIMIT 1;")
      .bind("saldo", saldo.getId())
      .map(mapeoBean)
      .list();
    return lista;
  } //Fin del metodo listarRuteoGestion

  @Override
  public boolean guardar(RuteoCobrador ruteo) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryMaker.eliminarColumna("\"FechaCumplido\"");
    ruteo.setFechaCreacion(new Date());
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(ruteo);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(RuteoCobrador ruteo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryMaker.eliminarColumna("\"Id\"");
    queryMaker.eliminarColumna("\"FechaCreacion\"");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(ruteo);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(RuteoCobrador ruteo) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true);
    return super.eliminar(ruteo);
  } //Fin del metodo eliminar

  /**
   * Devuelve el ruteo de una fecha especifica.
   * @param fechaInicio la fecha de inicio del rango a buscar
   * @param fechaFin la fecha de fin del rango a buscar
   * @return el listado de ruteos del dia.
   * @throws ExcepcionDao error lanzado al fallar el listado.
   */
  public List<RuteoCobrador> listarRuteoDia(Date fechaInicio,
       Date fechaFin) throws ExcepcionDao {
    List<RuteoCobrador> ruteoDia = new ArrayList<RuteoCobrador>();
    ruteoDia = conexion.createQuery("SELECT \"RuteoCobrador\".* FROM \"GestionVisita\" "
       + "JOIN \"RuteoCobrador\" ON \"RuteoCobrador\".\"Gestion\" = \"GestionVisita\".\"Id\""
       + " WHERE \"RuteoCobrador\".\"FechaVisita\" >= :fechaInicio AND "
       + "\"RuteoCobrador\".\"FechaVisita\" <= :fechaFin "
       + " ORDER BY \"RuteoCobrador\".\"Id\" ASC ")
       .bind("fechaInicio", fechaInicio)
       .bind("fechaFin",fechaFin)
       .map(mapeoBean)
       .list();
    return ruteoDia;
  } //Fin del metodo listarRuteoDia

  /**
   * Devuelve el ruteo de una fecha especifica de un cobrador..
   * @param cobrador el empleado cobrador a buscar.
   * @param fechaInicio la fecha de inicio del rango a buscar
   * @param fechaFin la fecha de fin del rango a buscar
   * @return el listado de ruteos del dia de los empleados.
   * @throws ExcepcionDao error lanzado al fallar el listado.
   */
  public List<RuteoCobrador> listarRuteoCobradorDia(Empleado cobrador,Date fechaInicio,
       Date fechaFin) throws ExcepcionDao {
    List<RuteoCobrador> ruteoDia = new ArrayList<RuteoCobrador>();
    ruteoDia = conexion.createQuery("SELECT \"RuteoCobrador\".* FROM \"GestionVisita\" "
       + "JOIN \"RuteoCobrador\" ON \"RuteoCobrador\".\"Gestion\" = \"GestionVisita\".\"Id\""
       + " WHERE \"RuteoCobrador\".\"FechaVisita\" >= :fechaInicio AND "
       + "\"RuteoCobrador\".\"FechaVisita\" <= :fechaFin AND"
       + " \"GestionVisita\".\"EmpleadoVisita\" = :cobrador ORDER BY \"RuteoCobrador\".\"Id\" ASC ")
       .bind("fechaInicio", fechaInicio)
       .bind("fechaFin",fechaFin)
       .bind("cobrador", cobrador.getId())
       .map(mapeoBean)
       .list();
    return ruteoDia;
  } //Fin del metodo listarRuteoCobradorDia

} //Fin de la clase RuteoCobradorDao