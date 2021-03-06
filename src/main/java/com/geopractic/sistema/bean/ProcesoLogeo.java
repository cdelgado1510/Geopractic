package com.geopractic.sistema.bean;

import com.geopractic.sistema.ControladorUsuario;
import com.geopractic.sistema.modelo.Usuario;

import java.io.Serializable;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean de administracion de inicio de logeo del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "login")
@RequestScoped
public class ProcesoLogeo implements Serializable {

  private static final long serialVersionUID = 1L;
  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName()); 
  private Usuario usuario;

  public ProcesoLogeo() {
    this.usuario = new Usuario();
  } //Fin de constructor

  public Usuario getUsuario() {
    return this.usuario;
  } //Fin del metodo setUsuario

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  } //Fin del metodo setUsuario

  /**
   * Valida que el usuario ingresado sea el correcto.
   * @return el resultado de la validacion.
   */
  public String validarUsuario() {
    
    Usuario usuarioValido = null;
    FacesMessage mensaje = null;
    String resultado = null;
    FacesContext contexto = FacesContext.getCurrentInstance();
    ControladorUsuario controlador = new ControladorUsuario();
    
    try {
      usuarioValido = controlador.buscar(usuario);
      if (usuarioValido == null) {
        mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, 
            "Error de acceso", "Usuario o contraseña inválido.");
      } else {
        boolean valido =  controlador.validarUsuario(usuario, usuarioValido);
        if (valido && usuarioValido.isActivo()) {
          //Generando UUID
          UUID uuid = UUID.randomUUID();
          String uuidString = uuid.toString().replace("-", "");

          log.info("Usuario {} logeado sesion {} ",this.usuario.getUsuario(),uuidString);
          mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
            "Acceso concedido", "Acceso concedido.");

          //Guardando datos de sesion
          contexto.getExternalContext().getSessionMap().put("usuario", usuarioValido);
          contexto.getExternalContext().getSessionMap().put("uuid", uuidString);
          resultado = "/app/principal.xhtml?faces-redirect=true";
        } else {
          log.warn("--> validarUsuario() - Ingreso fallido con usuario "
              + this.usuario.getUsuario());
          mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, 
            "Error de acceso", "Usuario o contraseña inválido.");
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> validarUsuario() - Error en validando usuario. ",excepcion);
      mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Hubo un error dentro del sistema.");
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error(",-- validarUsuario() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch

    contexto.addMessage("mensajes", mensaje);
    return resultado;
  } //Fin del metodo validarUsuario
  
} //Fin de la clase ProcesoLogeo