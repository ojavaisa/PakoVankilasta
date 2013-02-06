package pakovankilasta;

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
        private Pelilauta lauta;
        
        public Kayttoliittyma(Pelilauta lauta) {
            this.lauta = lauta;
        }

        @Override
        public void run() {
            frame = new JFrame("Pako vankilasta");
            frame.setPreferredSize(new Dimension(400, 400));
            
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            luoKomponentit(frame.getContentPane());
            
            frame.pack();
            frame.setVisible(true);
        }
        
        private void luoKomponentit(Container container) {
//            GridLayout layout = new GridLayout(1, 2);
//            container.setLayout(layout);
            Piirtoalusta piirtoalusta = new Piirtoalusta(lauta);
            
            container.add(piirtoalusta);
//            container.add(luoValikko());
        }
        
//        private JPanel luoValikko() {
//            JPanel panel = new JPanel(new GridLayout(2, 1));
//            panel.add(new JButton("Nappi 1"));
//            panel.add(new JButton("Nappi 2"));
//            return panel;
//        }
        
        public JFrame getFrame() {
            return frame;
        }
}
