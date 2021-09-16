
package modelo.vo;


public class Cuerpo_VentaVo {
    
    //atributos7
    private int id_cp;
    private int id_ecb; // ----> FK
    private int id_producto; // ----> FK
    private int cantidad;
    private double costo_final;

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
    
    
    
}
