package com.geopractic.administracion.validacion;

import com.geopractic.administracion.ControladorCliente;
import com.geopractic.administracion.modelo.Cliente;
import com.geopractic.controladores.ExcepcionControlador;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Validacion de control de nro de documento no duplicados dentro de cliente.
 * @author Cristhian
 */
@FacesValidator("com.geopractic.administracion.validacion.NroDocumentoValidacion")
public class NroDocumentoValidacion implements Validator, ClientValidator {
  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  @Override
  public void validate(FacesContext context, UIComponent component, Object value)
       throws ValidatorException {

    ControladorCliente controladorCliente = new ControladorCliente();
    boolean resultado = false;
    FacesMessage mensaje = null;
    Cliente cliente = new Cliente();

    try {
      switch (component.getId()) {
        case "txtNroDocumento":
          cliente.setNroDocumento(value.toString());
          resultado = controladorCliente.existeObjeto(cliente);
          mensaje = new FacesMessage("Nro de documento ya utilizado.", 
            "Ya existe un cliente con el numero de documento ya ingreasdo.");
          break;
        case "txtRuc":
          cliente.setRuc(value.toString());
          resultado = controladorCliente.existeRuc(cliente);
          mensaje = new FacesMessage("Nro de RUC ya utilizado.", 
              "Ya existe un cliente con el numero de RUC ingreasdo.");
          break;
        default:
          break;
      } //Fin de switch
    } catch (ExcepcionControlador excepcion) {
      log.error("--> validacion - Error en validacion de Nro de Documento. ",excepcion);
    } //Fin de try-catch

    if (resultado) {
      mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(mensaje);
    } //Fin de if

  } //Fin de validate

  @Override
  public Map<String, Object> getMetadata() {
    return null;
  } //Fin de getMetadata

  @Override
  public String getValidatorId() {
    return "com.geopractic.administracion.validacion.NroDocumentoValidacion";
  } //Fin de getValidatorId

} //Fin de la clase NroDocumentoValidacion