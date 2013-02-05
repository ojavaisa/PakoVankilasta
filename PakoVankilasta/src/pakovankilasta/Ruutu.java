package pakovankilasta;

/**
 * Ruutu-luokka muodostaa pelilaudan ruudut. Ruutu tuntee siinä olevan Pelinappulan.
 *
 * @author $Olli Väisänen
 */
public class Ruutu {
    
    /**
     * Ruudussa oleva Pelinappula (Vanki tai Vartija). Arvo null tarkoittaa tyhjää Ruutua.
     */
    private Pelinappula nappula;
    
    /**
     * Ruudun koordinaatit tallennetaan omiin muuttujiinsa.
     */
    private int riviNro, sarakeNro;
    
    /**
     * Ruutu voi olla tavallinen ruutu tai pakoruutu.
     */
    private boolean pakoRuutu;
    
    public Pelinappula getNappula() {
        return nappula;
    }
   
    public int getRiviNro() {
        return riviNro;
    }
    
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
     * //Antaa virheilmoituksen, jos siirto ei onnistu (ruudussa on jo nappula).
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
        this.nappula.setSijaintiNull();
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
