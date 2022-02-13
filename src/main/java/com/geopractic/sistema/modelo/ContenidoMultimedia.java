package com.geopractic.sistema.modelo;

import java.io.ByteArrayOutputStream;

import org.primefaces.model.DefaultStreamedContent;

/**
 * Clase que representa el contenido multimedia que se desea mostrar.
 * @author Cristhian
 *
 */
public class ContenidoMultimedia {

  private DefaultStreamedContent  streamContent;
  private ByteArrayOutputStream streamSalida;

  public ContenidoMultimedia() {

  } //Fin de constructor

  public ContenidoMultimedia(DefaultStreamedContent streamContent,
       ByteArrayOutputStream streamByte) {
    this.streamContent = streamContent;
    this.streamSalida = streamByte;
  } //Fin de constructor con argumentos

  public DefaultStreamedContent getStreamContent() {
    return streamContent;
  } //Fin del metodo getStreamContent

  public void setStreamContent(DefaultStreamedContent streamContent) {
    this.streamContent = streamContent;
  } //Fin del metodo setStreamContent

  public ByteArrayOutputStream getByteArrayStream() {
    return streamSalida;
  } //Fin del metodo getByteArrayStream

  public void setByteArrayStream(ByteArrayOutputStream streamSalida) {
    this.streamSalida = streamSalida;
  } //Fin del metodo setByteArrayStream

} //Fin de la clase ContenidoMultimedia