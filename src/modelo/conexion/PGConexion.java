package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PGConexion {

    /* conexion a la base de datos Postgresql*/
 
    Connection con; //conexion
    Statement st;//comandos sql
    ResultSet rs;//resultados de consulta.
    
    String CadenaConexion = "jdbc:postgresql://localhost:5432/nexo_gym"; //cadena de conección
    String usuarioBD = "postgres";
    String contraBD = "1989";//contraseña de la BD tres4
    
 

    public PGConexion() {

        try {
            Class.forName("org.postgresql.Driver");//paquete jar que importamos para postgresql
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PGConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(CadenaConexion, usuarioBD, contraBD);//conexion a la base
        } catch (SQLException ex) {
            Logger.getLogger(PGConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet consulta(String sqlc) {

        try {

            st = con.createStatement();//crear sentencia
            rs = st.executeQuery(sqlc);//resultado de la consulta
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(PGConexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean accion(String sqla) {

        try {

            st = con.createStatement();//crear sentencia
            boolean rb = st.execute(sqla); //metodo devuelve un booleano de acuerdo a la ejecucion de la consulta
            st.close(); //cerramos la conexion
            try {
                super.finalize();//ayuda a gestionar los recursos de la base de datos
            } catch (Throwable ex) {
                Logger.getLogger(PGConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;//rb;
            
        } catch (SQLException ex) {
            Logger.getLogger(PGConexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    

}
