package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.Ctg_ProductoVo;

public class Ctg_ProductoDao extends Ctg_ProductoVo {

    private PGConexion conecta = new PGConexion();

    public Ctg_ProductoDao() {
    }

    public Ctg_ProductoDao(int id_ctgp, String nombre, String descripcion) {
        super(id_ctgp, nombre, descripcion);
    }

    public List<Ctg_ProductoVo> mostrarDatos() {

        List<Ctg_ProductoVo> lista_categorias = new ArrayList<>();
        String sql = "select * from ctg_producto order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                Ctg_ProductoVo ctg = new Ctg_ProductoVo();

                ctg.setId_ctgp(rs.getInt("id_ctgp"));
                ctg.setNombre(rs.getString("nombre"));
                ctg.setDescripcion(rs.getString("descripcion"));

                lista_categorias.add(ctg);

            }

            rs.close();//cerramos conexion base.
            return lista_categorias;
        } catch (SQLException ex) {
            Logger.getLogger(Ctg_ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public List<Ctg_ProductoVo> mostrarDatosJoin(String aguja) {

        List<Ctg_ProductoVo> lista_ctg_producto = new ArrayList<>();
        String sql = "select * "
                + " from ctg_producto "
                + " Where "
                + " UPPER(nombre) like UPPER('%"+aguja+"%')";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                Ctg_ProductoVo ctg_p = new Ctg_ProductoDao();

                ctg_p.setId_ctgp(rs.getInt(1));
                ctg_p.setNombre(rs.getString(2));
                ctg_p.setDescripcion(rs.getString(3));
                lista_ctg_producto.add(ctg_p);

            }
            rs.close();//cerramos conexion base.
            return lista_ctg_producto;
        } catch (SQLException ex) {
            Logger.getLogger(Ctg_ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
    public List<Ctg_ProductoVo> mostrarDatosJoin(int id_ctgp) {

        List<Ctg_ProductoVo> lista_ctg_producto = new ArrayList<>();
        String sql = "select *  "
                + " from ctg_producto "
                + " Where "
                + " id_ctgp = "+id_ctgp+""
                + " order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                Ctg_ProductoVo ctg_p = new Ctg_ProductoDao();

                ctg_p.setId_ctgp(rs.getInt(1));
                ctg_p.setNombre(rs.getString(2));
                ctg_p.setDescripcion(rs.getString(3));
                lista_ctg_producto.add(ctg_p);

            }
            rs.close();//cerramos conexion base.
            return lista_ctg_producto;
        } catch (SQLException ex) {
            Logger.getLogger(Ctg_ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public int contadorCtg(String nombre){
        int con=0;
    String sql="SELECT COUNT(nombre) "
            + " FROM ctg_producto"
            + " where UPPER(nombre) = UPPER('"+nombre+"')";
          ResultSet rs = conecta.consulta(sql);
        try {
            while (rs.next()) {                
                con=rs.getInt(1);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return con;
    }

    public boolean insertar() {
        String sql = "INSERT INTO ctg_producto(\n"
                + "nombre, descripcion)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion() + "');";
        return conecta.accion(sql);
    }
    
    public boolean modificar1(int identificador) {

        String sql;
        sql = "UPDATE ctg_producto set \"nombre\"='" + getNombre()+ "',\"descripcion\"='" + getDescripcion()+"'"
                + "WHERE \"id_ctgp\"='" + identificador +"'";

        return conecta.accion(sql);
    }
    
     public boolean eliminar(int identificador) {

        String sql = "delete from ctg_producto where \"id_ctgp\"='" + identificador + "'";

        return conecta.accion(sql);
    }


}
