package com.geopractic.cobranza.dao;

import com.geopractic.cobranza.mapeos.GestionVisitaMapeo;
import com.geopractic.cobranza.modelo.GestionVisita;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.util.QueryMaker;

import java.util.List;
import java.util.Optional;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los tipos de georreferencias posibles.
 * @author Cristhian
  */
public class GestionVisitaDao extends Dao<GestionVisita> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public GestionVisitaDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"GestionVisita\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Fecha\"", ":fecha", false);
    queryMaker.agregarColumna("\"FechaVisita\"", ":fechaVisita", false);
    queryMaker.agregarColumna("\"Monto\"", ":monto", false);
    queryMaker.agregarColumna("\"EmpleadoCarga\"", ":empleadoCarga.id", false);
    queryMaker.agregarColumna("\"EmpleadoVisita\"", ":empleadoVisita.id", false);
    queryMaker.agregarColumna("\"FormaDePago\"", ":formaDePago.id", false);
    queryMaker.agregarColumna("\"LugarVisita\"", ":lugarVisita.id", false);
    queryMaker.agregarColumna("\"Saldo\"", ":saldo.id", false);
    queryMaker.agregarColumna("\"Comentario\"", ":comentario", false);
    queryMaker.agregarColumna("\"Motivo\"", ":motivo.id", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new GestionVisitaMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(GestionVisita gestion, GestionVisita gestionNueva) {
    gestion.setId(gestionNueva.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public int buscarDependencias(GestionVisita gestion) throws ExcepcionDao {
    int cantidadDependencias = 0;
    Optional<Integer> respuesta = null;

    respuesta = conexion.createQuery("SELECT count(\"Id\") FROM \"GestionVisita\""
      + " WHERE \"Saldo\" = :saldo AND \"FechaVisita\" = :fechaVisita "
      + "  AND \"EmpleadoVisita\" = :empleadoVisita  ;")
      .bind("saldo", gestion.getSaldo().getId())
      .bind("fechaVisita", gestion.getFechaVisita())
      .bind("empleadoVisita", gestion.getEmpleadoVisita().getId())
      .mapTo(Integer.class)
      .findFirst();
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    return cantidadDependencias;
  } //Fin del metodo buscarDependencias

  @Override
  public GestionVisita buscar(GestionVisita gestion) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Fecha\" = :fecha AND \"FechaVisita\" = :fechaVisita "
            + " AND \"EmpleadoCarga\" = :empleadoCarga.id AND"
            + " \"EmpleadoVisita\" = :empleadoVisita.id");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(gestion);
  } //Fin del metodo buscar

  @Override
  public GestionVisita buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<GestionVisita> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(GestionVisita gestion) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    //Verifica si se asigno un lugar de visita
    if (gestion.getLugarVisita() == null) {
      queryMaker.eliminarColumna("\"LugarVisita\"");
    } //Fin de if
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(gestion);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(GestionVisita gestion) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    //Verifica si se asigno un lugar de visita
    if (gestion.getLugarVisita() == null) {
      queryMaker.eliminarColumna("\"LugarVisita\"");
    } //Fin de if
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(gestion);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(GestionVisita gestion) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(gestion);
  } //Fin del metodo eliminar

} //Fin de la clase GestionVisitaDao