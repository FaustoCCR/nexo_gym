package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.CargoDao;
import modelo.dao.EmpleadoDao;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Empleado;

public class ControlRegistrar_Empleado {

    private EmpleadoDao modelo;
    private VistaRegistrar_Empleado vista;
    private PersonaDao modelo_persona = new PersonaDao();
    private CargoDao modelo_cargo = new CargoDao();
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlRegistrar_Empleado(EmpleadoDao modelo, VistaRegistrar_Empleado vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setTitle("Registro de Empleados - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista.getJd_fechacontrato().getDateEditor().setEnabled(false);
        vista.getJd_fechacontrato().setDate(new java.util.Date());

        cargarCargo();

    }

    public void funcionalidad() {
        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarPersona(vista.getTxt_cedula().getText());
            }

        });
        vista.getBt_registrar().addActionListener(l -> registrarEmpleado());

    }

    private boolean verificarPersonaRegistrada(String cedula) {

        /*Si ya exite una persona con la cedula 
        indicada, ya no se puede registrar*/
        boolean respuesta = modelo.mostrarDatosJoin("").stream().noneMatch(c -> c.getDni_persona().equals(cedula));
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

            modelo_persona.mostrarDatos().stream().filter(cedula_p).forEach((t) -> {
                vista.getTxt_persona().setText(t.getNombre() + " " + t.getApellido());

            });
            verificarPersonaRegistrada(cedula);
        } else {

            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxt_persona().setText("");
        }
        return busqueda;
    }

    private void cargarCargo() {
        modelo_cargo.mostrarDatos().stream().forEach((m) -> {
            vista.getCb_cargo().addItem(m.getNombre());
        });

    }

    private boolean validarRegistro() {

        String cedula = vista.getTxt_cedula().getText();
        String sueldo = vista.getTxtSueldo().getText();

        boolean validacion = true;

        if (cedula.isEmpty() || sueldo.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private int VerificarCedula(String cedula) {
        int id_persona = modelo_persona.mostrarDatos().stream().filter(persona -> persona.getDni().equals(cedula)).findAny().get().getId_persona();
        return id_persona;
    }

    private void sentenciaInsert() {

        int idpersona = VerificarCedula(vista.getTxt_cedula().getText());
        int id_cargo = modelo_cargo.mostrarDatos().get(vista.getCb_cargo().getSelectedIndex()).getId_cargo();
        Instant instant = vista.getJd_fechacontrato().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());
        Double sueldo = Double.valueOf(vista.getTxtSueldo().getText());

        modelo.setId_persona(idpersona);
        modelo.setId_cargo(id_cargo);
        modelo.setFecha_contrato(fecha);
        modelo.setSueldo(sueldo);

        if (modelo.grabar()) {
            JOptionPane.showMessageDialog(vista, "Empleado Registrado");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }

    }

    private void reiniciarCampos() {

        vista.getTxt_cedula().setText("");
        vista.getTxt_persona().setText("");
        vista.getCb_cargo().setSelectedIndex(0);
        vista.getJd_fechacontrato().setDate(new java.util.Date());
        vista.getTxtSueldo().setText("");

        vista.getTxt_cedula().setBorder(origin_border);

    }

    private void registrarEmpleado() {

        if (validarRegistro()) {

            if (buscarPersona(vista.getTxt_cedula().getText())) {

                if (verificarPersonaRegistrada(vista.getTxt_cedula().getText())) {

                    sentenciaInsert();
                    reiniciarCampos();

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

}
