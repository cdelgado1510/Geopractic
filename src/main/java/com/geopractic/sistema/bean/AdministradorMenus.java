package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorMenu;
import com.geopractic.sistema.modelo.Menu;
import com.geopractic.util.Iconos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de menus del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "manejarMenu")
@ViewScoped
public class AdministradorMenus extends Bean<Menu> implements Serializable {

  private static final long serialVersionUID = 1L;
  private String[] iconos;
  
  /**
   * Constructor sin argumentos.
   */
  public AdministradorMenus() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
    iconos = Iconos.getIconos();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Menu();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorMenu();
  } //Fin del metodo prepararControlador

  /**
  * Devuelve los iconos que pueden utilizarse.
  * @return los iconos a utilizar.
  */
  public String[] getIconos() {
    return iconos;
  } //Fin del metodo getIconos

  /**
  * Establece los iconos que pueden utilizarse.
  * @param iconos los iconos a establecer
  */
  public void setIconos(String[] iconos) {
    this.iconos = iconos;
  } //Fin del metodo setIconos

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando menu nuevo");
    try {
      //Verifica si no existe el menu ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El menu ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
                "Error de sistema", "El menu ingresado ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Menu guardado", "Nuevo menu guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El menu nuevo no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar el menu",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "El menu nuevo no pudo ser guardado.",null);
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
            "Cambios guardados", "Cambios de menu guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar el menu ",excepcion);
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

  @Override
  public void eliminar(Menu seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando menu");
    this.entidad = seleccion;
    try {
      boolean resultado = controlador.eliminar(entidad);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,"Menu eliminado", 
            "El menu a sido eliminado.",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "El menu no pudo ser eliminado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar el menu. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "El menu no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministradorMenus