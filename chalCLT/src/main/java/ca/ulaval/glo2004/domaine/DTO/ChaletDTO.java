package ca.ulaval.glo2004.domaine.DTO;
import ca.ulaval.glo2004.domaine.Chalet.Chalet;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.Toit.Toit;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Erreur;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChaletDTO implements Serializable{
    public MurDTO MurArriereDTO;
    public MurDTO MurGaucheDTO;
    public MurDTO MurFacadeDTO;
    public MurDTO MurDroiteDTO;

    public MurDTO[] MurList = new MurDTO[4];
   
    public List<AccessoireDTO> AccessoireArriereList = new ArrayList<>();
    public List<AccessoireDTO> AccessoireDroiteList = new ArrayList<>();
    public List<AccessoireDTO> AccessoireGaucheList = new ArrayList<>();
    public List<AccessoireDTO> AccessoireFacadeList = new ArrayList<>();
    public SensToit SensToit;
    
    public Dimension DimensionParDefaut;
    public UUID Uuid;
    public float EpaisseurMur;
    public float Rainure;
    public Toit Toit;
    public ToitDTO ToitDTO;
    
    public ChaletDTO(Chalet chalet){
        DimensionParDefaut = chalet.getDimensionChalet();
        
        MurArriereDTO = new MurDTO(chalet.getMurArriere());
        MurGaucheDTO = new MurDTO(chalet.getMurGauche());
        MurFacadeDTO = new MurDTO(chalet.getMurFacade());
        MurDroiteDTO = new MurDTO(chalet.getMurDroite());
        
        for (Accessoire accessoire : chalet.getAccessoireArriere()) {
            AccessoireArriereList.add(new AccessoireDTO(accessoire));
        }
        for (Accessoire accessoire : chalet.getAccessoireDroite()) {
            AccessoireArriereList.add(new AccessoireDTO(accessoire));
        }
        for (Accessoire accessoire : chalet.getAccessoireGauche()) {
            AccessoireGaucheList.add(new AccessoireDTO(accessoire));
        }
        for (Accessoire accessoire : chalet.getAccessoireFacade()){
            AccessoireFacadeList.add(new AccessoireDTO(accessoire));
        }
        MurList[0] = MurArriereDTO;
        MurList[1] = MurGaucheDTO;
        MurList[2] = MurFacadeDTO;
        MurList[3] = MurDroiteDTO;

        Uuid = chalet.getUUID();
        SensToit = chalet.getSensToit();
        EpaisseurMur = chalet.getEpaisseurMur() ;
        Rainure = chalet.getRainure();
        Toit = chalet.getToit();
        ToitDTO = new ToitDTO(chalet.getToit());
        
    } 
    public void valider(List<Erreur> erreurs) {       
        //To Update
    } 

    public void setDimensionParDefaut(Dimension DimensionParDefaut) {
        this.DimensionParDefaut = DimensionParDefaut;
    }
}
