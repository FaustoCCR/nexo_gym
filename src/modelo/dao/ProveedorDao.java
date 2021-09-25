package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.ProveedorVo;

public class ProveedorDao extends ProveedorVo {

    private PGConexion conecta = new PGConexion();

    public ProveedorDao() {
    }

    public ProveedorDao(String id_proveedor, String nombre, String contacto) {
        super(id_proveedor, nombre, contacto);
    }

    public List<ProveedorVo> mostrarDatos() {

        List<ProveedorVo> lista_proveedores = new ArrayList<>();
        String sql = "select * from proveedor order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProveedorVo pv = new ProveedorVo();

                pv.setId_proveedor(rs.getString("id_proveedor"));
                pv.setNombre(rs.getString("nombre"));
                pv.setContacto(rs.getString("contacto"));

                lista_proveedores.add(pv);

            }

            rs.close();//cerramos conexion base.
            return lista_proveedores;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO proveedor(\n"
                + "id_proveedor, nombre, contacto)\n"
                + "VALUES ('" + getId_proveedor() + "','" + getNombre() + "','" + getContacto()+ "');";
        return conecta.accion(sql);
    }

}
