package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.RolVo;

public class RolDao extends RolVo {

    private PGConexion conecta = new PGConexion();

    public RolDao() {
    }

    public RolDao(int id_rol, String nombre, String descripcion) {
        super(id_rol, nombre, descripcion);
    }

    public List<RolVo> mostrarDatos() {

        List<RolVo> lista_roles = new ArrayList<>();
        String sql = "select * from rol order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                RolVo r = new RolDao();

                r.setId_rol(rs.getInt("id_rol"));
                r.setNombre(rs.getString("nombre"));
                r.setDescripcion(rs.getString("descripcion"));

                lista_roles.add(r);

            }

            rs.close();//cerramos conexion base.
            return lista_roles;
        } catch (SQLException ex) {
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
