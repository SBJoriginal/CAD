package ca.ulaval.glo2004.domaine.Toit;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.PositionToit;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import java.io.Serializable;

public class Toiture implements Serializable{
    
    private double pAngleToit;
    private final Orientation pOrientation;
    private final Dimension pDimension;
    private final SensToit pSensToit;
    private String mLabelToit;
    private PointDecimal mPosition;
    
    public Toiture(Orientation mOrientation, Dimension mDimension, double angleToit, SensToit sensToit) {
        this.pOrientation = mOrientation;
        this.pDimension = mDimension;
        this.pAngleToit = angleToit;
        this.pSensToit = sensToit;
    }
    
    public Dimension getToitureDimension(){
        return pDimension;
    }
    
    public double getAngleToit(){
        return pAngleToit;
    }
    
    public void setAngleToit (double angleToit){
        this.pAngleToit = angleToit;
    }
    
    public Orientation getOrientationToiture(){
        return pOrientation;
    }
    @Override
    public String toString(){
        switch (pOrientation){
            case ARRIERE:
                mLabelToit = "ARRIERE";
                break;
            case FACADE:
                mLabelToit = "FACADE";
                break;
            case GAUCHE:
                mLabelToit = "GAUCHE";
                break;               
            case DROITE : 
                mLabelToit = "DROITE";
                
            default:
                throw new AssertionError();
        }
        return mLabelToit;
    }
    
        public SensToit getSensToiture(){
        return pSensToit;
    }
    public String toStringSens(){
        switch (pSensToit){
            case AVANT_ARRIERE:
                mLabelToit = "AVANT_ARRIERE";
                break;
            case GAUCHE_DROITE:
                mLabelToit = "GAUCHE_DROITE";
                break; 
            default:
                throw new AssertionError();
        }
        return mLabelToit;
    }
    
     
    public String getLabelToit(){
        return mLabelToit;
    }
    
    public PointDecimal getPositionToit(){
        return mPosition;
    }
    
     public void setPositionToit(Orientation pOrientationMur){
        PositionToit positionMur = new PositionToit();
        switch (pOrientation) {
            case ARRIERE:
                mPosition =  new PointDecimal(positionMur.PositionXToitFacadeArriere, positionMur.PositionYToitFacadeArriere );
                break;
            case FACADE:
                 mPosition =  new PointDecimal(positionMur.PositionXToitFacadeArriere, positionMur.PositionYToitFacadeArriere );
                break;
            case GAUCHE:
                 mPosition =  new PointDecimal(positionMur.PositionXToitGaucheDroit, positionMur.PositionYToitGaucheDroit );
                break;
            case DROITE:
                mPosition =  new PointDecimal(positionMur.PositionXToitGaucheDroit, positionMur.PositionYToitGaucheDroit );
                break;            
        }
    }
     
     public void setPositionToit(PointDecimal nouvellePosition) {
        this.mPosition = nouvellePosition;
    }

    public void deplacerToit(float deltaX, float deltaY) {
        // Faut que mPosition est initialis�e avant d'utiliser cette m�thode.
        if (mPosition != null) {
            mPosition.setX(mPosition.getX() + deltaX);
            mPosition.setY(mPosition.getY() + deltaY);
        } else {
            // Gestion d'une �ventuelle initialisation manquante de mPosition.
            System.err.println("Erreur : Position non initialisé pour la toiture.");
        }
    }


    //Méthodes pour la gestion de l'angle :
    public void ajusterAngleToit(double ajustement) {
        this.pAngleToit += ajustement;
    }

}