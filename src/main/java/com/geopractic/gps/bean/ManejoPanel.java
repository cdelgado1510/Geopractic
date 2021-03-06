package com.geopractic.gps.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.geopractic.cobranza.ControladorRuteoCobrador;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.georrefencia.ControladorPanelVisualizacion;
import com.geopractic.georrefencia.modelo.GeneradorJson;
import com.geopractic.georrefencia.modelo.Mapa;
import com.geopractic.georrefencia.modelo.PanelVisualizacion;
import com.geopractic.gps.ConsultasOpenGts;
import com.geopractic.gps.modelo.Cuenta;
import com.geopractic.gps.modelo.DeviceList;
import com.geopractic.gps.modelo.EventData;
import com.geopractic.sistema.modelo.Turnos;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean de administracion de paneles de visualizacion.
 * @author Cristhian
 *
 */
@ManagedBean (name = "controlPanel")
@ViewScoped
public class ManejoPanel {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  private PrimeFaces contextoPeticion;
  private PanelVisualizacion panel;
  private long idPanel;
  private Date inicioTurnoActual;
  private Date finTurnoActual;
  private boolean inicial;
  private String descripcion;

  /**
   * Constructor sin argumentos.
   */
  public ManejoPanel() {
    inicial = true;
    prepararVariables();
    obtenerParamentroUrl();
    cargarPanel();
  } //Fin de constructor

  /**
  * Procedimientos realizados al iniciar el bean.
  */
  @PostConstruct
  public void init() {

  } //Fin del metodo init

  /**
   * Inicia las variables a utilizar.
   */
  public void prepararVariables() {
    contextoPeticion = PrimeFaces.current();
  } //Fin del metodo prepararVariables

  /**
   * Devuelve el panel de visualiacion actual.
   * @return el panel de visualizacion actual.
   */
  public PanelVisualizacion getPanel() {
    return panel;
  } //Fin del metodo getPanel

  /**
   * Establece el panel de visualizacion actual.
   * @param panel el panel a establecer.
   */
  public void setPanel(PanelVisualizacion panel) {
    this.panel = panel;
  } //Fin del metodo setPanel

  /**
   * Devuelve el id del panel. 
   * @return el codigo identificador del panel a utilizar
   */
  public long getIdPanel() {
    return idPanel;
  } //Fin del metodo getIdPanel

  /**
   * Establece el id del panel.
   * @param idPanel el codigo del panel a establecer.
   */
  public void setIdPanel(long idPanel) {
    this.idPanel = idPanel;
  } //Fin del metodo setIdPanel

  /**
   * Devuelve el horario de inicio del turno del panel.
   * @return el horario de inicio del panel.
   */
  public Date getInicioTurnoActual() {
    return inicioTurnoActual;
  } //Fin del metodo getInicioTurnoActual

  /**
   * Establece el horario de inicio del turno del panel.
   * @param inicioTurnoActual el horario de inicio a establecer.
   */
  public void setInicioTurnoActual(Date inicioTurnoActual) {
    this.inicioTurnoActual = inicioTurnoActual;
  } //Fin del metodo setInicioTurnoActual

  /**
   * Devuelve el horario de fin del turno del panel.
   * @return el horario de fin del panel.
   */
  public Date getFinTurnoActual() {
    return finTurnoActual;
  } //Fin del metodo finTurnoActual

  /**
   * Establece el horario de fin del turno del panel.
   * @param finTurnoActual el horario de finalizacion a establecer.
   */
  public void setFinTurnoActual(Date finTurnoActual) {
    this.finTurnoActual = finTurnoActual;
  } //Fin del metodo setFinTurnoActual

