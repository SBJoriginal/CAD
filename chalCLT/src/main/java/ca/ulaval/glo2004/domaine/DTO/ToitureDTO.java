package ca.ulaval.glo2004.domaine.DTO;

import ca.ulaval.glo2004.domaine.Toit.Toiture;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import java.io.Serializable;

public class ToitureDTO implements Serializable{
    
    public double angleToit;
    public Orientation pOrientation;
    public Dimension pDimension;
    public SensToit pSensToit = null;
    public String mLabelToit;
    public PointDecimal mPosition;
    
    public ToitureDTO (Toiture ptoit){
        
        angleToit = ptoit.getAngleToit();
        pOrientation = ptoit.getOrientationToiture();
        pDimension = ptoit.getToitureDimension();
        pSensToit = ptoit.getSensToiture();
        mLabelToit = ptoit.getLabelToit();
        mPosition = ptoit.getPositionToit();
    }
    
}
