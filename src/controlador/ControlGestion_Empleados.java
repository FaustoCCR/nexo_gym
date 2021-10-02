
package controlador;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.dao.EmpleadoDao;
import vista.VistaGestion_Empleado;

/**
 *
 * @author Alex
 */
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

        mostrarDatosTabla();

    }
    
    public void funcionalidad() {

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
    
    private void mostrarDatosTabla() {
        
        tb_model.setRowCount(0);
        modelo_empleado.mostrarDatosJoin().stream().forEach((c) -> {
            Object[] contenido
                    = {c.getId_empleado(), c.getNombrecliente(), c.getNombreCargo(), c.getFecha_contrato(), c.getSueldo()};
            tb_model.addRow(contenido);
        });
    }
}
