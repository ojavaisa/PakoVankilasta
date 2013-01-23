package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Vartija extends Pelinappula {
    
    //attribuuttina Rivi, jolle Vartija kuuluu?
    public Vartija(Ruutu sijainti) {
        super(sijainti);
    }

    @Override
    public String toString(){
        return "@";
    }
    
}
