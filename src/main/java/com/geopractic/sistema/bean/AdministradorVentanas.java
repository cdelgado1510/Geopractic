package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorVentana;
import com.geopractic.sistema.modelo.Ventana;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de ventanas del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "manejarVentana")
@ViewScoped
public class AdministradorVentanas extends Bean<Ventana> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor sin argumentos.
   */
  public AdministradorVentanas() {

  } //Fin de constructor

  /**
   * Procedimientos realizados al iniciar el bean.
   */
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Ventana();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorVentana();
  } //Fin del metodo prepararControlador

  /**
   *  Guarda una ventana nueva dentro del sistema.
   */
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando ventana nueva");
    try {
      boolean resultado = controlador.guardar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Ventana guardado", "Nueva ventana guardada.",null);
        lista.add(entidad);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "La ventana nueva no pudo ser guardada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar la ventana ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La ventana nueva no pudo ser guardada.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase guardar

  /**
   *  Modifica una ventana del sistema.
   */
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios de ventana guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar la ventana ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Los cambios no pudieron ser guardados.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- modificar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase modificar

  /**
   *  Elimina una ventana del sistema.
   */
  public void eliminar(Ventana seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando ventana");
    this.entidad = seleccion;
    try {
      boolean resultado = controlador.eliminar(entidad);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Ventana eliminada", "La ventana a sido eliminada.",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "La ventana no pudo ser eliminada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar la ventana ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La ventana no pudo ser eliminada.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrandor recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministradorVentanas