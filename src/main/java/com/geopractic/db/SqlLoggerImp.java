package com.geopractic.db;

import java.sql.SQLException;

import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlLoggerImp implements SqlLogger {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  @Override
  public void logBeforeExecution(StatementContext contexto) {
    log.debug("Sentencia SQL ejecutada: " + contexto.getStatement());
  } //Fin del  metodo logBeforeExecution

  @Override
  public void logException(StatementContext contexto, SQLException excepcion) {
    log.error("Sentencia SQL ejecutada: " + contexto.getStatement()
         + " Causa: " + excepcion.getCause() + ":" + excepcion.getMessage());
  } //Fin del  metodo logException

} //Fin del metodo SqlLoggerImp