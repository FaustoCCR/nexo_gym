package controlador;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.dao.ProveedorDao;
import vista.VistaRegistrar_Proveedor;

public class ControlRegistrar_Proveedor {

    private ProveedorDao modelo;
    private VistaRegistrar_Proveedor vista;

    public ControlRegistrar_Proveedor(ProveedorDao modelo, VistaRegistrar_Proveedor vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Proveedores - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void funcionalidad() {

        vista.getBt_registrar().addActionListener(l -> registrarProveedor());

    }

    private boolean validarRegistro() {
        
        String ruc = vista.getTxt_ruc().getText();
        String nombre = vista.getTxt_nombre().getText();
        String contacto = vista.getTxt_contacto().getText();

        boolean validacion = true;
        if (ruc.isEmpty() || nombre.isEmpty() || contacto.isEmpty()) {

            validacion = false;

        }

        return validacion;
    }

    private boolean verificarRUC() {

        return modelo.mostrarDatos().stream().noneMatch(pv -> pv.getId_proveedor().equalsIgnoreCase(vista.getTxt_ruc().getText()));
    }

    private void sentenciaInsert() {
        
        String ruc = vista.getTxt_ruc().getText();
        String nombre = vista.getTxt_nombre().getText().trim();
        String contacto = vista.getTxt_contacto().getText().trim();
        
        modelo.setId_proveedor(ruc);
        modelo.setNombre(nombre);
        modelo.setContacto(contacto);

        if (modelo.insertar()) {
            JOptionPane.showMessageDialog(vista, "Proveedor Registrado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }
    }

    private void reiniciarCampos() {
       
        vista.getTxt_ruc().setText("");
        vista.getTxt_nombre().setText("");
        vista.getTxt_contacto().setText("");

    }

    private void registrarProveedor() {

        if (validarRegistro()) {

            if (verificarRUC()) {

                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista, "Proveedor ya registrado", "Advertencia", JOptionPane.ERROR_MESSAGE);
                vista.getTxt_ruc().grabFocus();
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

}
