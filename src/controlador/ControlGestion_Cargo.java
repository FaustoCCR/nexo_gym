package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.CargoDao;
import vista.VistaActualizar_Cargo;
import vista.VistaAdministrador;
import vista.VistaGestion_Cargo;

public class ControlGestion_Cargo {

    private CargoDao modelo_cargo;
    private VistaGestion_Cargo vista_cargo;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"Id", "Nombre", "Descripcion"};
    public static int id_cargo;

    public ControlGestion_Cargo(CargoDao modelo_cargo, VistaGestion_Cargo vista_cargo) {
        this.modelo_cargo = modelo_cargo;
        this.vista_cargo = vista_cargo;

        vista_cargo.setVisible(true);
        vista_cargo.setTitle("Cargos Registrados - Nexo Gym");
        vista_cargo.setResizable(false);
        vista_cargo.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista_cargo.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista_cargo.getHeight()) / 2);
        vista_cargo.setClosable(true);
        vista_cargo.setIconifiable(true);
        vista_cargo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();
        mostrarDatosTabla("");

    }

    public void funcionalidad() {
        vista_cargo.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mostrarDatosTabla(vista_cargo.getTxt_buscar().getText());
            }
        });
        vista_cargo.getBt_eliminar().addActionListener(l -> eliminarCargo());
        vista_cargo.getBt_verificar().addActionListener(l -> ventanaActualizar());
    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;

            }
        };
        vista_cargo.getJtable_cargos().setModel(tb_model);
        vista_cargo.getJtable_cargos().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_cargo.getJtable_cargos().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_cargo.getJtable_cargos().getColumnModel().getColumn(0).setPreferredWidth(20);
    }

    private void mostrarDatosTabla(String aguja) {
        tb_model.setRowCount(0);
        modelo_cargo.mostrarDatos(aguja).stream().forEach((c) -> {
            Object[] contenido
                    = {c.getId_cargo(), c.getNombre(), c.getDescripcion()};
            tb_model.addRow(contenido);
        });
    }

    private void ventanaActualizar() {
        int fila = vista_cargo.getJtable_cargos().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_cargo = (int) vista_cargo.getJtable_cargos().getValueAt(fila, columna);
            vista_cargo.dispose();

            VistaActualizar_Cargo vista = new VistaActualizar_Cargo();
            VistaAdministrador.getjDesktopPanePrincipal().add(vista);
            ControlActualizar_Cargo control = new ControlActualizar_Cargo(modelo_cargo, vista);
            control.funcionalidad();
        } else {
            JOptionPane.showMessageDialog(vista_cargo, "Seleccione el registro a verificar");
        }

    }

    private void eliminarCargo() {
        int fila = vista_cargo.getJtable_cargos().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {
            int id_cargo = (int) vista_cargo.getJtable_cargos().getValueAt(fila, columna);
            String nombre = modelo_cargo.mostrarDatos("").stream().filter(u -> u.getId_cargo() == id_cargo).findAny().get().getNombre();
            int resp = JOptionPane.showConfirmDialog(vista_cargo, "Seguro desea eliminar cargo : " + nombre, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                modelo_cargo.eliminar(id_cargo);
                JOptionPane.showMessageDialog(vista_cargo, "Registro Eliminado");
                mostrarDatosTabla("");
            }
        } else {
            JOptionPane.showMessageDialog(vista_cargo, "Seleccione el registro a eliminar");
        }
    }
}
