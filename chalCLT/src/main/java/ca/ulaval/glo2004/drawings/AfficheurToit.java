package ca.ulaval.glo2004.drawings;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.Chalet.Controleur;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import static ca.ulaval.glo2004.domaine.utils.Orientation.ARRIERE;
import static ca.ulaval.glo2004.domaine.utils.Orientation.FACADE;
import static ca.ulaval.glo2004.domaine.utils.Orientation.GAUCHE;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.PositionMur;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import static ca.ulaval.glo2004.domaine.utils.SensToit.AVANT_ARRIERE;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
/**
*
*/
public class AfficheurToit extends Afficheur implements Serializable{
 
    public Orientation mRientationMur;
    private Controleur controleur;
    
    public AfficheurToit(Controleur controleur) {
        super();
        this.controleur = controleur;
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
 
    @Override
    public void draw(Graphics g) {
        drawToit(g, this.controleur.getChaletDTO());    
        drawGrille(g); 
    }
        private void drawToit(Graphics g, ChaletDTO chalet) {
        PositionMur positionMur = new PositionMur();
        Orientation orientation = this.controleur.getVueCourant();
       
        switch (orientation) {
            case FACADE:
                //
                drawToitureFacade(g,this.controleur.getChaletDTO().MurFacadeDTO.Position, this.controleur.getAngleToit(), positionMur);
                break;
            case ARRIERE :
                 
                drawToitureArriere(g,this.controleur.getChaletDTO().MurFacadeDTO.Position,this.controleur.getAngleToit(),  positionMur);
                break;
            case DROITE:
                drawToitureDroit(g,this.controleur.getChaletDTO().MurDroiteDTO.Position,this.controleur.getAngleToit(),  positionMur);
                break;
            case GAUCHE:
                         
                drawToitureGauche(g,this.controleur.getChaletDTO().MurGaucheDTO.Position,this.controleur.getAngleToit(), positionMur);
            
                break;

        }
    }
    public void drawToitureDroit(Graphics g,PointDecimal position, double angleToit, PositionMur positionMur){
        
        double angle = this.controleur.getAngleToit();
        double hauteurViaAngle = this.controleur.getChaletDTO().DimensionParDefaut.getLargeur() * Math.tan(Math.toRadians(angle));
        int pignonHauteur = (int)hauteurViaAngle; 
        int pointenMurCoteX = positionMur.PositionXMurGaucheDroit;
        int pointenMurCoteY = positionMur.PositionYMurGaucheDroit;
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
        int largeur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getLargeur();
   
        if(this.controleur.getSensToitCourant() == SensToit.GAUCHE_DROITE){
            
            int toitDessusY = pointenMurCoteY - epaisseur/2; 
            int toitDessusWidth = largeur;
            int toitDessusHeight = epaisseur/2; 

            g.setColor(Color.BLACK);
            g.drawRect(pointenMurCoteX, toitDessusY, toitDessusWidth, toitDessusHeight);
            g.setColor(Color.BLACK);
            g.drawRect(pointenMurCoteX, pointenMurCoteY - pignonHauteur - epaceMurRainure, largeur, pignonHauteur);
            
        }
            
        else{
            
            int toitGaucheX = pointenMurCoteX;
            int toitGaucheY = pointenMurCoteY - pignonHauteur - 1; // Ajustez selon vos besoins
            g.setColor(Color.BLACK);
            
            int[] xPoints = {toitGaucheX, toitGaucheX + largeur, toitGaucheX};
            int[] yPoints = {toitGaucheY + 4, toitGaucheY + pignonHauteur , toitGaucheY + pignonHauteur};
            g.drawPolygon(xPoints, yPoints, 3);
            
            //Pignon
            
            int pointX = pointenMurCoteX;
            int pointY = pointenMurCoteY - pignonHauteur;
            int heightPignon = pignonHauteur;

            // Create a Polygon representing the rectangle
            int[] Points = {pointX - epaisseur/2, pointX -epaceMurRainure - 1, pointX -epaceMurRainure - 1, pointX - epaisseur/2};
            int[] Pointy = {pointY, pointY+2, pointY + heightPignon - epaceMurRainure, pointY + heightPignon - epaceMurRainure};  // Adjusted for the top right position
            g.setColor(Color.BLACK);
            g.drawPolygon(Points, Pointy, 4);
            
            
            //La toiture
            
            g.setColor(Color.BLACK);
            
            g.drawLine(pointenMurCoteX - 5, pointenMurCoteY - pignonHauteur - 6, pointenMurCoteX + largeur + 4, pointenMurCoteY - 9);
            g.setColor(Color.BLACK);
            g.drawLine(pointenMurCoteX - (epaisseur/2) - epaceMurRainure + 1, pointenMurCoteY - pignonHauteur, pointenMurCoteX + largeur + 5, pointenMurCoteY - 1);
            g.setColor(Color.BLACK);
            g.drawLine(pointenMurCoteX - 5, pointenMurCoteY - pignonHauteur - 6, pointenMurCoteX - 5, pointenMurCoteY - pignonHauteur);
            
            //rigth
            g.drawLine(pointenMurCoteX + largeur + 5, pointenMurCoteY - 9, pointenMurCoteX + largeur + 5, pointenMurCoteY - 1);

        }
    }
        
public void drawDessusFacadeArriere(Graphics g, PointDecimal position, double angleToit, PositionMur positionMur) {
    
    int epaisseur = (int)this.controleur.getEpaisseurMur();
    int epaceMurRainure = (int)this.controleur.getRainure();
    int pointenX = positionMur.PositionXMurFacadeArriere;
    int pointenY = positionMur.PositionYMurFacadeArriere;
    int longueur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getLongueur();
    double angle = this.controleur.getAngleToit();
    double hauteurViaAngle = this.controleur.getChaletDTO().DimensionParDefaut.getLargeur() * Math.tan(Math.toRadians(angle));
    int pignonHauteur = (int)hauteurViaAngle; 
    if (this.controleur.getSensToitCourant() == AVANT_ARRIERE){
    
        if(this.controleur.getVueCourant() == ARRIERE){
            
        }
        else if ((this.controleur.getVueCourant() == FACADE)){
            int toitDessusY = pointenY - epaisseur/2; 
            int toitDessusWidth = longueur;
            int toitDessusHeight = epaisseur/2; 

            g.setColor(Color.BLACK);
            g.drawRect(pointenX, toitDessusY, toitDessusWidth, toitDessusHeight);
            g.setColor(Color.BLACK);
            g.drawRect(pointenX, pointenY - pignonHauteur - epaceMurRainure, longueur, pignonHauteur);
            
        }

    }
    else {
    
       if(this.controleur.getVueCourant() == ARRIERE){
        
         
       }
       else if(this.controleur.getVueCourant() == FACADE){
            int toitGaucheX = pointenX;
            int toitGaucheY = pointenY - pignonHauteur - 1; // Ajustez selon vos besoins
            g.setColor(Color.BLACK);
            
            int[] xPoints = {toitGaucheX, toitGaucheX + longueur - epaisseur, toitGaucheX};
            int[] yPoints = {toitGaucheY + 4, toitGaucheY + pignonHauteur , toitGaucheY + pignonHauteur};
            g.drawPolygon(xPoints, yPoints, 3);
            
            //Pignon
            
            int pointX = pointenX;
            int pointY = pointenY - pignonHauteur;
            int heightPignon = pignonHauteur;

            // Create a Polygon representing the rectangle
            int[] Points = {pointX - epaisseur/2, pointX -epaceMurRainure - 1, pointX -epaceMurRainure - 1, pointX - epaisseur/2};
            int[] Pointy = {pointY, pointY+2, pointY + heightPignon - epaceMurRainure, pointY + heightPignon - epaceMurRainure};  // Adjusted for the top right position
            g.setColor(Color.BLACK);
            g.drawPolygon(Points, Pointy, 4);
            
            
            //La toiture
            
            g.setColor(Color.BLACK);
            
            g.drawLine(pointenX - epaisseur/2, pointenY - pignonHauteur - epaisseur/2 - epaceMurRainure, pointenX + longueur - 5, pointenY);
            g.setColor(Color.BLACK);
            g.drawLine(pointenX - (epaisseur/2) - epaceMurRainure + 1, pointenY - pignonHauteur, pointenX + longueur - 5 - epaisseur/2, pointenY - 1);
            g.setColor(Color.BLACK);
            g.drawLine(pointenX - epaisseur/2, pointenY - pignonHauteur, pointenX - epaisseur/2, pointenY - pignonHauteur - epaisseur/2);
            
            //rigth
            //g.drawLine(pointenX + longueur + 5, pointenY - 9, pointenX + longueur + 5, pointenY - 1); 
       }

    }
    
} 

    public void drawToitureFacade(Graphics g,PointDecimal position, double angleToit, PositionMur positionMur) {
  
        if(this.controleur.getSensToitCourant() == SensToit.GAUCHE_DROITE){
             drawDessusFacadeArriere(g, position, angleToit, positionMur);
        }
        else{
           drawDessusFacadeArriere(g, position, angleToit, positionMur);

        }
    }
    public void drawToitureArriere(Graphics g,PointDecimal position, double angleToit, PositionMur positionMur) {
  
      
        int pointenX = positionMur.PositionXMurFacadeArriere;
        int pointenY = positionMur.PositionYMurFacadeArriere;
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
        int longueur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getLongueur();
        double angle = this.controleur.getAngleToit();
        double hauteurViaAngle = this.controleur.getChaletDTO().DimensionParDefaut.getLargeur() * Math.tan(Math.toRadians(angle));
        int pignonHauteur = (int)hauteurViaAngle; 
        
        if(this.controleur.getSensToitCourant() == SensToit.GAUCHE_DROITE){
            
            int toitGaucheX = pointenX;
            int toitGaucheY = pointenY - pignonHauteur - 1; // Ajustez selon vos besoins
            g.setColor(Color.BLACK);
            
            int[] xPoints = {toitGaucheX, toitGaucheX + longueur - epaisseur, toitGaucheX};
            int[] yPoints = {toitGaucheY + 4, toitGaucheY + pignonHauteur , toitGaucheY + pignonHauteur};
            g.drawPolygon(xPoints, yPoints, 3);
            
            //Pignon
            
            int pointX = pointenX;
            int pointY = pointenY - pignonHauteur;
            int heightPignon = pignonHauteur;

            // Create a Polygon representing the rectangle
            int[] Points = {pointX - epaisseur/2, pointX -epaceMurRainure - 1, pointX -epaceMurRainure - 1, pointX - epaisseur/2};
            int[] Pointy = {pointY, pointY+2, pointY + heightPignon - epaceMurRainure, pointY + heightPignon - epaceMurRainure};  // Adjusted for the top right position
            g.setColor(Color.BLACK);
            g.drawPolygon(Points, Pointy, 4);
            
            
            //La toiture
            
            g.setColor(Color.BLACK);
            
            g.drawLine(pointenX - epaisseur/2, pointenY - pignonHauteur - epaisseur/2 - epaceMurRainure, pointenX + longueur - 5, pointenY);
            g.setColor(Color.BLACK);
            g.drawLine(pointenX - (epaisseur/2) - epaceMurRainure + 1, pointenY - pignonHauteur, pointenX + longueur - 5 - epaisseur/2, pointenY - 1);
            g.setColor(Color.BLACK);
            g.drawLine(pointenX - epaisseur/2, pointenY - pignonHauteur, pointenX - epaisseur/2, pointenY - pignonHauteur - epaisseur/2);
            
            //rigth
            //g.drawLine(pointenX + longueur + 5, pointenY - 9, pointenX + longueur + 5, pointenY - 1);
            
        }
        else{
            
            g.setColor(Color.BLACK);
            g.drawRect(pointenX, pointenY - pignonHauteur + epaisseur/2, longueur, (pignonHauteur - epaisseur/2)-1);
            g.setColor(Color.BLACK);
            g.drawRect(pointenX, pointenY - pignonHauteur - (epaceMurRainure+1), longueur, epaisseur/2);
        }
 

    }
    
    public void drawToitureGauche (Graphics g,PointDecimal position, double angleToit, PositionMur positionMur) {
        
        double angle = this.controleur.getAngleToit();
        double hauteurViaAngle = this.controleur.getChaletDTO().DimensionParDefaut.getLargeur() * Math.tan(Math.toRadians(angle));
        int pignonHauteur = (int)hauteurViaAngle;  
        int pointenMurCoteX = positionMur.PositionXMurGaucheDroit;
        int pointenMurCoteY = positionMur.PositionYMurGaucheDroit;
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
       
        int largeur = (int)this.controleur.getChaletDTO().DimensionParDefaut.getLargeur();
   
        if(this.controleur.getSensToitCourant() == SensToit.GAUCHE_DROITE){
            
            g.setColor(Color.BLACK);
            g.drawRect(pointenMurCoteX, pointenMurCoteY - pignonHauteur + epaisseur/2, largeur, (pignonHauteur - epaisseur/2)-1);
            g.setColor(Color.BLACK);
            g.drawRect(pointenMurCoteX, pointenMurCoteY - pignonHauteur - (epaceMurRainure+1), largeur, epaisseur/2);
            
        }
            
        else{
            int toitGaucheX = pointenMurCoteX;
            int toitGaucheY = pointenMurCoteY - pignonHauteur - 1; // Ajustez selon vos besoins
            g.setColor(Color.BLACK);
            
            int[] xPoints = {toitGaucheX, toitGaucheX + largeur, toitGaucheX};
            int[] yPoints = {toitGaucheY + 4, toitGaucheY + pignonHauteur , toitGaucheY + pignonHauteur};
            g.drawPolygon(xPoints, yPoints, 3);
            
            //Pignon
            
            int pointX = pointenMurCoteX;
            int pointY = pointenMurCoteY - pignonHauteur;
            int heightPignon = pignonHauteur;

            // Create a Polygon representing the rectangle
            int[] Points = {pointX - epaisseur/2, pointX -epaceMurRainure - 1, pointX -epaceMurRainure - 1, pointX - epaisseur/2};
            int[] Pointy = {pointY, pointY+2, pointY + heightPignon - epaceMurRainure, pointY + heightPignon - epaceMurRainure};  // Adjusted for the top right position
            g.setColor(Color.BLACK);
            g.drawPolygon(Points, Pointy, 4);
            
            
            //La toiture
            
            g.setColor(Color.BLACK);
            
            g.drawLine(pointenMurCoteX - 5, pointenMurCoteY - pignonHauteur - 6, pointenMurCoteX + largeur + 4, pointenMurCoteY - 9);
            g.setColor(Color.BLACK);
            g.drawLine(pointenMurCoteX - (epaisseur/2) - epaceMurRainure + 1, pointenMurCoteY - pignonHauteur, pointenMurCoteX + largeur + 5, pointenMurCoteY - 1);
            g.setColor(Color.BLACK);
            g.drawLine(pointenMurCoteX - 5, pointenMurCoteY - pignonHauteur - 6, pointenMurCoteX - 5, pointenMurCoteY - pignonHauteur);
            
            //rigth
            g.drawLine(pointenMurCoteX + largeur + 5, pointenMurCoteY - 9, pointenMurCoteX + largeur + 5, pointenMurCoteY - 1);
        }
    }	
}


