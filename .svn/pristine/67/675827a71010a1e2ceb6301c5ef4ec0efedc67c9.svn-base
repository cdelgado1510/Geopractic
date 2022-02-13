package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorLogs;
import com.geopractic.sistema.modelo.Logs;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


/**
 * Bean de administracion de menus del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "visorLogs")
@ViewScoped
public class VisualizacionLogs extends Bean<Logs> implements Serializable {

  private static final long serialVersionUID = 2536163598204719380L;

  /**
   * Constructor sin argumentos.
   */
  public VisualizacionLogs() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {

  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorLogs();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {

  }

  @Override
  public void modificar(ActionEvent actionEvent) {

  }

  @Override
  public void eliminar(Logs objetoSeleccionado) {

  }

} //Fin de la clase VisualizacionLogs