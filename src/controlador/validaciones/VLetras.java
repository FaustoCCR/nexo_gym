package controlador.validaciones;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class VLetras extends KeyAdapter {

    private final JTextField txt;

    public VLetras(JTextField txt) {
        this.txt = txt;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char car = e.getKeyChar();
        if ((!Validar.esLetras(car + "") && car != e.VK_ENTER && car != e.VK_BACK_SPACE) || txt.getText().length() >=17) {
            e.consume();
            Toolkit.getDefaultToolkit().beep(); // ---> alerta con sonido
        }
    }

}
