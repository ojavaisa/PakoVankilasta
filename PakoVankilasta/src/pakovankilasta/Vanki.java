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
        this.sijainti = null;
    }
    
    @Override
    public String toString() {
        return "x";
    }
}
