package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.Cuerpo_VentaVo;

public class Cuerpo_VentaDao extends Cuerpo_VentaVo {

    private PGConexion conecta = new PGConexion();

    public Cuerpo_VentaDao() {
    }

    public Cuerpo_VentaDao(int id_cp, int id_ecb, int id_producto, int cantidad, double costo_final) {
        super(id_cp, id_ecb, id_producto, cantidad, costo_final);
    }

    public List<Cuerpo_VentaVo> mostrarDatosJoin(String aguja, String consultaextra) {

        List<Cuerpo_VentaVo> lista_ventas = new ArrayList<>();
        String sql = "select ec.id_ecb, p.nombre||' '||p.apellido \"Cliente\",\n"
                + "v.nombre||' '||v.apellido \"Vendedor\",ec.fecha_venta,\n"
                + "sum(cv.cantidad) \"Nro Productos\", sum(cv.costo_final) \"Total\"\n"
                + "from cuerpo_venta cv\n"
                + "join ecb_venta ec on(cv.id_ecb=ec.id_ecb)\n"
                + "join cliente c on(ec.id_cliente = c.id_cliente)\n"
                + "join persona p on(c.id_persona = p.id_persona)\n"
                + "join usuario u on(ec.id_user = u.id_user)\n"
                + "join persona v on(u.id_persona = v.id_persona)\n"
                + "where (upper(p.nombre||' '||p.apellido) like upper('%" + aguja + "%') \n"
                + "or upper(v.nombre||' '||v.apellido) like upper('%" + aguja + "%'))\n"
                + consultaextra + "\n"
                + "group by ec.id_ecb, p.nombre||' '||p.apellido ,\n"
                + "v.nombre||' '||v.apellido,ec.fecha_venta\n"
                + "order by ec.fecha_venta;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                Cuerpo_VentaVo cv = new Cuerpo_VentaVo();

                cv.setId_ecb(rs.getInt(1));
                cv.setCliente(rs.getString(2));
                cv.setVendedor(rs.getString(3));
                cv.setFecha_venta(rs.getDate(4));
                cv.setNro_productos(rs.getInt(5));
                cv.setTotal(rs.getDouble(6));

                lista_ventas.add(cv);

            }

            rs.close();//cerramos conexion base.
            return lista_ventas;
        } catch (SQLException ex) {
            Logger.getLogger(Cuerpo_VentaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Cuerpo_VentaVo> mostrarDatosJoinEspecifico(int id_ecb) {

        List<Cuerpo_VentaVo> lista_ventas = new ArrayList<>();
        String sql = "select ec.id_ecb \"Id\", ec.fecha_venta \"Fecha Venta\" ,p.dni \"Cedula\",p.nombre||' '||p.apellido \"Cliente\",\n"
                + "v.nombre||' '||v.apellido \"Vendedor\",pr.id_prod \"ID prod\",pr.nombre \"Producto\",\n"
                + "cv.cantidad \"Cantidad\",pr.precio_u \"PrecioU\",cv.costo_final \"Precio Total\"\n"
                + "from cuerpo_venta cv\n"
                + "join ecb_venta ec on(cv.id_ecb=ec.id_ecb)\n"
                + "join cliente c on(ec.id_cliente = c.id_cliente)\n"
                + "join persona p on(c.id_persona = p.id_persona)\n"
                + "join usuario u on(ec.id_user = u.id_user)\n"
                + "join persona v on(u.id_persona = v.id_persona)\n"
                + "join producto pr on(cv.id_prod=pr.id_prod)\n"
                + "where ec.id_ecb = " + id_ecb + "\n"
                + "order by ec.fecha_venta;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                Cuerpo_VentaVo cv = new Cuerpo_VentaVo();

                cv.setId_ecb(rs.getInt(1));
                cv.setFecha_venta(rs.getDate(2));
                cv.setDni(rs.getString(3));
                cv.setCliente(rs.getString(4));
                cv.setVendedor(rs.getString(5));
                cv.setId_producto(rs.getInt(6));
                cv.setNombre_producto(rs.getString(7));
                cv.setCantidad(rs.getInt(8));
                cv.setPrecio_u(rs.getDouble(9));
                cv.setTotal(rs.getDouble(10));

                lista_ventas.add(cv);

            }

            rs.close();//cerramos conexion base.
            return lista_ventas;
        } catch (SQLException ex) {
            Logger.getLogger(Cuerpo_VentaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO cuerpo_venta(\n"
                + "id_ecb, id_prod, cantidad, costo_final)\n"
                + "VALUES ('" + getId_ecb() + "','" + getId_producto() + "','" + getCantidad() + "','" + getCosto_final() + "');";
        return conecta.accion(sql);
    }

}
