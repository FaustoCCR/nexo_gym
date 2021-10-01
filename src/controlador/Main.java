package controlador;


import modelo.dao.PersonaDao;
import modelo.dao.UsuarioDao;
import vista.VistaGestion_Persona;
import vista.VistaLogin;

public class Main {

    public static void main(String[] args) {

//        UsuarioDao user = new UsuarioDao();
//        VistaLogin vl = new VistaLogin();
//
//        ControlLogin login = new ControlLogin(user, vl);
//        login.funcionalidad();

            PersonaDao mode = new PersonaDao();
            VistaGestion_Persona vista = new VistaGestion_Persona();
            ControlGestion_Persona control = new ControlGestion_Persona(mode, vista);


    }

}
