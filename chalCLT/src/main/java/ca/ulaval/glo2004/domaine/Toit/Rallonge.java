package ca.ulaval.glo2004.domaine.Toit;

import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import java.io.Serializable;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import java.util.UUID;

public class Rallonge implements Serializable {
    

    ChaletDTO pChaletDTO;
    private final float hauteurRallonge;
    private float longeurRallonge;
    private final float epaisseurRallonge;
    private double angle;
    private float dimensionRallonge;
    private PointDecimal positionRallonge;
    private UUID uuidRallonge;
    private Orientation pOrientation;
    
    public Rallonge(Orientation mOrientation) {

        this.pOrientation = mOrientation;
        this.hauteurRallonge = (float) ((float)(pChaletDTO.MurGaucheDTO.Dimension.getLargeur()) * Math.tan(angle));
        this.epaisseurRallonge = pChaletDTO.EpaisseurMur;
        this.dimensionRallonge = (float) pChaletDTO.MurArriereDTO.Dimension.getLongueur();
        this.positionRallonge = pChaletDTO.MurArriereDTO.Position;
    }

    
    public float getHauteurRallonge() {
        return hauteurRallonge;
    }
    
    public float getLongueurRallonge() {
        return longeurRallonge;
    }
    
    public float getEpaisseurRallonge(){
        return epaisseurRallonge;
    }
    
    public double getAngleRallonge(){
        return angle;
    }
    
    public float getDimensionRallonge(){
        return dimensionRallonge;
    }
    
    public PointDecimal getPositionRallonge(){
        return positionRallonge;
    }
    
    public Orientation getOrientationRallonge(){
        return pOrientation;
    }
    
   public UUID getUuidRallonge() {
        return uuidRallonge;
    }

}
