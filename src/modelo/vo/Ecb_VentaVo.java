
package modelo.vo;

import java.sql.Date;


public class Ecb_VentaVo {
    
    //atributos
    private int id_ecb;
    private int id_cliente; // ----> FK
    private int id_user; // ----> FK
    private Date fecha_venta; // ----> FK

    public Ecb_VentaVo(int id_ecb, int id_cliente, int id_user, Date fecha_venta) {
        this.id_ecb = id_ecb;
        this.id_cliente = id_cliente;
        this.id_user = id_user;
        this.fecha_venta = fecha_venta;
    }

    public int getId_ecb() {
        return id_ecb;
    }

    public void setId_ecb(int id_ecb) {
        this.id_ecb = id_ecb;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }
    
    
}
