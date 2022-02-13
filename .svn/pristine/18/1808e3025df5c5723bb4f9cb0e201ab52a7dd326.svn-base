package com.geopractic.administracion.bean;

import com.geopractic.administracion.ControladorEmpleado;
import com.geopractic.administracion.ControladorTelefono;
import com.geopractic.administracion.modelo.Cargo;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.administracion.modelo.Telefono;
import com.geopractic.bean.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * Bean de administracion de empleados del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarEmpleado")
@ViewScoped
public class AdministradorEmpleado extends Bean<Empleado> implements Serializable {

  private static final long serialVersionUID = 2538877736258341424L;
  private Telefono telefono;
  private List<Empleado> listaEmpleadosCargos;

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorEmpleado() {

  } //Fin de constructor

  public Telefono getTelefono() {
    return telefono;
  } //Fin del metodo getTelefono

  public void setTelefono(Telefono telefono) {
    this.telefono = telefono;
  } //Fin del metodo setTelefono

  /**
   * Devuelve la lista de empleados por cargos.
   * @return el listado de empleados por cargo.
   */
  public List<Empleado> getListaEmpleadosCargos() {
    return listaEmpleadosCargos;
  } //Fin del metodo getListaEmpleadosCargos

  /**
   * Establece la lista de empleados por cargos.
   * @param listaEmpleadosCargos la lista a establecer.
   */
  public void setListaEmpleadosCargos(List<Empleado> listaEmpleadosCargos) {
    this.listaEmpleadosCargos = listaEmpleadosCargos;
  } //Fin del metodo setListaEmpleadosCargos

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    prepararVariables();
    generarLista();
  } //Fin del metodo init

  /**
   * Genera la lista de empleados por cargo.
   * @param cargo el cargo de los empleados a buscar.
   */
  public void generarListaCargo(Cargo cargo) {
    prepararVariables();
    log.debug("--> generarListaCargo() - Generando listado de empleados "
         + " por cargos del sistema.");
    try {
      listaEmpleadosCargos = ((ControladorEmpleado) controlador).listarPorCargo(cargo);
      agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Carga completa", "Listado por cargo cargado.",null);
    } catch (Exception excepcion) {
      log.error("--> generarListaCargo() - Error en generando lista por cargo ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Hubo un error dentro del sistema. No se pudo "
          + "obtener el listado de empleados por cargo. ",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("--> generarListaCargo() -  Error cerrando los recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo generarListaCargo

  /**
   * Genera la lista de empleados por cargo.
   * @param cargos los cargos de los empleados a buscar.
   */
  public void generarListaCargos(String[] cargos) {
    log.debug("--> generarListaCargo() - Generando listado de empleados "
         + " por cargos del sistema.");
    try {
      listaEmpleadosCargos = ((ControladorEmpleado) controlador).listarPorCargos(cargos);
      agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Carga completa", "Listado por cargo cargado.",null);
    } catch (Exception excepcion) {
      log.error("--> generarListaCargo() - Error en generando lista por cargo ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Hubo un error dentro del sistema. No se pudo "
          + "obtener el listado de empleados por cargo. ",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("--> generarListaCargo() -  Error cerrando los recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo generarListaCargo

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Empleado();
    this.telefono = new Telefono();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorEmpleado();
  } //Fin del metodo prepararControlador

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    log.debug("--> guardar() - Guardando empleado nuevo");
    try {
      //Verifica si no existe el empleado ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El empleado ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "El empleado ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Empleado guardado", "Nuevo empleado guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El nuevo empleado no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Empleado",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El nuevo empleado no pudo ser guardado.",null);
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
            "Cambios guardados", "Cambios del empleado guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Empleado. ",excepcion);
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
  public void eliminar(Empleado seleccion) {
    prepararVariables();
    log.debug("--> eliminar() - Eliminando empleado");
    this.entidad = seleccion;
    try {
      if (controlador.comprobarDependencias(entidad)) {
        log.warn("--> eliminar() - El empleado ya tiene operaciones asignadas.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El empleado " + entidad.getId()
              + "ya tiene operaciones asignadas",null);
      } else {
        boolean resultado = controlador.eliminar(entidad);
        if (resultado) {
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Empleado eliminado", "El empleado a sido eliminado.",null);
          generarLista();
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El empleado no pudo ser eliminado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Empleado",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El empleado no pudo ser eliminado.",null);
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
   * Guarda un numero de telefono asignado al empleado.
   * @param actionEvent el actionEvent del boton.
   */
  public void guardarTelf(ActionEvent actionEvent) {
    prepararVariables();
    ControladorTelefono controladorTelefono = new ControladorTelefono();
    log.debug("--> guardarTelf() - Guardando telefono nuevo");
    try {
      boolean resultado = controladorTelefono.asociarTelefonoEmpleado(entidad, telefono);
      if (resultado) {
        cerrarDialogo("diaTelf");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Telefono guardado", "Nuevo telefono guardado.",null);
        entidad.setNumerosTelefonicos(controladorTelefono.listarTelefonosEmpleado(entidad));
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
   * Elimina un numero de telefono seleccionado del empleado.
   * @param telefonoSeleccion el telefono seleccionado.
   */
  public void eliminarTelf(Telefono telefonoSeleccion) {
    prepararVariables();
    log.debug("Eliminando telefono.");
    ControladorTelefono controladorTelefono = new ControladorTelefono();
    try {
      boolean resultado = controladorTelefono.eliminarTelefonoEmpleado(entidad, telefonoSeleccion);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Telefono eliminado", "El telefono a sido eliminado.",null);
        entidad.setNumerosTelefonicos(controladorTelefono.listarTelefonosEmpleado(entidad));
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

} //Fin de la clase AdministradorEmpleado