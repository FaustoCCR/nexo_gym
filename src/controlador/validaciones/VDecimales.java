
package controlador.validaciones;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class VDecimales extends KeyAdapter{
    
    private final JTextField txt;
    private String ingreso;
    private Border origin_border = new LineBorder(Color.gray, 1);

    public VDecimales(JTextField txt) {
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
        if ((!Validar.esNumerosDecimales(car + "") && car != e.VK_ENTER && car != e.VK_BACK_SPACE) || txt.getText().length() >= 7) {
            e.consume();
            Toolkit.getDefaultToolkit().beep(); // ---> alerta con sonido
        }
    }

    
}
