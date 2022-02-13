package com.geopractic.propiedades;

import org.slf4j.Logger;

/**
 * Clase de excepcion de envoltura que maneja, agrupa y logea las excepciones
 * del paquete de propiedades.
 * @author Cristhian
 */
public class ExcepcionPropiedad extends Exception {

  private static final long serialVersionUID = 1982237314428658981L;

  public ExcepcionPropiedad(String mensaje, Throwable causa,Logger logger) {
    super(mensaje, causa);
    logger.error(mensaje, causa);
  } //Fin del constructor

  public ExcepcionPropiedad(String mensaje,Logger logger) {
    super(mensaje);
    logger.error(mensaje);
  } //Fin del constructor

  public ExcepcionPropiedad(Throwable causa,Logger logger) {
    super(causa);
    logger.error(" Causa: ",causa);
  } //Fin del constructor

} //Fin de la clase ExcepcionPropiedad