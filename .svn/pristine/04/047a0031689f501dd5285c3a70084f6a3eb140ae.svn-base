package com.geopractic.gps;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.gps.modelo.Cuenta;
import com.geopractic.gps.modelo.Rastreador;
import com.geopractic.propiedades.ExcepcionPropiedad;
import com.geopractic.propiedades.PropiedadesRastreo;
import com.geopractic.util.FechaUtilidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultasOpenGts {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  /**
   * Obtiene el listado de recorrido de un rango de fecha ingresado.
   * @param rastreador el dispositivo a buscar.
   * @param desde la fecha de inicio de la busqueda.
   * @param hasta la fecha final de la busqueda.
   * @return el listado de eventos a buscar.
   * @throws ExcepcionControlador error lanzado al momento de obtener el recorrido. 
   */
  public Cuenta obtenerRecorrido(Rastreador rastreador,Date desde, Date hasta)
        throws ExcepcionControlador {

    PropiedadesRastreo propiedades;
    Cuenta recorrido;

    try {
      log.debug("Default Charset=" + Charset.defaultCharset());
      //Genera la URL para consultar el recorrido
      propiedades = PropiedadesRastreo.obtenerInstancia();
      StringBuilder direccion = new StringBuilder(propiedades.getInicio());
      direccion.append("://" + propiedades.getServidor() + ":" + propiedades.getPuerto());
      direccion.append(propiedades.getDireccion() + "?a=" + propiedades.getCuenta());
      direccion.append("&u=" + propiedades.getUsuario() + "&p=" + propiedades.getContrasenha());
      direccion.append("&d=" + rastreador.getImei());
      direccion.append("&rf=" + FechaUtilidad.convertirFechaEnEpoca(desde));
      direccion.append("&rt=" + FechaUtilidad.convertirFechaEnEpoca(hasta));
      direccion.append("&l=1000");

      log.debug("Consulta URL: " + direccion.toString());
      URL url = new URL(direccion.toString());
      HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
      conexion.setRequestMethod("GET");
      conexion.setRequestProperty("Content-Type", "application/json");
      conexion.setRequestProperty("Accept-Charset", "UTF-8");

      BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
      String inputLine;
      StringBuffer respuesta = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        respuesta.append(inputLine);
      } //Fin de while
      in.close();

      ObjectMapper mapeadorJson = new ObjectMapper();
      recorrido = mapeadorJson.readValue(respuesta.toString(), Cuenta.class);

    } catch (ExcepcionPropiedad excepcion) {
      throw new ExcepcionControlador("--> obtenerRecorrido() - Error al obtener las propiedades"
          + " del servidor de rastreo. ",excepcion,this.log);
    } catch (ParseException excepcion) {
      throw new ExcepcionControlador("--> obtenerRecorrido() - Error al parsear las fechas"
          + " ingresadas. ",excepcion,this.log);
    } catch (MalformedURLException excepcion) {
      throw new ExcepcionControlador("--> obtenerRecorrido() - Error de URL mal formada. ",
            excepcion,this.log);
    } catch (JsonParseException excepcion) {
      throw new ExcepcionControlador("--> obtenerRecorrido() - Error al parsear el JSON"
          + " solicitado. ",excepcion,this.log);
    } catch (JsonMappingException excepcion) {
      throw new ExcepcionControlador("--> obtenerRecorrido() - Error al mapear el JSON. ",
            excepcion,this.log);
    } catch (IOException excepcion) {
      throw new ExcepcionControlador("--> obtenerRecorrido() - Error de obtencion de"
          + " Entrada/Salida. ",excepcion,this.log);
    } //Fin de try-catch

    return recorrido;
  } //Fin del metodo obtenerRecorrido

} //Fin de la clase ConsultasOpenGts