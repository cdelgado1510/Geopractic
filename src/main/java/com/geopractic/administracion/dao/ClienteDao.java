package com.geopractic.administracion.dao;

import com.geopractic.administracion.mapeos.ClienteMapeo;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;

import java.util.Optional;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los clientes.
 * @author Cristhian
 *
 */
public class ClienteDao extends Dao<Cliente> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public ClienteDao() {
    queryBuscar = "SELECT * FROM \"Cliente\" WHERE \"NroDocumento\" = :nroDocumento ;";

    queryBuscarId = "SELECT * FROM \"Cliente\" WHERE \"Id\" = :id";

    queryListarTodos = "SELECT * FROM \"Cliente\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"Cliente\"(\"PrimerNombre\","
        + "\"SegundoNombre\", \"PrimerApellido\", \"SegundoApellido\", \"ApellidoCasada\""
        + ", \"NroDocumento\", \"RUC\", \"FechaNacimiento\", \"VencimientoDocumento\", "
        + "\"FechaCreacion\", \"TipoDocumento\", \"Bloqueado\")"
        + " VALUES (:primerNombre , :segundoNombre , :primerApellido , :segundoApellido , "
        + ":apellidoCasada , :nroDocumento, :ruc, :fechaNacimiento, :vencimientoDocumento, "
        + "now(), :tipoDocumento.id, :bloqueado);";

    queryModificar = "UPDATE \"Cliente\" SET \"PrimerNombre\" = :primerNombre, "
        + "\"SegundoNombre\" = :segundoNombre, \"PrimerApellido\" = :primerApellido, "
        + "\"SegundoApellido\" = :segundoApellido, \"ApellidoCasada\" = :apellidoCasada, "
        + "\"NroDocumento\" = :nroDocumento, \"RUC\" = :ruc, \"FechaNacimiento\" = "
        + ":fechaNacimiento, \"VencimientoDocumento\" = :vencimientoDocumento, "
        + "\"FechaCreacion\" = :fechaCreacion, \"TipoDocumento\" = :tipoDocumento.id, "
        + "\"Bloqueado\" = :bloqueado WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM \"Cliente\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new ClienteMapeo(this.conexion,this.log);
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Cliente cliente, Cliente clienteNuevo) {
    cliente.setId(clienteNuevo.getId());
    cliente.setFechaCreacion(clienteNuevo.getFechaCreacion());
  } //Fin del metodo obtenerClavePrimaria
  
  @Override
  public int buscarDependencias(Cliente cliente) throws ExcepcionDao {
    int cantidadDependencias = 0;
    Optional<Integer> respuesta = null;

    respuesta = conexion.createQuery("SELECT count(\"Cliente\") FROM \"AsociacionGeorreferencia\""
      + " WHERE \"Cliente\" = :id ;")
      .bind("id", cliente.getId())
      .mapTo(Integer.class)
      .findFirst();
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    respuesta = conexion.createQuery("SELECT count(\"Cliente\") FROM \"Saldo\""
       + " WHERE \"Cliente\" = :id ;")
       .bind("id", cliente.getId())
       .mapTo(Integer.class)
       .findFirst();
    
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    respuesta = conexion.createQuery("SELECT count(\"Cliente\") FROM \"Cobranza\""
       + " WHERE \"Cliente\" = :id ;")
       .bind("id", cliente.getId())
       .mapTo(Integer.class)
       .findFirst();
    
    if (respuesta.isPresent()) {
      cantidadDependencias += respuesta.get();
    } //Fin de if

    return cantidadDependencias;
  } //Fin del metodo buscarDependencias

  /**
   * Busca un cliente que posea el mismo Ruc del cliente ingresado.
   * @param cliente el cliente con el ruc a buscar.
   * @return el cliente con el RUC ingresado.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public Cliente buscarRuc(Cliente cliente) throws ExcepcionDao {
    Cliente resultado = null;
    try {
      Optional<Cliente> respuesta = null; 
      respuesta = conexion.createQuery("SELECT * FROM \"Cliente\""
          + " WHERE \"RUC\" = :ruc ;")
          .bindBean(cliente)
          .map(mapeoBean)
          .findFirst();
      if (respuesta.isPresent()) {
        resultado = respuesta.get();
      } //Fin de if
    } catch (IllegalStateException  excepcion) {
      throw new ExcepcionDao("Cliente con RUC " + cliente.getRuc() 
         + " no encontrado. ", excepcion, this.log);
    } //Fin de try-catch

    return resultado;
  } //Fin del metodo buscarRuc
} //Fin de la clase ClienteDao