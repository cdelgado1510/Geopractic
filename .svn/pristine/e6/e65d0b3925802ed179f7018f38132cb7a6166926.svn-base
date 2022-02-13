package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.sistema.mapeos.LogsMapeo;
import com.geopractic.sistema.modelo.Logs;

public class LogsDao extends Dao<Logs> {

  public LogsDao() {
    queryListarTodos = "SELECT * FROM \"logs\" ORDER BY \"fecha\" DESC ";
  } //Fin de constructor 

  @Override
  public void iniciarMapeos() {
    mapeoBean = new LogsMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Logs log, Logs logNuevo) {
    log.setId(logNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

} //Fin de la clase LogsDao