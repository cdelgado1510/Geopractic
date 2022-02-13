package com.geopractic.georrefencia.modelo;

import com.geopractic.gps.modelo.Rastreador;

import java.util.Comparator;

public class Mapa {

  private long id;
  private boolean verZonas;
  private Rastreador rastreadorGps;
  private long panel;

  /**
   * Constructor sin argumentos.
   */
  public Mapa() {

  } //Fin de constructor

  /**
   * Devuelve el identificador del mapa.
   * @return el identificador del mapa.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del mapa.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve si se desean visualizar las zonas en el mapa.
   * @return si se ven las zonas o no.
   */
  public boolean isVerZonas() {
    return verZonas;
  } //Fin del metodo isVerZonas

  /**
   * Establece si se visualizan las zonas o no.
   * @param verZonas true o false para visualizar las zonas.
   */
  public void setVerZonas(boolean verZonas) {
    this.verZonas = verZonas;
  } //Fin del metodo setVerZonas

  /**
   * Devuelve el rastreador GPS asociado con el mapa.
   * @return el rastreador GPS asociado con el mapa
   */
  public Rastreador getRastreadorGps() {
    return rastreadorGps;
  } //Fin del metodo getRastreadorGps

  /**
   * Establece el rastreador GPS asociado al mapa.
   * @param rastreadorGps el rastreador a asignar.
   */
  public void setRastreadorGps(Rastreador rastreadorGps) {
    this.rastreadorGps = rastreadorGps;
  } //Fin del metodo setRastreadorGps

  /**
   * Devuelve el panel asociado al mapa.
   * @return el codigo identificador del panel.
   */
  public long getPanel() {
    return panel;
  } //Fin del metodo getPanel

  /**
   * Establece el panel asociado al mapa.
   * @param panel el codigo identificador del panel. 
   */
  public void setPanel(long panel) {
    this.panel = panel;
  } //Fin del metodo setPanel

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((rastreadorGps == null) ? 0 : rastreadorGps.hashCode());
    return result;
  } //Fin del hashCode 

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof Mapa)) {
      return false;
    } //Fin de if
    Mapa other = (Mapa) obj;
    if (rastreadorGps == null) {
      if (other.rastreadorGps != null) {
        return false;
      } //Fin de if
    } else if (!rastreadorGps.equals(other.rastreadorGps)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin del equals 

  @Override
  public String toString() {
    return id + " Panel: " + panel + " - " + rastreadorGps;
  } //Fin de toString

  /**
   * Comparador de ordenamiento ascendente por identificador.
   */
  public static Comparator<Mapa> ordenarPorId = new Comparator<Mapa>() {
    public int compare(Mapa mapa1, Mapa mapa2) {
      return (int) (mapa1.getId() - mapa2.getId());
    } //Fin de compare
  }; //Fin del ordenarPorId

} //Fin de la clase Mapa