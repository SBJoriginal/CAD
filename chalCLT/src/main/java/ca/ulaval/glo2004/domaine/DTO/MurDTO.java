package ca.ulaval.glo2004.domaine.DTO;

import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.*;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.Mur.Mur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MurDTO implements Serializable{

    public Orientation Orientation;
    public PointDecimal Position;
    public Dimension Dimension;
    public List<AccessoireDTO> AccessoiresList = new ArrayList<>();
    public String LabelMur;
    public UUID Uuid;
    public double Epaisseur;
    public double Retrait;

    public MurDTO(Mur pMur) {

        Uuid = pMur.getUuid();
        Position = pMur.getPosition();
        Dimension = pMur.getMurDimension();
        Orientation = pMur.getOrientation();
        Uuid = pMur.getUuid();
        Epaisseur = pMur.getEpaisseur();
        Retrait = pMur.getRetrait();
        LabelMur = pMur.getLabelMur();
        for (Accessoire accessoire : pMur.getAccessoiresList()) {
            AccessoiresList.add(new AccessoireDTO(accessoire));
        }
    }
}
