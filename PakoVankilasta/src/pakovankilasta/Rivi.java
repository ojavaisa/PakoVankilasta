package pakovankilasta;

/**
 *
 * @author $Olli Väisänen
 */
public class Rivi {
    
    private int riviNro;  //Alkaako numerointi venepäädystä vai vankilasta
    private Ruutu[] ruudut; //ArrayList tai vastaava?
    private Vartija vartija;

    public Rivi(int koko, int nro) {

        this.riviNro = nro;
        this.ruudut = new Ruutu[koko];
        luoRuudut(ruudut); //luodaan ruudut omassa metodissaan

    }

    /**
     * luoRuudut-metodi on konstruktorin käyttämä metodi joka luo ruudut-muuttujassa
     * olevat tyhjät Ruudut, sekä määrittää pakoruutujen paikat.
     *
     * @see pakovankilasta.Ruutu
     *
     * @param ruudut Rivin muodostava Ruutujen taulukko
     */
    private void luoRuudut(Ruutu[] ruudut) {
        
        int kanta = ruudut.length/2; //Lasketaan pakoruutukolmion kanta
        if(kanta%2 == 0){
            kanta++;
        }
        
        if((kanta-2*(ruudut.length-this.riviNro)) > 0) { //
            for(int i=0; i < ruudut.length; i++){
                if(i < ((ruudut.length-(kanta-2*(ruudut.length-this.riviNro)))/2)) {
                    ruudut[i] = new Ruutu(i, this.riviNro, false);
                } else if(i >= ((ruudut.length-(kanta-2*(ruudut.length-this.riviNro)))/2)+(kanta-2*(ruudut.length-this.riviNro))) {
                    ruudut[i] = new Ruutu(i, this.riviNro, false);
                } else {
                    ruudut[i] = new Ruutu(i, this.riviNro, true);
                }
            }
                
        } else {
            for(int i=0; i < ruudut.length; i++){
                ruudut[i] = new Ruutu(i, this.riviNro, false);
            }
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

        if (this.riviNro > 0) { 
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
        } else {
            this.vartija = null; //Ensimmäiselle riville (kauimpana venettä) ei tule Vartijaa.
        }

    }
    

    /**
     * getRiviNro-metodi antaa rivin järjestysnumeron vankilan pihasta.
     *
     * @return antaa rivin järjestynumeron
     */
    public int getRiviNro() {
        return riviNro;
    }
    
    public int getKoko() {
        return this.ruudut.length;
    }
    
    public Ruutu getRuutu(int sarakeNro) {
        return this.ruudut[sarakeNro];
    }
    
    //getVartija?
    
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
