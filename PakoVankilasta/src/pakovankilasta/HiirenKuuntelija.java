package pakovankilasta;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author $Olli Väisänen
 */
public class HiirenKuuntelija implements MouseListener {
    
    private Component component;
    private Pelilauta lauta;
    
    public HiirenKuuntelija(Pelilauta lauta, Component component) {
        this.lauta = lauta;
        this.component = component;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    
    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x, y, sarake, rivi;
        
        x = e.getX();
        y = e.getY();
        sarake = lauta.muunnaX(x);
        rivi = lauta.muunnaY(y);
        System.out.println("Ruutu (" + x + "," + y +")");
        //Ruutu valinta = lauta.getRivi(rivi).getRuutu(sarake);
    }
}
