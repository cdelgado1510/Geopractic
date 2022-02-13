package com.geopractic.util;

import com.geopractic.db.AdministradorConexion;
import com.geopractic.db.ExcepcionConexion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jdbi.v3.core.Handle;
import org.primefaces.model.DefaultStreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que generar los diferentes reportes del sistema.
 * @author Cristhian
 *
 */
public class GenerarReporte {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  private final String carpetaReportes = "reportes/";

  /**
   * Obtiene un reporte en forma de un OutputStream.
   * @param lista la lista de objetos a ingresar en el reporte.
   * @param parametros los parametrosa utilizar en el reporte.
   * @param reporte el nombre del reporte a utilizar.
   * @return el stream del reporte generado.
   * @throws ExcepcionUtilidades excepcion lanzada desde la utilizad.
   */
  public ByteArrayOutputStream obtenerOutputStreamDeReporte(List<?> lista,
        Map<String, Object> parametros, String reporte) throws ExcepcionUtilidades {
    ByteArrayOutputStream streamSalida = new ByteArrayOutputStream();
    ClassLoader cargadorClase = Thread.currentThread().getContextClassLoader();
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
    JasperPrint jasperPrint = null;

    try (InputStream archivoReporte = cargadorClase
        .getResourceAsStream(carpetaReportes + reporte + ".jrxml")) {
      jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(archivoReporte),
          parametros, dataSource);
      JasperExportManager.exportReportToPdfStream(jasperPrint, streamSalida);
      streamSalida.flush();
      streamSalida.close();
    } catch (JRException excepcion) {
      throw new ExcepcionUtilidades("Error al generar el reporte Jasper ",
          excepcion, this.log);
    } catch (IOException excepcion) {
      throw new ExcepcionUtilidades("Error de IO al generar el Stream ",
          excepcion, this.log); 
    } //Fin de try-catch
    return streamSalida;
  } //Fin del metodo obtenerOutputStreamDeReporte

  /**
   * Obtiene un reporte en forma de un OutputStream.
   * @param parametros los parametrosa utilizar en el reporte.
   * @param reporte el nombre del reporte a utilizar.
   * @return el stream del reporte generado.
   * @throws ExcepcionUtilidades excepcion lanzada desde la utilizad.
   */
  public ByteArrayOutputStream obtenerOutputStreamDeReporte(
        Map<String, Object> parametros, String reporte) throws ExcepcionUtilidades {
    ByteArrayOutputStream streamSalida = new ByteArrayOutputStream();
    ClassLoader cargadorClase = Thread.currentThread().getContextClassLoader();
    JasperPrint jasperPrint = null;
    Handle conexion = null;
    try (InputStream archivoReporte = cargadorClase
        .getResourceAsStream(carpetaReportes + reporte + ".jrxml")) {
      conexion = AdministradorConexion.obtenerInstancia().iniciarRecursos(log, this);
      jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(archivoReporte),
              parametros,conexion.getConnection());
      JasperExportManager.exportReportToPdfStream(jasperPrint, streamSalida);
      streamSalida.flush();
      streamSalida.close();
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionUtilidades("Error al generar la conexion a la base de datos ",
          excepcion, this.log);
    } catch (JRException excepcion) {
      throw new ExcepcionUtilidades("Error al generar el reporte Jasper ",
          excepcion, this.log);
    } catch (IOException excepcion) {
      throw new ExcepcionUtilidades("Error de IO al generar el Stream ",
          excepcion, this.log); 
    } finally {
      if (conexion != null) {
        try {
          AdministradorConexion.obtenerInstancia().finalizarRecursos(conexion, log, this);
        } catch (ExcepcionConexion excepcion) {
          throw new ExcepcionUtilidades("--> finalizarRecursos() - Error al cerrar la conexion"
              + " y recursos de la base de datos.",excepcion,this.log);
        } //Fin de try-catch
      } //Fin de if
    } //Fin de try-catch
    return streamSalida;
  } //Fin del metodo obtenerOutputStreamDeReporte

  /**
   * Genera un StreamedContent para objetos media a partir de un stream ya generado. 
   * @param streamSalida el stream a utilizar.
   * @param tipoContenido el tipo de contenido a generar.
   * @param nombreArchivo el nombre de asignado al archivo.
   * @return el StreamedContent a utilizar con componentes PrimeFaces.
   */
  public DefaultStreamedContent obtenerStreamContentDeOutputStream(
      ByteArrayOutputStream streamSalida,String tipoContenido, String nombreArchivo) {
    DefaultStreamedContent archivo = null;
    InputStream streamEntrada = new ByteArrayInputStream(streamSalida.toByteArray());
    archivo = new DefaultStreamedContent(streamEntrada, tipoContenido, nombreArchivo);
    return archivo;
  } //Fin del metodo obtenerStreamContentDeOutputStream

} //Fin de la clase GenerarReporte