package com.geopractic.georrefencia.modelo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geopractic.cobranza.modelo.RuteoCobrador;
import com.geopractic.gps.modelo.Cuenta;
import com.geopractic.gps.modelo.DeviceList;
import com.geopractic.gps.modelo.EventData;
import com.geopractic.util.FechaUtilidad;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Clase de utilidad para generacion de GeoJson.
 * @author Cristhian
 *
 */
public class GeneradorJson {

  /**
   * Convierte una geometria en un objeto GeoJson.
   * @param georreferencia la georreferencia a convertir.
   * @return el string en formato GeoJson.
   * @throws JsonProcessingException error al generar el Json. 
   */
  public static String generarGeoJson(Georreferencia georreferencia) 
      throws JsonProcessingException {
    ObjectMapper mapeador = new ObjectMapper();

    ObjectNode nodoPadre = mapeador.createObjectNode();

    nodoPadre.put("type", "Feature");

    ObjectNode nodoPropiedades = mapeador.createObjectNode();
    nodoPropiedades.put("Id", georreferencia.getId());
    nodoPropiedades.put("Descripcion", georreferencia.getDescripcion());
    nodoPropiedades.put("Tipo", georreferencia.getTipo().getDescripcion());
    nodoPropiedades.put("Geometria", georreferencia.getTipo().getClase());

    //Verifica si el cliente no es nulo
    if (georreferencia.getCliente() != null) {
      nodoPropiedades.put("Cliente", georreferencia.getCliente().getNombreCompleto());
    } //Fin de if

    ObjectNode nodoGeometria = mapeador.createObjectNode();
    if (georreferencia.getTipo().getClase().equals("Punto")) {
      nodoPropiedades.put("icono", georreferencia.getTipo().getIcono());
      nodoGeometria.put("type", "Point");
      ArrayNode coordenadas = mapeador.createArrayNode();
      coordenadas.add(georreferencia.getPunto().getLongitud());
      coordenadas.add(georreferencia.getPunto().getLatitud());
      nodoGeometria.set("coordinates", coordenadas);
    } else {
      nodoPropiedades.put("relleno", georreferencia.getTipo().getColorRelleno());
      nodoPropiedades.put("contorno", georreferencia.getTipo().getColorContorno());
      nodoGeometria.put("type", "Polygon");      
      ArrayNode array = mapeador.createArrayNode();
      ArrayNode coordenadas = mapeador.createArrayNode();
      //Recorre todos los puntos de la zona
      for (Punto punto : georreferencia.getZona().getPuntos()) {
        ArrayNode puntos = mapeador.createArrayNode();
        puntos.add(punto.getLongitud());
        puntos.add(punto.getLatitud());
        coordenadas.add(puntos);
      } //Fin de for
      array.add(coordenadas);
      nodoGeometria.set("coordinates", array);
    } //Fin de if

    nodoPadre.set("properties", nodoPropiedades);
    nodoPadre.set("geometry", nodoGeometria);

    return mapeador.writeValueAsString(nodoPadre);
  } //Fin del metodo generarGeoJson

  /**
   * Convierte el recorrido OpenGTS en un objeto GeoJson.
   * @param cuenta la cuenta con el recorrido a convertir.
   * @return el string en formato GeoJson.
   * @throws JsonProcessingException error al generar el Json. 
   */
  public static String generarGeoJson(Cuenta cuenta) 
      throws JsonProcessingException {
    ObjectMapper mapeador = new ObjectMapper();

    ObjectNode nodoPadre = mapeador.createObjectNode();
    nodoPadre.put("type", "FeatureCollection");

    DeviceList[] rastreador = cuenta.getDeviceList();
    EventData[] recorrido = rastreador[0].getEventData();

    ArrayNode puntos = mapeador.createArrayNode();
    //Recorre todos los puntos
    for (EventData punto : recorrido) {
      Date fecha = null;
      try {
        fecha = FechaUtilidad.convertirEpocaEnFecha(Long.parseLong(punto.getTimeStamp()));
      } catch (NumberFormatException | ParseException excepcion) {
        System.out.println("Error al parsear o de formato de TimeStamp" + excepcion);
      } //Fin de try

      ObjectNode ubicacion = mapeador.createObjectNode();
      ubicacion.put("type", "Feature");

      ObjectNode nodoPropiedades = mapeador.createObjectNode();
      nodoPropiedades.put("Fecha", new java.text.SimpleDateFormat("dd/MM/yyyy")
           .format(fecha));
      nodoPropiedades.put("Hora", new java.text.SimpleDateFormat("HH:mm")
           .format(fecha));
      nodoPropiedades.put("Velocidad", Double.parseDouble(punto.getSpeed()));
      nodoPropiedades.put("time", new java.text.SimpleDateFormat(" dd/MM/yyyy HH:mm")
              .format(fecha));
      ubicacion.set("properties", nodoPropiedades);

      ObjectNode nodoGeometria = mapeador.createObjectNode();
      nodoGeometria.put("type", "Point");
      ArrayNode coordenadas = mapeador.createArrayNode();
      coordenadas.add(Double.parseDouble(punto.getGpsPointLon()));
      coordenadas.add(Double.parseDouble(punto.getGpsPointLat()));
      nodoGeometria.set("coordinates", coordenadas);
      ubicacion.set("geometry", nodoGeometria);

      puntos.add(ubicacion);
    } //Fin de for

    nodoPadre.set("features", puntos);
    return mapeador.writeValueAsString(nodoPadre);
  } //Fin del metodo generarGeoJson

