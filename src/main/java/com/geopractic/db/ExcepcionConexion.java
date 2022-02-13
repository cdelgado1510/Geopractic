package com.geopractic.db;

import org.slf4j.Logger;

/**
 * Clase de excepcion de envoltura que maneja, agrupa y logea las excepciones
 * del manejo de conexions.
 * @author Cristhian
 */
public class ExcepcionConexion extends Exception {

  private static final long serialVersionUID = 8498029442135303878L;

  public ExcepcionConexion(String mensaje, Throwable causa,Logger logger) {
    super(mensaje, causa);
    logger.error(mensaje, causa);
  } //Fin del constructor

  public ExcepcionConexion(String mensaje,Logger logger) {
    super(mensaje);
    logger.error(mensaje);
  } //Fin del constructor

  public ExcepcionConexion(Throwable causa,Logger logger) {
    super(causa);
    logger.error(" Causa: ",causa);
  } //Fin del constructor

} //Fin de la clase ExcepcionConexion