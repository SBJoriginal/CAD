package ca.ulaval.glo2004.domaine.Chalet;
import ca.ulaval.glo2004.domaine.DTO.PignonDTO;
import ca.ulaval.glo2004.domaine.Toit.Pignon;
public class PignonMapper {
    
    public PignonDTO mapVersPignonDTO(Pignon pignon){
        PignonDTO pignonDTO = new PignonDTO(pignon);
        return pignonDTO;
    }
    
public void mettreAjourPignonDTO(Pignon pignon, PignonDTO pignonDTO){
    pignonDTO.pLargeurPignon = pignon.getLargeurPignon();
    pignonDTO.pHauteurPignon = pignon.getHauteurPignon();
    pignonDTO.pPentePignon = pignon.getPentePignon();
    pignonDTO.pDimensionPignon = pignon.getDimensionPignon();
    pignonDTO.uuidPignon = pignon.getUuid();
    pignonDTO.pOrientation = pignon.getOrientationPignon();
    pignonDTO.mLabelPignon = pignon.toString();
    pignonDTO.positionPignon = pignon.getPositionPignon();
}
    
}
