package modelo.dao;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import modelo.conexion.PGConexion;
import modelo.vo.ProductoVo;
import org.postgresql.util.Base64;

public class ProductoDao extends ProductoVo {

    private PGConexion conecta = new PGConexion();

    public ProductoDao() {
    }

    public ProductoDao(int id_prod, String nombre, int id_ctgp, String id_proveedor, String descripcion, double precio_u, int stock, Image foto) {
        super(id_prod, nombre, id_ctgp, id_proveedor, descripcion, precio_u, stock, foto);
    }

    public Image obtenerImagen(byte[] bytes) throws IOException {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        ImageInputStream lis = ImageIO.createImageInputStream(source);
        reader.setInput(lis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0, param);

    }

    public List<ProductoVo> mostrarDatos(String aguja) {

        List<ProductoVo> lista_productos = new ArrayList<>();
        String sql = "select * from producto where upper(nombre) like upper('%" + aguja + "%')or\n"
                + "upper(descripcion) like upper('%" + aguja + "%') or\n"
                + "to_char(stock,'9999') like '%" + aguja + "%' \n"
                + "order by 1";
        ResultSet rs = conecta.consulta(sql);

        try {

            byte[] bf;
            while (rs.next()) {

                ProductoVo pr = new ProductoVo();

                pr.setId_prod(rs.getInt("id_prod"));
                pr.setNombre(rs.getString("nombre"));
                pr.setId_ctgp(rs.getInt("id_ctgp"));
                pr.setId_proveedor(rs.getString("id_proveedor"));
                pr.setDescripcion(rs.getString("descripcion"));
                pr.setPrecio_u(rs.getDouble("precio_u"));
                pr.setStock(rs.getInt("stock"));
                //Decodificacion de los bytes
                bf = rs.getBytes("imagen");
                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);

                    try {
                        pr.setFoto(obtenerImagen(bf));
                    } catch (IOException ex) {
                        Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                lista_productos.add(pr);

            }

            rs.close();//cerramos conexion base.
            return lista_productos;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ProductoVo> mostrarDatosJoin(int id) {

        List<ProductoVo> lista_productos = new ArrayList<>();
        String sql = "select p.id_prod , p.nombre \"Producto\", c.nombre \"Categoria\", \n"
                + "pr.id_proveedor, pr.nombre \"Proveedor\",p.descripcion,p.precio_u,p.stock,p.imagen\n"
                + "from producto p\n"
                + "join ctg_producto c using(id_ctgp)\n"
                + "join proveedor pr using(id_proveedor)\n"
                + "where p.id_prod = " + id + ";";
        ResultSet rs = conecta.consulta(sql);

        try {

            byte[] bf;
            while (rs.next()) {

                ProductoVo pr = new ProductoVo();

                pr.setId_prod(rs.getInt(1));
                pr.setNombre(rs.getString(2));
                pr.setNombre_catgoria(rs.getString(3));
                pr.setRUC_proveedor(rs.getString(4));
                pr.setNombre_proveedor(rs.getString(5));
                pr.setDescripcion(rs.getString(6));
                pr.setPrecio_u(rs.getDouble(7));
                pr.setStock(rs.getInt(8));
                //Decodificacion de los bytes
                bf = rs.getBytes(9);
                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);

                    try {
                        pr.setFoto(obtenerImagen(bf));
                    } catch (IOException ex) {
                        Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                lista_productos.add(pr);

            }

            rs.close();//cerramos conexion base.
            return lista_productos;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ProductoVo> mostrarDatosJoin(String nombre, int id) {

        List<ProductoVo> lista_productos = new ArrayList<>();
        String sql = "select id_prod, nombre\n"
                + "from producto \n"
                + "where upper(nombre) = upper('" + nombre + "') and not id_prod = " + id;
        ResultSet rs = conecta.consulta(sql);

        try {

            while (rs.next()) {

                ProductoVo pr = new ProductoVo();

                pr.setId_prod(rs.getInt(1));
                pr.setNombre(rs.getString(2));

                lista_productos.add(pr);

            }

            rs.close();//cerramos conexion base.
            return lista_productos;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private BufferedImage imgBimage(Image img) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;

        }
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGR = bi.createGraphics();
        bGR.drawImage(img, 0, 0, null);
        bGR.dispose();
        return bi;

    }

    public String grabarFoto() {

        String foto64 = null;/*cambiamos el archivo a base 64
        esto permite formatear la foto y evitar conflictos con el formato
        de la base de datos*/


        try {

            BufferedImage img = imgBimage(getFoto());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            foto64 = Base64.encodeBytes(imgb);

        } catch (IOException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return foto64;

    }

    public boolean insertar() {
        String sql;
        if (getFoto() != null) {
            sql = "INSERT INTO producto(\n"
                    + "nombre, id_ctgp, id_proveedor, descripcion, precio_u, stock, imagen)\n"
                    + "VALUES ('" + getNombre() + "','" + getId_ctgp() + "','" + getId_proveedor() + "','" + getDescripcion()
                    + "','" + getPrecio_u() + "','" + getStock() + "','" + grabarFoto() + "');";
        } else {
            sql = "INSERT INTO producto(\n"
                    + "nombre, id_ctgp, id_proveedor, descripcion, precio_u, stock)\n"
                    + "VALUES ('" + getNombre() + "','" + getId_ctgp() + "','" + getId_proveedor() + "','" + getDescripcion()
                    + "','" + getPrecio_u() + "','" + getStock() + "');";

        }
        return conecta.accion(sql);
    }

    public boolean modificar(int identificador) {

        String sql;
        if (getFoto() != null) {
            sql = "UPDATE producto set \"nombre\"='" + getNombre() + "',\"id_ctgp\"='" + getId_ctgp()
                    + "',\"id_proveedor\"='" + getId_proveedor() + "',\"descripcion\"='" + getDescripcion() + "',\"precio_u\"='" + getPrecio_u()
                    + "',\"stock\"='" + getStock() + "',\"imagen\"='" + grabarFoto() + "'"
                    + "WHERE \"id_prod\"='" + identificador + "'";
        } else {
            sql = "UPDATE producto set \"nombre\"='" + getNombre() + "',\"id_ctgp\"='" + getId_ctgp()
                    + "',\"id_proveedor\"='" + getId_proveedor() + "',\"descripcion\"='" + getDescripcion() + "',\"precio_u\"='" + getPrecio_u()
                    + "',\"stock\"='" + getStock() + "'"
                    + "WHERE \"id_prod\"='" + identificador + "'";
        }

        return conecta.accion(sql);
    }

    public boolean modificarStock(int newstock, int id_prod) {

        String sql;
        sql = "UPDATE producto set \"stock\"='" + newstock + "'"
                + "WHERE \"id_prod\"='" + id_prod + "'";

        return conecta.accion(sql);
    }

    public boolean eliminar(int identificador) {

        String sql = "delete from producto where \"id_prod\"='" + identificador + "'";

        return conecta.accion(sql);
    }

}
