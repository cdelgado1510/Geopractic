package com.geopractic.georrefencia.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa una zona dentro de un mapa.
 * @author Cristhian
 *
 */
public class Zona {

  private List<Punto> puntos;
  private String puntosMakePoint;

  /**
   *Constructor sin argumentos.
   */
  public Zona() {

  } //Fin de constructor

  /**
   * Constructor que recibe una geometria de la base de datos.
   * @param geometria la geometria a convertir en puntos.
   */
  public Zona(String geometria) {
    puntos = new ArrayList<Punto>();
    //Elimina los caracteres de tupo
    String resultado = geometria.replace("POLYGON((", "");
    resultado = resultado.replace("))", "");

    //Separa todos los puntos de la linea
    String[] puntos = resultado.split(",");

    //Recorre todos los puntos.
    for (String string : puntos) {
      String[] coordenadas  = string.split(" ");
      Punto punto = new Punto(Double.parseDouble(coordenadas[1]),
              Double.parseDouble(coordenadas[0]));
      agregarPunto(punto);
    } //Fin de for
  } //Fin de constructor

  /**
   * Devuelve el listado de puntos de la zona.
   * @return el listado de puntos
   */
  public List<Punto> getPuntos() {
    return puntos;
  } //Fin del metodo getPuntos

  /**
   * Establece el listado de puntos de la zona.
   * @param puntos un listado de puntos.
   */
  public void setPuntos(List<Punto> puntos) {
    this.puntos = puntos;
  } //Fin del metodo setPuntos

  public String getPuntosMakePoint() {
    return puntosMakePoint;
  } //Fin del metodo getPuntosMakePoint

  public void setPuntosMakePoint(String puntosMakePoint) {
    this.puntosMakePoint = puntosMakePoint;
  } //Fin del metodo setPuntosMakePoint

  /**
   * Agrega un punto en blanco al poligono de la zona.
   */
  public void agregarPunto() {
    this.puntos.add(new Punto());
  } //Fin del metodo agregarPunto

  /**
   * Agrega un punto al poligono de la zona con su latitud
   * y longitud.
   * @param lat latitud del punto a generar.
   * @param lon longitud del punto a generar.
   */
  public void agregarPunto(double lat, double lon) {
    this.puntos.add(new Punto(lat, lon));
  } //Fin del metodo agregarPunto

  /**
   * Agrega un punto al poligono de la zona.
   * @param punto el punto nuevo a agregar.
   */
  public void agregarPunto(Punto punto) {
    this.puntos.add(punto);
  } //Fin del metodo agregarPunto

  /**
   * Convierte el punto en formato WKT.
   * @return el punto en formato WKT.
   */
  public String convertirWkt() {
    StringBuilder builder = new StringBuilder("LINESTRING(");

    Punto primerPunto = puntos.get(0); //Obtiene el primer punto

    //Agrega los puntos
    for (Iterator<Punto> iterator = puntos.iterator(); iterator.hasNext();) {
      Punto punto = iterator.next();
      builder.append(punto.getLongitud());
      builder.append(" ");
      builder.append(punto.getLatitud());
      builder.append(", ");
    } //Fin de for

    //Agrega el primer punto para cerrar el poligono.
    builder.append(primerPunto.getLongitud());
    builder.append(" ");
    builder.append(primerPunto.getLatitud());
    builder.append(")");
    return builder.toString();
  } //Fin del metodo convertirWkt

  /**
   * Convierte el los puntos en MakePoint.
   */
  public void convertirMakePoint() {
    StringBuilder builder = new StringBuilder();

    builder.append("LINESTRING(");
    Punto primerPunto = puntos.get(0); //Obtiene el primer punto

    //Agrega los puntos
    for (Iterator<Punto> iterator = puntos.iterator(); iterator.hasNext();) {
      Punto punto = iterator.next();
      builder.append(punto.getLongitud());
      builder.append(" ");
      builder.append(punto.getLatitud());
      builder.append(", ");
    } //Fin de for

    //Agrega el primer punto para cerrar el poligono.
    builder.append(primerPunto.getLongitud());
    builder.append(" ");
    builder.append(primerPunto.getLatitud());
    builder.append(")");
    puntosMakePoint = builder.toString();
  } //Fin del metodo convertirWkt

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Zona [");

    //Imprime el listado completo de puntos
    for (Punto punto : puntos) {
      builder.append("Y=");
      builder.append(punto.getLatitud());
      builder.append(" X=");
      builder.append(punto.getLongitud());
      builder.append(" , ");
    } //Fin de for each
    builder.append("]");
    return builder.toString();
  }

} //Fin del metodo Zona