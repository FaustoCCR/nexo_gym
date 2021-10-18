
package controlador.validaciones;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class VCampoParticular extends KeyAdapter{
    
    
    private final JTextField txt;
    private String ingreso;
    private int lenght;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public VCampoParticular(JTextField txt, int lenght) {
        this.txt = txt;
        this.lenght = lenght;
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
        if (txt.getText().length() >= lenght) {
            e.consume();
            Toolkit.getDefaultToolkit().beep(); // ---> alerta con sonido
        }
    }
    
}
