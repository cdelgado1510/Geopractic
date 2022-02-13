package com.geopractic.cobranza.modelo;

import java.util.Date;

public class RuteoCobrador {

  private long id;
  private Date fechaVisita;
  private boolean visitado;
  private boolean verificado;
  private GestionVisita gestion;
  private boolean modificadoCumplido;
  private Date fechaCreacion;
  private Date fechaCumplido;

  /**
   * Constructor sin argumentos.
   */
  public RuteoCobrador() {
    setModificadoCumplido(false);
  } //Fin del constructor sin argumentos

  /**
   * Devuelve el identificador del ruteo.
   * @return el identificador del ruteo.
   */
  public long getId() {
    return id;
  } //Fin del metodo getId

  /**
   * Establece el identificador del ruteo.
   * @param id el identificador a establecer.
   */
  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  /**
   * Devuelve la fecha de visita del ruteo.
   * @return la fecha de visita del ruteo.
   */
  public Date getFechaVisita() {
    return fechaVisita;
  } //Fin del metodo getFechaVisita

  /**
   * Establece la fecha de visita del ruteo.
   * @param fechaVisita la fecha de visita a establecer.
   */
  public void setFechaVisita(Date fechaVisita) {
    this.fechaVisita = fechaVisita;
  } //Fin del metodo setFechaVisita

  /**
   * Devuelve si el ruteo fue visitado o no.
   * @return si el ruteo fue visitado o no.
   */
  public boolean isVisitado() {
    return visitado;
  } //Fin del metodo getVisitado

  /**
   * Establece si el ruteo fue visitado o no.
   * @param visitado si o no a establecer.
   */
  public void setVisitado(boolean visitado) {
    this.visitado = visitado;
  } //Fin del metodo setVisitado

  /**
   * Devuelve si fue verificado el ruteo o no.
   * @return si fue verificado el ruteo o no.
   */
  public boolean isVerificado() {
    return verificado;
  } //Fin del metodo isVerificado

  /**
   * Establece la verificacion del ruteo.
   * @param verificado si o no a establecer.
   */
  public void setVerificado(boolean verificado) {
    this.verificado = verificado;
  } //Fin del metodo setVerificado

  /**
   * Devuelve si fue modificado el campo cumplido.
   * @return si fue modificado el campo cumplido.
   */
  public boolean isModificadoCumplido() {
    return modificadoCumplido;
  } //Fin del metodo isModificadoCumplido

  /**
   * Establece si fue modificado o no el campo cumlido. 
   * @param modificadoCumplido si fue o no modificado.
   */
  public void setModificadoCumplido(boolean modificadoCumplido) {
    this.modificadoCumplido = modificadoCumplido;
  } //Fin del metodo setModificadoCumplido

  /**
   * Devuelve la gestion asociada al ruteo.
   * @return la gestion asociada al ruteo.
   */
  public GestionVisita getGestion() {
    return gestion;
  } //Fin del metodo getGestion

  /**
   * Establece la gestion asociada al ruteo.
   * @param gestion la gestion a establecer.
   */
  public void setGestion(GestionVisita gestion) {
    this.gestion = gestion;
  } //Fin del metodo setGestion

  /**
   * Devuelve la fecha de creación del ruteo.
   * @return la fecha y hora de creacion del ruteo.
   */
  public Date getFechaCreacion() {
    return fechaCreacion;
  } //Fin del metodo getFechaCreacion

  /**
   * Establece la fecha de creacion del ruteo.
   * @param fechaCreacion la fecha de creacion a establecer.
   */
  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  } //Fin del metodo setFechaCreacion

  /**
   * Devuelve la fecha de cumplimiento del ruteo.
   * @return la fecha de cumplido del ruteo.
   */
  public Date getFechaCumplido() {
    return fechaCumplido;
  } //Fin del metodo fechaCumplido

  /**
   * Establece la fecha de cumplimiento del ruteo.
   * @param fechaCumplido la fecha de cumplimiento a establecer.
   */
  public void setFechaCumplido(Date fechaCumplido) {
    this.fechaCumplido = fechaCumplido;
  } //Fin del metodo setFechaCumplido

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((gestion == null) ? 0 : gestion.hashCode());
    return result;
  } //Fin del metodo hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } //Fin de if
    if (obj == null) {
      return false;
    } //Fin de if
    if (!(obj instanceof RuteoCobrador)) {
      return false;
    } //Fin de if
    RuteoCobrador other = (RuteoCobrador) obj;
    if (gestion == null) {
      if (other.gestion != null) {
        return false;
      } //Fin de if
    } else if (!gestion.equals(other.gestion)) {
      return false;
    } //Fin de if-else
    return true;
  } //Fin del metodo equals

  @Override
  public String toString() {
    return id + " Fecha Visita: " + fechaVisita + " Empleado: " 
          + gestion.getEmpleadoVisita().getNombre() + " " 
          + gestion.getEmpleadoVisita().getApellido() + " Saldo: " + gestion.getSaldo().getId();
  } //Fin de toString

} //Fin de la clase RuteoCobrador