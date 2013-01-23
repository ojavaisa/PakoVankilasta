package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Rivi {

    protected int koko;
    protected int riviNro;  //Alkaako numerointi venepäädystä vai vankilasta
    protected Ruutu[] ruudut; //ArrayList tai vastaava?
    //protected Vartija vartija;

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
        //tarkistetaan ja korjataan parittomuus
        //mahdolliset Pakoruudut?
        
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
    
//    //Tarvitaanko?
//    /**
//     * getRiviNro-metodi antaa rivin järjestysnumeron (alkaen venepäädystä).
//     *
//     * @return antaa rivin järjestynumeron
//     */
//    public int getRiviNro() {
//        return koko;
//    }



}
