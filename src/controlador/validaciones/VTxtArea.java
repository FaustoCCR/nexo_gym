package controlador.validaciones;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class VTxtArea extends KeyAdapter {

    private final JTextArea txt_area;
    private String ingreso;
    private int lenght;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public VTxtArea(JTextArea txt_area, int lenght) {
        this.txt_area = txt_area;
        this.lenght = lenght;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ingreso = txt_area.getText().trim();

        if (ingreso.equals("")) {

            txt_area.setBorder(new LineBorder(Color.decode("#C33529"), 2));

        } else {
            txt_area.setBorder(origin_border);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (txt_area.getText().length() >= lenght) {
            e.consume();
            Toolkit.getDefaultToolkit().beep(); // ---> alerta con sonido
        }
    }

}
