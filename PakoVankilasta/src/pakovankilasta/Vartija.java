package pakovankilasta;

/**
 * Vartija-luokka muodostaa pelin Riveillä sijaitsevat Vartija-nappulat.
 * Vartijat liikkuvat vain Rivillään, ja aina pelaajan siirron jälkeen.
 * 
 * @author $Olli Väisänen
 */
public class Vartija extends Pelinappula {
    
    /**
     * Konstruktori aloitusruudussa sijaitsevan Vartijan luomiseen.
     * Kaikki Vartijat luodaan aloitusruutuihinsa.
     *
     * @param sijainti pelinappulan aloitusruutu
     */
    public Vartija(Ruutu sijainti) {
        super();
        this.setSijainti(sijainti);
    }

    @Override
    public void liiku(Ruutu kohde) {
        
        //this.getSijainti().setNappulaNull(); //Miks tämä aiheutti ongelman???
        kohde.setNappula(this);
        this.setSijainti(kohde);
       
    }
    
    @Override
    public String toString(){
        return "@";
    }

    
}
