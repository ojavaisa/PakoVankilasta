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
        if(rivi >= 0 && rivi < 99 && sarake >= 0) {
            System.out.println("Ruutu (" + sarake + "," + rivi +")");
//            Ruutu valinta = lauta.getRivi(rivi).getRuutu(sarake);
        } else if (rivi==-1 && sarake >= 0) {
            System.out.println("Aloitussiirto");
        } else if (rivi==99 && sarake >= 0) {
            System.out.println("Venesiirto");
        } else {
            System.out.println("Laudan ulkopuolella");
        }
        

    }
}
