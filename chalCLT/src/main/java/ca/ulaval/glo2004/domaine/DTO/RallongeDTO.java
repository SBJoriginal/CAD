package ca.ulaval.glo2004.domaine.DTO;

import ca.ulaval.glo2004.domaine.Toit.Rallonge;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import java.io.Serializable;
import java.util.UUID;

public class RallongeDTO implements Serializable{
    
    public float hauteurRallonge;
    public float longeurRallonge;
    public float epaisseurRallonge;
    public double angle;
    public float dimensionRallonge;
    public PointDecimal positionRallonge;
    public UUID uuidRallonge;
    public Orientation pOrientation;
    
    public RallongeDTO(Rallonge rallonge){
    hauteurRallonge = rallonge.getHauteurRallonge();
    longeurRallonge = rallonge.getLongueurRallonge();
    epaisseurRallonge = rallonge.getEpaisseurRallonge();
    angle = rallonge.getAngleRallonge();
    dimensionRallonge = rallonge.getDimensionRallonge();
    positionRallonge = rallonge.getPositionRallonge();
    uuidRallonge = rallonge.getUuidRallonge();
    pOrientation = rallonge.getOrientationRallonge();    
    }
    
}
