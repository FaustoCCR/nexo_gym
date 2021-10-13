package controlador;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
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
import modelo.dao.ProgramaClienteDao;
import vista.Fondo;
import vista.VistaAdministrador;
import vista.VistaGestionVentas;
import vista.VistaGestion_Cargo;
import vista.VistaGestion_Clientes;
import vista.VistaGestion_CtgProducto;
import vista.VistaGestion_Empleado;
import vista.VistaGestion_Membresias;
import vista.VistaGestion_Persona;
import vista.VistaGestion_Productos;
import vista.VistaGestion_Proveedor;
import vista.VistaGestion_Rutina;
import vista.VistaGestion_Users;
import vista.VistaLogin;
import vista.VistaPlantilla_Instructor;
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

    private VistaAdministrador vp;
    private int id_user;

    private UsuarioDao modelo_user = new UsuarioDao();

    public ControlVista_Admin(VistaAdministrador vp) {
        this.vp = vp;

        //--colacion del Icono
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("vista/img/logo83.png"));
        vp.setIconImage(ico);
        //--fondo
        VistaAdministrador.getjDesktopPanePrincipal().setBorder(new Fondo());

        id_user = ControlLogin.id_user;
        vp.setVisible(true);
        vp.setTitle("Sesión de " + userName() + " - Nexo Gym");
        vp.setResizable(false);
        vp.setLocationRelativeTo(null);
        vp.setExtendedState(Frame.MAXIMIZED_BOTH);//acopla el frame a toda la pantalla
        identificarRol();

    }

    private String userName() {

        return modelo_user.mostrarDatos().stream()
                .filter(u -> u.getId_user() == id_user).
                findAny().get().getUser_name();

    }

    public void funcionalidad() {

        vp.getJmi_salir().addActionListener(l -> ventanaLogin());
        vp.getJmi_registraruser().addActionListener(l -> ventanaRegistrar_Usuario());
        vp.getJmi_rgpersona().addActionListener(l -> ventanaRegistrar_Persona());
        vp.getJmi_registrarcargo().addActionListener(l -> ventanaRegistrar_Cargo());
        vp.getJmi_rgmembresia().addActionListener(l -> ventanaRegistrar_Membresia());
        vp.getJmi_rgrutina().addActionListener(l -> ventanaRegistrar_Rutina());
        vp.getJmi_rgempleado().addActionListener(l -> ventanaRegistrar_Empleado());
        vp.getJmi_rgcliente().addActionListener(l -> ventanaRegistrar_Cliente());
        vp.getJmi_gclientes().addActionListener(l -> ventanaGestion_Clientes());
        vp.getJmi_rgctgproducto().addActionListener(l -> ventanaRg_CtgProducto());
        vp.getJmi_rgproveedor().addActionListener(l -> ventanaRg_Proveedor());
        vp.getJmi_rgproducto().addActionListener(l -> ventanaRg_Producto());
        vp.getJmi_gproductos().addActionListener(l -> ventanaGestion_Productos());
        vp.getJmi_gusers().addActionListener(l -> ventanaGestion_Users());
        vp.getJmi_gpersonas().addActionListener(l -> ventanaGestion_Personas());
        vp.getJmi_grutinas().addActionListener(l -> ventanaGestion_Rutinas());
        vp.getJmi_realizarventa().addActionListener(l -> ventanaRealizarVenta());
        vp.getJmi_gempleados().addActionListener(l -> ventanaGestion_Empleados());
        vp.getJmi_gmembresias().addActionListener(l -> ventanaGestion_Membresias());
        vp.getJmi_gcargos().addActionListener(l -> ventanaGestion_Cargos());
        vp.getJmi_gcategorias().addActionListener(l -> ventanaGestion_Categorias());
        vp.getJmi_gproveedores().addActionListener(l -> ventanaGestion_Proveedores());
        vp.getJmi_plantillaEntrenamiento().addActionListener(l -> ventanaPlantilla_Instructor());
        vp.getJmi_reporteventas().addActionListener(l -> ventanaGestion_Ventas());

        /*Funciones para los botones del JToolBar */
        vp.getBt_rpersona().addActionListener(l -> ventanaRegistrar_Persona());
        vp.getBt_rcliente().addActionListener(l -> ventanaRegistrar_Cliente());
        vp.getBt_rproveedor().addActionListener(l -> ventanaRg_Proveedor());
        vp.getBt_rproducto().addActionListener(l -> ventanaRg_Producto());
        vp.getBt_rventa().addActionListener(l -> ventanaRealizarVenta());
        vp.getBt_plantilla().addActionListener(l -> ventanaPlantilla_Instructor());

    }

    private void identificarRol() {

        switch (ControlLogin.permiso) {

            case 1:

                vp.getJmi_plantillaEntrenamiento().setVisible(false);
                vp.getBt_plantilla().setVisible(false);
                break;

            case 2:

                vp.getJmi_registraruser().setVisible(false);
                vp.getJmi_registrarcargo().setVisible(false);
                vp.getJmi_rgempleado().setVisible(false);
                vp.getJmi_gusers().setVisible(false);
                vp.getJmi_gcargos().setVisible(false);
                vp.getJmi_gempleados().setVisible(false);
                vp.getJmi_rgproducto().setVisible(false);
                vp.getJmi_rgctgproducto().setVisible(false);
                vp.getJmi_rgproveedor().setVisible(false);
                vp.getJmi_rgmembresia().setVisible(false);
                vp.getJmi_rgrutina().setVisible(false);
                vp.getJmi_plantillaEntrenamiento().setVisible(false);
                vp.getBt_plantilla().setVisible(false);
                vp.getJmi_reporteventas().setVisible(false);

                /* */
                vp.getSp2().setVisible(false);
                vp.getBt_rproveedor().setVisible(false);
                vp.getSp3().setVisible(false);
                vp.getBt_rproducto().setVisible(false);

                break;
            case 3:

                vp.getJm_personas().setText("Clientes");
                vp.getJmi_rgpersona().setVisible(false);
                vp.getJmi_gpersonas().setVisible(false);
                vp.getJmi_registraruser().setVisible(false);
                vp.getJmi_registrarcargo().setVisible(false);
                vp.getJmi_rgcliente().setVisible(false);
                vp.getJmi_rgempleado().setVisible(false);
                vp.getJmi_gusers().setVisible(false);
                vp.getJmi_gcargos().setVisible(false);
                vp.getJmi_gempleados().setVisible(false);
                vp.getJmenu_productos().setVisible(false);
                vp.getJmenuMembresias().setText("Rutinas");
                vp.getJmi_rgmembresia().setVisible(false);
                vp.getJmi_gmembresias().setVisible(false);
                vp.getJmenuVentas().setVisible(false);
                ventanaPlantilla_Instructor();

                /* */
                vp.getSp0().setVisible(false);
                vp.getBt_rpersona().setVisible(false);
                vp.getSp1().setVisible(false);
                vp.getBt_rcliente().setVisible(false);
                vp.getSp2().setVisible(false);
                vp.getBt_rproveedor().setVisible(false);
                vp.getSp3().setVisible(false);
                vp.getBt_rproducto().setVisible(false);
                vp.getSp4().setVisible(false);
                vp.getBt_rventa().setVisible(false);

                break;

        }
    }

    private void ventanaLogin() {

        UsuarioDao user = new UsuarioDao();
        VistaLogin vl = new VistaLogin();

        ControlLogin login = new ControlLogin(user, vl);
        vp.dispose();
        login.funcionalidad();
    }

    private void ventanaRegistrar_Persona() {
        PersonaDao modelo = new PersonaDao();
        VistaRegistrar_Persona vista = new VistaRegistrar_Persona();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRegistrar_Persona control = new ControlRegistrar_Persona(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRegistrar_Usuario() {

        UsuarioDao modelo_rguser = new UsuarioDao();
        VistaRegistrar_Usuario v_rguser = new VistaRegistrar_Usuario();
        vp.getjDesktopPanePrincipal().add(v_rguser);
        ControlRegistrar_Usuario controlrg_user = new ControlRegistrar_Usuario(modelo_rguser, v_rguser);
        controlrg_user.funcionalidad();
    }

    private void ventanaRegistrar_Cargo() {

        CargoDao modelo_rgcargo = new CargoDao();
        VistaRegistrarCargo v_rgcargo = new VistaRegistrarCargo();
        vp.getjDesktopPanePrincipal().add(v_rgcargo);
        ControlRegistrar_Cargo control_rgcargo = new ControlRegistrar_Cargo(modelo_rgcargo, v_rgcargo);
        control_rgcargo.funcionalidad();

    }

    private void ventanaRegistrar_Membresia() {

        MembresiaDao m_rgmembresia = new MembresiaDao();
        VistaRegistrar_Membresia v_rgmembresia = new VistaRegistrar_Membresia();
        vp.getjDesktopPanePrincipal().add(v_rgmembresia);
        ControlRegistrar_Membresia c_rgmembresia = new ControlRegistrar_Membresia(m_rgmembresia, v_rgmembresia);
        c_rgmembresia.funcionalidad();
    }

    private void ventanaRegistrar_Rutina() {
        RutinaDao modelo = new RutinaDao();
        VistaRegistrar_Rutina vista = new VistaRegistrar_Rutina();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRegistrar_Rutina control = new ControlRegistrar_Rutina(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRegistrar_Empleado() {
        EmpleadoDao modelo = new EmpleadoDao();
        VistaRegistrar_Empleado vista = new VistaRegistrar_Empleado();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRegistrar_Empleado control = new ControlRegistrar_Empleado(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRegistrar_Cliente() {

        ClienteDao m_rgcliente = new ClienteDao();
        VistaRegistrar_Cliente v_rgcliente = new VistaRegistrar_Cliente();
        vp.getjDesktopPanePrincipal().add(v_rgcliente);
        ControlRegistrar_Cliente c_rgcliente = new ControlRegistrar_Cliente(m_rgcliente, v_rgcliente);
        c_rgcliente.funcionalidad();
    }

    private void ventanaGestion_Clientes() {

        ClienteDao modelo = new ClienteDao();
        VistaGestion_Clientes vista = new VistaGestion_Clientes();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Clientes control = new ControlGestion_Clientes(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRg_CtgProducto() {

        Ctg_ProductoDao modelo = new Ctg_ProductoDao();
        VistaRegistrar_CtgProducto vista = new VistaRegistrar_CtgProducto();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRegistrar_CtgProducto control = new ControlRegistrar_CtgProducto(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRg_Proveedor() {

        ProveedorDao modelo = new ProveedorDao();
        VistaRegistrar_Proveedor vista = new VistaRegistrar_Proveedor();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRegistrar_Proveedor control = new ControlRegistrar_Proveedor(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRg_Producto() {
        ProductoDao modelo = new ProductoDao();
        VistaRegistrar_Producto vista = new VistaRegistrar_Producto();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRegistrar_Producto control = new ControlRegistrar_Producto(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Productos() {

        ProductoDao modelo = new ProductoDao();
        VistaGestion_Productos vista = new VistaGestion_Productos();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Productos control = new ControlGestion_Productos(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Personas() {

        PersonaDao modelo = new PersonaDao();
        VistaGestion_Persona vista = new VistaGestion_Persona();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Persona control = new ControlGestion_Persona(modelo, vista);
        control.funcionalidad();

    }

    private void ventanaGestion_Cargos() {

        CargoDao modelo = new CargoDao();
        VistaGestion_Cargo vista = new VistaGestion_Cargo();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Cargo control = new ControlGestion_Cargo(modelo, vista);
        control.funcionalidad();

    }

    private void ventanaGestion_Empleados() {

        EmpleadoDao modelo = new EmpleadoDao();
        VistaGestion_Empleado vista = new VistaGestion_Empleado();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Empleados control = new ControlGestion_Empleados(modelo, vista);
        control.funcionalidad();

    }

    private void ventanaGestion_Membresias() {

        MembresiaDao modelo = new MembresiaDao();
        VistaGestion_Membresias vista = new VistaGestion_Membresias();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Membresia control = new ControlGestion_Membresia(modelo, vista);
        control.funcionalidad();

    }

    private void ventanaGestion_Rutinas() {

        RutinaDao modelo = new RutinaDao();
        VistaGestion_Rutina vista = new VistaGestion_Rutina();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Rutina control = new ControlGestion_Rutina(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Users() {
        UsuarioDao modelo = new UsuarioDao();
        VistaGestion_Users vista = new VistaGestion_Users();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Users control = new ControlGestion_Users(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaRealizarVenta() {

        Ecb_VentaDao modelo = new Ecb_VentaDao();
        Cuerpo_VentaDao modelo2 = new Cuerpo_VentaDao();
        VistaRealizar_Venta vista = new VistaRealizar_Venta();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlRealizar_Venta control = new ControlRealizar_Venta(modelo, modelo2, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Categorias() {

        Ctg_ProductoDao modelo = new Ctg_ProductoDao();
        VistaGestion_CtgProducto vista = new VistaGestion_CtgProducto();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_CtgProducto control = new ControlGestion_CtgProducto(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Proveedores() {

        ProveedorDao proveedor = new ProveedorDao();
        VistaGestion_Proveedor vista = new VistaGestion_Proveedor();
        vp.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Proveedor control = new ControlGestion_Proveedor(proveedor, vista);
        control.funcionalidad();

    }

    private void ventanaPlantilla_Instructor() {
        ProgramaClienteDao modelo = new ProgramaClienteDao();
        VistaPlantilla_Instructor vista = new VistaPlantilla_Instructor();
        VistaAdministrador.getjDesktopPanePrincipal().add(vista);
        ControlPlantilla_Instructor control = new ControlPlantilla_Instructor(modelo, vista);
        control.funcionalidad();
    }

    private void ventanaGestion_Ventas() {
        Ecb_VentaDao modelo1 = new Ecb_VentaDao();
        Cuerpo_VentaDao modelo2 = new Cuerpo_VentaDao();
        VistaGestionVentas vista = new VistaGestionVentas();
        VistaAdministrador.getjDesktopPanePrincipal().add(vista);
        ControlGestion_Ventas control = new ControlGestion_Ventas(modelo1, modelo2, vista);
        control.funcionalidad();
    }
}
