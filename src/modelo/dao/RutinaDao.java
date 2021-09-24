/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.RutinaVo;

/**
 *
 * @author Casa
 */
public class RutinaDao extends RutinaVo {

    private PGConexion conecta = new PGConexion();

    public RutinaDao() {
    }

    public RutinaDao(int id_rutina, String nombre, String descripcion) {
        super(id_rutina, nombre, descripcion);
    }
    
    public List<RutinaVo> mostrarDatos() {

        List<RutinaVo> lista_rutina = new ArrayList<>();
        String sql = "select * from rutina order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                RutinaVo c = new RutinaDao();

                c.setId_rutina(rs.getInt("id_rutina"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                lista_rutina.add(c);

            }

            rs.close();//cerramos conexion base.
            return lista_rutina;
        } catch (SQLException ex) {
            Logger.getLogger(RutinaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO rutina(\n"
                + "nombre, descripcion)\n"
                + "VALUES ('" + getNombre() + "','" + getDescripcion()+ "');";
        return conecta.accion(sql);
    }

}
