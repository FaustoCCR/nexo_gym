package controlador;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.dao.ProgramaClienteDao;
import vista.VistaAdministrador;
import vista.VistaPlantilla_Instructor;

public class ControlPlantilla_Instructor {

    private ProgramaClienteDao modelo_pc;
    private VistaPlantilla_Instructor vista;
    private DefaultTableModel tb_model;
    private Object[] columnas = {"Nro", "Cliente", "Rutina", "Fecha"};
    private int id_user;

    public ControlPlantilla_Instructor(ProgramaClienteDao modelo_pc, VistaPlantilla_Instructor vista) {
        this.modelo_pc = modelo_pc;
        this.vista = vista;

        id_user = ControlLogin.id_user;
        vista.setVisible(true);
        vista.setTitle("Planes de Entrenamiento - Nexo Gym");
        vista.setResizable(false);
        vista.setLocation((int) (1920 - vista.getWidth()) / 2, (int) (878 - vista.getHeight()) / 2);
        vista.setClosable(true);
        vista.setIconifiable(true);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        disenioTabla();

        mostrarPlanesClientes("");

    }

    public void funcionalidad() {

        vista.getTxt_buscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mostrarPlanesClientes(vista.getTxt_buscar().getText());

            }

        });

    }

    private void disenioTabla() {
        tb_model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        vista.getJtable_planentrenamiento().setModel(tb_model);
        vista.getJtable_planentrenamiento().getTableHeader().setFont(new Font("Trebuchet MS", 1, 15));//fuente que lleve la cabecera
        vista.getJtable_planentrenamiento().setShowHorizontalLines(true);//colocar lineas horizontales
        vista.getJtable_planentrenamiento().getColumnModel().getColumn(0).setPreferredWidth(10);
        vista.getJtable_planentrenamiento().getColumnModel().getColumn(1).setPreferredWidth(40);
        vista.getJtable_planentrenamiento().getColumnModel().getColumn(2).setPreferredWidth(40);

    }

    private void mostrarPlanesClientes(String aguja) {

        tb_model.setRowCount(0);

        Holder<Integer> i = new Holder<>(1);//contador Holder
        modelo_pc.mostrarDatosJoinPlantilla(id_user, aguja).forEach((pc) -> {

            Object[] contenido = {i.value, pc.getNombreCliente(), pc.getNombreRutina(), formatoFecha(pc.getFecha())};

            tb_model.addRow(contenido);

            i.value++;

        });

    }

    private String formatoFecha(Date fecha) {

        SimpleDateFormat formateador = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        String nuevafecha = formateador.format(fecha);
        return nuevafecha;

    }

}
