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

    public ClienteDao(int id_cliente, int id_persona, int id_membresia, Date f_inicio, Date f_vence, double pago) {
        super(id_cliente, id_persona, id_membresia, f_inicio, f_vence, pago);
    }

    public List<ClienteVo> mostrarDatos() {

        List<ClienteVo> lista_clientes = new ArrayList<>();
        String sql = "select * from cliente order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ClienteVo cl = new ClienteDao();

                cl.setId_cliente(rs.getInt("id_cliente"));
                cl.setId_persona(rs.getInt("id_persona"));
                cl.setId_membresia(rs.getInt("id_membresia"));
                cl.setF_inicio(rs.getDate("f_inicio"));
                cl.setF_vence(rs.getDate("f_vence"));
                cl.setPago(rs.getDouble("pago"));

                lista_clientes.add(cl);

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
                + "id_persona, id_membresia, f_inicio, f_vence, pago)\n"
                + "VALUES ('" + getId_persona() + "','" + getId_membresia() + "','" + getF_inicio() + "','" + getF_vence()
                + "','" + getPago() + "');";
        return conecta.accion(sql);
    }

}
