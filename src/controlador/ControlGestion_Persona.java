/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.dao.PersonaDao;
import vista.VistaGestion_Persona;

/**
 *
 * @author Usuario
 */
public class ControlGestion_Persona {

    private PersonaDao modelo_persona;
    private VistaGestion_Persona vista_persona;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "DNI", "Nombre", "Edad", "Teléfono", "Dirección", "Correo"};

    public ControlGestion_Persona(PersonaDao modelo_persona, VistaGestion_Persona vista_persona) {
        this.modelo_persona = modelo_persona;
        this.vista_persona = vista_persona;

        vista_persona.setVisible(true);
        vista_persona.setTitle("Personas Registradas - Nexo Gym");
        vista_persona.setResizable(false);
        vista_persona.setLocationRelativeTo(null);
        vista_persona.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disenioTabla();

        mostrarDatosTabla();
    }

    public void funcionalidad() {

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return true;

            }

        };

        vista_persona.getJtable_persona().setModel(tb_model);
        vista_persona.getJtable_persona().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista_persona.getJtable_persona().setShowHorizontalLines(true);//colocar lineas horizontales
        //----- Tamaños de las celdas --------
        vista_persona.getJtable_persona().getColumnModel().getColumn(0).setPreferredWidth(15);
        vista_persona.getJtable_persona().getColumnModel().getColumn(1).setPreferredWidth(60);
        vista_persona.getJtable_persona().getColumnModel().getColumn(2).setPreferredWidth(90);
        vista_persona.getJtable_persona().getColumnModel().getColumn(3).setPreferredWidth(30);
        vista_persona.getJtable_persona().getColumnModel().getColumn(4).setPreferredWidth(50);

    }

    private void mostrarDatosTabla() {

        tb_model.setRowCount(0);
        modelo_persona.mostrarDatosJoin().stream().forEach((p) -> {

            Object[] contenido
                    = {p.getId_persona(), p.getDni(), p.getNombre() + " " + p.getApellido(), Calcular_Edad(p.getBirthdate()), p.getTelefono(), p.getDireccion(), p.getCorreo()};

            tb_model.addRow(contenido);

        });
    }

    public String Calcular_Edad(Date fecha) {
        LocalDate fechaactual = LocalDate.now();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_na = formato.format(fecha);
        String fec[] = fecha_na.split("/");
        LocalDate fechanace = LocalDate.of(Integer.parseInt(fec[2]), Integer.parseInt(fec[1]), Integer.parseInt(fec[0]));
        Period periodo = Period.between(fechanace, fechaactual);
        String edad = (" " + periodo.getYears() + " Años");
        return edad;
    }

}
