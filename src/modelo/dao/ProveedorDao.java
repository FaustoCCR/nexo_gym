package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.ProveedorVo;

public class ProveedorDao extends ProveedorVo {

    private PGConexion conecta = new PGConexion();

    public ProveedorDao() {
    }

    public ProveedorDao(String id_proveedor, String nombre, String contacto) {
        super(id_proveedor, nombre, contacto);
    }

    public List<ProveedorVo> mostrarDatos() {

        List<ProveedorVo> lista_proveedores = new ArrayList<>();
        String sql = "select * from proveedor order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProveedorVo pv = new ProveedorVo();

                pv.setId_proveedor(rs.getString("id_proveedor"));
                pv.setNombre(rs.getString("nombre"));
                pv.setContacto(rs.getString("contacto"));

                lista_proveedores.add(pv);

            }

            rs.close();//cerramos conexion base.
            return lista_proveedores;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ProveedorVo> mostrarDatosJoin(String aguja) {

        List<ProveedorVo> lista_proveedores = new ArrayList<>();
        String sql = "select * "
                + " from proveedor "
                + " Where "
                + " UPPER(id_proveedor) like UPPER('%" + aguja + "%') OR"
                + " UPPER(nombre) like UPPER ('%" + aguja + "%')";
        
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProveedorVo pv = new ProveedorDao();

                pv.setId_proveedor(rs.getString(1));
                pv.setNombre(rs.getString(2));
                pv.setContacto(rs.getString(3));
                lista_proveedores.add(pv);

            }
            rs.close();//cerramos conexion base.
            return lista_proveedores;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ProveedorVo> mostrarDatosJoinID(String id_proveedor) {

        List<ProveedorVo> lista_proveedores = new ArrayList<>();
        String sql = "select *  "
                + " from proveedor "
                + " Where "
                + " id_proveedor = " + id_proveedor + ""
                + " order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProveedorVo pv = new ProveedorDao();

                pv.setId_proveedor(rs.getString(1));
                pv.setNombre(rs.getString(2));
                pv.setContacto(rs.getString(3));
                lista_proveedores.add(pv);

            }
            rs.close();//cerramos conexion base.
            return lista_proveedores;
        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO proveedor(\n"
                + "id_proveedor, nombre, contacto)\n"
                + "VALUES ('" + getId_proveedor() + "','" + getNombre() + "','" + getContacto() + "');";
        return conecta.accion(sql);
    }

    public int contadorPer(String id_proveedor) {
        int con = 0;
        String sql = "SELECT COUNT(id_proveedor) "
                + " FROM proveedor"
                + " where UPPER(id_proveedor) = UPPER('" + id_proveedor + "')";
        ResultSet rs = conecta.consulta(sql);
        try {
            while (rs.next()) {
                con = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public boolean modificar1(String identificador) {

        String sql;
        sql = "UPDATE proveedor set \"id_proveedor\"='" + getId_proveedor()+ "',\"nombre\"='" + getNombre()+ "',\"contacto\"='" +getContacto()+ "'"
                + "WHERE \"id_proveedor\"='" + identificador + "'";

        return conecta.accion(sql);   
    }

    public boolean eliminar(String identificador) {

        String sql = "delete from proveedor where \"id_proveedor\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
