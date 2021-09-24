/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Persona;

/**
 *
 * @author Usuario
 */
public class ControlRegistrar_Persona {

    private VistaRegistrar_Persona vista_persona;
    private PersonaDao modelo_persona;

    public ControlRegistrar_Persona(PersonaDao modelo_persona, VistaRegistrar_Persona vista) {
        this.modelo_persona = modelo_persona;
        this.vista_persona = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Persona - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void funcionalidad() {

        vista_persona.getBtnRegistrar().addActionListener(l -> crearPersona());

    }

    private boolean buscarPersona(String cedula) {

        boolean busqueda;
//        if (!cedula.isEmpty()) {
        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);

        busqueda = modelo_persona.mostrarDatos().stream().anyMatch(cedula_p);

        if (busqueda == true) {
            JOptionPane.showMessageDialog(vista_persona, "Exite un registro con esta cedula", "Mensaje", JOptionPane.ERROR_MESSAGE);

        } else {
            //JOptionPane.showMessageDialog(vista_persona, "Correcto");

        }

        return busqueda;

    }

    private void crearPersona() {
        boolean busqueda = buscarPersona(vista_persona.getTxtDni().getText());
        if (!busqueda) {
            List<PersonaVo> lista = modelo_persona.mostrarDatos();
            
            int id = lista.size();
            String dni = vista_persona.getTxtDni().getText();
            String nom = vista_persona.getTxtNombre().getText();
            String ape = vista_persona.getTxtApellido().getText();
            String corre = vista_persona.getTxtCorreo().getText();
            char gene = vista_persona.getCbGenero().getSelectedItem().toString().charAt(0);
            String direc = vista_persona.getTxtDireccion().getText();
            String telef = vista_persona.getTxtTeléfono().getText();

            Instant instant = vista_persona.getDateFecha().getDate().toInstant();
            ZoneId zid = ZoneId.of("America/Guayaquil");
            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
            Date fecha = Date.valueOf(zdt.toLocalDate());

            PersonaDao perso = new PersonaDao();
            perso.setId_persona(id+1);
            perso.setDni(dni);
            perso.setNombre(nom);
            perso.setApellido(ape);
            perso.setBirthdate(fecha);
            perso.setCorreo(corre);
            perso.setGenero(gene);
            perso.setDireccion(direc);
            perso.setTelefono(telef);

            if (perso.grabar()) {
                JOptionPane.showMessageDialog(vista_persona, "Persona creada");
            } else {
                JOptionPane.showMessageDialog(vista_persona, "error");
            }

        }
    }
    
    // Para despues validar la cedula... y tambien el corre

//    public boolean validadorDeCedula(String cedula) {
//        boolean cedulaCorrecta = false;
//
//        try {
//
//            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
//            {
//                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
//                if (tercerDigito < 6) {
//// Coeficientes de validación cédula
//// El decimo digito se lo considera dígito verificador
//                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
//                    int verificador = Integer.parseInt(cedula.substring(9, 10));
//                    int suma = 0;
//                    int digito = 0;
//                    for (int i = 0; i < (cedula.length() - 1); i++) {
//                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
//                        suma += ((digito % 10) + (digito / 10));
//                    }
//
//                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
//                        cedulaCorrecta = true;
//                    } else if ((10 - (suma % 10)) == verificador) {
//                        cedulaCorrecta = true;
//                    } else {
//                        cedulaCorrecta = false;
//                    }
//                } else {
//                    cedulaCorrecta = false;
//                }
//            } else {
//                cedulaCorrecta = false;
//            }
//        } catch (NumberFormatException nfe) {
//            cedulaCorrecta = false;
//        } catch (Exception err) {
//            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
//            cedulaCorrecta = false;
//        }
//
//        if (!cedulaCorrecta) {
//            System.out.println("La Cédula ingresada es Incorrecta");
//        }
//        return cedulaCorrecta;
//    }
}
