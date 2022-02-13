package com.geopractic.sistema.bean;

import com.geopractic.sistema.ControladorAccesos;
import com.geopractic.sistema.dao.MenuDao;
import com.geopractic.sistema.modelo.Menu;
import com.geopractic.sistema.modelo.Usuario;
import com.geopractic.sistema.modelo.Ventana;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean para generar la barra de menu y dar acceso a los permisos
 * utilizados por el usuario.
 * @author Cristhian
 *
 */
@ManagedBean (name = "accesos")
@ViewScoped
public class ManejadorDeAccesos implements Serializable {

  private static final long serialVersionUID = 1L;
  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  private List<Menu> menus;
  private MenuModel modelo;
  private Usuario usuario;
  private ControladorAccesos controlador;
  private String ubicacionVentana;
  FacesContext contexto;

  /**
   * Procedimientos realizados al iniciar el bean.
   */
  @PostConstruct
  public void init() {
    contexto = FacesContext.getCurrentInstance();
    usuario = (Usuario) contexto.getExternalContext().getSessionMap().get("usuario");
    this.listarMenus();
    modelo = new DefaultMenuModel();
    //Verifica que se hayan devuelto menus.
    if (menus != null) {
      this.establecerModelo();
    } //Fin de if;
    //Generando controlador de accesos
    controlador = new ControladorAccesos();
    //Obteniendo ventana actual
    ubicacionVentana = contexto.getViewRoot().getViewId();
  } //Fin del metodo init

  /**
  *  Constructor sin argumentos.
  */
  public ManejadorDeAccesos() {

  } //Fin del constructor
  
  /**
   * Devuelve el usuario de la sesion.
   * @return el usuario de la sesion.
   */
  public Usuario getUsuario() {
    return usuario;
  } //Fin del metodo getUsuario

  /**
   * Establece el usuario de la sesion. 
   * @param usuario el usuario a establecer.
   */
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  } //Fin del metodo setUsuario

  /**
   * Devuelve el modelo del menu a utilizar.
   * @return el modelo del menu del usuario.
   */
  public MenuModel getModelo() {
    return modelo;
  } //Fin del metodo getModelo

  /**
   * Establece el modelo del menu del usuario. 
   * @param modelo el modelo del menu a establecer.
   */
  public void setModelo(MenuModel modelo) {
    this.modelo = modelo;
  } //Fin del metodo setModelo

  /**
   * Genera todos los menus asignado al rol del usuario.
   */
  private void listarMenus() {
    MenuDao dao = new MenuDao();
    try {
      dao.iniciarRecursos();
      this.menus = dao.listarMenusRol(this.usuario.getRol());
    } catch (Exception excepcion) {
      log.error("--> listarMenus() - Error en listando menus. ",excepcion);
    } finally {
      try {
        dao.finalizarRecursos();
      } catch (Exception excepcion) {
        log.error("<-- listarMenus() - Error cerrando recursos. ", excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo listarMenus
  
  /**
   * Establece el modelo utilizado por el menu.
   */
  private void establecerModelo() {
    for (Menu menu : menus) {
      //Verifica si el tipo es submenu
      if (menu.getTipo().equals("submenu")) {
        DefaultSubMenu subMenuPadre = new DefaultSubMenu(menu.getNombre(),menu.getIcono());
        
        //Recorre el listado para obtener los items del menu princial.
        for (Menu menu2 : menus) {
          
          //Obtiene el codigo del menu padre del menu.
          long subMenu = menu2.getMenuPadre();
          
          //Si devolvio algun valor
          if (subMenu != 0) {
            //Verifica que el codigo de menu padre obtenido sea el mismo del menu padre.
            if (subMenu == menu.getId()) {
              DefaultMenuItem item = new DefaultMenuItem(menu2.getNombre());
              establecerPropiedadesMenuItem(item, menu2);
              //Agrega el item al subMenuPadre
              subMenuPadre.addElement(item);
            } //Fin de if interno de codigo de submenu.
          } //Fin de if interno de submenu 
        } //Fin de for interno
        //Agrega el subMenu principal al modelo
        modelo.addElement(subMenuPadre);
      } else {
        //Verifica si el menu tiene un menu padre
        if (menu.getMenuPadre() == 0) {
          DefaultMenuItem item = new DefaultMenuItem(menu.getNombre());
          establecerPropiedadesMenuItem(item, menu);
          //Agrega el menu al modelo
          modelo.addElement(item);
        } //Fin de if interno de verificacion si posee submenu
      } //Fin de if 
    } //Fin de for
  } //Fin del metodo establecerModelo
  
  /**
   * Establece las propiedades de un menu.
   * @param item el item a establecer los datos.
   * @param menu el menu para obtener los datos.
   */
  private void establecerPropiedadesMenuItem(DefaultMenuItem item, Menu menu) {
    item.setUrl(menu.getVentana());
    item.setIcon(menu.getIcono());
  } //Fin del metodo establecerPropiedadesMenu
  
  /**
   * Verifica si el permiso solicitado esta habilitado para el usuario o no.
   * @param permiso el permiso a comprobar.
   * @return si tiene el acceso solicitado.
   */
  public boolean esValido(String permiso) {
    boolean resultado = false;
    Ventana ventanaBuscada = new Ventana();
    if (ubicacionVentana != null) {
      ventanaBuscada.setUbicacion(ubicacionVentana);
      resultado = controlador.estaHabilitado(usuario.getRol(), ventanaBuscada, permiso);    
    } //Fin de if

    return resultado;
  } //Fin del metodo esValido

} //Fin de la clase ManejadorDeAccesos