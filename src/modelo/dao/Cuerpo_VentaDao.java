package modelo.dao;

import modelo.conexion.PGConexion;
import modelo.vo.Cuerpo_VentaVo;

public class Cuerpo_VentaDao extends Cuerpo_VentaVo {

    private PGConexion conecta = new PGConexion();

    public Cuerpo_VentaDao() {
    }

    public Cuerpo_VentaDao(int id_cp, int id_ecb, int id_producto, int cantidad, double costo_final) {
        super(id_cp, id_ecb, id_producto, cantidad, costo_final);
    }

    public boolean insertar() {
        String sql = "INSERT INTO cuerpo_venta(\n"
                + "id_ecb, id_prod, cantidad, costo_final)\n"
                + "VALUES ('" + getId_ecb() + "','" + getId_producto() + "','" + getCantidad() + "','" + getCosto_final() + "');";
        return conecta.accion(sql);
    }

}
