package pakovankilasta;

/**
 * Vartija-luokka muodostaa pelin Riveillä sijaitsevat Vartija-nappulat.
 * Vartijat liikkuvat vain Rivillään, ja aina pelaajan siirron jälkeen.
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
        this.setSijainti(sijainti);
    }
    
//    protected void setRivi(Rivi rivi) {
//        this.rivi = rivi;
//    }

    @Override
    public void liiku(Ruutu kohde) {
        
        this.getSijainti().setNappulaNull();
        kohde.setNappula(this);
        this.setSijainti(kohde);
       
    }
    
    @Override
    public String toString(){
        return "@";
    }
    
}
