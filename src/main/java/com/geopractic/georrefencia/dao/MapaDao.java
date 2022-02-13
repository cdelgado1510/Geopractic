package com.geopractic.georrefencia.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.mapeos.MapaMapeo;
import com.geopractic.georrefencia.modelo.Mapa;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;
import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los mapas del sistema.
 * @author Cristhian
 */
public class MapaDao extends Dao<Mapa> {


  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public MapaDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"PanelVisualizacionMapas\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"VerZonas\"", ":verZonas", false);
    queryMaker.agregarColumna("\"Rastreador\"", ":rastreadorGps.id", false);
    queryMaker.agregarColumna("\"PanelVisualizacion\"", ":panel", false);

  } //Fin del constructor sin argumentos

  @Override
  public void iniciarMapeos() {
    mapeoBean = new MapaMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Mapa mapa, Mapa mapaNuevo) {
    mapa.setId(mapaNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public Mapa buscar(Mapa mapa) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Rastreador\" = :rastreadorGps.id AND"
          + " \"PanelVisualizacion\" = :panel ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(mapa);
  } //Fin del metodo buscar

  @Override
  public Mapa buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Mapa> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Mapa mapa) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(mapa);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Mapa mapa) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(mapa);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Mapa mapa) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(mapa);
  } //Fin del metodo eliminar

  /**
   * Devuelve el listado de mapas asignados al panel solicitado.
   * @param panel el panel cuyos mapas buscar.
   * @return el listado de los mapas del panel.
   * @throws ExcepcionDao error lanzado al fallar el listado.
   */
  public List<Mapa> listarMapasPanel(PanelVisualizacion panel) throws ExcepcionDao {
    List<Mapa> listaMapa = new ArrayList<Mapa>();
    listaMapa = conexion.createQuery("SELECT * FROM \"PanelVisualizacionMapas\" "
       + "WHERE \"PanelVisualizacion\" = :panel ")
       .bind("panel", panel.getId())
       .map(this.mapeoBean)
       .list();
    return listaMapa;
  } //Fin del metodo listarMapasPanel

  /**
   * Elimina las relaciones de mapa asociadas al panel.
   * @param panel panel a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarMapasPanel(PanelVisualizacion panel) throws ExcepcionDao {
    int respuesta = 0;
    try {
      respuesta = conexion.createUpdate("DELETE FROM \"PanelVisualizacionMapas\""
         + " WHERE \"PanelVisualizacion\" = :panel ;")
         .bind("panel", panel.getId())
         .execute();
    } catch (Exception excepcion) {
      throw new ExcepcionDao("<-- eliminarMapasPanel() - Error al eliminar las"
            + " dependencias del Panel. ",
            excepcion, this.log);
    } //Fin de try-catch
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol

} //Fin de la clase MapaDao