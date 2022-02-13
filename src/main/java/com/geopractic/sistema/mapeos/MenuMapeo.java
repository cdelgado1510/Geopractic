package com.geopractic.sistema.mapeos;

import com.geopractic.sistema.modelo.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

/**
 * Clase de mapeado para los objetos menu del sistema.
 * @author Cristhian
 */
public class MenuMapeo implements RowMapper<Menu> {

  @Override
  public Menu map(ResultSet rs, StatementContext ctx) throws SQLException {
    Menu menu = new Menu();
    menu.setId(rs.getLong("Id"));
    menu.setNombre(rs.getString("Nombre"));
    menu.setTipo(rs.getString("Tipo"));
    menu.setIcono(rs.getString("Icono"));
    menu.setVentana(rs.getString("Ventana"));
    menu.setActivo(rs.getBoolean("Activo"));
    menu.setOrden(rs.getInt("Orden"));
    menu.setMenuPadre(rs.getLong("MenuPadre"));

    return menu;
  } //Fin del metodo map

} //Fin de la clase Menu