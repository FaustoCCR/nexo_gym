package controlador;

import modelo.dao.Ctg_ProductoDao;
import modelo.dao.UsuarioDao;
import vista.VistaGestion_CtgProducto;
import vista.VistaLogin;

public class Main {

    public static void main(String[] args) {

//        UsuarioDao user = new UsuarioDao();
//        VistaLogin vl = new VistaLogin();
//
//        ControlLogin login = new ControlLogin(user, vl);
//        login.funcionalidad();
        
        Ctg_ProductoDao mode = new Ctg_ProductoDao();
        VistaGestion_CtgProducto vista = new VistaGestion_CtgProducto();
        ControlGestion_CtgProducto control = new ControlGestion_CtgProducto(mode, vista);
        control.funcionalidad();
        
    }

}
