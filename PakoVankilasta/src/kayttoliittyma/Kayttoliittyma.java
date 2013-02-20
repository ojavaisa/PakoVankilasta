package kayttoliittyma;

/**
 *
 * @author $Olli Väisänen
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
