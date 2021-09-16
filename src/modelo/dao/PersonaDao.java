package modelo.dao;

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

        try {
            List<PersonaVo> lista_persona = new ArrayList<>();
            String sql = "select * from persona order by 1";
            ResultSet rs = conecta.consulta(sql);
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

}
