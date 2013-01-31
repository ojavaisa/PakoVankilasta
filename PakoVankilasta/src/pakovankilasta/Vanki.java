package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Vanki extends Pelinappula {
    
    private Pelaaja pelaaja;

    public Vanki() {
        super();
    }
    
//    public void takaisinSelliin() {
//        
//        Ruutu ruutu = this.getSijainti();
//        ruutu.setNappulaNull();
//        this.setSijainti(null);
//    }
    
    protected Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    protected void setPelaaja(Pelaaja pelaaja){
        this.pelaaja = pelaaja;
    }
    
    @Override
    public String toString() {
        return "x";
    }
}
