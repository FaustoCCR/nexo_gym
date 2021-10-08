package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ProveedorDao;
import vista.VistaActualizar_Proveedores;
import vista.VistaGestion_Proveedor;

public class ControlGestion_Proveedor {

    private ProveedorDao modelo_proveedor;
    private VistaGestion_Proveedor vista_proveedor;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"Ruc", "Nombre", "Contacto"};
    public static String id_proveedor;

    public ControlGestion_Proveedor(ProveedorDao modelo_proveedor, VistaGestion_Proveedor vista_proveedor) {
        this.modelo_proveedor = modelo_proveedor;
        this.vista_proveedor = vista_proveedor;

        vista_proveedor.setVisible(true);
        vista_proveedor.setTitle("Proveedores Registrados - Nexo Gym");
        vista_proveedor.setResizable(false);
        vista_proveedor.setLocation(611, 159);
        vista_proveedor.setClosable(true);
        vista_proveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();
        mostrarDatosTabla("");

    }

    public void funcionalidad() {
        vista_proveedor.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                mostrarDatosTabla(vista_proveedor.getTxt_buscar().getText());

            }

        });

        vista_proveedor.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista_proveedor.getBt_eliminar().addActionListener(l -> sentenciaDelete());
    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                
                /*true me permite tomar los datos de la tabla para copiar en otro formulario*/
                return true;

            }

        };

        vista_proveedor.getJtable_proveedores().setModel(tb_model);
        vista_proveedor.getJtable_proveedores().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_proveedor.getJtable_proveedores().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_proveedor.getJtable_proveedores().getColumnModel().getColumn(0).setPreferredWidth(20);
    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);

        modelo_proveedor.mostrarDatosJoin(aguja).stream().forEach((pv) -> {

            Object[] contenido = {pv.getId_proveedor(), pv.getNombre(), pv.getContacto()};

            tb_model.addRow(contenido);

        });
    }

    private void ventanaActualizar() {

        int fila = vista_proveedor.getJtable_proveedores().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_proveedor = (String) vista_proveedor.getJtable_proveedores().getValueAt(fila, columna);
            vista_proveedor.dispose();
            VistaActualizar_Proveedores vista = new VistaActualizar_Proveedores();
            ControlActualizar_Proveedor control = new ControlActualizar_Proveedor(modelo_proveedor, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_proveedor, "Seleccione el registro a verificar");
        }

    }

    private void sentenciaDelete() {

        int fila = vista_proveedor.getJtable_proveedores().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            String id_prov = (String) vista_proveedor.getJtable_proveedores().getValueAt(fila, columna);
            String proveedor = modelo_proveedor.mostrarDatos().stream().filter(u -> u.getId_proveedor().equals(id_prov)).findAny().get().getNombre();

//          String producto = modelo.mostrarDatos("").stream().filter(u -> u.getId_prod() == id_producto).findAny().get().getNombre();
            int resp = JOptionPane.showConfirmDialog(vista_proveedor, "Seguro desea eliminar el proveedor : " + proveedor, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_proveedor.eliminar(id_prov);
                JOptionPane.showMessageDialog(vista_proveedor, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista_proveedor, "Seleccione el registro a eliminar");
        }
    }
}
