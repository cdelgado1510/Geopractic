package com.geopractic.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage.Severity;

/**
 * Funciones generales utilizadas por los Bean.
 * @author Cristhian
 *
 */
public interface IBean {

  /**
  * Procedimientos realizados al iniciar el bean.
  */
  @PostConstruct
  public void init();

  /**
   * Devuelve la funcion a realizar dentro del ABM.
   * @return la funcion de agregar o modificar.
   */
  String getFuncion();

  /**
   * Establece la funcion de agregar o modificar a realizar.
   * @param funcion la funcion a realizar.
   */
  void setFuncion(String funcion);

  /**
   * Inicial las variables de la clase para realizar operaciones. 
   */
  void prepararVariables();

  /**
   * Inicia el controlador a utilizar. 
   */
  void prepararControlador();
  
  /**
   * Limpia la variable de entidad y genera una nueva
   * dentro de la variable local del Bean.
   */
  void generarNuevaEntidad();
  
  /**
   * Agrega un mensaje al contexto.
   * @param severidad la severidad del mensaje. 
   * @param resumen el resumen o titulo del mensaje.
   * @param detalle el detalle del mensaje.
   * @param componente el componente al mostrar el mensaje
   *     , en caso de ser nulo es <b>"mensajes".</b>
   */
  void agregarMensaje(Severity severidad,String resumen, String detalle,String componente);

  /**
   * Resetea el estado de un objeto ingresado.
   * @param objeto el objeto a resetear.
   */
  void resetear(String objeto);

  /**
   * Abre una ventana de dialogo ingresada.
   * @param dialogo el nombre del dialogo a abrir.
   */
  void abrirDialogo(String dialogo);

  /**
   * Cierra una ventana de dialogo ingresada.
   * @param dialogo el nombre del dialogo a cerrar.
   */
  void cerrarDialogo(String dialogo);

} //Fin de la interface IBean