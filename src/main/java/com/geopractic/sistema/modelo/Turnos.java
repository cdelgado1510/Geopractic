package com.geopractic.sistema.modelo;

import java.util.Date;

/**
 * Representa los turnos utilizados por el sistema.
 * @author Cristhian
 *
 */
public class Turnos {

  private long id;
  private String descripcion;
  private Date horaInicio;
  private Date horaFin;
  private boolean domingo;
  private boolean lunes;
  private boolean martes;
  private boolean miercoles;
  private boolean jueves;
  private boolean viernes;
  private boolean sabado;

  /**
   * Constructor sin argumentos.
   */
  public Turnos() {

  } //Fin del constructor 

  public long getId() {
    return id;
  } //Fin del metodo getId

  public void setId(long id) {
    this.id = id;
  } //Fin del metodo setId

  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  public Date getHoraInicio() {
    return horaInicio;
  } //Fin del metodo getHoraInicio

  public void setHoraInicio(Date horaInicio) {
    this.horaInicio = horaInicio;
  } //Fin del metodo setHoraInicio

  public Date getHoraFin() {
    return horaFin;
  } //Fin del metodo getHoraFin

  public void setHoraFin(Date horaFin) {
    this.horaFin = horaFin;
  } //Fin del metodo setHoraFin

  public boolean isDomingo() {
    return domingo;
  } //Fin del metodo isDomingo

  public void setDomingo(boolean domingo) {
    this.domingo = domingo;
  } //Fin del metodo setDomingo

  public boolean isLunes() {
    return lunes;
  } //Fin del metodo isLunes

  public void setLunes(boolean lunes) {
    this.lunes = lunes;
  } //Fin del metodo setLunes

  public boolean isMartes() {
    return martes;
  } //Fin del metodo isMartes

  public void setMartes(boolean martes) {
    this.martes = martes;
  } //Fin del metodo setMartes

  public boolean isMiercoles() {
    return miercoles;
  } //Fin del metodo isMiercoles

  public void setMiercoles(boolean miercoles) {
    this.miercoles = miercoles;
  } //Fin del metodo setMiercoles

  public boolean isJueves() {
    return jueves;
  } //Fin del metodo isJueves

  public void setJueves(boolean jueves) {
    this.jueves = jueves;
  } //Fin del metodo setJueves

  public boolean isViernes() {
    return viernes;
  } //Fin del metodo isViernes

  public void setViernes(boolean viernes) {
    this.viernes = viernes;
  } //Fin del metodo setViernes

  public boolean isSabado() {
    return sabado;
  } //Fin del metodo isSabado

  public void setSabado(boolean sabado) {
    this.sabado = sabado;
  } //Fin del metodo setSabado

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (domingo ? 1231 : 1237);
    result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
    result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
    result = prime * result + (jueves ? 1231 : 1237);
    result = prime * result + (lunes ? 1231 : 1237);
    result = prime * result + (martes ? 1231 : 1237);
    result = prime * result + (miercoles ? 1231 : 1237);
    result = prime * result + (sabado ? 1231 : 1237);
    result = prime * result + (viernes ? 1231 : 1237);
    return result;
  } //Fin de hashCode

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Turnos)) {
      return false;
    }
    Turnos other = (Turnos) obj;
    if (domingo != other.domingo) {
      return false;
    }
    if (horaFin == null) {
      if (other.horaFin != null) {
        return false;
      }
    } else if (!horaFin.equals(other.horaFin)) {
      return false;
    }
    if (horaInicio == null) {
      if (other.horaInicio != null) {
        return false;
      }
    } else if (!horaInicio.equals(other.horaInicio)) {
      return false;
    }
    if (jueves != other.jueves) {
      return false;
    }
    if (lunes != other.lunes) {
      return false;
    }
    if (martes != other.martes) {
      return false;
    }
    if (miercoles != other.miercoles) {
      return false;
    }
    if (sabado != other.sabado) {
      return false;
    }
    if (viernes != other.viernes) {
      return false;
    }
    return true;
  } //Fin de equals

  @Override
  public String toString() {
    return id + " - " + descripcion + " Inicio: " + horaInicio + " - Fin: " + horaFin;
  } //Fin de toString

} //Fin de la clase Turnos