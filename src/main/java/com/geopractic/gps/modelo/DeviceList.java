package com.geopractic.gps.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa los dispositivos de rastreo. 
 * @author Cristhian
 *
 */
public class DeviceList {

  @JsonProperty("Device")
  private String device;
  @JsonProperty("Device_desc")
  private String deviceDesc;
  @JsonProperty("EventData")
  private EventData[] eventData;

  /**
   * Constructor sin argumentos.
   */
  public DeviceList() {

  } //Fin del constructor

  public String getDevice() {
    return device;
  } //Fin del metodo getDevice

  public void setDevice(String device) {
    this.device = device;
  } //Fin del metodo setDevice

  public String getDeviceDesc() {
    return deviceDesc;
  } //Fin del metodo getDeviceDesc

  public void setDeviceDesc(String deviceDesc) {
    this.deviceDesc = deviceDesc;
  } //Fin del metodo setDeviceDesc

  public EventData[] getEventData() {
    return eventData;
  } //Fin del metodo getEventData

  public void setEventData(EventData[] eventData) {
    this.eventData = eventData;
  } //Fin del metodo setEventData

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nDispositivo [\ndevice=");
    builder.append(device);
    builder.append("\n, deviceDesc=");
    builder.append(deviceDesc);
    
    //Listado de eventos
    for (EventData evento : eventData) {
      builder.append(evento.toString());
    } //Fin de for
    
    builder.append("\n]");
    return builder.toString();
  } //Fin de toString

} //Fin de la clase DeviceList