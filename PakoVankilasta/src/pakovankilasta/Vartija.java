package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Vartija extends Pelinappula {
    
    //attribuuttina Rivi, jolle Vartija kuuluu?
    //protected Rivi rivi;
    
    public Vartija(Ruutu sijainti) {
        super(sijainti);
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
