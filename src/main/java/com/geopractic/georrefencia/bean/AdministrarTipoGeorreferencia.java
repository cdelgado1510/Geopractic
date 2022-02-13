package com.geopractic.georrefencia.bean;

import com.geopractic.bean.Bean;
import com.geopractic.georrefencia.ControladorTipoGeorrefencia;
import com.geopractic.georrefencia.modelo.TipoGeorreferencia;
import com.geopractic.util.Iconos;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de tipos de georreferencias del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarTipoGeo")
@ViewScoped
public class AdministrarTipoGeorreferencia extends Bean<TipoGeorreferencia> {

  private String[] marcadores;

  /**
   * Constructor sin argumentos. 
   */
  public AdministrarTipoGeorreferencia() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
    marcadores = Iconos.getMarcadores();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new TipoGeorreferencia();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorTipoGeorrefencia();
  } //Fin del metodo prepararControlador

  /**
  * Devuelve los marcadores que pueden utilizarse.
  * @return los marcadores a utilizar.
  */
  public String[] getMarcadores() {
    return marcadores;
  } //Fin del metodo getMarcadores

  /**
  * Establece los marcadores que pueden utilizarse.
  * @param marcadores los marcadores a establecer
  */
  public void setMarcadores(String[] marcadores) {
    this.marcadores = marcadores;
  } //Fin del metodo setMarcadores

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando tipo nuevo");
    try {
      //Verifica si no existe el tipo ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El tipo de georreferencia ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El tipo de georreferencia ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Tipo de georreferencia guardado", "Nuevo tipo de georreferencia guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El nuevo tipo de georreferencia no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Tipo Georreferencia",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El nuevo tipo de georreferencia no pudo ser guardado.",null);
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
      log.error("--> modificar() - Error en modificar TipoGeorreferencia. ",excepcion);
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
  public void eliminar(TipoGeorreferencia seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando tipo georreferencia");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El tipo ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El tipo de georreferencia " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Tipo eliminado", "El tipo de georreferencia a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El tipo de georreferencia no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Tipo Georreferencia",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El tipo de georreferencia no pudo ser eliminado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminar

} //Fin de la clase AdministrarTipoGeorreferencia