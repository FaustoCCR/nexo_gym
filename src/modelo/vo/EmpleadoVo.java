package modelo.vo;

import java.sql.Date;

public class EmpleadoVo {

    //atributos
    private int id_empleado;
    private int id_persona; // ----> FK
    private int id_cargo; // ----> FK
    private Date fecha_contrato;
    private double sueldo;

    //----> variables Adicionales
    private String dni_persona;
    private String nombrecliente;
    private String ncargo;

    public EmpleadoVo() {
    }

    public EmpleadoVo(int id_empleado, int id_persona, int id_cargo, Date fecha_contrato, double sueldo) {
        this.id_empleado = id_empleado;
        this.id_persona = id_persona;
        this.id_cargo = id_cargo;
        this.fecha_contrato = fecha_contrato;
        this.sueldo = sueldo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public Date getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(Date fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getNombreCargo() {
        return ncargo;
    }

    public void setNombreCargo(String ncargo) {
        this.ncargo = ncargo;
    }

    public String getDni_persona() {
        return dni_persona;
    }

    public void setDni_persona(String dni_persona) {
        this.dni_persona = dni_persona;
    }

}
