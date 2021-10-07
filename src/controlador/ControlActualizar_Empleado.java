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
import vista.VistaActualizar_Empleado;

public class ControlActualizar_Empleado {

    private EmpleadoDao modelo_empleado;
    private CargoDao modelo_cargo = new CargoDao();
    private PersonaDao modelo_persona = new PersonaDao();
    private VistaActualizar_Empleado vista;
    private int id_empleado;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_Empleado(EmpleadoDao modelo_empleado, VistaActualizar_Empleado vista) {
        this.modelo_empleado = modelo_empleado;
        this.vista = vista;

        id_empleado = ControlGestion_Empleados.id_empleado;

        vista.setVisible(true);
        vista.setTitle("Actualizar Empleado - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarCargos();
        cargarDatosEmpleado();
        vista.getJd_fechacontrato().getDateEditor().setEnabled(false);
    }

    public void funcionalidad() {

        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarPersona(vista.getTxt_cedula().getText());
            }

        });
        vista.getBt_actualizar().addActionListener(l -> actualizarUsuario());

    }

    private void cargarDatosEmpleado() {

        modelo_empleado.mostrarDatosJoin(id_empleado).forEach((em) -> {

            vista.getTxt_cedula().setText(em.getDni_persona());
            vista.getTxt_persona().setText(em.getNombreempleado());
            vista.getCb_cargo().setSelectedItem(em.getNombreCargo());
            vista.getJd_fechacontrato().setDate(em.getFecha_contrato());
            vista.getTxtSueldo().setText(String.valueOf(em.getSueldo()));

        });

    }

    private void cargarCargos() {
        modelo_cargo.mostrarDatos().stream().forEach((m) -> {
            vista.getCb_cargo().addItem(m.getNombre());
        });

    }

    private boolean verificarPersonaRegistrada(String cedula, int id) {

        /*Si ya exite una persona con la cedula 
        indicada, ya no se puede registrar*/
        boolean respuesta = modelo_empleado.mostrarDatosJoinDNI(cedula, id).isEmpty();
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
            verificarPersonaRegistrada(cedula, id_empleado);
        } else {
            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxt_persona().setText("");

        }
        return busqueda;

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

    private void restaurarBordes() {

        vista.getTxt_cedula().setBorder(origin_border);

    }

    private void sentenciaUpdate() {

        int idpersona = VerificarCedula(vista.getTxt_cedula().getText());
        int id_cargo = modelo_cargo.mostrarDatos().get(vista.getCb_cargo().getSelectedIndex()).getId_cargo();
        Instant instant = vista.getJd_fechacontrato().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());
        Double sueldo = Double.valueOf(vista.getTxtSueldo().getText());

        modelo_empleado.setId_persona(idpersona);
        modelo_empleado.setId_cargo(id_cargo);
        modelo_empleado.setFecha_contrato(fecha);
        modelo_empleado.setSueldo(sueldo);

        if (modelo_empleado.modificar(id_empleado)) {
            
            JOptionPane.showMessageDialog(vista, "Empleado Actualizado");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al Actualizar");
        }

    }

    private void actualizarUsuario() {

        if (validarRegistro()) {

            if (buscarPersona(vista.getTxt_cedula().getText())) {

                if (verificarPersonaRegistrada(vista.getTxt_cedula().getText(), id_empleado)) {

                    sentenciaUpdate();
                    restaurarBordes();

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
