package com.geopractic.georrefencia.modelo;

import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.sistema.modelo.Turnos;

import java.util.List;

/**
 * Clase que representa cada panel de visualizacion. 
 * @author Cristhian
 *
 */
public class PanelVisualizacion {

  private long id;
  private String descripcion;
  private int intervaloRefresco;
  private Empleado empleadoCreador;
  private List<Mapa> listaMapas;
  private Turnos turno;

  /**
   * Constructor sin argumentos.
   */
  public PanelVisualizacion() {

  } //Fin de constructor

  /**
   * Devuelve el identificador del panel.
   * @return el identificador del panel.
   */
  public long getId() {
    return id;
  } //Fin de metodo getId

  /**
   * Establece el identificador unico del panel.
   * @param id el identificador del panel.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin de metodo setId

  /**
   * Devuelve la descripcion del panel.
   * @return the descripcion
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin de metodo getDescripcion

  /**
   * Establece la descripcion del panel.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin de metodo setDescripcion

  /**
   * Devuelve el intervalo de refresco del panel.
   * @return el intervalo de refresco en segundos del panel.
   */
  public int getIntervaloRefresco() {
    return intervaloRefresco;
  } //Fin de metodo getIntervaloRefresco

  /**
   * Establece el intervalo de refresco del panel.
   * @param intervaloRefresco el intervalo a establecer.
   */
  public void setIntervaloRefresco(int intervaloRefresco) {
    this.intervaloRefresco = intervaloRefresco;
  } //Fin de metodo setIntervaloRefresco

  /**
   * Devuelve el empleado que creo el panel.
   * @return el empleado que creo el panel
   */
  public Empleado getEmpleadoCreador() {
    return empleadoCreador;
  } //Fin de metodo getEmpleadoCreador

  /**
   * Establece el empleado que creo el panel. 
   * @param empleadoCreador el empleado creador del panel.
   */
  public void setEmpleadoCreador(Empleado empleadoCreador) {
    this.empleadoCreador = empleadoCreador;
  } //Fin de metodo setEmpleadoCreador

  /**
   * Devuelve el turno asigando al panel.
   * @return el turno de visualizacion del panel.
   */
  public Turnos getTurno() {
    return turno;
  } //Fin del metodo getTurno

  /**
   * Establece el turno de visualizacion del panel.
   * @param turno el turno de visualizacion del panel.
   */
  public void setTurno(Turnos turno) {
    this.turno = turno;
  } //Fin del metodo setTurno

  /**
   * Devuelve el listado de mapas del panel.
   * @return el listado de mapas del panel.
   */
  public List<Mapa> getListaMapas() {
    return listaMapas;
  } //Fin del metodo getListaMapas

  /**
   * Establece al lista de mapas del panel.
   * @param listaMapas la lista de mapas a establecer.
   */
  public void setListaMapas(List<Mapa> listaMapas) {
    this.listaMapas = listaMapas;
  } //Fin del metodo setListaMapas

  /**
   * Agrega un mapa nuevo al listado de mapas.
   * @param mapa el mapa a agregar.
   */
  public void agregarMapa(Mapa mapa) {
    this.listaMapas.add(mapa);
  } //Fin del metodo agregarMapa

  /**
   * Elimina un mapa del listado de mapas.
   * @param mapa el mapa a eliminar.
   */
  public void eliminarMapa(Mapa mapa) {
    this.listaMapas.remove(mapa);
  } //Fin del metodo eliminarMapa

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
    if (!(obj instanceof PanelVisualizacion)) {
      return false;
    } //Fin de if
    PanelVisualizacion other = (PanelVisualizacion) obj;
    if (descripcion == null) {
      if (other.descripcion != null) {
        return false;
      } //Fin de if
    } else if (!descripcion.equals(other.descripcion)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin de equals  

  @Override
  public String toString() {
    return id + " " + descripcion;
  } //Fin de toString 

} //Fin de la clase PanelVisualizacion