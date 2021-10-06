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

public class ClienteDao extends ClienteVo {

    private PGConexion conecta = new PGConexion();

    public ClienteDao() {
    }

    public ClienteDao(int id_cliente, int id_persona, int id_membresia, Date f_inicio, Date f_vence, double pago, boolean estado_pago) {
        super(id_cliente, id_persona, id_membresia, f_inicio, f_vence, pago, estado_pago);
    }

    public List<ClienteVo> mostrarDatosJoin(String aguja) {

        List<ClienteVo> lista_clientes = new ArrayList<>();
        String sql = "select c.id_cliente, p.dni, p.nombre||' '||p.apellido as \"Persona\",m.nombre,c.pago,c.f_inicio,c.f_vence,c.estado_pago\n"
                + "from cliente c\n"
                + "join persona p using(id_persona)\n"
                + "join membresia m using (id_membresia)\n"
                + "where upper(p.dni) like upper('%"+ aguja +"%') or \n"
                + "upper(p.nombre||' '||p.apellido) like upper('%" + aguja + "%') or\n"
                + "upper(m.nombre) like upper('%" + aguja + "%')\n"
                + "order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ClienteVo cl = new ClienteDao();

                cl.setId_cliente(rs.getInt(1));
                cl.setCedulapersona(rs.getString(2));
                cl.setNombrecliente(rs.getString(3));
                cl.setMembresia(rs.getString(4));
                cl.setPago(rs.getDouble(5));
                cl.setF_inicio(rs.getDate(6));
                cl.setF_vence(rs.getDate(7));
                cl.setEstado_pago(rs.getBoolean(8));

                lista_clientes.add(cl);

            }

            rs.close();//cerramos conexion base.
            return lista_clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ClienteVo> mostrarDatosJoin(int id) {

        List<ClienteVo> lista_clientes = new ArrayList<>();
        String sql = "select c.id_cliente, p.dni, p.nombre||' '||p.apellido as \"Persona\",m.nombre,c.pago, c.estado_pago, c.f_inicio, c.f_vence ,\n"
                + "case when f_inicio > current_date then f_vence - f_inicio else f_vence - current_date end, \n"
                + "case when c.estado_pago=true then 'Pagado' else 'Sin Pagar' end,m.descuento,\n"
                + "abs(extract(month from c.f_vence) - extract(month from c.f_inicio))\n"
                + "from cliente c\n"
                + "join persona p using(id_persona)\n"
                + "join membresia m using (id_membresia)\n"
                + "where c.id_cliente = " + id + "\n"
                + "order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ClienteVo cl = new ClienteDao();

                cl.setId_cliente(rs.getInt(1));
                cl.setCedulapersona(rs.getString(2));
                cl.setNombrecliente(rs.getString(3));
                cl.setMembresia(rs.getString(4));
                cl.setPago(rs.getDouble(5));
                cl.setEstado_pago(rs.getBoolean(6));
                cl.setF_inicio(rs.getDate(7));
                cl.setF_vence(rs.getDate(8));
                cl.setDias_faltantes(rs.getInt(9));
                cl.setEstadop(rs.getString(10));
                cl.setDesc(rs.getDouble(11));
                cl.setN_meses(rs.getInt(12));

                lista_clientes.add(cl);

            }

            rs.close();//cerramos conexion base.
            return lista_clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ClienteVo> mostrarDatosJoinDNI(String cedula, int id_cliente) {

        List<ClienteVo> lista_clientes = new ArrayList<>();
        String sql = "select c.id_cliente,p.dni\n"
                + "from cliente c\n"
                + "join persona p on(c.id_persona=p.id_persona)\n"
                + "where p.dni ='" + cedula + "' and not id_cliente = " + id_cliente;
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ClienteVo c = new ClienteVo();

                c.setId_cliente(rs.getInt(1));
                c.setCedulapersona(rs.getString(2));

                lista_clientes.add(c);

            }

            rs.close();//cerramos conexion base.
            return lista_clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO cliente(\n"
                + "id_persona, id_membresia, f_inicio, f_vence, pago, estado_pago)\n"
                + "VALUES ('" + getId_persona() + "','" + getId_membresia() + "','" + getF_inicio() + "','" + getF_vence()
                + "','" + getPago() + "','" + isEstado_pago() + "');";
        return conecta.accion(sql);
    }

    public boolean modificar(int identificador) {

        String sql;
        sql = "UPDATE cliente set \"id_persona\"='" + getId_persona() + "',\"id_membresia\"='" + getId_membresia()
                + "',\"f_inicio\"='" + getF_inicio() + "',\"f_vence\"='" + getF_vence() + "',\"pago\"='" + getPago()
                + "',\"estado_pago\"='" + isEstado_pago() + "'"
                + "WHERE \"id_cliente\"='" + identificador + "'";

        return conecta.accion(sql);
    }

    public boolean eliminar(int identificador) {

        String sql = "delete from cliente where \"id_cliente\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
