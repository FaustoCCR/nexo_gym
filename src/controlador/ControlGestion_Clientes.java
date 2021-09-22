package controlador;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import vista.VistaGestion_Clientes;

public class ControlGestion_Clientes {

    private ClienteDao modelo_cliente;
    private VistaGestion_Clientes vista_cliente;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Membresia", "Precio", "Inicia", "Termina", "Estado Pago"};

    public ControlGestion_Clientes(ClienteDao modelo_cliente, VistaGestion_Clientes vista_cliente) {
        this.modelo_cliente = modelo_cliente;
        this.vista_cliente = vista_cliente;

        vista_cliente.setVisible(true);
        vista_cliente.setTitle("Clientes Registrados - Nexo Gym");
        vista_cliente.setResizable(false);
        vista_cliente.setLocationRelativeTo(null);
        vista_cliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla();

    }

    public void funcionalidad() {

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;

                } else {
                    return false;
                }
            }

        };

        vista_cliente.getJtable_clientes().setModel(tb_model);
        vista_cliente.getJtable_clientes().getTableHeader().setFont(new Font("Trebuchet MS", 1, 14));//fuente que lleve la cabecera
        vista_cliente.getJtable_clientes().setShowHorizontalLines(true);//colocar lineas horizontales

    }

    private void mostrarDatosTabla() {

        tb_model.setRowCount(0);
        modelo_cliente.mostrarDatosJoin().stream().forEach((c) -> {

            Object[] contenido
                    = {c.getId_cliente(), c.getNombrecliente(), c.getMembresia(), c.getPago(), c.getF_inicio(), c.getF_vence(), estadoPaga(c.isEstado_pago())};

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

}
