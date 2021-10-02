package controlador;

import modelo.dao.EmpleadoDao;
import modelo.dao.UsuarioDao;
import vista.VistaGestion_Empleado;
import vista.VistaLogin;

public class Main {

    public static void main(String[] args) {

//        UsuarioDao user = new UsuarioDao();
//        VistaLogin vl = new VistaLogin();
//
//        ControlLogin login = new ControlLogin(user, vl);
//        login.funcionalidad();
        
        EmpleadoDao mode = new EmpleadoDao();
        VistaGestion_Empleado vista = new VistaGestion_Empleado();
        ControlGestion_Empleados control = new ControlGestion_Empleados(mode, vista);

    }

}
