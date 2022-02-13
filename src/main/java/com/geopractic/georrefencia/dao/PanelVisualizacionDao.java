package com.geopractic.georrefencia.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.mapeos.PanelVisualizacionMapeo;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;
import com.geopractic.util.QueryMaker;

import java.util.List;
import java.util.Optional;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los paneles de visualizaciones.
 * @author Cristhian
 */
public class PanelVisualizacionDao extends Dao<PanelVisualizacion> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public PanelVisualizacionDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"PanelVisualizacion\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);
    queryMaker.agregarColumna("\"IntervaloRefresco\"", ":intervaloRefresco", false);
    queryMaker.agregarColumna("\"EmpleadoCreador\"", ":empleadoCreador.id", false);
    queryMaker.agregarColumna("\"Turno\"", ":turno.id", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new PanelVisualizacionMapeo(conexion, log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(PanelVisualizacion panel, PanelVisualizacion panelNuevo) {
    panel.setId(panelNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public PanelVisualizacion buscar(PanelVisualizacion panel) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Descripcion\" = :descripcion ");
    queryBuscar = queryMaker.crearSelect(true, false);
    return super.buscar(panel);
  } //Fin del metodo buscar

  @Override
  public PanelVisualizacion buscarId(long id) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryBuscarId = queryMaker.crearSelect(true, false);
    PanelVisualizacion resultado = null;
    try {
      Optional<PanelVisualizacion> respuesta = conexion.createQuery(queryBuscarId)
          .bind("id", id)
          .map(mapeoBean)
          .findFirst();
      if (respuesta.isPresent()) {
        resultado = respuesta.get();
      } //Fin de if
    } catch (IllegalStateException  excepcion) {
      throw new ExcepcionDao("Id de panel de visualizacion no encontrado",
           excepcion, this.log);
    } //Fin de try-catch

    return resultado;
  } //Fin del metodo buscarId

  @Override
  public List<PanelVisualizacion> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(PanelVisualizacion panel) throws ExcepcionDao {
    queryMaker.eliminarColumna("\"Id\"");
    queryGuardar = queryMaker.crearInsert();
    return super.guardar(panel);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(PanelVisualizacion panel) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryModificar = queryMaker.crearUpdate();
    return super.modificar(panel);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(PanelVisualizacion panel) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(panel);
  } //Fin del metodo eliminar

} //Fin de la clase PanelVisualizacionDao