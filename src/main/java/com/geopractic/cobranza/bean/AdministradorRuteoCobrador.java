package com.geopractic.cobranza.bean;

import com.geopractic.bean.Bean;
import com.geopractic.cobranza.ControladorRuteoCobrador;
import com.geopractic.cobranza.modelo.RuteoCobrador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de forma de pago del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarRuteoCobrador")
@ViewScoped
public class AdministradorRuteoCobrador extends Bean<RuteoCobrador> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorRuteoCobrador() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new RuteoCobrador();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorRuteoCobrador();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {

  } //Fin de la clase agregar

  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios del ruteo guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Ruteo de Cobrador.",excepcion);
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
  public void eliminar(RuteoCobrador seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando ruteo");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El ruteo ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El ruteo " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Ruteo eliminado", "El motivo a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El ruteo no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar ruteo ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El ruteo no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar
} //Fin de la clase AdministradorRuteoCobrador