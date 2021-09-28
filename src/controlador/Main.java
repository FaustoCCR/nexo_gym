package controlador;


import modelo.dao.UsuarioDao;
import vista.VistaLogin;

public class Main {

    public static void main(String[] args) {

        UsuarioDao user = new UsuarioDao();
        VistaLogin vl = new VistaLogin();

        ControlLogin login = new ControlLogin(user, vl);
        login.funcionalidad();


    }

}
