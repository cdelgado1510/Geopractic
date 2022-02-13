package com.geopractic.sistema.modelo;

import java.util.Date;

public class Logs {

  private long id;
  private Date fecha;
  private String logger;
  private String nivel;
  private String mensaje;
  private String excepcion;

  public Logs() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getLogger() {
    return logger;
  }

  public void setLogger(String logger) {
    this.logger = logger;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getExcepcion() {
    return excepcion;
  }

  public void setExcepcion(String excepcion) {
    this.excepcion = excepcion;
  }

} //Fin de la clase logs