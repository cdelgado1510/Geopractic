package com.geopractic.util;

import com.geopractic.sistema.modelo.Usuario;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;

/**
 * Filtro utilizado para registrar el usuario y sesion activa dentro de los logs.
 * @author Cristhian
 *
 */
public class UsuarioFiltro implements Filter {

  private final String claveUsuario = "usuario";
  private final String claveSesion = "uuid";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  } //Fin del metodo init

  @Override
  public void doFilter(ServletRequest peticion, ServletResponse respuesta, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) peticion;
    HttpSession sesion = req.getSession();

    //Verifica si hay una sesion activa
    if (sesion != null) {
      //Obtiene los datos de la sesion
      Usuario usuario = (Usuario) sesion.getAttribute(claveUsuario);
      String uuid = (String) sesion.getAttribute(claveSesion);

      //Verifica que se recuperen datos de la sesion
      if (uuid != null && usuario != null) {
        //Regitra los datos dentro del MDC
        MDC.put(claveUsuario, usuario.getUsuario());
        MDC.put(claveSesion, uuid);
      }
      //Realiza el filtro
      try {
        chain.doFilter(peticion, respuesta);
      } finally {
        //Quita los campos MDC
        MDC.remove(claveUsuario);
        MDC.remove(claveSesion);
      } //Fin de try
    } //Fin de if

  } //Fin del metodo doFilter

  @Override
  public void destroy() {
  } //Fin del metodo destroy

} //Fin de la clase UsuarioFiltro