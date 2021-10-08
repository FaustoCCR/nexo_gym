
package controlador;


import java.awt.Color;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.CargoDao;
import modelo.vo.CargoVo;
import vista.VistaActualizar_Cargo;

/**
 *
 * @author Alex
 */
public class ControlActualizar_Cargo {
    
    private CargoDao modelo_cargo;
    private VistaActualizar_Cargo vista;
    private int id_cargo;
    private Border origin_border = new LineBorder(Color.gray, 1);
    
    public ControlActualizar_Cargo(CargoDao modelo_cargo, VistaActualizar_Cargo vista) {
        this.modelo_cargo = modelo_cargo;
        this.vista = vista;

        id_cargo = ControlGestion_Cargo.id_cargo;

        vista.setVisible(true);
        vista.setTitle("Actualizar Cargo - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosCargo();
    }
    
    public void funcionalidad() {
        vista.getBt_actualizar().addActionListener(l -> actualizarCargo());
    }
    
    private void cargarDatosCargo() {
        modelo_cargo.mostrarDatos(id_cargo).forEach((m) -> {
            vista.getTxt_nombre().setText(m.getNombre());
            vista.getTxt_descripcion().setText(m.getDescripcion());
        });
    }
    
    private boolean validacionRegistro() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText();
        boolean validacion = true;
        if (nombre.isEmpty() || descripcion.isEmpty()) {
            return false;
        }
        return validacion;
    }
    
    private boolean verificarCargo(String nombre, int id) {
        Predicate<CargoVo> condicion1 = mem -> mem.getNombre().equalsIgnoreCase(nombre);
        Predicate<CargoVo> condicion2 = mem -> mem.getId_cargo() != id;
        boolean resp = modelo_cargo.mostrarDatos("").stream().noneMatch(condicion1.and(condicion2));
        if (resp) {
            vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
        } else {
            vista.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
        }
        return resp;
    }
    
    private void restaurarCampos() {
        vista.getTxt_nombre().setBorder(origin_border);

    }

    private void modificarCargo() {
        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText().trim();
        modelo_cargo.setNombre(nombre);
        modelo_cargo.setDescripcion(descripcion);
        if (modelo_cargo.modificar(id_cargo)) {
            JOptionPane.showMessageDialog(vista, "Cargo Actualizado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Actualizar");
        }
    }
    
    private void actualizarCargo() {
        if (validacionRegistro()) {
            if (verificarCargo(vista.getTxt_nombre().getText(), id_cargo)) {
                modificarCargo();
                restaurarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Cargo ya registrado", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxt_nombre().grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
