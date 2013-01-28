package pakovankilasta;

/**
 * Ruutu-luokka muodostaa pelilaudan ruudut. Ruutu tuntee siinä olevan Pelinappulan.
 *
 * @author $Olli Väisänen
 */
public class Ruutu {
    
    private Pelinappula nappula;
    private int riviNro, sarakeNro;
    private boolean pakoRuutu;
    
    /**
     * getNappula-metodi antaa Ruudussa olevan pelinappulan.
     *
     * @return Ruudussa oleva pelinappula
     */
    public Pelinappula getNappula() {
        return nappula;
    }
    
    /**
     * getRivi-metodi antaa Ruudun rivinumeron.
     *
     * @return Ruudun rivin numero
     */
    public int getRivi() {
        return riviNro;
    }
    
    /**
     * getSarake-metodi antaa Ruudun sarakenumeron.
     *
     * @return Ruudun sarakkeen numero
     */
    public int getSarake() {
        return sarakeNro;
    }
    
    /**
     * onkoPako-metodi kertoo onko Ruutu pakoruutu.
     * 
     * @return Totuusarvona onko Ruutu pakoruutu.
     */
    public boolean onkoPako() {
        return pakoRuutu;
    }

    /**
     * setNappula-metodi asettaa ruutuun pelinappulan.
     * Antaa virheilmoituksen, jos siirto ei onnistu (ruudussa on jo nappula).
     *
     * @param nappula nappula-muuttujan uusi arvo
     */
    public void setNappula(Pelinappula nappula) { //Entä kun ruutuun siirtyy vartija?
        if(this.nappula == null) {
            this.nappula = nappula;
        } else {  //Myöhemmin käyttöliittymässä!!!
            System.out.println("Siirto ei onnistu!");
        }
    }

    /**
     * setNappulaNull-metodi tyhjentää ruudun (asettaa Ruudun Pelinappulan arvoksi null).
     */
    public void setNappulaNull() {
        this.nappula = null;
    }

    /**
     * Konstruktori luo tyhjän ruudun
     * 
     */
    public Ruutu(int sarake, int rivi, boolean pako) {
        this.nappula = null;
        this.riviNro = rivi;
        this.sarakeNro = sarake;
        this.pakoRuutu = pako;
    }

    @Override
    public String toString() {
        if(this.nappula == null) {
            if(this.pakoRuutu){
                return "|_";
            } else {
                return "| ";
            }
        } else {
            return "|" + this.nappula.toString();
        }
    }
    
    

}
