package com.geopractic.cobranza.bean;

import com.geopractic.bean.Bean;
import com.geopractic.cobranza.ControladorCuentaPorCobrar;
import com.geopractic.cobranza.ControladorSaldo;
import com.geopractic.cobranza.modelo.CuentaPorCobrar;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * Bean de administracion de forma de pago del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarCuentaPorCobrar")
@ViewScoped
public class AdministradorCuentaPorCobrar extends Bean<CuentaPorCobrar> implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean cambioEmpAsignado;

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorCuentaPorCobrar() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new CuentaPorCobrar();
    this.cambioEmpAsignado = false;
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorCuentaPorCobrar();
  } //Fin del metodo prepararControlador

  /**
   * Verifica si se cambio el campo Empleado Asignado.
   * @param evento evento de cambiado.
   */
  public void cambioEmpAsig(ValueChangeEvent evento) {
    this.cambioEmpAsignado = true;
  } //Fin del metodo cambioEmpAsig

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando cuenta nueva");
    try {
      boolean resultado = controlador.guardar(entidad);
      entidad.generarSaldos();
      resultado = new ControladorSaldo().guardarSaldos(entidad.getSaldos());
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cuenta guardada", "Nueva cuenta por cobrar guardado.",null);
        lista.add(entidad);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "La nueva cuenta por cobrar no pudo ser guardada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Cuenta por Cobrar",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La nueva cuenta no pudo ser guardada. ",null);
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
      
      //Verifica si cambio el empleado asignado 
      if (cambioEmpAsignado) {
        resultado = ((ControladorCuentaPorCobrar) controlador).cambiarEmpAsig(entidad);
      } //Fin de if
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios de la cuenta por cobrar guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Cuenta por Cobrar.",excepcion);
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
  public void eliminar(CuentaPorCobrar seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando cuenta");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - La cuenta ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "La cuenta " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Forma de pago eliminada", "La cuenta a sido eliminada.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "La cuenta no pudo ser eliminada.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Forma de pago",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La cuenta no pudo ser eliminada.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar
} //Fin de la clase AdministradorCuentaPorCobrar