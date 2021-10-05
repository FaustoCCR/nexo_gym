/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Enumeration;
import java.util.function.Predicate;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaActualizar_Persona;

/**
 *
 * @author Usuario
 */
public class ControlActualizar_Persona {
    
    private PersonaDao modelo_persona;
    private VistaActualizar_Persona vista_persona;
    private int id_persona;
    private Border origin_border = new LineBorder(Color.gray, 1);
    
    public ControlActualizar_Persona(PersonaDao modelo_persona, VistaActualizar_Persona vista_persona) {
        this.modelo_persona = modelo_persona;
        this.vista_persona = vista_persona;
        
        id_persona = ControlGestion_Persona.id_persona;
        vista_persona.setVisible(true);
        vista_persona.setTitle("Actualizar Persona - Nexo Gym");
        vista_persona.setResizable(false);
        vista_persona.setLocationRelativeTo(null);
        vista_persona.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatosPersona();
    }
    
    public void funcionalidad() {

        vista_persona.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                verificarCedulaIngresada(vista_persona.getTxt_cedula().getText());
            }

        });

        vista_persona.getBt_actualizar().addActionListener(l -> actualizarPersona());

    }
    
    private void cargarDatosPersona() {

        modelo_persona.mostrarDatosJoin(id_persona).stream().forEach((pe) -> {

            vista_persona.getTxt_cedula().setText(pe.getDni());
            vista_persona.getTxt_nombre().setText(pe.getNombre());
            vista_persona.getTxt_apellido().setText(pe.getApellido());
            vista_persona.getTxt_correo().setText(pe.getCorreo());
            vista_persona.getTxt_direccion().setText(pe.getDireccion());
            vista_persona.getTxt_telefono().setText(pe.getTelefono());
            vista_persona.getJdnacimiento().setDate(pe.getBirthdate());
            vista_persona.getRbt_masculino().setSelected(seleccionarRadioM(pe.getGenero()));
            vista_persona.getRbt_femenino().setSelected(seleccionarRadioF(pe.getGenero()));
        });
    }
    
     private boolean seleccionarRadioM(char ge){
        if (ge=='M') {
            return true;
        }else{return false;}
    }
     private boolean seleccionarRadioF(char ge){
        if (ge=='F') {
             return true;
         } else{return false;}
        
    }
     
        //--------- metodos para modificar ---------- 
    
    private boolean validarRegistro() {

        String cedula = vista_persona.getTxt_cedula().getText();
        String nombre = vista_persona.getTxt_nombre().getText();
        String apellido = vista_persona.getTxt_apellido().getText();
        String correo = vista_persona.getTxt_correo().getText();
        String direccion = vista_persona.getTxt_direccion().getText();
        String telefono = vista_persona.getTxt_telefono().getText();

        boolean validacion = true;
        if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || vista_persona.getJdnacimiento().getDate()==null
                || correo.isEmpty() || (!vista_persona.getRbt_masculino().isSelected() && !vista_persona.getRbt_femenino().isSelected())
                || direccion.isEmpty() || telefono.isEmpty()) {

            validacion = false;

        }

        return validacion;

    }
     
    private String getSelectedbtgenero(ButtonGroup bt_genero) {
        for (Enumeration<AbstractButton> buttons = bt_genero.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    } 
    
    private void sentenciaUpdate() {

        String cedula = vista_persona.getTxt_cedula().getText();
        String nombre = vista_persona.getTxt_nombre().getText();
        String apellido = vista_persona.getTxt_apellido().getText();

        //------------------------- FECHA -----------------------------//
        Instant instant = vista_persona.getJdnacimiento().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());
        //-------------------------      -----------------------------//
        String correo = vista_persona.getTxt_correo().getText();
        char genero = getSelectedbtgenero(vista_persona.getBt_genero()).charAt(0);
        String direccion = vista_persona.getTxt_direccion().getText();
        String telefono = vista_persona.getTxt_telefono().getText();

        modelo_persona.setDni(cedula);
        modelo_persona.setNombre(nombre);
        modelo_persona.setApellido(apellido);
        modelo_persona.setBirthdate(fecha);
        modelo_persona.setCorreo(correo);
        modelo_persona.setGenero(genero);
        modelo_persona.setDireccion(direccion);
        modelo_persona.setTelefono(telefono);

        if (modelo_persona.modificar1(id_persona)) {

            JOptionPane.showMessageDialog(vista_persona, "Usuario Actualizado");

        } else {
            JOptionPane.showMessageDialog(vista_persona, "Error al Actualizar");
        }

    }
    
     
    
    private void restaurarBordes() {

        vista_persona.getTxt_cedula().setBorder(origin_border);
    }
    
    
    
    
    
    private void actualizarPersona() {

        if (validarRegistro()) {
            if (verificarCedulaIngresada(vista_persona.getTxt_cedula().getText())) {
                if (saber_NoHayOtraCedula()) {
                    sentenciaUpdate();
                    restaurarBordes();
                }else{
                   JOptionPane.showMessageDialog(vista_persona, "La cedula ya existe en otro registro", "Mensaje", JOptionPane.ERROR_MESSAGE); 
                }
                
            }else{
                JOptionPane.showMessageDialog(vista_persona, "Cedúla Incorrecta", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
         
        } else {

            JOptionPane.showMessageDialog(vista_persona, "Rellenar todos los campos", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //---------- Referente a la cedula -----------------
    
    
        private boolean verificarCedulaIngresada(String cedula) {

        /*Si la cedula tiene 10 caracteres
            y verificar que sea correcta la cedula*/
        boolean respuesta=longitudCedula(cedula);
        if (respuesta) {

            vista_persona.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
        } else {
            vista_persona.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));

        }
        return respuesta;

    }
        
    
    private int campoId(String cedula) {
        int id_persona;
        /*Retora el id de la persona de acuerdo a su número de cédula*/
        id_persona = modelo_persona.mostrarDatos().stream().filter(p -> p.getDni().equals(cedula)).findAny().get().getId_persona();      
        return id_persona;

    }
    
    private int verificarCedula2(String cedula) {
        int cont=modelo_persona.contadorPer(cedula);
        System.out.println(cont);
        return cont;

    }
    
    private boolean saber_NoHayOtraCedula(){
        String cedula1=vista_persona.getTxt_cedula().getText();
        
        if (verificarCedula2(cedula1)>=1) {
            int id1=campoId(cedula1);
            if (id1==id_persona) {
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    
    
    }
    
    private boolean longitudCedula(String cedula){
        int log = cedula.length();
            if (log>=10) {
                vista_persona.getTxt_cedula().setText(cedula.substring(0, 10));
                cedula=vista_persona.getTxt_cedula().getText();
                return validadorCedulaEcua(cedula);
            }
        
    return false;
    
    }
    
    private boolean validadorCedulaEcua(String document) {
        byte sum = 0;
        document=document.substring(0,10);
        try {
            if (document.trim().length() != 10) {
                return false;
            }
            String[] data = document.split("");
            byte verifier = Byte.parseByte(data[0] + data[1]);
            if (verifier < 1 || verifier > 24) {
                return false;
            }
            byte[] digits = new byte[data.length];
            for (byte i = 0; i < digits.length; i++) {
                digits[i] = Byte.parseByte(data[i]);
            }
            if (digits[2] > 6) {
                return false;
            }
            for (byte i = 0; i < digits.length - 1; i++) {
                if (i % 2 == 0) {
                    verifier = (byte) (digits[i] * 2);
                    if (verifier > 9) {
                        verifier = (byte) (verifier - 9);
                    }
                } else {
                    verifier = (byte) (digits[i] * 1);
                }
                sum = (byte) (sum + verifier);
            }
            if ((sum - (sum % 10) + 10 - sum) == digits[9]) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    //    private boolean verificarCedula(String cedula) {
//
//        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);
//
//        return modelo_persona.mostrarDatos().stream().noneMatch(cedula_p);
//
//    }
}
  

  