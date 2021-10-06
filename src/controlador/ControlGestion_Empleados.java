package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.EmpleadoDao;
import vista.VistaGestion_Empleado;

public class ControlGestion_Empleados {

    private EmpleadoDao modelo_empleado;
    private VistaGestion_Empleado vista_empleado;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"Id", "Nombre", "Cargo", "Fecha Contrato", "Sueldo"};

    public ControlGestion_Empleados(EmpleadoDao modelo_empleado, VistaGestion_Empleado vista_empleado) {
        this.modelo_empleado = modelo_empleado;
        this.vista_empleado = vista_empleado;

        vista_empleado.setVisible(true);
        vista_empleado.setTitle("Empleados Registrados - Nexo Gym");
        vista_empleado.setResizable(false);
        vista_empleado.setLocationRelativeTo(null);
        vista_empleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla("");

    }

    public void funcionalidad() {
        vista_empleado.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mostrarDatosTabla(vista_empleado.getTxt_buscar().getText());
            }

        });
        vista_empleado.getBt_eliminar().addActionListener(l -> BorrarEmpleado());
    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;

                } else {
                    return false;
                }
            }
        };

        vista_empleado.getJtable_empleados().setModel(tb_model);
        vista_empleado.getJtable_empleados().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_empleado.getJtable_empleados().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_empleado.getJtable_empleados().getColumnModel().getColumn(0).setPreferredWidth(20);

    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);
        modelo_empleado.mostrarDatosJoin().stream().forEach((c) -> {
            Object[] contenido
                    = {c.getId_empleado(), c.getNombrecliente(), c.getNombreCargo(), c.getFecha_contrato(), c.getSueldo()};
            tb_model.addRow(contenido);
        });
    }

    private void BorrarEmpleado() {
        int fila = vista_empleado.getJtable_empleados().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_emp = (int) vista_empleado.getJtable_empleados().getValueAt(fila, columna);
            String emp = modelo_empleado.mostrarDatos().stream().filter(u -> u.getId_empleado() == id_emp).findAny().get().getNombrecliente();

            int resp = JOptionPane.showConfirmDialog(vista_empleado, "Seguro desea eliminar al Empleado : " + emp, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_empleado.eliminar(id_emp);
                JOptionPane.showMessageDialog(vista_empleado, "Registro Eliminado");
                mostrarDatosTabla("");
            }
        } else {
            JOptionPane.showMessageDialog(vista_empleado, "Seleccione el registro a eliminar");
        }
    }
}
