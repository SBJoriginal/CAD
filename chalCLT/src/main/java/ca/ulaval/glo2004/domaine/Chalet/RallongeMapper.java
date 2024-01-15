package ca.ulaval.glo2004.domaine.Chalet;
import ca.ulaval.glo2004.domaine.DTO.RallongeDTO;
import ca.ulaval.glo2004.domaine.Toit.Rallonge;
public class RallongeMapper {
    
    public RallongeDTO mapVersRallongeDTO(Rallonge rallonge){
        RallongeDTO rallongeDTO = new RallongeDTO(rallonge);
        return rallongeDTO;
    }
    
    public void mettreAjourRallongeDTO(Rallonge rallonge, RallongeDTO rallongeDTO){
        rallongeDTO.hauteurRallonge = rallonge.getHauteurRallonge();
        rallongeDTO.longeurRallonge = rallonge.getLongueurRallonge();
        rallongeDTO.epaisseurRallonge = rallonge.getEpaisseurRallonge();
        rallongeDTO.angle = rallonge.getAngleRallonge();
        rallongeDTO.dimensionRallonge = rallonge.getDimensionRallonge();
        rallongeDTO.positionRallonge = rallonge.getPositionRallonge();
        rallongeDTO.uuidRallonge = rallonge.getUuidRallonge();
        rallongeDTO.pOrientation = rallonge.getOrientationRallonge();
    
    }
}
