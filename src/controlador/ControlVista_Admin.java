package controlador;

import modelo.dao.CargoDao;
import modelo.dao.ClienteDao;
import modelo.dao.MembresiaDao;
import modelo.dao.UsuarioDao;
import vista.VistaAdministrador;
import vista.VistaGestion_Clientes;
import vista.VistaLogin;
import vista.VistaRegistrarCargo;
import vista.VistaRegistrar_Cliente;
import vista.VistaRegistrar_Membresia;
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
        vista.getJmi_registrarcargo().addActionListener(l -> ventanaRegistrar_Cargo());
        vista.getJmi_rgmembresia().addActionListener(l->ventanaRegistrar_Membresia());
        vista.getJmi_rgcliente().addActionListener(l->ventanaRegistrar_Cliente());
        vista.getJmi_gclientes().addActionListener(l->ventanaGestion_Clientes());
        
        

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

    private void ventanaRegistrar_Cargo() {

        CargoDao modelo_rgcargo = new CargoDao();
        VistaRegistrarCargo v_rgcargo = new VistaRegistrarCargo();
        ControlRegistrar_Cargo control_rgcargo = new ControlRegistrar_Cargo(modelo_rgcargo, v_rgcargo);
        control_rgcargo.funcionalidad();

    }
    
    private void ventanaRegistrar_Membresia(){
        
        MembresiaDao m_rgmembresia = new MembresiaDao();
        VistaRegistrar_Membresia v_rgmembresia = new VistaRegistrar_Membresia();
        
        ControlRegistrar_Membresia c_rgmembresia = new ControlRegistrar_Membresia(m_rgmembresia, v_rgmembresia);
        c_rgmembresia.funcionalidad();
    }
    
    private void ventanaRegistrar_Cliente(){
        
        ClienteDao m_rgcliente = new ClienteDao();
        VistaRegistrar_Cliente v_rgcliente = new VistaRegistrar_Cliente();
        ControlRegistrar_Cliente c_rgcliente = new  ControlRegistrar_Cliente(m_rgcliente, v_rgcliente);
        c_rgcliente.funcionalidad();
    }
    
    private void ventanaGestion_Clientes(){
        
        ClienteDao modelo = new ClienteDao();
        VistaGestion_Clientes vista = new VistaGestion_Clientes();
        
        ControlGestion_Clientes control = new ControlGestion_Clientes(modelo, vista);
        control.funcionalidad();
    }

}
