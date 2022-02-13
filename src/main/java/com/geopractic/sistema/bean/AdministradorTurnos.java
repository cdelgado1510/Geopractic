package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorTurnos;
import com.geopractic.sistema.modelo.Turnos;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de turnos del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarTurno")
@ViewScoped
public class AdministradorTurnos extends Bean<Turnos> {

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorTurnos() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Turnos();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorTurnos();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando turno nuevo");
    try {
      //Verifica si no existe el horario ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El turno ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El turno ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Turno guardado", "Nuevo turno guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El nuevo turno no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Turno ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El nuevo turno no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase guardar
  
  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios del turno guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Turno. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Los cambios no pudieron ser guardados.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- modificar() - Error cerrar recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase modificar

  @Override
  public void eliminar(Turnos seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando turno");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El turno ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El turno " + entidad.getId()
              + "ya tiene operacoiones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Turn eliminado", "El turno a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El turno no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Turno ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El turno no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministradorTurnos