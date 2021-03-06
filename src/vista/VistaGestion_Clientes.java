package vista;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VistaGestion_Clientes extends javax.swing.JInternalFrame {

    public VistaGestion_Clientes() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_clientes = new javax.swing.JTable();
        bt_verificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        bt_asignarRutina = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setText("Clientes Registrados");

        jtable_clientes.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jtable_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtable_clientes.setGridColor(new java.awt.Color(51, 102, 255));
        jtable_clientes.setRowHeight(20);
        jScrollPane1.setViewportView(jtable_clientes);

        bt_verificar.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        bt_verificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/verify.png"))); // NOI18N
        bt_verificar.setText("Verificar");
        bt_verificar.setFocusPainted(false);
        bt_verificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        bt_eliminar.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        bt_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/remove1.png"))); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setFocusPainted(false);
        bt_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel1.setText("Buscar:");

        txt_buscar.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_buscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        bt_asignarRutina.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        bt_asignarRutina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/rutina1.png"))); // NOI18N
        bt_asignarRutina.setText("Asignar Rutina");
        bt_asignarRutina.setFocusPainted(false);
        bt_asignarRutina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        bt_imprimir.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        bt_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/print.png"))); // NOI18N
        bt_imprimir.setText("Imprimir");
        bt_imprimir.setFocusPainted(false);
        bt_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(bt_verificar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_asignarRutina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_verificar)
                    .addComponent(bt_eliminar)
                    .addComponent(bt_asignarRutina))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_imprimir)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTable getJtable_clientes() {
        return jtable_clientes;
    }

    public void setJtable_clientes(JTable jtable_clientes) {
        this.jtable_clientes = jtable_clientes;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JButton getBt_eliminar() {
        return bt_eliminar;
    }

    public void setBt_eliminar(JButton bt_eliminar) {
        this.bt_eliminar = bt_eliminar;
    }

    public JButton getBt_verificar() {
        return bt_verificar;
    }

    public void setBt_verificar(JButton bt_verificar) {
        this.bt_verificar = bt_verificar;
    }

    public JTextField getTxt_buscar() {
        return txt_buscar;
    }

    public void setTxt_buscar(JTextField txt_buscar) {
        this.txt_buscar = txt_buscar;
    }

    public JButton getBt_asignarRutina() {
        return bt_asignarRutina;
    }

    public void setBt_asignarRutina(JButton bt_asignarRutina) {
        this.bt_asignarRutina = bt_asignarRutina;
    }

    public JButton getBt_imprimir() {
        return bt_imprimir;
    }

    public void setBt_imprimir(JButton bt_imprimir) {
        this.bt_imprimir = bt_imprimir;
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
            java.util.logging.Logger.getLogger(VistaGestion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGestion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGestion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGestion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGestion_Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_asignarRutina;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_verificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable_clientes;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
