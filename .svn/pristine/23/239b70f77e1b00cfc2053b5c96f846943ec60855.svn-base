package com.geopractic.georrefencia.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.georrefencia.mapeos.GeorreferenciaMapeo;
import com.geopractic.georrefencia.modelo.Georreferencia;
import com.geopractic.util.QueryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * las georreferencias.
 * @author Cristhian
 *
 */
public class GeorreferenciaDao extends Dao<Georreferencia> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public GeorreferenciaDao() {

    //Inicia el queryMaker para generar consultas
    queryMaker = new QueryMaker();
    queryMaker.setNombreTabla("\"Georreferencia\"");
    queryMaker.agregarColumna("\"Id\"", ":id", true);
    queryMaker.agregarColumna("\"Descripcion\"", ":descripcion", false);
    queryMaker.agregarColumna("\"Tipo\"", ":tipo.id", true);
    queryMaker.agregarColumna("\"Cliente\"", ":cliente.id", false);

  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new GeorreferenciaMapeo(this.conexion,this.log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Georreferencia georreferencia, 
       Georreferencia georreferenciaNueva) {
    georreferencia.setId(georreferenciaNueva.getId());
  } //Fin del metodo obtenerClavePrimaria


  @Override
  public Georreferencia buscar(Georreferencia georreferencia) throws ExcepcionDao {
    StringBuilder builder = new StringBuilder("SELECT * FROM \"Georreferencia\" "
        + "WHERE \"Tipo\" = :tipo.id");
    if (georreferencia.getCliente() != null) {
      builder.append(" AND \"Cliente\" = :cliente.id");
    } //Fin de if
    builder.append(";");
    queryBuscar = builder.toString();
    return super.buscar(georreferencia);
  } //Fin del metodo buscar

  @Override
  public Georreferencia buscarId(long id) throws ExcepcionDao {
    queryBuscarId = "SELECT * FROM \"Georreferencia\" WHERE \"Id\" = :id";
    return super.buscarId(id);
  } //Fin del metodo buscarId

  @Override
  public List<Georreferencia> listarTodos() throws ExcepcionDao {
    queryMaker.setOrden(" \"Id\" ASC ");
    queryListarTodos = queryMaker.crearSelectAll(true);
    return super.listarTodos();
  } //Fin del metodo listarTodos

  @Override
  public boolean guardar(Georreferencia georreferencia) throws ExcepcionDao {
    StringBuilder builder = new StringBuilder("INSERT INTO \"Georreferencia\""
         + "(\"Descripcion\",\"Tipo\"");
    //Verifica si tiene un cliente
    if (georreferencia.getCliente() != null) {
      builder.append(",\"Cliente\"");
    } //Fin de if
    //Verifica si es un punto o una zona
    if (georreferencia.getTipo().getClase().equals("Punto")) {
      builder.append(",\"Punto\"");
    } else {
      builder.append(",\"Zona\"");
    } //Fin de if-else

    //Agrega los valores normales
    builder.append(" ) VALUES( :descripcion, :tipo.id, ");
    //Verifica si tiene un cliente
    if (georreferencia.getCliente() != null) {
      builder.append(":cliente.id, ");
    } //Fin de if
    //Verifica si es un punto o una zona
    if (georreferencia.getTipo().getClase().equals("Punto")) {
      builder.append("ST_SetSRID( ST_MakePoint( :punto.longitud , :punto.latitud ), 4326 )");
    } else {
      georreferencia.getZona().convertirMakePoint();
      builder.append("ST_SetSRID( ST_MakePolygon( ST_GeomFromText ( \'");
      builder.append(georreferencia.getZona().getPuntosMakePoint());
      builder.append("\')) ,4326 )");
    } //Fin de if-else

    builder.append(" );");

    queryGuardar = builder.toString();
    return super.guardar(georreferencia);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Georreferencia georreferencia) throws ExcepcionDao {
    StringBuilder builder = new StringBuilder("UPDATE \"Georreferencia\" SET "
            + "\"Descripcion\" = :descripcion ,\"Tipo\" = :tipo.id ");

    //Verifica si tiene un cliente
    if (georreferencia.getCliente() != null) {
      builder.append(" , \"Cliente\" = :cliente.id ");
    } //Fin de if

    //Verifica si es un punto o una zona
    if (georreferencia.getTipo().getClase().equals("Punto")) {
      builder.append(" , \"Punto\" = ST_SetSRID( ST_MakePoint( :punto.longitud , :punto.latitud )"
          + ", 4326 )");
    } else {
      georreferencia.getZona().convertirMakePoint();
      builder.append(" , \"Zona\" = ST_SetSRID( ST_MakePolygon( ST_GeomFromText ( \'");
      builder.append(georreferencia.getZona().getPuntosMakePoint());
      builder.append("\')) ,4326 )");
    } //Fin de if-else

    builder.append(" WHERE \"Id\" = :id  ;");
    queryModificar = builder.toString();

    return super.modificar(georreferencia);
  } //Fin del metodo modificar

  @Override
  public boolean eliminar(Georreferencia georreferencia) throws ExcepcionDao {
    queryMaker.setCondicion(" \"Id\" = :id ");
    queryEliminar = queryMaker.crearDelete(true); 
    return super.eliminar(georreferencia);
  } //Fin del metodo eliminar

  /**
   * Lista las georreferencias pertenecientes al cliente.
   * @param cliente el identificador del cliente a buscar.
   * @return el listado de georreferencias del cliente.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public List<Georreferencia> listarGeorreferenciasClientes(long cliente) throws ExcepcionDao {
    List<Georreferencia> lista = new ArrayList<Georreferencia>();
    lista = conexion.createQuery("SELECT * FROM \"Georreferencia\" WHERE  \"Cliente\" = :id ")
       .bind("id", cliente)
       .map(mapeoBean)
       .list();
    return lista;
  } //Fin del metodo listarGeorreferenciasClientes

} //Fin de la clase GeorreferenciaDao