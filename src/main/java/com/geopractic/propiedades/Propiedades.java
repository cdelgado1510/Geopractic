package com.geopractic.propiedades;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que lee y administra las propiedades dentro del sistema.
 * @author Cristhian
 */
public abstract class Propiedades {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  protected Properties propiedades;

  /**
   * Lee el archivo propiedades y asigna los valores a variables locales.
   * para su uso dentro del sistema
   * @throws ExcepcionPropiedad excepcion lanzada en la lectura del archivo.
   */
  public void leerPropiedades(String nombreArchivo) throws ExcepcionPropiedad {
    ClassLoader cargadorClase = Thread.currentThread().getContextClassLoader();

    try (InputStream entrada = cargadorClase.getResourceAsStream(nombreArchivo)) {
      propiedades = new Properties();
      propiedades.load(entrada);
    } catch (Exception excepcion) {
      throw new ExcepcionPropiedad("Error al obtener el archivo de la propiedad",
         excepcion, this.log); 
    } //Fin de catch

  } //Fin del metodo leerPropiedades
} //Fin de clase Propiedades