package com.geopractic.sistema.bean;

import com.geopractic.bean.Bean;
import com.geopractic.sistema.ControladorMenu;
import com.geopractic.sistema.ControladorRol;
import com.geopractic.sistema.ControladorVentana;
import com.geopractic.sistema.modelo.Menu;
import com.geopractic.sistema.modelo.Rol;
import com.geopractic.sistema.modelo.Ventana;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 * Bean de administracion de rol del sistema.
 * @author Cristhian
 *
 */
@ManagedBean (name = "manejarRol")
@ViewScoped
public class ManejadorRol extends Bean<Rol> implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Menu> listaMenus;
  private List<Menu> listaDisponiblesMenus;
  private List<Menu> listaSeleccionMenus;
  private DualListModel<Menu> modeloListaDobleMenu;
  private List<Ventana> listaVentanas;
  private List<Ventana> listaDisponiblesVentanas;
  private List<Ventana> listaSeleccionVentanas;
  private DualListModel<Ventana> modeloListaDobleVentanas;

  /**
   * Constructor sin argumentos.
   */
  public ManejadorRol() {

  } //Fin de constructor

  /**
   * Procedimientos realizados al iniciar el bean.
   */
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
    listarMenus();
    listarVentanas();
    
    this.listaDisponiblesMenus = new ArrayList<Menu>();
    this.listaSeleccionMenus = new ArrayList<Menu>();
    this.modeloListaDobleMenu = new DualListModel<>(listaDisponiblesMenus, listaSeleccionMenus);

    this.listaDisponiblesVentanas = new ArrayList<Ventana>();
    this.listaSeleccionVentanas = new ArrayList<Ventana>();
    this.modeloListaDobleVentanas = 
      new DualListModel<>(listaDisponiblesVentanas, listaSeleccionVentanas);
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Rol();
  } //Fin del metodo generarNuevaEntidad
  
  @Override
  public void prepararControlador() {
    controlador = new ControladorRol();
  } //Fin del metodo prepararControlador
  
  /**
   * Devuelve el listado de menus obtenido.
   * @return la lista de menus.
   */
  public List<Menu> getListaMenus() {
    return listaMenus;
  } //Fin del metodo getListaMenus

  /**
  * Establece la lista de menus.
  * @param listaMenus la lista de menus a establecer
  */
  public void setListaMenus(List<Menu> listaMenus) {
    this.listaMenus = listaMenus;
  } //Fin del metodo setListaMenus

  /**
   * Devuelve el listado de menus disponibles.
   * @return la lista de menus disponibles.
   */
  public List<Menu> getListaDisponiblesMenus() {
    return listaDisponiblesMenus;
  } //Fin del metodo getListaDisponiblesMenus

  /**
  * Establece la lista de menus disponibles.
  * @param listaDisponiblesMenus la lista de menus disponibles a establecer
  */
  public void setListaDisponiblesMenus(List<Menu> listaDisponiblesMenus) {
    this.listaDisponiblesMenus = listaDisponiblesMenus;
  } //Fin del metodo setListaDisponiblesMenus
  
  /**
   * Devuelve el listado de menus seleccionados.
   * @return la lista de menus seleccionados.
   */
  public List<Menu> getListaSeleccionMenus() {
    return listaSeleccionMenus;
  } //Fin del metodo getListaSeleccionMenus

  /**
  * Establece la lista de menus seleccionados.
  * @param listaSeleccionMenus la lista de menus seleccionados a establecer
  */
  public void setListaSeleccionMenus(List<Menu> listaSeleccionMenus) {
    this.listaSeleccionMenus = listaSeleccionMenus;
  } //Fin del metodo setListaSeleccionMenus

  /**
   * Devuelve el modelo del PickList de menus.
   * @return el modelo del picklist
   */
  public DualListModel<Menu> getModeloListaDobleMenu() {
    return modeloListaDobleMenu;
  } //Fin del metodo getModeloListaDobleMenu

  /**
   * Establece el modelo de picklist de menus.
   * @param modeloListaDobleMenu el modelo de picklist.
   */
  public void setModeloListaDobleMenu(DualListModel<Menu> modeloListaDobleMenu) {
    this.modeloListaDobleMenu = modeloListaDobleMenu;
  } //Fin del metodo setModeloListaDobleMenu

  /**
   * Devuelve el listado de ventanas.
   * @return la lista de rventanas.
   */
  public List<Ventana> getListaVentanas() {
    return listaVentanas;
  } //Fin del metodo getListaVentanas

  /**
  * Establece la lista de ventanas.
  * @param listaVentanas la lista de ventanas a establecer
  */
  public void setListaVentanas(List<Ventana> listaVentanas) {
    this.listaVentanas = listaVentanas;
  } //Fin del metodo setListaVentanas

  /**
   * Devuelve el listado de ventanas disponibles.
   * @return la lista de ventanas disponibles.
   */
  public List<Ventana> getListaDisponiblesVentanas() {
    return listaDisponiblesVentanas;
  } //Fin del metodo getListaDisponiblesVentanas
  
  /**
  * Establece la lista de ventanas disponibles.
  * @param listaDisponiblesVentanas la lista de ventanas disponibles a establecer
  */
  public void setListaDisponiblesVentanas(List<Ventana> listaDisponiblesVentanas) {
    this.listaDisponiblesVentanas = listaDisponiblesVentanas;
  } //Fin del metodo setListaDisponiblesVentanas
  
  /**
   * Devuelve el listado de ventanas seleccionadas.
   * @return la lista de ventanas seleccionadas.
   */
  public List<Ventana> getListaSeleccionVentanas() {
    return listaSeleccionVentanas;
  } //Fin del metodo getListaSeleccionVentanas

  /**
  * Establece la lista de ventanas seleccionadas.
  * @param listaSeleccionVentanas la lista de ventanas seleccionadas a establecer
  */
  public void setListaSeleccionVentanas(List<Ventana> listaSeleccionVentanas) {
    this.listaSeleccionVentanas = listaSeleccionVentanas;
  } //Fin del metodo setListaSeleccionVentanas

  /**
   * Devuelve el modelo del PickList de ventanas.
   * @return el modelo del picklist.
   */
  public DualListModel<Ventana> getModeloListaDobleVentanas() {
    return modeloListaDobleVentanas;
  } //Fin del metodo getModeloListaDobleVentanas

  /**
   * Establece el modelo de picklist de ventanas.
   * @param modeloListaDobleVentanas el modelo de picklist.
   */
  public void setModeloListaDobleVentanas(DualListModel<Ventana> modeloListaDobleVentanas) {
    this.modeloListaDobleVentanas = modeloListaDobleVentanas;
  } //Fin del metodo setModeloListaDobleVentanas

  /**
   * Lista todos los menus del sistema.
   */
  public void listarMenus() {
    ControladorMenu controladorMenu = new ControladorMenu();
    try {
      listaMenus = controladorMenu.listarTodos();
    } catch (Exception excepcion) {
      log.error("--> listarMenus() - Error en listado menus. ",excepcion);
    } finally {
      try {
        controladorMenu.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- listarMenus() - Error en cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo listarMenus

  /**
   * Funcion de procesamiento de transferencia de listado de
   * menus de rol.
   * @param evento el evento de la lista.
   */
  @SuppressWarnings("unchecked")
  public void transferirMenu(TransferEvent evento) {
    List<Menu> tranferencias = (List<Menu>) evento.getItems();
    if (evento.isAdd()) {
      asignarMenus(tranferencias);
    } else {
      eliminarMenus(tranferencias);
    } //Fin de if-else
  } //Fin de metodo transferirMenu

  /**
   * Lista todas las ventanas del sistema.
   */
  public void listarVentanas() {
    ControladorVentana controladorVentana = new ControladorVentana();
    try {
      listaVentanas = controladorVentana.listarTodos();
    } catch (Exception excepcion) {
      log.error("--> listarVentanas() - Error en listar ventanas. ",excepcion);
    } finally {
      try {
        controladorVentana.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- listarVentanas() - Error en cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo listarVentanas

  /**
   * Funcion de procesamiento de transferencia de listado de
   * ventanas de rol.
   * @param evento el evento de la lista.
   */
  @SuppressWarnings("unchecked")
  public void transferirVentana(TransferEvent evento) {
    List<Ventana> tranferencias = (List<Ventana>) evento.getItems();
    if (evento.isAdd()) {
      asignarVentanas(tranferencias);
    } else {
      eliminarVentanas(tranferencias);
    } //Fin de if-else
  } //Fin de metodo transferirVentana

  @Override
  public void seleccionar(Rol seleccion) {
    this.funcion = "modificar";
    this.entidad = seleccion;
    listarMenusRol();
    listarVentanasRol();
  } //Fin del metodo seleccionar

  /**
   * Lista los menus de la entidad actual.
   */
  private void listarMenusRol() {
    //Obtiene los menus disponibles para asignar al rol
    this.listaDisponiblesMenus = new ArrayList<Menu>();
    this.listaDisponiblesMenus.addAll(listaMenus);
    this.listaDisponiblesMenus.removeAll(entidad.getMenus());

    this.listaSeleccionMenus.addAll(entidad.getMenus());
    modeloListaDobleMenu = new DualListModel<>(listaDisponiblesMenus, listaSeleccionMenus);
  } //Fin del metodo listarMenusRol

  /**
   * Lista las ventanas de la entidad actual.
   */
  private void listarVentanasRol() {
    //Obtiene las ventanas disponibles para asignar al rol
    this.listaDisponiblesVentanas = new ArrayList<Ventana>();
    this.listaDisponiblesVentanas.addAll(listaVentanas);
    this.listaDisponiblesVentanas.removeAll(entidad.getVentanas());

    this.listaSeleccionVentanas.addAll(entidad.getVentanas());
    modeloListaDobleVentanas = new 
      DualListModel<>(listaDisponiblesVentanas, listaSeleccionVentanas);
  } ///Fin del metodo listarVentanasRol

  /**
   * Limpia las listas de seleccion de menus y ventanas.
   */
  public void limpiarListasSeleccion() {
    if (!CollectionUtils.isEmpty(listaSeleccionMenus)) {
      this.listaSeleccionMenus.clear();
    } //Fin de if
    if (!CollectionUtils.isEmpty(listaSeleccionVentanas)) {
      this.listaSeleccionVentanas.clear();
    } //Fin de if
  } //Fin del metodo limpiarListasSeleccion

  /**
   *  Guarda un rol nuevo dentro del sistema.
   */
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      //Verifica si no existe el usuario ingresado
      if (controlador.existeObjeto(entidad)) {
        log.warn("--> guardar() - El rol ya existe.");
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de sistema", "El rol ingresado ya existe.",null);
      } else {
        boolean resultado = controlador.guardar(entidad);
        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Rol guardado", "Nuevo rol guardado.",null);
          lista.add(entidad);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "El rol nuevo no pudo ser guardado.",null);
        } //Fin de if-else
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardando Rol",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El rol nuevo no pudo ser guardado.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error en Rol",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase agregar
  
  /**
   *  Modifica un rol del sistema.
   */
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(this.entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios de rol guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en Rol",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Los cambios no pudieron ser guardados.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- modificar() - Error cerrar recursos.",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase modificar

  /**
   *  Elimina un rol del sistema.
   */
  public void eliminar(Rol seleccion) {
    prepararVariables();
    log.debug("Eliminando rol");
    this.entidad = seleccion;
    try {
      boolean resultado = controlador.eliminar(this.entidad);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Rol eliminado", "El rol a sido eliminado.",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "El rol no pudo ser eliminado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en Rol",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "El rol no pudo ser eliminado.",null);
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
   * Asigna los menus seleccionados al rol actual.
   * @param listaAsignar lista de menus a asignar.
   */
  public void asignarMenus(List<Menu> listaAsignar) {
    prepararVariables();
    try {
      boolean resultado = ((ControladorRol) controlador)
          .agregarRolMenu(this.entidad, listaAsignar);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
             "Asignacion completa", "La asignacion a sido finalizada",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
             "Error de asignacion", "El menu no pudo ser asignado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("asignarMenus() - Error de asignacion. ", excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La asignacion no puedo realizarse.",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- asignarMenus() - Error en cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase asignarMenus
  
  /**
   * Elimina los menus seleccionados al rol asignado actual.
   * @param listaEliminar lista de menus a eliminar.
   */
  public void eliminarMenus(List<Menu> listaEliminar) {
    prepararVariables();
    try {
      boolean resultado = ((ControladorRol) controlador)
          .eliminarRolMenu(this.entidad, listaEliminar);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
             "Eliminacion completa", "La eliminacion a sido finalizada",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "El menu no pudo ser eliminado.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminarMenus() - Error en eliminando menu. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La eliminacion no puedo realizarse.",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminarMenus() - Error cerrando recursos.",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminarMenus
  
  /**
   * Asigna las ventanas seleccionadas al rol actual.
   * @param listaAsignar lista de ventanas a asignar.
   */
  public void asignarVentanas(List<Ventana> listaAsignar) {
    prepararVariables();
    try {
      boolean resultado = ((ControladorRol) controlador)
          .agregarRolVentana(this.entidad, listaAsignar);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
             "Asignacion completa", "La asignacion a sido finalizada",null);
        generarLista();

        //Agrega las ventanas de la entidad actual
        for (Ventana ventana : listaAsignar) {
          this.entidad.agregarVentana(ventana);
        } //Fin de for
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de asignacion", "La ventana no pudo ser asignada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> asignarVentanas() - Error en asignando ventana. ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La asignacion no puedo realizarse.",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- asignarVentanas() - Error en cerrando recursos.",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase asignarVentanas
  
  /**
   * Elimina las ventanas seleccionadas al rol asignado actual.
   * @param listaEliminar lista de ventanas a eliminar.
   */
  public void eliminarVentanas(List<Ventana> listaEliminar) {
    prepararVariables();
    try {
      boolean resultado = ((ControladorRol) controlador)
          .eliminarRolVentana(this.entidad, listaEliminar);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
             "Eliminacion completa", "La eliminacion a sido finalizada",null);
        generarLista();

        //Elimina las ventanas de la entidad actual
        for (Ventana ventana : listaEliminar) {
          this.entidad.eliminarVentana(ventana);
        } //Fin de for
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de eliminacion", "La ventana no pudo ser eliminada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminarVentanas() - Error al eliminar ventanas ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La eliminacion no puedo realizarse.",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminarVentanas() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase eliminarVentanas
  
  /**
   * Guarda los cambios de los permisos de la ventana editada.
   * @param evento el evento ocurrido.
   */
  public void editarPermisosVentana(RowEditEvent evento) {
    prepararVariables();
    Ventana ventanaEditada = (Ventana) evento.getObject();
    ControladorVentana controladorVentana = new ControladorVentana();
    try {
      boolean resultado = controladorVentana.modificarPermisosVentana(ventanaEditada,this.entidad);
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
               "Permisos actualizados", "Los permisos fueron actualizados",null);
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
                "Error de modificacion", "Los permisos no pudieron se modificados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> editarPermisosVentana() - Error en editar permisos ventana ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La edicion de los permisos no puedo realizarse.","mensajeMenus");
    } finally {
      try {
        controladorVentana.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- editarPermisosVentana() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo editarPermisosVentana
  
} //Fin de la clase ManejadorRol