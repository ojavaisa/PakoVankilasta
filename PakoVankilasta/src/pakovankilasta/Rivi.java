package pakovankilasta;

/**
 * Pako vankilasta-pelin lauta muodostuu Riveistä, jotka ovat Ruutu-taulukoita.
 * Rivillä on yksi Vartija (lukuunottamatta ensimmäistä riviä, jolla ei ole
 * Vartijaa), joka pystyy liikkumaan vain Rivillä.
 *
 * @see pakovankilasta.Ruutu
 * @see pakovankilasta.Vartija
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
     * paikat. Jos Rivillä on pakoruutuja, Rivi luodaan erillisellä alimetodilla.
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

        if ((kanta - 2 * (ruudut.length - this.riviNro)) > 0) {
            luoPakoRuudut(kanta, ruudut);

        } else {
            for (int i = 0; i < ruudut.length; i++) {
                ruudut[i] = new Ruutu(i, this.riviNro, false);
            }
        }

    }
    
    /**
     * luoPakoRuudut on luoRuudut-metodin käyttämä metodi joka määrittää pakoruutujen
     * paikat rivinumeron ja pakoruutujen muodostaman kannan pituuden avulla.
     *
     * @see pakovankilasta.Ruutu
     *
     * @param kanta Pakoruutujen muodostaman kolmion kannan pituus
     * @param ruudut Rivin muodostava Ruutujen taulukko
     */
    private void luoPakoRuudut(int kanta, Ruutu[] ruudut) {
        
        for (int i = 0; i < ruudut.length; i++) {
                if (i < ((ruudut.length - (kanta - 2 * (ruudut.length - this.riviNro))) / 2)) {
                    ruudut[i] = new Ruutu(i, this.riviNro, false);
                } else if (i >= ((ruudut.length - (kanta - 2 * (ruudut.length - this.riviNro))) / 2) + (kanta - 2 * (ruudut.length - this.riviNro))) {
                    ruudut[i] = new Ruutu(i, this.riviNro, false);
                } else {
                    ruudut[i] = new Ruutu(i, this.riviNro, true);
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
     * vartijaEiSyo-metodi tarkistaa, että Rivillä oleva Vartija-nappula ei saa
     * liikutettavaa Vanki-nappulaa kiinni. vartijaEiSyo() tarkistaa että
     * liikkumassa olevaa vankia ei syödä. Pelaajan muiden vankien turvassa
     * pysyminen tarkistetaan erillisella metodilla.
     *
     * @param vanki Vanki-nappula, jota halutaan siirtää
     * @param sarake Rivin Ruutu (sarake), johon Vanki on siirtymässä
     * @return totuusarvo siitä, onko siirto sallittu (siirron on oltava
     * sellainen, että pelaajan omaa nappulaa ei syödä)
     */
    public boolean vartijaEiSyo(Vanki vanki, int sarake) {

        if (this.riviNro == 0) {
            return true;
        } else {
            int vartijanSarake = this.vartija.getSijainti().getSarake();
            if (sarake > vartijanSarake) {
                if ((vartijanSarake + siirronPituus(vanki, sarake)) >= sarake) {
                    return false;
                }
            } else { //sarake < vartijanSarake
                if ((vartijanSarake - siirronPituus(vanki, sarake)) <= sarake) {
                    return false;
                }
            }
            return vartijaEiSyoMuita(vanki, sarake);
        }
    }

    /**
     * vartijaEiSyoMuita() tarkistaa, että pelaajan muut kuin juuri liikkumassa
     * oleva Vanki pysyvät turvassa.
     *
     * @param vanki Vanki jota ollaan liikuttamassa
     * @param sarake Rivin Ruutu (sarake), johon Vanki on siirtymässä
     * @return totuusarvo siitä, onko siirto sallittu (siirron on oltava
     * sellainen, että pelaajan omaa nappulaa ei syödä)
     */
    private boolean vartijaEiSyoMuita(Vanki vanki, int sarake) {
        Pelaaja pelaaja = vanki.getPelaaja();
        int vartijanSarake = getVartija().getSijainti().getSarake();

        for (int i = 0; i < pelaaja.getJaljella(); i++) {
            if (pelaaja.getVanki(i).getSijainti() != null) {
                if (pelaaja.getVanki(i) != vanki) {
                    if (pelaaja.getVanki(i).getSijainti().getRiviNro() == this.riviNro) {
                        int vanginSarake = pelaaja.getVanki(i).getSijainti().getSarake();
                        if (sarake > vartijanSarake && vanginSarake > vartijanSarake && vanginSarake < sarake) {
                            return false;
                        } else if (sarake < vartijanSarake && vanginSarake < vartijanSarake && vanginSarake > sarake) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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
     * @param suunta Totuusarvo liikkumisen suunnasta (arvo true vastaa oikeaa
     * laitaa ja false vasenta)
     * @param pituus Siirron pituus
     */
    public void liikutaVartijaa(boolean suunta, int pituus) {

        int min, max, uusi;

        if (suunta) {
            min = this.vartija.getSijainti().getSarake();
            max = min + pituus;
            uusi = max;
        } else {
            max = this.vartija.getSijainti().getSarake();
            min = max - pituus;
            uusi = min;
        }
        this.syoVangit(min, max);
        this.vartija.liiku(this.ruudut[uusi]);
    }
    
    /**
     * syoVangit on liikutaVartijaa-metodin käyttämä metodi Vartijan matkan varrella olevien 
     * Vankien passittamiseksi takaisin selliin.
     * 
     * @param min Vartijan siirron alaraja
     * @param max Vartijan siirron yläraja
     */
    private void syoVangit(int min, int max) {
        
        for (int i = min; i <= max; i++) { //Tarkistetaan matkan varrella olevat Vangit
            if (this.ruudut[i].getNappula() != null) {
                this.ruudut[i].setNappulaNull();
            }
        }
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
}
