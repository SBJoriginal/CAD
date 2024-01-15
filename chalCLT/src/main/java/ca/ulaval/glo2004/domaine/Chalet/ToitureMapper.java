package ca.ulaval.glo2004.domaine.Chalet;
import ca.ulaval.glo2004.domaine.DTO.RallongeDTO;
import ca.ulaval.glo2004.domaine.DTO.ToitureDTO;
import ca.ulaval.glo2004.domaine.DTO.PignonDTO;
import ca.ulaval.glo2004.domaine.DTO.ToitDTO;
import ca.ulaval.glo2004.domaine.Toit.Rallonge;
import ca.ulaval.glo2004.domaine.Toit.Toiture;
public class ToitureMapper{
    
    public ToitureDTO mapVersToitureDTO(Toiture toiture){       
        ToitureDTO toitureDTO = new ToitureDTO(toiture);        
        return toitureDTO;
    }
    
    public void mettreAjourToitureDTO(Toiture toiture, ToitureDTO toitureDTO){
//        rallongeMapper.mettreAjourRallongeDTO(rallonge, rallongeDTO);
//        pignonMapper.mettreAjourPignonDTO(pignon, pignonDTO);
//        toitMapper.mettreAjourToitDTO(toit, toitDTO);
        
        toitureDTO.angleToit = toiture.getAngleToit();
        toitureDTO.pOrientation = toiture.getOrientationToiture();
        toitureDTO.pDimension = toiture.getToitureDimension();
        toitureDTO.pSensToit = toiture.getSensToiture();
        toitureDTO.mLabelToit = toiture.getLabelToit();
        toitureDTO.mPosition = toiture.getPositionToit();
    }
    
}
