
package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import modelo.dao.CargoDao;
import modelo.dao.EmpleadoDao;
import modelo.dao.PersonaDao;
import modelo.vo.EmpleadoVo;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Empleado;

/**
 *
 * @author Alex
 */

public class ControlRegistrar_Empleado {
    private EmpleadoDao modelo;
    private VistaRegistrar_Empleado vista;
    private PersonaDao modelo_persona = new PersonaDao();
    private CargoDao modelo_cargo = new CargoDao();
    public ControlRegistrar_Empleado(EmpleadoDao modelo, VistaRegistrar_Empleado vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
        vista.setTitle("Registro de Empleados - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //buscarPersona(vista.getTxtCedula().getText());
        //verificarCargo();
        cargarCargo();
        
    }
    
    public void funcionalidad(){
     
        vista.getBtnRegistrarE().addActionListener(l->crearEmpleado());
        
    }
   
    private boolean verificarPersona(String cedula) {
        boolean busqueda;
        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);
        busqueda = modelo_persona.mostrarDatos().stream().anyMatch(cedula_p);
        if (busqueda == true) {
            System.out.println("Cedula Correcta");
            vista.getTxtCedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            modelo_persona.mostrarDatos().stream().filter(cedula_p).forEach((t) -> {
                vista.getTxtPersona().setText(t.getNombre() + " " + t.getApellido());
               
            });
        } else {
            System.out.println("Cedula Incorrecta");
            vista.getTxtCedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxtCedula().setText("");
        }
        return busqueda;
    }
    
    
    private void cargarCargo() {
        modelo_cargo.mostrarDatos().stream().forEach((m) -> {
        vista.getCb_cargo().addItem(m.getNombre());
        //vista.getCb_cargo().addItem(m.getId_cargo()+"");
        });
        
    }
    
    private int VerificarCedula( String cedula){
        int id_persona=modelo_persona.mostrarDatos().stream().filter(persona ->persona.getDni().equals(cedula)).findAny().get().getId_persona();
        return id_persona;
    }
    
    private void crearEmpleado() {
        
        boolean busqueda = verificarPersona(vista.getTxtCedula().getText());
        if (busqueda) {
           
            List<EmpleadoVo> lista = modelo.mostrarDatos();
            
            int idpersona=VerificarCedula(vista.getTxtCedula().getText());
            int id_cargo=modelo_cargo.mostrarDatos().get(vista.getCb_cargo().getSelectedIndex()).getId_cargo();
            Instant instant= vista.getFechaContrato().getDate().toInstant();
            ZoneId zid= ZoneId.of("America/Guayaquil");
            ZonedDateTime zdt=ZonedDateTime.ofInstant(instant, zid);  
            Date fecha = Date.valueOf(zdt.toLocalDate());
            System.out.println("fecha"+ fecha);
            Double sueldo = Double.valueOf(vista.getTxtSueldo().getText());
            System.out.println("Sueldo"+ sueldo);
            
           
           
            try {
                EmpleadoDao emp = new EmpleadoDao();
                emp.setId_persona(idpersona);
                emp.setId_cargo(id_cargo);
                emp.setFecha_contrato(fecha);
                emp.setSueldo(sueldo);

                if (emp.grabar()) {
                    System.out.println("graba");
                    JOptionPane.showMessageDialog(vista, "Empleado creado");
                } else {
                    JOptionPane.showMessageDialog(vista, "error");
                }
            } catch ( NumberFormatException ex ) {
                System.out.println("Datos Vacios "+ ex);
            
           }
        }else{
            System.out.println("Persona no esta registrada");
        }
    }
      
}
