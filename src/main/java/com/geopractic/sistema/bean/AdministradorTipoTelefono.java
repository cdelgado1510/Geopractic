package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorTipoTelefono;
import com.geopractic.sistema.modelo.TipoTelefono;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de tipos de telefono del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarTipoTelf")
@ViewScoped
public class AdministradorTipoTelefono extends Bean<TipoTelefono> {

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorTipoTelefono() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new TipoTelefono();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorTipoTelefono();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando tipo nuevo");
    try {
      //Verifica si no existe el tipo ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El tipo de telefono ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El tipo de documento ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Tipo de documento guardado", "Nuevo tipo de telefono guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El nuevo tipo de telefono no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Tipo Documento",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El nuevo tipo de telefono no pudo ser guardado.",null);
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
            "Cambios guardados", "Cambios del tipo guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar TipoDocumento. ",excepcion);
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
  public void eliminar(TipoTelefono seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando tipo telefono");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El tipo ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El tipo de telefono " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Tipo eliminado", "El tipo de telefono a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El tipo telefono no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Tipo Telefono",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El tipo de telefono no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministradorTipoTelefono