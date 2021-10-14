package controlador;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.PGConexion;
import modelo.dao.ProductoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.VistaActualizar_Producto;
import vista.VistaAdministrador;
import vista.VistaGestion_Productos;

public class ControlGestion_Productos {

    private ProductoDao modelo;
    private VistaGestion_Productos vista;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Descripción", "Precio U", "Stock", "Imagen"};
    public static int id_prod;

    public ControlGestion_Productos(ProductoDao modelo, VistaGestion_Productos vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setTitle("Productos Registrados - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        identificarRol();
        disenioTabla();

        mostrarDatosTabla("");
    }

    public void funcionalidad() {

        vista.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mostrarDatosTabla(vista.getTxt_buscar().getText());

            }

        });
        vista.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista.getBt_eliminar().addActionListener(l -> sentenciaDelete());
        vista.getBt_imprimir().addActionListener(l -> imprimirReporte());

    }

    private void identificarRol() {

        switch (ControlLogin.permiso) {

            case 2:

                vista.getBt_verificar().setVisible(false);
                vista.getBt_eliminar().setVisible(false);

                break;

        }
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

        vista.getJtable_productos().setModel(tb_model);
        vista.getJtable_productos().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getJtable_productos().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getJtable_productos().getColumnModel().getColumn(0).setPreferredWidth(8);
        vista.getJtable_productos().getColumnModel().getColumn(1).setPreferredWidth(110);
        vista.getJtable_productos().getColumnModel().getColumn(2).setPreferredWidth(140);
        vista.getJtable_productos().getColumnModel().getColumn(3).setPreferredWidth(40);
        vista.getJtable_productos().getColumnModel().getColumn(4).setPreferredWidth(25);
        vista.getJtable_productos().getColumnModel().getColumn(5).setPreferredWidth(100);
        vista.getJtable_productos().setDefaultRenderer(Object.class, new ImagenTabla());
        vista.getJtable_productos().setRowHeight(85);

    }

    private JLabel revelarFoto(Image foto) {
        Image img = foto;
        if (img != null) {
            Image nimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(nimg);
//            renderer.setIcon(icon);

            return new JLabel(icon);

        } else {
            JLabel lb = new JLabel("No Imagen");
            lb.setHorizontalAlignment(SwingConstants.CENTER);
            lb.setVerticalAlignment(SwingConstants.CENTER);
            return lb;
        }
    }

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);

        modelo.mostrarDatos(aguja).stream().forEach((pr) -> {

            Object[] contenido = {pr.getId_prod(), pr.getNombre(), pr.getDescripcion(), pr.getPrecio_u() + " $", pr.getStock(), revelarFoto(pr.getFoto())};

            tb_model.addRow(contenido);

        });
    }

    private void ventanaActualizar() {
        int fila = vista.getJtable_productos().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_prod = (int) vista.getJtable_productos().getValueAt(fila, columna);
            vista.dispose();
            VistaActualizar_Producto vista = new VistaActualizar_Producto();
            VistaAdministrador.getjDesktopPanePrincipal().add(vista);
            ControlActualizar_Producto control = new ControlActualizar_Producto(modelo, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a verificar");
        }
    }

    private void sentenciaDelete() {

        int fila = vista.getJtable_productos().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_producto = (int) vista.getJtable_productos().getValueAt(fila, columna);
            String producto = modelo.mostrarDatos("").stream().filter(u -> u.getId_prod() == id_producto).findAny().get().getNombre();

            int resp = JOptionPane.showConfirmDialog(vista, "Seguro desea eliminar el producto: " + producto, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo.eliminar(id_producto);
                JOptionPane.showMessageDialog(vista, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione el registro a eliminar");
        }
    }

    private void imprimirReporte() {

        PGConexion con = new PGConexion();

        try {
            Map<String, Object> parametros = new HashMap<>();
            String aguja = vista.getTxt_buscar().getText().trim();
            parametros.put("paguja", "%" + aguja + "%");
            parametros.put("pdetalle", subitituloReport(aguja));

            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/vista/reportes/ReporteProductos.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
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
