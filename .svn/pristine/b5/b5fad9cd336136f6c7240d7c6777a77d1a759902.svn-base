package com.geopractic.cobranza.bean;

import com.geopractic.administracion.ControladorCliente;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.bean.Bean;
import com.geopractic.cobranza.ControladorCobranza;
import com.geopractic.cobranza.ControladorRuteoCobrador;
import com.geopractic.cobranza.ControladorSaldo;
import com.geopractic.cobranza.modelo.Cobranza;
import com.geopractic.cobranza.modelo.RuteoCobrador;
import com.geopractic.cobranza.modelo.Saldo;
import com.geopractic.controladores.ExcepcionControlador;

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
 * Bean de administracion de cobranza del sistema.
 * @author Cristhian
 */
@ManagedBean (name = "manejarCobranza")
@ViewScoped
public class AdministradorCobranza extends Bean<Cobranza> implements Serializable {

  private static final long serialVersionUID = 1L;
  private Cliente cliente;
  private List<Cliente> listaClientes;
  private List<Cliente> listaClientesFiltro;
  private List<Saldo> listaSaldos;
  private List<Saldo> listaSaldosFiltro;

  /**
   * Constructor sin argumentos. 
   */
  public AdministradorCobranza() {

  } //Fin de constructor

  public Cliente getCliente() {
    return cliente;
  } //Fin del metodo getCliente

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  } //Fin del metodo setCliente

  public List<Cliente> getListaClientes() {
    return listaClientes;
  } //Fin del metodo getListaClientes

  public void setListaClientes(List<Cliente> listaClientes) {
    this.listaClientes = listaClientes;
  } //Fin del metodo setListaClientes

  public List<Cliente> getListaClientesFiltro() {
    return listaClientesFiltro;
  } //Fin del metodo getListaClientesFiltro

  public void setListaClientesFiltro(List<Cliente> listaClientes) {
    this.listaClientesFiltro = listaClientes;
  } //Fin del metodo setListaClientesFiltro

  public List<Saldo> getListaSaldos() {
    return listaSaldos;
  } //Fin del metodo getListaSaldos

  public void setListaSaldos(List<Saldo> listaSaldos) {
    this.listaSaldos = listaSaldos;
  } //Fin del metodo setListaSaldos

  public List<Saldo> getListaSaldosFiltro() {
    return listaSaldosFiltro;
  } //Fin del metodo getListaSaldosFiltro

  public void setListaSaldosFiltro(List<Saldo> listaSaldosFiltro) {
    this.listaSaldosFiltro = listaSaldosFiltro;
  } //Fin del metodo setListaSaldosFiltro

  @Override
  @PostConstruct
  public void init() {
    generarNuevaEntidad();
    generarLista();
  } //Fin del metodo init

  @Override
  public void generarNuevaEntidad() {
    this.entidad = new Cobranza();
  } //Fin del metodo generarNuevaEntidad

  @Override
  public void prepararControlador() {
    controlador = new ControladorCobranza();
  } //Fin del metodo prepararControlador

  /**
   * Funciones realizadas al seleccionar el saldo.
   * @param evento el evento de seleccion.
   */
  public void seleccionarSaldo(SelectEvent evento) {
    Saldo saldo = ((Saldo) evento.getObject());
    this.entidad.setSaldo(saldo);
    this.entidad.setMonto(saldo.getSaldo());
    this.entidad.setFecha(new Date());
    ControladorCliente controladorCliente = new ControladorCliente();
    try {
      this.entidad.setCliente(controladorCliente.buscarId(saldo.getCliente()));
    } catch (ExcepcionControlador excepcion) {
      log.error("--> seleccionarSaldo() - Error a obtener el cliente del saldo ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Error a obtener el cliente del saldo.",null);
    } finally {
      try {
        controladorCliente.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- seleccionarSaldo() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de metodo seleccionarSaldo

  /**
   * Funciones realizadas al seleccionar el cliente.
   * @param evento el evento de seleccion.
   */
  public void seleccionarCliente(SelectEvent evento) {
    cliente = ((Cliente) evento.getObject());
    ControladorSaldo controladorSaldo = new ControladorSaldo();
    try {
      this.listaSaldos = controladorSaldo.listarSaldosCliente(cliente)
            .stream().filter(saldo -> saldo.getSaldo() > 0)
            .collect(Collectors.toList());
    } catch (ExcepcionControlador excepcion) {
      log.error("--> seleccionarCliente() - Error a obtener los saldos del cliente ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Error a obtener los saldos del cliente.",null);
    } finally {
      try {
        controladorSaldo.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- seleccionarCliente() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de metodo seleccionarCliente

  /**
   * Lista los clientes del sistema.
   */
  public void listarClientes() {
    ControladorCliente controladorCliente = new ControladorCliente();
    try {
      this.listaClientes = controladorCliente.listarTodos();
    } catch (ExcepcionControlador excepcion) {
      log.error("--> listarClientes() - Error a obtener el listado de clientes ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "Error a obtener el listado de clientes.",null);
    } finally {
      try {
        controladorCliente.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- listarClientes() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
    } //Fin del try-catch
  } //Fin de metodo listarClientes

  @Override
  public void guardar(ActionEvent actionEvent) {
    prepararVariables();
    ControladorSaldo controladorSaldo = new ControladorSaldo();
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    log.debug("--> guardar() - Guardando cobranza nueva");
    try {
      boolean resultado = controlador.guardar(entidad);
      this.entidad.getSaldo().setSaldo(
          this.entidad.getSaldo().getSaldo() - this.entidad.getMonto());
      resultado = controladorSaldo.modificar(this.entidad.getSaldo());

      List<RuteoCobrador> listaRuteos = controladorRuteo
           .listarRuteoGestion(this.entidad.getSaldo());

      //Verifica si se devolvio ruteos perteneciente al saldo.
      if (listaRuteos != null && listaRuteos.size() > 0) {
        RuteoCobrador ruteo = listaRuteos.stream().findFirst().get();

        //Verifica si se devuelve un ruteo
        if (ruteo != null) {
          ruteo.setVerificado(true);
          ruteo.setVisitado(true);
          ruteo.setModificadoCumplido(true);
          ruteo.setFechaCumplido(entidad.getFecha());
          resultado = controladorRuteo.modificar(ruteo);
        } //Fin de if
      } //Fin de if
      if (resultado) {
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Cobranza guardada", "Nueva cobranza guardada.",null);
        controlador.cerrarRecursosControlador();
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "La nueva cobranza no pudo ser guardada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> guardar() - Error en guardar Cobranza ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La nueva cobranza no pudo ser guardada.",null);
    } finally {
      try {
        controladorRuteo.cerrarRecursosControlador();
        controladorSaldo.cerrarRecursosControlador();
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- guardar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
      generarNuevaEntidad();
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
            "Cambios guardados", "Cambios de la cobranza guardados.",null);
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de guardado", "Los cambios no pudieron ser guardados.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> modificar() - Error en modificar Cobranza.",excepcion);
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
  public void eliminar(Cobranza seleccion) {
    prepararVariables();
    ControladorSaldo controladorSaldo = new ControladorSaldo();
    ControladorRuteoCobrador controladorRuteo = new ControladorRuteoCobrador();
    log.debug("--> eliminar() - Eliminando cobranza ");
    this.entidad = seleccion;
    try {
      boolean resultado = controlador.eliminar(entidad);
      this.entidad.getSaldo().setSaldo(
          this.entidad.getSaldo().getSaldo() + this.entidad.getMonto());
      resultado = controladorSaldo.modificar(this.entidad.getSaldo());

      List<RuteoCobrador> listaRuteos = controladorRuteo
              .listarRuteoGestion(this.entidad.getSaldo());

      //Verifica si se devolvio ruteos perteneciente al saldo.
      if (listaRuteos != null && listaRuteos.size() > 0) {
        RuteoCobrador ruteo = listaRuteos.stream().findFirst().get();

        //Verifica si se devuelve un ruteo
        if (ruteo != null) {
          ruteo.setVerificado(true);
          ruteo.setVisitado(true);
          resultado = controladorRuteo.modificar(ruteo);
        } //Fin de if
      } //Fin de if
      if (resultado) {
        cerrarDialogo("busquedaCobranzas");
        agregarMensaje(FacesMessage.SEVERITY_INFO,
            "Pago eliminado", "La cobranza a sido eliminada.",null);
        controlador.cerrarRecursosControlador();
        generarLista();
      } else {
        agregarMensaje(FacesMessage.SEVERITY_FATAL,
            "Error de eliminacion", "La cobranza no pudo ser eliminada.",null);
      } //Fin de if-else
    } catch (Exception excepcion) {
      log.error("--> eliminar() - Error en eliminar Cobranza ",excepcion);
      agregarMensaje(FacesMessage.SEVERITY_FATAL,
          "Error de sistema", "La cobranza no pudo ser eliminada. ",null);
    } finally {
      try {
        controladorRuteo.cerrarRecursosControlador();
        controladorSaldo.cerrarRecursosControlador();
        controlador.cerrarRecursosControlador();
      } catch (Exception excepcion) {
        log.error("<-- eliminar() - Error cerrando recursos. ",excepcion);
      } //Fin del try-catch interno
      generarNuevaEntidad();
    } //Fin del try-catch
  } //Fin de la clase eliminar
} //Fin de la clase AdministradorCobranza