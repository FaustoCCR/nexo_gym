package controlador;

import controlador.validaciones.VCampoParticular;
import controlador.validaciones.VLetras;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import modelo.dao.CargoDao;
import vista.VistaAdministrador;
import vista.VistaRegistrarCargo;

public class ControlRegistrar_Cargo {

    private CargoDao modelo;
    private VistaRegistrarCargo vista;

    public ControlRegistrar_Cargo(CargoDao modelo, VistaRegistrarCargo vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Cargos - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void funcionalidad() {

        /*--validaciones----  */
        vista.getTxt_nombre().addKeyListener(new VLetras(vista.getTxt_nombre(), 17));
        vista.getTxt_descripcion().addKeyListener(new VCampoParticular(vista.getTxt_descripcion(), 45));

        vista.getBt_registrar().addActionListener(l -> registrarCargo());

    }

    private boolean validacion() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText();
        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            return false;

        }
        return validacion;
    }

    private boolean verificarCargo() {

        return modelo.mostrarDatos().stream().noneMatch(c -> c.getNombre().equalsIgnoreCase(vista.getTxt_nombre().getText()));
    }

    private void sentenciaInsert() {

        String cargo = vista.getTxt_nombre().getText().trim();
        String descripcion = vista.getTxt_descripcion().getText().trim();

        modelo.setNombre(cargo);
        modelo.setDescripcion(descripcion);

        if (modelo.insertar()) {
            JOptionPane.showMessageDialog(vista, "Cargo Registrado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }
    }

    private void reiniciarCampos() {

        vista.getTxt_nombre().setText("");
        vista.getTxt_descripcion().setText("");

    }

    private void registrarCargo() {

        boolean validacion_campos = validacion();

        if (validacion_campos) {

            boolean checkcargo = verificarCargo();

            if (checkcargo) {

                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Cargo ya registrado", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
                vista.getTxt_nombre().grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}
