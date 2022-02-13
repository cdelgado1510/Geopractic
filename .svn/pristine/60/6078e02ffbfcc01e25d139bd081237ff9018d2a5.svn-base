package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.TurnosMapeo;
import com.geopractic.sistema.modelo.Turnos;
import com.geopractic.util.QueryMaker;

import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los turnos.
 * @author Cristhian
 *
 */
public class TurnosDao extends Dao<Turnos> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public TurnosDao() {
    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"Turnos\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);
    queryMaker.agregarColumna("\"HoraInicio\"", ":horaInicio", false);
    queryMaker.agregarColumna("\"HoraFin\"", ":horaFin", false);
    queryMaker.agregarColumna("\"Domingo\"", ":domingo", false);
    queryMaker.agregarColumna("\"Lunes\"", ":lunes", false);
    queryMaker.agregarColumna("\"Martes\"", ":martes", false);
    queryMaker.agregarColumna("\"Miercoles\"", ":miercoles", false);
    queryMaker.agregarColumna("\"Jueves\"", ":jueves", false);
    queryMaker.agregarColumna("\"Viernes\"", ":viernes", false);
    queryMaker.agregarColumna("\"Sabado\"", ":sabado", false);
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new TurnosMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Turnos turno, Turnos turnoNuevo) {
    turno.setId(turnoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Turnos buscar(Turnos turno) throws ExcepcionDao {
    queryMaker.setCondicion(" \"HoraInicio\" = :horaInicio AND \"HoraFin\" = :horaFin "
        + " AND \"Domingo\" = :domingo AND \"Lunes\" = :lunes AND \"Martes\" = :martes "
        + " AND \"Miercoles\" = :miercoles AND \"Jueves\" = :jueves AND "
        + " \"Viernes\" = :viernes AND \"Sabado\" = :sabado ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(turno);
  } //Fin del metodo buscar

  @Override
  public Turnos buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Turnos> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Turnos turno) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(turno);
  } //Fin del metodo guardar
  
  @Override
  public boolean modificar(Turnos turno) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(turno);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Turnos turno) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(turno);
  } //Fin del metodo eliminar

} //Fin de la clase TurnosDao