
package modelo.vo;


public class RutinaVo {
    
    //atributos
    private int id_rutina;
    private String nombre;
    private String descripcion;

    public RutinaVo(int id_rutina, String nombre, String descripcion) {
        this.id_rutina = id_rutina;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(int id_rutina) {
        this.id_rutina = id_rutina;
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
