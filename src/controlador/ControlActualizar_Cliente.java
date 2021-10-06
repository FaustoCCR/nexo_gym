package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.ClienteDao;
import modelo.dao.MembresiaDao;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaActualizar_Cliente;

public class ControlActualizar_Cliente {

    private ClienteDao modelo_cliente;
    private VistaActualizar_Cliente vista;
    private MembresiaDao modelo_membresia = new MembresiaDao();
    private PersonaDao modelo_persona = new PersonaDao();
    private int id_cliente;
    private boolean estado_pago;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlActualizar_Cliente(ClienteDao modelo_cliente, VistaActualizar_Cliente vista) {
        this.modelo_cliente = modelo_cliente;
        this.vista = vista;

        id_cliente = ControlGestion_Clientes.id_cliente;

        vista.setVisible(true);
        vista.setTitle("Actualizar Cliente - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarMembresias();
        cargarDatosCliente();
        vista.getJdate_inicio().getDateEditor().setEnabled(false);
        switchBoton();

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
            estado_pago = false;
            switchBoton();

        });
        vista.getJdate_inicio().getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                diasRestantes();
                estado_pago = false;
                switchBoton();
            }
        });
        vista.getTxt_pago().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                calcularTotal();
                estado_pago = false;
                switchBoton();
            }

        });
        vista.getJspinner_meses().addChangeListener((js) -> {
            calcularTotal();
            diasRestantes();
            estado_pago = false;
            switchBoton();

        });

        vista.getBt_pagar().addActionListener((b) -> {

            if (estado_pago == false) {

                vista.getTxt_estadopago().setText("Pagado");
                estado_pago = true;
                switchBoton();
            }
        });

        vista.getBt_actualzar().addActionListener(l -> actualizarUsuario());

    }

    private void switchBoton() {
        if (estado_pago == true) {
            vista.getBt_pagar().setEnabled(false);
            vista.getBt_pagar().setBackground(null);
        } else {
            vista.getBt_pagar().setEnabled(true);
            vista.getBt_pagar().setBackground(new Color(153, 204, 255));
            vista.getTxt_estadopago().setText("Sin Pagar");
        }
    }

    private void cargarDatosCliente() {

        modelo_cliente.mostrarDatosJoin(id_cliente).forEach((c) -> {

            vista.getTxt_cedula().setText(c.getCedulapersona());
            vista.getTxt_persona().setText(c.getNombrecliente());
            vista.getCb_membresia().setSelectedItem(c.getMembresia());
            vista.getJdate_inicio().setDate(c.getF_inicio());

            vista.getTxt_diasfaltantes().setText(String.valueOf((c.getDias_faltantes())));
            estado_pago = c.isEstado_pago();
            vista.getTxt_estadopago().setText(c.getEstadop());

            double pago = c.getPago();
            double desc = c.getDesc();
            long n_meses = ChronoUnit.MONTHS.between(c.getF_inicio().toLocalDate(), c.getF_vence().toLocalDate());

            vista.getTxt_total().setText(String.valueOf(pago));
            vista.getTxt_desc().setText(String.valueOf(desc * 100));
            vista.getJspinner_meses().setValue(n_meses);

            double subtotal = pago + (pago * desc);
            double round_subtotal = Math.round(subtotal * 100.0) / 100.0;
            double precio_u = subtotal / n_meses;
            double round_preciou = Math.round(precio_u * 100.0) / 100.0;

            vista.getTxt_subtotal().setText(String.valueOf(round_subtotal));

            vista.getTxt_pago().setText(String.valueOf(round_preciou));

        });

    }

    private void cargarMembresias() {

        modelo_membresia.mostrarDatos().stream().forEach((m) -> {
            vista.getCb_membresia().addItem(m.getNombre());

        });

    }

    private boolean verificarPersonaRegistrada(String cedula, int id) {

        /*Si ya exite una persona con la cedula 
        indicada, ya no se puede registrar*/
        boolean respuesta = modelo_cliente.mostrarDatosJoinDNI(cedula, id).isEmpty();
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
                verificarPersonaRegistrada(cedula, id_cliente);

            });
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

    private Date fechaInicio() {
        Instant instant = vista.getJdate_inicio().getDateEditor().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fechainicio = Date.valueOf(zdt.toLocalDate());

        return fechainicio;
    }

    private Date calcularVencimiento() {

        double meses = Double.parseDouble(String.valueOf(vista.getJspinner_meses().getValue()));

        LocalDate ld_vence = fechaInicio().toLocalDate().plusMonths((long) meses);
        Date fecha_vence = Date.valueOf(ld_vence);
        return fecha_vence;

    }

    private void diasRestantes() {
        long diff;
        if (fechaInicio().before(new java.util.Date())) {

            diff = ChronoUnit.DAYS.between(LocalDate.now(), calcularVencimiento().toLocalDate());

        } else {
            diff = ChronoUnit.DAYS.between(fechaInicio().toLocalDate(), calcularVencimiento().toLocalDate());

        }
        vista.getTxt_diasfaltantes().setText(String.valueOf(diff));
    }

    private double cargarDescuento() {

        int seleccion = vista.getCb_membresia().getSelectedIndex();
        double descuento = (modelo_membresia.mostrarDatos().get(seleccion).getDescuento());
        return descuento;
    }

    private double calcularSubtotal() {

        String campo_pago = vista.getTxt_pago().getText().trim();
        double pago;
        double meses;
        double subtotal;

        if (campo_pago.isEmpty()) {

            pago = 0;
        } else {

            pago = Double.parseDouble(campo_pago);

        }

        meses = Double.parseDouble(String.valueOf(vista.getJspinner_meses().getValue()));

        if (meses > 0) {
            subtotal = pago * meses;
        } else {
            subtotal = pago + meses;
        }

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

    private boolean validarRegistro() {

        String persona = vista.getTxt_cedula().getText();
        String precio = vista.getTxt_pago().getText();

        boolean validacion = true;

        if (persona.isEmpty() || precio.isEmpty()) {

            validacion = false;
        }
        return validacion;

    }

    private void sentenciaUpdate() {

        int id_persona = campoPersona(vista.getTxt_cedula().getText());
        int id_membresia = modelo_membresia.mostrarDatos().get(vista.getCb_membresia().getSelectedIndex()).getId_membresia();

        modelo_cliente.setId_persona(id_persona);
        modelo_cliente.setId_membresia(id_membresia);
        modelo_cliente.setF_inicio(fechaInicio());
        modelo_cliente.setF_vence(calcularVencimiento());
        modelo_cliente.setPago(calcularTotal());
        modelo_cliente.setEstado_pago(estado_pago);

        if (modelo_cliente.modificar(id_cliente)) {

            JOptionPane.showMessageDialog(vista, "Cliente Actualizado");

        } else {
            JOptionPane.showMessageDialog(vista, "Error al Actualizar");
        }

    }

    private void restaurarBordes() {

        vista.getTxt_cedula().setBorder(origin_border);

    }

    private void actualizarUsuario() {

        if (validarRegistro()) {

            if (buscarPersona(vista.getTxt_cedula().getText())) {

                if (verificarPersonaRegistrada(vista.getTxt_cedula().getText(), id_cliente)) {

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
