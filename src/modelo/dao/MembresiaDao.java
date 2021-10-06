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

    public List<MembresiaVo> mostrarDatos(String aguja) {

        List<MembresiaVo> lista_membresias = new ArrayList<>();
        String sql = "select * from membresia "
                + "where upper(nombre) like upper('%" + aguja + "%')\n"
                + "order by 1";
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

    public List<MembresiaVo> mostrarDatos(int id) {

        List<MembresiaVo> lista_membresias = new ArrayList<>();
        String sql = "select * from membresia "
                + "where id_membresia = " + id + "\n"
                + "order by 1";
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
        String sql = "INSERT INTO membresia("
                + "nombre, descripcion, descuento)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion() + "','" + getDescuento() + "');";
        return conecta.accion(sql);
    }

    public boolean modificar(int identificador) {

        String sql;
        sql = "UPDATE membresia set \"nombre\"='" + getNombre() + "',\"descripcion\"='" + getDescripcion() + "',\"descuento\"='" + getDescuento() + "'"
                + "WHERE \"id_membresia\"='" + identificador + "'";

        return conecta.accion(sql);
    }

    public boolean eliminar(int identificador) {

        String sql = "delete from membresia where \"id_membresia\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
