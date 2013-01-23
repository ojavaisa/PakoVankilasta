package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Pakoruutu extends Ruutu {

    public Pakoruutu() {
        super();
    }
    
    @Override
    public String toString(){
        if(this.nappula == null){
            return "|_";
        } else {
            return "|" + this.nappula.toString();
        }
    }    
    
}
