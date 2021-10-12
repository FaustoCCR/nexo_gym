package controlador.validaciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class VCedula extends KeyAdapter {

    private final JTextField txt;

    public VCedula(JTextField txt) {
        this.txt = txt;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char car = e.getKeyChar();
        if ((!Validar.esNumeros(car + "") && car != e.VK_ENTER && car != e.VK_BACK_SPACE) || txt.getText().length() >= 10) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

}
