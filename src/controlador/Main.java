package controlador;


import modelo.dao.RutinaDao;
import modelo.dao.UsuarioDao;
import vista.VistaLogin;
import vista.VistaRegistrar_Rutina;

public class Main {

    public static void main(String[] args) {
//
//        UsuarioDao user = new UsuarioDao();
//        VistaLogin vl = new VistaLogin();
//
//        ControlLogin login = new ControlLogin(user, vl);
//        login.funcionalidad();
        
        RutinaDao mode =new RutinaDao();
        VistaRegistrar_Rutina vis = new VistaRegistrar_Rutina();
        ControlRegistrar_Rutina cont =new ControlRegistrar_Rutina(mode, vis);
        cont.funcionalidad();

    }

}
