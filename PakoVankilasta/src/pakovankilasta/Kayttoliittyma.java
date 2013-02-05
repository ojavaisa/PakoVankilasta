package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {

        private JFrame frame;
        
        public Kayttoliittyma() {
        }

        @Override
        public void run() {
            frame = new JFrame("Pako vankilasta");
            frame.setPreferredSize(new Dimension(200, 100));
            
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            luoKomponentit(frame.getContentPane());
            
            frame.pack();
            frame.setVisible(true);
        }
        
        private void luoKomponentit(Container container) {
            
        }
        
        public JFrame getFrame() {
            return frame;
        }
}
