package vista;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VistaAdministrador extends javax.swing.JFrame {

    public VistaAdministrador() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
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
        JMenu5 = new javax.swing.JMenu();
        jmi_rgproducto = new javax.swing.JMenuItem();
        jmi_rgctgproducto = new javax.swing.JMenuItem();
        jmi_rgproveedor = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmi_gproductos = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmi_rgmembresia = new javax.swing.JMenuItem();
        jmi_rgrutina = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmi_gmembresias = new javax.swing.JMenuItem();
        jmi_grutinas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(153, 153, 255));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

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

        JMenu5.setText("Productos");
        JMenu5.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_rgproducto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgproducto.setText("Registrar Producto");
        JMenu5.add(jmi_rgproducto);

        jmi_rgctgproducto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgctgproducto.setText("Registrar Categoría");
        JMenu5.add(jmi_rgctgproducto);

        jmi_rgproveedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgproveedor.setText("Registrar Proveedor");
        JMenu5.add(jmi_rgproveedor);
        JMenu5.add(jSeparator1);

        jmi_gproductos.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gproductos.setText("Mostrar Productos");
        JMenu5.add(jmi_gproductos);

        jMenuBar1.add(JMenu5);

        jMenu6.setText("Membresías");
        jMenu6.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_rgmembresia.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgmembresia.setText("Registrar Membresia");
        jMenu6.add(jmi_rgmembresia);

        jmi_rgrutina.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_rgrutina.setText("Registrar Rutina");
        jMenu6.add(jmi_rgrutina);
        jMenu6.add(jSeparator2);

        jmi_gmembresias.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_gmembresias.setText("Mostrar Membresías");
        jMenu6.add(jmi_gmembresias);

        jmi_grutinas.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jmi_grutinas.setText("Mostrar Rutinas");
        jMenu6.add(jmi_grutinas);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 542, Short.MAX_VALUE))
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
    private javax.swing.JMenu JMenu5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu jm_personas;
    private javax.swing.JMenuItem jmi_gcargos;
    private javax.swing.JMenuItem jmi_gclientes;
    private javax.swing.JMenuItem jmi_gempleados;
    private javax.swing.JMenuItem jmi_gmembresias;
    private javax.swing.JMenuItem jmi_gpersonas;
    private javax.swing.JMenuItem jmi_gproductos;
    private javax.swing.JMenuItem jmi_grutinas;
    private javax.swing.JMenuItem jmi_gusers;
    private javax.swing.JMenuItem jmi_registrarcargo;
    private javax.swing.JMenuItem jmi_registraruser;
    private javax.swing.JMenuItem jmi_rgcliente;
    private javax.swing.JMenuItem jmi_rgctgproducto;
    private javax.swing.JMenuItem jmi_rgempleado;
    private javax.swing.JMenuItem jmi_rgmembresia;
    private javax.swing.JMenuItem jmi_rgpersona;
    private javax.swing.JMenuItem jmi_rgproducto;
    private javax.swing.JMenuItem jmi_rgproveedor;
    private javax.swing.JMenuItem jmi_rgrutina;
    private javax.swing.JMenuItem jmi_salir;
    // End of variables declaration//GEN-END:variables
}
