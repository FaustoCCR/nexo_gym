package vista;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VistaRegistrar_Persona extends javax.swing.JFrame {

    public VistaRegistrar_Persona() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_genero = new javax.swing.ButtonGroup();
        txt_cedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        lblCedula1 = new javax.swing.JLabel();
        txt_apellido = new javax.swing.JTextField();
        lblCedula2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdnacimiento = new com.toedter.calendar.JDateChooser();
        txt_correo = new javax.swing.JTextField();
        lblCedula3 = new javax.swing.JLabel();
        rbt_masculino = new javax.swing.JRadioButton();
        rbt_femenino = new javax.swing.JRadioButton();
        lblCedula4 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        lblCedula5 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        lblCedula6 = new javax.swing.JLabel();
        bt_registrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txt_cedula.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_cedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCedula.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula.setText("Cédula:");

        lblTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblTitulo.setText("Registro de Personas");

        txt_nombre.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCedula1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula1.setText("Nombre:");

        txt_apellido.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_apellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCedula2.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula2.setText("Apellido:");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel4.setText("F.Nacimiento:");

        jdnacimiento.setForeground(new java.awt.Color(153, 153, 255));
        jdnacimiento.setMaxSelectableDate(new Date());

        txt_correo.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_correo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCedula3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula3.setText("Correo:");

        bt_genero.add(rbt_masculino);
        rbt_masculino.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        rbt_masculino.setText("M");

        bt_genero.add(rbt_femenino);
        rbt_femenino.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        rbt_femenino.setText("F");

        lblCedula4.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula4.setText("Género:");

        txt_direccion.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_direccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCedula5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula5.setText("Dirección:");

        txt_telefono.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        txt_telefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCedula6.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lblCedula6.setText("Teléfono:");

        bt_registrar.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        bt_registrar.setText("Registrar Persona");
        bt_registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_cedula, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(lblCedula))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addComponent(lblCedula1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_apellido, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addComponent(lblCedula2))
                            .addComponent(jLabel4)
                            .addComponent(jdnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addComponent(lblCedula5))
                            .addComponent(lblCedula4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbt_masculino, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(rbt_femenino))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_correo, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addComponent(lblCedula3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bt_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(lblCedula6))))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblTitulo)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCedula3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula1)
                    .addComponent(lblCedula4))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbt_masculino, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbt_femenino, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCedula2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCedula5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCedula6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(bt_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ButtonGroup getBt_genero() {
        return bt_genero;
    }

    public void setBt_genero(ButtonGroup bt_genero) {
        this.bt_genero = bt_genero;
    }

    public JButton getBt_registrar() {
        return bt_registrar;
    }

    public void setBt_registrar(JButton bt_registrar) {
        this.bt_registrar = bt_registrar;
    }

    public JDateChooser getJdnacimiento() {
        return jdnacimiento;
    }

    public void setJdnacimiento(JDateChooser jdnacimiento) {
        this.jdnacimiento = jdnacimiento;
    }

    public JRadioButton getRbt_femenino() {
        return rbt_femenino;
    }

    public void setRbt_femenino(JRadioButton rbt_femenino) {
        this.rbt_femenino = rbt_femenino;
    }

    public JRadioButton getRbt_masculino() {
        return rbt_masculino;
    }

    public void setRbt_masculino(JRadioButton rbt_masculino) {
        this.rbt_masculino = rbt_masculino;
    }

    public JTextField getTxt_apellido() {
        return txt_apellido;
    }

    public void setTxt_apellido(JTextField txt_apellido) {
        this.txt_apellido = txt_apellido;
    }

    public JTextField getTxt_cedula() {
        return txt_cedula;
    }

    public void setTxt_cedula(JTextField txt_cedula) {
        this.txt_cedula = txt_cedula;
    }

    public JTextField getTxt_correo() {
        return txt_correo;
    }

    public void setTxt_correo(JTextField txt_correo) {
        this.txt_correo = txt_correo;
    }

    public JTextField getTxt_direccion() {
        return txt_direccion;
    }

    public void setTxt_direccion(JTextField txt_direccion) {
        this.txt_direccion = txt_direccion;
    }

    public JTextField getTxt_nombre() {
        return txt_nombre;
    }

    public void setTxt_nombre(JTextField txt_nombre) {
        this.txt_nombre = txt_nombre;
    }

    public JTextField getTxt_telefono() {
        return txt_telefono;
    }

    public void setTxt_telefono(JTextField txt_telefono) {
        this.txt_telefono = txt_telefono;
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
            java.util.logging.Logger.getLogger(VistaRegistrar_Membresia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrar_Membresia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrar_Membresia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrar_Membresia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaRegistrar_Persona().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bt_genero;
    private javax.swing.JButton bt_registrar;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JDateChooser jdnacimiento;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCedula1;
    private javax.swing.JLabel lblCedula2;
    private javax.swing.JLabel lblCedula3;
    private javax.swing.JLabel lblCedula4;
    private javax.swing.JLabel lblCedula5;
    private javax.swing.JLabel lblCedula6;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rbt_femenino;
    private javax.swing.JRadioButton rbt_masculino;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
