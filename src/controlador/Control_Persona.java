/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaPersona;

/**
 *
 * @author Usuario
 */
public class Control_Persona {
    private VistaPersona vista;
    private PersonaDao modelo;
    private int seleccion=-1;

    public Control_Persona(VistaPersona vista, PersonaDao modelo) {
        this.vista = vista;
        this.modelo = modelo;
        // mostrar al inicio
        vista.setTitle("CRUD PERSONAS");
        vista.getLbMensajes().setText("Bienvienidos Sistema 1.0");
        vista.setVisible(true);
        cargaLista();
    }
    
    public void iniciaControlPersona() {
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                cargaLista(vista.getTxtBusqueda().getText());
            }
        };
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                datosTabla();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        vista.getBtnActualizar().addActionListener(l -> cargaLista());
        vista.getBtnNuevo().addActionListener(l -> cargarDialogo(1));
        vista.getBtnEditar().addActionListener(l -> cargarDialogo(2));
        vista.getTxtBusqueda().addKeyListener(kl);
        vista.getTablaPersona().addMouseListener(ml);
        vista.getBtnEliminar().addActionListener(al -> eliminar());

    }

    private void cargarDialogo(int origen) {
        vista.getDlgPersona().setSize(600, 400);
        vista.getDlgPersona().setLocationRelativeTo(vista);
        if (origen == 1) {
            vista.getDlgPersona().setTitle("Crear Persona");
            limpiar();
            vista.getBtnAceptar().addActionListener(al -> crearPersona());

        } else {
            vista.getDlgPersona().setTitle("Editar Persona");
            vista.getBtnAceptar().addActionListener(ml -> modificar());
        }
        vista.getDlgPersona().setVisible(true);

    }

    private void cargaLista() {
        //Carga datos a la vista.
        DefaultTableModel tablaMd;
        tablaMd = (DefaultTableModel) vista.getTablaPersona().getModel();
        tablaMd.setNumRows(0);
        List<PersonaVo> lista = modelo.mostrarDatos();
        lista.stream().forEach(per -> {
            String[] fila = {per.getId_persona() + "", per.getDni(), per.getNombre(), per.getApellido(), per.getBirthdate() + "",
                per.getCorreo(), per.getGenero() + "", per.getDireccion(), per.getTelefono()};
            tablaMd.addRow(fila);
        });

    }

    private void cargaLista(String aguja) {
        //Carga datos a la vista.
        if (aguja.equals("")) {
            cargaLista();
        } else {
            DefaultTableModel tablaMd;
            tablaMd = (DefaultTableModel) vista.getTablaPersona().getModel();
            tablaMd.setNumRows(0);
            List<PersonaVo> lista = modelo.listarPersonas(aguja);
            lista.stream().forEach(per -> {
                String[] fila = {per.getId_persona() + "", per.getDni(), per.getNombre(), per.getApellido(), per.getBirthdate() + "",
                    per.getCorreo(), per.getGenero() + "", per.getDireccion(), per.getTelefono()};
                tablaMd.addRow(fila);
            });
        }

    }

    private void crearPersona() {
        int id = Integer.parseInt(vista.getTxtId().getText());
        String dni = vista.getTxtDNI().getText();
        String nom = vista.getTxtNombre().getText();
        String ape = vista.getTxtApellido().getText();
        String corre = vista.getTxtCorreo().getText();
        char gene = vista.getCbGenero().getSelectedItem().toString().charAt(0);
        String direc = vista.getTxtDireccion().getText();
        String telef = vista.getTxtTelefono().getText();

        Instant instant = vista.getDtFecha().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());

        PersonaDao perso = new PersonaDao();
        perso.setId_persona(id);
        perso.setDni(dni);
        perso.setNombre(nom);
        perso.setApellido(ape);
        perso.setBirthdate(fecha);
        perso.setCorreo(corre);
        perso.setGenero(gene);
        perso.setDireccion(direc);
        perso.setTelefono(telef);

        if (perso.grabar()) {
            JOptionPane.showMessageDialog(vista, "Persona creada");
            vista.getDlgPersona().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(vista, "error");
        }

    }

    private void eliminar() {
        String ident = vista.getTxtId().getText();
        if (vista.getTxtId().getText() != " ") {
            int confirmareliminar = JOptionPane.showConfirmDialog(null, "SEGURO DESEAS ELIMINAR "
                    + ident, "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (confirmareliminar == 0) {
                if (modelo.eliminar(ident)) {
                    JOptionPane.showMessageDialog(null, "eliminado");

                } else {
                    JOptionPane.showMessageDialog(null, "error no eliminado");
                }
            }
        } else {
            System.out.println("No ha selecionado un dato");

        }

    }

    private void modificar() {
        int idper = Integer.parseInt(vista.getTxtId().getText());
        int id = Integer.parseInt(vista.getTxtId().getText());
        String dni = vista.getTxtDNI().getText();
        String nom = vista.getTxtNombre().getText();
        String ape = vista.getTxtApellido().getText();
        String corre = vista.getTxtCorreo().getText();
        char gene = vista.getCbGenero().getSelectedItem().toString().charAt(0);
        String direc = vista.getTxtDireccion().getText();
        String telef = vista.getTxtTelefono().getText();

        Instant instant = vista.getDtFecha().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha = Date.valueOf(zdt.toLocalDate());

        PersonaDao perso = new PersonaDao();
        perso.setNombre(nom);
        perso.setApellido(ape);
        perso.setBirthdate(fecha);
        perso.setCorreo(corre);
        perso.setGenero(gene);
        perso.setDireccion(direc);
        perso.setTelefono(telef);

        if (perso.modificar(id + "")) {
            JOptionPane.showMessageDialog(null, "Modificado");
            vista.getDlgPersona().setVisible(false);

        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar");
        }
    }

    private void datosTabla() {
        List<PersonaVo> ulista = modelo.mostrarDatos();
        seleccion = vista.getTablaPersona().getSelectedRow();
        if (seleccion != -1) {
            PersonaVo p = new PersonaVo();
            vista.getTxtDNI().setText(ulista.get(seleccion).getDni());
            vista.getTxtId().setText(ulista.get(seleccion).getId_persona() + "");
            vista.getTxtNombre().setText(ulista.get(seleccion).getNombre());
            vista.getTxtApellido().setText(ulista.get(seleccion).getApellido());
            vista.getTxtTelefono().setText(ulista.get(seleccion).getTelefono());
            vista.getTxtDireccion().setText(ulista.get(seleccion).getDireccion());
            vista.getTxtCorreo().setText(ulista.get(seleccion).getCorreo());
            vista.getCbGenero().setName(ulista.get(seleccion).getGenero() + "");
            vista.getDtFecha().setDate(ulista.get(seleccion).getBirthdate());
            //vista.getDtFecha().setDate(modelo.fech(ulista.get(seleccion).getIdPersona()));
        } else {
            vista.getTxtId().setText(" ");
        }

    }

    private void limpiar() {
        vista.getTxtApellido().setText("");
        vista.getTxtId().setText("");
        vista.getTxtCorreo().setText("");
        vista.getTxtDNI().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtDireccion().setText("");
        // vista.getDtFecha().setDateFormatString("12/09/1999");
        vista.getDlgPersona().setVisible(false);

    }
}
