package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.ProveedorDao;
import modelo.vo.ProveedorVo;
import vista.VistaActualizar_Proveedores;

public class ControlActualizar_Proveedor {

    private ProveedorDao modelo_proveedor;
    private VistaActualizar_Proveedores vista_proveedor;
    private String id_proveedor;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_Proveedor(ProveedorDao modelo_proveedor, VistaActualizar_Proveedores vista_proveedor) {
        this.modelo_proveedor = modelo_proveedor;
        this.vista_proveedor = vista_proveedor;

        id_proveedor = ControlGestion_Proveedor.id_proveedor;

        vista_proveedor.setVisible(true);
        vista_proveedor.setTitle("Actualizar Rutina - Nexo Gym");
        vista_proveedor.setResizable(false);
        vista_proveedor.setLocationRelativeTo(null);
        vista_proveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosProveedor();
    }

    public void funcionalidad() {
        vista_proveedor.getTxt_ruc().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarRUCRepetido(vista_proveedor.getTxt_ruc().getText());
            }

        });
        vista_proveedor.getBt_actualizar().addActionListener(l -> actualizarProveedor());
    }

    private void cargarDatosProveedor() {

        modelo_proveedor.mostrarDatosJoin(id_proveedor).stream().forEach((us) -> {

            vista_proveedor.getTxt_ruc().setText(us.getId_proveedor());
            vista_proveedor.getTxt_nombre().setText(us.getNombre());
            vista_proveedor.getTxt_contacto().setText(us.getContacto());

        });

    }

    private boolean verificarRUCRepetido(String ruc) {

        /*Analiza si el RUC a ser cambiada pertenece a otra proveedor ya registrado, por lo que no podrá si es el caso
         */
        if (!ruc.isEmpty()) {

            Predicate<ProveedorVo> condicion = p -> p.getId_proveedor().equalsIgnoreCase(ruc);
            Predicate<ProveedorVo> condicion2 = p -> p.getId_proveedor() != id_proveedor;

            boolean respuesta = modelo_proveedor.mostrarDatos(ruc, id_proveedor).isEmpty();
            if (respuesta) {

                vista_proveedor.getTxt_ruc().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            } else {
                vista_proveedor.getTxt_ruc().setBorder(new LineBorder(Color.decode("#C33529"), 2));

            }
            return respuesta;
        } else {
            vista_proveedor.getTxt_ruc().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            return false;
        }

    }

    private boolean validarRegistro() {

        String ruc = vista_proveedor.getTxt_ruc().getText();
        String nombre = vista_proveedor.getTxt_nombre().getText();
        String contacto = vista_proveedor.getTxt_contacto().getText();

        boolean validacion = true;

        if (ruc.isEmpty() || ruc.isEmpty() || nombre.isEmpty() || contacto.isEmpty()) {

            validacion = false;

        }
        return validacion;
    }

    private void restaurarBordes() {

        vista_proveedor.getTxt_ruc().setBorder(origin_border);
    }

    private void sentenciaUpdate() {

        String ruc = vista_proveedor.getTxt_ruc().getText().trim();
        String nombre = vista_proveedor.getTxt_nombre().getText().trim();
        String contacto = vista_proveedor.getTxt_contacto().getText().trim();

        modelo_proveedor.setId_proveedor(ruc);
        modelo_proveedor.setNombre(nombre);
        modelo_proveedor.setContacto(contacto);

        if (modelo_proveedor.modificar1(id_proveedor)) {

            JOptionPane.showMessageDialog(vista_proveedor, "Proveedor Actualizado");

        } else {
            JOptionPane.showMessageDialog(vista_proveedor, "Error al Actualizar");
        }
    }

    private void actualizarProveedor() {

        if (validarRegistro()) {

            if (verificarRUCRepetido(vista_proveedor.getTxt_ruc().getText())) {

                sentenciaUpdate();
                restaurarBordes();

            } else {
                JOptionPane.showMessageDialog(vista_proveedor, "Proveedor no disponible", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista_proveedor.getTxt_nombre().grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(vista_proveedor, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

}
