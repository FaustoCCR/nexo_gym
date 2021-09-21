package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.CargoVo;

public class CargoDao extends CargoVo {

    private PGConexion conecta = new PGConexion();

    public CargoDao() {
    }

    public CargoDao(int id_cargo, String nombre, String descripcion) {
        super(id_cargo, nombre, descripcion);
    }

    public List<CargoVo> mostrarDatos() {

        List<CargoVo> lista_cargos = new ArrayList<>();
        String sql = "select * from cargo order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                CargoVo c = new CargoDao();

                c.setId_cargo(rs.getInt("id_cargo"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista_cargos.add(c);

            }

            rs.close();//cerramos conexion base.
            return lista_cargos;
        } catch (SQLException ex) {
            Logger.getLogger(CargoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO cargo(\n"
                + "nombre, descripcion)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion() + "');";
        return conecta.accion(sql);
    }

}
