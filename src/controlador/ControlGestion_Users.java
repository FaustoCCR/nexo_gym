package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.UsuarioDao;
import vista.VistaActualizar_Usuario;
import vista.VistaGestion_Users;

public class ControlGestion_Users {

    private UsuarioDao modelo_user;
    private VistaGestion_Users vista;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Usuario", "Rol", "Estado"};
    public static int id_user;

    public ControlGestion_Users(UsuarioDao modelo_user, VistaGestion_Users vista) {
        this.modelo_user = modelo_user;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Clientes Registrados - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation(611, 159);
        vista.setClosable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla("");
    }

    public void funcionalidad() {

        vista.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                mostrarDatosTabla(vista.getTxt_buscar().getText());

            }

        });

        vista.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista.getBt_eliminar().addActionListener(l->sentenciaDelete());
        

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;

                } else {
                    return false;
                }
            }

        };

        vista.getJtable_users().setModel(tb_model);
        vista.getJtable_users().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getJtable_users().setShowHorizontalLines(true);//colocar lineas horizontales7
        vista.getJtable_users().getColumnModel().getColumn(0).setPreferredWidth(20);

    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);
        modelo_user.mostrarDatosJoin(aguja).stream().forEach((u) -> {

            Object[] contenido
                    = {u.getId_persona(), u.getNombres_usuario(), u.getUser_name(), u.getNombre_rol(), estadoCuenta(u.isEstado_cuenta())};

            tb_model.addRow(contenido);

        });
    }

    private void ventanaActualizar() {

        int fila = vista.getJtable_users().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_user = (int) vista.getJtable_users().getValueAt(fila, columna);
            vista.dispose();
            VistaActualizar_Usuario vista = new VistaActualizar_Usuario();
            ControlActualizar_User control = new ControlActualizar_User(modelo_user, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a verificar");
        }

    }

    private String estadoCuenta(boolean estado) {

        String mensaje;
        if (estado == true) {

            mensaje = "Activo";
        } else {
            mensaje = "Inactivo";
        }

        return mensaje;

    }

    private void sentenciaDelete() {

        int fila = vista.getJtable_users().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_user = (int) vista.getJtable_users().getValueAt(fila, columna);
            String user = modelo_user.mostrarDatos().stream().filter(u -> u.getId_user()== id_user).findAny().get().getUser_name();

            int resp = JOptionPane.showConfirmDialog(vista, "Seguro desea eliminar al usuario : " + user, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_user.eliminar(id_user);
                JOptionPane.showMessageDialog(vista, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a eliminar");
        }
    }

}
