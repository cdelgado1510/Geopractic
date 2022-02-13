package com.geopractic.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase utilizada para la creacion de sentencias SQL.
 * @author Cristhian
 */
public class QueryMaker {

  private String nombreTabla;
  private List<Columna> columnas;
  private String condicion;
  private String orden;

  /**
  * Constructor sin argumentos.
  */
  public QueryMaker() {
    columnas = new ArrayList<Columna>();
  } //Constructor sin argumentos

  public String getNombreTabla() {
    return nombreTabla;
  } //Fin del metodo getNombreTabla

  public void setNombreTabla(String nombreTabla) {
    this.nombreTabla = nombreTabla;
  } //Fin del metodo setNombreTabla

  public List<Columna> getColumnas() {
    return columnas;
  } //Fin del metodo getColumnass

  public void setColumnas(List<Columna> columnas) {
    this.columnas = columnas;
  } //Fin del metodo setColumnas

  public String getCondicion() {
    return condicion;
  } //Fin del metodo getCondicion

  public void setCondicion(String condicion) {
    this.condicion = condicion;
  } //Fin del metodo setCondicion

  public String getOrden() {
    return orden;
  } //Fin del metodo getOrden

  public void setOrden(String orden) {
    this.orden = orden;
  } //Fin del metodo setOrden

  /**
   * Agrega una columna nueva al query.
   * @param nombre el nombre de la columna.
   * @param parametro el parametro de la columna.
   * @param esPk si la columna es o no una llave primaria.
   */
  public void agregarColumna(String nombre, String parametro, boolean esPk) {
    Columna columna =  new Columna(nombre, parametro, esPk);
    this.columnas.add(columna);
  } //Fin del metodo agregarColumna
  
  /**
   * Elimina una columna del query a generar.
   * @param nombre el nombre de la columna a eliminar.
   */
  public void eliminarColumna(String nombre) {
    Columna columna = new Columna();
    columna.setNombre(nombre);
    if (this.columnas.contains(columna)) {
      this.columnas.remove(columna);
    } //Fin de if
  } //Fin del metodo eliminarColumna

  /*
   * Metodos para creacion de sentencias SQL.
   */
  
  /**
   * Genera un select de todos los datos de la tabla.
   * @param orden si tiene ORDER BY o no la sentencia.
   * @return la sentencia SQL.
   */
  public String crearSelectAll(boolean orden) {
    StringBuilder builder = new StringBuilder("SELECT * FROM ");
    builder.append(nombreTabla);

    //Verifica si la seleccion tiene un orden.
    if (orden) {
      builder.append(" ORDER BY ");
      builder.append(this.orden);
    } //Fin de if 

    builder.append(" ;");
    return builder.toString();
  } //Fin del metodo crearSelectAll

  /**
   * Genera un select de los datos de la tabla.
   * @param tieneCondicion si tiene WHERE o no la sentencia.
   * @param orden si tiene ORDER BY o no la sentencia.
   * @return la sentencia SQL.
   */
  public String crearSelect(boolean tieneCondicion, boolean orden) {
    StringBuilder builder = new StringBuilder("SELECT ");
    if (columnas.size() > 0) {
      for (Iterator<Columna> iterator = columnas.iterator(); iterator.hasNext();) {
        Columna columna = iterator.next();
        builder.append(columna.getNombre());
        
        if (iterator.hasNext()) {
          builder.append(", ");
        } // Fin de if
      } //Fin de for

      builder.append(" FROM ");
      builder.append(nombreTabla);
      
      //Verifica si se usa una condicion
      if (tieneCondicion) {
        builder.append(" WHERE ");
        builder.append(this.condicion);
      } //Fin de if
      
      //Verifica si la seleccion tiene un orden.
      if (orden) {
        builder.append(" ORDER BY ");
        builder.append(this.orden);
      } //Fin de if 

      builder.append(" ;");
    } else {
      throw new NullPointerException("No hay columnas especificadas.");
    } //Fin de if-else
    return builder.toString();
  } //Fin del metodo crearSelect

  /**
   * Genera un insert de la tabla.
   * @return la sentencia SQL.
   */
  public String crearInsert() {
    StringBuilder builder = new StringBuilder("INSERT INTO ");
    if (columnas.size() > 0) {
      builder.append(nombreTabla);
      builder.append("(");
      ArrayList<String> parametros = new ArrayList<String>();

      //Agrega las columnas a insertar
      for (Iterator<Columna> iterator = columnas.iterator(); iterator.hasNext();) {
        Columna columna = iterator.next();
        builder.append(columna.getNombre());
        parametros.add(columna.getParametro());
        
        if (iterator.hasNext()) {
          builder.append(", ");
        } // Fin de if
      } //Fin de for
      
      builder.append(") VALUES (");

      //Agrega los parametros a insertar
      for (Iterator<String> iterator = parametros.iterator(); iterator.hasNext();) {
        String parametro = (String) iterator.next();
        builder.append(parametro);
        if (iterator.hasNext()) {
          builder.append(", ");
        } // Fin de if
      } //Fin de for

      builder.append(") ;");
    } else {
      throw new NullPointerException("No hay columnas especificadas.");
    } //Fin de if-else
    return builder.toString();
  } //Fin del metodo crearInsert
  
  /**
   * Genera un update de la tabla.
   * @return la sentencia SQL.
   */
  public String crearUpdate() {
    StringBuilder builder = new StringBuilder("UPDATE ");
    if (columnas.size() > 0) {
      builder.append(nombreTabla);
      builder.append(" SET ");

      //Agrega las columnas a insertar
      for (Iterator<Columna> iterator = columnas.iterator(); iterator.hasNext();) {
        Columna columna = iterator.next();
        builder.append(columna.getNombre());
        builder.append(" = ");
        builder.append(columna.getParametro());
        if (iterator.hasNext()) {
          builder.append(", ");
        } // Fin de if
      } //Fin de for
      
      //Verifica las condiciones
      if (condicion != null) {
        builder.append(" WHERE ");
        builder.append(condicion);
      } else {
        throw new NullPointerException("No hay una condicion especificadas.");
      } //Fin de if-else
            
      builder.append(" ;");
    } else {
      throw new NullPointerException("No hay columnas especificadas.");
    } //Fin de if-else
    return builder.toString();
  } //Fin del metodo crearInsert

  /**
   * Genera un delete de la tabla.
   * @param tieneCondicion se tiene WHERE o no la sentencia.
   * @return la sentencia SQL.
   */
  public String crearDelete(boolean tieneCondicion) {
    StringBuilder builder = new StringBuilder("DELETE FROM ");
    builder.append(nombreTabla);
    
    //Verifica si se usa una condicion
    if (tieneCondicion) {
      builder.append(" WHERE ");
      builder.append(this.condicion);
    } //Fin de if
    
    builder.append(" ;");
    return builder.toString();
  } //Fin del metodo crearDelete

} //Fin de la clase querymaker