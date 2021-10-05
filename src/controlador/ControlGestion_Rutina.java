/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.ControlGestion_Users.id_user;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.RutinaDao;
import vista.VistaActualizar_Rutina;
import vista.VistaGestion_Rutina;

/**
 *
 * @author Casa
 */
public class ControlGestion_Rutina {

    private RutinaDao modelo_rutina;
    private VistaGestion_Rutina vista_rutina;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Descripcion"};
    public static int id_rutina;

    public ControlGestion_Rutina(RutinaDao modelo_rutina, VistaGestion_Rutina vista_rutina) {
        this.modelo_rutina = modelo_rutina;
        this.vista_rutina = vista_rutina;

        vista_rutina.setVisible(true);
        vista_rutina.setTitle("Rutinas Registradas - Nexo Gym");
        vista_rutina.setResizable(false);
        vista_rutina.setLocationRelativeTo(null);
        vista_rutina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();
        mostrarDatosTabla("");

    }

    public void funcionalidad() {
      vista_rutina.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                mostrarDatosTabla(vista_rutina.getTxt_buscar().getText());

            }

        });

        vista_rutina.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista_rutina.getBt_eliminar().addActionListener(l->sentenciaDelete());
        
    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;

                } else {
                    return false;
                }
            }

        };

        vista_rutina.getJtable_rutinas().setModel(tb_model);
        vista_rutina.getJtable_rutinas().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_rutina.getJtable_rutinas().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_rutina.getJtable_rutinas().getColumnModel().getColumn(0).setPreferredWidth(20);

    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);

        modelo_rutina.mostrarDatosJoin(aguja).stream().forEach((ru) -> {

            Object[] contenido = {ru.getId_rutina(), ru.getNombre(), ru.getDescripcion()};

            tb_model.addRow(contenido);

        });
    }
    
    private void ventanaActualizar() {

        int fila = vista_rutina.getJtable_rutinas().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_rutina = (int) vista_rutina.getJtable_rutinas().getValueAt(fila, columna);
            vista_rutina.dispose();
            VistaActualizar_Rutina vista = new VistaActualizar_Rutina();
            ControlActualizar_Rutina control = new ControlActualizar_Rutina(modelo_rutina, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_rutina , "Seleccione el registro a verificar");
        }

    }
     private void sentenciaDelete() {

        int fila = vista_rutina.getJtable_rutinas().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_rutina = (int) vista_rutina.getJtable_rutinas().getValueAt(fila, columna);
            String nombre = modelo_rutina.mostrarDatos().stream().filter(u -> u.getId_rutina()== id_rutina).findAny().get().getNombre();

            int resp = JOptionPane.showConfirmDialog(vista_rutina, "Seguro desea eliminar la rutina : " + nombre, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_rutina.eliminar(id_rutina);
                JOptionPane.showMessageDialog(vista_rutina, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista_rutina, "Seleccione el registro a eliminar");
        }
    }
    
    
}
