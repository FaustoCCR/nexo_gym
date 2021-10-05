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
        String sql = "select * from persona order by 1";
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
    
    public List<PersonaVo> mostrarDatosJoin(String aguja) {

        List<PersonaVo> lista_clientes = new ArrayList<>();
        String sql = "select p.id_persona, p.nombre||' '||p.apellido \"Persona\", p.birthdate, p.correo, p.direccion, p.telefono"
                + " from persona p"
                + " where upper(p.nombre||' '||p.apellido)  like upper('%" + aguja + "%') or"
                + " upper(p.dni) like upper('%" + aguja + "%')"
                + " order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                PersonaVo pl = new PersonaDao();

                pl.setId_persona(rs.getInt(1));
                pl.setNombre(rs.getString(2));
               // pl.setApellido(rs.getString(3));
                pl.setBirthdate(rs.getDate(3));
                pl.setCorreo(rs.getString(4));
                pl.setDireccion(rs.getString(5));
                pl.setTelefono(rs.getString(6));

                lista_clientes.add(pl);

            }

            rs.close();//cerramos conexion base.
            return lista_clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public List<PersonaVo> mostrarDatosJoin(int id_persona) {

        List<PersonaVo> lista_persona = new ArrayList<>();
        String sql = "select * "
                + " from persona "
                + " where id_persona = '" + id_persona + "'\n"
                + " order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                PersonaVo pl = new PersonaDao();

                pl.setId_persona(rs.getInt(1));
                pl.setDni(rs.getString(2));
                pl.setNombre(rs.getString(3));
                pl.setApellido(rs.getString(4));
                pl.setBirthdate(rs.getDate(5));
                pl.setCorreo(rs.getString(6));
                pl.setGenero(rs.getString(7).charAt(0));
                pl.setDireccion(rs.getString(8));
                pl.setTelefono(rs.getString(9));

                lista_persona.add(pl);

            }

            rs.close();//cerramos conexion base.
            return lista_persona;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
     public int contadorPer(String cedula){
        int con=0;
    String sql="SELECT COUNT(dni) "
            + " FROM persona"
            + " where dni = '"+cedula+"'";
          ResultSet rs = conecta.consulta(sql);
        try {
            while (rs.next()) {                
                con=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return con;
    }

    public boolean grabar() {
        String sql;
        sql = "INSERT INTO persona(id_persona,dni,nombre,apellido,birthdate,correo,genero,direccion,telefono) ";
        sql += " VALUES (" + getId_persona() + ",'" + getDni() + "','" + getNombre() + "','" + getApellido() + "','" + getBirthdate() + "','" + getCorreo() + "','" + getGenero() + "','" + getDireccion() + "','" + getTelefono() + "')";
        return conecta.accion(sql);

    }
    
    public boolean modificar1(int identificador) {

        String sql;
        sql = "UPDATE persona set \"dni\"='" + getDni() + "',\"nombre\"='" + getNombre()
                + "',\"apellido\"='" + getApellido() + "',\"birthdate\"='" + getBirthdate() 
                + "',\"correo\"='" + getCorreo() + "',\"genero\"='" + getGenero()
                + "',\"direccion\"='" + getDireccion() + "',\"telefono\"='" + getTelefono() + "'"
                + "WHERE \"id_persona\"='" + identificador + "'";

        return conecta.accion(sql);
    }
    
    public boolean eliminar(int identificador) {

        String sql = "delete from persona where \"id_persona\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
