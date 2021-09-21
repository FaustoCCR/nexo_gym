package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.MembresiaVo;

public class MembresiaDao extends MembresiaVo {

    private PGConexion conecta = new PGConexion();

    public MembresiaDao() {
    }

    public MembresiaDao(int id_membresia, String nombre, String descripcion, double descuento) {
        super(id_membresia, nombre, descripcion, descuento);
    }

    public List<MembresiaVo> mostrarDatos() {

        List<MembresiaVo> lista_membresias = new ArrayList<>();
        String sql = "select * from membresia order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                MembresiaVo m = new MembresiaDao();

                m.setId_membresia(rs.getInt("id_membresia"));
                m.setNombre(rs.getString("nombre"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setDescuento(rs.getDouble("descuento"));

                lista_membresias.add(m);

            }

            rs.close();//cerramos conexion base.
            return lista_membresias;
        } catch (SQLException ex) {
            Logger.getLogger(MembresiaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean insertar() {
        String sql = "INSERT INTO membresia(\n"
                + "nombre, descripcion, descuento)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion() + "','" + getDescuento() + "');";
        return conecta.accion(sql);
    }

}
