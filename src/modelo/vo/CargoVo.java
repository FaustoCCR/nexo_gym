
package modelo.vo;


public class CargoVo {
    
    //atributos
    private int id_cargo;
    private String nombre;
    private String descripcion;

    public CargoVo() {
    }

    public CargoVo(int id_cargo, String nombre, String descripcion) {
        this.id_cargo = id_cargo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
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
