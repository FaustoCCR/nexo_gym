package vista;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class VistaAdministrador extends javax.swing.JFrame {

    public VistaAdministrador() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        sp0 = new javax.swing.JToolBar.Separator();
        bt_rpersona = new javax.swing.JButton();
        sp1 = new javax.swing.JToolBar.Separator();
        bt_rcliente = new javax.swing.JButton();
        sp2 = new javax.swing.JToolBar.Separator();
        bt_rproveedor = new javax.swing.JButton();
        sp3 = new javax.swing.JToolBar.Separator();
        bt_rproducto = new javax.swing.JButton();
        sp4 = new javax.swing.JToolBar.Separator();
        bt_rventa = new javax.swing.JButton();
        sp5 = new javax.swing.JToolBar.Separator();
        bt_plantilla = new javax.swing.JButton();
        jlfooter = new javax.swing.JLabel();
        jDesktopPanePrincipal = new javax.swing.JDesktopPane();
        jlb_logo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmi_salir = new javax.swing.JMenuItem();
        jm_personas = new javax.swing.JMenu();
        jmi_rgpersona = new javax.swing.JMenuItem();
        jmi_registraruser = new javax.swing.JMenuItem();
        jmi_registrarcargo = new javax.swing.JMenuItem();
        jmi_rgcliente = new javax.swing.JMenuItem();
        jmi_rgempleado = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jmi_gpersonas = new javax.swing.JMenuItem();
        jmi_gusers = new javax.swing.JMenuItem();
        jmi_gcargos = new javax.swing.JMenuItem();
        jmi_gclientes = new javax.swing.JMenuItem();
        jmi_gempleados = new javax.swing.JMenuItem();
        jmenu_productos = new javax.swing.JMenu();
        jmi_rgproducto = new javax.swing.JMenuItem();
        jmi_rgctgproducto = new javax.swing.JMenuItem();
        jmi_rgproveedor = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmi_gproductos = new javax.swing.JMenuItem();
        jmi_gcategorias = new javax.swing.JMenuItem();
        jmi_gproveedores = new javax.swing.JMenuItem();
        jmenuMembresias = new javax.swing.JMenu();
        jmi_rgmembresia = new javax.swing.JMenuItem();
        jmi_rgrutina = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmi_gmembresias = new javax.swing.JMenuItem();
        jmi_grutinas = new javax.swing.JMenuItem();
        jmi_plantillaEntrenamiento = new javax.swing.JMenuItem();
        jmenuVentas = new javax.swing.JMenu();
        jmi_realizarventa = new javax.swing.JMenuItem();
        jmi_reporteventas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(21, 45, 59));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(sp0);

        bt_rpersona.setBackground(new java.awt.Color(215, 233, 244));
        bt_rpersona.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bt_rpersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/contacto.png"))); // NOI18N
        bt_rpersona.setFocusable(false);
        bt_rpersona.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_rpersona.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(bt_rpersona);
        jToolBar1.add(sp1);

        bt_rcliente.setBackground(new java.awt.Color(215, 233, 244));
        bt_rcliente.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bt_rcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/cliente.png"))); // NOI18N
        bt_rcliente.setFocusable(false);
        bt_rcliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_rcliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(bt_rcliente);
        jToolBar1.add(sp2);

        bt_rproveedor.setBackground(new java.awt.Color(215, 233, 244));
        bt_rproveedor.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bt_rproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/proveedor.png"))); // NOI18N
        bt_rproveedor.setFocusable(false);
        bt_rproveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_rproveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(bt_rproveedor);
        jToolBar1.add(sp3);

        bt_rproducto.setBackground(new java.awt.Color(215, 233, 244));
        bt_rproducto.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bt_rproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/producto.png"))); // NOI18N
        bt_rproducto.setFocusable(false);
        bt_rproducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_rproducto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(bt_rproducto);
        jToolBar1.add(sp4);

        bt_rventa.setBackground(new java.awt.Color(215, 233, 244));
        bt_rventa.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bt_rventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/vender.png"))); // NOI18N
        bt_rventa.setFocusable(false);
        bt_rventa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_rventa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(bt_rventa);
        jToolBar1.add(sp5);

        bt_plantilla.setBackground(new java.awt.Color(215, 233, 244));
        bt_plantilla.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        bt_plantilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/plantilla.png"))); // NOI18N
        bt_plantilla.setFocusable(false);
        bt_plantilla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_plantilla.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(bt_plantilla);

        jlfooter.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jlfooter.setText("  Sistema Nexo - Gym v1.0.0");
        jlfooter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jlb_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/logo8.png"))); // NOI18N

        jDesktopPanePrincipal.setLayer(jlb_logo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPanePrincipalLayout = new javax.swing.GroupLayout(jDesktopPanePrincipal);
        jDesktopPanePrincipal.setLayout(jDesktopPanePrincipalLayout);
        jDesktopPanePrincipalLayout.setHorizontalGroup(
            jDesktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPanePrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlb_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDesktopPanePrincipalLayout.setVerticalGroup(
            jDesktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPanePrincipalLayout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(jlb_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_salir.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_salir.setText("Salir");
        jMenu1.add(jmi_salir);

        jMenuBar1.add(jMenu1);

        jm_personas.setText("Personas");
        jm_personas.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_rgpersona.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgpersona.setText("Registrar Persona");
        jm_personas.add(jmi_rgpersona);

        jmi_registraruser.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_registraruser.setText("Registrar Usuario");
        jm_personas.add(jmi_registraruser);

        jmi_registrarcargo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_registrarcargo.setText("Registrar Cargo");
        jm_personas.add(jmi_registrarcargo);

        jmi_rgcliente.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgcliente.setText("Registrar Cliente");
        jm_personas.add(jmi_rgcliente);

        jmi_rgempleado.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgempleado.setText("Registrar Empleado");
        jm_personas.add(jmi_rgempleado);
        jm_personas.add(jSeparator3);

        jmi_gpersonas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gpersonas.setText("Mostrar Personas");
        jm_personas.add(jmi_gpersonas);

        jmi_gusers.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gusers.setText("Mostrar Usuarios");
        jm_personas.add(jmi_gusers);

        jmi_gcargos.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gcargos.setText("Mostrar Cargos");
        jm_personas.add(jmi_gcargos);

        jmi_gclientes.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gclientes.setText("Mostrar Clientes");
        jm_personas.add(jmi_gclientes);

        jmi_gempleados.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gempleados.setText("Mostrar Empleados");
        jm_personas.add(jmi_gempleados);

        jMenuBar1.add(jm_personas);

        jmenu_productos.setText("Productos");
        jmenu_productos.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_rgproducto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgproducto.setText("Registrar Producto");
        jmenu_productos.add(jmi_rgproducto);

        jmi_rgctgproducto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgctgproducto.setText("Registrar Categoría");
        jmenu_productos.add(jmi_rgctgproducto);

        jmi_rgproveedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgproveedor.setText("Registrar Proveedor");
        jmenu_productos.add(jmi_rgproveedor);
        jmenu_productos.add(jSeparator1);

        jmi_gproductos.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gproductos.setText("Mostrar Productos");
        jmenu_productos.add(jmi_gproductos);

        jmi_gcategorias.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gcategorias.setText("Mostrar Categorías");
        jmenu_productos.add(jmi_gcategorias);

        jmi_gproveedores.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gproveedores.setText("Mostrar Proveedores");
        jmenu_productos.add(jmi_gproveedores);

        jMenuBar1.add(jmenu_productos);

        jmenuMembresias.setText("Membresías");
        jmenuMembresias.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_rgmembresia.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgmembresia.setText("Registrar Membresia");
        jmenuMembresias.add(jmi_rgmembresia);

        jmi_rgrutina.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgrutina.setText("Registrar Rutina");
        jmenuMembresias.add(jmi_rgrutina);
        jmenuMembresias.add(jSeparator2);

        jmi_gmembresias.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gmembresias.setText("Mostrar Membresías");
        jmenuMembresias.add(jmi_gmembresias);

        jmi_grutinas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_grutinas.setText("Mostrar Rutinas");
        jmenuMembresias.add(jmi_grutinas);

        jmi_plantillaEntrenamiento.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_plantillaEntrenamiento.setText("Mi  Plantilla");
        jmenuMembresias.add(jmi_plantillaEntrenamiento);

        jMenuBar1.add(jmenuMembresias);

        jmenuVentas.setText("Ventas");
        jmenuVentas.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_realizarventa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_realizarventa.setText("Realizar Venta");
        jmenuVentas.add(jmi_realizarventa);

        jmi_reporteventas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_reporteventas.setText("Reporte Ventas");
        jmenuVentas.add(jmi_reporteventas);

        jMenuBar1.add(jmenuVentas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
            .addComponent(jlfooter, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
            .addComponent(jDesktopPanePrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPanePrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlfooter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenuItem getJmi_registraruser() {
        return jmi_registraruser;
    }

    public void setJmi_registraruser(JMenuItem jmi_registraruser) {
        this.jmi_registraruser = jmi_registraruser;
    }

    public JMenuItem getJmi_salir() {
        return jmi_salir;
    }

    public void setJmi_salir(JMenuItem jmi_salir) {
        this.jmi_salir = jmi_salir;
    }

    public JMenuItem getJmi_registrarcargo() {
        return jmi_registrarcargo;
    }

    public void setJmi_registrarcargo(JMenuItem jmi_registrarcargo) {
        this.jmi_registrarcargo = jmi_registrarcargo;
    }

    public JMenuItem getJmi_rgmembresia() {
        return jmi_rgmembresia;
    }

    public void setJmi_rgmembresia(JMenuItem jmi_rgmembresia) {
        this.jmi_rgmembresia = jmi_rgmembresia;
    }

    public JMenuItem getJmi_rgcliente() {
        return jmi_rgcliente;
    }

    public void setJmi_rgcliente(JMenuItem jmi_rgcliente) {
        this.jmi_rgcliente = jmi_rgcliente;
    }

    public JMenuItem getJmi_gclientes() {
        return jmi_gclientes;
    }

    public void setJmi_gclientes(JMenuItem jmi_gclientes) {
        this.jmi_gclientes = jmi_gclientes;
    }

    public JMenuItem getJmi_rgctgproducto() {
        return jmi_rgctgproducto;
    }

    public void setJmi_rgctgproducto(JMenuItem jmi_rgctgproducto) {
        this.jmi_rgctgproducto = jmi_rgctgproducto;
    }

    public JMenuItem getJmi_rgproducto() {
        return jmi_rgproducto;
    }

    public void setJmi_rgproducto(JMenuItem jmi_rgproducto) {
        this.jmi_rgproducto = jmi_rgproducto;
    }

    public JMenuItem getJmi_rgproveedor() {
        return jmi_rgproveedor;
    }

    public void setJmi_rgproveedor(JMenuItem jmi_rgproveedor) {
        this.jmi_rgproveedor = jmi_rgproveedor;
    }

    public JMenuItem getJmi_gproductos() {
        return jmi_gproductos;
    }

    public void setJmi_gproductos(JMenuItem jmi_gproductos) {
        this.jmi_gproductos = jmi_gproductos;
    }

    public JMenuItem getJmi_rgempleado() {
        return jmi_rgempleado;
    }

    public void setJmi_rgempleado(JMenuItem jmi_rgempleado) {
        this.jmi_rgempleado = jmi_rgempleado;
    }

    public JMenuItem getJmi_rgpersona() {
        return jmi_rgpersona;
    }

    public void setJmi_rgpersona(JMenuItem jmi_rgpersona) {
        this.jmi_rgpersona = jmi_rgpersona;
    }

    public JMenuItem getJmi_rgrutina() {
        return jmi_rgrutina;
    }

    public void setJmi_rgrutina(JMenuItem jmi_rgrutina) {
        this.jmi_rgrutina = jmi_rgrutina;
    }

    public JMenuItem getJmi_gusers() {
        return jmi_gusers;
    }

    public void setJmi_gusers(JMenuItem jmi_gusers) {
        this.jmi_gusers = jmi_gusers;
    }

    public JMenu getJm_personas() {
        return jm_personas;
    }

    public void setJm_personas(JMenu jm_personas) {
        this.jm_personas = jm_personas;
    }

    public JMenuItem getJmi_gcargos() {
        return jmi_gcargos;
    }

    public void setJmi_gcargos(JMenuItem jmi_gcargos) {
        this.jmi_gcargos = jmi_gcargos;
    }

    public JMenuItem getJmi_gempleados() {
        return jmi_gempleados;
    }

    public void setJmi_gempleados(JMenuItem jmi_gempleados) {
        this.jmi_gempleados = jmi_gempleados;
    }

    public JMenuItem getJmi_gpersonas() {
        return jmi_gpersonas;
    }

    public void setJmi_gpersonas(JMenuItem jmi_gpersonas) {
        this.jmi_gpersonas = jmi_gpersonas;
    }

    public JMenuItem getJmi_gmembresias() {
        return jmi_gmembresias;
    }

    public void setJmi_gmembresias(JMenuItem jmi_gmembresias) {
        this.jmi_gmembresias = jmi_gmembresias;
    }

    public JMenuItem getJmi_grutinas() {
        return jmi_grutinas;
    }

    public void setJmi_grutinas(JMenuItem jmi_grutinas) {
        this.jmi_grutinas = jmi_grutinas;
    }

    public JMenuItem getJmi_realizarventa() {
        return jmi_realizarventa;
    }

    public void setJmi_realizarventa(JMenuItem jmi_realizarventa) {
        this.jmi_realizarventa = jmi_realizarventa;
    }

    public static JDesktopPane getjDesktopPanePrincipal() {
        return jDesktopPanePrincipal;
    }

    public void setjDesktopPanePrincipal(JDesktopPane jDesktopPanePrincipal) {
        this.jDesktopPanePrincipal = jDesktopPanePrincipal;
    }

    public JLabel getJlfooter() {
        return jlfooter;
    }

    public void setJlfooter(JLabel jlfooter) {
        this.jlfooter = jlfooter;
    }

    public JMenuItem getJmi_gcategorias() {
        return jmi_gcategorias;
    }

    public void setJmi_gcategorias(JMenuItem jmi_gcategorias) {
        this.jmi_gcategorias = jmi_gcategorias;
    }

    public JMenuItem getJmi_gproveedores() {
        return jmi_gproveedores;
    }

    public void setJmi_gproveedores(JMenuItem jmi_gproveedores) {
        this.jmi_gproveedores = jmi_gproveedores;
    }

    public JMenu getJmenu_productos() {
        return jmenu_productos;
    }

    public void setJmenu_productos(JMenu jmenu_productos) {
        this.jmenu_productos = jmenu_productos;
    }

    public JMenu getJmenuVentas() {
        return jmenuVentas;
    }

    public void setJmenuVentas(JMenu jmenuVentas) {
        this.jmenuVentas = jmenuVentas;
    }

    public JMenu getJmenuMembresias() {
        return jmenuMembresias;
    }

    public void setJmenuMembresias(JMenu jmenuMembresias) {
        this.jmenuMembresias = jmenuMembresias;
    }

    public JMenuItem getJmi_plantillaEntrenamiento() {
        return jmi_plantillaEntrenamiento;
    }

    public void setJmi_plantillaEntrenamiento(JMenuItem jmi_plantillaEntrenamiento) {
        this.jmi_plantillaEntrenamiento = jmi_plantillaEntrenamiento;
    }

    public JMenuItem getJmi_reporteventas() {
        return jmi_reporteventas;
    }

    public void setJmi_reporteventas(JMenuItem jmi_reporteventas) {
        this.jmi_reporteventas = jmi_reporteventas;
    }

    public JLabel getJlb_logo() {
        return jlb_logo;
    }

    public void setJlb_logo(JLabel jlb_logo) {
        this.jlb_logo = jlb_logo;
    }

    public JButton getBt_rcliente() {
        return bt_rcliente;
    }

    public void setBt_rcliente(JButton bt_rcliente) {
        this.bt_rcliente = bt_rcliente;
    }

    public JButton getBt_rpersona() {
        return bt_rpersona;
    }

    public void setBt_rpersona(JButton bt_rpersona) {
        this.bt_rpersona = bt_rpersona;
    }

    public JButton getBt_rproducto() {
        return bt_rproducto;
    }

    public void setBt_rproducto(JButton bt_rproducto) {
        this.bt_rproducto = bt_rproducto;
    }

    public JButton getBt_rproveedor() {
        return bt_rproveedor;
    }

    public void setBt_rproveedor(JButton bt_rproveedor) {
        this.bt_rproveedor = bt_rproveedor;
    }

    public JButton getBt_rventa() {
        return bt_rventa;
    }

    public void setBt_rventa(JButton bt_rventa) {
        this.bt_rventa = bt_rventa;
    }

    public JToolBar.Separator getSp0() {
        return sp0;
    }

    public void setSp0(JToolBar.Separator sp0) {
        this.sp0 = sp0;
    }

    public JToolBar.Separator getSp1() {
        return sp1;
    }

    public void setSp1(JToolBar.Separator sp1) {
        this.sp1 = sp1;
    }

    public JToolBar.Separator getSp2() {
        return sp2;
    }

    public void setSp2(JToolBar.Separator sp2) {
        this.sp2 = sp2;
    }

    public JToolBar.Separator getSp3() {
        return sp3;
    }

    public void setSp3(JToolBar.Separator sp3) {
        this.sp3 = sp3;
    }

    public JToolBar.Separator getSp4() {
        return sp4;
    }

    public void setSp4(JToolBar.Separator sp4) {
        this.sp4 = sp4;
    }

    public JButton getBt_plantilla() {
        return bt_plantilla;
    }

    public void setBt_plantilla(JButton bt_plantilla) {
        this.bt_plantilla = bt_plantilla;
    }

    public JToolBar.Separator getSp5() {
        return sp5;
    }

    public void setSp5(JToolBar.Separator sp5) {
        this.sp5 = sp5;
    }
    
    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_plantilla;
    private javax.swing.JButton bt_rcliente;
    private javax.swing.JButton bt_rpersona;
    private javax.swing.JButton bt_rproducto;
    private javax.swing.JButton bt_rproveedor;
    private javax.swing.JButton bt_rventa;
    public static javax.swing.JDesktopPane jDesktopPanePrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel jlb_logo;
    private javax.swing.JLabel jlfooter;
    private javax.swing.JMenu jm_personas;
    private javax.swing.JMenu jmenuMembresias;
    private javax.swing.JMenu jmenuVentas;
    private javax.swing.JMenu jmenu_productos;
    private javax.swing.JMenuItem jmi_gcargos;
    private javax.swing.JMenuItem jmi_gcategorias;
    private javax.swing.JMenuItem jmi_gclientes;
    private javax.swing.JMenuItem jmi_gempleados;
    private javax.swing.JMenuItem jmi_gmembresias;
    private javax.swing.JMenuItem jmi_gpersonas;
    private javax.swing.JMenuItem jmi_gproductos;
    private javax.swing.JMenuItem jmi_gproveedores;
    private javax.swing.JMenuItem jmi_grutinas;
    private javax.swing.JMenuItem jmi_gusers;
    private javax.swing.JMenuItem jmi_plantillaEntrenamiento;
    private javax.swing.JMenuItem jmi_realizarventa;
    private javax.swing.JMenuItem jmi_registrarcargo;
    private javax.swing.JMenuItem jmi_registraruser;
    private javax.swing.JMenuItem jmi_reporteventas;
    private javax.swing.JMenuItem jmi_rgcliente;
    private javax.swing.JMenuItem jmi_rgctgproducto;
    private javax.swing.JMenuItem jmi_rgempleado;
    private javax.swing.JMenuItem jmi_rgmembresia;
    private javax.swing.JMenuItem jmi_rgpersona;
    private javax.swing.JMenuItem jmi_rgproducto;
    private javax.swing.JMenuItem jmi_rgproveedor;
    private javax.swing.JMenuItem jmi_rgrutina;
    private javax.swing.JMenuItem jmi_salir;
    private javax.swing.JToolBar.Separator sp0;
    private javax.swing.JToolBar.Separator sp1;
    private javax.swing.JToolBar.Separator sp2;
    private javax.swing.JToolBar.Separator sp3;
    private javax.swing.JToolBar.Separator sp4;
    private javax.swing.JToolBar.Separator sp5;
    // End of variables declaration//GEN-END:variables
}
