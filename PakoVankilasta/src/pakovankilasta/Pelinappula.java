package pakovankilasta;

/**
 * Pelinappula-luokka on pelissä käytettävien nappuloiden (Vartija ja Vanki) yliluokka.
 * Pelinappula sisältää tiedon Ruudusta, jossa on.
 * 
 * @author Olli Väisänen
 */
public class Pelinappula {
    
    private Ruutu sijainti;

    /**
     * getSijainti palauttaa Pelinappulan sijainnin (Ruudun)
     *
     * @return sijainti-muuttujan arvon (Ruutu)
     */
    public Ruutu getSijainti() {
        return sijainti;
    }
    
    protected void setSijainti(Ruutu ruutu) {
        this.sijainti = ruutu;
    }
    
    protected void setSijaintiNull() {
        this.sijainti = null;
    }
    
    /**
     * liiku-metodi liikuttaa Pelinappulaa laudalla
     *
     * @param kohde Pelinappulan uusi Ruutu
     */
    public void liiku(Ruutu kohde) {
        
        if(kohde.getNappula() == null){
            if(this.sijainti != null){
                this.sijainti.setNappulaNull();
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

        
}
