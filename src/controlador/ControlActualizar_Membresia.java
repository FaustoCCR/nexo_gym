package controlador;

import controlador.validaciones.VCampoParticular;
import controlador.validaciones.VDecimales;
import java.awt.Color;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.MembresiaDao;
import modelo.vo.MembresiaVo;
import vista.VistaActualizar_Membresia;
import vista.VistaAdministrador;

public class ControlActualizar_Membresia {

    private MembresiaDao modelo_membresia;
    private VistaActualizar_Membresia vista;
    private int id_membresia;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_Membresia(MembresiaDao modelo_membresia, VistaActualizar_Membresia vista) {
        this.modelo_membresia = modelo_membresia;
        this.vista = vista;

        id_membresia = ControlGestion_Membresia.id_membresia;

        vista.setVisible(true);
        vista.setTitle("Actualizar Membresía - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosMembresia();

    }

    public void funcionalidad() {
        /*--- validaciones ---  */
        vista.getTxt_nombre().addKeyListener(new VCampoParticular(vista.getTxt_nombre(), 17));
        vista.getTxt_descripcion().addKeyListener(new VCampoParticular(vista.getTxt_descripcion(), 45));
        vista.getTxt_descuento().addKeyListener(new VDecimales(vista.getTxt_descuento()));

        vista.getBt_actualizar().addActionListener(l -> actualizarMembresia());

    }

    private void cargarDatosMembresia() {

        modelo_membresia.mostrarDatos(id_membresia).forEach((m) -> {

            vista.getTxt_nombre().setText(m.getNombre());
            vista.getTxt_descripcion().setText(m.getDescripcion());
            vista.getTxt_descuento().setText(Double.toString(m.getDescuento() * 100));
        });

    }

    private boolean validacionRegistro() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText();
        String descuento = vista.getTxt_descuento().getText();
        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty() || descuento.isEmpty()) {
            return false;

        }
        return validacion;
    }

    private boolean verificarMembresia(String nombre, int id) {

        Predicate<MembresiaVo> condicion1 = mem -> mem.getNombre().equalsIgnoreCase(nombre);
        Predicate<MembresiaVo> condicion2 = mem -> mem.getId_membresia() != id;

        boolean resp = modelo_membresia.mostrarDatos("").stream().noneMatch(condicion1.and(condicion2));
        if (resp) {
            vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
        } else {
            vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
        }

        return resp;
    }

    private double valorDescuento(String porcentaje) {

        double descuento_decimal = (Double.parseDouble(porcentaje)) / 100;

        return descuento_decimal;

    }

    private void restaurarCampos() {
        vista.getTxt_nombre().setBorder(origin_border);

    }

    private void sentenciaUpdate() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText().trim();
        double descuento = valorDescuento(vista.getTxt_descuento().getText());

        modelo_membresia.setNombre(nombre);
        modelo_membresia.setDescripcion(descripcion);
        modelo_membresia.setDescuento(descuento);

        if (modelo_membresia.modificar(id_membresia)) {

            JOptionPane.showMessageDialog(vista, "Membresía Actualizada");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Actualizar");
        }
    }

    private void actualizarMembresia() {

        if (validacionRegistro()) {

            if (verificarMembresia(vista.getTxt_nombre().getText(), id_membresia)) {

                sentenciaUpdate();
                restaurarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Membresía ya registrada", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxt_nombre().grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}
