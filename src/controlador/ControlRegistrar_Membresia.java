package controlador;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.MembresiaDao;
import vista.VistaAdministrador;
import vista.VistaRegistrar_Membresia;

public class ControlRegistrar_Membresia {

    private MembresiaDao modelo;
    private VistaRegistrar_Membresia vista;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlRegistrar_Membresia(MembresiaDao modelo, VistaRegistrar_Membresia vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Membresias - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int)(VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth())/2,
                (int)(VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight())/2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void funcionalidad() {

        vista.getBt_registrar().addActionListener(l -> registrarMembresia());

    }

    private boolean validacion() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText();
        String descuento = vista.getTxt_descuento().getText();
        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty() || descuento.isEmpty()) {
            return false;

        }
        return validacion;
    }

    private boolean verificarMembresia() {

        return modelo.mostrarDatos("").stream().noneMatch(c -> c.getNombre().equalsIgnoreCase(vista.getTxt_nombre().getText()));
    }

    private double valorDescuento(String porcentaje) {

        double descuento_decimal = (Double.parseDouble(porcentaje)) / 100;

        return descuento_decimal;

    }

    private void reiniciarCampos() {

        vista.getTxt_nombre().setText("");
        vista.getTxt_descripcion().setText("");
        vista.getTxt_descuento().setText("");
        vista.getTxt_nombre().setBorder(origin_border);

    }

    private void sentenciaInsert() {

        String nombre = vista.getTxt_nombre().getText().trim();
        String descripcion = vista.getTxt_descripcion().getText().trim();
        double descuento = valorDescuento(vista.getTxt_descuento().getText().trim());

        modelo.setNombre(nombre);
        modelo.setDescripcion(descripcion);
        modelo.setDescuento(descuento);

        if (modelo.insertar()) {

            JOptionPane.showMessageDialog(vista, "Membresia Registrada");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }

    }

    private void registrarMembresia() {

        if (validacion()) {

            if (verificarMembresia()) {
                vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Membresía ya registrada", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
                vista.getTxt_nombre().grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}
