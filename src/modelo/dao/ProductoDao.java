package modelo.dao;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import modelo.vo.UsuarioVo;
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

    public List<ProductoVo> mostrarDatos() {

        List<ProductoVo> lista_productos = new ArrayList<>();
        String sql = "select * from producto order by 1";
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
        String sql = "INSERT INTO producto(\n"
                + "nombre, id_ctgp, id_proveedor, descripcion, precio_u, stock, imagen)\n"
                + "VALUES ('" + getNombre() + "','" + getId_ctgp() + "','" + getId_proveedor() + "','" + getDescripcion()
                + "','" + getPrecio_u() + "','" + getStock() + "','" + grabarFoto() + "');";
        return conecta.accion(sql);
    }

}
