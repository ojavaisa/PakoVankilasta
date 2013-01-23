package pakovankilasta;

/**
 * Ruutu-luokka muodostaa pelilaudan ruudut. Ruutu tuntee siinä olevan Pelinappulan.
 *
 * @author $Olli Väisänen
 */
public class Ruutu {
    
    protected Pelinappula nappula;
    
    /**
     * getNappula-metodi antaa ruudussa olevan pelinappulan.
     *
     * @return Ruudussa oleva pelinappula
     */
    public Pelinappula getNappula() {
        return nappula;
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

    //setNappula(null) ruudun tyhjentämiseen?

    /**
     * Konstruktori luo tyhjän ruudun
     * 
     */
    public Ruutu() {
        this.nappula = null;
    }

    @Override
    public String toString() {
        if(this.nappula == null) {
            return "| ";
        } else {
            return "|" + this.nappula.toString();
        }
    }
    
    

}
