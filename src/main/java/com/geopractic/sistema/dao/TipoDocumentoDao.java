package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.TipoDocumentoMapeo;
import com.geopractic.sistema.modelo.TipoDocumento;

import java.util.Optional;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los tipos de documento.
 * @author Cristhian
 *
 */
public class TipoDocumentoDao extends Dao<TipoDocumento> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public TipoDocumentoDao() {
    queryBuscar = "SELECT * FROM \"TiposDocumentos\" WHERE \"Descripcion\" = :descripcion ;";

    queryBuscarId = "SELECT * FROM \"TiposDocumentos\" WHERE \"Id\" = :id";

    queryListarTodos = "SELECT * FROM \"TiposDocumentos\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"TiposDocumentos\"( \"Descripcion\")"
        + " VALUES ( :descripcion);";

    queryModificar = "UPDATE \"TiposDocumentos\" SET \"Descripcion\" = :descripcion" 
        + " WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM \"TiposDocumentos\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new TipoDocumentoMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(TipoDocumento tipoDocumento, TipoDocumento tipoDocumentoNuevo) {
    tipoDocumento.setId(tipoDocumentoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public int buscarDependencias(TipoDocumento tipoDocumento) throws ExcepcionDao {
    int cantidadDependencias = 0;
    Optional<Integer> respuesta = null;

    respuesta = conexion.createQuery("SELECT count(\"NroDocumento\") FROM \"Cliente\""
      + " WHERE \"TipoDocumento\" = :id ;")
      .bind("id", tipoDocumento.getId())
      .mapTo(Integer.class)
      .findFirst();
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    return cantidadDependencias;
  } //Fin del metodo buscarDependencias
} //Fin de la clase TipoDocumentoDao