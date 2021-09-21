package controlador;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import modelo.dao.ClienteDao;
import modelo.dao.MembresiaDao;
import modelo.dao.PersonaDao;
import modelo.vo.PersonaVo;
import vista.VistaRegistrar_Cliente;

public class ControlRegistrar_Cliente {

    private ClienteDao modelo;
    private VistaRegistrar_Cliente vista;
    private PersonaDao modelo_persona = new PersonaDao();
    private MembresiaDao modelo_membresia = new MembresiaDao();

    public ControlRegistrar_Cliente(ClienteDao modelo, VistaRegistrar_Cliente vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setVisible(true);
        vista.setTitle("Registro de Clientes - Nexo Gym");
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarMembresias();

    }

    public void funcionalidad() {

        vista.getTxt_cedula().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscarPersona(vista.getTxt_cedula().getText());
            }

        });

    }

    private boolean buscarPersona(String cedula) {

        boolean busqueda;

        Predicate<PersonaVo> cedula_p = p -> p.getDni().equals(cedula);

        busqueda = modelo_persona.mostrarDatos().stream().anyMatch(cedula_p);

        if (busqueda == true) {
            System.out.println("Cedula Correcta");
            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#6CC01B"), 2));
            modelo_persona.mostrarDatos().stream().filter(cedula_p).forEach((t) -> {
                vista.getTxt_persona().setText(t.getNombre() + " " + t.getApellido());

            });
        } else {
            System.out.println("Cedula Incorrecta");
            vista.getTxt_cedula().setBorder(new LineBorder(Color.decode("#C33529"), 2));
            vista.getTxt_persona().setText("");

        }
        return busqueda;

    }

    private void cargarMembresias() {

        modelo_membresia.mostrarDatos().stream().forEach((m) -> {

            vista.getCb_membresia().addItem(m.getNombre());

        });

    }

}
