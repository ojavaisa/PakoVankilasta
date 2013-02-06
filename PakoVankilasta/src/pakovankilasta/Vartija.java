package pakovankilasta;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Vartija-luokka muodostaa pelin Riveillä sijaitsevat Vartija-nappulat.
 * Vartijat liikkuvat vain Rivillään, ja aina pelaajan siirron jälkeen.
 * 
 * @author $Olli Väisänen
 */
public class Vartija extends Pelinappula {
    
    //attribuuttina Rivi, jolle Vartija kuuluu?
    //protected Rivi rivi;
    
    /**
     * Konstruktori aloitusruudussa sijaitsevan Vartijan luomiseen.
     * Kaikki Vartijat luodaan aloitusruutuihinsa.
     *
     * @param sijainti pelinappulan aloitusruutu
     */
    public Vartija(Ruutu sijainti) {
        this.setSijainti(sijainti);
    }
    
//    protected void setRivi(Rivi rivi) {
//        this.rivi = rivi;
//    }

    @Override
    public void liiku(Ruutu kohde) {
        
        this.getSijainti().setNappulaNull();
        kohde.setNappula(this);
        this.setSijainti(kohde);
       
    }
    
    @Override
    public String toString(){
        return "@";
    }
    
    @Override
    public void piirra(Graphics g) {
        
        int x = ((this.getSijainti().getSarake() * 20) + 50) + 5;
        int y = (400 - (this.getSijainti().getRiviNro() * 20)) + 5;
        
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 11, 11);
        
    }
    
}
