package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
//import java.awt.GridLayout;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.JPanel;

/**
 * Kayttoliittyma-luokka on pääohjelmaluokan kutsuma luokka, joka luo graafisen käyttöliitymän
 * komponentit.
 *
 * @author $Olli Väisänen
 */
public class Kayttoliittyma implements Runnable {

        private JFrame frame;
        private Peli peli;
        
        public Kayttoliittyma() {
            this.peli = new Peli();
        }

        @Override
        public void run() {
            frame = new JFrame("Pako vankilasta");
            frame.setPreferredSize(new Dimension(400, 500));
            
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            luoKomponentit(frame.getContentPane());
            
            frame.pack();
            frame.setVisible(true);
        }
        
        private void luoKomponentit(Container container) {
//            GridLayout layout = new GridLayout(1, 2);
//            container.setLayout(layout);
            Piirtoalusta piirtoalusta = new Piirtoalusta(this.peli);
            
            container.add(piirtoalusta);
            frame.addMouseListener(new HiirenKuuntelija(this.peli, piirtoalusta));

        }
        
        public JFrame getFrame() {
            return frame;
        }
}
