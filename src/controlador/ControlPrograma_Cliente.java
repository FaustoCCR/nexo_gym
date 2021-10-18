package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import modelo.dao.EmpleadoDao;
import modelo.dao.ProgramaClienteDao;
import modelo.dao.RutinaDao;
import modelo.vo.EmpleadoVo;
import vista.VistaAdministrador;
import vista.VistaPrograma_Cliente;

public class ControlPrograma_Cliente {

    private ProgramaClienteDao modelo_pgcliente;
    private VistaPrograma_Cliente vista_pgcliente;
    private ClienteDao modelo_cliente = new ClienteDao();
    private RutinaDao modelo_rutina = new RutinaDao();
    private EmpleadoDao modelo_empleado = new EmpleadoDao();
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Rutina", "Instructor", "Actividad", "Fecha"};

    private int id_cliente;
    private String nombre;
    private List<EmpleadoVo> listainstructores;
    private int id_pgcliente;
    private int switchboton;

    public ControlPrograma_Cliente(ProgramaClienteDao modelo_pgcliente, VistaPrograma_Cliente vista_pgcliente) {
        this.modelo_pgcliente = modelo_pgcliente;
        this.vista_pgcliente = vista_pgcliente;

        id_cliente = ControlGestion_Clientes.id_cliente;
        vista_pgcliente.setVisible(true);
        vista_pgcliente.setTitle("Programa Cliente - Nexo Gym");
        vista_pgcliente.setResizable(false);
        vista_pgcliente.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista_pgcliente.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista_pgcliente.getHeight()) / 2);
        vista_pgcliente.setClosable(true);
        vista_pgcliente.setIconifiable(true);
        vista_pgcliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosCliente();
        disenioTabla();
        mostrarDatosTabla();
        cargarRutinas();
        cargarInstructores();

    }

    public void funcionalidad() {

        vista_pgcliente.getBt_registrar().addActionListener(l -> switchboton = abrirJDialog(1));
        vista_pgcliente.getBt_verificar().addActionListener(l -> switchboton = abrirJDialog(2));
        vista_pgcliente.getBt_eliminar().addActionListener(l -> borrarRutinaAsiganda());

        vista_pgcliente.getBt_registrarRutina().addActionListener((e) -> {
            if (switchboton == 1) {

                registrarRutinaCliente();

            } else if (switchboton == 2) {
                actualizarRutinaCliente();
            }

        });

    }

    private void cargarDatosCliente() {

        modelo_cliente.mostrarDatosJoin(id_cliente).forEach((cl) -> {

            vista_pgcliente.getTxt_cedula().setText(cl.getCedulapersona());
            nombre = cl.getNombrecliente();
            vista_pgcliente.getTxt_persona().setText(nombre);
            vista_pgcliente.getTxt_membresia().setText(cl.getMembresia());

        });
    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        vista_pgcliente.getJtable_programacliente().setModel(tb_model);
        vista_pgcliente.getJtable_programacliente().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_pgcliente.getJtable_programacliente().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_pgcliente.getJtable_programacliente().getColumnModel().getColumn(0).setPreferredWidth(10);
        vista_pgcliente.getJtable_programacliente().getColumnModel().getColumn(1).setPreferredWidth(30);
        vista_pgcliente.getJtable_programacliente().getColumnModel().getColumn(3).setPreferredWidth(200);

    }

    private void mostrarDatosTabla() {

        tb_model.setRowCount(0);
        modelo_pgcliente.mostrarDatosJoin(id_cliente).forEach((pgc) -> {

            Object[] contenido = {pgc.getId_pgcliente(), pgc.getNombreRutina(), pgc.getNombreInstructor(), pgc.getDescripcion_rutina(), pgc.getFecha()};
            tb_model.addRow(contenido);

        });
    }

    private void cargarRutinas() {

        modelo_rutina.mostrarDatosJoin("").forEach((r) -> {

            vista_pgcliente.getCb_rutina().addItem(r.getNombre());

        });
    }

    private void cargarInstructores() {
        listainstructores = modelo_empleado.mostrarDatosJoin("")
                .stream().filter(cg -> cg.getNombreCargo().equalsIgnoreCase("Entrenador") || cg.getNombreCargo().equalsIgnoreCase("Instructor"))
                .collect(Collectors.toList());

        listainstructores.forEach((em) -> {
            vista_pgcliente.getCb_instructor().addItem(em.getNombreempleado());
        });

    }

    /* ----------------------- gestor JDialog ------------------------ */
    private int abrirJDialog(int item) {

        vista_pgcliente.getjDialogRutina().setSize(663, 385);
        vista_pgcliente.getjDialogRutina().setResizable(false);
        vista_pgcliente.getjDialogRutina().setLocationRelativeTo(vista_pgcliente);
        vista_pgcliente.getJdate_fecha().getDateEditor().setEnabled(false);

        vista_pgcliente.getTxt_cliente().setText(nombre);

        switch (item) {

            case 1:
                ventanaRegistrar();
                break;
            case 2:

                ventanaActualizar();

                break;
        }

        return item;

    }

    /* ----------------------- ITEM REGISTRAR ------------------------ */
    private void cargarIconJbuttonInsertar() {

        ImageIcon img = new ImageIcon(getClass().getResource("/vista/img/crear1.png"));
        vista_pgcliente.getBt_registrarRutina().setIcon(img);
        vista_pgcliente.getBt_registrar().setIconTextGap(2);
        vista_pgcliente.getBt_registrarRutina().setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vista_pgcliente.getBt_registrarRutina().setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        vista_pgcliente.getBt_registrarRutina().setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        vista_pgcliente.getBt_registrarRutina().setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    }

    private void ventanaRegistrar() {
        reiniciarCampos();
        cargarIconJbuttonInsertar();
        vista_pgcliente.getjDialogRutina().setVisible(true);
        vista_pgcliente.getjDialogRutina().setTitle("Asignar Rutina - Nexo Gym");
        vista_pgcliente.getJdate_fecha().setDate(new java.util.Date());
        vista_pgcliente.getBt_registrarRutina().setText("Registrar");
//        vista_pgcliente.getBt_registrarRutina().addActionListener(l -> registrarRutinaCliente());
    }

    private boolean validarRegistro() {

        boolean validacion = true;

        String rutina = vista_pgcliente.getCb_rutina().getSelectedItem().toString();
        String instructor = vista_pgcliente.getCb_instructor().getSelectedItem().toString();

        if (rutina.isEmpty() || vista_pgcliente.getJdate_fecha().getDate() == null || instructor.isEmpty()) {

            validacion = false;

        }
        return validacion;
    }

    private void sentenciaInsert() {

        int id_rutina = modelo_rutina.mostrarDatos().get(vista_pgcliente.getCb_rutina().getSelectedIndex()).getId_rutina();
        /*-----------------------fecha-----------------------*/
        Instant instant = vista_pgcliente.getJdate_fecha().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = java.sql.Date.valueOf(zdt.toLocalDate());

        int id_empleado = listainstructores.get(vista_pgcliente.getCb_instructor().getSelectedIndex()).getId_empleado();

        modelo_pgcliente.setId_cliente(id_cliente);
        modelo_pgcliente.setId_rutina(id_rutina);
        modelo_pgcliente.setId_empleado(id_empleado);
        modelo_pgcliente.setFecha(fecha);

        if (modelo_pgcliente.insertar()) {
            JOptionPane.showMessageDialog(vista_pgcliente.getjDialogRutina(), "Rutina asignada correctamente");
        } else {
            JOptionPane.showMessageDialog(vista_pgcliente.getjDialogRutina(), "Error al guardar");
        }
    }

    private void reiniciarCampos() {
        vista_pgcliente.getCb_rutina().setSelectedIndex(0);
        vista_pgcliente.getJdate_fecha().setDate(new java.util.Date());
        vista_pgcliente.getCb_instructor().setSelectedIndex(0);

    }

    private void registrarRutinaCliente() {

        if (validarRegistro()) {
            sentenciaInsert();
            reiniciarCampos();
            mostrarDatosTabla();

        } else {
            JOptionPane.showMessageDialog(vista_pgcliente.getjDialogRutina(), "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* ----------------------- ITEM ACTUALIZAR ------------------------ */
    private void cargarIconJbuttonActualizar() {

        ImageIcon img = new ImageIcon(getClass().getResource("/vista/img/editar1.png"));
        vista_pgcliente.getBt_registrarRutina().setIcon(img);
        vista_pgcliente.getBt_registrar().setIconTextGap(2);
        vista_pgcliente.getBt_registrarRutina().setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vista_pgcliente.getBt_registrarRutina().setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        vista_pgcliente.getBt_registrarRutina().setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        vista_pgcliente.getBt_registrarRutina().setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    }

    private void ventanaActualizar() {

        int fila = vista_pgcliente.getJtable_programacliente().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            id_pgcliente = Integer.parseInt(vista_pgcliente.getJtable_programacliente().getValueAt(fila, columna).toString());
            cargarIconJbuttonActualizar();
            vista_pgcliente.getjDialogRutina().setVisible(true);
            vista_pgcliente.getjDialogRutina().setTitle("Actualizar rutina - Nexo Gym");
            vista_pgcliente.getBt_registrarRutina().setText("Actualizar");

            cargarDatosRutina(id_pgcliente);
//            vista_pgcliente.getBt_registrarRutina().addActionListener(l -> actualizarRutinaCliente());

        } else {
            JOptionPane.showMessageDialog(vista_pgcliente, "Seleccione el registro dentro de la tabla");
        }

    }

    private void cargarDatosRutina(int id_pgcliente) {

        vista_pgcliente.getTxt_cliente().setText(nombre);
        modelo_pgcliente.mostrarDatosJoin2(id_pgcliente).forEach((pgc) -> {

            vista_pgcliente.getCb_rutina().setSelectedItem(pgc.getNombreRutina());
            vista_pgcliente.getJdate_fecha().setDate(pgc.getFecha());
            vista_pgcliente.getCb_instructor().setSelectedItem(pgc.getNombreInstructor());

        });

    }

    private void sentenciaUpdate() {

        int id_rutina = modelo_rutina.mostrarDatos().get(vista_pgcliente.getCb_rutina().getSelectedIndex()).getId_rutina();
        /*-----------------------fecha-----------------------*/
        Instant instant = vista_pgcliente.getJdate_fecha().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = java.sql.Date.valueOf(zdt.toLocalDate());

        int id_empleado = listainstructores.get(vista_pgcliente.getCb_instructor().getSelectedIndex()).getId_empleado();

        modelo_pgcliente.setId_cliente(id_cliente);
        modelo_pgcliente.setId_rutina(id_rutina);
        modelo_pgcliente.setId_empleado(id_empleado);
        modelo_pgcliente.setFecha(fecha);

        if (modelo_pgcliente.modificar(id_pgcliente)) {
            JOptionPane.showMessageDialog(vista_pgcliente.getjDialogRutina(), "Rutina actualizada correctamente");
        } else {
            JOptionPane.showMessageDialog(vista_pgcliente.getjDialogRutina(), "Error al actualizar");
        }
    }

    private void actualizarRutinaCliente() {

        if (validarRegistro()) {
            sentenciaUpdate();
            mostrarDatosTabla();

        } else {
            JOptionPane.showMessageDialog(vista_pgcliente.getjDialogRutina(), "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* ----------------------- ITEM ELIMINAR ------------------------ */
    private void borrarRutinaAsiganda() {
        int fila = vista_pgcliente.getJtable_programacliente().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_pgc = (int) vista_pgcliente.getJtable_programacliente().getValueAt(fila, columna);
            String rutina = modelo_pgcliente.mostrarDatosJoin2(id_pgc).get(0).getNombreRutina();

            int resp = JOptionPane.showConfirmDialog(vista_pgcliente, "Seguro desea eliminar la rutina de : " + rutina, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_pgcliente.eliminar(id_pgc);
                JOptionPane.showMessageDialog(vista_pgcliente, "Registro Eliminado");
                mostrarDatosTabla();
            }
        } else {
            JOptionPane.showMessageDialog(vista_pgcliente, "Seleccione el registro a eliminar");
        }
    }

}
