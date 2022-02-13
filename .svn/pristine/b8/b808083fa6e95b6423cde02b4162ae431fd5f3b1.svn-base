package com.geopractic.georrefencia.modelo;

/**
 * Clase que representa los tipos de georreferencias cargados
 * dentro del sistema.
 * @author Cristhian
 *
 */
public class TipoGeorreferencia {

  private long id;
  private String descripcion;
  private String clase;
  private String icono;
  private String colorContorno;
  private String colorRelleno;
  private boolean activo;

  /**
   * Constructor sin argumentos.
   */
  public TipoGeorreferencia() {

  } //Fin de constructor

  /**
   * Devuelve el identificador del tipo.
   * @return el identificador del tipo.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del tipo.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la descripcion del tipo.
   * @return la descripcion del tipo.
   */
  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  /**
   * Establece la descripcion del tipo.
   * @param descripcion la descripcion a establecer.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  /**
   * Devuelve si el tipo es para un punto o una zona.
   * @return si es un punto o una zona.
   */
  public String getClase() {
    return clase;
  } //Fin del metodo getClase

  /**
   * Establece si es un punto o una zona. 
   * @param clase Punto o Zona.
   */
  public void setClase(String clase) {
    this.clase = clase;
  } //Fin del metodo setClase

  /**
   * Devuelve la direccion del icono utilizado.
   * @return la direccion del icono utilizado.
   */
  public String getIcono() {
    return icono;
  } //Fin del metodo getIcono

  /**
   * Establece la direccion del icono a utilizar.
   * @param icono la direccion del icono a establecer.
   */
  public void setIcono(String icono) {
    if (this.clase.equals("Zona")) {
      this.icono = "marker-vacio.png";
    } else {
      this.icono = icono;
    } //Fin de if
  } //Fin del metodo setIcono

  /**
   * Devuelve el codigo de color de contorno utilizado en las zonas.
   * @return el codigo del color del contorno a utilizar.
   */
  public String getColorContorno() {
    return colorContorno;
  } //Fin del metodo getColorContorno
  
  /**
   * Establece el codigo del contorno a utilizar en las zonas.
   * @param colorContorno el codigo del color a utilizar.
   */
  public void setColorContorno(String colorContorno) {
    if (this.clase.equals("Punto")) {
      this.colorContorno = "ffffff";
    } else {
      this.colorContorno = colorContorno;
    } //Fin de if
  } //Fin del metodo setColorContorno

  /**
   * Devuelve el color de relleno a utilizar en las zonas.
   * @return el codigo del color de relleno a utilizar.
   */
  public String getColorRelleno() {
    return colorRelleno;
  } //Fin del metodo getColorRelleno

  /**
   * Establece el codigo de color de relleno a utilizar en las zonas.
   * @param colorRelleno el codigo del color a utilizar.
   */
  public void setColorRelleno(String colorRelleno) {
    if (clase.equals("Punto")) {
      this.colorRelleno = "ffffff";
    } else {
      this.colorRelleno = colorRelleno;
    } //Fin de if
  } //Fin del metodo setColorRelleno

  /**
   * Devuelve si el tipo se encuentra activo. 
   * @return si esta activo o no.
   */
  public boolean isActivo() {
    return activo;
  } //Fin del metodo isActivo

  /**
   * Establece si el topo se encuentra activo.
   * @param activo si se encuentra activo o no.
   */
  public void setActivo(boolean activo) {
    this.activo = activo;
  } //Fin del metodo setActivo

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clase == null) ? 0 : clase.hashCode());
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
    if (!(obj instanceof TipoGeorreferencia)) {
      return false;
    } //Fin de if
    TipoGeorreferencia other = (TipoGeorreferencia) obj;
    if (clase == null) {
      if (other.clase != null) {
        return false;
      } //Fin de if
    } else if (!clase.equals(other.clase)) {
      return false;
    } //Fin de if-else
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
    return clase + " - " + descripcion;
  } //Fin del toString

} //Fin de la clase TipoGeorreferencia