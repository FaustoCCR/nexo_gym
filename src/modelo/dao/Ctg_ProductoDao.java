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

    public boolean insertar() {
        String sql = "INSERT INTO ctg_producto(\n"
                + "nombre, descripcion)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion() + "');";
        return conecta.accion(sql);
    }

}
