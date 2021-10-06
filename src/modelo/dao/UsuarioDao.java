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

    public List<UsuarioVo> mostrarDatosJoin(String aguja) {

        List<UsuarioVo> lista_usuarios = new ArrayList<>();
        String sql = "select u.id_user,p.dni,p.nombre||' '||p.apellido \"Persona\",\n"
                + "u.user_name,r.nombre,u.estado_cuenta\n"
                + "from usuario u\n"
                + "join persona p on(u.id_persona=p.id_persona)\n"
                + "join rol r on (u.id_rol=r.id_rol)\n"
                + "where upper(p.nombre||' '||p.apellido)  like upper('%" + aguja + "%') or \n"
                + "upper(u.user_name) like upper('%" + aguja + "%')\n"
                + "order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                UsuarioVo u = new UsuarioDao();
                u.setId_persona(rs.getInt(1));
                u.setCedula(rs.getString(2));
                u.setNombres_usuario(rs.getString(3));
                u.setUser_name(rs.getString(4));
                u.setNombre_rol(rs.getString(5));
                u.setEstado_cuenta(rs.getBoolean(6));

                lista_usuarios.add(u);

            }

            rs.close();//cerramos conexion base.
            return lista_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<UsuarioVo> mostrarDatosJoin(int id_user) {

        List<UsuarioVo> lista_usuarios = new ArrayList<>();
        String sql = "select u.id_user, p.dni,p.nombre||' '||p.apellido \"Persona\",\n"
                + "u.user_name,r.nombre,\n"
                + "case when u.estado_cuenta=true then 'Activo' else 'Inactivo' end\n"
                + "from usuario u\n"
                + "join persona p on(u.id_persona=p.id_persona)\n"
                + "join rol r on (u.id_rol=r.id_rol)\n"
                + "where u.id_user = '" + id_user + "'\n"
                + "order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                UsuarioVo u = new UsuarioDao();
                u.setId_user(rs.getInt(1));
                u.setCedula(rs.getString(2));
                u.setNombres_usuario(rs.getString(3));
                u.setUser_name(rs.getString(4));
                u.setNombre_rol(rs.getString(5));
                u.setEstado(rs.getString(6));

                lista_usuarios.add(u);

            }

            rs.close();//cerramos conexion base.
            return lista_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<UsuarioVo> mostrarDatosJoinDNI(String cedula, int id_user) {

        List<UsuarioVo> lista_usuarios = new ArrayList<>();
        String sql = "select u.id_user,p.dni\n"
                + "from usuario u\n"
                + "join persona p on(u.id_persona=p.id_persona)\n"
                + "where p.dni ='"+ cedula +"' and not id_user = " +id_user ;
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                UsuarioVo u = new UsuarioDao();
                u.setId_user(rs.getInt(1));
                u.setId_persona(rs.getInt(2));

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
                + "id_persona, id_rol, user_name, password, estado_cuenta)\n"
                + "VALUES ('" + getId_persona() + "','" + getId_rol() + "','" + getUser_name() + "','" + getPassword()
                + "','" + isEstado_cuenta() + "');";
        return conecta.accion(sql);
    }

    public boolean modificar1(int identificador) {

        String sql;
        sql = "UPDATE usuario set \"id_persona\"='" + getId_persona() + "',\"id_rol\"='" + getId_rol()
                + "',\"user_name\"='" + getUser_name() + "',\"estado_cuenta\"='" + isEstado_cuenta() + "'"
                + "WHERE \"id_user\"='" + identificador + "'";

        return conecta.accion(sql);
    }

    public boolean modificarPassword(int identificador) {
        String sql = "UPDATE usuario set \"password\"='" + getPassword() + "'"
                + "WHERE \"id_user\"='" + identificador + "'";

        return conecta.accion(sql);
    }

    public boolean eliminar(int identificador) {

        String sql = "delete from usuario where \"id_user\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
