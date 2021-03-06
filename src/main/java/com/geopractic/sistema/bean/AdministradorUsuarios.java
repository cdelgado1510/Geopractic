package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorUsuario;
import com.geopractic.sistema.modelo.ContenidoMultimedia;
import com.geopractic.sistema.modelo.Usuario;
import com.geopractic.util.Encriptador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * Bean de administracion de usuarios del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "manejarUsuario")
@ViewScoped
public class AdministradorUsuarios extends Bean<Usuario> implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean cambioContrasenha;
  
  /**
   * Constructor sin argumentos.
   */
  public AdministradorUsuarios() {

  } //Fin de constructor

  /**
   * Procedimientos realizados al iniciar el bean.
   */
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
    cambioContrasenha = false;
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Usuario();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorUsuario();
  } //Fin del metodo prepararControlador
  
  /**
   * Verifica si se cambio el campo contrasenha.
   * @param evento evento de cambiado.
   */
  public void cambioContrasenha(ValueChangeEvent evento) {
    this.cambioContrasenha = true;
  } //Fin del metodo cambioContrasenha

  /**
   *  Guarda un usuario nuevo dentro del sistema.
   */
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando usuario nuevo");
    try {
      //Verifica si no existe el usuario ingresado
      if (controlador.existeObjeto(this.entidad)) {
        log.warn("--> guardar() - El usuario ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El usuario ingresado ya existe.",null);
      } else {
        this.entidad.setContrasenha(((ControladorUsuario) controlador)
            .encriptarContrasenha(this.entidad.getContrasenha()));
        boolean resultado = controlador.guardar(this.entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Usuario guardado", "Nuevo usuario guardado.",null);
          lista.add(entidad);
        } else {
          mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El usuario nuevo no pudo ser guardado.");
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar usuario ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El usuario nuevo no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase guardar
  
  /**
   *  Modifica un usuario del sistema.
   */
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      //Verifica si la contrasenha fue cambiada
      if (cambioContrasenha) {
        byte[] sal = Encriptador.salarSiguiente();
        this.entidad.setContrasenha(Encriptador.encriptarTexto(this.entidad.getContrasenha(), sal));
      } //Fin de if

      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios de usuario guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar el usuario ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Los cambios no pudieron ser guardados.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- modificar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase modificar

  /**
   *  Elimina un usuario del sistema.
   */
  public void eliminar(Usuario seleccion) {
    prepararVariables();
    log.debug("Eliminando usuario");
    this.entidad = seleccion;
    try {
      boolean resultado = controlador.eliminar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
             "Usuario eliminado", "El usuario a sido eliminado.",null);
        generarLista();
      } else {
        mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "El usuario no pudo ser eliminado.");
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error eliminando usuario ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El usuario no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos.",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

  /**
   * Genera un reporte con todos los usuarios dentro del sistema.
   */
  public void generarReportesListaCompletaUsuarios() {
    ContenidoMultimedia contenido = generateReporte("listaUsuarios",
          "listaCompletaUsuarios.pdf", getLista());
    setMultimedia(contenido.getStreamContent());

    prepararVariables();
    //Verifica si el reporte fue cargado
    if (multimedia != null) {
      contexto.getExternalContext().getSessionMap().put("reporteBytesArray",
           contenido.getByteArrayStream().toByteArray());
      abrirDialogo("diaReporte");
    } else {
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de reporte", "No se puede mostrar el reporte.",null);
    } //Fin de if
  } //Fin del generarReportesListaCompletaUsuarios
} //Fin de la clase AdministradorUsuarios