package controlador;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Persona;

public class ControlRegistrar_Persona {

    private VistaRegistrar_Persona vista_persona;
    private PersonaDao modelo_persona;

    public ControlRegistrar_Persona(PersonaDao modelo_persona, VistaRegistrar_Persona vista) {
        this.modelo_persona = modelo_persona;
        this.vista_persona = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Persona - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        vista.getJdnacimiento().getDateEditor().setEnabled(false);

    }

    public void funcionalidad() {

        vista_persona.getBt_registrar().addActionListener(l -> registrarPersona());

    }

    private boolean validarRegistro() {

        String cedula = vista_persona.getTxt_cedula().getText();
        String nombre = vista_persona.getTxt_nombre().getText();
        String apellido = vista_persona.getTxt_apellido().getText();
        String correo = vista_persona.getTxt_correo().getText();
        String direccion = vista_persona.getTxt_direccion().getText();
        String telefono = vista_persona.getTxt_telefono().getText();

        boolean validacion = true;
        if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || vista_persona.getJdnacimiento().getDate()==null
                || correo.isEmpty() || (!vista_persona.getRbt_masculino().isSelected() && !vista_persona.getRbt_femenino().isSelected())
                || direccion.isEmpty() || telefono.isEmpty()) {

            validacion = false;

        }

        return validacion;

    }

    private boolean verificarCedula(String cedula) {

        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);

        return modelo_persona.mostrarDatos().stream().noneMatch(cedula_p);

    }

    private String getSelectedbtgenero(ButtonGroup bt_genero) {
        for (Enumeration<AbstractButton> buttons = bt_genero.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    private void reiniciarCampos() {

        vista_persona.getTxt_cedula().setText("");
        vista_persona.getTxt_nombre().setText("");
        vista_persona.getTxt_apellido().setText("");
        vista_persona.getJdnacimiento().setDate(null);
        vista_persona.getTxt_correo().setText("");
        vista_persona.getBt_genero().clearSelection();
        vista_persona.getTxt_direccion().setText("");
        vista_persona.getTxt_telefono().setText("");

    }

    private void sentenciaInsert() {

        String cedula = vista_persona.getTxt_cedula().getText();
        String nombre = vista_persona.getTxt_nombre().getText();
        String apellido = vista_persona.getTxt_apellido().getText();

        //------------------------- FECHA -----------------------------//
        Instant instant = vista_persona.getJdnacimiento().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());
        //-------------------------      -----------------------------//
        String correo = vista_persona.getTxt_correo().getText();
        char genero = getSelectedbtgenero(vista_persona.getBt_genero()).charAt(0);
        String direccion = vista_persona.getTxt_direccion().getText();
        String telefono = vista_persona.getTxt_telefono().getText();

        modelo_persona.setDni(cedula);
        modelo_persona.setNombre(nombre);
        modelo_persona.setApellido(apellido);
        modelo_persona.setBirthdate(fecha);
        modelo_persona.setCorreo(correo);
        modelo_persona.setGenero(genero);
        modelo_persona.setDireccion(direccion);
        modelo_persona.setTelefono(telefono);

        if (modelo_persona.grabar()) {

            JOptionPane.showMessageDialog(vista_persona, "Persona Registrada");

        } else {
            JOptionPane.showMessageDialog(vista_persona, "Error al Guardar");
        }

    }

    private void registrarPersona() {

        if (validarRegistro()) {

            if (verificarCedula(vista_persona.getTxt_cedula().getText())) {

                sentenciaInsert();
                reiniciarCampos();

            } else {
                JOptionPane.showMessageDialog(vista_persona, "Exite un registro con esta cédula", "Mensaje", JOptionPane.ERROR_MESSAGE);
                vista_persona.getTxt_cedula().grabFocus();
            }

        } else {

            JOptionPane.showMessageDialog(vista_persona, "Rellenar todos los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Para despues validar la cedula... y tambien el corre
//    public boolean validadorDeCedula(String cedula) {
//        boolean cedulaCorrecta = false;
//
//        try {
//
//            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
//            {
//                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
//                if (tercerDigito < 6) {
//// Coeficientes de validación cédula
//// El decimo digito se lo considera dígito verificador
//                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
//                    int verificador = Integer.parseInt(cedula.substring(9, 10));
//                    int suma = 0;
//                    int digito = 0;
//                    for (int i = 0; i < (cedula.length() - 1); i++) {
//                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
//                        suma += ((digito % 10) + (digito / 10));
//                    }
//
//                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
//                        cedulaCorrecta = true;
//                    } else if ((10 - (suma % 10)) == verificador) {
//                        cedulaCorrecta = true;
//                    } else {
//                        cedulaCorrecta = false;
//                    }
//                } else {
//                    cedulaCorrecta = false;
//                }
//            } else {
//                cedulaCorrecta = false;
//            }
//        } catch (NumberFormatException nfe) {
//            cedulaCorrecta = false;
//        } catch (Exception err) {
//            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
//            cedulaCorrecta = false;
//        }
//
//        if (!cedulaCorrecta) {
//            System.out.println("La Cédula ingresada es Incorrecta");
//        }
//        return cedulaCorrecta;
//    }
}
