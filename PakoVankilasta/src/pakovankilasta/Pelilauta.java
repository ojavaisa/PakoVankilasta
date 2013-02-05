package pakovankilasta;

/**
 * Pelilauta-luokka muodostaa pelin laudan. Pelilauta koostuu Riveistä.
 * Pelilaudan leveyden määrittää käyttäjän antama parametri koko. 
 * //jotain pakoruutujen ja vartijoiden sijainneista?
 *
 * @see pakovankilasta.Rivi
 *
 * @author $Olli Väisänen
 */
public class Pelilauta {

    private int leveys;
    private Rivi[] rivit; //ArrayList tai vast?

    /**
     * Konstruktori luo Pelilaudan eli taulukon Rivejä. Pelilaudan leveys on 
     * pelaajan antama pariton luku väliltä 5..15. Pelilaudan korkeus on yhtä suurempi,
     * sillä laudan alin rivi (kauimpana venettä) on Vartijaton, "turvallinen" Rivi.
     * 
     * @param koko pelaajan antama laudan leveys
     */
    public Pelilauta(int koko) {

        this.leveys = koko;

        this.rivit = new Rivi[this.leveys + 1]; 
        luoRivit(rivit);
    }

    /**
     * luoRivit-metodi on konstruktorin käyttämä metodi, joka luo
     * rivit-muuttujassa olevat Rivit ja asettaa niihin Vartijat
     * (Rivi-luokan metodilla asetaVartija).
     *
     * @see pakovankilasta.Rivi
     * @see pakovankilasta.Rivi#asetaVartija(int)
     *
     * @param rivit Pelilaudan muodostava Rivien taulukko
     */
    private void luoRivit(Rivi[] rivit) {

        for (int i = 0; i < rivit.length; i++) {
            rivit[i] = new Rivi(this.leveys, i);
        }
        for (int i = 0; i < rivit.length; i++) {
            rivit[i].asetaVartija();
        }
    }

