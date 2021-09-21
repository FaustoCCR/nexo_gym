package modelo.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.PersonaVo;

public class PersonaDao extends PersonaVo {

    private PGConexion conecta = new PGConexion();

    public PersonaDao() {
    }

    public PersonaDao(int id_persona, String dni, String nombre, String apellido, Date birthdate, String correo, char genero, String direccion, String telefono) {
        super(id_persona, dni, nombre, apellido, birthdate, correo, genero, direccion, telefono);
    }

    public List<PersonaVo> mostrarDatos() {
        
        List<PersonaVo> lista_persona = new ArrayList<>();
        String sql = "select * from persona";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                PersonaVo p = new PersonaVo();
                p.setId_persona(rs.getInt("id_persona"));
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setBirthdate(rs.getDate("birthdate"));
                p.setCorreo(rs.getString("correo"));
                p.setGenero(rs.getString("genero").charAt(0));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));

                lista_persona.add(p);

            }

            rs.close();//cerramos conexion base.
            return lista_persona;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<PersonaVo> listarPersonas(String aguja){
           
       try {
           String sql="select * from persona WHERE ";
            sql+=" UPPER(nombre) like UPPER('%"+aguja+"%') OR";
            sql+=" UPPER(apellido) like UPPER('%"+aguja+"%') OR";
            sql+=" id_persona = ("+aguja+") ";
            ResultSet rs= conecta.consulta(sql);
            List<PersonaVo> lista=new ArrayList<PersonaVo>();
           while(rs.next()){
             PersonaVo p= new PersonaVo();
             p.setId_persona(rs.getInt("id_persona"));
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setBirthdate(rs.getDate("birthdate"));
                p.setCorreo(rs.getString("correo"));
                p.setGenero(rs.getString("genero").charAt(0));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
             
             lista.add(p);
           }
  
           rs.close();
           return lista;
       } catch (SQLException ex) {
           Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }      
    }
    
    public boolean grabar(){
    String sql;
        sql="INSERT INTO persona(id_persona,dni,nombre,apellido,birthdate,correo,genero,direccion,telefono) ";
        sql+=" VALUES ('"+getId_persona()+"','"+getDni()+"','"+getNombre()+"','"+getApellido()+"','"+getBirthdate()+"','"+getCorreo()+"','"+getGenero()+"','"+getDireccion()+"','"+getTelefono()+"')";
        return conecta.accion(sql);
    
    }
    
    public boolean eliminar(String indentificar){
        String nsql = "delete from persona where \"idpersona\"='" +indentificar+ "'";
        if(conecta.accion(nsql)){
            
            return true;
        }
        else
        {
            System.out.println("Error eliminar");
            return false;
        }
    }
    
    public boolean modificar(String identificador){
        //System.out.println(getFechaNacimiento()+"");
        String nsql = "UPDATE persona set \"nombre\"='"+getNombre()+"',\"apellido\"='"+getApellido()+"',\"birthdate\"='"+getBirthdate()+"',\"correo\"='"+getCorreo()+"',\"genero\"='"+getGenero()+"',\"direccion\"='"+getDireccion()+"',\"telefono\"='"+getTelefono()+"'"
         + "WHERE \"id_persona\"='"+identificador+"'";
        return conecta.accion(nsql);
    
    }
}
