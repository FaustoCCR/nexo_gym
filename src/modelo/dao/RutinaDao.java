
package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.RutinaVo;

public class RutinaDao extends RutinaVo {

    private PGConexion conecta = new PGConexion();

    public RutinaDao() {
    }

    public RutinaDao(int id_rutina, String nombre, String descripcion) {
        super(id_rutina, nombre, descripcion);
    }

    public List<RutinaVo> mostrarDatos() {

        List<RutinaVo> lista_rutina = new ArrayList<>();
        String sql = "select * from rutina order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                RutinaVo c = new RutinaDao();

                c.setId_rutina(rs.getInt("id_rutina"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista_rutina.add(c);

            }

            rs.close();//cerramos conexion base.
            return lista_rutina;
        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<RutinaVo> mostrarDatosJoin(String aguja) {

        List<RutinaVo> lista_rutina = new ArrayList<>();
        String sql = "select id_rutina,nombre,replace(descripcion,chr(10),' | ')"
                + " from rutina "
                + " Where "
                + " UPPER(nombre) like UPPER('%" + aguja + "%') order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                RutinaVo ru = new RutinaDao();

                ru.setId_rutina(rs.getInt(1));
                ru.setNombre(rs.getString(2));
                ru.setDescripcion(rs.getString(3));
                lista_rutina.add(ru);

            }
            rs.close();//cerramos conexion base.
            return lista_rutina;
        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<RutinaVo> mostrarDatosJoin(int id_rutina) {

        List<RutinaVo> lista_rutina = new ArrayList<>();
        String sql = "select *  "
                + " from rutina "
                + " Where "
                + " id_rutina = " + id_rutina + ""
                + " order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                RutinaVo ru = new RutinaDao();

                ru.setId_rutina(rs.getInt(1));
                ru.setNombre(rs.getString(2));
                ru.setDescripcion(rs.getString(3));
                lista_rutina.add(ru);

            }
            rs.close();//cerramos conexion base.
            return lista_rutina;
        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO rutina(\n"
                + "nombre, descripcion)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion() + "');";
        return conecta.accion(sql);
    }

    public int contadorPer(String nombre) {
        int con = 0;
        String sql = "SELECT COUNT(nombre) "
                + " FROM rutina"
                + " where UPPER(nombre) = UPPER('" + nombre + "')";
        ResultSet rs = conecta.consulta(sql);
        try {
            while (rs.next()) {
                con = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public boolean modificar1(int identificador) {

        String sql;
        sql = "UPDATE rutina set \"nombre\"='" + getNombre() + "',\"descripcion\"='" + getDescripcion() + "'"
                + "WHERE \"id_rutina\"='" + identificador + "'";

        return conecta.accion(sql);
    }

    public boolean eliminar(int identificador) {

        String sql = "delete from rutina where \"id_rutina\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
