/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.dao.RutinaDao;
import vista.VistaRegistrar_Rutina;

/**
 *
 * @author Casa
 */
public class ControlRegistrar_Rutina {
    private RutinaDao modelo;
    private VistaRegistrar_Rutina vista;

    public ControlRegistrar_Rutina(RutinaDao modelo, VistaRegistrar_Rutina vista){
        this.vista = vista;
        this.modelo=modelo;
        
        vista.setVisible(true);
        vista.setTitle("Registro de Rutina - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void funcionalidad() {

        vista.getBtnRegistrar().addActionListener(l -> registrarRutina());

    }

    private boolean validacion() {

        String nombre = vista.getTxtNombre().getText();
        String descripcion = vista.getTxtDescripcion().getText();
        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            return false;

        }
        return validacion;
    }

    private boolean verificarRutina() {

        return modelo.mostrarDatos().stream().noneMatch(c -> c.getNombre().equals(vista.getTxtNombre().getText()));
    }

    private void sentenciaInsert() {

        String rutina = vista.getTxtNombre().getText().trim();
        String descripcion = vista.getTxtDescripcion().getText().trim();

        modelo.setNombre(rutina);
        modelo.setDescripcion(descripcion);

        if (modelo.insertar()) {
            JOptionPane.showMessageDialog(vista, "Rutina Registrado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }
    }

    private void reiniciarCampos() {

        vista.getTxtNombre().setText("");
        vista.getTxtDescripcion().setText("");

    }

    private void registrarRutina() {

        boolean validacion_campos = validacion();

        if (validacion_campos) {

            boolean checkrutina = verificarRutina();

            if (checkrutina) {

                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Rutina ya registrado", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxtNombre().grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
