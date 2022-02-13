package com.geopractic.db;

import com.geopractic.propiedades.ExcepcionPropiedad;
import com.geopractic.propiedades.PropiedadesPostgres;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase singleton para la generacion de conexiones con diferentes datasources.
 * @author Cristhian
 */
public class FabricaDataSource {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  private static FabricaDataSource instancia;
  private static HikariConfig configuracion = new HikariConfig();
  private static Jdbi jdbi;

  /**
   * Constructor privado para Singleton.
   * @throws ExcepcionConexion error lanzado al tratar de generar el datasource.
   */
  private FabricaDataSource() throws ExcepcionConexion {
    generarDatasources();
  } //Fin de contructor privado

  /**
   * Metodo para la obtención de la instanca.
   * @return la instancia a utilizar.
   * @throws ExcepcionConexion error lanzado al tratar de generar el datasource. 
   */
  public static FabricaDataSource obtenerInstancia() throws ExcepcionConexion {
    if (instancia == null) {
      synchronized (FabricaDataSource.class) {
        if (instancia == null) {
          instancia = new FabricaDataSource();
        } //Fin de if interno de instancia
      } //Fin de synchronized
    } //Fin de if
    return instancia;
  } //Fin del metodo obtenerInstancia

  /**
   * Genera todos los datasource a utilizar por la aplicacion.
   * @throws ExcepcionConexion error lanzado al tratar de generar el datasource.
   */
  private void generarDatasources() throws ExcepcionConexion {
    log.info("--> Generando DataSource de Postgresql.");

    configuracion = new HikariConfig();

    try {
      configuracion.setUsername(PropiedadesPostgres.obtenerInstancia().getUserAdmin());
      configuracion.setPassword(PropiedadesPostgres.obtenerInstancia().getSenhaAdmin());
      StringBuilder url = new StringBuilder("jdbc:postgresql://");
      url.append(PropiedadesPostgres.obtenerInstancia().getServidor());
      url.append(":" + PropiedadesPostgres.obtenerInstancia().getPuerto());
      url.append("/" + PropiedadesPostgres.obtenerInstancia().getBaseDeDatos());

      configuracion.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
      configuracion.setJdbcUrl(url.toString());
      configuracion.setAutoCommit(false);
      configuracion.setMaximumPoolSize(20);

      jdbi = Jdbi.create(new HikariDataSource(configuracion)).installPlugin(new PostgresPlugin());
    } catch (ExcepcionPropiedad excepcion) {
      throw new ExcepcionConexion("--> generarDatasources - Error en generarDatasources()",
            excepcion, this.log);
    } //Fin de try-catch

  } //Fin del metodo generarDatasources

  /**
   * Devuelve el la conexion creada por el Jdbi.
   * @return el Handle establecido por el Jdbi.
   */
  public Handle obtenerConexion() {
    return jdbi.open();
  } //Fin del metodo obtenerConexion

} //Fin de la clase FabricaDataSource