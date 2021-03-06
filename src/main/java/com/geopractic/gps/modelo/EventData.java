package com.geopractic.gps.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa los eventos solicitados de un dispositivo.
 * @author Cristhian
 */
@JsonIgnoreProperties({"StatusCode_desc"})
public class EventData implements Comparable<EventData> {

  @JsonProperty("Device")
  private String device;
  @JsonProperty("Timestamp")
  private String timeStamp;
  @JsonProperty("Timestamp_date")
  private String timeStampDate;
  @JsonProperty("Timestamp_time")
  private String timeStampTime;
  @JsonProperty("StatusCode")
  private String statusCode;
  @JsonProperty("StatusCode_hex")
  private String statusCodeHex;
  @JsonProperty("StatusCode_desc")
  private String statusCodeDesc;
  @JsonProperty("GPSPoint")
  private String gpsPoint;
  @JsonProperty("GPSPoint_lat")
  private String gpsPointLat;
  @JsonProperty("GPSPoint_lon")
  private String gpsPointLon;
  @JsonProperty("Speed_kph")
  private String speedKph;
  @JsonProperty("Speed")
  private String speed;
  @JsonProperty("Speed_units")
  private String speedUnits;
  @JsonProperty("Odometer_km")
  private String odometerKm;
  @JsonProperty("Odometer")
  private String odometer;
  @JsonProperty("Odometer_units")
  private String odometerUnits;
  @JsonProperty("Index")
  private String index;
  @JsonProperty("Heading")
  private String heading;
  @JsonProperty("Heading_desc")
  private String headingDesc;

  /**
   * Constructor sin argumentos.
   */
  public EventData() {

  } //Fin de constructor

  public String getDevice() {
    return device;
  } //Fin del metodo getDevice

  public void setDevice(String device) {
    this.device = device;
  } //Fin del metodo setDevice

  public String getTimeStamp() {
    return timeStamp;
  } //Fin del metodo getTimeStamp

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  } //Fin del metodo setTimeStamp

  public String getTimeStampDate() {
    return timeStampDate;
  } //Fin del metodo getTimeStampDate

  public void setTimeStampDate(String timeStampDate) {
    this.timeStampDate = timeStampDate;
  } //Fin del metodo setTimeStampDate

  public String getTimeStampTime() {
    return timeStampTime;
  } //Fin del metodo getTimeStampTime

  public void setTimeStampTime(String timeStampTime) {
    this.timeStampTime = timeStampTime;
  } //Fin del metodo setTimeStampTime

  public String getStatusCode() {
    return statusCode;
  } //Fin del metodo getStatusCode

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  } //Fin del metodo setStatusCode

  public String getStatusCodeHex() {
    return statusCodeHex;
  } //Fin del metodo getStatusCodeHex

  public void setStatusCodeHex(String statusCodeHex) {
    this.statusCodeHex = statusCodeHex;
  } //Fin del metodo setStatusCodeHex

  public String getStatusCodeDesc() {
    return statusCodeDesc;
  } //Fin del metodo getStatusCodeDesc

  public void setStatusCodeDesc(String statusCodeDesc) {
    this.statusCodeDesc = statusCodeDesc;
  } //Fin del metodo setStatusCodeDesc

  public String getGpsPoint() {
    return gpsPoint;
  } //Fin del metodo getGpsPoint

  public void setGpsPoint(String gpsPoint) {
    this.gpsPoint = gpsPoint;
  } //Fin del metodo setGpsPoint

  public String getGpsPointLat() {
    return gpsPointLat;
  } //Fin del metodo getGpsPointLat

  public void setGpsPointLat(String gpsPointLat) {
    this.gpsPointLat = gpsPointLat;
  } //Fin del metodo setGpsPointLat

  public String getGpsPointLon() {
    return gpsPointLon;
  } //Fin del metodo getGpsPointLon

  public void setGpsPointLon(String gpsPointLon) {
    this.gpsPointLon = gpsPointLon;
  } //Fin del metodo setGpsPointLon

  public String getSpeedKph() {
    return speedKph;
  } //Fin del metodo getSpeedKph

  public void setSpeedKph(String speedKph) {
    this.speedKph = speedKph;
  } //Fin del metodo setSpeedKph

  public String getSpeed() {
    return speed;
  } //Fin del metodo getSpeed

  public void setSpeed(String speed) {
    this.speed = speed;
  } //Fin del metodo setSpeed

  public String getSpeedUnits() {
    return speedUnits;
  } //Fin del metodo getSpeedUnits 

  public void setSpeedUnits(String speedUnits) {
    this.speedUnits = speedUnits;
  } //Fin del metodo setSpeedUnits

  public String getOdometerKm() {
    return odometerKm;
  } //Fin del metodo getOdometerKm

  public void setOdometerKm(String odometerKm) {
    this.odometerKm = odometerKm;
  } //Fin del metodo setOdometerKm

  public String getOdometer() {
    return odometer;
  } //Fin del metodo getOdometer

  public void setOdometer(String odometer) {
    this.odometer = odometer;
  } //Fin del metodo setOdometer

  public String getOdometerUnits() {
    return odometerUnits;
  } //Fin del metodo getOdometerUnits

  public void setOdometerUnits(String odometerUnits) {
    this.odometerUnits = odometerUnits;
  } //Fin del metodo setOdometerUnits

  public String getHeading() {
    return heading;
  } //Fin del metodo getHeading

  public void setHeading(String heading) {
    this.heading = heading;
  } //Fin del metodo setHeading

  public String getHeadingDesc() {
    return headingDesc;
  } //Fin del metodo getHeadingDesc

  public void setHeadingDesc(String headingDesc) {
    this.headingDesc = headingDesc;
  } //Fin del metodo setHeadingDesc

  public String getIndex() {
    return index;
  } //Fin del metodo getIndex

  public void setIndex(String index) {
    this.index = index;
  } //Fin del metodo setIndex

  @Override
  public int compareTo(EventData otro) {
    return Integer.parseInt(this.timeStamp) - Integer.parseInt(otro.timeStamp);
  } //Fin de compareTo

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nEvento [\ndevice=");
    builder.append(device);
    builder.append("\n, timeStamp=");
    builder.append(timeStamp);
    builder.append("\n, timeStampDate=");
    builder.append(timeStampDate);
    builder.append("\n, timeStampTime=");
    builder.append(timeStampTime);
    builder.append("\n, statusCode=");
    builder.append(statusCode);
    builder.append("\n, statusCodeHex=");
    builder.append(statusCodeHex);
    builder.append("\n, statusCodeDesc=");
    builder.append(statusCodeDesc);
    builder.append("\n, gpsPoint=");
    builder.append(gpsPoint);
    builder.append("\n, gpsPointLat=");
    builder.append(gpsPointLat);
    builder.append("\n, gpsPointLon=");
    builder.append(gpsPointLon);
    builder.append("\n, speedKph=");
    builder.append(speedKph);
    builder.append("\n, speed=");
    builder.append(speed);
    builder.append("\n, speedUnits=");
    builder.append(speedUnits);
    builder.append("\n, odometerKm=");
    builder.append(odometerKm);
    builder.append("\n, odometer=");
    builder.append(odometer);
    builder.append("\n, odometerUnits=");
    builder.append(odometerUnits);
    builder.append("\n, heading=");
    builder.append(heading);
    builder.append("\n, headingDesc=");
    builder.append(headingDesc);
    builder.append("\n, index=");
    builder.append(index);
    builder.append("\n]");
    return builder.toString();
  } //Fin de toString
  
} //Fin de la clase EventData