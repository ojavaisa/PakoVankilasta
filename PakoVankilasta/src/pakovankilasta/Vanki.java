package pakovankilasta;

/**
 * Vanki-nappula toimii pelaajan pelinappulana pelissä.
 * 
 * @author $Olli Väisänen
 */
public class Vanki extends Pelinappula {
    
    /**
     * Vanki kuuluu tietylle Pelaajalle.
     */
    private Pelaaja pelaaja;

    public Vanki() {
        super();
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public void setPelaaja(Pelaaja pelaaja){
        this.pelaaja = pelaaja;
    }
    
    @Override
    public String toString() {
        return "x";
    }

}
