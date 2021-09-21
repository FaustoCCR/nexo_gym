package vista;

import javax.swing.JMenuItem;

public class VistaAdministrador extends javax.swing.JFrame {

    public VistaAdministrador() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmi_salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jmi_registraruser = new javax.swing.JMenuItem();
        jmi_registrarcargo = new javax.swing.JMenuItem();
        jmi_rgmembresia = new javax.swing.JMenuItem();
        jmi_rgcliente = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_salir.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jmi_salir.setText("Salir");
        jMenu1.add(jmi_salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jMenu3.setText("Registrar");
        jMenu3.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N

        jmi_registraruser.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jmi_registraruser.setText("Usuario");
        jMenu3.add(jmi_registraruser);

        jmi_registrarcargo.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jmi_registrarcargo.setText("Cargo");
        jMenu3.add(jmi_registrarcargo);

        jmi_rgmembresia.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jmi_rgmembresia.setText("Membresia");
        jMenu3.add(jmi_rgmembresia);

        jmi_rgcliente.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jmi_rgcliente.setText("Cliente");
        jMenu3.add(jmi_rgcliente);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmi_registrarcargo;
    private javax.swing.JMenuItem jmi_registraruser;
    private javax.swing.JMenuItem jmi_rgcliente;
    private javax.swing.JMenuItem jmi_rgmembresia;
    private javax.swing.JMenuItem jmi_salir;
    // End of variables declaration//GEN-END:variables
}
