package com.geopractic.controladores;

import org.slf4j.Logger;

/**
 * Clase de excepcion de envoltura que maneja, agrupa y logea las excepciones
 * del manejo de conexions.
 * @author Cristhian
 */
public class ExcepcionControlador extends Exception {

  private static final long serialVersionUID = -6246021378872653794L;
  
  public ExcepcionControlador(String mensaje, Throwable causa,Logger logger) {
    super(mensaje, causa);
    logger.error(mensaje, causa);
  } //Fin del constructor

  public ExcepcionControlador(String mensaje,Logger logger) {
    super(mensaje);
    logger.error(mensaje);
  } //Fin del constructor

  public ExcepcionControlador(Throwable causa,Logger logger) {
    super(causa);
    logger.error(" Causa: ",causa);
  } //Fin del constructor

} //Fin de la clase ExcepcionControlador