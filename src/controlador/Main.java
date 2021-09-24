package controlador;


import modelo.dao.PersonaDao;
import modelo.dao.RutinaDao;
import modelo.dao.UsuarioDao;
import vista.VistaLogin;
import vista.VistaRegistrar_Persona;
import vista.VistaRegistrar_Rutina;

public class Main {

    public static void main(String[] args) {
//
//        UsuarioDao user = new UsuarioDao();
//        VistaLogin vl = new VistaLogin();
//
//        ControlLogin login = new ControlLogin(user, vl);
//        login.funcionalidad();
        PersonaDao mode =new PersonaDao();
        VistaRegistrar_Persona vis = new VistaRegistrar_Persona();
        ControlRegistrar_Persona cont =new ControlRegistrar_Persona(mode, vis);
        cont.funcionalidad();

    }

}
