package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Ruutu {
    
    protected Pelinappula nappula;
    
    /**
     * Palauttaa muuttujan nappula (ruudussa oleva pelinappula) arvo
     *
     * @return nappula-muuttujan arvo
     */
    public Pelinappula getNappula() {
        return nappula;
    }

    /**
     * Asettaa muuttujan nappula (ruudussa oleva pelinappula) arvo
     *
     * @param nappula nappula-muuttujan uusi arvo
     */
    public void setNappula(Pelinappula nappula) {
        this.nappula = nappula;
    }

    /**
     * Konstruktori luo tyhjän ruudun
     * 
     * 
     */
    public Ruutu() {
        this.nappula = null;
    }

}
