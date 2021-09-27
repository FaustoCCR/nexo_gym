package modelo.dao;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.ProductoVo;
import modelo.vo.UsuarioVo;

public class ProductoDao extends ProductoVo {

    private PGConexion conecta = new PGConexion();

    public ProductoDao() {
    }

    public ProductoDao(int id_prod, String nombre, int id_ctgp, String id_proveedor, String descripcion, double precio_u, int stock, byte[] foto) {
        super(id_prod, nombre, id_ctgp, id_proveedor, descripcion, precio_u, stock, foto);
    }

    public List<ProductoVo> mostrarDatos() {

        List<ProductoVo> lista_productos = new ArrayList<>();
        String sql = "select * from producto order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProductoVo pr = new ProductoVo();

                pr.setId_prod(rs.getInt("id_prod"));
                pr.setNombre(rs.getString("nombre"));
                pr.setId_ctgp(rs.getInt("id_ctgp"));
                pr.setId_proveedor(rs.getString("id_proveedor"));
                pr.setDescripcion(rs.getString("descripcion"));
                pr.setPrecio_u(rs.getDouble("precio_u"));
                pr.setStock(rs.getInt("stock"));
                pr.setFoto(rs.getBytes("imagen"));

                lista_productos.add(pr);

            }

            rs.close();//cerramos conexion base.
            return lista_productos;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO producto(\n"
                + "nombre, id_ctgp, id_proveedor, descripcion, precio_u, stock, imagen)\n"
                + "VALUES ('" + getNombre() + "','" + getId_ctgp() + "','" + getId_proveedor() + "','" + getDescripcion()
                + "','" + getPrecio_u() + "','" + getStock() + "','" + getFoto() + "');";
        return conecta.accion(sql);
    }

}
