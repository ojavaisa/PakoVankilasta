package pakovankilasta;

/**
 * Pelaaja-luokka luo pelaajan Vanki-nappulat. Pelaajalla on neljä Vanki-nappulaa.
 * Vangit luodaan Vanki[]-taulukkoon. Sitä mukaan kun pelaaja saa vankejaan pakoveneeseen,
 * taulukon kokoa muutetaan pienemmäksi.
 * 
 * @see pakovankilasta.Vanki
 *
 * @author $Olli Väisänen
 */
public class Pelaaja {
    
    /**
     * Pelaajan Vanki-nappulat taulukkona. Taulukkoa pienennetään, sitä mukaa kuin vankeja pääsee pakoveneeseen.
     * Vanki-nappulan, jonka sijainti-attribuutin arvo on null, tulkitaan olevan sellissä.
     */
    private Vanki[] vangit;
    
    /**
     * Konstruktori luo Pelaaja-olion ja tämän 4 Vanki-nappulaa.
     */
    public Pelaaja(int nro) {
        this.vangit = new Vanki[4];
        for(int i=0; i<4; i++) {
            this.vangit[i] = new Vanki();
        }
    }
    
    public Vanki getVanki(int nro){
        return this.vangit[nro];
    }
    
    /**
     * getVankiSellista() getteri palauttaa ensimmäisen sellissä olevan Vangin.
     * 
     * @return Vanki, joka ei ole laudalla
     */
    public Vanki getVankiSellista() {
        for(int i=0; i<this.getJaljella(); i++) {
            if(this.vangit[i].getSijainti() == null) {
                return this.vangit[i];
            }
        }
        //tänne ei pitäis päästä, tarkistus luokassa Peli
        return null; 
    }
    
    /**
     * Pelaajalla jäljellä olevien (laudalla tai sellissä) vankien lukumäärä saadaan Vanki[]-taulukon pituudesta.
     * 
     * @return Pelaajan jäljellä olevien vankien määrä
     */
    public int getJaljella() {
        return this.vangit.length;
    }
    
    /**
     * Palauttaa sellissä olevien vankien lukumäärän.
     * 
     * @return Sellissä olevien vankien lukumäärä.
     */
    public int getSellissa() {
        int sellissa = 0;
        for(int i=0; i<this.getJaljella(); i++) {
            if(this.vangit[i].getSijainti() == null) {
                sellissa++;
            }
        }
        return sellissa;
    }
    
    /**
     * Metodi, jolla jolla venesiirron jälkeen pienennetään vankien listaa yhdellä 
     * ja siirretään jäljellä olevat vangit (laudalla tai sellissä olevat) siihen
     * 
     * @param vanki Veneeseen siirretty vanki
     */
    public void siirraVeneeseen(Vanki vanki){
        Vanki[] temp = new Vanki[this.getJaljella()-1];
        int j=0;
        for(int i=0; i<this.getJaljella(); i++){
            if(this.vangit[i] != vanki) {
                temp[j] = this.vangit[i];
                j++;
            }
        }
        this.vangit = temp;
    }
    
}
