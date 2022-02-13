package com.geopractic.util;

import org.slf4j.Logger;

/**
 * Clase de excepcion de envoltura que maneja, agrupa y logea las excepciones
 * de los Utilidades implementados.
 * @author Cristhian
 */
public class ExcepcionUtilidades extends Exception {

  private static final long serialVersionUID = 7637239934357542310L;

  public ExcepcionUtilidades(String mensaje, Throwable causa,Logger logger) {
    super(mensaje, causa);
    logger.error(mensaje, causa);
  } //Fin del constructor

  public ExcepcionUtilidades(String mensaje,Logger logger) {
    super(mensaje);
    logger.error(mensaje);
  } //Fin del constructor

  public ExcepcionUtilidades(String mensaje, Throwable causa) {
    super(mensaje, causa);
  } //Fin del constructor
  
  public ExcepcionUtilidades(Throwable causa,Logger logger) {
    super(causa);
    logger.error(" Causa: ",causa);
  } //Fin del constructor

} //Fin de la clase ExcepcionUtilidades