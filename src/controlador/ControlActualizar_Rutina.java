package controlador;

import controlador.validaciones.VCampoParticular;
import controlador.validaciones.VTxtArea;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.RutinaDao;
import vista.VistaActualizar_Rutina;
import vista.VistaAdministrador;

public class ControlActualizar_Rutina {

    private RutinaDao modelo_rutina;
    private VistaActualizar_Rutina vista_rutina;
    private int id_rutina;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_Rutina(RutinaDao modelo_rutina, VistaActualizar_Rutina vista_rutina) {
        this.modelo_rutina = modelo_rutina;
        this.vista_rutina = vista_rutina;

        id_rutina = ControlGestion_Rutina.id_rutina;

        vista_rutina.setVisible(true);
        vista_rutina.setTitle("Actualizar Rutina - Nexo Gym");
        vista_rutina.setResizable(false);
        vista_rutina.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista_rutina.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista_rutina.getHeight()) / 2);
        vista_rutina.setClosable(true);
        vista_rutina.setIconifiable(true);
        vista_rutina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosRutina();

    }

    public void funcionalidad() {
        /*-- validaciones --*/
        vista_rutina.getTxt_nombre().addKeyListener(new VCampoParticular(vista_rutina.getTxt_nombre(), 17));
        vista_rutina.getTxt_descripcion().addKeyListener(new VTxtArea(vista_rutina.getTxt_descripcion(), 95));

        vista_rutina.getTxt_nombre().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificRutina(vista_rutina.getTxt_nombre().getText());
            }

        });
        vista_rutina.getBt_actualizar().addActionListener(l -> actualizarRutina());

    }

    private void cargarDatosRutina() {

        modelo_rutina.mostrarDatosJoin(id_rutina).stream().forEach((us) -> {

            vista_rutina.getTxt_nombre().setText(us.getNombre());
            vista_rutina.getTxt_descripcion().setText(us.getDescripcion());

        });

    }

    private boolean validarRegistro() {

        String nombre = vista_rutina.getTxt_nombre().getText();
        String descripcion = vista_rutina.getTxt_descripcion().getText();

        boolean validacion = true;

        if (nombre.isEmpty() || descripcion.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private boolean verificarRutina(String nombre) {

        boolean busqueda = Validar_Rutina_Nueva();
        if (!nombre.isEmpty()) {
            if (busqueda) {
                vista_rutina.getTxt_nombre().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            } else {
                vista_rutina.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            }
            return busqueda;

        } else {
            vista_rutina.getTxt_nombre().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            return false;
        }
    }

    private void sentenciaUpdate() {

        String rutina = vista_rutina.getTxt_nombre().getText().trim();
        String descripcion = vista_rutina.getTxt_descripcion().getText().trim();

        modelo_rutina.setNombre(rutina);
        modelo_rutina.setDescripcion(descripcion);

        if (modelo_rutina.modificar1(id_rutina)) {

            JOptionPane.showMessageDialog(vista_rutina, "Rutina Actualizada");

        } else {
            JOptionPane.showMessageDialog(vista_rutina, "Error al Actualizar");
        }

    }

    private void restaurarBordes() {

        vista_rutina.getTxt_nombre().setBorder(origin_border);
    }

    private void actualizarRutina() {

        if (validarRegistro()) {

            if (verificarRutina(vista_rutina.getTxt_nombre().getText())) {

                sentenciaUpdate();
                restaurarBordes();

            } else {
                JOptionPane.showMessageDialog(vista_rutina, " Rutina no disponible", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista_rutina.getTxt_nombre().grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(vista_rutina, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

    private int verificRutina(String nombre) {

        int cont = modelo_rutina.contadorPer(nombre);
//        System.out.println(cont);
        return cont;

    }

    private int campoId_Rutina(String nombre) {

        int id_rutina;
        id_rutina = modelo_rutina.mostrarDatos().stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findAny().get().getId_rutina();
        return id_rutina;

    }

    private boolean Validar_Rutina_Nueva() {

        String nombre2 = vista_rutina.getTxt_nombre().getText();

        if (verificRutina(nombre2) >= 1) {
            int id1 = campoId_Rutina(nombre2);
            if (id1 == id_rutina) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }
}
