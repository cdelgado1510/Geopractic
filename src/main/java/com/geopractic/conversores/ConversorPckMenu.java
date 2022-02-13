package com.geopractic.conversores;

import com.geopractic.sistema.modelo.Menu;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "ConversorPckMenu")
public class ConversorPckMenu implements Converter {

  @Override
  public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
    Object ret = null;
    if (componente instanceof PickList) {
      Object dualList = ((PickList) componente).getValue();
      DualListModel<?> dl = (DualListModel<?>) dualList;
      for (Object o : dl.getSource()) {
        String id = "" + ((Menu) o).getId();
        if (valor.equals(id)) {
          ret = o;
          break;
        } //Fin de if interno
      } //Fin de for
      if (ret == null) {
        for (Object o : dl.getTarget()) {
          String id = "" + ((Menu) o).getId();
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
    if (valor instanceof Menu) {
      str = "" + ((Menu) valor).getId();
    } //Fin de if
    return str;
  } //Fin del metodo getAsString

} //Fin de la clase ConversorPckMenu