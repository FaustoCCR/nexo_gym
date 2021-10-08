package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import modelo.dao.ProgramaClienteDao;
import vista.VistaActualizar_Cliente;
import vista.VistaGestion_Clientes;
import vista.VistaPrograma_Cliente;

public class ControlGestion_Clientes {

    private ClienteDao modelo_cliente;
    private VistaGestion_Clientes vista_cliente;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "DNI", "Nombre", "Membresia", "Precio", "Inicia", "Termina", "Estado Pago"};
    public static int id_cliente;

    public ControlGestion_Clientes(ClienteDao modelo_cliente, VistaGestion_Clientes vista_cliente) {
        this.modelo_cliente = modelo_cliente;
        this.vista_cliente = vista_cliente;

        vista_cliente.setVisible(true);
        vista_cliente.setTitle("Clientes Registrados - Nexo Gym");
        vista_cliente.setResizable(false);
        vista_cliente.setLocation(611, 159);
        vista_cliente.setClosable(true);
        vista_cliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla("");

    }

    public void funcionalidad() {

        vista_cliente.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                mostrarDatosTabla(vista_cliente.getTxt_buscar().getText());

            }

        });

        vista_cliente.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista_cliente.getBt_eliminar().addActionListener(l -> sentenciaDelete());
        vista_cliente.getBt_asignarRutina().addActionListener(l -> ventanaAsignarRutina());

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return true;
            }

        };

        vista_cliente.getJtable_clientes().setModel(tb_model);
        vista_cliente.getJtable_clientes().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_cliente.getJtable_clientes().setShowHorizontalLines(true);//colocar lineas horizontales
        vista_cliente.getJtable_clientes().getColumnModel().getColumn(0).setPreferredWidth(15);
        vista_cliente.getJtable_clientes().getColumnModel().getColumn(1).setPreferredWidth(70);
        vista_cliente.getJtable_clientes().getColumnModel().getColumn(2).setPreferredWidth(80);

    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);
        modelo_cliente.mostrarDatosJoin(aguja).stream().forEach((c) -> {

            Object[] contenido
                    = {c.getId_cliente(), c.getCedulapersona(), c.getNombrecliente(), c.getMembresia(), "$" + c.getPago(), c.getF_inicio(), c.getF_vence(), estadoPaga(c.isEstado_pago())};

            tb_model.addRow(contenido);

        });
    }

    private String estadoPaga(boolean estado) {

        String mensaje;

        if (estado == true) {
            mensaje = "Pagado";

        } else {
            mensaje = "Sin Pagar";
        }
        return mensaje;
    }

    private void ventanaActualizar() {

        int fila = vista_cliente.getJtable_clientes().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_cliente = (int) vista_cliente.getJtable_clientes().getValueAt(fila, columna);
            vista_cliente.dispose();
            VistaActualizar_Cliente vista = new VistaActualizar_Cliente();
            ControlActualizar_Cliente control = new ControlActualizar_Cliente(modelo_cliente, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_cliente, "Seleccione el registro a verificar");
        }

    }

    private void sentenciaDelete() {

        int fila = vista_cliente.getJtable_clientes().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_cliente = (int) vista_cliente.getJtable_clientes().getValueAt(fila, columna);
            String cliente = modelo_cliente.mostrarDatosJoin(id_cliente).stream().filter(u -> u.getId_cliente() == id_cliente).findAny().get().getNombrecliente();

            int resp = JOptionPane.showConfirmDialog(vista_cliente, "Seguro desea eliminar al cliente: " + cliente, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_cliente.eliminar(id_cliente);
                JOptionPane.showMessageDialog(vista_cliente, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista_cliente, "Seleccione el registro a eliminar");
        }
    }

    private void ventanaAsignarRutina() {

        int fila = vista_cliente.getJtable_clientes().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_cliente = (int) vista_cliente.getJtable_clientes().getValueAt(fila, columna);
            ProgramaClienteDao modelo_pgcliente = new ProgramaClienteDao();
            VistaPrograma_Cliente vista = new VistaPrograma_Cliente();
            ControlPrograma_Cliente control = new ControlPrograma_Cliente(modelo_pgcliente, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_cliente, "Seleccione el registro a asignar");
        }

    }

}
