package controlador;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.dao.Cuerpo_VentaDao;
import modelo.dao.Ecb_VentaDao;
import vista.VistaAdministrador;
import vista.VistaEditar_Venta;

public class ControlEditar_Venta {

    private Ecb_VentaDao modeloecb_venta;
    private Cuerpo_VentaDao modelocuerpo_venta;
    private VistaEditar_Venta vista;
    private DefaultTableModel tb_model;
    private String[] columnas = {"Nro", "ID", "Producto", "Cantidad", "Precio U", "Total"};
    private int id_ecb;
    private double total_pagar = 0;

    public ControlEditar_Venta(Ecb_VentaDao modeloecb_venta, Cuerpo_VentaDao modelocuerpo_venta, VistaEditar_Venta vista) {
        this.modeloecb_venta = modeloecb_venta;
        this.modelocuerpo_venta = modelocuerpo_venta;
        this.vista = vista;

        id_ecb = ControlGestion_Ventas.id_ecb;

        vista.setVisible(true);
        vista.setTitle("Edición de la venta - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista.getJdate_venta().getDateEditor().setEnabled(false);

        disenioTabla();
        cargarDetallesVenta();
    }

    public void funcionalidad() {

    }

    private void disenioTabla() {

        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;

                } else {
                    return false;
                }
            }

        };

        vista.getTb_detalleventa().setModel(tb_model);
        vista.getTb_detalleventa().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getTb_detalleventa().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getTb_detalleventa().getColumnModel().getColumn(0).setPreferredWidth(15);
        vista.getTb_detalleventa().getColumnModel().getColumn(1).setPreferredWidth(15);
        vista.getTb_detalleventa().setRowHeight(20);

    }

    private void cargarDetallesVenta() {
        
        
        Holder<Integer> i = new Holder<>(1);

        modelocuerpo_venta.mostrarDatosJoinEspecifico(id_ecb).forEach((cv) -> {

            vista.getTxt_cedula().setText(cv.getDni());
            vista.getTxt_persona().setText(cv.getCliente());
            vista.getTxt_vendedor().setText(cv.getVendedor());
            vista.getJdate_venta().setDate(cv.getFecha_venta());

            //-------------llenado de tabla-------------------//
            double total = cv.getTotal();
            total_pagar += total;
            Object[] contenido = {i.value, cv.getId_producto(), cv.getNombre_producto(), cv.getCantidad(), cv.getPrecio_u(), total};
            
            tb_model.addRow(contenido);
            
            i.value++;
        });
        
        vista.getTxt_total().setText(String.valueOf(total_pagar));
        

    }
}
