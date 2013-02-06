package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {
    
    private Pelilauta lauta;

    public Piirtoalusta(Pelilauta lauta) {
        super.setBackground(Color.WHITE);
        this.lauta = lauta;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        lauta.piirra(g);
    }
}
