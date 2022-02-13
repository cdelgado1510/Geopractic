package com.geopractic.sistema.modelo;

/**
 * Clase que representa un menu dentro del sistema.
 * @author Cristhian
 *
 */
public class Menu {

  private long id;
  private String nombre;
  private String tipo;
  private long menuPadre;
  private String icono;
  private String ventana;
  private int orden;
  private boolean activo;

  /**
   * Constructor sin argumentos.
   */
  public Menu() {
 
  } //Fin de constructor
  
  /**
   * Devuelve el id del menu.
   * @return el id del menu.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el id del menu.
   * @param id el id a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve el nombre del menu.
   * @return el nombre del menu.
   */
  public String getNombre() {
    return nombre;
  } //Fin del metodo getNombre

  /**
   * Establece el nombre del menu.
   * @param nombre el nombre a establecer.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  } //Fin del metodo setNombre

  /**
  * Devuelve el tipo de menu.
  * @return el tipo de menu
  */
  public String getTipo() {
    return tipo;
  } //Fin del metodo getTipo

  /**
   * Establece el tipo de menu.
   * @param tipo el tipo a establecer.
   */
  public void setTipo(String tipo) {
    this.tipo = tipo;
  } //Fin del metodo setTipo

  /**
   * Devuelve de el menu padre de menu actual.
   * @return el menu padre.
   */
  public long getMenuPadre() {
    return menuPadre;
  } //Fin del metodo getMenuPadre

  /**
   * Establece el menu padre del menu actual. 
   * @param menuPadre el menu padre
   */
  public void setMenuPadre(long menuPadre) {
    this.menuPadre = menuPadre;
  } //Fin del metodo setMenuPadre

  /**
   * Devuelve la direccion del icono utilizado por el menu.
   * @return el icono utilizado
   */
  public String getIcono() {
    return icono;
  } //Fin del metodo getIcono

  /**
   * Establece la direccion del icono utilizado por el menu.
   * @param icono la direccion del icono.
   */
  public void setIcono(String icono) {
    this.icono = icono;
  } //Fin del metodo setIcono

  /**
   * Devuelve la ventana asociada al menu.
   * @return la ventana del menu.
   */
  public String getVentana() {
    return ventana;
  } //Fin del metodo getVentana

  /**
   * Establece la ventana asociada al menu.
   * @param ventana el nombre de la ventana a establecer
   */
  public void setVentana(String ventana) {
    this.ventana = ventana;
  } //Fin del metodo setVentana

  /**
   * Devuelve el orden del menu.
   * @return el numero de orden del menu
   */
  public int getOrden() {
    return orden;
  } //Fin del metodo getOrden

  /**
   * Establece el numero de orden del menu.
   * @param orden el orden a establecer.
   */
  public void setOrden(int orden) {
    this.orden = orden;
  } //Fin del metodo setOrden

  /**
   * Devuelve si el menu se encuentra activo.
   * @return si se encuentra activo.
   */
  public boolean isActivo() {
    return activo;
  } //Fin del metodo isActivo

  /**
   * Establece si el menu se encuentra activo.
   * @param activo el valor true o false.
   */
  public void setActivo(boolean activo) {
    this.activo = activo;
  } //Fin del metodo setActivo



  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return nombre;
  } //Fin del metodo toString

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
    result = prime * result + ((ventana == null) ? 0 : ventana.hashCode());
    return result;
  } //Fin del metodo hashCode

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof Menu)) {
      return false;
    } //Fin de if
    Menu other = (Menu) obj;
    if (nombre == null) {
      if (other.nombre != null) {
        return false;
      } //Fin de if
    } else if (!nombre.equals(other.nombre)) {
      return false;
    } //Fin de if-else
    if (tipo == null) {
      if (other.tipo != null) {
        return false;
      } //Fin de if interno
    } else if (!tipo.equals(other.tipo)) {
      return false;
    } //Fin de if-else
    if (ventana == null) {
      if (other.ventana != null) {
        return false;
      } //Fin de if interno
    } else if (!ventana.equals(other.ventana)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin del metodo equals
  
} //Fin de la clase Menu