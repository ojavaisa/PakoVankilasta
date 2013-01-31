package pakovankilasta;

/**
 * Pelilauta-luokka muodostaa pelin laudan. Pelilauta koostuu Riveistä.
 * Pelilaudan leveyden määrittää käyttäjän antama parametri koko. Laudan korkeus
 * on koko+1. //Blaa blaa, jotain laudan muodosta, pakoruuduista ja vartijoista?
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

        this.rivit = new Rivi[this.leveys + 1]; //Laudan korkeus on leveys+1
        luoRivit(rivit);
    }

    /**
     * luoRivit-metodi on konstruktorin käyttämä metodi, joka luo
     * rivit-muuttujassa (Rivi[koko]) olevat Rivit ja asettaa niihin Vartijat
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
     * siirtoSallittu tarkistaa että siirto on joko vaakasuoraan tai
     * pystysuoraan
     *
     * @param vanki
     * @param sarake
     * @param rivi
     * @return
     */
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
     * reittiVapaa tarkistaa että kohteena oleva Ruutu on vapaa ja ettei siirron
     * tiellä ole Vartijoita
     *
     * @param vanki Vanki, jota halutaan liikuttaa
     * @param sarake kohteena olevan Ruudun sarakeen numero
     * @param rivi kohteena olevan Ruudun rivinumero
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

    private boolean sarakeSiirto(Vanki vanki, int rivi) {

        int lahtoRivi = vanki.getSijainti().getRiviNro();
        int sarake = vanki.getSijainti().getSarake();
        int vartijanSarake;

        if (lahtoRivi < rivi) {
            for (int i = lahtoRivi; i <= rivi; i++) {
                vartijanSarake = rivit[i].getVartija().getSijainti().getSarake();
                if (vartijanSarake == sarake) {
                    return false;
                }
            }
            return true;
        } else { //lahtoRivi > rivi (lahtoRivi == rivi)-tapaus käsitellään muualla?
            for (int i = lahtoRivi; i >= rivi; i--) {
                vartijanSarake = rivit[i].getVartija().getSijainti().getSarake();
                if (vartijanSarake == sarake) {
                    return false;
                }
            }
            return true;
        }
    }

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
     * @param rivi Halutun Rivin numero (alkaen vankilan pihasta)
     * @return Haluttu Rivi
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
