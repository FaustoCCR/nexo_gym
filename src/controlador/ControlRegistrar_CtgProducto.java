package controlador;

import controlador.validaciones.VCampoParticular;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.dao.Ctg_ProductoDao;
import vista.VistaAdministrador;
import vista.VistaRegistrar_CtgProducto;

public class ControlRegistrar_CtgProducto {

    private Ctg_ProductoDao modelo;
    private VistaRegistrar_CtgProducto vista;

    public ControlRegistrar_CtgProducto(Ctg_ProductoDao modelo, VistaRegistrar_CtgProducto vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro Categorias Productos - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void funcionalidad() {

        /* validaciones */
        vista.getTxt_nombre().addKeyListener(new VCampoParticular(vista.getTxt_nombre(), 17));
        vista.getTxt_descripcion().addKeyListener(new VCampoParticular(vista.getTxt_descripcion(), 45));

        vista.getBt_registrar().addActionListener(l -> registrarCategoria());

    }

    private boolean validarRegistro() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText();

        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty()) {

            return false;

        }
        return validacion;
    }

    private boolean verificarCategoria() {

        return modelo.mostrarDatos().stream().noneMatch(c -> c.getNombre().equalsIgnoreCase(vista.getTxt_nombre().getText()));
    }

    private void sentenciaInsert() {

        String nombre = vista.getTxt_nombre().getText();
        String descripcion = vista.getTxt_descripcion().getText().trim();

        modelo.setNombre(nombre);
        modelo.setDescripcion(descripcion);

        if (modelo.insertar()) {

            JOptionPane.showMessageDialog(vista, "Categor??a Registrada");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }
    }

    private void reiniciarCampos() {

        vista.getTxt_nombre().setText("");
        vista.getTxt_descripcion().setText("");

    }

    private void registrarCategoria() {

        if (validarRegistro()) {

            if (verificarCategoria()) {

                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Categor??a ya registrada", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxt_nombre().grabFocus();

            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}
