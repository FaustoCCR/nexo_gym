
package modelo.vo;


public class MemebresiaVo {
    
    //atributos
    private int id_membresia;
    private String nombre;
    private String descripcion;
    private double descuento;

    public MemebresiaVo(int id_membresia, String nombre, String descripcion, double descuento) {
        this.id_membresia = id_membresia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
    }

    public int getId_membresia() {
        return id_membresia;
    }

    public void setId_membresia(int id_membresia) {
        this.id_membresia = id_membresia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    
}
