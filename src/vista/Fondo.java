
package vista;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class Fondo implements Border{
    
    
    private BufferedImage image;

    public Fondo() {
        
        try {
            URL imagenPath = new URL(getClass().getResource("/vista/img/fondo.png").toString());
            try {
                image = ImageIO.read(imagenPath);
            } catch (IOException ex) {
                Logger.getLogger(Fondo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Fondo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(image,(x +(width -image.getWidth())/2), (y +(height - image.getHeight())/2),null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
    
}
