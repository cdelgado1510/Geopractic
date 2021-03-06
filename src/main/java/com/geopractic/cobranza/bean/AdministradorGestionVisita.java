package com.geopractic.cobranza.bean;

import com.geopractic.administracion.modelo.Cargo;
import com.geopractic.administracion.modelo.Empleado;
import com.geopractic.bean.Bean;
import com.geopractic.cobranza.ControladorGestionVisita;
import com.geopractic.cobranza.ControladorRuteoCobrador;
import com.geopractic.cobranza.ControladorSaldo;
import com.geopractic.cobranza.modelo.GestionVisita;
import com.geopractic.cobranza.modelo.RuteoCobrador;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.controladores.ExcepcionControlador;
import com.geopractic.georrefencia.ControladorGeorreferencia;
import com.geopractic.georrefencia.modelo.Georreferencia;
import com.geopractic.sistema.bean.SesionBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

/**
 * Bean de administracion de gestiones de visitas.
 * @author Cristhian
 */
@ManagedBean (name = "manejarGestionVisita")
@ViewScoped
public class AdministradorGestionVisita extends Bean<GestionVisita> implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Saldo> listaSaldos;
  private List<Saldo> listaSaldosFiltro;
  private List<Georreferencia> listaGeorreferenciaCliente;
  private Empleado empleadoCarga;

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorGestionVisita() {

  } //Fin de constructor

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  /**
   * Devuelve el listado de saldos de la cuenta.
   * @return el listado de saldos de la cuenta.
   */
  public List<Saldo> getListaSaldos() {
    return listaSaldos;
  } //Fin del metodo getListaSaldos

  /**
   * Establece el listado de saldos.
   * @param listaSaldos el listado de saldos a establecer.
   */
  public void setListaSaldos(List<Saldo> listaSaldos) {
    this.listaSaldos = listaSaldos;
  } //Fin del metodo setListaSaldos

  /**
   * Devuelve el listado de saldos a filtrar.
   * @return el listado de saldos filtrado.
   */
  public List<Saldo> getListaSaldosFiltro() {
    return listaSaldosFiltro;
  } //Fin del metodo getListaSaldosFiltro

  /**
   * Establece el listado de saldos a filtrar.
   * @param listaSaldosFiltro el listado de saldos a establecer.
   */
  public void setListaSaldosFiltro(List<Saldo> listaSaldosFiltro) {
    this.listaSaldosFiltro = listaSaldosFiltro;
  } //Fin del metodo setListaSaldosFiltro

  /**
   * Devuelve el listado de georreferencias pertenecientes al cliente.
   * @return el listado de georreferencias del cliente.
   */
  public List<Georreferencia> getListaGeorreferenciaCliente() {
    return listaGeorreferenciaCliente;
  } //Fin del metodo getListaGeorreferenciaCliente

  /**
   * Establece el listado de georreferencias del cliente.
   * @param listaGeorreferenciaCliente el listado de georreferencias a establecer.
   */
  public void setListaGeorreferenciaCliente(List<Georreferencia> listaGeorreferenciaCliente) {
    this.listaGeorreferenciaCliente = listaGeorreferenciaCliente;
  } //Fin del metodo setListaGeorreferenciaCliente

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new GestionVisita();
    this.entidad.setFecha(new Date());
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorGestionVisita();
  } //Fin del metodo prepararControlador

  /**
   * Funciones realizadas al seleccionar el saldo.
   * @param evento el evento de seleccion.
   */
  public void seleccionarSaldo(SelectEvent evento) {
    Saldo saldo = ((Saldo) evento.getObject());
    this.entidad.setSaldo(saldo);
    this.entidad.setEmpleadoCarga(this.empleadoCarga);
    this.entidad.setMonto(saldo.getSaldo());

    ControladorGeorreferencia controladorGeorreferencia = new ControladorGeorreferencia();
    try {
      //Obtiene las georreferencias del cliente.
      this.listaGeorreferenciaCliente =  controladorGeorreferencia
         .listarGeorreferenciaCliente(saldo.getCliente());
    } catch (ExcepcionControlador excepcion) {
      log.error("--> seleccionarSaldo() - Error a obtener georreferencias del cliente ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Error a obtener georreferencias del cliente.",null);
    } finally {
      try {
        controladorGeorreferencia.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- seleccionarSaldo() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch

  } //Fin de metodo seleccionarSaldo

  @Override
  public void generarLista() {
    prepararVariables();
    log.debug("--> generarLista() - Generando listado de saldos para gestion.");
    ControladorSaldo controladorSaldo = new ControladorSaldo();
    try {
      SesionBean sesion = (SesionBean) contexto.getApplication()
              .evaluateExpressionGet(contexto, "#{sesion}", SesionBean.class);
      this.empleadoCarga = sesion.getUsuario().getEmpleado();
      Date hoy = new Date();
      //Genera el listado de acuerdo al cargo correspondiente
      if (this.empleadoCarga.getCargo() != null) {
        if (this.empleadoCarga.getCargo().equals(new Cargo("Operativo Cobranza"))) {
          this.listaSaldos = controladorSaldo.listarSaldosCuentaEmpleado(empleadoCarga)
            .stream().filter(saldo -> saldo.getSaldo() > 0
            && ((hoy.getTime() - saldo.getFechaGestion().getTime()) / (24 * 60 * 60 * 1000)) > 0)
            .collect(Collectors.toList());
          log.debug("--> generarLista() - Lista de Operativo Cobranza cargada.");
        } else {
          this.listaSaldos = controladorSaldo.listarTodos()
              .stream().filter(saldo -> saldo.getSaldo() > 0 
              && ((hoy.getTime() - saldo.getFechaGestion().getTime()) / (24 * 60 * 60 * 1000)) > 0)
              .collect(Collectors.toList());
          log.debug("--> generarLista() - Lista completa de saldos.");
        } //Fin de if-else interno
      } else {
        this.listaSaldos = controladorSaldo.listarTodos()
          .stream().filter(saldo -> saldo.getSaldo() > 0 
          && ((hoy.getTime() - saldo.getFechaGestion().getTime()) / (24 * 60 * 60 * 1000)) > 0)
          .collect(Collectors.toList());
        log.debug("--> generarLista() - Lista completa de saldos.");
      } //Fin de if-else
      agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Carga completa", "Listado de saldos cargados.",null);
    } catch (Exception excepcion) {
      log.error("--> generarLista() - Error en generando lista de saldos ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Hubo un error dentro del sistema. No se pudo obtener"
             + " el listado de saldos.",null);
    } finally {
      try {
        controlador.cerrarRecursosControlador();
        controladorSaldo.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("--> generarLista() -  Error cerrando los recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin del metodo generarLista

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    log.debug("--> guardar() - Guardando gestion nueva");
    try {
      SesionBean sesion = (SesionBean) contexto.getApplication()
              .evaluateExpressionGet(contexto, "#{sesion}", SesionBean.class);

      //Comprueba si ya existe la misma gestion agendada
      if (controlador.comprobarDependencias(entidad)) {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de sistema", "Ya existe una gestion asignada al saldo en la"
            + " fecha asignada.",null);
      } else {
        Empleado empleadoCarga = sesion.getUsuario().getEmpleado();
        this.entidad.setEmpleadoCarga(empleadoCarga);
        boolean resultado = controlador.guardar(entidad);
        resultado = ((ControladorGestionVisita) controlador)
          .actualizarGestion(this.entidad.getSaldo(), this.entidad);

        RuteoCobrador ruteo = new RuteoCobrador();
        ruteo.setGestion(this.entidad);
        ruteo.setFechaVisita(this.entidad.getFechaVisita());
        ruteo.setVerificado(false);
        ruteo.setVisitado(false);

        resultado = controladorRuteo.guardar(ruteo);

        if (resultado) {
          cerrarDialogo("diaAbm");
          agregarMensaje(FacesMessage.SEVERITY_INFO,
              "Gestion guardada", "Nueva gestion guardada.",null);
        } else {
          agregarMensaje(FacesMessage.SEVERITY_FATAL,
              "Error de guardado", "La nueva gestion no pudo ser guardada.",null);
        } //Fin de if-else
      } //Fin de if-else

    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Gestion Visita",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La nueva gestion no pudo ser guardada.",null);
    } finally {
      try {
        controladorRuteo.cerrarRecursosControlador();
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
      generarNuevaEntidad();
      generarLista();
      log.debug("Guardado completo");
    } //Fin del try-catch
  } //Fin de la clase agregar

  @Override
  public void modificar(ActionEvent actionEvent) {
    prepararVariables();
    try {
      boolean resultado = controlador.modificar(entidad);
      if (resultado) {
        cerrarDialogo("diaAbm");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cambios guardados", "Cambios de la gestion guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Gestion Visita.",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Los cambios no pudieron ser guardados.",null);
    } finally {
      generarNuevaEntidad();
      try {
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- modificar() - Error cerrar recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de la clase modificar

  @Override
  public void eliminar(GestionVisita seleccion) {
    agregarMensaje(FacesMessage.SEVERITY_FATAL,
           "Error de sistema", "No se puede eliminar una gestion ya cargada.",null);
  } //Fin del metodo eliminar

} //Fin de la clase AdministradorGestionVisita