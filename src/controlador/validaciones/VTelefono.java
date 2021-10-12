package controlador.validaciones;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class VTelefono extends KeyAdapter {

    private final JTextField txt;
    private String ingreso;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public VTelefono(JTextField txt) {
        this.txt = txt;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ingreso = txt.getText().trim();
        
        if (ingreso.equals("")) {

            txt.setBorder(new LineBorder(Color.decode("#C33529"), 2));

        } else {
            txt.setBorder(origin_border);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char car = e.getKeyChar();
        if ((car < '0' || car > '9') || txt.getText().length()>=10) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

}
