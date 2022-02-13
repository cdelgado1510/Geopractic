package com.geopractic.georrefencia.modelo;

/**
 * Clase que representa cada punto de georreferencia.
 * @author Cristhian
 *
 */
public class Punto {

  private double latitud;
  private double longitud;

  /**
   * Constructor sin argumentos.
   */
  public Punto() {

  } //Fin de constructor
  
  /**
   * Constructor con argumentos.
   * @param latitud la latitud del punto.
   * @param longitud la longitud del punto.
   */
  public Punto(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  } //Fin de constructor

  /**
   * Devuelve la latitud(y) del punto.
   * @return la latitud del punto
   */
  public double getLatitud() {
    return latitud;
  } //Fin del metodo getLatitud

  /**
   * Establece la coordenada de latitud del punto.
   * @param latitud la coordenada de latitud a establecer.
   */
  public void setLatitud(double latitud) {
    this.latitud = latitud;
  } //Fin del metodo setLatitud

  /**
   * Devuelve la longitud(x) del punto.
   * @return la longitud del punto
   */
  public double getLongitud() {
    return longitud;
  } //Fin del metodo getLongitud

  /**
   * Establece la coordenada de longitud del punto.
   * @param longitud la coordenada de longitud a establecer.
   */
  public void setLongitud(double longitud) {
    this.longitud = longitud;
  } //Fin del metodo setLongitud

  /**
   * Obtiene la latitud y longitud de un punto geometrico.
   * @param geometria la geometria a extraer.
   */
  public void extraerLatLon(String geometria) {
    String resultado = geometria.replace("POINT(","");
    resultado = resultado.replace(")", "");
    String[] coordenadas = resultado.split(" ");
    setLongitud(Double.parseDouble(coordenadas[0]));
    setLatitud(Double.parseDouble(coordenadas[1]));
  } //Fin del metodo extraerLatLon

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(latitud);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(longitud);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  } //Fin de hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof Punto)) {
      return false;
    } //Fin de if
    Punto other = (Punto) obj;
    if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud)) {
      return false;
    }  //Fin de if
    if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud)) {
      return false;
    } //Fin de if
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return "Punto [" + latitud + ", " + longitud + "]";
  } //Fin de toString

} //Fin de clase Punto