package controlador;

import controlador.validaciones.VCampoParticular;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.Ctg_ProductoDao;
import vista.VistaActualizar_CtgProducto;
import vista.VistaAdministrador;

public class ControlActualizar_CtgProducto {

    private Ctg_ProductoDao modelo_ctgp;
    private VistaActualizar_CtgProducto vista_ctgp;
    private int id_ctgp;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_CtgProducto(Ctg_ProductoDao modelo_ctgp, VistaActualizar_CtgProducto vista_ctgp) {
        this.modelo_ctgp = modelo_ctgp;
        this.vista_ctgp = vista_ctgp;

        id_ctgp = ControlGestion_CtgProducto.id_ctgp;

        vista_ctgp.setVisible(true);
        vista_ctgp.setTitle("Actualizar Rutina - Nexo Gym");
        vista_ctgp.setResizable(false);
        vista_ctgp.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista_ctgp.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista_ctgp.getHeight()) / 2);
        vista_ctgp.setClosable(true);
        vista_ctgp.setIconifiable(true);
        vista_ctgp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosCtgProducto();

    }

    public void funcionalidad() {
        /* validaciones */
        vista_ctgp.getTxt_nombre().addKeyListener(new VCampoParticular(vista_ctgp.getTxt_nombre(), 17));
        vista_ctgp.getTxt_descripcion().addKeyListener(new VCampoParticular(vista_ctgp.getTxt_descripcion(), 45));

        vista_ctgp.getTxt_nombre().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarCategoria(vista_ctgp.getTxt_nombre().getText());
            }

        });
        vista_ctgp.getBt_actualizar().addActionListener(l -> actualizarCtgProducto());

    }

    private void cargarDatosCtgProducto() {

        modelo_ctgp.mostrarDatosJoin(id_ctgp).stream().forEach((us) -> {

            vista_ctgp.getTxt_nombre().setText(us.getNombre());
            vista_ctgp.getTxt_descripcion().setText(us.getDescripcion());

        });

    }

    private boolean validarRegistro() {

        String nombre = vista_ctgp.getTxt_nombre().getText();
        String descripcion = vista_ctgp.getTxt_descripcion().getText();

        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private boolean verificarCtgProducto(String nombre) {

        boolean busqueda = Validar_Categoria_Nueva();
        if (!nombre.isEmpty()) {
            if (busqueda) {
                vista_ctgp.getTxt_nombre().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            } else {
                vista_ctgp.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            }
            return busqueda;

        } else {
            vista_ctgp.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            return false;
        }
    }

    private void sentenciaUpdate() {

        String nombre = vista_ctgp.getTxt_nombre().getText().trim();
        String descripcion = vista_ctgp.getTxt_descripcion().getText().trim();

        modelo_ctgp.setNombre(nombre);
        modelo_ctgp.setDescripcion(descripcion);

        if (modelo_ctgp.modificar1(id_ctgp)) {

            JOptionPane.showMessageDialog(vista_ctgp, "Rutina Actualizada");

        } else {
            JOptionPane.showMessageDialog(vista_ctgp, "Error al Actualizar");
        }

    }

    private void restaurarBordes() {

        vista_ctgp.getTxt_nombre().setBorder(origin_border);
    }

    private void actualizarCtgProducto() {

        if (validarRegistro()) {

            if (verificarCtgProducto(vista_ctgp.getTxt_nombre().getText())) {

                sentenciaUpdate();
                restaurarBordes();

            } else {
                JOptionPane.showMessageDialog(vista_ctgp, " Categoría no disponible", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista_ctgp.getTxt_nombre().grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(vista_ctgp, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

    private int verificarCategoria(String nombre) {

        int cont = modelo_ctgp.contadorCtg(nombre);
//        System.out.println(cont);
        return cont;

    }

    private int campoId_Categoria(String nombre) {

        int id_ctg;
        id_ctg = modelo_ctgp.mostrarDatosJoin(nombre).stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findAny().get().getId_ctgp();
        return id_ctg;

    }

    private boolean Validar_Categoria_Nueva() {

        String nombre2 = vista_ctgp.getTxt_nombre().getText();

        if (verificarCategoria(nombre2) >= 1) {
            int id1 = campoId_Categoria(nombre2);
            if (id1 == id_ctgp) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }

}
