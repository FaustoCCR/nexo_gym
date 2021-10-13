package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.conexion.PGConexion;
import modelo.dao.ClienteDao;
import modelo.dao.Cuerpo_VentaDao;
import modelo.dao.Ecb_VentaDao;
import modelo.dao.ProductoDao;
import modelo.vo.ClienteVo;
import modelo.vo.ProductoVo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.VistaAdministrador;
import vista.VistaEditar_Venta;

public class ControlEditar_Venta {

    private Ecb_VentaDao modeloecb_venta;
    private Cuerpo_VentaDao modelocuerpo_venta;
    private ProductoDao modelo_producto = new ProductoDao();
    private ClienteDao modelo_cliente = new ClienteDao();
    private VistaEditar_Venta vista;
    private DefaultTableModel tb_model;
    private String[] columnas = {"Nro", "CD", "ID Producto", "Producto", "Cantidad", "Precio U", "Total"};
    private int id_ecb;
    private int id_user;
    private int item = 0;
    private int stock;
    private Border origin_border = new LineBorder(new Color(204, 204, 204), 1);

    public ControlEditar_Venta(Ecb_VentaDao modeloecb_venta, Cuerpo_VentaDao modelocuerpo_venta, VistaEditar_Venta vista) {
        this.modeloecb_venta = modeloecb_venta;
        this.modelocuerpo_venta = modelocuerpo_venta;
        this.vista = vista;

        id_user = ControlLogin.id_user;
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

        vista.getBt_retirar().addActionListener(l -> retirarProducto());
        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarCliente(vista.getTxt_cedula().getText());
            }

        });
        vista.getTxt_codproducto().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarProducto(vista.getTxt_codproducto().getText());

            }

        });
        vista.getBt_agregar().addActionListener(l -> agregarProducto());
        vista.getBt_actualizarventa().addActionListener(l -> realizarVenta());
        vista.getBt_imprimir().addActionListener(l->imprimirReporte());

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

        vista.getTb_detalleventa().setModel(tb_model);
        vista.getTb_detalleventa().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getTb_detalleventa().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getTb_detalleventa().getColumnModel().getColumn(0).setPreferredWidth(15);
        vista.getTb_detalleventa().getColumnModel().getColumn(1).setPreferredWidth(15);
        vista.getTb_detalleventa().setRowHeight(20);

    }

    private boolean buscarCliente(String cedula) {

        boolean busqueda;

        Predicate<ClienteVo> cedula_cliente = cl -> cl.getCedulapersona().equals(cedula);

        busqueda = modelo_cliente.mostrarDatosJoin("").stream().anyMatch(cedula_cliente);

        if (busqueda == true) {

            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            modelo_cliente.mostrarDatosJoin("").stream().filter(cedula_cliente).forEach((t) -> {
                vista.getTxt_persona().setText(t.getNombrecliente());

            });

        } else {
            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxt_persona().setText("");

        }
        return busqueda;

    }

    private boolean buscarProducto(String id_producto) {

        boolean busqueda = true;

        if (!id_producto.isEmpty()) {

            int id = Integer.parseInt(id_producto);
            Predicate<ProductoVo> bc_producto = pr -> pr.getId_prod() == id;

            busqueda = modelo_producto.mostrarDatos("").stream().anyMatch(bc_producto);

            if (busqueda == true) {

                vista.getTxt_codproducto().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
                modelo_producto.mostrarDatos("").stream().filter(bc_producto).forEach((pr) -> {

                    vista.getTxt_producto().setText(pr.getNombre());
                    vista.getTxt_preciou().setText(String.valueOf(pr.getPrecio_u()));
                    stock = pr.getStock();
                    vista.getTxt_stock().setText(String.valueOf(stock));
                    SpinnerModel sm = new SpinnerNumberModel(0, 0, stock, 1);//(valor inicial,mínimo,máximo,incremento)
                    vista.getJspinner_cantidad().setModel(sm);

                });

            } else {
                vista.getTxt_codproducto().setBorder(new LineBorder(Color.decode("#C33529"), 2));
                vista.getTxt_producto().setText("");
                vista.getTxt_preciou().setText("");
                vista.getTxt_stock().setText("");

            }

        } else {
            busqueda = false;
            vista.getTxt_codproducto().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxt_producto().setText("");
            vista.getTxt_preciou().setText("");
            vista.getTxt_stock().setText("");
        }

        return busqueda;

    }

    private void calcularTotal() {

        double totalpagar = 0;
        final int columna = 6;

        for (int i = 0; i < vista.getTb_detalleventa().getRowCount(); i++) {

            totalpagar += (double) vista.getTb_detalleventa().getValueAt(i, columna);
        }

        vista.getTxt_total().setText(String.valueOf(totalpagar));

    }

    private void agregarProducto() {

        String producto = vista.getTxt_producto().getText();
        if (!producto.isEmpty()) {

            /* validar el stock*/
            if (stock > 0) {
                item++;//---Nro
                int id = Integer.parseInt(vista.getTxt_codproducto().getText());
                int cantidad = (int) vista.getJspinner_cantidad().getValue();
                double precio_u = Double.parseDouble(vista.getTxt_preciou().getText());
                double total = Math.round((cantidad * precio_u) * 100.0) / 100.0;
                if (cantidad > 0) {

                    Object[] filas = {item, "?", id, producto, cantidad, precio_u, total};
                    tb_model.addRow(filas);
                    calcularTotal();

                    /* restauramos campos*/
                    vista.getTxt_producto().setText("");
                    vista.getTxt_preciou().setText("");
                    vista.getTxt_stock().setText("");
                    vista.getTxt_codproducto().setText("");
                    vista.getTxt_codproducto().requestFocus();
                    vista.getTxt_codproducto().setBorder(origin_border);
                } else {
                    JOptionPane.showMessageDialog(vista, "Ingrese la cantidad");
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Revisar Stock");
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado", "Advertencia", 0);
        }

    }

    private void cargarDetallesVenta() {

        tb_model.setRowCount(0);

        Holder<Integer> i = new Holder<>(1);

        modelocuerpo_venta.mostrarDatosJoinEspecifico(id_ecb).forEach((cv) -> {

            vista.getTxt_cedula().setText(cv.getDni());
            vista.getTxt_persona().setText(cv.getCliente());
            vista.getTxt_vendedor().setText(cv.getVendedor());
            vista.getJdate_venta().setDate(cv.getFecha_venta());

            //-------------llenado de tabla-------------------//
            Object[] contenido = {i.value, cv.getId_cp(), cv.getId_producto(), cv.getNombre_producto(), cv.getCantidad(), cv.getPrecio_u(), cv.getTotal()};

            tb_model.addRow(contenido);

            i.value++;
        });

        calcularTotal();

    }

    private void devolucionStock(int fila) {

        /*Regresa la cantidad de productos al stock
         */
        int id_prod = Integer.parseInt(vista.getTb_detalleventa().getValueAt(fila, 2).toString());
        int cantidad = Integer.parseInt(vista.getTb_detalleventa().getValueAt(fila, 4).toString());

        int stock = modelo_producto.mostrarDatos("").stream().filter(pr -> pr.getId_prod() == id_prod).findAny().get().getStock();
        int newstock = stock + cantidad;
        modelo_producto.modificarStock(newstock, id_prod);

    }

    private void eliminarProductoDetalle(int fila) {
        final int columna = 1; // ---> id cuerpo venta

        int id_cp = Integer.parseInt(vista.getTb_detalleventa().getValueAt(fila, columna).toString());
        modelocuerpo_venta.eliminarPK(id_cp);

    }

    private void retirarProducto() {
        int seleccion = vista.getTb_detalleventa().getSelectedRow();
        final int columna = 1;
        String newValor = vista.getTb_detalleventa().getValueAt(seleccion, columna).toString();
        if (seleccion != -1) {
            if (newValor.equals("?")) {
                tb_model.removeRow(seleccion);
                calcularTotal();

            } else {
                int resp = JOptionPane.showConfirmDialog(vista, "Se retirara este producto de esta venta", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    devolucionStock(seleccion);
                    eliminarProductoDetalle(seleccion);
                    cargarDetallesVenta();
                }
            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione en la tabla el producto", "Mensaje", 1);
        }
    }

    private int id_Cliente() {
        String cedula = vista.getTxt_cedula().getText();
        return modelo_cliente.mostrarDatosJoin("").stream().filter(cl -> cl.getCedulapersona().equals(cedula)).findAny().get().getId_cliente();

    }

    private void sentenciaUpdateEncabezado() {
        /*Si se desean hacer cambios del cliente al que se
        realizo la venta y vendedor que edita la venta
         */

        int id_cliente = id_Cliente();
        //------------------------- FECHA -----------------------------//
        Instant instant = vista.getJdate_venta().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());
        //-------------------------      -----------------------------//
        modeloecb_venta.setId_cliente(id_cliente);
        modeloecb_venta.setId_user(id_user);
        modeloecb_venta.setFecha_venta(fecha);

        if (modeloecb_venta.modificar(id_ecb)) {

            System.out.println("Ecb Actualizado");

        } else {
            System.out.println("Error al Actualizar Ecb");
        }

    }

    private void sentenciaInsert_CuerpoVenta() {

        for (int i = 0; i < vista.getTb_detalleventa().getRowCount(); i++) {

            if (vista.getTb_detalleventa().getValueAt(i, 1).toString().equals("?")) {
                int id_prod = (int) vista.getTb_detalleventa().getValueAt(i, 2);
                int cantidad = (int) vista.getTb_detalleventa().getValueAt(i, 4);
                double precio = (double) vista.getTb_detalleventa().getValueAt(i, 6);

                modelocuerpo_venta.setId_ecb(id_ecb);
                modelocuerpo_venta.setId_producto(id_prod);
                modelocuerpo_venta.setCantidad(cantidad);
                modelocuerpo_venta.setCosto_final(precio);

                if (modelocuerpo_venta.insertar()) {

                    System.out.println("Detalle Registrado");

                } else {
                    System.out.println("Error al Guardar Detalle");
                }
            }

        }

    }

    private void actualizarStock() {

        /*Merma el stock de acuerdo a la cantidad de productos que se se 
        aplico en la venta
         */
        for (int i = 0; i < tb_model.getRowCount(); i++) {

            int id_prod = Integer.parseInt(vista.getTb_detalleventa().getValueAt(i, 2).toString());
            int cantidad = Integer.parseInt(vista.getTb_detalleventa().getValueAt(i, 4).toString());

            int stock = modelo_producto.mostrarDatos("").stream().filter(pr -> pr.getId_prod() == id_prod).findAny().get().getStock();
            int newstock = stock - cantidad;
            modelo_producto.modificarStock(newstock, id_prod);

        }
    }

    private void realizarVenta() {

        String cedula_cliente = vista.getTxt_cedula().getText();
        if (!cedula_cliente.isEmpty()) {
            if (buscarCliente(vista.getTxt_cedula().getText())) {
                if (vista.getTb_detalleventa().getRowCount() > 0) {

                    sentenciaUpdateEncabezado();
                    sentenciaInsert_CuerpoVenta();
                    actualizarStock();
                    JOptionPane.showMessageDialog(vista, "Venta realizada exitosamente", "Mensaje", 1);
//                    reiniciarCampos();

                } else {
                    JOptionPane.showMessageDialog(vista, "No se encuentran productos a registrar", "Mensaje", 1);
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Cliente no registrado", "Aviso", 0);
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Ingrese el DNI del cliente", "Aviso", 0);
        }

    }

    private void imprimirReporte() {

        PGConexion con = new PGConexion();

        try {

            /*Creacion de un mapa
            asigna un dato a los diferentes 
            parámetros*/
            Map<String, Object> parametros = new HashMap<>();
            int id_factura = id_ecb;

//            put( ?,  ?)---> el nombre del parametro(tipo de dato), la variable que recibe 
            parametros.put("p_idfactura", id_factura);
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/vista/reportes/Factura.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControlGestion_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
