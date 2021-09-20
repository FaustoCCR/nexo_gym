package controlador;

import modelo.dao.CargoDao;
import modelo.dao.UsuarioDao;
import vista.VistaAdministrador;
import vista.VistaLogin;
import vista.VistaRegistrarCargo;
import vista.VistaRegistrar_Usuario;

public class ControlVista_Admin {

    private VistaAdministrador vista;

    public ControlVista_Admin(VistaAdministrador vista) {
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Administrador - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        

    }

    public void funcionalidad() {

        vista.getJmi_salir().addActionListener(l -> ventanaLogin());
        vista.getJmi_registraruser().addActionListener(l -> ventanaRegistrar_Usuario());
        vista.getJmi_registrarcargo().addActionListener(l->ventanaRegistrar_Cargo());

    }

    private void ventanaLogin() {

        UsuarioDao user = new UsuarioDao();
        VistaLogin vl = new VistaLogin();

        ControlLogin login = new ControlLogin(user, vl);
        vista.dispose();
        login.funcionalidad();
    }

    private void ventanaRegistrar_Usuario() {

        UsuarioDao modelo_rguser = new UsuarioDao();
        VistaRegistrar_Usuario v_rguser = new VistaRegistrar_Usuario();
        ControlRegistrar_Usuario controlrg_user = new ControlRegistrar_Usuario(modelo_rguser, v_rguser);
        controlrg_user.funcionalidad();
    }
    private void ventanaRegistrar_Cargo(){
        
        CargoDao modelo_rgcargo = new CargoDao();
        VistaRegistrarCargo v_rgcargo = new VistaRegistrarCargo();
        ControlRegistrar_Cargo control_rgcargo = new ControlRegistrar_Cargo(modelo_rgcargo, v_rgcargo);
        control_rgcargo.funcionalidad();

    }

}
