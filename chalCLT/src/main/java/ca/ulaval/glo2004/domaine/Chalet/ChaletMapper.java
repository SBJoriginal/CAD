package ca.ulaval.glo2004.domaine.Chalet;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.Mur.Mur;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
 
/**
* Permet de faire une liaison et mise à jour entre les objets du domaine et le
* DTO
*/
public class ChaletMapper implements Serializable{ 
    
 
    /**
     * Permet de créer un mur DTO à partir d'un mur du domaine
     */
    public ChaletDTO mapVersChaletDTO(Chalet chalet) {
        return new ChaletDTO(chalet);
    }
 
    
    public void mettreAjourChaletDTO(Chalet chalet, ChaletDTO chaletDTO) {
        
        
        MurMapper murMapper = new MurMapper();
        chalet.getMurArriere().setMurDimension(chalet.getDimensionChalet());
        chalet.getMurFacade().setMurDimension(chalet.getDimensionChalet());
        chalet.getMurDroite().setMurDimension(chalet.getDimensionChalet());
        chalet.getMurGauche().setMurDimension(chalet.getDimensionChalet());
        
        chalet.getMurArriere().setEpaisseur(chalet.getEpaisseurMur());
        chalet.getMurFacade().setEpaisseur(chalet.getEpaisseurMur());
        chalet.getMurDroite().setEpaisseur(chalet.getEpaisseurMur());
        chalet.getMurGauche().setEpaisseur(chalet.getEpaisseurMur());
        
        murMapper.mettreAjourMurDTO(chalet.getMurArriere(),chaletDTO.MurArriereDTO);
        murMapper.mettreAjourMurDTO(chalet.getMurGauche(), chaletDTO.MurGaucheDTO);
        murMapper.mettreAjourMurDTO(chalet.getMurFacade(), chaletDTO.MurFacadeDTO);
        murMapper.mettreAjourMurDTO(chalet.getMurDroite(), chaletDTO.MurDroiteDTO);    
 
        MurDTO[] MurList = new MurDTO[4];
 
         MurList[0] = chaletDTO.MurArriereDTO;
         MurList[1] = chaletDTO.MurGaucheDTO;
         MurList[2] = chaletDTO.MurFacadeDTO;
         MurList[3] = chaletDTO.MurDroiteDTO;     
 
        chaletDTO.SensToit = chalet.getSensToit();
        chaletDTO.DimensionParDefaut= chalet.getDimensionChalet();
        chaletDTO.EpaisseurMur = chalet.getEpaisseurMur();
        chaletDTO.Rainure = chalet.getRainure();
        this.createMapMurWIthUuid(chalet);
        this.createMapAccesssoireWithUuid(chalet);
        chaletDTO.Toit = chalet.getToit();
    }
    
    /**
     * 
     * 
     * @param chalet
     * @return 
    **/
    public Map<UUID, Accessoire> createMapAccesssoireWithUuid(Chalet chalet){
        Map<UUID, Accessoire> accessoireMap = new HashMap<>();     
        for(Mur mur : chalet.getListeDeMurs()){
            for(Accessoire accessoire : mur.getAccessoiresList()){
                accessoireMap.put(accessoire.getUuid(), accessoire);
            }
        }
        return accessoireMap;
    }
    /**
     * 
     * @param chalet
     * @return 
     */
    public Map<UUID, Mur> createMapMurWIthUuid(Chalet chalet){
        Map<UUID, Mur> murMap = new HashMap<>();     
        for(Mur mur : chalet.getListeDeMurs()){
            murMap.put(mur.getUuid(), mur);
        }
        return murMap;
    }

 
}