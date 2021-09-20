package controlador;

import modelo.dao.UsuarioDao;
import vista.VistaAdministrador;
import vista.VistaLogin;
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

    }

    private void ventanaLogin() {

        UsuarioDao user = new UsuarioDao();
        VistaLogin vl = new VistaLogin();

        ControlLogin login = new ControlLogin(user, vl);
        vista.dispose();
        login.funcionalidad();
    }

    private void ventanaRegistrar_Usuario() {

        UsuarioDao user = new UsuarioDao();
        VistaRegistrar_Usuario v_rguser = new VistaRegistrar_Usuario();
        ControlRegistrar_Usuario controlrg_user = new ControlRegistrar_Usuario(user, v_rguser);
        controlrg_user.funcionalidad();
    }

}
