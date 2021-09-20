package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.UsuarioVo;

public class UsuarioDao extends UsuarioVo {

    private PGConexion conecta = new PGConexion();

    public UsuarioDao() {
    }

    public UsuarioDao(int id_user, int id_persona, int id_rol, String user_name, String password, boolean estado_cuenta) {
        super(id_user, id_persona, id_rol, user_name, password, estado_cuenta);
    }

    public List<UsuarioVo> mostrarDatos() {

        List<UsuarioVo> lista_usuarios = new ArrayList<>();
        String sql = "select * from usuario order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                UsuarioVo u = new UsuarioDao();

                u.setId_user(rs.getInt("id_user"));
                u.setId_rol(rs.getInt("id_persona"));
                u.setId_rol(rs.getInt("id_rol"));
                u.setUser_name(rs.getString("user_name"));
                u.setPassword(rs.getString("password"));
                u.setEstado_cuenta(rs.getBoolean("estado_cuenta"));

                lista_usuarios.add(u);

            }

            rs.close();//cerramos conexion base.
            return lista_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO usuario(\n"
                + "	id_persona, id_rol, user_name, password, estado_cuenta)\n"
                + "	VALUES ('" + getId_persona() + "','" + getId_rol() + "','" + getUser_name() + "','" + getPassword()
                + "','" + isEstado_cuenta() + "');";
       return conecta.accion(sql);
    }

}
