package com.geopractic.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.DecoderException;

/**
 * Clase para le manejo de la encryptacion de datos.
 * @author Cristhian
 *
 */
public class Encriptador {

  /**
   * Constructor privado.
   */
  private Encriptador() {}
  
  /**
   * Genera un codigo aleatorio Salt a ser utilizado.
   * @return el array de bits a utilizar.
   * @throws NoSuchAlgorithmException error lanzado en caso de usar algoritmos invalidos.
   */
  public static byte[] salarSiguiente() throws NoSuchAlgorithmException {
  
    //Generador aleatorio
    SecureRandom aleatorio = SecureRandom.getInstance("SHA1PRNG");
    //Crea el array sal
    byte[] sal = new byte[16];
    //Genera el valor aleatorio para la sal
    aleatorio.nextBytes(sal);
    return sal;
  } //Fin del metodo salarSiguiente 
  
  /**
   * Metodo de encriptación del texto. 
   * @param textoAEncriptar el texto a encriptar.
   * @return el texto encriptado.
   * @throws InvalidKeySpecException error de llave de especificacion de llave invalida.
   * @throws NoSuchAlgorithmException  error de algoritmo de encriptacion no existente.
   */
  public static String encriptarTexto(String textoAEncriptar, byte[] sal)
      throws NoSuchAlgorithmException, InvalidKeySpecException {

    char[] characterDeTexto = textoAEncriptar.toCharArray();
    int iteraciones = 10000;
    int largoDeLlave = 512;
    
    SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    PBEKeySpec especificaciones = new PBEKeySpec(characterDeTexto,sal, iteraciones, largoDeLlave);
    SecretKey llave = secretFactory.generateSecret(especificaciones);
    byte[] textoHasheado = llave.getEncoded();
    
    return iteraciones + ":" + convertirAHex(sal) + ":" + convertirAHex(textoHasheado);
  } //Fin del metodo encriptarTexto
  
  /**
   * Convierte un array de bits a Hexagecimal.
   * @param array el array de bits a convertir.
   * @return el text en hexagecimal.
   * @throws NoSuchAlgorithmException error de algoritmo de encriptacion no existente.
   */
  private static String convertirAHex(byte[] array) throws NoSuchAlgorithmException {
    BigInteger bigInt = new BigInteger(1, array);
    String hex = bigInt.toString(16);
    int largo = (array.length * 2) - hex.length();
    if (largo > 0) {
      return String.format("%0"  + largo + "d", 0) + hex;
    } else {
      return hex;
    } //Fin de if else
  } //Fin del metodo convertirAHex
  
  /**
   * Valida si el texto ingresado sea igual al texto encriptado.
   * @param textoAValidar el texto a comprobar.
   * @param textEncriptado el texto encriptado a comprobar.
   * @return si es valido o no el texto.
   * @throws InvalidKeySpecException error de llave de especificacion de llave invalida.
   * @throws NoSuchAlgorithmException  error de algoritmo de encriptacion no existente.
   * @throws DecoderException error del decodificador.
   */
  public static boolean validarTexto(String textoAValidar, String textEncriptado)
      throws InvalidKeySpecException, NoSuchAlgorithmException, DecoderException {
    String[] partes = textEncriptado.split(":");
    int iteraciones = Integer.parseInt(partes[0]);
    byte[] sal = hexATexto(partes[1]);
    byte[] textoEncriptadoHash = hexATexto(partes[2]);

    PBEKeySpec especificaciones = new PBEKeySpec(
         textoAValidar.toCharArray(), sal, iteraciones, textoEncriptadoHash.length * 8);
    SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hashResultado = secretFactory.generateSecret(especificaciones).getEncoded();
    
    
    int diferencia = textoEncriptadoHash.length ^ hashResultado.length;
    
    for (int i = 0; i < textoEncriptadoHash.length && i < hashResultado.length; i++) {
      diferencia |= textoEncriptadoHash[i] ^ hashResultado[i];
    } //Fin de for
    
    return diferencia == 0;
  } //Fin del metodo validarTexto
    
  /**
   * Convierte un Hexagecimal a un array de bits.
   * @param hex el text en hexagecimal.
   * @return el array de bits.
   * @throws NoSuchAlgorithmException error de algoritmo de encriptacion no existente.
   */
  private static byte[] hexATexto(String hex) throws NoSuchAlgorithmException {
    byte[] bytes = new byte[hex.length() / 2];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    } //Fin de for
    return bytes;
  } //Fin del metodo hexATexto
} //Fin de clase Encriptador