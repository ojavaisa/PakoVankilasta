package pakovankilasta;

/**
 *
 * @author Olli Väisänen
 */
public class Pelinappula {
    
    protected Ruutu sijainti;

    /**
     * Get the value of sijainti
     *
     * @return the value of sijainti
     */
    public Ruutu getSijainti() {
        return sijainti;
    }
    
    /**
     * Set the value of sijainti
     *
     * @param Sijainti new value of sijainti
     */
    public void setSijainti(Ruutu sijainti) {
        this.sijainti = sijainti;
    }

    public Pelinappula() {
        this.sijainti = null;
    }
    
    public Pelinappula(Ruutu sijainti) {
        this.sijainti = sijainti;
    }
        
}
