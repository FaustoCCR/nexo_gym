package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.MembresiaDao;
import vista.VistaActualizar_Membresia;
import vista.VistaGestion_Membresias;

public class ControlGestion_Membresia {

    private MembresiaDao modelo;
    private VistaGestion_Membresias vista;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Descripción", "Descuento"};
    public static int id_membresia;

    public ControlGestion_Membresia(MembresiaDao modelo, VistaGestion_Membresias vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setTitle("Membresías Registradas - Nexo Gym");
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
        vista.getBt_eliminar().addActionListener(l->sentenciaDelete());
        vista.getBt_verificar().addActionListener(l->ventanaActualizar());
        
        

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;

                } else {
                    return false;
                }
            }

        };

        vista.getJtable_membresias().setModel(tb_model);
        vista.getJtable_membresias().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getJtable_membresias().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getJtable_membresias().getColumnModel().getColumn(0).setPreferredWidth(20);

    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);

        modelo.mostrarDatos(aguja).stream().forEach((m) -> {

            Object[] contenido = {m.getId_membresia(), m.getNombre(), m.getDescripcion(), (m.getDescuento() * 100) + " %"};

            tb_model.addRow(contenido);

        });
    }

    private void ventanaActualizar() {

        int fila = vista.getJtable_membresias().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_membresia = (int) vista.getJtable_membresias().getValueAt(fila, columna);
            vista.dispose();
            VistaActualizar_Membresia vista = new VistaActualizar_Membresia();
            ControlActualizar_Membresia control = new ControlActualizar_Membresia(modelo, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a verificar");
        }

    }
    private void sentenciaDelete() {

        int fila = vista.getJtable_membresias().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_membresia = (int) vista.getJtable_membresias().getValueAt(fila, columna);
            String nombre = modelo.mostrarDatos("").stream().filter(u -> u.getId_membresia() == id_membresia).findAny().get().getNombre();

            int resp = JOptionPane.showConfirmDialog(vista, "Seguro desea eliminar memebresía : " + nombre, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo.eliminar(id_membresia);
                JOptionPane.showMessageDialog(vista, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a eliminar");
        }
    }
}
