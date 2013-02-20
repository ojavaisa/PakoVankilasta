package pakovankilasta;

//import java.awt.Color;
//import java.awt.Graphics;

/**
 * Vanki-nappula toimii pelaajan pelinappulana pelissä.
 * 
 * @author $Olli Väisänen
 */
public class Vanki extends Pelinappula {
    
    /**
     * Vanki kuuluu tietylle Pelaajalle.
     */
    private Pelaaja pelaaja;

    public Vanki() {
        super();
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public void setPelaaja(Pelaaja pelaaja){
        this.pelaaja = pelaaja;
    }
    
    @Override
    public String toString() {
        return "x";
    }
    
//    @Override
//    public void piirra(Graphics g) {
//        
//        int x,y;
//        
//        if(this.getSijainti() == null) {
//            y = 430;
//            x = (this.pelaaja.getPelaajaNro() * 80) + (vanginNro * 20) + 55;
//        } else {
//            x = ((this.getSijainti().getSarake() * 20) + 50) + 5;
//            y = (400 - (this.getSijainti().getRiviNro() * 20)) + 5;
//        }
//        if(this.pelaaja.getPelaajaNro()==0) {
//            g.setColor(Color.BLUE);
//        } else if(this.pelaaja.getPelaajaNro()==1) {
//            g.setColor(Color.RED);
//        } else if(this.pelaaja.getPelaajaNro()==2) {
//            g.setColor(Color.GREEN);
//        } else if(this.pelaaja.getPelaajaNro()==3) {
//            g.setColor(Color.YELLOW);
//        } else {
//            //Tänne ei pitäisi koskaan päätyä...
//            g.setColor(Color.WHITE);
//        }
//        g.fillOval(x, y, 11, 11);
//        g.setColor(Color.BLACK);
//        g.drawOval(x, y, 11, 11);
//        
//    }
}
