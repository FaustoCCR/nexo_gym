
package modelo.vo;


public class UsuarioVo {
    
    //atributos
    private int id_user;
    private int id_persona; // ----> FK
    private int id_rol; // ----> FK
    private String user_name;
    private String password;
    private boolean estado_cuenta;

    public UsuarioVo(int id_user, int id_persona, int id_rol, String user_name, String password, boolean estado_cuenta) {
        this.id_user = id_user;
        this.id_persona = id_persona;
        this.id_rol = id_rol;
        this.user_name = user_name;
        this.password = password;
        this.estado_cuenta = estado_cuenta;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(boolean estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }
    
    
    
}
