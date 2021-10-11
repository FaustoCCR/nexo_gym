package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.conexion.PGConexion;
import modelo.dao.PersonaDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.VistaActualizar_Persona;
import vista.VistaAdministrador;
import vista.VistaGestion_Persona;

public class ControlGestion_Persona {

    private PersonaDao modelo_persona;
    private VistaGestion_Persona vista_persona;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"ID", "DNI", "Nombre", "Edad", "Teléfono", "Dirección", "Correo"};

    public static int id_persona;

    public ControlGestion_Persona(PersonaDao modelo_persona, VistaGestion_Persona vista_persona) {
        this.modelo_persona = modelo_persona;
        this.vista_persona = vista_persona;

        vista_persona.setVisible(true);
        vista_persona.setTitle("Personas Registradas - Nexo Gym");
        vista_persona.setResizable(false);
        vista_persona.setLocation((int) (VistaAdministrador.getjDesktopPanePrincipal().getWidth() - vista_persona.getWidth()) / 2,
                (int) (VistaAdministrador.getjDesktopPanePrincipal().getHeight() - vista_persona.getHeight()) / 2);
        vista_persona.setClosable(true);
        vista_persona.setIconifiable(true);
        vista_persona.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        identificarRol();
        disenioTabla();

        mostrarDatosTabla("");
    }

    public void funcionalidad() {
        vista_persona.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                mostrarDatosTabla(vista_persona.getTxt_buscar().getText());

            }

        });

        vista_persona.getBt_verificar().addActionListener(l -> ventanaActualizar());
        vista_persona.getBt_eliminar().addActionListener(l -> sentenciaDelete());
        vista_persona.getBt_imprimir().addActionListener(l->imprimirReporte());

    }

    private void identificarRol() {

        switch (ControlLogin.permiso) {

            case 3:

                vista_persona.getBt_verificar().setVisible(false);
                vista_persona.getBt_eliminar().setVisible(false);

                break;

        }
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

    private void mostrarDatosTabla(String aguja) {

        tb_model.setRowCount(0);
        modelo_persona.mostrarDatosJoin(aguja).stream().forEach((p) -> {

            Object[] contenido
                    = {p.getId_persona(), p.getDni(), p.getNombre(), Calcular_Edad(p.getBirthdate()), p.getTelefono(), p.getDireccion(), p.getCorreo()};

            tb_model.addRow(contenido);

        });
    }

    private void ventanaActualizar() {

        int fila = vista_persona.getJtable_persona().getSelectedRow();
        final int columna = 0;

        if (fila != -1) {

            id_persona = (int) vista_persona.getJtable_persona().getValueAt(fila, columna);
//            System.out.println("funciona");
            vista_persona.dispose();
            VistaActualizar_Persona vista = new VistaActualizar_Persona();
            VistaAdministrador.getjDesktopPanePrincipal().add(vista);
            ControlActualizar_Persona control = new ControlActualizar_Persona(modelo_persona, vista);
            control.funcionalidad();

        } else {
            JOptionPane.showMessageDialog(vista_persona, "Seleccione el registro a verificar");
        }

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

    private void sentenciaDelete() {

        int fila = vista_persona.getJtable_persona().getSelectedRow();
        final int columna = 0;
        if (fila != -1) {

            int id_persona = (int) vista_persona.getJtable_persona().getValueAt(fila, columna);
            String nombre = modelo_persona.mostrarDatos().stream().filter(u -> u.getId_persona() == id_persona).findAny().get().getNombre();

            int resp = JOptionPane.showConfirmDialog(vista_persona, "Seguro desea eliminar al usuario : " + nombre, "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {

                modelo_persona.eliminar(id_persona);
                JOptionPane.showMessageDialog(vista_persona, "Registro Eliminado");
                mostrarDatosTabla("");

            }

        } else {
            JOptionPane.showMessageDialog(vista_persona, "Seleccione el registro a eliminar");
        }
    }

    private void imprimirReporte() {

        PGConexion con = new PGConexion();

        try {

            /*Creacion de un mapa
            asigna un dato a los diferentes 
            parámetros*/
            Map<String, Object> parametros = new HashMap<>();
            String aguja = vista_persona.getTxt_buscar().getText().trim();

//            put( ?,  ?)---> el nombre del parametro(tipo de dato), la variable que recibe 
            parametros.put("paguja", "%" + aguja + "%");
            parametros.put("pdetalle", subitituloReport(aguja));
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/vista/reportes/ReportePersonas.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con.getCon());
            // (jr,null,con.getCon()) null --> cuando no enviamos un parametro
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControlGestion_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String subitituloReport(String filtro) {

        if (filtro.isEmpty()) {

            return "Búsqueda general";

        } else {
            return "Párametro de búsqueda : " + filtro;

        }

    }

}
