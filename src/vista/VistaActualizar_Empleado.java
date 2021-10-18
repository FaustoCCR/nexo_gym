package vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VistaActualizar_Empleado extends javax.swing.JInternalFrame {

    public VistaActualizar_Empleado() {
        initComponents();
    }

    public JButton getBt_actualizar() {
        return bt_actualizar;
    }

    public void setBt_actualizar(JButton bt_actualizar) {
        this.bt_actualizar = bt_actualizar;
    }

    public JComboBox<String> getCb_cargo() {
        return cb_cargo;
    }

    public void setCb_cargo(JComboBox<String> cb_cargo) {
        this.cb_cargo = cb_cargo;
    }

    public JDateChooser getJd_fechacontrato() {
        return jd_fechacontrato;
    }

    public void setJd_fechacontrato(JDateChooser jd_fechacontrato) {
        this.jd_fechacontrato = jd_fechacontrato;
    }

    public JTextField getTxtSueldo() {
        return txtSueldo;
    }

    public void setTxtSueldo(JTextField txtSueldo) {
        this.txtSueldo = txtSueldo;
    }

    public JTextField getTxt_cedula() {
        return txt_cedula;
    }

    public void setTxt_cedula(JTextField txt_cedula) {
        this.txt_cedula = txt_cedula;
    }

    public JTextField getTxt_persona() {
        return txt_persona;
    }

    public void setTxt_persona(JTextField txt_persona) {
        this.txt_persona = txt_persona;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cb_cargo = new javax.swing.JComboBox<>();
        txt_cedula = new javax.swing.JTextField();
        txt_persona = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblPersona = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblSueldo = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        jd_fechacontrato = new com.toedter.calendar.JDateChooser();
        bt_actualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cb_cargo.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        cb_cargo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txt_cedula.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_cedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txt_persona.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_persona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt_persona.setEnabled(false);

        lblTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblTitulo.setText("Información del Empleado");

        lblCedula.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula.setText("Cédula:");

        lblPersona.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblPersona.setText("Persona:");

        lblCargo.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCargo.setText("Cargo");

        lblFecha.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblFecha.setText("Fecha de Contrato:");

        lblSueldo.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblSueldo.setText("Sueldo:");

        txtSueldo.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txtSueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSueldoActionPerformed(evt);
            }
        });

        jd_fechacontrato.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N

        bt_actualizar.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        bt_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/editar1.png"))); // NOI18N
        bt_actualizar.setText("Actualizar Empleado");
        bt_actualizar.setFocusPainted(false);
        bt_actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_persona, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(txt_cedula, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(lblCedula)
                    .addComponent(lblTitulo)
                    .addComponent(lblPersona)
                    .addComponent(lblCargo)
                    .addComponent(cb_cargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblFecha)
                        .addComponent(jd_fechacontrato, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                        .addComponent(lblSueldo)
                        .addComponent(txtSueldo))
                    .addComponent(bt_actualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitulo)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula)
                    .addComponent(lblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jd_fechacontrato, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPersona)
                    .addComponent(lblSueldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblCargo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_actualizar)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSueldoActionPerformed

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
            java.util.logging.Logger.getLogger(VistaActualizar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaActualizar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaActualizar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaActualizar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaActualizar_Empleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JComboBox<String> cb_cargo;
    private com.toedter.calendar.JDateChooser jd_fechacontrato;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPersona;
    private javax.swing.JLabel lblSueldo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_persona;
    // End of variables declaration//GEN-END:variables
}
