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

    protected int leveys;
    protected Rivi[] rivit; //ArrayList tai vast?
    
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
    private void luoRivit(Rivi[] rivit) { //private?

        for(int i=0; i < rivit.length; i++){
            rivit[i] = new Rivi(this.leveys,i);
        }
        for(int i=0; i < rivit.length; i++){ 
            rivit[i].asetaVartija();
        }
    }
    
//    /**
//     * siirtoSallittu tarkistaa että siirto on joko vaakasuoraan tai pystysuoraan
//     * (Selvittää lähtöruudunkin???)
//     * ALSO pitää selvittää, että kyseisen rivin Vartija ei jyrää päälle!!!
//     * 
//     * 
//     * @param vanki
//     * @param sarake
//     * @param rivi
//     * @return 
//     */
//    public boolean siirtoSallittu(Vanki vanki, int sarake, int rivi){
//        
//        int vartijanRuudunNro;
//        //ENNEN tätä tapaus rivi=0!!! Siirto aina sallittu? tarviiko tulla tähän metodiin
//        if(vanki.getSijainti() == null){
//            for(int i=0; i<this.leveys; i++){
//                if(this.rivit[rivi].ruudut[i].getNappula() == this.rivit[rivi].vartija) { //Olioiden vertailu?
//                    vartijanRuudunNro = i;
//                }
//            }
//            if(vartijanRuudunNro <= sarake){ //TARKISTA aukot! -> vartijan#==sarake
//                if((rivi+1) >= (vartijanRuudunNro + (rivi+1))) { //TARKISTA että menee oikein!
//                    return false;
//                } else {
//                    return true;
//                }
//            } else {
//                if((rivi+1) >= (vartijanRuudunNro - (rivi+1))) { //TARKISTA
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//
//        }
//    }
    
//    /**
//     * reittiVapaa tarkistaa ettei siirron tiellä ole muita nappuloita (Vartijoita tai Vankeja)
//     * 
//     * @param vanki
//     * @param sarake
//     * @param rivi
//     * @return 
//     */
//    public boolean reittiVapaa(Vanki vanki, int sarake, int rivi) {
//        
//        if(vanki.getSijainti() == null) {
//            //???
//        } else {
//            Ruutu alku = vanki.getSijainti();
//            //HUOM! Jatka tästä! Miten saadaan nappulalta tieto sarakkeesta/rivistä???
//        }
//        
//        return true;
//    }
    
    /**
     * getRivi antaa parametrina annetun Rivin
     *
     * @param rivi Halutun Ruudun numero Rivillä
     * @return Haluttu Ruutu
     */
    public Rivi getRivi(int rivi) {
        return rivit[rivi];
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
