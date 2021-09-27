package controlador;

import modelo.dao.CargoDao;
import modelo.dao.ClienteDao;
import modelo.dao.Ctg_ProductoDao;
import modelo.dao.MembresiaDao;
import modelo.dao.ProductoDao;
import modelo.dao.ProveedorDao;
import modelo.dao.UsuarioDao;
import vista.VistaAdministrador;
import vista.VistaGestion_Clientes;
import vista.VistaLogin;
import vista.VistaRegistrarCargo;
import vista.VistaRegistrar_Cliente;
import vista.VistaRegistrar_CtgProducto;
import vista.VistaRegistrar_Membresia;
import vista.VistaRegistrar_Producto;
import vista.VistaRegistrar_Proveedor;
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
        vista.getJmi_rgmembresia().addActionListener(l -> ventanaRegistrar_Membresia());
        vista.getJmi_rgcliente().addActionListener(l -> ventanaRegistrar_Cliente());
        vista.getJmi_gclientes().addActionListener(l -> ventanaGestion_Clientes());
        vista.getJmi_rgctgproducto().addActionListener(l -> ventanaRg_CtgProducto());
        vista.getJmi_rgproveedor().addActionListener(l -> ventanaRg_Proveedor());
        vista.getJmi_rgproducto().addActionListener(l -> ventanaRg_Producto());

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

    private void ventanaRegistrar_Membresia() {

        MembresiaDao m_rgmembresia = new MembresiaDao();
        VistaRegistrar_Membresia v_rgmembresia = new VistaRegistrar_Membresia();

        ControlRegistrar_Membresia c_rgmembresia = new ControlRegistrar_Membresia(m_rgmembresia, v_rgmembresia);
        c_rgmembresia.funcionalidad();
    }

    private void ventanaRegistrar_Cliente() {

        ClienteDao m_rgcliente = new ClienteDao();
        VistaRegistrar_Cliente v_rgcliente = new VistaRegistrar_Cliente();
        ControlRegistrar_Cliente c_rgcliente = new ControlRegistrar_Cliente(m_rgcliente, v_rgcliente);
        c_rgcliente.funcionalidad();
    }

    private void ventanaGestion_Clientes() {

        ClienteDao modelo = new ClienteDao();
        VistaGestion_Clientes vista = new VistaGestion_Clientes();

        ControlGestion_Clientes control = new ControlGestion_Clientes(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRg_CtgProducto() {

        Ctg_ProductoDao modelo = new Ctg_ProductoDao();
        VistaRegistrar_CtgProducto vista = new VistaRegistrar_CtgProducto();
        ControlRegistrar_CtgProducto control = new ControlRegistrar_CtgProducto(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRg_Proveedor() {

        ProveedorDao modelo = new ProveedorDao();
        VistaRegistrar_Proveedor vista = new VistaRegistrar_Proveedor();
        ControlRegistrar_Proveedor control = new ControlRegistrar_Proveedor(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRg_Producto() {
        ProductoDao modelo = new ProductoDao();
        VistaRegistrar_Producto vista = new VistaRegistrar_Producto();
        ControlRegistrar_Producto control = new ControlRegistrar_Producto(modelo, vista);
        control.funcionalidad();
    }

}
