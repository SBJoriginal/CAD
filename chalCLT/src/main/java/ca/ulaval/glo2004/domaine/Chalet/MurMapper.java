package ca.ulaval.glo2004.domaine.Chalet;
 
import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.Mur.Mur;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.PositionMur;
import ca.ulaval.glo2004.domaine.utils.TypeAccessoire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
/**
* Permet de faire une liaison et mise à jour entre les objets du domaine et le
* DTO
*/
public class MurMapper implements Serializable{
 
    
        private AccessoireMapper accessoireMapper = new  AccessoireMapper();

        /**
     * Permet de créer un mur DTO à partir d'un mur du domaine
     *
     */
        public MurDTO mapVersMurDTO(Mur mur) {
            MurDTO murDTO = new MurDTO(mur);
            return murDTO;
        }
 
        public void mettreAjourMurDTO(Mur mur, MurDTO murDTO) {
            murDTO.Position = mur.getPosition();
            murDTO.Dimension = mur.getMurDimension();
            murDTO.Orientation = mur.getOrientation();
            murDTO.LabelMur= mur.getLabelMur();
            murDTO.Epaisseur= mur.getEpaisseur();
            murDTO.Retrait= mur.getRetrait();
            
 
            // Mettez à jour les accessoires du murDTO en fonction des accessoires du mur
            List<AccessoireDTO> accessoiresDTO = new ArrayList<>();
            for (Accessoire accessoire : mur.getAccessoiresList()) { 
//                PositionMur PositionDeMur = new PositionMur();
//                int distanceMin = 3;
//                if(accessoire.getTypeAccessoire() == TypeAccessoire.PORTE){
//                    PointDecimal point = accessoire.getPosition();
//                    if(mur.getOrientation() == Orientation.ARRIERE || mur.getOrientation() == Orientation.FACADE){
//                        int pointY = (PositionDeMur.PositionYMurFacadeArriere  + (int)mur.getMurDimension().getHauteur()) -  ((int)accessoire.getDimension().getHauteur() + distanceMin);
//                        point =  new PointDecimal(point.getX(),pointY);
//                    }
//                    if(mur.getOrientation() == Orientation.GAUCHE || mur.getOrientation() == Orientation.DROITE){
//                        
//                        int pointY = (PositionDeMur.PositionYMurGaucheDroit  + (int)mur.getMurDimension().getHauteur()) -  ((int)accessoire.getDimension().getHauteur() + distanceMin);
//                        point =  new PointDecimal(point.getX(),pointY);
//                    }
//                    accessoire.setPosition(point);
//                }
                AccessoireDTO accessoireDTO = accessoireMapper.mapVersAccessoireDTO(accessoire);
                accessoiresDTO.add(accessoireDTO);
            }
            murDTO.AccessoiresList.clear();
            murDTO.AccessoiresList.addAll(accessoiresDTO);
        }

}