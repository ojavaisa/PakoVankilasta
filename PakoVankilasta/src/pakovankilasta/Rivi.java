package pakovankilasta;

//import java.awt.Graphics;

/**
 * Pako vankilasta-pelin lauta muodostuu Riveistä, jotka ovat Ruutu-taulukoita.
 * Rivillä on yksi Vartija (lukuunottamatta ensimmäistä riviä, jolla ei ole
 * Vartijaa), joka pystyy liikkumaan vain Rivillä.
 *
 * @author $Olli Väisänen
 */
public class Rivi {

    /**
     * Rivin numero tallennetaan omaan muuttujaan.
     */
    private int riviNro;
    /**
     * Rivi muodostuu taulukosta Ruutuja.
     */
    private Ruutu[] ruudut;
    /**
     * Rivillä on yksi Vartija
     */
    private Vartija vartija;

    /**
     * Konstruktori luo Rivi-olion Ruudut. Konstruktori käyttää erillistä
     * luoRuudut-metodia.
     *
     * @param koko Rivin koko (sama kuin Pelilaudan leveys)
     * @param nro Rivin numero
     */
    public Rivi(int koko, int nro) {

        this.riviNro = nro;
        this.ruudut = new Ruutu[koko];
        luoRuudut(ruudut);

    }

    /**
     * luoRuudut-metodi on konstruktorin käyttämä metodi joka luo
     * ruudut-muuttujassa olevat tyhjät Ruudut, sekä määrittää pakoruutujen
     * paikat.
     *
     * @see pakovankilasta.Ruutu
     *
     * @param ruudut Rivin muodostava Ruutujen taulukko
     */
    private void luoRuudut(Ruutu[] ruudut) {

        int kanta = ruudut.length / 2; //Lasketaan pakoruutukolmion kanta
        if (kanta % 2 == 0) {
            kanta++;
        }

        if ((kanta - 2 * (ruudut.length - this.riviNro)) > 0) { //
            for (int i = 0; i < ruudut.length; i++) {
                if (i < ((ruudut.length - (kanta - 2 * (ruudut.length - this.riviNro))) / 2)) {
                    ruudut[i] = new Ruutu(i, this.riviNro, false);
                } else if (i >= ((ruudut.length - (kanta - 2 * (ruudut.length - this.riviNro))) / 2) + (kanta - 2 * (ruudut.length - this.riviNro))) {
                    ruudut[i] = new Ruutu(i, this.riviNro, false);
                } else {
                    ruudut[i] = new Ruutu(i, this.riviNro, true);
                }
            }

        } else {
            for (int i = 0; i < ruudut.length; i++) {
                ruudut[i] = new Ruutu(i, this.riviNro, false);
            }
        }

    }

    /**
     * asetaVartija-metodi asettaa Rivin Vartijan oikealle paikalleen
     * aloitustilanteessa. Vartijan paikka Rivillä määräytyy rivin numeron
     * perusteella.
     *
     * @see pakovankilasta.Vartija
     *
     */
    protected void asetaVartija() {

        if (this.riviNro > 0) {
            int ruutuNro;
            if ((this.riviNro) % 2 == 1) {
                ruutuNro = ruudut.length - ((this.riviNro / 2) + 1);
                this.vartija = new Vartija(ruudut[ruutuNro]);
                ruudut[ruutuNro].setNappula(this.vartija);
                //this.vartija.setRivi(this);
            } else {
                ruutuNro = (this.riviNro / 2) - 1;
                this.vartija = new Vartija(ruudut[ruutuNro]);
                ruudut[ruutuNro].setNappula(this.vartija);
                //this.vartija.setRivi(this);
            }
        } else {
            this.vartija = null; //Ensimmäiselle riville (kauimpana venettä) ei tule Vartijaa.
        }

    }

