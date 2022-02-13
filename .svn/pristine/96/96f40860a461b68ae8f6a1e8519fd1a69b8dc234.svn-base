package com.geopractic.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reporte.pdf")
public class PdfServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
      throws ServletException, IOException {
    byte[] contenido = (byte[]) peticion.getSession().getAttribute("reporteBytesArray");
    respuesta.setContentType("application/pdf");
    respuesta.setContentLength(contenido.length);
    respuesta.getOutputStream().write(contenido);
    peticion.getSession().removeAttribute("reporteBytesArray");
  } //Fin de doGet
} //Fin de la clase PdfServlet