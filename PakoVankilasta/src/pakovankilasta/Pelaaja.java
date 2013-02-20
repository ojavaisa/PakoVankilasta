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
    
    public Vanki getVankiSellista() {
        for(int i=0; i<this.getJaljella(); i++) {
            if(this.vangit[i].getSijainti() == null) {
                return this.vangit[i];
            }
        }
        //tänne ei pitäis päästä, tarkistus luokassa Peli
        return null; 
    }
    
    public int getJaljella() {
        return this.vangit.length;
    }
    
    public int getSellissa() {
        int sellissa = 0;
        for(int i=0; i<this.getJaljella(); i++) {
            if(this.vangit[i].getSijainti() == null) {
                sellissa++;
            }
        }
        return sellissa;
    }
    
    //metodi jolla venesiirron jälkeen pienennetään vankien listaa yhdellä ja siirretään jäljellä olevat vangit (laudalla tai sellissä olevat) siihen
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
