package com.geopractic.sistema.dao;

import com.geopractic.db.Dao;
import com.geopractic.db.ExcepcionDao;
import com.geopractic.sistema.mapeos.MenuMapeo;
import com.geopractic.sistema.modelo.Menu;
import com.geopractic.sistema.modelo.Rol;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza todas las consultas de base de datos relacionadas con
 * los menus de la aplicacion.
 * @author Cristhian
 *
 */
public class MenuDao extends Dao<Menu> {

  /**
   * Constructor que principal que crea el mapeo a utilizar y
   * inicializa las variables de consulta.
   */
  public MenuDao() {
    queryBuscar = "SELECT * FROM \"Menu\" WHERE \"Nombre\" = :nombre"
            + " AND \"Tipo\" = :tipo AND \"Ventana\" = :ventana ;";

    queryBuscarId = "SELECT * FROM \"Menu\" WHERE \"Id\" = :id";

    queryListarTodos = "SELECT * FROM \"Menu\" ORDER BY \"Id\" ASC ";

    queryGuardar = "INSERT INTO \"Menu\"(\"Nombre\", \"Tipo\", "
            + "\"Icono\", \"Ventana\", \"Activo\", \"Orden\", \"MenuPadre\") "
            + "VALUES ( :nombre , :tipo , :icono , :ventana ,"
            + " :activo , :orden , :menuPadre );";

    queryModificar = "UPDATE \"Menu\" SET \"Nombre\" = :nombre , "
            + "\"Tipo\" = :tipo , \"Icono\" = :icono , \"Ventana\" = :ventana "
            + ", \"Activo\" = :activo , \"Orden\" = :orden , "
            + "\"MenuPadre\" = :menuPadre WHERE \"Id\" = :id ;";

    queryEliminar = "DELETE FROM \"Menu\" WHERE \"Id\" = :id ;";
  } //Fin del constructor sin argumento

  @Override
  public void iniciarMapeos() {
    mapeoBean = new MenuMapeo();
  } //Fin del metodo iniciarMapeos

  @Override
  public void obtenerClavePrimaria(Menu menu, Menu menuNuevo) {
    menu.setId(menuNuevo.getId());
  } //Fin del metodo obtenerClavePrimaria

  @Override
  public boolean guardar(Menu menu) throws ExcepcionDao {
    //Verifica que el menupadre es nulo
    if (menu.getMenuPadre() == 0) {
      queryGuardar = "INSERT INTO \"Menu\"(\"Nombre\", \"Tipo\", "
         + "\"Icono\", \"Ventana\", \"Activo\", \"Orden\") "
         + "VALUES ( :nombre , :tipo , :icono , :ventana ,"
         + " :activo , :orden );";
    } //Fin de if
    return super.guardar(menu);
  } //Fin del metodo guardar

  @Override
  public boolean modificar(Menu menu) throws ExcepcionDao {
    //Verifica que el menupadre es nulo
    if (menu.getMenuPadre() == 0) {
      queryModificar = "UPDATE \"Menu\" SET \"Nombre\" = :nombre , "
         + "\"Tipo\" = :tipo , \"Icono\" = :icono , \"Ventana\" = :ventana "
         + ", \"Activo\" = :activo , \"Orden\" = :orden "
         + " WHERE \"Id\" = :id ;";
    } //Fin de if
    return super.modificar(menu);
  } //Fin del metodo modificar

  /**
   * Devuelve el listado de menus asignados al rol solicitado.
   * @param rol el rol a buscar.
   * @return el listado de los menus del rol.
   * @throws ExcepcionDao error lanzado al fallar el listado.
   */
  public List<Menu> listarMenusRol(Rol rol) throws ExcepcionDao {
    List<Menu> listaMenu = new ArrayList<Menu>();
    listaMenu = conexion.createQuery("SELECT * FROM \"menus_de_rol\" "
       + "WHERE \"Rol\" = :rol AND \"Activo\" = true "
       + " ORDER BY \"Orden\" ASC ")
       .bind("rol", rol.getId())
       .mapToBean(Menu.class)
       .list();
    return listaMenu;
  } //Fin del metodo listarMenusRol

  /**
   * Elimina las relaciones de menu asociadas a roles.
   * @param menu el menu a eliminar.
   * @return si elimino o no la relacion.
   * @throws ExcepcionDao excepcion lanzada en la capa dao.
   */
  public boolean eliminarMenuRol(Menu menu) throws ExcepcionDao {
    int respuesta = 0;
    try {
      respuesta = conexion.createUpdate("DELETE FROM \"RolMenu\" WHERE \"Menu\" = :id ;")
         .bind("id", menu.getId())
         .execute();
    } catch (Exception excepcion) {
      throw new ExcepcionDao("<-- eliminarMenuRol() - Error al eliminar las dependenciasl de Menu "
            + "no encontrado o existen multiples", excepcion, this.log);
    } //Fin de try-catch
    return (respuesta == 1) ? true : false;
  } //Fin del metodo eliminarMenuRol
  
} //Fin de la clase MenuDao