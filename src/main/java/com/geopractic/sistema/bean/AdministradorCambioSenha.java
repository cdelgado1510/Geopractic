package com.geopractic.sistema.bean;

import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.sistema.ControladorUsuario;
import com.geopractic.sistema.modelo.Usuario;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean de administracion de inicio de logeo del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "cambioSenha")
@RequestScoped
public class AdministradorCambioSenha implements Serializable {

  private static final long serialVersionUID = 1L;
  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  @ManagedProperty(value = "#{sesion.usuario}")
  private Usuario usuario;

  public AdministradorCambioSenha() {
    
  } //Fin de constructor

  public Usuario getUsuario() {
    return this.usuario;
  } //Fin del metodo setUsuario

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  } //Fin del metodo setUsuario

  /**
   * Valida que el usuario ingresado sea el correcto.
   */
  public void cambiarSenha() {
 
    log.debug("--> cambiarSenha() - Iniciando proceso de cambio de contraseña.");
    
    FacesMessage mensaje = null;
    boolean modificado = false;
    ControladorUsuario controlador = new ControladorUsuario();
    
    try {
      log.debug("Guardando usuario.");
      modificado = controlador.modificarContrasenha(usuario);
    } catch (Exception excepcion) {
      log.error("--> cambiarSenha() - Error en cambiar senha ",excepcion);
      mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Hubo un error dentro del sistema.");
      modificado = false;
      try {
        controlador.realizarRollback();
      } catch (ExcepcionControlador excepcionInterna) {
        log.error("<-- cambiarSenha() - Error realizando el rollback. ",excepcionInterna);
        mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "Hubo un error dentro del sistema.");
      } //Fin de try-catch
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("--> cambiarSenha() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
    
    if (modificado) {
      mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuario actualizado", "Contraseña cambiada.");
    } else {
      mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                "Error de actualizacion", "Contraseña no cambiada.");
    } //Fin de if-else
    
    FacesContext contexto = FacesContext.getCurrentInstance();
    contexto.addMessage("mensajes", mensaje);
  } //Fin del metodo cambiarSenha
  
} //Fin de la clase AdministradorCambioSenha