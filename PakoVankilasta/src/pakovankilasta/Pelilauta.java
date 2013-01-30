package pakovankilasta;

/**
 * Pelilauta-luokka muodostaa pelin laudan. Pelilauta koostuu Riveistä.
 * Pelilaudan leveyden määrittää käyttäjän antama parametri koko. Laudan
 * korkeus on koko+1. //Blaa blaa, jotain laudan muodosta, pakoruuduista ja vartijoista? 
 * 
 * @see pakovankilasta.Rivi
 *
 * @author $Olli Väisänen
 */
public class Pelilauta {

    private int leveys;
    private Rivi[] rivit; //ArrayList tai vast?
    
    public Pelilauta(int koko) {

        this.leveys = koko;

        this.rivit = new Rivi[this.leveys+1]; //Laudan korkeus on leveys+1
        luoRivit(rivit);
    }
    
    /**
     * luoRivit-metodi on konstruktorin käyttämä metodi, joka luo rivit-muuttujassa
     * (Rivi[koko]) olevat Rivit ja asettaa niihin Vartijat (Rivi-luokan metodilla asetaVartija).
     *
     * @see pakovankilasta.Rivi
     * @see pakovankilasta.Rivi#asetaVartija(int)
     *
     * @param rivit Pelilaudan muodostava Rivien taulukko
     */
    private void luoRivit(Rivi[] rivit) {

        for(int i=0; i < rivit.length; i++){
            rivit[i] = new Rivi(this.leveys,i);
        }
        for(int i=0; i < rivit.length; i++){ 
            rivit[i].asetaVartija();
        }
    }
    
    /**
     * siirtoSallittu tarkistaa että siirto on joko vaakasuoraan tai pystysuoraan
     * 
     * @param vanki
     * @param sarake
     * @param rivi
     * @return 
     */
    protected boolean siirtoSallittu(Vanki vanki, int sarake, int rivi){
        
        if(rivi == 0 || vanki.getSijainti() == null) {
            return true;
        } else if(vanki.getSijainti().getRiviNro() == rivi) {
            return true;
        } else if(vanki.getSijainti().getSarake() == sarake) {
            return true;
        } else {
            return false;
        }
    }
    
    

    //Erillinen metodi tarkistaa, että kohteena olevan Rivin Vartija ei syö pelaajan omia nappuloita.
    //vartijaEiSyo Esim. Pelaajan tai Rivin metodi???
//    protected boolean vartijaEiSyo(Vanki vanki, int sarake, int rivi) {
//        
//    }
    
    /**
     * reittiVapaa tarkistaa että kohteena oleva Ruutu on vapaa ja ettei siirron tiellä ole Vartijoita
     * 
     * @param vanki Vanki, jota halutaan liikuttaa
     * @param sarake kohteena olevan Ruudun sarakeen numero
     * @param rivi kohteena olevan Ruudun rivinumero
     * @return totuusarvo true, jos edessä ei ole vartijoita
     */
    protected boolean reittiVapaa(Vanki vanki, int sarake, int rivi) {
        
        if(this.rivit[rivi].getRuutu(sarake).getNappula() != null) {
            return false;
        } else {
            if(vanki.getSijainti() == null) {
                for(int i=0; i<=rivi; i++){
                    if(rivit[i].getRuutu(sarake).getNappula() == rivit[i].getVartija()) {
                        return false;
                    }
                }
            } else if(vanki.getSijainti().getSarake() == sarake) {
                return reittiSarakkeella(vanki, sarake, rivi);
            } else if(vanki.getSijainti().getRiviNro() == rivi) {
                return reittiRivilla(vanki, sarake, rivi);
            } else {
                System.out.println("You're not supposed to get here!");
                return false;
            }
        }

    }
    
    private boolean reittiSarakkeella(Vanki vanki, int sarake, int rivi) {
        
        if(vanki.getSijainti().getRiviNro() < rivi){
            for(int i=vanki.getSijainti().getRiviNro(); i<=rivi; i++) {
                if(rivit[i].getRuutu(sarake).getNappula() == rivit[i].getVartija()) {
                        return false;
                } 
            } 
            return true;
        } else {
            for(int i=vanki.getSijainti().getRiviNro(); i>=rivi; i--) {
                if(rivit[i].getRuutu(sarake).getNappula() == rivit[i].getVartija()) {
                        return false;
                } 
            }
            return true;
        }
    }
    
    private boolean reittiRivilla(Vanki vanki, int sarake, int rivi) { //Jatka tästä!
        return true;
    }
    
    /**
     * getRiviNro antaa parametrina annetun Rivin
     *
     * @param rivi Halutun Rivin numero (alkaen vankilan pihasta)
     * @return Haluttu Rivi
     */
    public Rivi getRivi(int rivi) {
        return rivit[rivi];
    }
    
    /**
     * getKoko-metodi palauttaa pelilaudan korkeuden eli Rivien lukumäärä. 
     * Huom! korkeus = leveys + 1 
     * 
     * @return Pelilaudan korkeus eli Rivien lukumäärä.
     */
    public int getKoko() {
        return this.rivit.length;
    }

    @Override
    public String toString(){

        String lauta = "    ";
        
        for(int i=0; i<this.leveys; i++){
            int kirjain = 'A';
            kirjain+=i;
            lauta = lauta + (char) kirjain +" ";
        }
        lauta = lauta + "\n";

        for(int i=rivit.length-1; i>=0; i--){
            lauta = lauta + rivit[i] + "\n";
        }
        return lauta;
    }    
    
}
