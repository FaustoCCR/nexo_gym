package modelo.vo;

import java.sql.Date;

public class ProgramaClienteVo {

    //atributos
    private int id_pgcliente;
    private int id_cliente; // ----> FK
    private int id_rutina; // ----> FK
    private int id_empleado; // ----> FK
    private Date fecha;

    /*varibles extras*/
    private String nombreCliente;
    private String nombreRutina;
    private String descripcion_rutina;
    private String nombreInstructor;

    public ProgramaClienteVo() {
    }

    public ProgramaClienteVo(int id_pgcliente, int id_cliente, int id_rutina, int id_empleado, Date fecha) {
        this.id_pgcliente = id_pgcliente;
        this.id_cliente = id_cliente;
        this.id_rutina = id_rutina;
        this.id_empleado = id_empleado;
        this.fecha = fecha;
    }

    public int getId_pgcliente() {
        return id_pgcliente;
    }

    public void setId_pgcliente(int id_pgcliente) {
        this.id_pgcliente = id_pgcliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(int id_rutina) {
        this.id_rutina = id_rutina;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getDescripcion_rutina() {
        return descripcion_rutina;
    }

    public void setDescripcion_rutina(String descripcion_rutina) {
        this.descripcion_rutina = descripcion_rutina;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }
    
    

}
