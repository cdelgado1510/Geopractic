package com.geopractic.conversores;

import com.geopractic.sistema.modelo.Ventana;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "ConversorPckVentana")
public class ConversorPckVentana implements Converter {

  @Override
  public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
    Object ret = null;
    if (componente instanceof PickList) {
      Object dualList = ((PickList) componente).getValue();
      DualListModel<?> dl = (DualListModel<?>) dualList;
      for (Object o : dl.getSource()) {
        String id = "" + ((Ventana) o).getId();
        if (valor.equals(id)) {
          ret = o;
          break;
        } //Fin de if interno
      } //Fin de for
      if (ret == null) {
        for (Object o : dl.getTarget()) {
          String id = "" + ((Ventana) o).getId();
          if (valor.equals(id)) {
            ret = o;
            break;
          } //Fin de if interno
        } //Fin de for
      } //Fin de if interno
    } //Fin de if
    return ret;
  } //Fin del metodo getAsObject

  @Override
  public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
    String str = "";
    if (valor instanceof Ventana) {
      str = "" + ((Ventana) valor).getId();
    } //Fin de if
    return str;
  } //Fin del metodo getAsString

} //Fin de la clase ConversorPckVentana