package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Rivi {

    protected int koko;
    protected int riviNro;  //Alkaako numerointi venepäädystä vai vankilasta
    protected Ruutu[] ruudut; //ArrayList tai vastaava?
    protected Vartija vartija;

    public Rivi(int koko, int nro) {

        this.koko = koko;
        this.riviNro = nro;
        this.ruudut = new Ruutu[koko];
        luoRuudut(ruudut); //luodaan ruudut omassa metodissaan

    }

    /**
     * luoRuudut-metodi on konstruktorin käyttämä ruudut-muuttujassa
     * (Ruudut[koko]) olevat tyhjät Ruudut
     *
     * @see pakovankilasta.Ruutu
     *
     * @param ruudut Rivin muodostava Ruutujen taulukko
     */
    private void luoRuudut(Ruutu[] ruudut) { //private?
        //mahdolliset Pakoruudut?
        
        for(int i=0; i < ruudut.length; i++) {
            ruudut[i] = new Ruutu();
        }
    }

    /**
     * asetaVartija-metodi asettaa Rivin Vartijan oikealle paikalleen
     * aloitustilanteessa. Metodi saa riviNro-tiedon Pelilauta-luokalta ja
     * asettaa sen perusteella Vartijan oikeaan kohtaan Rivillä.
     *
     * @see pakovankilasta.Pelilauta
     * @see pakovankilasta.Vartija
     *
     * @param riviNro Pelilauta-luokalta saatava tieto Rivin numerosta
     */
    public void asetaVartija() {

        if (this.riviNro > 0) { //Jos ensimmäinen rivi (kauimpana venettä) ei riville tule Vartijaa
            int ruutuNro;
            if ((this.riviNro) % 2 == 1) {
                ruutuNro = ruudut.length - ((this.riviNro / 2) + 1);
                this.vartija = new Vartija(ruudut[ruutuNro]);
                ruudut[ruutuNro].setNappula(this.vartija);
            } else {
                ruutuNro = (this.riviNro / 2) - 1;
                this.vartija = new Vartija(ruudut[ruutuNro]);
                ruudut[ruutuNro].setNappula(this.vartija);
            }           
        }

    }
    
    /**
     * getRuutu antaa parametrina annetun Ruudun
     *
     * @param sarake Halutun Ruudun numero Rivillä
     * @return Haluttu Ruutu
     */
    public Ruutu getRuutu(int sarake) {
        return ruudut[sarake];
    }
    
//    //Tarvitaanko?
//    /**
//     * getKoko antaa rivin koon.
//     *
//     * @return rivin koko
//     */
//    public int getKoko() {
//        return koko;
//    }

    /**
     * getRiviNro-metodi antaa rivin järjestysnumeron vankilan pihasta.
     *
     * @return antaa rivin järjestynumeron
     */
    public int getRiviNro() {
        return riviNro;
    }
    
    @Override
    public String toString(){
        String rivi = " ";
        
        if(riviNro < 9) {
            rivi = rivi + " " + (riviNro + 1);
        } else {
            rivi = rivi + (riviNro + 1);
        }

        for(int i=0; i<ruudut.length; i++){
            rivi = rivi + ruudut[i].toString();
        }
        rivi = rivi + "|";

        return rivi;
    }    
    
}
