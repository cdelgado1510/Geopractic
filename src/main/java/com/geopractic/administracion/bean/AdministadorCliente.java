package com.geopractic.administracion.bean;

import com.geopractic.administracion.ControladorCliente;
import com.geopractic.administracion.ControladorTelefono;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.administracion.modelo.Telefono;
import com.geopractic.bean.Bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * Bean de administracion de clientes del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "manejarCliente")
@ViewScoped
public class AdministadorCliente extends Bean<Cliente> implements Serializable {

  private static final long serialVersionUID = -4899732023679460475L;
  private Telefono telefono;
  private boolean bloqueo;

  /**
   * Constructor sin argumentos.
   */
  public AdministadorCliente() {

  } //Fin de constructor

  public Telefono getTelefono() {
    return telefono;
  } //Fin del metodo getTelefono

  public void setTelefono(Telefono telefono) {
    this.telefono = telefono;
  } //Fin del metodo setTelefono

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
    this.bloqueo = false;
  } //Fin del metodo init

  /**
   * Verifica si se cambio el campo bloqueo.
   * @param evento evento de cambiado.
   */
  public void cambioBloqueo(ValueChangeEvent evento) {
    this.bloqueo = true;
  } //Fin del metodo cambioBloqueo

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Cliente();
    this.telefono = new Telefono();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorCliente();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando cliente nuevo");
    try {
      //Verifica si no existe el cliente ingresado
      if (controlador.existeObjeto(entidad)) {
        log.error("--> guardar() - El cliente ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El cliente ingresado ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Cliente guardado", "Nuevo cliente guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El cliente nuevo no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Usuario",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El cliente nuevo no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase guardar
  
  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    //Verifica si esta bloqueado el cliente
    if (this.entidad.isBloqueado() && !(bloqueo)) {
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Cliente bloqueado", "El cliente actual se encuentra bloqueado.",null);
    } else {
      try {
        boolean resultado = controlador.modificar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Cambios guardados", "Cambios del cliente guardados.",null);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "Los cambios no pudieron ser guardados.",null);
        } //Fin de if-else
      } catch (Exception excepcion) {
        log.error("--> modificar() - Error en modificar Cliente",excepcion);
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "Los cambios no pudieron ser guardados.",null);
      } finally {
        generarNuevaEntidad();
        try {
          controlador.cerrarRecursosControlador();
        } catch (Exception excepcion) {
          log.error("--> modificar() - Error cerrando recursos. ",excepcion);
        } //Fin del try-catch interno
      } //Fin del try-catch
      this.bloqueo = false;
    } //Fin de if-else
  } //Fin de la clase modificar

  @Override
  public void eliminar(Cliente seleccion) {
    prepararVariables();
    log.debug("Eliminando cliente");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El cliente ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El cliente " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Cliente eliminado", "El cliente a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El cliente no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() -  Error en eliminar Cliente",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El cliente no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

  /**
   * Guarda un numero de telefono asignado al cliente.
   * @param actionEvent el actionEvent del boton.
   */
  public void guardarTelf(ActionEvent actionEvent) {
    prepararVariables();
    ControladorTelefono controladorTelefono = new ControladorTelefono();
    log.debug("--> guardarTelf() - Guardando telefono nuevo");
    try {
      boolean resultado = controladorTelefono.asociarTelefonoCliente(entidad, telefono);
      if (resultado) {
        cerrarDialogo("diaTelf");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Telefono guardado", "Nuevo telefono guardado.",null);
        entidad.setNumerosTelefonicos(controladorTelefono.listarTelefonosCliente(entidad));
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El telefono nuevo no pudo ser guardado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> guardarTelf() - Error en guardar de Telefono",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El telefono nuevo no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardarTelf() - Error en cerrar recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase guardarTelf
  
  /**
   * Elimina un numero de telefono seleccionado del cliente.
   * @param telefonoSeleccion el telefono seleccionado.
   */
  public void eliminarTelf(Telefono telefonoSeleccion) {
    prepararVariables();
    log.debug("Eliminando telefono.");
    ControladorTelefono controladorTelefono = new ControladorTelefono();
    try {
      boolean resultado = controladorTelefono.eliminarTelefonoCliente(entidad, telefonoSeleccion);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Telefono eliminado", "El telefono a sido eliminado.",null);
        entidad.setNumerosTelefonicos(controladorTelefono.listarTelefonosCliente(entidad));
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "El telefono no pudo ser eliminado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminarTelf() - Error en eliminar telefono",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El telefono no pudo ser eliminado.",null);
    } finally {
      try {
        controladorTelefono.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminarTelf() - Error en cerrar recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminarTelf

} //Fin de la clase AdministadorCliente