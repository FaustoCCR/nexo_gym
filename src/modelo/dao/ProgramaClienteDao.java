package modelo.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.PGConexion;
import modelo.vo.ProgramaClienteVo;

public class ProgramaClienteDao extends ProgramaClienteVo {

    private PGConexion conecta = new PGConexion();

    public ProgramaClienteDao() {
    }

    public ProgramaClienteDao(int id_pgcliente, int id_cliente, int id_rutina, int id_empleado, Date fecha) {
        super(id_pgcliente, id_cliente, id_rutina, id_empleado, fecha);
    }

    public List<ProgramaClienteVo> mostrarDatos() {

        List<ProgramaClienteVo> lista_programas = new ArrayList<>();
        String sql = "select * from programa_cliente order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProgramaClienteVo pgc = new ProgramaClienteVo();

                pgc.setId_pgcliente(rs.getInt(1));
                pgc.setId_cliente(rs.getInt(2));
                pgc.setId_rutina(rs.getInt(3));
                pgc.setId_empleado(4);
                pgc.setFecha(rs.getDate(5));

                lista_programas.add(pgc);

            }

            rs.close();//cerramos conexion base.
            return lista_programas;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<ProgramaClienteVo> mostrarDatosJoin(int id) {

        List<ProgramaClienteVo> lista_programas = new ArrayList<>();
        String sql = "select pgc.id_pgcliente,p.nombre||' '||p.apellido \"Persona\",r.nombre \"Rutina\",replace(r.descripcion,chr(10),' | '),p2.nombre||' '||p2.apellido \"Instructor\",pgc.fecha\n"
                + "from programa_cliente pgc\n"
                + "join cliente c on(pgc.id_cliente = c.id_cliente)\n"
                + "join persona p on(c.id_persona = p.id_persona)\n"
                + "join rutina r on(pgc.id_rutina = r.id_rutina)\n"
                + "join empleado em on(pgc.id_empleado = em.id_empleado)\n"
                + "join persona p2 on(em.id_persona = p2.id_persona)\n"
                + "where c.id_cliente = " + id + "\n"
                + "order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProgramaClienteVo pgc = new ProgramaClienteVo();

                pgc.setId_pgcliente(rs.getInt(1));
                pgc.setNombreCliente(rs.getString(2));
                pgc.setNombreRutina(rs.getString(3));
                pgc.setDescripcion_rutina(rs.getString(4));
                pgc.setNombreInstructor(rs.getString(5));
                pgc.setFecha(rs.getDate(6));

                lista_programas.add(pgc);

            }

            rs.close();//cerramos conexion base.
            return lista_programas;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<ProgramaClienteVo> mostrarDatosJoin2(int id) {

        List<ProgramaClienteVo> lista_programas = new ArrayList<>();
        String sql = "select pgc.id_pgcliente,p.nombre||' '||p.apellido \"Persona\",r.nombre \"Rutina\",r.descripcion,p2.nombre||' '||p2.apellido \"Instructor\",pgc.fecha\n"
                + "from programa_cliente pgc\n"
                + "join cliente c on(pgc.id_cliente = c.id_cliente)\n"
                + "join persona p on(c.id_persona = p.id_persona)\n"
                + "join rutina r on(pgc.id_rutina = r.id_rutina)\n"
                + "join empleado em on(pgc.id_empleado = em.id_empleado)\n"
                + "join persona p2 on(em.id_persona = p2.id_persona)\n"
                + "where pgc.id_pgcliente = " + id + "\n"
                + "order by 1;";
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProgramaClienteVo pgc = new ProgramaClienteVo();

                pgc.setId_pgcliente(rs.getInt(1));
                pgc.setNombreCliente(rs.getString(2));
                pgc.setNombreRutina(rs.getString(3));
                pgc.setDescripcion_rutina(rs.getString(4));
                pgc.setNombreInstructor(rs.getString(5));
                pgc.setFecha(rs.getDate(6));

                lista_programas.add(pgc);

            }

            rs.close();//cerramos conexion base.
            return lista_programas;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean insertar() {
        String sql = "INSERT INTO programa_cliente(id_cliente, id_rutina, id_empleado , fecha)\n"
                + "VALUES ('" + getId_cliente() + "','" + getId_rutina() + "','" + getId_empleado() + "','"
                + getFecha() + "');";
        return conecta.accion(sql);
    }

    public boolean modificar(int identificador) {

        String sql;
        sql = "UPDATE programa_cliente set \"id_cliente\"='" + getId_cliente() + "',\"id_rutina\"='" + getId_rutina()
                + "',\"id_empleado\"='" + getId_empleado() + "',\"fecha\"='" + getFecha() + "'"
                + "WHERE \"id_pgcliente\"='" + identificador + "'";

        return conecta.accion(sql);
    }

    public boolean eliminar(int identificador) {
        String sql = "delete from programa_cliente where \"id_pgcliente\"='" + identificador + "'";
        return conecta.accion(sql);
    }
}
