package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.dao.Cuerpo_VentaDao;
import modelo.dao.Ecb_VentaDao;
import vista.VistaAdministrador;
import vista.VistaEditar_Venta;
import vista.VistaGestionVentas;

public class ControlGestion_Ventas {

    private Ecb_VentaDao modelo_ecbventa;
    private VistaGestionVentas vista;
    private Cuerpo_VentaDao modelo_cventa;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"#", "ID", "Cliente", "Vendedor", "Fecha", "Nro.Productos", "Total"};
    public static int id_ecb;

    public ControlGestion_Ventas(Ecb_VentaDao modelo_ecbventa, Cuerpo_VentaDao modelo_cventa, VistaGestionVentas vista) {
        this.modelo_ecbventa = modelo_ecbventa;
        this.modelo_cventa = modelo_cventa;
        this.vista = vista;
        vista.setVisible(true);
        vista.setTitle("Ventas Registradas - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista.getJdatefiltro().getDateEditor().setEnabled(false);

        disenioTabla();
        cargarDatosTabla("", "");
    }

    public void funcionalidad() {

        vista.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                cargarDatosTabla(vista.getTxt_buscar().getText(), "");
                ((JTextField) vista.getJdatefiltro().getDateEditor().getUiComponent()).setText("");

            }
        });

        vista.getJdatefiltro().getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (vista.getJdatefiltro().getDate() != null) {

//                String sql = "or to_char(ec.fecha_venta,'dd/MM/yyyy') = " + "'"+ vista.getJdatefiltro().getDate() + "'";
//                String sql = "and to_char(ec.fecha_venta,'dd/mm/yyyy') = '04/10/2021'";
                    String sql = "and ec.fecha_venta = to_date('" + formateadorFecha(transformarFecha()) + "','dd/MM/yyyy')";
                    cargarDatosTabla(vista.getTxt_buscar().getText(), sql);
                }
            }
        });
        vista.getBt_verificar().addActionListener(l -> ventanaEditarVenta());
        vista.getBt_eliminar().addActionListener(l->sentenciaDelete());

    }

    private Date transformarFecha() {
        //------------------------- FECHA -----------------------------//
        Instant instant = vista.getJdatefiltro().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        return Date.valueOf(zdt.toLocalDate());

    }

    private String formateadorFecha(Date fecha) {

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(fecha);
    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }

        };

        vista.getJtable_ventas().setModel(tb_model);
        vista.getJtable_ventas().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getJtable_ventas().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getJtable_ventas().getColumnModel().getColumn(0).setPreferredWidth(20);
        vista.getJtable_ventas().getColumnModel().getColumn(1).setPreferredWidth(20);
    }

    private void cargarDatosTabla(String aguja, String consultaextra) {

        tb_model.setRowCount(0);
        Holder<Integer> i = new Holder<>(1);//contador Holder
        modelo_cventa.mostrarDatosJoin(aguja, consultaextra).forEach((cv) -> {

            Object[] contenido = {i.value, cv.getId_ecb(), cv.getCliente(), cv.getVendedor(),
                formateadorFecha(cv.getFecha_venta()), cv.getNro_productos(), cv.getTotal()};

            i.value++;

            tb_model.addRow(contenido);

        });

        long count = modelo_cventa.mostrarDatosJoin(aguja, consultaextra).stream().count();
        vista.getJlnumeroventas().setText(String.valueOf(count));
        DoubleSummaryStatistics statistics = modelo_cventa.mostrarDatosJoin(aguja, consultaextra).stream()
                .collect(Collectors
                        .summarizingDouble(cv -> cv.getTotal()));
        vista.getJltotal().setText(String.valueOf(statistics.getSum()) + " $");

    }

    private void ventanaEditarVenta() {

        int fila = vista.getJtable_ventas().getSelectedRow();
        final int columna = 1; // id_ecb

        if (fila != -1) {

            id_ecb = (int) vista.getJtable_ventas().getValueAt(fila, columna);
            vista.dispose();
            VistaEditar_Venta vista = new VistaEditar_Venta();
            VistaAdministrador.getjDesktopPanePrincipal().add(vista);
            ControlEditar_Venta control = new ControlEditar_Venta(modelo_ecbventa, modelo_cventa, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a verificar");
        }

    }

    private void sentenciaDelete() {

        int fila = vista.getJtable_ventas().getSelectedRow();
        final int columna = 1;
        if (fila != -1) {

            int id_ecb = (int) vista.getJtable_ventas().getValueAt(fila, columna);
            String cliente = modelo_cventa.mostrarDatosJoinEspecifico(id_ecb).stream().filter(ecb -> ecb.getId_ecb() == id_ecb).findAny().get().getCliente();
            int resp = JOptionPane.showConfirmDialog(vista, "Seguro desea eliminar la factura con ID : " + id_ecb + "\nCliente : " + cliente, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                
                JOptionPane.showMessageDialog(vista, "Se eliminaran todos los datos relacionados a esta factura");
                modelo_cventa.eliminarFK(id_ecb);
                modelo_ecbventa.eliminar(id_ecb);
                JOptionPane.showMessageDialog(vista, "Venta Eliminada");
                cargarDatosTabla("", "");

            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a eliminar");
        }
    }

}
