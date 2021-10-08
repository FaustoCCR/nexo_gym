package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Persona;

public class ControlRegistrar_Persona {

    private VistaRegistrar_Persona vista_persona;
    private PersonaDao modelo_persona;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlRegistrar_Persona(PersonaDao modelo_persona, VistaRegistrar_Persona vista) {
        this.modelo_persona = modelo_persona;
        this.vista_persona = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Persona - Nexo Gym");
        vista.setResizable(false);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista.setClosable(true);

        vista.getJdnacimiento().getDateEditor().setEnabled(false);
        vista.getJdnacimiento().setDate(new java.util.Date());

    }

    public void funcionalidad() {

        vista_persona.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarCedulaIngresada(vista_persona.getTxt_cedula().getText());
            }

        });

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
        if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || vista_persona.getJdnacimiento().getDate() == null
                || correo.isEmpty() || (!vista_persona.getRbt_masculino().isSelected() && !vista_persona.getRbt_femenino().isSelected())
                || direccion.isEmpty() || telefono.isEmpty()) {

            validacion = false;

        }

        return validacion;

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

    private int campoId() {
        List<PersonaVo> lista = modelo_persona.mostrarDatos();
        int id = 1;
        for (int i = 0; i < lista.size(); i++) {
            if (id != lista.get(i).getId_persona()) {
                break;
            }
            id++;
        }
        return id;
    }

    private void sentenciaInsert() {
        int id_persona = campoId();
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

        modelo_persona.setId_persona(id_persona);
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
            if (verificarCedulaIngresada(vista_persona.getTxt_cedula().getText())) {
                if (verificarCedula(vista_persona.getTxt_cedula().getText())) {

                    sentenciaInsert();
                    reiniciarCampos();
                    restaurarBordes();

                } else {
                    JOptionPane.showMessageDialog(vista_persona, "Exite un registro con esta cédula", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    vista_persona.getTxt_cedula().grabFocus();
                }
            } else {
                JOptionPane.showMessageDialog(vista_persona, "La cédula es Incorrecta", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(vista_persona, "Rellenar todos los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void restaurarBordes() {

        vista_persona.getTxt_cedula().setBorder(origin_border);
    }

    //--------- Metodos referente a la cédula -----
    private boolean verificarCedulaIngresada(String cedula) {

        /*Si la cedula tiene 10 caracteres
            y verificar que sea correcta la cedula*/
        boolean respuesta = longitudCedula(cedula);
        if (respuesta) {

            vista_persona.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
        } else {
            vista_persona.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));

        }
        return respuesta;

    }

    private boolean longitudCedula(String cedula) {
        int log = cedula.length();
        if (log >= 10) {
            vista_persona.getTxt_cedula().setText(cedula.substring(0, 10));
            cedula = vista_persona.getTxt_cedula().getText();
            return validadorCedulaEcua(cedula);
        }

        return false;

    }

    private boolean verificarCedula(String cedula) {

        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);

        return modelo_persona.mostrarDatos().stream().noneMatch(cedula_p);

    }

    private boolean validadorCedulaEcua(String document) {
        byte sum = 0;
        document = document.substring(0, 10);
        try {
            if (document.trim().length() != 10) {
                return false;
            }
            String[] data = document.split("");
            byte verifier = Byte.parseByte(data[0] + data[1]);
            if (verifier < 1 || verifier > 24) {
                return false;
            }
            byte[] digits = new byte[data.length];
            for (byte i = 0; i < digits.length; i++) {
                digits[i] = Byte.parseByte(data[i]);
            }
            if (digits[2] > 6) {
                return false;
            }
            for (byte i = 0; i < digits.length - 1; i++) {
                if (i % 2 == 0) {
                    verifier = (byte) (digits[i] * 2);
                    if (verifier > 9) {
                        verifier = (byte) (verifier - 9);
                    }
                } else {
                    verifier = (byte) (digits[i] * 1);
                }
                sum = (byte) (sum + verifier);
            }
            if ((sum - (sum % 10) + 10 - sum) == digits[9]) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
