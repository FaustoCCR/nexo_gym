
package modelo.vo;


public class Ctg_ProductoVo {
    
    //atributos
    private int id_ctgp;
    private String nombre;
    private String descripcion;

    public Ctg_ProductoVo() {
    }

    public Ctg_ProductoVo(int id_ctgp, String nombre, String descripcion) {
        this.id_ctgp = id_ctgp;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_ctgp() {
        return id_ctgp;
    }

    public void setId_ctgp(int id_ctgp) {
        this.id_ctgp = id_ctgp;
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

    
    
    
    
}
