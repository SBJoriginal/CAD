package ca.ulaval.glo2004.domaine.DTO;
import ca.ulaval.glo2004.domaine.Toit.Pignon;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import java.io.Serializable;
import java.util.UUID;
public class PignonDTO implements Serializable{
   
    public float pLargeurPignon;
    public float pHauteurPignon;
    public float pPentePignon;
    public float pDimensionPignon;
    public UUID uuidPignon;
    public Orientation pOrientation;
    public String mLabelPignon;
    public PointDecimal positionPignon;
    
    public PignonDTO(Pignon pignon){
    
    pLargeurPignon = pignon.getLargeurPignon();
    pHauteurPignon = pignon.getHauteurPignon();
    pPentePignon = pignon.getPentePignon();
    pDimensionPignon = pignon.getDimensionPignon();
    uuidPignon = pignon.getUuid();
    pOrientation = pignon.getOrientationPignon();
    mLabelPignon = pignon.toString();
    positionPignon = pignon.getPositionPignon();
    }
    
}
