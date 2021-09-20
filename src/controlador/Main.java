package controlador;

import modelo.dao.PersonaDao;
import modelo.dao.UsuarioDao;
import vista.VistaLogin;
import vista.VistaRegistrar_Usuario;

public class Main {

    public static void main(String[] args) {
        
        UsuarioDao user = new UsuarioDao();
        VistaLogin vl = new VistaLogin();

        ControlLogin login = new ControlLogin(user, vl);
        login.funcionalidad();

    }

}
