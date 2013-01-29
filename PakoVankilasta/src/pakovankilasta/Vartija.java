package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Vartija extends Pelinappula {
    
    //attribuuttina Rivi, jolle Vartija kuuluu?
    //protected Rivi rivi;
    
    /**
     * Konstruktori aloitusruudussa sijaitsevan Vartijan luomiseen.
     * Kaikki Vartijat luodaan aloitusruutuihinsa.
     *
     * @param sijainti pelinappulan aloitusruutu
     */
    public Vartija(Ruutu sijainti) {
        this.sijainti = sijainti;
        sijainti.setNappula(this);
    }

    @Override
    public void liiku(Ruutu kohde) {
        
        this.sijainti.setNappulaNull();
        kohde.setNappula(this);
        this.sijainti = kohde;
       
    }
    
    @Override
    public String toString(){
        return "@";
    }
    
}
