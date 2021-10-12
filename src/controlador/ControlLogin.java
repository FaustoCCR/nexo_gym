package controlador;

import controlador.cifrado.Hash;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDao;
import modelo.vo.UsuarioVo;
import vista.VistaAdministrador;
import vista.VistaLogin;
import vista.placeholder.TextPrompt;

public class ControlLogin {

    //objetos de la vista y controlador
    private UsuarioDao modelo;
    private VistaLogin vista;
    public static int id_user;
    private TextPrompt placeholder;
    public static int permiso;

    public ControlLogin(UsuarioDao modelo, VistaLogin vista) {
        this.modelo = modelo;
        this.vista = vista;

        //--colacion del Icono
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("vista/img/logo83.png"));
        vista.setIconImage(ico);

        vista.setVisible(true);
        vista.setTitle("Login");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        placeholder = new TextPrompt("Nombre de usuario", vista.getTxt_user());
        placeholder = new TextPrompt("Contraseña", vista.getJpassword());

    }

    public void funcionalidad() {

        vista.getBt_ingresar()
                .addActionListener(l -> autenticacionUsusario(vista.getTxt_user().getText(), vista.getJpassword().getPassword()));
        vista.getJpassword()
                .addActionListener(l -> autenticacionUsusario(vista.getTxt_user().getText(), vista.getJpassword().getPassword()));

    }

    private void autenticacionUsusario(String user, char pass[]) {

        /*Usar un metodo stream con un lambda que verifique los datos
        o realizar una consulta sql mas especifica
         */
        String password = String.valueOf(pass);
        String password_cifrada = Hash.sha1(password);

        if (!user.isEmpty() && !password.isEmpty()) {

            Predicate<UsuarioVo> checkuser = u -> u.getUser_name().equals(user);
            Predicate<UsuarioVo> checkpass = u -> u.getPassword().equals(password_cifrada);

            List<UsuarioVo> filtro_login
                    = modelo.mostrarDatos().stream()
                            .filter(checkuser.and(checkpass))
                            .collect(Collectors.toList());

            if (!filtro_login.isEmpty()) {

                Predicate<UsuarioVo> checkestado = u -> u.isEstado_cuenta() == true;
                boolean estado = filtro_login.stream().anyMatch(checkestado);

                if (estado) {

                    id_user = filtro_login.stream().filter(checkpass).findAny().get().getId_user();

                    filtro_login.stream().forEach((t) -> {
                        if (t.getId_rol() == 1) {

                            System.out.println("Bienvenido Administrador");
                            permiso = 1;
                            vista.dispose();
                            ventanaGestion();

                        } else if (t.getId_rol() == 2) {
                            permiso = 2;
                            System.out.println("Bienvenido Recepcionista");
                            vista.dispose();
                            ventanaGestion();
                        } else if (t.getId_rol() == 3) {
                            permiso = 3;
                            vista.dispose();
                            System.out.println("Bienvenido Instructor");
                            ventanaGestion();
                        }
                    });

                } else {
                    JOptionPane.showMessageDialog(vista, "Su cuenta se encuentra inhabilitada", "Advertencia", 0);
                    vista.getTxt_user().setText("");
                    vista.getJpassword().setText("");
                }

            } else {

                JOptionPane.showMessageDialog(vista, "Datos de acceso incorrectos", "Advertencia", 0);
                vista.getTxt_user().grabFocus();
                vista.getTxt_user().setText("");
                vista.getJpassword().setText("");
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Aviso", 1);
        }

    }

    private void ventanaGestion() {

        VistaAdministrador vadmin = new VistaAdministrador();
        ControlVista_Admin ctadmin = new ControlVista_Admin(vadmin);
        ctadmin.funcionalidad();
    }

}
