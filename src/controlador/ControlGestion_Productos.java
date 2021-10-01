package controlador;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ProductoDao;
import vista.VistaGestion_Productos;

public class ControlGestion_Productos {

    private ProductoDao modelo;
    private VistaGestion_Productos vista;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "Nombre", "Descripción", "Precio U", "Stock", "Imagen"};

    public ControlGestion_Productos(ProductoDao modelo, VistaGestion_Productos vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setTitle("Clientes Registrados - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla();
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

        vista.getJtable_productos().setModel(tb_model);
        vista.getJtable_productos().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getJtable_productos().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getJtable_productos().getColumnModel().getColumn(0).setPreferredWidth(20);
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

    private void mostrarDatosTabla() {

        tb_model.setRowCount(0);

        modelo.mostrarDatos().stream().forEach((pr) -> {

            Object[] contenido = {pr.getId_prod(), pr.getNombre(), pr.getDescripcion(), pr.getPrecio_u(), pr.getStock(), revelarFoto(pr.getFoto())};

            tb_model.addRow(contenido);

        });
    }

}
