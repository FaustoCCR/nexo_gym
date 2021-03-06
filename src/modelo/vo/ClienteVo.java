package modelo.vo;

import java.sql.Date;

public class ClienteVo {

    //atributos
    private int id_cliente;
    private int id_persona; // ----> FK
    private int id_membresia; // ----> FK
    private Date f_inicio;
    private Date f_vence;
    private double pago;
    private boolean estado_pago;

    private String nombrecliente;  // ----> variables extra
    private String membresia;
    private String cedulapersona;
    private String estadop;
    private int dias_faltantes;
    private double desc;
    private int n_meses;

    public ClienteVo() {
    }

    public ClienteVo(int id_cliente, int id_persona, int id_membresia, Date f_inicio, Date f_vence, double pago, boolean estado_pago) {
        this.id_cliente = id_cliente;
        this.id_persona = id_persona;
        this.id_membresia = id_membresia;
        this.f_inicio = f_inicio;
        this.f_vence = f_vence;
        this.pago = pago;
        this.estado_pago = estado_pago;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_membresia() {
        return id_membresia;
    }

    public void setId_membresia(int id_membresia) {
        this.id_membresia = id_membresia;
    }

    public Date getF_inicio() {
        return f_inicio;
    }

    public void setF_inicio(Date f_inicio) {
        this.f_inicio = f_inicio;
    }

    public Date getF_vence() {
        return f_vence;
    }

    public void setF_vence(Date f_vence) {
        this.f_vence = f_vence;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public boolean isEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(boolean estado_pago) {
        this.estado_pago = estado_pago;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getMembresia() {
        return membresia;
    }

    public void setMembresia(String membresia) {
        this.membresia = membresia;
    }

    public String getCedulapersona() {
        return cedulapersona;
    }

    public void setCedulapersona(String cedulapersona) {
        this.cedulapersona = cedulapersona;
    }

    public String getEstadop() {
        return estadop;
    }

    public void setEstadop(String estadop) {
        this.estadop = estadop;
    }

    public int getDias_faltantes() {
        return dias_faltantes;
    }

    public void setDias_faltantes(int dias_faltantes) {
        this.dias_faltantes = dias_faltantes;
    }

    public double getDesc() {
        return desc;
    }

    public void setDesc(double desc) {
        this.desc = desc;
    }

    public int getN_meses() {
        return n_meses;
    }

    public void setN_meses(int n_meses) {
        this.n_meses = n_meses;
    }
    
    
    
    

}
