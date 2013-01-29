package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Vanki extends Pelinappula {

    public Vanki() {
        super();
    }
    
    public void takaisinSelliin() {
        
        Ruutu ruutu = this.sijainti;
        ruutu.setNappulaNull();
        this.sijainti = null;
    }
    
    @Override
    public String toString() {
        return "x";
    }
}
