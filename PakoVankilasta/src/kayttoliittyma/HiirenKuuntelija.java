package kayttoliittyma;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Hiirenkuuntelija-luokka hoitaa graafisen käyttöliittymän komentojen kuuntelun.
 * 
 * @author $Olli Väisänen
 */
public class HiirenKuuntelija implements MouseListener {

    /**
     * Hiirenkuuntelija-luokalle onnetaan Piirtoalusta-olio repaint[]-metodia varten. 
     * Pelilauta piirretään uudelleen jokaisen klikkauksen jälkeen.
     */
    private Component component;
    /**
     * Peli-luokka hoitaa pelin pyörittämisen.
     */
    private Peli peli;
    /**
     * Hiiren klikkauksen toiminto riippuu siitä onko liikutettava vanki valittu vai ei
     */
    private boolean vankiValittu;
    /**
     * Hiiri reagoi vain jos peli on kesken.
     */
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

        if (this.kesken) {
            if (sarake != -1) {
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
                } else {
                    //valitaan siirrettävä vanki
                    if (this.peli.valitseVanki(rivi, sarake)) {
                        this.vankiValittu = true;
                    } else {
                        this.vankiValittu = false;
                    }
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
