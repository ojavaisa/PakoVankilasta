package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Ruutu {
    
    protected Pelinappula nappula;
    
    /**
     * Get the value of nappula
     *
     * @return the value of nappula
     */
    public Pelinappula getNappula() {
        return nappula;
    }

    /**
     * Set the value of nappula
     *
     * @param nappula new value of nappula
     */
    public void setNappula(Pelinappula nappula) {
        this.nappula = nappula;
    }

    public Ruutu() {
        this.nappula = null;
    }

}
