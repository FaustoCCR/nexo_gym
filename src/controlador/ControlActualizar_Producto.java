package controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.dao.Ctg_ProductoDao;
import modelo.dao.ProductoDao;
import modelo.dao.ProveedorDao;
import modelo.vo.ProveedorVo;
import vista.VistaActualizar_Producto;
import vista.VistaAdministrador;

public class ControlActualizar_Producto {

    private ProductoDao modelo_producto;
    private VistaActualizar_Producto vista_producto;

    private ProveedorDao modelo_proveedor = new ProveedorDao();
    private Ctg_ProductoDao modelo_categoria = new Ctg_ProductoDao();
    private Border origin_border = new LineBorder(Color.gray, 1);
    private int id_prod;

    public ControlActualizar_Producto(ProductoDao modelo_producto, VistaActualizar_Producto vista) {
        this.modelo_producto = modelo_producto;
        this.vista_producto = vista;

        id_prod = ControlGestion_Productos.id_prod;

        vista.setVisible(true);
        vista.setTitle("Actualizar Productos - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int)(VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista.getWidth())/2,
                (int)(VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista.getHeight())/2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarCategorias();
        cargarDatosProducto();
    }

    public void funcionalidad() {

        vista_producto.getTxt_ruc().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarProveedor(vista_producto.getTxt_ruc().getText());
            }

        });
        vista_producto.getTxt_producto().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarProductorRegistrado(vista_producto.getTxt_producto().getText(), id_prod);
            }

        });

        vista_producto.getBt_cargar().addActionListener(l -> buscarFoto());
        vista_producto.getBt_actualizar().addActionListener(l -> actualizarProducto());
    }

    private void cargarDatosProducto() {

        modelo_producto.mostrarDatosJoin(id_prod).forEach((pr) -> {

            vista_producto.getTxt_ruc().setText(pr.getRUC_proveedor());
            vista_producto.getTxt_proveedor().setText(pr.getNombre_proveedor());
            vista_producto.getTxt_producto().setText(pr.getNombre());
            vista_producto.getCb_categoria().setSelectedItem(pr.getNombre_catgoria());
            vista_producto.getTxt_descripcion().setText(pr.getDescripcion());
            vista_producto.getTxt_preciou().setText(String.valueOf(pr.getPrecio_u()));
            vista_producto.getJspinner_stock().setValue(pr.getStock());
            Image img = pr.getFoto();
            if (img != null) {
                Image nimg = img.getScaledInstance(vista_producto.getJlfoto().getWidth(), vista_producto.getJlfoto().getHeight(), Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(nimg);
                vista_producto.getJlfoto().setIcon(icon);

            } else {
                vista_producto.getJlfoto().setText("No Imagen");
                vista_producto.getJlfoto().setHorizontalAlignment(SwingConstants.CENTER);
                vista_producto.getJlfoto().setVerticalAlignment(SwingConstants.CENTER);
//                vista_producto.getJlfoto().setIcon(null);
            }

        });

    }

    private boolean verificarProductorRegistrado(String nombre, int id) {

        /*Si ya exite un producto con el mismo nombre
        indicada, ya no se puede registrar otro*/
        if (!nombre.isEmpty()) {
            boolean respuesta = modelo_producto.mostrarDatosJoin(nombre, id).isEmpty();
            if (respuesta) {

                vista_producto.getTxt_producto().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            } else {
                vista_producto.getTxt_producto().setBorder(new LineBorder(Color.decode("#C33529"), 2));

            }
            return respuesta;
        }else{
            vista_producto.getTxt_producto().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            return false;
        }

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

    private void sentenciaUpdate() {

        String nombre = vista_producto.getTxt_producto().getText();
        int indexcb = vista_producto.getCb_categoria().getSelectedIndex();
        int id_ctgp = modelo_categoria.mostrarDatos().get(indexcb).getId_ctgp();
        String id_proveedor = vista_producto.getTxt_ruc().getText();
        String descripcion = vista_producto.getTxt_descripcion().getText();
        double precio_u = Double.parseDouble(vista_producto.getTxt_preciou().getText());
        int stock = Integer.parseInt(vista_producto.getJspinner_stock().getValue().toString());

        modelo_producto.setNombre(nombre);
        modelo_producto.setId_ctgp(id_ctgp);
        modelo_producto.setId_proveedor(id_proveedor);
        modelo_producto.setDescripcion(descripcion);
        modelo_producto.setPrecio_u(precio_u);
        modelo_producto.setStock(stock);
        modelo_producto.setStock(stock);

        //foto
        Icon icon = vista_producto.getJlfoto().getIcon();
        if (icon != null) {
            ImageIcon ic = (ImageIcon) icon;
            modelo_producto.setFoto(ic.getImage());
        }
        if (modelo_producto.modificar(id_prod)) {

            JOptionPane.showMessageDialog(vista_producto, "Producto Actualizado");

        } else {
            JOptionPane.showMessageDialog(vista_producto, "Error al Actualizar");
        }

    }

    private void restaurarBordes() {

        vista_producto.getTxt_ruc().setBorder(origin_border);
        vista_producto.getTxt_producto().setBorder(origin_border);
    }

    private void actualizarProducto() {

        if (validarRegistro()) {

            if (buscarProveedor(vista_producto.getTxt_ruc().getText())) {

                if (verificarProductorRegistrado(vista_producto.getTxt_producto().getText(), id_prod)) {

                    sentenciaUpdate();
                    restaurarBordes();

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
