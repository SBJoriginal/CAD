package ca.ulaval.glo2004.domaine.Chalet;

import ca.ulaval.glo2004.domaine.DTO.ToitDTO;
import ca.ulaval.glo2004.domaine.DTO.ToitureDTO;
import ca.ulaval.glo2004.domaine.Toit.Toit;
import ca.ulaval.glo2004.domaine.Toit.Toiture;
import java.io.Serializable;
public class ToitMapper implements Serializable{
    
    private RallongeMapper rallongeMapper = new RallongeMapper();
    private PignonMapper pignonMapper = new PignonMapper();
    private ToitureMapper toitureMapper = new  ToitureMapper(); 
    
    
    public ToitureDTO mapVersToitDTO(Toiture toiture){       
        ToitureDTO toitureDTO = new ToitureDTO(toiture);        
        return toitureDTO;
    }
    
    public void mettreAjourToitDTO(Toit toit, ToitDTO toitDTO){        
        toitDTO.AngleToit = toit.getAngleToit();        
        toitDTO.SensToit = toit.getSensToiture();
        toitDTO.LabelToit = toit.getLabelToit();
        toitDTO.Position = toit.getPositionToit();
    }
    
}

