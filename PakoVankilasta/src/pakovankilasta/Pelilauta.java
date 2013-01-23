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

        if(koko<5 || koko>15){ //Tämä osa pääohjelmaan?
            this.leveys = 9;
            System.out.println("Annoit virheellisen laudan koon. \n" +
                    "Käytetään oletuslautaa (9x9).\n");
        } else if((koko%2)==0){
            this.leveys = koko - 1;
            System.out.println("Annoit parillisen laudan koon.\n" +
                    "Käytetään yhtä pienempää laudan kokoa (" +this.leveys+ "x" +this.leveys+ ").\n");
        } else {
            this.leveys = koko;
            System.out.println("Laudan koko on (" +this.leveys+ "x" +this.leveys+ ").\n");
        }

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
    
    public boolean reittiVapaa(Vanki vanki, int sarake, int rivi) {
        
        if(vanki.getSijainti() == null) {
            //???
        } else {
            Ruutu alku = vanki.getSijainti();
            //HUOM! Jatka tästä! Miten saadaan nappulalta tieto sarakkeesta/rivistä???
        }
        
        return true;
    }
    
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
