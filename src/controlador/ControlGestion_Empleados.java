package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.PGConexion;
import modelo.dao.EmpleadoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.VistaActualizar_Empleado;
import vista.VistaAdministrador;
import vista.VistaGestion_Empleado;

public class ControlGestion_Empleados {

    private EmpleadoDao modelo_empleado;
    private VistaGestion_Empleado vista_empleado;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"Id", "DNI", "Nombre", "Cargo", "Fecha Contrato", "Sueldo"};
    public static int id_empleado;

    public ControlGestion_Empleados(EmpleadoDao modelo_empleado, VistaGestion_Empleado vista_empleado) {
        this.modelo_empleado = modelo_empleado;
        this.vista_empleado = vista_empleado;

        vista_empleado.setVisible(true);
        vista_empleado.setTitle("Empleados Registrados - Nexo Gym");
        vista_empleado.setResizable(false);
        vista_empleado.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista_empleado.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista_empleado.getHeight()) / 2);
        vista_empleado.setClosable(true);
        vista_empleado.setIconifiable(true);
        vista_empleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla("");

    }

    public void funcionalidad() {
        vista_empleado.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mostrarDatosTabla(vista_empleado.getTxt_buscar().getText());
            }

        });
        vista_empleado.getBt_eliminar().addActionListener(l -> BorrarEmpleado());
        vista_empleado.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista_empleado.getBt_imprimir().addActionListener(l->imprimirReporte());

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;

            }
        };

        vista_empleado.getJtable_empleados().setModel(tb_model);
        vista_empleado.getJtable_empleados().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_empleado.getJtable_empleados().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_empleado.getJtable_empleados().getColumnModel().getColumn(0).setPreferredWidth(20);

    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);
        modelo_empleado.mostrarDatosJoin(aguja).stream().forEach((c) -> {
            Object[] contenido
                    = {c.getId_empleado(), c.getDni_persona(), c.getNombreempleado(), c.getNombreCargo(), c.getFecha_contrato(), c.getSueldo()};
            tb_model.addRow(contenido);
        });
    }

    private void ventanaActualizar() {

        int fila = vista_empleado.getJtable_empleados().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_empleado = (int) vista_empleado.getJtable_empleados().getValueAt(fila, columna);
            vista_empleado.dispose();
            VistaActualizar_Empleado vista = new VistaActualizar_Empleado();
            VistaAdministrador.getjDesktopPanePrincipal().add(vista);
            ControlActualizar_Empleado control = new ControlActualizar_Empleado(modelo_empleado, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_empleado, "Seleccione el registro a verificar");
        }

    }

    private void BorrarEmpleado() {
        int fila = vista_empleado.getJtable_empleados().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_emp = (int) vista_empleado.getJtable_empleados().getValueAt(fila, columna);
            String emp = modelo_empleado.mostrarDatosJoin("").stream().filter(u -> u.getId_empleado() == id_emp).findAny().get().getNombreempleado();

            int resp = JOptionPane.showConfirmDialog(vista_empleado, "Seguro desea eliminar al Empleado : " + emp, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_empleado.eliminar(id_emp);
                JOptionPane.showMessageDialog(vista_empleado, "Registro Eliminado");
                mostrarDatosTabla("");
            }
        } else {
            JOptionPane.showMessageDialog(vista_empleado, "Seleccione el registro a eliminar");
        }
    }

    private void imprimirReporte() {

        PGConexion con = new PGConexion();

        try {

            /*Creacion de un mapa
            asigna un dato a los diferentes 
            parámetros*/
            Map<String, Object> parametros = new HashMap<>();
            String aguja = vista_empleado.getTxt_buscar().getText().trim();

//            put( ?,  ?)---> el nombre del parametro(tipo de dato), la variable que recibe 
            parametros.put("paguja", "%" + aguja + "%");
            parametros.put("pdetalle", subitituloReport(aguja));
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/vista/reportes/ReporteEmpleados.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            // (jr,null,con.getCon()) null --> cuando no enviamos un parametro
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControlGestion_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String subitituloReport(String filtro) {

        if (filtro.isEmpty()) {

            return "Búsqueda general";

        } else {
            return "Párametro de búsqueda : " + filtro;

        }

    }
}
