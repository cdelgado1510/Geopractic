package com.geopractic.cobranza;

import com.geopractic.cobranza.dao.GestionVisitaDao;
import com.geopractic.cobranza.dao.SaldoDao;
import com.geopractic.cobranza.modelo.GestionVisita;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

/**
 * Clase controlador que maneja los accesos y modificaciones de las gestiones
 * de visita.
 * @author Cristhian
  */
public class ControladorGestionVisita extends Controlador<GestionVisita> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new GestionVisitaDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Gestiones de cobrador.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(GestionVisita gestion) throws ExcepcionControlador {
    iniciarDao();
    boolean resultado = false;
    try {
      int cantidadDependencias = dao.buscarDependencias(gestion);
      resultado = (cantidadDependencias > 0) ?  true : false;
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> comprobarDependencias() - Error lanzado al buscar"
          + " las dependencias de la gestion de visita.",excepcion,this.log);
    } //Fin de try-catch
    return resultado;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(GestionVisita forma) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

  /**
   * Actualiza los datos de la gestion del saldo.
   * @param saldo el saldo a actualizar.
   * @param gestion la gestion realizada.
   * @return si fue actualizado o no.
   * @throws ExcepcionControlador excepcion lanzada en la capa controlador.
   */
  public boolean actualizarGestion(Saldo saldo,GestionVisita gestion) throws ExcepcionControlador {
    boolean modificado = false;
    try {
      //Inicia la conexion si no se inicio todavia
      if (dao.getConexion() == null) {
        iniciarDao();
      } //Fin de if
      SaldoDao saldoDao = new SaldoDao();
      saldoDao.setConexion(dao.getConexion());
      modificado = saldoDao.actualizarGestion(saldo, gestion);
      administradorConexion.realizarCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> actualizarGestion () - Error lanzado al tratar de "
        + "actualizar los datos de gestion del saldo.",excepcion,this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> modificar () - Error lanzada al tratar de realizar el"
        + " commit.",excepcion,this.log);
    } //Fin de try-catch
    return modificado;
  } //Fin del metodo actualizarGestion

} //Fin de la clase ControladorGestionVisita