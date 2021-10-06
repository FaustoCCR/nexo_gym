package modelo.vo;

import java.awt.Image;

public class ProductoVo {

    //atributos
    private int id_prod;
    private String nombre;
    private int id_ctgp; // ----> FK
    private String id_proveedor; // ----> FK
    private String descripcion;
    private double precio_u;
    private int stock;
    private Image foto;

    //variables extras
    private String RUC_proveedor;
    private String nombre_catgoria;
    private String nombre_proveedor;

    public ProductoVo() {
    }

    public ProductoVo(int id_prod, String nombre, int id_ctgp, String id_proveedor, String descripcion, double precio_u, int stock, Image foto) {
        this.id_prod = id_prod;
        this.nombre = nombre;
        this.id_ctgp = id_ctgp;
        this.id_proveedor = id_proveedor;
        this.descripcion = descripcion;
        this.precio_u = precio_u;
        this.stock = stock;
        this.foto = foto;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_ctgp() {
        return id_ctgp;
    }

    public void setId_ctgp(int id_ctgp) {
        this.id_ctgp = id_ctgp;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_u() {
        return precio_u;
    }

    public void setPrecio_u(double precio_u) {
        this.precio_u = precio_u;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public String getNombre_catgoria() {
        return nombre_catgoria;
    }

    public void setNombre_catgoria(String nombre_catgoria) {
        this.nombre_catgoria = nombre_catgoria;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getRUC_proveedor() {
        return RUC_proveedor;
    }

    public void setRUC_proveedor(String RUC_proveedor) {
        this.RUC_proveedor = RUC_proveedor;
    }

}
