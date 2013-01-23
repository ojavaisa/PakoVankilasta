package pakovankilasta;

/**
 * Pelinappula-luokka on pelissä käytettävien nappuloiden (Vartija ja Vanki) yliluokka.
 * Pelinappula sisältää tiedon Ruudusta, jossa on.
 * 
 * @author Olli Väisänen
 */
public class Pelinappula {
    
    protected Ruutu sijainti;

    /**
     * getSijainti palauttaa Pelinappulan sijainnin (Ruudun)
     *
     * @return sijainti-muuttujan arvon (Ruutu)
     */
    public Ruutu getSijainti() {
        return sijainti;
    }
    
    /**
     * liiku-metodi liikuttaa Pelinappulaa laudalla
     *
     * @param kohde Pelinappulan uusi Ruutu
     */
    public void liiku(Ruutu kohde) {
        
        if(kohde.getNappula() == null){
            if(this.sijainti != null){
                this.sijainti.setNappula(null);
            }
            kohde.setNappula(this);
            this.sijainti = kohde;
        } else { //Myöhemmin käyttöliittymässä!!
            System.out.println("Ruudussa on jo nappula!");
        }

    }

    /**
     * Kostruktori pelinappulalle, joka ei ole ruudussa (Vangit alussa)
     * 
     */
    public Pelinappula() {
        this.sijainti = null;
    }
    
    //Tarvitaanko tätä Vartijoille?
    /**
     * Konstruktori aloitusruudussa sijaitsevan pelinappulan luomiseen (Vartijat)
     * 
     * @param sijainti pelinappulan aloitusruutu
     */
    public Pelinappula(Ruutu sijainti) {
        this.sijainti = sijainti;
    }
        
}
