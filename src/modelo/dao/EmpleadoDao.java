
package modelo.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.EmpleadoVo;

/**
 *
 * @author Alex
 */

public class EmpleadoDao extends EmpleadoVo{
     private PGConexion conecta = new PGConexion();
     
    //Constructor por defecto
    public EmpleadoDao(){
    }
    
    //Constructor con parametros
     public EmpleadoDao(int id_empleado, int id_persona, int id_cargo, Date fecha_contrato, double sueldo) {
        super(id_empleado, id_persona, id_cargo, fecha_contrato, sueldo);
    }
      public List<EmpleadoVo> mostrarDatos() {

        List<EmpleadoVo> lista_empleados = new ArrayList<>();
        String sql = "select * from empleado order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {
            while (rs.next()) {
                EmpleadoVo e1 = new EmpleadoDao();
                e1.setId_empleado(rs.getInt("id_empleado"));
                e1.setId_persona(rs.getInt("id_persona"));
                e1.setId_cargo(rs.getInt("id_cargo"));
                e1.setFecha_contrato(rs.getDate("fecha_contrato"));
                e1.setSueldo(rs.getDouble("sueldo"));
                lista_empleados.add(e1);
            }
            rs.close();//cerramos conexion base.
            return lista_empleados;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
     
    //Metodo para grabar empleados
    public boolean grabar(){
        String sql;
        sql="INSERT INTO empleado(id_persona, id_cargo, fecha_contrato, sueldo) ";
        sql+=" VALUES ('"+getId_persona()+"','"+getId_cargo()+"','"+getFecha_contrato()+"','"+getSueldo()
                +"')";
        return conecta.accion(sql);
    }
    
}