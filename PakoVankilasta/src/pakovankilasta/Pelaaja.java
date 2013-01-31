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
            this.vangit[i].setPelaaja(this);
        }
    }
    
    protected boolean laudalla(int vanginNro) {
        
        if(this.vangit[vanginNro] == null){
            return false;
        } else if(this.vangit[vanginNro].getSijainti() == null) {
            return false;
        } else {
            return true;
        }
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
