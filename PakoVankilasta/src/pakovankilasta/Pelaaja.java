package pakovankilasta;

/**
 * Pelaaja-luokka luo pelaajan Vanki-nappulat.
 * 
 * @see pakovankilasta.Vanki
 *
 * @author $Olli Väisänen
 */
public class Pelaaja {
    
    private Vanki[] vangit;
    
    public Pelaaja() {
        this.vangit = new Vanki[4];
        for(int i=0; i<4; i++) {
            this.vangit[i] = new Vanki();
        }
    }
    
    protected int laudalla() {
        
        int laudalla = 4;
        for(int i=0; i<4; i++){
            if(this.vangit[i] == null){
                laudalla--;
            } else if(this.vangit[i].getSijainti() == null){
                laudalla--;
            }
        }
        return laudalla;
    }
    
    protected boolean voittaunut() {
        
        int veneessa = 0;
        for(int i=0; i<4; i++){
            if(this.vangit[i] == null) {
                veneessa++;
            }
        }
        return (veneessa >= 3);
    }

}
