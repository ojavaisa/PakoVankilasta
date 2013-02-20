package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
//import pakovankilasta.Pelilauta;

/**
 *
 * @author $Olli Väisänen
 */
public class Piirtoalusta extends JPanel {

//    private Pelilauta lauta;
    private Peli peli;
    private static int ruudunKoko = 20;
    private static int vasenReuna = 50;
    private static int alaReuna = 400; //todellisuudessa alimman rivin yläkulma

    public Piirtoalusta(Peli peli) {
        super.setBackground(Color.WHITE);
        this.peli = peli;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        piirraLauta(g);
        piirraVartijat(g);
        piirraVangit(g);
        piirraVene(g);
    }

    private void piirraLauta(Graphics g) {
        int x, y;

        for (int i = 0; i < this.peli.getLauta().getKoko(); i++) {
            for (int j = 0; j < this.peli.getLauta().getRivi(i).getKoko(); j++) {
                x = this.peli.getLauta().getRivi(i).getRuutu(j).getSarake();
                x = (x * ruudunKoko) + vasenReuna;
                y = this.peli.getLauta().getRivi(i).getRuutu(j).getRiviNro();
                y = alaReuna - (y * ruudunKoko);

                if (this.peli.getLauta().getRivi(i).getRuutu(j).onkoPako()) {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, ruudunKoko, ruudunKoko);
                }
                if (this.peli.valittu != null && this.peli.getLauta().getRivi(i).getRuutu(j).getNappula() == this.peli.valittu) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, ruudunKoko, ruudunKoko);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x, y, ruudunKoko, ruudunKoko);

            }
        }
        if (this.peli.valittu != null && this.peli.valittu.getSijainti() == null) {
            g.setColor(Color.GREEN);
            g.fillRect(vasenReuna, alaReuna + ruudunKoko, (this.peli.getLauta().getKoko() - 1) * ruudunKoko, ruudunKoko);
        }
        g.setColor(Color.BLACK);
        g.drawRect(vasenReuna, alaReuna + ruudunKoko, (this.peli.getLauta().getKoko() - 1) * ruudunKoko, ruudunKoko);
    }

    private void piirraVartijat(Graphics g) {
        int x, y;

        for (int i = 1; i < this.peli.getLauta().getKoko(); i++) {
            x = this.peli.getLauta().getRivi(i).getVartija().getSijainti().getSarake();
            x = ((x * ruudunKoko) + vasenReuna) + 5; // +5 jotta nappula on keskellä ruutua!
            y = this.peli.getLauta().getRivi(i).getVartija().getSijainti().getRiviNro();
            y = (alaReuna - (y * ruudunKoko)) + 5;  // sama kuin yllä

            g.setColor(Color.BLACK);
            g.fillRect(x, y, 11, 11);  //nappuloiden koko 11, jotta kivasti keskellä ruutua
        }

    }

    private void piirraVangit(Graphics g) {
        int x, y;

        for (int i = 0; i < this.peli.getPelaajat().length; i++) {
            for (int j = 0; j < this.peli.getPelaaja(i).getJaljella(); j++) {
                if (this.peli.getPelaaja(i).getVanki(j).getSijainti() == null) {
                    y = alaReuna + ruudunKoko + 5; //10 pikseliä alimman rivin alapuolella
                    x = (i * 4 * 15) + (j * 15) + vasenReuna + 5; //ruudunKoko korvattu 15:lla, sellissä vangit eivät tarvitse ruudun levyistä piirtotilaa
                } else {
                    x = this.peli.getPelaaja(i).getVanki(j).getSijainti().getSarake();
                    x = ((x * ruudunKoko) + vasenReuna) + 5;
                    y = this.peli.getPelaaja(i).getVanki(j).getSijainti().getRiviNro();
                    y = (alaReuna - (y * ruudunKoko)) + 5;
                }
                asetaVari(g, i);
                g.fillOval(x, y, 11, 11);
                g.setColor(Color.BLACK);
                g.drawOval(x, y, 11, 11);

            }
        }
    }

    private void piirraVene(Graphics g) {
        int leveys = ((this.peli.getLauta().getKoko()-1) * ruudunKoko) / 2;
        int x = vasenReuna + (leveys / 2);
        int y = alaReuna - (this.peli.getLauta().getKoko() * ruudunKoko + 10);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, leveys, ruudunKoko);
        
        for (int i = 0; i < this.peli.getPelaajat().length; i++) {
            int veneessa = (4 - this.peli.getPelaaja(i).getJaljella());
        }
    }

    private void asetaVari(Graphics g, int pelaaja) {

        if (pelaaja == 0) {
            g.setColor(Color.BLUE);
        } else if (pelaaja == 1) {
            g.setColor(Color.RED);
        } else if (pelaaja == 2) {
            g.setColor(Color.GREEN);
        } else if (pelaaja == 3) {
            g.setColor(Color.YELLOW);
        } else {
            //Tänne ei pitäisi koskaan päätyä...
            g.setColor(Color.WHITE);
        }
    }
}