    /**
     * siirtoSallittu tarkistaa, että pelaajan yrittämä siirto on joko vaakasuoraan tai
     * pystysuoraan
     *
     * @param vanki Vanki-nappula, jota halutaan siirtää
     * @param kohde kohteena oleva Ruutu, johon ollaan siirtymässä
     * @return totuusarvo, siitä onko siirto sallittu
     */
    //Siirtyminen samaan ruutuun??? Tässä tarkistus? (lisää myös testiin jos näin)
    protected boolean siirtoSallittu(Vanki vanki, Ruutu kohde) {
        
        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();

        if (rivi == 0 || vanki.getSijainti() == null) {
            return true;
        } else if (vanki.getSijainti().getRiviNro() == rivi) {
            return true;
        } else if (vanki.getSijainti().getSarake() == sarake) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * reittiVapaa-metodi tarkistaa että kohteena oleva Ruutu on vapaa ja ettei siirron
     * tiellä ole Vartijoita. Edessä olevat Vanki-nappulat eivät estä siirtoa (paitsi
     * kohteena olevassa Ruudussa). Tarkistus tapahtuu siirron tyypistä riippuvalla
     * metodilla.
     *
     * @param vanki Vanki, jota halutaan liikuttaa
     * @param kohde kohteena oleva Ruutu, johon ollaan siirtymässä
     * @return totuusarvo true, jos edessä ei ole vartijoita
     */
    protected boolean reittiVapaa(Vanki vanki, Ruutu kohde) {

        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        
        if (this.rivit[rivi].getRuutu(sarake).getNappula() != null) {
            return false;
        } else {
            if (vanki.getSijainti() == null) {
                return aloitusSiirto(kohde);
            } else if (vanki.getSijainti().getSarake() == sarake) {
                return sarakeSiirto(vanki, rivi);
            } else if (vanki.getSijainti().getRiviNro() == rivi) {
                return riviSiirto(vanki, sarake);
            } else {
                System.out.println("You're not supposed to get here!");
                return false;
            }
        }

    }

    /**
     * aloitusSiirto-metodi on reittiVapaa-metodin käyttämä tarkistus siirrolle,
     * joka lähtee sellistä (liikutettava Vanki ei ole laudalla).
     * 
     * @param kohde Siirron kohteena oleva Ruutu
     * @return totuusarvo siitä, onko reitti kohderuutuun vapaa
     */
    private boolean aloitusSiirto(Ruutu kohde) {

        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        
        for (int i = 1; i <= rivi; i++) { //Rivillä 0 ei ole Vartijaa!
            if (rivit[i].getVartija().getSijainti().getSarake() == sarake) {
                return false;
            }
        }
        return true;

    }

    /**
     * sarakeSiirto-metodi on reittiVapaa-metodin käyttämä tarkistus siirrolle,
     * joka tehdään pitkin saraketta (pystysuora siirto).
     * 
     * @param vanki Vanki-nappula jota ollaan liikuttamassa
     * @param rivi kohderivi, jolle ollaan menossa
     * @return totuusarvo siitä, onko reitti kohderuutuun vapaa
     */
    private boolean sarakeSiirto(Vanki vanki, int rivi) {

        int lahtoRivi = vanki.getSijainti().getRiviNro();
        int sarake = vanki.getSijainti().getSarake();
        int vartijanSarake;

        if (lahtoRivi < rivi) {
            if(lahtoRivi == 0) {
                lahtoRivi++; //Riviä 0 ei tarvitse tarkistaa
            }
            for (int i = lahtoRivi; i <= rivi; i++) {
                vartijanSarake = rivit[i].getVartija().getSijainti().getSarake();
                if (vartijanSarake == sarake) {
                    return false;
                }
            }
            return true;
        } else { //lahtoRivi > rivi (lahtoRivi == rivi)-tapaus käsitellään muualla?
            if(rivi == 0) {
                rivi++; //Riviä 0 ei tarvitse tarkistaa
            }
            for (int i = lahtoRivi; i >= rivi; i--) {
                vartijanSarake = rivit[i].getVartija().getSijainti().getSarake();
                if (vartijanSarake == sarake) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * riviSiirto-metodi on reittiVapaa-metodin käyttämä tarkistus siirrolle,
     * joka tehdään pitkin riviä (vaakasuora siirto).
     * 
     * @param vanki Vanki-nappula jota ollaan liikuttamassa
     * @param sarake kohdesarake, jolle ollaan menossa
     * @return totuusarvo siitä, onko reitti kohderuutuun vapaa
     */
    private boolean riviSiirto(Vanki vanki, int sarake) {

        int lahtoSarake = vanki.getSijainti().getSarake();
        int rivi = vanki.getSijainti().getRiviNro();
        int vartijanSarake = rivit[rivi].getVartija().getSijainti().getSarake();

        if (lahtoSarake < sarake) {
            for (int i = lahtoSarake; i <= sarake; i++) {
                if (vartijanSarake == i) {
                    return false;
                }
            }
            return true;
        } else { //lahtoSarake > sarake (lahtoSarake == sarake)-tapaus käsitellään muualla?
            for (int i = lahtoSarake; i >= sarake; i--) {
                if (vartijanSarake == i) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * getRiviNro antaa parametrina annetun Rivin
     *
     * @param rivi halutun Rivin numero (alkaen vankilan pihasta)
     * @return pyydetty Rivi
     */
    public Rivi getRivi(int rivi) {
        return rivit[rivi];
    }

    /**
     * getKoko-metodi palauttaa pelilaudan korkeuden eli Rivien lukumäärä. Huom!
     * korkeus = leveys + 1
     *
     * @return Pelilaudan korkeus eli Rivien lukumäärä.
     */
    public int getKoko() {
        return this.rivit.length;
    }

    @Override
    public String toString() {

        String lauta = "    ";

        for (int i = 0; i < this.leveys; i++) {
            int kirjain = 'A';
            kirjain += i;
            lauta = lauta + (char) kirjain + " ";
        }
        lauta = lauta + "\n";

        for (int i = rivit.length - 1; i >= 0; i--) {
            lauta = lauta + rivit[i] + "\n";
        }
        return lauta;
    }
}
