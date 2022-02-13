package com.geopractic.gps.bean;

import com.geopractic.bean.Bean;
import com.geopractic.gps.ControladorEstadosRastreador;
import com.geopractic.gps.modelo.EstadosRastreadorGps;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de estados del rastreador del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarEstadosRastreador")
@ViewScoped
public class AdministradorEstadosRastreador extends Bean<EstadosRastreadorGps> {

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorEstadosRastreador() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new EstadosRastreadorGps();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorEstadosRastreador();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando estado nuevo");
    try {
      //Verifica si no existe el estado ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El estado ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El estado ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Estado Rastreador guardado", "Nuevo estado guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El nuevo estado no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Estado de Rastreador",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El nuevo estado no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase agregar

  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios del estado guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Estado rastreador. ",excepcion);
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
  public void eliminar(EstadosRastreadorGps seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando estado del rastreador");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El estado ya tiene funciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El estado " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Tipo eliminado", "El estado a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El estado no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Estado Rastreador",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El estado de rastreador no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministradorEstadosRastreador