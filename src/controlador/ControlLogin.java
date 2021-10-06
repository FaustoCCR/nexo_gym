package controlador;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDao;
import modelo.vo.UsuarioVo;
import vista.VistaAdministrador;
import vista.VistaLogin;

public class ControlLogin {

    //objetos de la vista y controlador
    private UsuarioDao modelo;
    private VistaLogin vista;
    public static int id_user;

    public ControlLogin(UsuarioDao modelo, VistaLogin vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Login");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);

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

        if (!user.isEmpty() && !password.isEmpty()) {

            Predicate<UsuarioVo> checkuser = u -> u.getUser_name().equals(user);
            Predicate<UsuarioVo> checkpass = u -> u.getPassword().equals(password);

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
                            vista.dispose();
                            ventanaAdmin();

                        } else if (t.getId_rol() == 2) {
                            System.out.println("Bienvenido Recepcionista");
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

    public void ventanaAdmin() {

        VistaAdministrador vadmin = new VistaAdministrador();
        ControlVista_Admin ctadmin = new ControlVista_Admin(vadmin);
        ctadmin.funcionalidad();
    }

}