    /**
     * vartijaEiSyo-metodi tarkistaa, että Rivillä oleva Vartija-nappula ei saa
     * liikutettavaa Vanki-nappulaa kiinni.
     *
     * @param vanki Vanki-nappula, jota halutaan siirtää
     * @param sarake Rivin Ruutu (sarake), johon Vanki on siirtymässä
     * @return totuusarvo siitä, onko siirto sallittu (siirron on oltava
     * sellainen, että pelaajan omaa nappulaa ei syödä)
     */
    //Tarkistaa tällä hetkellä vain että juuri liikkunutta Vankia ei syödä! pitää tarkistaa vielä muut saman pelaajan vangit!
    public boolean vartijaEiSyo(Vanki vanki, int sarake) {

        if (this.riviNro == 0) {
            return true;
        } else {
            int vartijanSarake = this.vartija.getSijainti().getSarake();
            if (sarake > vartijanSarake) {
                if ((vartijanSarake + siirronPituus(vanki, sarake)) >= sarake) {
                    return false;
                } else { //vartijanSarake + siirronPituus < sarake
                    return true;
                }
            } else { //sarake < vartijanSarake
                if ((vartijanSarake - siirronPituus(vanki, sarake)) <= sarake) {
                    return false;
                } else { //vartijanSarake - siirronPituus > sarake
                    return true;
                }
            }
        }
    }

    /**
     * siirronPituus-metodi palauttaa halutun siirron pituuden Ruutujen
     * lukumääränä. Tietoa tarvitaan Vartijan liikutteluun.
     *
     * @param vanki Vanki-nappula, jota ollaan siirtämässä
     * @param sarake kohdesarake, johon ollaan liikkumassa
     * @return siirron pituus Ruutujen lukumääränä.
     */
    public int siirronPituus(Vanki vanki, int sarake) {

        if (vanki.getSijainti() == null) {
            return (this.riviNro + 1);
        } else if (vanki.getSijainti().getRiviNro() == this.riviNro) {
            if (sarake - vanki.getSijainti().getSarake() > 0) {
                return (sarake - vanki.getSijainti().getSarake());
            } else {
                return (vanki.getSijainti().getSarake() - sarake);
            }
        } else { //Liikutaan sarakkeella
            if (this.riviNro - vanki.getSijainti().getRiviNro() > 0) {
                return (this.riviNro - vanki.getSijainti().getRiviNro());
            } else {
                return (vanki.getSijainti().getRiviNro() - this.riviNro);
            }
        }
    }

    /**
     * liikutaVartijaa-metodi liikuttaa Rivin Vartijaa pelaajan siirron jälkeen
     * liikkuneen Vangin suuntaan. Liikkuvan Vartijan siirron varrella olevat
     * Vanki-nappulat joutuvat takaisin selliin.
     *
     * @param vanki Vanki-nappula, joka liikkui
     * @param sarake Ruudun sarake, johon Vanki liikkui
     */
    public void liikutaVartijaa(Vanki vanki, int pituus) {

        int min, max, uusi;
        
        if (vanki.getSijainti().getSarake() > this.vartija.getSijainti().getSarake()) {
            min = this.vartija.getSijainti().getSarake();
            max = min + pituus;
            uusi = max;
        } else {
            max = this.vartija.getSijainti().getSarake();
            min = max - pituus;
            uusi = min;
        }
        
        for(int i=min; i<=max; i++){ //Tarkistetaan matkan varrella olevat Vangit
                if (this.ruudut[i].getNappula() != null) {
                    this.ruudut[i].setNappulaNull();
                }
            }
        this.vartija.liiku(this.ruudut[uusi]);
    }

    public int getRiviNro() {
        return riviNro;
    }

    public int getKoko() {
        return this.ruudut.length;
    }

    /**
     * getRuutu-metodi antaa pyydetyn Ruudun.
     *
     * @param sarakeNro halutun Ruudun numero Rivillä
     * @return pyydetty Ruutu
     */
    public Ruutu getRuutu(int sarakeNro) {
        return this.ruudut[sarakeNro];
    }

    public Vartija getVartija() {
        return this.vartija;
    }

    @Override
    public String toString() {
        String rivi = " ";

        if (riviNro < 9) {
            rivi = rivi + " " + (riviNro + 1);
        } else {
            rivi = rivi + (riviNro + 1);
        }

        for (int i = 0; i < ruudut.length; i++) {
            rivi = rivi + ruudut[i].toString();
        }
        rivi = rivi + "|";

        return rivi;
    }
    
//    public void piirra(Graphics g) {
//        for(int i=0; i<ruudut.length; i++){
//            ruudut[i].piirra(g);
//        }
//        
//    }
}
