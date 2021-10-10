package controlador;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.RutinaDao;
import vista.VistaAdministrador;
import vista.VistaRegistrar_Rutina;

public class ControlRegistrar_Rutina {

    private RutinaDao modelo;
    private VistaRegistrar_Rutina vista;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlRegistrar_Rutina(RutinaDao modelo, VistaRegistrar_Rutina vista) {
        this.vista = vista;
        this.modelo = modelo;

        vista.setVisible(true);
        vista.setTitle("Registro de Rutina - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int)(VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth())/2,
                (int)(VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight())/2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void funcionalidad() {

        vista.getBt_registrar().addActionListener(l -> registrarRutina());

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

        return modelo.mostrarDatos().stream().noneMatch(c -> c.getNombre().equalsIgnoreCase(vista.getTxtNombre().getText()));
    }

    private void sentenciaInsert() {

        String rutina = vista.getTxtNombre().getText().trim();
        String descripcion = vista.getTxtDescripcion().getText().trim();

        modelo.setNombre(rutina);
        modelo.setDescripcion(descripcion);

        if (modelo.insertar()) {
            JOptionPane.showMessageDialog(vista, "Rutina Registrada");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }
    }

    private void reiniciarCampos() {

        vista.getTxtNombre().setText("");
        vista.getTxtNombre().setBorder(origin_border);
        vista.getTxtDescripcion().setText("");

    }

    private void registrarRutina() {

        boolean validacion_campos = validacion();

        if (validacion_campos) {

            boolean checkrutina = verificarRutina();

            if (checkrutina) {
                vista.getTxtNombre().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Rutina ya registrada", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxtNombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
                vista.getTxtNombre().grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