  public String getDescripcion() {
    return descripcion;
  } //Fin del metodo getDescripcion

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  } //Fin del metodo setDescripcion

  /**
   * Obtiene los parametros de URL de la peticion actual.
   */
  private void obtenerParamentroUrl() {
    FacesContext context = FacesContext.getCurrentInstance();
    Map<String, String> parametros = context.getExternalContext().getRequestParameterMap();
    setIdPanel(Long.parseLong(parametros.get("panel")));
  } //Fin del metodo obtenerParamentroUrl

  /**
   * Busca el panel ingresado por el empleado.
   */
  private void cargarPanel() {
    log.debug("--> cargarPanel() - Buscando panel con Id: {}",getIdPanel());
    ControladorPanelVisualizacion controlador = new ControladorPanelVisualizacion();
    try {
      setPanel(controlador.buscarId(getIdPanel()));
      //Verifica si se obtuvo un panel
      if (panel == null) {
        setDescripcion("No se encontro ningun panel con el numero ingresado.");
      } else {
        iniciarHora();
        Collections.sort(getPanel().getListaMapas(), Mapa.ordenarPorId);
        obtenerRuteosPanel();
        contextoPeticion.executeScript("PF('pollRecorrido').start();");
      } //Fin de if-else
    } catch (ExcepcionControlador excepcion) {
      log.error("--> cargarPanel() - Error obteniendo el panel ",excepcion);
      setDescripcion("No se pudo obtener el panel de visualizacion solicitado.");
    } finally {
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- cargarPanel() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin de try-catch
  } //Fin del metodo cargarPanel

  /**
   * Inicia las horas de inicio, actual y fin del turno.
   */
  private void iniciarHora() {
    Calendar calendario = Calendar.getInstance();

    Turnos turnoPanel = this.panel.getTurno();
    calendario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(new java.text.SimpleDateFormat("HH")
            .format(turnoPanel.getHoraInicio())));
    calendario.set(Calendar.MINUTE, Integer.parseInt(new java.text.SimpleDateFormat("mm")
           .format(turnoPanel.getHoraInicio())));
    setInicioTurnoActual(calendario.getTime());
    log.debug("Estableciendo hora de inico de turno: {}", getInicioTurnoActual());

    calendario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(new java.text.SimpleDateFormat("HH")
            .format(turnoPanel.getHoraFin())));
    calendario.set(Calendar.MINUTE, Integer.parseInt(new java.text.SimpleDateFormat("mm")
            .format(turnoPanel.getHoraFin())));
    setFinTurnoActual(calendario.getTime());
    log.debug("Estableciendo hora de fin de turno: {}", getFinTurnoActual());
  } //Fin del metodo iniciarHora

  /**
   * Obtiene el ruteo de los mapas del panel.
   */
  public void obtenerRuteosPanel() {

    log.debug("--> obtenerRuteosPanel() - Consultando recorrido de rastreo");
    String ruteoDia = null;
    int contador = 0;
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    
    for (Mapa mapa : panel.getListaMapas()) {
      try {
        ruteoDia = GeneradorJson.generarGeoJson(controladorRuteo.listarRuteoCobradorDia(
              mapa.getRastreadorGps().getEmpleadoAsignado(),
              getInicioTurnoActual(), getFinTurnoActual()));
      } catch (JsonProcessingException excepcion) {
        log.error("--> obtenerRuteosPanel() - Error procesando JSON de la ruta. ",excepcion);
        setDescripcion("Error al procesar el ruteo de la ruta.");
      } catch (ExcepcionControlador excepcion) {
        log.error("--> obtenerRuteosPanel() - Error en ruteo la ruta. ",excepcion);
        setDescripcion("No es posible obtener el ruteo.");
      } //Fin del try-catch
      contextoPeticion.executeScript("cargarRuteo(" + ruteoDia + "," + contador + " );");
      contador++;
    } //Fin de for
    try {
      controladorRuteo.cerrarRecursosControlador();
    } catch (Exception excepcion) {
      log.error("<-- obtenerRuteosPanel() - Error cerrando recursos. ",excepcion);
    } //Fin del try-catch interno
  } //Fin del metodo obtenerRuteosPanel

  /**
   * Obtiene el recorrido de los mapas del panel.
   */
  public void obtenerRecorridosPanel() {
    log.debug("--> obtenerRecorridosPanel() - Consultando recorrido de rastreo");
    int contador = 0;
    Cuenta ultimaPosicion = null;
    Calendar calendario = Calendar.getInstance();
    Date horaActual = calendario.getTime();

    log.debug("Iniciando barra");
    contextoPeticion.executeScript("PF('carga').start();");
    contextoPeticion.executeScript("PF('pollRecorrido').stop();");
    for (Mapa mapa : panel.getListaMapas()) {
      try {
        //Verifica si es la primera consulta
        if (inicial) {
          log.debug("Es la primera peticion");
          ultimaPosicion = new ConsultasOpenGts().obtenerRecorrido(mapa.getRastreadorGps(),
                  getInicioTurnoActual(),getFinTurnoActual());
        } else {

          log.debug("Se encuentra dentro del horario {} == {}",inicioTurnoActual,finTurnoActual);

          //Verifique si se encuentre dentro del turno
          if (horaActual.after(inicioTurnoActual) && horaActual.before(finTurnoActual)) {
            ultimaPosicion = new ConsultasOpenGts().obtenerRecorrido(mapa.getRastreadorGps(),
              horaActual,getFinTurnoActual());
          } //Fin de if

          //Verifica si es terminado el turno
          if (horaActual.after(finTurnoActual)) {
            log.debug("Es despues del turno");
            ultimaPosicion = new ConsultasOpenGts().obtenerRecorrido(mapa.getRastreadorGps(),
                    getInicioTurnoActual(),getFinTurnoActual());
          } //Fin de if

        } //Fin de if-else
      } catch (ExcepcionControlador excepcion) {
        log.error("--> obtenerRecorridosPanel() - Error en obtener la ruta. ",excepcion);
        setDescripcion("No es posible obtener el recorrido.");
      } //Fin de try-catch
      if (ultimaPosicion != null) {
        //Recorre todos los datos obtenidos del dispositivo
        for (DeviceList dispositivo : ultimaPosicion.getDeviceList()) {
          //Recorre todos los eventos del dispositivo
          for (EventData evento : dispositivo.getEventData()) {
            log.debug("Agregando punto");
            contextoPeticion.executeScript("agregarPunto(" + evento.getGpsPointLat() + "," 
                    + evento.getGpsPointLon() + "," + contador + ",\'" + dispositivo.getDeviceDesc()
                    + "\',\'" + evento.getTimeStampDate() + "\',\'" + evento.getTimeStampTime() 
                    + "\',\'" + evento.getSpeed() + "\' );");
          } //Fin de for de Eventos
        } // Fin de for de dispositivos
      } //Fin de if
      contador++;
    } //Fin de for

    //Establece si ya fue la primera consulta
    if (inicial) {
      inicial = false;
    } //Fin de if

    //Verifica si ya son las 0:00 horas para refrescar los paneles
    if ((Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 0)) {
      contextoPeticion.executeScript("location.reload();");
    } //Fin de if
    log.debug("Deteniendo barra");
    contextoPeticion.executeScript("PF('carga').cancel();");
    contextoPeticion.executeScript("PF('pollRecorrido').start();");
  } //Fin del metodo obtenerRecorridosPanel

} //Fin de la clase ManejoPanel