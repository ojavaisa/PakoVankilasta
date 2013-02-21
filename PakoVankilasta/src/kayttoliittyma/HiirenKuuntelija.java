package kayttoliittyma;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author $Olli Väisänen
 */
public class HiirenKuuntelija implements MouseListener {

    private Component component;
    private Peli peli;
    private boolean vankiValittu;
    private boolean kesken;

    public HiirenKuuntelija(Peli peli, Component component) {
        this.peli = peli;
        this.component = component;
        this.vankiValittu = false;
        this.kesken = true;
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
        sarake = this.peli.getLauta().muunnaX(x);
        rivi = this.peli.getLauta().muunnaY(y);

        if(this.kesken) {
            if (vankiValittu) {
                if (rivi == -1) {
                    if (this.peli.valitseVanki(rivi, sarake)) {
                        this.vankiValittu = true;
                    } else {
                        this.vankiValittu = false;
                    }
                } else if (this.peli.valitseVanki(rivi, sarake)) {
                    this.vankiValittu = true;
                } else if (this.peli.vuoro(rivi, sarake)) {
                    this.kesken = this.peli.kesken;
                    vankiValittu = false;
                }
                //valitaan kohderuutu
                //kohteen tarkistus:ok->siirto->vankivalittu=false
                //vangin vaihto
                //kohteen tarkistus:ei ok->ei tehdä mitään->seuraava klikkaus
            } else {
                //valitaan siirrettävä vanki
                if (this.peli.valitseVanki(rivi, sarake)) {
                    this.vankiValittu = true;
                } else {
                    this.vankiValittu = false;
                }
            }
        }

//        if (rivi >= 0 && rivi < 99 && sarake >= 0) {
//            System.out.println("Ruutu (" + sarake + "," + rivi + ")");
////            Ruutu valinta = lauta.getRivi(rivi).getRuutu(sarake);
//        } else if (rivi == -1 && sarake >= 0) {
//            System.out.println("Aloitussiirto");
//        } else if (rivi == 99 && sarake >= 0) {
//            System.out.println("Venesiirto");
//        } else {
//            System.out.println("Laudan ulkopuolella");
//        }
        component.repaint();

    }
}
