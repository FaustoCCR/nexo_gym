package controlador;


import modelo.dao.EmpleadoDao;
import modelo.dao.PersonaDao;
import modelo.dao.RutinaDao;
import modelo.dao.UsuarioDao;
import vista.VistaLogin;
import vista.VistaRegistrar_Empleado;
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
        
//        EmpleadoDao mode =new EmpleadoDao();
//        VistaRegistrar_Empleado vis = new VistaRegistrar_Empleado();
//        ControlRegistrar_Empleado cont =new ControlRegistrar_Empleado(mode, vis);
//        cont.funcionalidad();

    }

}
