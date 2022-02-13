package com.geopractic.cobranza.bean;

import com.geopractic.bean.Bean;
import com.geopractic.cobranza.ControladorSaldo;
import com.geopractic.cobranza.modelo.Saldo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de saldos de cuentas.
 * @author Cristhian
 */
@ManagedBean (name = "manejarSaldo")
@ViewScoped
public class AdministradorSaldo extends Bean<Saldo> implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorSaldo() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Saldo();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorSaldo();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {

  } //Fin de la clase guardar

  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios del saldo guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Saldo.",excepcion);
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
  public void eliminar(Saldo seleccion) {

  } //Fin de la clase eliminar
} //Fin de la clase AdministradorSaldo