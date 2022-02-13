package com.geopractic.sistema.modelo;

import java.util.Comparator;

import java.util.List;

/**
 * Rol de acceso asignado al usuario.
 * @author Cristhian
 */
public class Rol {

  private Long id;
  private String descripcion;
  private List<Ventana> ventanas;
  private List<Menu> menus;
  
  /**
   * Constructor sin argumentos.
   */
  public Rol() {}
  
  /**
   * Devuelve el id.
   * @return el id del rol.
   */
  public Long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el id.
   * @param id el id a establecer.
   */
  public void setId(Long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la descripcion del rol.
   * @return la descripcion
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion del rol.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  /**
   * Devuelve el listado de ventanas del rol.
   * @return el listado de ventanas.
   */
  public List<Ventana> getVentanas() {
    return ventanas;
  } //Fin del metodo getVentanas

  /**
   * Establece el listado de ventanas del rol.
   * @param ventanas el listado de ventanas a establecer.
   */
  public void setVentanas(List<Ventana> ventanas) {
    this.ventanas = ventanas;
  } //Fin del metodo setVentanas

  /**
   * Devuelve el listado de menus del rol.
   * @return el listado de menus.
   */
  public List<Menu> getMenus() {
    return menus;
  } //Fin del metodo getMenus

  /**
   * Establece el listado de menus del rol.
   * @param menus el listado de menus a establecer.
   */
  public void setMenus(List<Menu> menus) {
    this.menus = menus;
  } //Fin del metodo setMenus
  
  /**
   * Verifica si la ventana se encuentra dentro del listado de
   * ventanas del rol.
   * @param ventana la ventana a buscar.
   * @return el resultado de la busqueda.
   */
  public boolean contieneVentana(Ventana ventana) {
    return this.ventanas.contains(ventana);
  } //Fin del metodo contienenVentana
  
  /**
   * Busca y devuelve la ventana especificada dentro de las ventanas
   * asignadas al rol.
   * @param ventana la ventana a buscar.
   * @return la ventana buscada.
   */
  public Ventana buscarVentana(Ventana ventana) {
    if (contieneVentana(ventana)) {
      return this.ventanas.get(this.ventanas.indexOf(ventana));
    } //Fin de if
    return null;
  } //Fin del metodo buscarVentana
  
  /**
   * Agrega una ventana dentro del rol.
   * @param ventana la ventana a agregar.
   */
  public void agregarVentana(Ventana ventana) {
    this.ventanas.add(ventana);
  } //Fin de metodo agregarVentana
  
  /**
   * Elimina una ventana dentro del rol.
   * @param ventana la ventana a eliminar.
   */
  public void eliminarVentana(Ventana ventana) {
    if (contieneVentana(ventana)) {
      this.ventanas.remove(ventana);
    } //Fin de if
  } //Fin de metodo eliminarVentana
  
  /**
   * Verifica si el menu se encuentra dentro del listado de
   * menus del rol.
   * @param menu el menu a buscar.
   * @return el resultado de la busqueda.
   */
  public boolean contieneMenu(Menu menu) {
    return this.menus.contains(menu);
  } //Fin del metodo contieneMenu
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return id + " - " + descripcion;
  } //Fin del metodo toString()

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  } //Fin de hashCode

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object objeto) {
    if (this == objeto) {
      return true;
    } //Fin de if
    if (objeto == null) {
      return false;
    } //Fin de if
    if (!(objeto instanceof Rol)) {
      return false;
    } //Fin de if
    Rol otroRol = (Rol) objeto;
    if (descripcion == null) {
      if (otroRol.descripcion != null) {
        return false;
      } //Fin de if interno
    } else if (!descripcion.equals(otroRol.descripcion)) {
      return false;
    } //Fin de else if
    return true;
  } //Fin del metodo equals 

  /**
   * Comparador de ordenamiento ascendente por descripcion de la ventana
   * de interfaz gráfica.
   */
  public static Comparator<Ventana> ComparadorDescripcionGui = new Comparator<Ventana>() {

    public int compare(Ventana ventana1, Ventana ventana2) {
      String descripcion1 = ventana1.getNombre().toUpperCase();
      String descripcion2 = ventana2.getNombre().toUpperCase();

      return descripcion1.compareTo(descripcion2);
    }
  }; //Fin del ComparadorNombreGui

} //Fin de la clase Rol