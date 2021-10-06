package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.PersonaDao;
import modelo.dao.RolDao;
import modelo.dao.UsuarioDao;
import modelo.vo.PersonaVo;
import modelo.vo.UsuarioVo;
import vista.VistaActualizar_Usuario;

public class ControlActualizar_User {

    private UsuarioDao modelo_user;
    private VistaActualizar_Usuario vista;

    private PersonaDao modelo_persona = new PersonaDao();
    private RolDao modelo_rol = new RolDao();
    private int id_user;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_User(UsuarioDao modelo_user, VistaActualizar_Usuario vista) {
        this.modelo_user = modelo_user;
        this.vista = vista;

        id_user = ControlGestion_Users.id_user;

        vista.setVisible(true);
        vista.setTitle("Actualizar Usuario - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarEstados();
        cargarRoles();
        cargarDatosUser();
    }

    public void funcionalidad() {

        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarPersona(vista.getTxt_cedula().getText());
            }

        });
        vista.getTxt_user().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarUserName(vista.getTxt_user().getText(), id_user);
            }

        });

        vista.getBt_actualizar().addActionListener(l -> actualizarUsuario());
        vista.getBt_restaurarpass().addActionListener(l -> mostrarJDialog());

        /* -------------JDialog ---------------------*/
        JPasswordField[] claves = {vista.getJpass(), vista.getJconfirm_pass()};

        for (JPasswordField clave : claves) {
            clave.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    compararPassword();
                }
            });

        }
        vista.getBt_cambiarpass().addActionListener(l -> cambiarPassword());

    }

    private void cargarDatosUser() {

        modelo_user.mostrarDatosJoin(id_user).stream().forEach((us) -> {

            vista.getTxt_cedula().setText(us.getCedula());
            vista.getTxt_persona().setText(us.getNombres_usuario());
            vista.getCb_rol().setSelectedItem(us.getNombre_rol());
            vista.getTxt_user().setText(us.getUser_name());
            vista.getCb_estado().setSelectedItem(us.getEstado());

        });

    }

    private void cargarRoles() {

        modelo_rol.mostrarDatos().stream()
                .forEach((t) -> {
                    vista.getCb_rol().addItem(t.getNombre());
                });

    }

    private void cargarEstados() {
        vista.getCb_estado().addItem("Activo");
        vista.getCb_estado().addItem("Inactivo");

    }

    private boolean verificarPersonaRegistrada(String cedula, int id) {

        /*Si ya exite una persona con la cedula 
        indicada, ya no se puede registrar*/
//        Predicate<UsuarioVo> usuario_comparar = u -> u.getCedula().equals(cedula);
//        boolean respuesta = modelo_user.mostrarDatosJoin("").stream().noneMatch(usuario_comparar.and(u -> u.getId_user()!=id));
        boolean respuesta = modelo_user.mostrarDatosJoinDNI(cedula, id_user).isEmpty();
        if (respuesta) {

            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
        } else {
            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));

        }
        return respuesta;

    }

    private boolean buscarPersona(String cedula) {

        boolean busqueda;
        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);

        busqueda = modelo_persona.mostrarDatos().stream().anyMatch(cedula_p);

        if (busqueda == true) {

//            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            modelo_persona.mostrarDatos().stream().filter(cedula_p).forEach((t) -> {
                vista.getTxt_persona().setText(t.getNombre() + " " + t.getApellido());

            });
            verificarPersonaRegistrada(cedula, id_user);
        } else {

            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxt_persona().setText("");

        }

        return busqueda;

    }

    private boolean validarRegistro() {

        String persona = vista.getTxt_cedula().getText();
        String usuario = vista.getTxt_user().getText();

        boolean validacion = true;

        if (persona.isEmpty() || usuario.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private int campoPersona(String cedula) {

        /*Retora el id de la persona de acuerdo a su número de cédula*/
        int id_persona = modelo_persona.mostrarDatos().stream().filter(p -> p.getDni().equals(cedula)).findAny().get().getId_persona();

        return id_persona;

    }

    private boolean verificarUserName(String username, int id) {

        /*Pregunta si existe un nombre de usuario igual al que coloquemos que pertenezca a otro usuario(user_id) */
        Predicate<UsuarioVo> usuario_comparar = u -> u.getUser_name().equals(username) && u.getId_user() != id;

        boolean busqueda = modelo_user.mostrarDatos().stream().noneMatch(usuario_comparar);
        if (!username.isEmpty()) {
            if (busqueda) {
                vista.getTxt_user().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            } else {
                vista.getTxt_user().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            }
            return busqueda;

        } else {
            vista.getTxt_user().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            return false;
        }

    }

    private boolean actualizarEstado() {

        String estado = vista.getCb_estado().getSelectedItem().toString();
        if (estado == "Activo") {
            return true;

        } else {
            return false;
        }
    }

    private void sentenciaUpdate() {

        int id_persona = campoPersona(vista.getTxt_cedula().getText());
        int id_rol = modelo_rol.mostrarDatos().get(vista.getCb_rol().getSelectedIndex()).getId_rol();
        String user = vista.getTxt_user().getText();
        boolean estado = actualizarEstado();

        modelo_user.setId_persona(id_persona);
        modelo_user.setId_rol(id_rol);
        modelo_user.setUser_name(user);
        modelo_user.setEstado_cuenta(estado);

        if (modelo_user.modificar1(id_user)) {

            JOptionPane.showMessageDialog(vista, "Usuario Actualizado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Actualizar");
        }

    }

    private void restaurarBordes() {

        vista.getTxt_cedula().setBorder(origin_border);
        vista.getTxt_user().setBorder(origin_border);
    }

    private void actualizarUsuario() {

        if (validarRegistro()) {

            if (buscarPersona(vista.getTxt_cedula().getText())) {

                if (verificarPersonaRegistrada(vista.getTxt_cedula().getText(), id_user)) {

                    if (verificarUserName(vista.getTxt_user().getText(), id_user)) {

                        sentenciaUpdate();
                        restaurarBordes();

                    } else {
                        JOptionPane.showMessageDialog(vista, "Nombre de usuario no disponible", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        vista.getTxt_user().grabFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(vista, "Ya se encuentra registrada esta persona", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    vista.getTxt_cedula().grabFocus();
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Cédula no identificada", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*------------------------ JDialog -------------------------------- */
    private void mostrarJDialog() {

        vista.getJdialog_Passwords().setSize(455, 375);
        vista.getJdialog_Passwords().setVisible(true);
        vista.getJdialog_Passwords().setTitle("Restaurar Contraseñas - Nexo Gym");
        vista.getJdialog_Passwords().setResizable(false);
        vista.getJdialog_Passwords().setLocationRelativeTo(vista);

    }

    private boolean compararPassword() {

        String password = String.valueOf(vista.getJpass().getPassword());
        String confirm_password = String.valueOf(vista.getJconfirm_pass().getPassword());

        if (!password.isEmpty()) {

            if (confirm_password.equals(password)) {
                vista.getJconfirm_pass().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));

                return true;

            } else {
                vista.getJconfirm_pass().setBorder(new LineBorder(Color.decode("#C33529"), 2));
                return false;
            }

        } else {
            vista.getJconfirm_pass().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            return false;
        }

    }

    private void reiniciarJPasswords() {

        vista.getJpass().setText("");
        vista.getJconfirm_pass().setText("");
        vista.getJconfirm_pass().setBorder(origin_border);

    }

    private void upadatePassword() {

        String password = String.valueOf(vista.getJpass().getPassword());

        modelo_user.setPassword(password);

        if (modelo_user.modificarPassword(id_user)) {

            JOptionPane.showMessageDialog(vista.getJdialog_Passwords(), "Constraseña actualizada");

        } else {
            JOptionPane.showMessageDialog(vista.getJdialog_Passwords(), "Error al Actualizar");
        }
    }

    private void cambiarPassword() {

        if (!String.valueOf(vista.getJpass().getPassword()).isEmpty()) {

            if (compararPassword()) {

                upadatePassword();
                reiniciarJPasswords();
                vista.getJdialog_Passwords().dispose();

            } else {
                JOptionPane.showMessageDialog(vista.getJdialog_Passwords(), "Revise la contraseña", "Mensaje", JOptionPane.ERROR_MESSAGE);
                vista.getJpass().grabFocus();
            }

        } else {

            JOptionPane.showMessageDialog(vista.getJdialog_Passwords(), "Contraseña vacía", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
