package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import modelo.dao.PersonaDao;
import modelo.dao.RolDao;
import modelo.dao.UsuarioDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Usuario;

public class ControlRegistrar_Usuario {

    private UsuarioDao modelo_usuario;
    private VistaRegistrar_Usuario vista;
    private PersonaDao modelo_persona = new PersonaDao();
    private RolDao modelo_rol = new RolDao();
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlRegistrar_Usuario(UsuarioDao modelo_usuario, VistaRegistrar_Usuario vista) {
        this.modelo_usuario = modelo_usuario;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Usuarios - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        cargarRoles();

    }

    public void funcionalidad() {

        vista.getBt_check().addActionListener(l -> buscarPersona(vista.getTxt_cedula().getText()));

        JPasswordField[] claves = {vista.getJpass(), vista.getJconfirm_pass()};

        for (JPasswordField clave : claves) {
            clave.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    compararPassword();
                }
            });

        }
        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarPersona(vista.getTxt_cedula().getText());
            }
            
        });
        

        vista.getBt_registrar().addActionListener(l -> registrarUsuario());

    }

    private boolean buscarPersona(String cedula) {

        boolean busqueda;
//        if (!cedula.isEmpty()) {
            Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);

            busqueda = modelo_persona.mostrarDatos().stream().anyMatch(cedula_p);

            if (busqueda == true) {
                System.out.println("Cedula Correcta");
                vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
                modelo_persona.mostrarDatos().stream().filter(cedula_p).forEach((t) -> {
                    vista.getTxt_persona().setText(t.getNombre() + " " + t.getApellido());

                });
            } else {
                System.out.println("Cedula Incorrecta");
                vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
                vista.getTxt_persona().setText("");

            }

//        } else {
//
//            busqueda = false;
//            JOptionPane.showMessageDialog(vista, "Ingrese la cédula");
//            System.out.println("Ingrese la cédula");
//            vista.getTxt_cedula().setBorder(vista.getTxt_user().getBorder());
//        }

        return busqueda;

    }

    private boolean compararPassword() {

        String password = String.valueOf(vista.getJpass().getPassword());
        String confirm_password = String.valueOf(vista.getJconfirm_pass().getPassword());

        if (confirm_password.equals(password)) {

            System.out.println("Contraseñas coinciden");
            vista.getJconfirm_pass().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));

            return true;

        } else {
            vista.getJconfirm_pass().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            System.out.println("No conciden las contraseñas");
            return false;
        }

    }

    private void cargarRoles() {

        modelo_rol.mostrarDatos().stream()
                .forEach((t) -> {
                    vista.getCb_rol().addItem(t.getNombre());
                });

    }

    private boolean validarRegistro() {

        String persona = vista.getTxt_cedula().getText();
        String usuario = vista.getTxt_user().getText();
        String password = String.valueOf(vista.getJpass().getPassword());
        String confirm_password = String.valueOf(vista.getJconfirm_pass().getPassword());

        boolean validacion = true;

        if (persona.isEmpty() || usuario.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private int campoPersona(String cedula) {

        /*Retora el id de la persona de acuerdo a su número de cédula*/
        int id_persona = modelo_persona.mostrarDatos().stream().findAny().filter(p -> p.getDni().equals(cedula)).get().getId_persona();

        return id_persona;

    }

    private void sentenciaInsert() {

        int id_persona = campoPersona(vista.getTxt_cedula().getText());
        int id_rol = modelo_rol.mostrarDatos().get(vista.getCb_rol().getSelectedIndex()).getId_rol();
        String user = vista.getTxt_user().getText();
        String password = String.valueOf(vista.getJpass().getPassword());
        boolean estado_cuenta = true;
        /* cuando se registra, queda habilitado */

        modelo_usuario.setId_persona(id_persona);
        modelo_usuario.setId_rol(id_rol);
        modelo_usuario.setUser_name(user);
        modelo_usuario.setPassword(password);
        modelo_usuario.setEstado_cuenta(estado_cuenta);

        if (modelo_usuario.insertar()) {

            JOptionPane.showMessageDialog(vista, "Usuario Registrado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }

    }

    private void reiniciarCampos() {

        vista.getTxt_cedula().setText("");
        vista.getCb_rol().setSelectedIndex(0);
        vista.getTxt_user().setText("");
        vista.getJpass().setText("");
        vista.getJconfirm_pass().setText("");

        vista.getTxt_cedula().setBorder(origin_border);
        vista.getJconfirm_pass().setBorder(origin_border);

    }

    private boolean usuarioMatch() {

        String user = vista.getTxt_user().getText();
        /*si ninguno de la lista, tiene el mismo nombre de usuario = true ,sino = false*/
        boolean respuesta = modelo_usuario.mostrarDatos().stream().noneMatch(u -> u.getUser_name().equals(user));
        return respuesta;
    }

    private void registrarUsuario() {

        boolean validacion = validarRegistro();

        if (validacion) {
            boolean contrasenias = compararPassword();

            if (buscarPersona(vista.getTxt_cedula().getText())) {

                if (contrasenias) {
                    if (usuarioMatch()) {

                        sentenciaInsert();
                        reiniciarCampos();

                    } else {
                        JOptionPane.showMessageDialog(vista, "Nombre de usuario no disponible", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        vista.getTxt_cedula().grabFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(vista, "No conciden las contraseñas", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Cedula no identificada", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}