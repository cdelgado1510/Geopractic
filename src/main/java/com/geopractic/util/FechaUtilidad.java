package com.geopractic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Clase que con funciones para la convercion de datos UnixEpoch.
 * @author Cristhian
 *
 */
public class FechaUtilidad {

  /**
   * Convierte una fecha ingresada en formato epoca.
   * @param desde la fecha a convertir.
   * @return el numero que representa la fecha ingresada en epoca.
   * @throws ParseException error al parsear la fecha introducida.
   */
  public static long convertirFechaEnEpoca(Date desde) throws ParseException {
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    formato.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
    long epoca = formato.parse(formato.format(desde)).getTime() / 1000;  
    return epoca;
  } //Fin del metodo convertirFechaEnEpoca

  /**
   * Convierte un numero de epoca ingresada en formato de fecha actual.
   * @param epoca la epoca a convertir en fecha.
   * @return la fecha extraida de la epoca.
   * @throws ParseException error al parsear la fecha introducida.
   */
  public static Date convertirEpocaEnFecha(long epoca) throws ParseException {
    String fecha = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
        .format(new java.util.Date(epoca * 1000));
    return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(fecha);
  } //Fin del metodo convertirEpocaEnFecha

} //Fin de la clase FechaUtilidad