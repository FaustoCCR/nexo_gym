
package modelo.vo;

import java.sql.Date;


public class Cuerpo_VentaVo {
    
    //atributos
    private int id_cp;
    private int id_ecb; // ----> FK
    private int id_producto; // ----> FK
    private int cantidad;
    private double costo_final;
    
    
    //variables extras
    private String cliente;
    private String vendedor;
    private Date fecha_venta;
    private int nro_productos;
    private double total;
    
    public Cuerpo_VentaVo(){
    }

    public Cuerpo_VentaVo(int id_cp, int id_ecb, int id_producto, int cantidad, double costo_final) {
        this.id_cp = id_cp;
        this.id_ecb = id_ecb;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.costo_final = costo_final;
    }

    public int getId_cp() {
        return id_cp;
    }

    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }

    public int getId_ecb() {
        return id_ecb;
    }

    public void setId_ecb(int id_ecb) {
        this.id_ecb = id_ecb;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto_final() {
        return costo_final;
    }

    public void setCosto_final(double costo_final) {
        this.costo_final = costo_final;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getNro_productos() {
        return nro_productos;
    }

    public void setNro_productos(int nro_productos) {
        this.nro_productos = nro_productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
}
