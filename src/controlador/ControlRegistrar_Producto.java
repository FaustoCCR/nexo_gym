package controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.dao.Ctg_ProductoDao;
import modelo.dao.ProductoDao;
import modelo.dao.ProveedorDao;
import modelo.vo.ProveedorVo;
import vista.VistaRegistrar_Producto;

public class ControlRegistrar_Producto {

    private ProductoDao modelo_producto;
    private VistaRegistrar_Producto vista_producto;

    private ProveedorDao modelo_proveedor = new ProveedorDao();
    private Ctg_ProductoDao modelo_categoria = new Ctg_ProductoDao();
    private Border origin_border = new LineBorder(Color.gray, 1);

    public ControlRegistrar_Producto(ProductoDao modelo_producto, VistaRegistrar_Producto vista_producto) {
        this.modelo_producto = modelo_producto;
        this.vista_producto = vista_producto;

        vista_producto.setVisible(true);
        vista_producto.setTitle("Registro de Productos - Nexo Gym");
        vista_producto.setResizable(false);
        vista_producto.setLocationRelativeTo(null);
        vista_producto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarCategorias();
    }

    public void funcionalidad() {

        vista_producto.getTxt_ruc().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarProveedor(vista_producto.getTxt_ruc().getText());
            }

        });

        vista_producto.getBt_registrar().addActionListener(l -> registrarProducto());
        vista_producto.getBt_cargar().addActionListener(l -> buscarFoto());

    }

    private boolean buscarProveedor(String RUC) {

        boolean busqueda;

        Predicate<ProveedorVo> predicate_ruc = p -> p.getId_proveedor().equals(RUC);

        busqueda = modelo_proveedor.mostrarDatos().stream().anyMatch(predicate_ruc);

        if (busqueda == true) {
            vista_producto.getTxt_ruc().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            String proveedor = modelo_proveedor.mostrarDatos().stream().filter(predicate_ruc).findAny().get().getNombre();
            vista_producto.getTxt_proveedor().setText(proveedor);

        } else {

            vista_producto.getTxt_ruc().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista_producto.getTxt_proveedor().setText("");

        }

        return busqueda;

    }

    private boolean verificarProducto() {

        return modelo_producto.mostrarDatos().stream().noneMatch(pr -> pr.getNombre().equalsIgnoreCase(vista_producto.getTxt_producto().getText()));

    }

    private void cargarCategorias() {

        modelo_categoria.mostrarDatos().stream().forEach((ct) -> {

            vista_producto.getCb_categoria().addItem(ct.getNombre());

        });
    }

    private boolean validarRegistro() {

        String ruc = vista_producto.getTxt_ruc().getText();
        String producto = vista_producto.getTxt_producto().getText();
        String descripcion = vista_producto.getTxt_descripcion().getText();
        String precio_u = vista_producto.getTxt_preciou().getText();

        boolean validacion = true;

        if (ruc.isEmpty() || producto.isEmpty() || descripcion.isEmpty() || precio_u.isEmpty()) {

            validacion = false;

        }
        return validacion;
    }

    private void buscarFoto() {
        JFileChooser jfc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        jfc.setFileFilter(filter);

        int seleccion = jfc.showOpenDialog(null);
        jfc.setDialogTitle("Buscador de archivos");

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            try {
                Image miImagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
                        vista_producto.getJlfoto().getWidth(), vista_producto.getJlfoto().getHeight(),
                        Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(miImagen);
                vista_producto.getJlfoto().setIcon(icon);
                vista_producto.getJlfoto().updateUI();/* Metodo alternativo depende de la version */
            } catch (IOException ex) {
                Logger.getLogger(ControlRegistrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void sentenciaInsert() {

        String producto = vista_producto.getTxt_producto().getText();
        int id_categoria = modelo_categoria.mostrarDatos().get(vista_producto.getCb_categoria().getSelectedIndex()).getId_ctgp();
        String ruc = vista_producto.getTxt_ruc().getText();
        String descripcion = vista_producto.getTxt_descripcion().getText().trim();
        double precio_u = Double.parseDouble(vista_producto.getTxt_preciou().getText());
        int stock = (int) vista_producto.getJspinner_stock().getValue();

        modelo_producto.setNombre(producto);
        modelo_producto.setId_ctgp(id_categoria);
        modelo_producto.setId_proveedor(ruc);
        modelo_producto.setDescripcion(descripcion);
        modelo_producto.setPrecio_u(precio_u);
        modelo_producto.setStock(stock);
        ImageIcon ic = (ImageIcon) vista_producto.getJlfoto().getIcon();
        modelo_producto.setFoto(ic.getImage());

        if (modelo_producto.insertar()) {
            JOptionPane.showMessageDialog(vista_producto, "Producto Registrado");

        } else {
            JOptionPane.showMessageDialog(vista_producto, "Error al Guardar");
        }

    }

    private void reiniciarCampos() {

        vista_producto.getTxt_ruc().setText("");
        vista_producto.getTxt_proveedor().setText("");
        vista_producto.getTxt_producto().setText("");
        vista_producto.getCb_categoria().setSelectedIndex(0);
        vista_producto.getTxt_descripcion().setText("");
        vista_producto.getTxt_preciou().setText("");
        vista_producto.getJspinner_stock().setValue(0);
        vista_producto.getJlfoto().setIcon(null);

        vista_producto.getTxt_ruc().setBorder(origin_border);
    }

    private void registrarProducto() {

        if (validarRegistro()) {

            if (buscarProveedor(vista_producto.getTxt_ruc().getText())) {

                if (verificarProducto()) {

                    sentenciaInsert();
                    reiniciarCampos();

                } else {
                    JOptionPane.showMessageDialog(vista_producto, "El producto ya se encuentra registrado", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    vista_producto.getTxt_producto().grabFocus();
                }

            } else {
                JOptionPane.showMessageDialog(vista_producto, "RUC no identificado", "Mensaje", JOptionPane.ERROR_MESSAGE);
                vista_producto.getTxt_ruc().grabFocus();
            }

        } else {

            JOptionPane.showMessageDialog(vista_producto, "Rellenar todos los campos ", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}
