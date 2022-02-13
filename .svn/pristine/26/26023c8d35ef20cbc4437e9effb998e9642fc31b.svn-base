package com.geopractic.gps.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa la cuenta de la empresa.
 * @author Cristhian
 *
 */
public class Cuenta {

  @JsonProperty("Account")
  private String account;
  @JsonProperty("Account_desc")
  private String accountDesc;
  @JsonProperty("TimeZone")
  private String timeZone;
  @JsonProperty("DeviceList")
  private DeviceList[] deviceList;

  /**
   * Constructor sin argumentos.
   */
  public Cuenta() {
    
  } //Fin del constructor

  public String getAccount() {
    return account;
  } //Fin del metodo getAccount

  public void setAccount(String account) {
    this.account = account;
  } //Fin del metodo setAccount

  public String getAccountDesc() {
    return accountDesc;
  } //Fin del metodo getAccountDesc

  public void setAccountDesc(String accountDesc) {
    this.accountDesc = accountDesc;
  } //Fin del metodo setAccountDesc

  public String getTimeZone() {
    return timeZone;
  } //Fin del metodo getTimeZone

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  } //Fin del metodo setTimeZone

  public DeviceList[] getDeviceList() {
    return deviceList;
  } //Fin del metodo getDeviceList

  public void setDeviceList(DeviceList[] deviceList) {
    this.deviceList = deviceList;
  } //Fin del metodo setDeviceList

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\n account=");
    builder.append(account);
    builder.append("\n, accountDesc=");
    builder.append(accountDesc);
    builder.append("\n, timeZone=");
    builder.append(timeZone);

    //Listado de dispositvos.
    for (DeviceList dispositivo : deviceList) {
      builder.append(dispositivo.toString());
    } //Fin de for
    
    builder.append("]");
    return builder.toString();
  }
  
} //Fin de la clase Cuenta