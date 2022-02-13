package com.geopractic.cobranza;

import com.geopractic.cobranza.dao.CobranzaDao;
import com.geopractic.cobranza.modelo.Cobranza;
import com.geopractic.controladores.Controlador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.db.ExcepcionConexion;
import com.geopractic.db.ExcepcionDao;

/**
 * Clase controlador que maneja los accesos y modificaciones de las cobranzas de pago.
 * @author Cristhian
  */
public class ControladorCobranza extends Controlador<Cobranza> {

  @Override
  protected void iniciarDao() throws ExcepcionControlador {
    //Verifica si el dao esta instanciado
    if (dao == null) {
      dao = new CobranzaDao();
    } //Fin de if
    try {
      dao.iniciarRecursos();
      administradorConexion.desactivarAutoCommit(dao.getConexion());
    } catch (ExcepcionDao excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al iniciar los"
            + " recursos del Dao de Cobranza.", excepcion, this.log);
    } catch (ExcepcionConexion excepcion) {
      throw new ExcepcionControlador("--> iniciarDao() - Error lanzado al desactivar"
            + " el autocommit de la conexion.",excepcion,this.log);
    } //Fin de try-catch
  } //Fin del metodo iniciarDao

  @Override
  public boolean comprobarDependencias(Cobranza cobranza) throws ExcepcionControlador {
    return false;
  } //Fin del metodo comprobarDependencias

  @Override
  public boolean eliminarDependencias(Cobranza cobranza) throws ExcepcionControlador {
    return false;
  } //Fin del metodo eliminarDependencias

} //Fin de la clase ControladorCobranza