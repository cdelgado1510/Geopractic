package com.geopractic.bean;

import com.geopractic.sistema.modelo.ContenidoMultimedia;
import javax.faces.application.FacesMessage.Severity;

/**
 * Funciones generales utilizadas por los Bean generadores de reportes.
 * @author Cristhian
 *
 */
public interface IReporteable {

  /**
   * Inicial las variables de la clase para realizar operaciones. 
   */
  void prepararVariables();

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

  /**
   * Genera los parametros utilizados en los reportes.
   */
  void generarParametros();

  /**
   * Genera un reporte en base a Jasper.
   * @param reporte el nombre del reporte en formato jasper a usar.
   * @param nombreArchivo el nombre del reporte generado.
   * @return el reporte como un objeto stream multimedia.
   */
  ContenidoMultimedia generateReporte(String reporte,String nombreArchivo); 
} //Fin de la interface IReporteable