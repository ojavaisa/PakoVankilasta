package kayttoliittyma;

import javax.swing.SwingUtilities;

/**
 * Pako Vankilasta -pelin pääohjelmaluokka
 *
 * @author Olli Väisänen
 */
public class PakoVankilasta {

    /**
     * @param args Komentoriviargumentit
     */
    
    public static void main(String[] args) {
        
        Kayttoliittyma kayttoliittyma; 
        
        kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
             
    }
    
}
