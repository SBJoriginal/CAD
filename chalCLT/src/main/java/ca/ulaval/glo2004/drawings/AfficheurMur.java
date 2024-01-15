package ca.ulaval.glo2004.drawings;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.Chalet.Controleur;
import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PositionMur;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import ca.ulaval.glo2004.domaine.utils.TypeAccessoire;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class AfficheurMur extends Afficheur {
    public Orientation mRientationMur;
    
    private boolean rainureActive = false;
    private Controleur controleur;
    public AfficheurMur(Controleur controleur) {
        super();
        this.controleur = controleur;
    }
    
    @Override
    public void draw(Graphics g) {
        drawMurs(g, this.controleur.getChaletDTO());    
        drawGrille(g);
    }
    private void drawMurs(Graphics g, ChaletDTO chalet) {
        PositionMur positionMur = new PositionMur();
        Orientation orientation = this.controleur.getVueCourant();
        switch (orientation) {
            case FACADE:
                drawMurFacade(g,this.controleur.getChaletDTO().MurFacadeDTO,positionMur);
                break;
            case DROITE:
                drawMurDroit(g,this.controleur.getChaletDTO().MurDroiteDTO,positionMur);
                break;
            case GAUCHE:
                drawMurGauche(g,this.controleur.getChaletDTO().MurGaucheDTO,positionMur);
                break;
            case ARRIERE:
                drawMurArriere(g,this.controleur.getChaletDTO().MurArriereDTO,positionMur);
                break;
        }
    }
    
    public void drawMurArriere(Graphics g, MurDTO murArriere, PositionMur positionXY){
        
        int pointenX = positionXY.PositionXMurFacadeArriere;
        int pointenY = positionXY.PositionYMurFacadeArriere;
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
        int longueur = (int)murArriere.Dimension.getLongueur();
        int hauteur = (int)murArriere.Dimension.getHauteur();
        
        if(this.controleur.getSensToitCourant() == SensToit.AVANT_ARRIERE){
            int widthFacade = longueur ;
            int heightFacade = hauteur ; 
            g.setColor(Color.BLACK);
            g.drawRect(pointenX, pointenY, widthFacade, heightFacade);
            
        }else{
                 // Debordement code Gauche
                int facadeGauchex = pointenX - (epaisseur / 2) ;
                int facadeGauchey = pointenY; 
                int widthFacadeGauche = (epaisseur / 2) - 1 - epaceMurRainure; // 
                int heightFacadeGauche = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(facadeGauchex, facadeGauchey, widthFacadeGauche, heightFacadeGauche);

                //Mur Principal 
                int widthFacade = longueur -  epaisseur; // 
                int heightFacade = hauteur ; 
                g.setColor(Color.BLACK);
                g.drawRect(pointenX, pointenY, widthFacade, heightFacade);

                //Mur debordement Droid
                
                
                int facadeDroidx = (longueur + pointenX) - epaisseur +  epaceMurRainure+1;
                int facadeDroidy = pointenY; 
                int widthFacadeDroid = (epaisseur / 2) -  1 - epaceMurRainure; // 
                int heightFacadeDroid = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(facadeDroidx, facadeDroidy, widthFacadeDroid, heightFacadeDroid);
               
            }
        drawAccessoire(g, murArriere);
    }
    public void drawMurDroit(Graphics g, MurDTO murDroit,PositionMur positionXY){
        
        int hauteur = (int)murDroit.Dimension.getHauteur();
        int largeur = (int)murDroit.Dimension.getLargeur();
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
        int pointenMurCoteX = positionXY.PositionXMurGaucheDroit;
        int pointenMurCoteY = positionXY.PositionYMurGaucheDroit;
        if(this.controleur.getSensToitCourant() == SensToit.GAUCHE_DROITE){
            
                int widthDroitGauche = largeur ;
                int heightDroitGauche = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(pointenMurCoteX, pointenMurCoteY, widthDroitGauche, heightDroitGauche);
                
            
            
            }else{
                
                int facadeGauchex = pointenMurCoteX - (epaisseur / 2); // pointenX - (epaisseur / 2)
                int facadeGauchey = pointenMurCoteY; 
                int widthFacadeGauche = epaisseur / 2 - 1 -  epaceMurRainure; // (epaisseur / 2) - 1 - epaceMurRainure;
                int heightFacadeGauche = hauteur ; 
                g.setColor(Color.BLACK);
                g.drawRect(facadeGauchex, facadeGauchey, widthFacadeGauche, heightFacadeGauche);

                //Mur Principal 
                int widthDroitGauche = largeur ;
                int heightDroitGauche = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(pointenMurCoteX, pointenMurCoteY, widthDroitGauche, heightDroitGauche);

                //Mur debordement Droid

                int facadeDroidx = (largeur + pointenMurCoteX)+ epaceMurRainure + 1;
                int facadeDroidy = pointenMurCoteY; 
                int widthFacadeDroid = epaisseur / 2 - 1 -  epaceMurRainure; // (epaisseur / 2) -  1 - epaceMurRainure;
                int heightFacadeDroid = hauteur ; 
                g.setColor(Color.BLACK);
                g.drawRect(facadeDroidx, facadeDroidy, widthFacadeDroid, heightFacadeDroid);
                
            }
        drawAccessoire(g, murDroit);

    }
    public void drawMurGauche(Graphics g, MurDTO murGauche,PositionMur positionXY){
        
        int hauteur = (int)murGauche.Dimension.getHauteur();
        int largeur = (int)murGauche.Dimension.getLargeur();
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
        int pointenMurCoteX = positionXY.PositionXMurGaucheDroit;
        int pointenMurCoteY = positionXY.PositionYMurGaucheDroit;
        if(this.controleur.getSensToitCourant() == SensToit.GAUCHE_DROITE){
            
                int widthDroitGauche = largeur ;
                int heightDroitGauche = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(pointenMurCoteX, pointenMurCoteY, widthDroitGauche, heightDroitGauche);
                
            
            
            }else{
                
                int facadeGauchex = pointenMurCoteX - (epaisseur / 2); // pointenX - (epaisseur / 2)
                int facadeGauchey = pointenMurCoteY; 
                int widthFacadeGauche = epaisseur / 2 - 1 -  epaceMurRainure; // (epaisseur / 2) - 1 - epaceMurRainure;
                int heightFacadeGauche = hauteur ; 
                g.setColor(Color.BLACK);
                g.drawRect(facadeGauchex, facadeGauchey, widthFacadeGauche, heightFacadeGauche);

                //Mur Principal 
                int widthDroitGauche = largeur ;
                int heightDroitGauche = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(pointenMurCoteX, pointenMurCoteY, widthDroitGauche, heightDroitGauche);

                //Mur debordement Droid

                int facadeDroidx = (largeur + pointenMurCoteX)+ epaceMurRainure + 1;
                int facadeDroidy = pointenMurCoteY; 
                int widthFacadeDroid = epaisseur / 2 - 1 -  epaceMurRainure; // (epaisseur / 2) -  1 - epaceMurRainure;
                int heightFacadeDroid = hauteur ; 
                g.setColor(Color.BLACK);
                g.drawRect(facadeDroidx, facadeDroidy, widthFacadeDroid, heightFacadeDroid);
                
            }
        drawAccessoire(g, murGauche);
    }
    public void drawMurFacade(Graphics g, MurDTO murFacade, PositionMur positionXY){
         int pointenX = positionXY.PositionXMurFacadeArriere;
        int pointenY = positionXY.PositionYMurFacadeArriere;
        int epaisseur = (int)this.controleur.getEpaisseurMur();
        int epaceMurRainure = (int)this.controleur.getRainure();
        int longueur = (int)murFacade.Dimension.getLongueur();
        int hauteur = (int)murFacade.Dimension.getHauteur();
        
        if(this.controleur.getSensToitCourant() == SensToit.AVANT_ARRIERE){
            int widthFacade = longueur ;
            int heightFacade = hauteur ; 
            g.setColor(Color.BLACK);
            g.drawRect(pointenX, pointenY, widthFacade, heightFacade);
            
        }else{
                
                // Debordement code Gauche
                int facadeGauchex = pointenX - (epaisseur / 2) ;
                int facadeGauchey = pointenY; 
                int widthFacadeGauche = (epaisseur / 2) - 1 - epaceMurRainure; // 
                int heightFacadeGauche = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(facadeGauchex, facadeGauchey, widthFacadeGauche, heightFacadeGauche);

                //Mur Principal 
                int widthFacade = longueur -  epaisseur; // 
                int heightFacade = hauteur ; 
                g.setColor(Color.BLACK);
                g.drawRect(pointenX, pointenY, widthFacade, heightFacade);

                //Mur debordement Droid
                
                
                int facadeDroidx = (longueur + pointenX) - epaisseur +  epaceMurRainure+1;
                int facadeDroidy = pointenY; 
                int widthFacadeDroid = (epaisseur / 2) -  1 - epaceMurRainure; // 
                int heightFacadeDroid = hauteur ; 
                g.setColor(Color.BLUE);
                g.drawRect(facadeDroidx, facadeDroidy, widthFacadeDroid, heightFacadeDroid);
            }
        drawAccessoire(g, murFacade);
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
    
    public void drawAccessoire(Graphics g, MurDTO mur){
        List<AccessoireDTO> accessoires = mur.AccessoiresList;
        int hauteurAccessoire, largeurAccessoire,positionX,positionY;
        for(AccessoireDTO accessoire : accessoires){
            if(accessoire.TypeAccessoire == TypeAccessoire.FENETRE){
                positionX = (int)accessoire.Position.getX();
                positionY = (int)accessoire.Position.getY();
                hauteurAccessoire = (int)accessoire.Dimension.getHauteur();
                largeurAccessoire = (int)accessoire.Dimension.getLargeur();
                g.setColor(accessoire.CouleurAccessoire);
                g.drawRect(positionX , positionY, largeurAccessoire, hauteurAccessoire);              
            }
            else{
                positionX = (int)accessoire.Position.getX();
                positionY = (int)accessoire.Position.getY();
                hauteurAccessoire = (int)accessoire.Dimension.getHauteur();
                largeurAccessoire = (int)accessoire.Dimension.getLargeur();
                g.setColor(accessoire.CouleurAccessoire);
                g.drawRect(positionX, positionY, largeurAccessoire, hauteurAccessoire);
            }
        }
    }
    
}

