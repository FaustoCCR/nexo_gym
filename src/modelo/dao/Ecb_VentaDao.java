package modelo.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.ClienteVo;
import modelo.vo.Ecb_VentaVo;

public class Ecb_VentaDao extends Ecb_VentaVo {

    private PGConexion conecta = new PGConexion();

    public Ecb_VentaDao() {
    }

    public Ecb_VentaDao(int id_ecb, int id_cliente, int id_user, Date fecha_venta) {
        super(id_ecb, id_cliente, id_user, fecha_venta);
    }

    public int mostrar_maxID() {

        String sql = "select max(id_ecb) from ecb_venta;";
        ResultSet rs = conecta.consulta(sql);
        int max_id = 0;
        try {

            while (rs.next()) {
                max_id = rs.getInt(1);
            }

            rs.close();//cerramos conexion base.
            return max_id;
        } catch (SQLException ex) {
            Logger.getLogger(Ecb_VentaDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public boolean insertar() {
        String sql = "INSERT INTO ecb_venta(\n"
                + "id_cliente, id_user, fecha_venta)\n"
                + "VALUES ('" + getId_cliente() + "','" + getId_user() + "','" + getFecha_venta() + "');";
        return conecta.accion(sql);
    }

}
