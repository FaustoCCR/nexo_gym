package controlador;

import modelo.dao.CargoDao;
import modelo.dao.ClienteDao;
import modelo.dao.Ctg_ProductoDao;
import modelo.dao.Cuerpo_VentaDao;
import modelo.dao.EmpleadoDao;
import modelo.dao.MembresiaDao;
import modelo.dao.PersonaDao;
import modelo.dao.ProductoDao;
import modelo.dao.ProveedorDao;
import modelo.dao.RutinaDao;
import modelo.dao.UsuarioDao;
import modelo.dao.Ecb_VentaDao;
import vista.VistaAdministrador;
import vista.VistaGestion_Clientes;
import vista.VistaGestion_Persona;
import vista.VistaGestion_Productos;
import vista.VistaGestion_Rutina;
import vista.VistaGestion_Users;
import vista.VistaLogin;
import vista.VistaRealizar_Venta;
import vista.VistaRegistrarCargo;
import vista.VistaRegistrar_Cliente;
import vista.VistaRegistrar_CtgProducto;
import vista.VistaRegistrar_Empleado;
import vista.VistaRegistrar_Membresia;
import vista.VistaRegistrar_Persona;
import vista.VistaRegistrar_Producto;
import vista.VistaRegistrar_Proveedor;
import vista.VistaRegistrar_Rutina;
import vista.VistaRegistrar_Usuario;

public class ControlVista_Admin {

    private VistaAdministrador vista;
    private int id_user;
    private String usuario;

    private UsuarioDao modelo_user = new UsuarioDao();

    public ControlVista_Admin(VistaAdministrador vista) {
        this.vista = vista;

        id_user = ControlLogin.id_user;
        vista.setVisible(true);
        vista.setTitle("Sesión de " + userName() + " - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);

    }

    private String userName() {

        return modelo_user.mostrarDatos().stream()
                .filter(u -> u.getId_user() == id_user).
                findAny().get().getUser_name();

    }

    public void funcionalidad() {

        vista.getJmi_salir().addActionListener(l -> ventanaLogin());
        vista.getJmi_registraruser().addActionListener(l -> ventanaRegistrar_Usuario());
        vista.getJmi_rgpersona().addActionListener(l -> ventanaRegistrar_Persona());
        vista.getJmi_registrarcargo().addActionListener(l -> ventanaRegistrar_Cargo());
        vista.getJmi_rgmembresia().addActionListener(l -> ventanaRegistrar_Membresia());
        vista.getJmi_rgrutina().addActionListener(l -> ventanaRegistrar_Rutina());
        vista.getJmi_rgempleado().addActionListener(l -> ventanaRegistrar_Empleado());
        vista.getJmi_rgcliente().addActionListener(l -> ventanaRegistrar_Cliente());
        vista.getJmi_gclientes().addActionListener(l -> ventanaGestion_Clientes());
        vista.getJmi_rgctgproducto().addActionListener(l -> ventanaRg_CtgProducto());
        vista.getJmi_rgproveedor().addActionListener(l -> ventanaRg_Proveedor());
        vista.getJmi_rgproducto().addActionListener(l -> ventanaRg_Producto());
        vista.getJmi_gproductos().addActionListener(l -> ventanaGestion_Productos());
        vista.getJmi_gusers().addActionListener(l -> ventanaGestion_Users());
        vista.getJmi_gpersonas().addActionListener(l -> ventanaGestion_Personas());
        vista.getJmi_grutinas().addActionListener(l -> ventanaGestion_Rutinas());
        vista.getJmi_realizarventa().addActionListener(l -> ventanaRealizarVenta());

    }

    private void ventanaLogin() {

        UsuarioDao user = new UsuarioDao();
        VistaLogin vl = new VistaLogin();

        ControlLogin login = new ControlLogin(user, vl);
        vista.dispose();
        login.funcionalidad();
    }

    private void ventanaRegistrar_Persona() {
        PersonaDao modelo = new PersonaDao();
        VistaRegistrar_Persona vista = new VistaRegistrar_Persona();
        ControlRegistrar_Persona control = new ControlRegistrar_Persona(modelo, vista);
        control.funcionalidad();
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

    private void ventanaRegistrar_Rutina() {
        RutinaDao modelo = new RutinaDao();
        VistaRegistrar_Rutina vista = new VistaRegistrar_Rutina();
        ControlRegistrar_Rutina control = new ControlRegistrar_Rutina(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRegistrar_Empleado() {
        EmpleadoDao modelo = new EmpleadoDao();
        VistaRegistrar_Empleado vista = new VistaRegistrar_Empleado();
        ControlRegistrar_Empleado control = new ControlRegistrar_Empleado(modelo, vista);
        control.funcionalidad();
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

    private void ventanaGestion_Productos() {

        ProductoDao modelo = new ProductoDao();
        VistaGestion_Productos vista = new VistaGestion_Productos();
        ControlGestion_Productos control = new ControlGestion_Productos(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Personas() {

        PersonaDao modelo = new PersonaDao();
        VistaGestion_Persona vista = new VistaGestion_Persona();
        ControlGestion_Persona control = new ControlGestion_Persona(modelo, vista);
        control.funcionalidad();

    }

    private void ventanaGestion_Cargos() {

    }

    private void ventanaGestion_Empleados() {

    }

    private void ventanaGestion_Membresias() {

    }

    private void ventanaGestion_Rutinas() {

        RutinaDao modelo = new RutinaDao();
        VistaGestion_Rutina vista = new VistaGestion_Rutina();
        ControlGestion_Rutina control = new ControlGestion_Rutina(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Users() {
        UsuarioDao modelo = new UsuarioDao();
        VistaGestion_Users vista = new VistaGestion_Users();
        ControlGestion_Users control = new ControlGestion_Users(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRealizarVenta() {

        Ecb_VentaDao modelo = new Ecb_VentaDao();
        Cuerpo_VentaDao modelo2 = new Cuerpo_VentaDao();
        VistaRealizar_Venta vista = new VistaRealizar_Venta();
        ControlRealizar_Venta control = new ControlRealizar_Venta(modelo, modelo2, vista);
        control.funcionalidad();
    }

}
