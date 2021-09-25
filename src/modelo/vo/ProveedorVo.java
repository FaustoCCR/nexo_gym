
package modelo.vo;

public class ProveedorVo {
    
    //atributos
    private String id_proveedor;
    private String nombre;
    private String contacto;
    
    public ProveedorVo(){
        
    }

    public ProveedorVo(String id_proveedor, String nombre, String contacto) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    
    
}
