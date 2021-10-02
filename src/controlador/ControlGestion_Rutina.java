/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.dao.RutinaDao;
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

    public ControlGestion_Rutina(RutinaDao modelo_rutina, VistaGestion_Rutina vista_rutina) {
        this.modelo_rutina = modelo_rutina;
        this.vista_rutina = vista_rutina;

        vista_rutina.setVisible(true);
        vista_rutina.setTitle("Rutinas Registradas - Nexo Gym");
        vista_rutina.setResizable(false);
        vista_rutina.setLocationRelativeTo(null);
        vista_rutina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla();

    }

    public void funcionalidad() {

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

    private void mostrarDatosTabla() {

        tb_model.setRowCount(0);

        modelo_rutina.mostrarDatos().stream().forEach((ru) -> {

            Object[] contenido = {ru.getId_rutina(), ru.getNombre(), ru.getDescripcion()};

            tb_model.addRow(contenido);

        });
    }

}
