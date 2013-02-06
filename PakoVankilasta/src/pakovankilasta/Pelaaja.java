package pakovankilasta;

/**
 * Pelaaja-luokka luo pelaajan Vanki-nappulat. Pelaajalla on neljä Vanki-nappulaa.
 * 
 * @see pakovankilasta.Vanki
 *
 * @author $Olli Väisänen
 */
public class Pelaaja {
    
    private Vanki[] vangit;
    private int pelaajaNro;
    
    /**
     * Konstruktori luo Pelaaja-olion ja tämän 4 Vanki-nappulaa.
     */
    public Pelaaja(int nro) {
        this.pelaajaNro = nro;
        this.vangit = new Vanki[4];
        for(int i=0; i<4; i++) {
            this.vangit[i] = new Vanki(i);
            this.vangit[i].setPelaaja(this);
        }
    }
    
    public int getPelaajaNro() {
        return this.pelaajaNro;
    }
    
    public Vanki getVanki(int nro){
        return this.vangit[nro];
    }
    
    /**
     * laudalla-metodi kertoo onko pelaajan kysytty Vanki-nappula Pelilaudalla.
     * Pelaajan Vangit voivat olla laudan lisäksi veneessä (Vanki == null) tai 
     * sellissä (Vanki.sijainti == null).
     * 
     * @param vanginNro Pelaajan Vangin numero (0..3)
     * @return totuusarvo siitä onko kysytty Vanki-nappula laudalla vai ei.
     */
    protected boolean laudalla(int vanginNro) {
        
        if(this.vangit[vanginNro] == null){
            return false;
        } else if(this.vangit[vanginNro].getSijainti() == null) {
            return false;
        } else {
            return true;
        }
    }
    
//    protected boolean kaikkiLaudalla() {
//        int laudalla=0;
//        for(int i=0; i<4; i++){
//            if(laudalla(i)){
//                laudalla++;
//            }
//        }
//        if(laudalla==4) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    
    /**
     * veneessa-metodi kertoo kuinka moni Pelaajan Vangeista on veneessä (Vanki == null).
     * Jos kolme Pelaajan neljästä Vangista on veneessä, Pelaaja on voittanut. 
     * 
     * @return veneessä olevien Vankien lukumäärä
     */
    protected int veneessa() {
        
        int veneessa = 0;
        for(int i=0; i<4; i++){
            if(this.vangit[i] == null) {
                veneessa++;
            }
        }
        return veneessa;
    }

}
