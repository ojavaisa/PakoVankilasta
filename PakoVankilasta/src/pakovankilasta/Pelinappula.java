package pakovankilasta;

/**
 *
 * @author Olli Väisänen
 */
public class Pelinappula {
    
    protected Ruutu sijainti;

    /**
     * Palauttaa muuttujan sijainti (ruudun jossa pelinappula on) arvon
     *
     * @return sijainti-muuttujan arvo
     */
    public Ruutu getSijainti() {
        return sijainti;
    }
    
    /**
     * Asettaa muuttujalle sijainti uuden arvon
     *
     * @param sijainti sijainti-muuttujan uusi arvo
     */
    public void setSijainti(Ruutu sijainti) {
        this.sijainti = sijainti;
    }

    /**
     * Kostruktori pelinappulalle, joka ei ole ruudussa
     * 
     * 
     */
    public Pelinappula() {
        this.sijainti = null;
    }
    
    /**
     * Konstruktori aloitusruudussa sijaitsevan pelinappulan luomiseen 
     * 
     * @param sijainti pelinappulan aloitusruutu
     */
    public Pelinappula(Ruutu sijainti) {
        this.sijainti = sijainti;
    }
        
}
