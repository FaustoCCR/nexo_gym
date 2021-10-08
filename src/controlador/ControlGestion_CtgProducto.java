
package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.Ctg_ProductoDao;
import vista.VistaActualizar_CtgProducto;
import vista.VistaGestion_CtgProducto;

public class ControlGestion_CtgProducto {
    private Ctg_ProductoDao modelo_ctgp;
    private VistaGestion_CtgProducto vista_ctgp;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Descripcion"};
    public static int id_ctgp;
    
    public ControlGestion_CtgProducto(Ctg_ProductoDao modelo_ctgp, VistaGestion_CtgProducto vista_ctgp) {
        this.modelo_ctgp = modelo_ctgp;
        this.vista_ctgp = vista_ctgp;

        vista_ctgp.setVisible(true);
        vista_ctgp.setTitle("Rutinas Registradas - Nexo Gym");
        vista_ctgp.setResizable(false);
        vista_ctgp.setLocation(611, 159);
        vista_ctgp.setClosable(true);
        vista_ctgp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();
        mostrarDatosTabla("");

    }
    
    public void funcionalidad() {
        vista_ctgp.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                mostrarDatosTabla(vista_ctgp.getTxt_buscar().getText());

            }

        });

        vista_ctgp.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista_ctgp.getBt_eliminar().addActionListener(l -> sentenciaDelete());

    }
    
    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;

                } else {
                    return false;
                }
            }

        };

        vista_ctgp.getJtable_ctgProductos().setModel(tb_model);
        vista_ctgp.getJtable_ctgProductos().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_ctgp.getJtable_ctgProductos().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_ctgp.getJtable_ctgProductos().getColumnModel().getColumn(0).setPreferredWidth(10);
        vista_ctgp.getJtable_ctgProductos().getColumnModel().getColumn(2).setPreferredWidth(200);

    }
    
    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);

        modelo_ctgp.mostrarDatosJoin(aguja).stream().forEach((ctg) -> {

            Object[] contenido = {ctg.getId_ctgp(), ctg.getNombre(), ctg.getDescripcion()};

            tb_model.addRow(contenido);

        });
    }
    
    private void ventanaActualizar() {

        int fila = vista_ctgp.getJtable_ctgProductos().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_ctgp = (int) vista_ctgp.getJtable_ctgProductos().getValueAt(fila, columna);
            vista_ctgp.dispose();
            VistaActualizar_CtgProducto vista = new VistaActualizar_CtgProducto();
            ControlActualizar_CtgProducto control = new ControlActualizar_CtgProducto(modelo_ctgp, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_ctgp, "Seleccione el registro a verificar");
        }

    }
    
    private void sentenciaDelete() {

        int fila = vista_ctgp.getJtable_ctgProductos().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_ctgp = (int) vista_ctgp.getJtable_ctgProductos().getValueAt(fila, columna);
            String nombre = modelo_ctgp.mostrarDatos().stream().filter(u -> u.getId_ctgp() == id_ctgp).findAny().get().getNombre();

            int resp = JOptionPane.showConfirmDialog(vista_ctgp, "Seguro desea eliminar la Categoria : " + nombre, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_ctgp.eliminar(id_ctgp);
                JOptionPane.showMessageDialog(vista_ctgp, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista_ctgp, "Seleccione el registro a eliminar");
        }
    }

    
}
