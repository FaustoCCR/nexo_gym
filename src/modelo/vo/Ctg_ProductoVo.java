
package modelo.vo;


public class Ctg_ProductoVo {
    
    //atributos
    private int id_ctgp;
    private String nombre;

    public Ctg_ProductoVo(int id_ctgp, String nombre) {
        this.id_ctgp = id_ctgp;
        this.nombre = nombre;
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
    
    
    
}
