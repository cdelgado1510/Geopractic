package com.geopractic.administracion.mapeos;

import com.geopractic.administracion.dao.TelefonoDao;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.dao.TipoDocumentoDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import org.slf4j.Logger;

/**
 * Clase de mapeado para los objetos Cliente del sistema.
 * @author Cristhian
 */
public class ClienteMapeo implements RowMapper<Cliente> {

  private Handle conexion;
  private Logger logger;

  /**
   * Establece el handle con la conexion a ser utilizada por
   * la clase de mapeo.
   * @param conexion el handle con la conexino a establecer.
   * @param logger logger a establecer.
   */
  public ClienteMapeo(Handle conexion, Logger logger) {
    this.conexion = conexion;
    this.logger = logger;
  } //Fin de constructor con argumentos.

  @Override
  public Cliente map(ResultSet rs, StatementContext ctx) throws SQLException {
    Cliente cliente = new Cliente();
    cliente.setId(rs.getLong("Id"));
    cliente.setPrimerNombre(rs.getString("PrimerNombre"));
    cliente.setSegundoNombre(rs.getString("SegundoNombre"));
    cliente.setPrimerApellido(rs.getString("PrimerApellido"));
    cliente.setSegundoApellido(rs.getString("SegundoApellido"));
    cliente.setApellidoCasada(rs.getString("ApellidoCasada"));
    cliente.setNroDocumento(rs.getString("NroDocumento"));
    cliente.setRuc(rs.getString("RUC"));
    cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
    cliente.setVencimientoDocumento(rs.getDate("VencimientoDocumento"));
    cliente.setFechaCreacion(rs.getDate("FechaCreacion"));    
    cliente.setBloqueado(rs.getBoolean("Bloqueado"));

    try {
      //Obtiene el tipo de documento del cliente.
      TipoDocumentoDao daoTipoDoc = new TipoDocumentoDao();
      daoTipoDoc.setConexion(conexion);
      cliente.setTipoDocumento(daoTipoDoc.buscarId(rs.getLong("TipoDocumento")));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> ClienteMapeo - Error al mapear el tipo de documento del cliente.",
            excepcion);
    } //Fin de try -catch

    try {
      //Obtiene los numeros de telefono asociados al cliente.
      TelefonoDao daoTelefono = new TelefonoDao();
      daoTelefono.setConexion(conexion);
      cliente.setNumerosTelefonicos(daoTelefono.listarTelefonosCliente(cliente));
    } catch (ExcepcionDao excepcion) {
      logger.error("--> ClienteMapeo - Error al mapear los telefonos del cliente. ",
            excepcion);
    } //Fin de try -catch

    return cliente;
  } //Fin de mapeo

} //Fin de la clase ClienteMapeo