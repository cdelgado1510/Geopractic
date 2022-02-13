package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.TipoTelefonoMapeo;
import com.geopractic.sistema.modelo.TipoTelefono;

import java.util.Optional;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los tipos de telefono.
 * @author Cristhian
 *
 */
public class TipoTelefonoDao extends Dao<TipoTelefono> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public TipoTelefonoDao() {
    queryBuscar = "SELECT * FROM \"TipoTelefono\" WHERE \"Nombre\" = :nombre ;";

    queryBuscarId = "SELECT * FROM \"TipoTelefono\" WHERE \"Id\" = :id\"";

    queryListarTodos = "SELECT * FROM \"TipoTelefono\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"TipoTelefono\" (\"Nombre\", \"Patron\") "
          + "VALUES ( :nombre, :patron );";

    queryModificar = "UPDATE \"TipoTelefono\" SET \"Nombre\" = :nombre , "
          + "\"Patron\" = :patron WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM \"TipoTelefono\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new TipoTelefonoMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(TipoTelefono tipoTelefono, TipoTelefono tipoTelefonoNuevo) {
    tipoTelefono.setId(tipoTelefonoNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public int buscarDependencias(TipoTelefono tipoTelefono) throws ExcepcionDao {
    int cantidadDependencias = 0;
    Optional<Integer> respuesta = null;

    respuesta = conexion.createQuery("SELECT count(\"Numero\") FROM \"Telefonos\""
      + " WHERE \"TipoTelefono\" = :id ;")
      .bind("id", tipoTelefono.getId())
      .mapTo(Integer.class)
      .findFirst();
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    return cantidadDependencias;
  } //Fin del metodo buscarDependencias
} //Fin de la clase TipoTelefonoDao