  /**
   * Convierte el listado de ruteos ingresado en un objeto GeoJson.
   * @param listaRuteo la lista de ruteos ingresada
   * @return el string en formato GeoJson.
   * @throws JsonProcessingException error al generar el Json. 
   */
  public static String generarGeoJson(List<RuteoCobrador> listaRuteo) 
          throws JsonProcessingException {
    ObjectMapper mapeador = new ObjectMapper();
    ObjectNode nodoPadre = mapeador.createObjectNode();
    nodoPadre.put("type", "FeatureCollection");

    ArrayNode georreferencias = mapeador.createArrayNode();
    for (RuteoCobrador ruteo : listaRuteo) {

      ObjectNode lugarVisita = mapeador.createObjectNode();
      lugarVisita.put("type", "Feature");

      //Genera las propiedades del GeoJson
      ObjectNode propiedades = mapeador.createObjectNode();
      propiedades.put("EmpleadoCarga", ruteo.getGestion().getEmpleadoCarga().getNombre()
            + " " + ruteo.getGestion().getEmpleadoCarga().getApellido());
      propiedades.put("Lugar", ruteo.getGestion().getLugarVisita().getDescripcion());
      propiedades.put("Monto", ruteo.getGestion().getMonto());
      propiedades.put("FormaDePago", ruteo.getGestion().getFormaDePago().getDescripcion());
      propiedades.put("Motivo", ruteo.getGestion().getMotivo().getDescripcion());
      propiedades.put("Visitado", ruteo.isVisitado());
      propiedades.put("Cumplito", ruteo.isVerificado());

      ObjectNode geometria = mapeador.createObjectNode();
      if (ruteo.getGestion().getLugarVisita().getTipo().getClase().equals("Punto")) {
        propiedades.put("icono", ruteo.getGestion().getLugarVisita().getTipo().getIcono());
        geometria.put("type", "Point");
        ArrayNode coordenadas = mapeador.createArrayNode();
        coordenadas.add(ruteo.getGestion().getLugarVisita().getPunto().getLongitud());
        coordenadas.add(ruteo.getGestion().getLugarVisita().getPunto().getLatitud());
        geometria.set("coordinates", coordenadas);
      } else {
        propiedades.put("relleno", ruteo.getGestion().getLugarVisita().getTipo().getColorRelleno());
        propiedades.put("contorno", ruteo.getGestion().getLugarVisita().getTipo()
             .getColorContorno());
        geometria.put("type", "Polygon");      
        ArrayNode array = mapeador.createArrayNode();
        ArrayNode coordenadas = mapeador.createArrayNode();
        //Recorre todos los puntos de la zona
        for (Punto punto : ruteo.getGestion().getLugarVisita().getZona().getPuntos()) {
          ArrayNode puntos = mapeador.createArrayNode();
          puntos.add(punto.getLongitud());
          puntos.add(punto.getLatitud());
          coordenadas.add(puntos);
        } //Fin de for
        array.add(coordenadas);
        geometria.set("coordinates", array);
      } //Fin de if

      lugarVisita.set("properties", propiedades);
      lugarVisita.set("geometry", geometria);

      georreferencias.add(lugarVisita);
    } //Fin de ruteo

    nodoPadre.set("features", georreferencias);
    return mapeador.writeValueAsString(nodoPadre);
  } //Fin del metodo generarGeoJson

} //Fin de la clase GeneradorJson