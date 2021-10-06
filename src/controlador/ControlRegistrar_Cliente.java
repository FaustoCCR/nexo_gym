package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.ClienteDao;
import modelo.dao.MembresiaDao;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Cliente;

public class ControlRegistrar_Cliente {

    private ClienteDao modelo_cliente;
    private VistaRegistrar_Cliente vista;
    private PersonaDao modelo_persona = new PersonaDao();
    private MembresiaDao modelo_membresia = new MembresiaDao();
    private Border origin_border = new LineBorder(Color.gray, 1);

    private boolean estado_pago = false;

    public ControlRegistrar_Cliente(ClienteDao modelo_cliente, VistaRegistrar_Cliente vista) {
        this.modelo_cliente = modelo_cliente;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Clientes - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        vista.getJdate_inicio().getDateEditor().setDate(new java.util.Date());
        vista.getJdate_inicio().setMinSelectableDate(new java.util.Date());
        vista.getJdate_inicio().getDateEditor().setEnabled(false);

        cargarMembresias();
        datosReferencia();

    }

    public void funcionalidad() {

        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarPersona(vista.getTxt_cedula().getText());
            }

        });

        vista.getCb_membresia().addItemListener((e) -> {

            vista.getTxt_desc().setText(String.valueOf(cargarDescuento() * 100));
            calcularTotal();

        });
        vista.getTxt_pago().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                calcularTotal();
            }

        });
        vista.getJspinner_meses().addChangeListener(js -> calcularTotal());

        vista.getBt_registrar().addActionListener(l -> registrarCliente());
        vista.getBt_pagar().addActionListener(l -> estado_pago = true);

    }

    private void datosReferencia() {
        vista.getTxt_subtotal().setText("0.0");
        vista.getTxt_desc().setText("0.0");

    }

    private boolean verificarPersonaRegistrada(String cedula) {

        /*Si ya exite una persona con la cedula 
        indicada, ya no se puede registrar*/
        boolean respuesta = modelo_cliente.mostrarDatosJoin("").stream().noneMatch(c -> c.getCedulapersona().equals(cedula));
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

    private int campoPersona(String cedula) {

        /*Retora el id de la persona de acuerdo a su número de cédula*/
        int id_persona = modelo_persona.mostrarDatos().stream().filter(p -> p.getDni().equals(cedula)).findAny().get().getId_persona();

        return id_persona;

    }

    private void cargarMembresias() {

        modelo_membresia.mostrarDatos("").stream().forEach((m) -> {

            vista.getCb_membresia().addItem(m.getNombre());

        });

    }

    private Date fechaInicio() {
        Instant instant = vista.getJdate_inicio().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fechainicio = Date.valueOf(zdt.toLocalDate());

        return fechainicio;
    }

    private Date calcularVencimiento() {

        int meses = (int) vista.getJspinner_meses().getValue();

        LocalDate ld_vence = fechaInicio().toLocalDate().plusMonths(meses);
        Date fecha_vence = Date.valueOf(ld_vence);

        return fecha_vence;

    }

    private double cargarDescuento() {

        int seleccion = vista.getCb_membresia().getSelectedIndex();
        double descuento = (modelo_membresia.mostrarDatos("").get(seleccion).getDescuento());
        return descuento;
    }

    private double calcularSubtotal() {

        String campo_pago = vista.getTxt_pago().getText().trim();
        double pago;
        int meses;
        double subtotal;

        if (campo_pago.isEmpty()) {

            pago = 0;
        } else {

            pago = Double.parseDouble(campo_pago);

        }

        meses = (int) vista.getJspinner_meses().getValue();

        if (meses > 0) {
            subtotal = pago * meses;
        } else {
            subtotal = pago + meses;
        }

//        return subtotal;
        return Math.round(subtotal * 100.0) / 100.0;

    }

    private double calcularTotal() {

        double subtotal = calcularSubtotal();
        vista.getTxt_subtotal().setText(String.valueOf(subtotal));
        double desc = Double.parseDouble(vista.getTxt_desc().getText());

        double total = subtotal - (subtotal * desc / 100);
        double round_total = Math.round(total * 100.0) / 100.0;
        vista.getTxt_total().setText(String.valueOf(round_total));
        return round_total;

    }

    private void sentenciaInsert() {

        int id_persona = campoPersona(vista.getTxt_cedula().getText());
        int id_membresia = modelo_membresia.mostrarDatos("").get(vista.getCb_membresia().getSelectedIndex()).getId_membresia();

        modelo_cliente.setId_persona(id_persona);
        modelo_cliente.setId_membresia(id_membresia);
        modelo_cliente.setF_inicio(fechaInicio());
        modelo_cliente.setF_vence(calcularVencimiento());
        modelo_cliente.setPago(calcularTotal());
        modelo_cliente.setEstado_pago(estado_pago);

        if (modelo_cliente.insertar()) {

            JOptionPane.showMessageDialog(vista, "Cliente Registrado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Guardar");
        }

    }

    private void reiniciarCampos() {

        vista.getTxt_cedula().setText("");
        vista.getTxt_persona().setText("");
        vista.getCb_membresia().setSelectedIndex(0);
        vista.getJdate_inicio().setDate(new java.util.Date());
        vista.getJspinner_meses().setValue(0);
        vista.getTxt_pago().setText("");
        datosReferencia();//reinicia campos contables
        vista.getTxt_total().setText("");
        vista.getTxt_cedula().setBorder(origin_border);

    }

    private boolean validarRegistro() {

        String persona = vista.getTxt_cedula().getText();
        String precio = vista.getTxt_pago().getText();

        boolean validacion = true;

        if (persona.isEmpty() || precio.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private void registrarCliente() {

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
                JOptionPane.showMessageDialog(vista, "Cedula no identificada", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(vista, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }

}
