package ca.ulaval.glo2004.domaine.DTO;
import ca.ulaval.glo2004.domaine.utils.*;
import ca.ulaval.glo2004.domaine.Toit.Toit;
import java.io.Serializable;

public class ToitDTO implements Serializable {
    
    public double AngleToit;
    public SensToit SensToit;
    public String LabelToit;
    public PointDecimal Position;
    
    public ToitDTO(Toit ptoit){
        AngleToit = ptoit.getAngleToit();
       
    }
    public void setPosition(PointDecimal pPosition){
        Position = pPosition;
    }
}
