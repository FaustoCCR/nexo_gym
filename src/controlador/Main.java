package controlador;

import modelo.dao.CargoDao;
import modelo.dao.Ctg_ProductoDao;
import modelo.dao.UsuarioDao;
import vista.VistaGestion_Cargo;
import vista.VistaGestion_CtgProducto;
import vista.VistaLogin;

public class Main {

    public static void main(String[] args) {

//        UsuarioDao user = new UsuarioDao();
//        VistaLogin vl = new VistaLogin();
//
//        ControlLogin login = new ControlLogin(user, vl);
//        login.funcionalidad();
        
        CargoDao mode = new CargoDao();
        VistaGestion_Cargo vista = new VistaGestion_Cargo();
        ControlGestion_Cargo control = new ControlGestion_Cargo(mode, vista);
        control.funcionalidad();
        
        
        
    }

}
