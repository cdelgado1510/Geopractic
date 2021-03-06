package com.geopractic.sistema.bean;

import com.geopractic.sistema.ControladorAccesos;
import com.geopractic.sistema.excepciones.SesionExcepcion;
import com.geopractic.sistema.modelo.Usuario;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Clase para el manejo y control de datos de sesion.
 * @author Cristhian
 *
 */
@ManagedBean (name = "sesion")
@SessionScoped
public class SesionBean implements Serializable {

  private static final long serialVersionUID = 1L;
  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  private Usuario usuario;
  private FacesContext contexto;
  private ControladorAccesos accesos;
  private String uuid; 

  /**
   * Procedimientos realizados al iniciar el bean.
   */
  @PostConstruct
  public void init() {
    //Generando controlador de accesos
    accesos = new ControladorAccesos();
  } //Fin del metodo init
  
  /**
   * Devuelve el usuario actual de la sesion.
   * @return el usuario actual.
   */
  public Usuario getUsuario() {
    return usuario;
  } //Fin del metodo getUsuario

  /**
   * Establece el usuario actual de la sesion.
   * @param usuario el usuario de la sesion
   */
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  } //Fin del metodo setUsuario
  
  /**
   * Verifica que el usuario tenga la sesion iniciada. 
   * @throws SesionExcepcion error lanzado al no tener una sesion iniciada. 
   */
  public void verificarSesion(ComponentSystemEvent evento) throws SesionExcepcion {
    contexto = FacesContext.getCurrentInstance();
    usuario = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
    uuid = (String) contexto.getExternalContext().getSessionMap().get("uuid");
    
    if (usuario == null) {
      throw new SesionExcepcion("Sesion " + uuid + " espirada");
    } else {
      log.debug("Verificando sesion de usuario {}",usuario.toString());
    } //Fin de if-else
    
  } //Fin del metodo verificarSesion

  /**
   * Elimina la sesion actual del usuario.
   * @throws IOException excepcion lanzada si no encuentra la p??gina.
   */
  public void cerrarSesion(ActionEvent evento) throws IOException {
    FacesContext contexto = FacesContext.getCurrentInstance();
    contexto.getExternalContext().invalidateSession();
    log.info("Finalizando session {} de usuario {}",uuid,usuario.getUsuario());
    contexto.getExternalContext().redirect(contexto.getExternalContext().getRequestContextPath()
         + "/index.xhtml");
  } //Fin del metodo cerrarSesion

  /**
   * Refresca actualiza el rol del usuario de la sesion actual.
   * @param evento el evento del componente.
   */
  public void refrescarSesion(ActionEvent evento) {
    contexto = FacesContext.getCurrentInstance();
    usuario = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
    Usuario usuarioRecargado = accesos.comprobarRol(usuario);
    contexto.getExternalContext().getSessionMap().put("usuario", usuarioRecargado);
  } //Fin de metodo refrescarSesion
} //Fin de la clase ControladorSesion