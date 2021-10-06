
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
    
    
    //Muestra Datos
    public List<EmpleadoVo> mostrarDatosJoin() {

        List<EmpleadoVo> lista_clientes = new ArrayList<>();
        String sql = "select e.id_empleado, p.nombre||' '||p.apellido \"Persona\",c.nombre,e.fecha_contrato,e.sueldo\n"
                + "from empleado e\n"
                + "join persona p using(id_persona)\n"
                + "join cargo c using (id_cargo)";
        ResultSet rs = conecta.consulta(sql);

        try {
            while (rs.next()) {
                EmpleadoVo em = new EmpleadoDao();
                
                em.setId_empleado(rs.getInt(1));
                em.setNombrecliente(rs.getString(2));
                em.setNombreCargo(rs.getString(3));
                em.setFecha_contrato(rs.getDate(4));
                em.setSueldo(rs.getDouble(5));
                lista_clientes.add(em);
            }
            
            rs.close();//cerramos conexion base.
            return lista_clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean eliminar(int identificador) {
        String sql = "delete from empleado where \"id_empleado\"='" + identificador + "'";
    return conecta.accion(sql);
    }
    
}