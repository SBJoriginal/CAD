package ca.ulaval.glo2004.drawings;
import ca.ulaval.glo2004.domaine.Chalet.Controleur;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PositionMur;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AfficheurDessus extends Afficheur {
    public Orientation mRientationMur;
    private Controleur controleur;
    
    public AfficheurDessus(Controleur controleur) {
        super();
        this.controleur = controleur;
    }
     @Override
    public void draw(Graphics g) {
        drawDessus(g);
        drawGrille(g);
    }
    private void drawDessus(Graphics g) {
        if(this.controleur.getSensToitCourant() == SensToit.AVANT_ARRIERE){
            drawDessusSensGaucheDroite(g);
        }else{
            drawDessusSensToitArriereFacade(g);
        }
    }
    public void drawDessusSensToitArriereFacade(Graphics g){
        PositionMur positionMur = new PositionMur();
        int longueur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getLongueur(); 
        int hauteur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getHauteur();
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();

        int pointenX = positionMur.PositionXVueDESSUS;
        int pointenY = positionMur.PositionYVueDESSUS;
        
        //Mur Arriere Exterieur
        int x = pointenX + epaisseur/2 + epaceMurRainure; 
        int y = pointenY; 
        int width = longueur - epaisseur - epaceMurRainure*2; 
        int height = epaisseur/2; 

        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
         
        //Mur Arriere Interieur
        int xx = pointenX + epaisseur+epaceMurRainure; 
        int yy = pointenY + (epaisseur/2); 
        int widthT = longueur -  (epaisseur*2) - epaceMurRainure*2; 
        int heightT = epaisseur/2; 
        g.setColor(Color.BLACK);
        g.fillRect(xx, yy, widthT, heightT);
    
        /// Draw mur droit exterieur 
        int droitx = pointenX; 
        int droity = pointenY ; 
        int widthDroid = epaisseur/2; 
        int heightDroit = hauteur ; 
        g.setColor(Color.BLUE);
        g.fillRect(droitx, droity, widthDroid, heightDroit);
      
        /// Draw mur droit interieur
        int droitxx = pointenX + (epaisseur/2); 
        int droityy = pointenY + (epaisseur/2) + epaceMurRainure; 
        int widthhDroid = epaisseur/2; 
        int heighttDroit = hauteur - epaisseur - epaceMurRainure*2; 
        g.setColor(Color.BLUE);
        g.fillRect(droitxx, droityy, widthhDroid, heighttDroit);
        
        
        /// Draw mur Facade Exterieur
        int facadex = pointenX + epaisseur/2 + epaceMurRainure;
        int facadey = hauteur + pointenY - epaisseur/2; 
        int widthFacade = longueur - epaisseur - epaceMurRainure*2; 
        int heightFacade = epaisseur / 2 ; 
        g.setColor(Color.BLACK);
        g.fillRect(facadex, facadey, widthFacade, heightFacade);
        
        
        /// Draw mur Facade Interieur
        int facadexx = pointenX + epaisseur + epaceMurRainure;
        int facadeyy = hauteur + pointenY - epaisseur ; 
        int widthhFacade = longueur - (epaisseur*2) - epaceMurRainure*2; 
        int heighttFacade = epaisseur / 2 ; 
        g.setColor(Color.BLACK);
        g.fillRect(facadexx, facadeyy, widthhFacade, heighttFacade);
        
        /// Draw mur Gauche Exterieur
        int gauchex = longueur + pointenX - (epaisseur / 2) ;
        int gauchey = pointenY;
        int widthGauche = epaisseur / 2; 
        int heightGauche = hauteur ; 
        g.setColor(Color.BLUE);
        g.fillRect(gauchex, gauchey, widthGauche, heightGauche);
        
        /// Draw mur Gauche Interieur
        int gauchexx = longueur + pointenX - (epaisseur);
        int gaucheyy = pointenY + epaisseur /2 + epaceMurRainure; 
        int widthhGauche = epaisseur / 2; 
        int heighttGauche = hauteur - epaisseur - epaceMurRainure*2; 
        g.setColor(Color.BLUE);
        g.fillRect(gauchexx, gaucheyy, widthhGauche, heighttGauche);
    
    }
    public void drawDessusSensGaucheDroite(Graphics g){
        PositionMur positionMur = new PositionMur();
        int longueur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getLongueur(); //400;
        int hauteur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getHauteur();//200;
        int epaisseur = (int)this.controleur.getEpaisseurMur();//50;
        int epaceMurRainure = (int)this.controleur.getRainure();
        int pointenX = positionMur.PositionXVueDESSUS;
        int pointenY = positionMur.PositionYVueDESSUS;
        
        //Mur Arriere Exterieur
        int x = pointenX; 
        int y = pointenY; 
        int width = longueur; 
        int height = epaisseur/2; 

        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
         
        //Mur Arriere Interieur
        int xx = pointenX + (epaisseur/2) + epaceMurRainure; 
        int yy = pointenY + (epaisseur/2); 
        int widthT = longueur -  (epaisseur) - epaceMurRainure*2; 
        int heightT = epaisseur/2; 
        g.setColor(Color.BLACK);
        g.fillRect(xx, yy, widthT, heightT);
        
        /// Draw mur droit exterieur 
        int droitx = pointenX; 
        int droity = pointenY + (epaisseur/2) + epaceMurRainure; 
        int widthDroid = epaisseur/2; 
        int heightDroit = hauteur ; 
        g.setColor(Color.BLUE);
        g.fillRect(droitx, droity, widthDroid, heightDroit);
        
        
        /// Draw mur droit interieur
        int droitxx = pointenX + (epaisseur/2); 
        int droityy = pointenY + epaisseur + epaceMurRainure; 
        int widthhDroid = epaisseur/2; 
        int heighttDroit = hauteur - epaisseur ; 

        // Set the color (e.g., black)
        g.setColor(Color.BLUE);

        // Draw the rectangle using the drawRect method
        g.fillRect(droitxx, droityy, widthhDroid, heighttDroit);
        
        int facadex = pointenX;

        int facadey = hauteur + pointenY + (epaisseur / 2) + epaceMurRainure*2; 

        int widthFacade = longueur ; // 

        int heightFacade = epaisseur / 2 ; 

        g.setColor(Color.BLACK);

        g.fillRect(facadex, facadey, widthFacade, heightFacade);


        /// Draw mur Facade Interieur

        int facadexx = pointenX + epaisseur /2 + epaceMurRainure;

        int facadeyy = hauteur + pointenY + epaceMurRainure*2; 

        int widthhFacade = longueur - epaisseur - epaceMurRainure*2; 

        int heighttFacade = epaisseur / 2 ; 

        g.setColor(Color.BLACK);

        g.fillRect(facadexx, facadeyy, widthhFacade, heighttFacade);

        /// Draw mur Gauche Exterieur

        int gauchex = longueur + pointenX - (epaisseur / 2) ;

        int gauchey = pointenY + epaisseur /2 + epaceMurRainure; 

        int widthGauche = epaisseur / 2; 

        int heightGauche = hauteur ; 

        g.setColor(Color.BLUE);

        g.fillRect(gauchex, gauchey, widthGauche, heightGauche);

        /// Draw mur Gauche Interieur

        int gauchexx = longueur + pointenX - (epaisseur);

        int gaucheyy = pointenY + epaisseur + epaceMurRainure; 

        int widthhGauche = epaisseur / 2; 

        int heighttGauche = hauteur - epaisseur; 

        g.setColor(Color.BLUE);

        g.fillRect(gauchexx, gaucheyy, widthhGauche, heighttGauche);
    }
    @Override
    public void drawGrille(Graphics g) {
         ((Graphics2D) g).setColor(Color.lightGray);

       if (controleur.getAfficherGrille()) {
           int dimensionGrille = controleur.getDimensionGrille();
           //Changer les dimensions selon la valeur
           int intervalX = -1000 * dimensionGrille;
           int intervalY = -1000 * dimensionGrille;

           ((Graphics2D) g).setStroke(new BasicStroke(0.1f));
           while (intervalX < 1366 * 100) {
               ((Graphics2D) g).drawLine(intervalX, -1000000, intervalX, 1000000);
               intervalX += dimensionGrille;
           }
           while (intervalY < 768 * 100) {
               ((Graphics2D) g).drawLine(-1000000, intervalY, 1000000, intervalY);
               intervalY += dimensionGrille;
           }
       }
    }
}
