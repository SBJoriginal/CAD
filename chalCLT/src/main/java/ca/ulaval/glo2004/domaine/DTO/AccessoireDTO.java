package ca.ulaval.glo2004.domaine.DTO;

import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.*;
import java.util.UUID;
import java.awt.*;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import java.io.Serializable;

public class AccessoireDTO implements Serializable{

    public final UUID Uuid;
    public PointDecimal Position;
    public Dimension Dimension;
    public Color CouleurAccessoire;
    public TypeAccessoire TypeAccessoire;
    public boolean AccessoireValide;
    public boolean SelectionStatus;
    public String LabelAccessoire;
    
    
    public AccessoireDTO(Accessoire pAccessoire) {

        Uuid = pAccessoire.getUuid();
        Position = new PointDecimal(pAccessoire.getPosition().getX(), pAccessoire.getPosition().getY());
        Dimension = new Dimension(pAccessoire.getDimension().getLongueur(), pAccessoire.getDimension().getLargeur(), pAccessoire.getDimension().getHauteur());
        AccessoireValide = pAccessoire.estAccessoireValide();
        TypeAccessoire = pAccessoire.getTypeAccessoire();
        LabelAccessoire = pAccessoire.getAccessoireLabel();
        CouleurAccessoire = pAccessoire.getColor();
        SelectionStatus = pAccessoire.isSelected();
    }
        
        public void setPosition(PointDecimal pPosition)
        {
            Position = pPosition;
        }
        @Override
        public String toString() {
            return LabelAccessoire;
        }
        public void setDimension(Dimension pDimension)
        {
            Dimension = pDimension;
        }
        
        public void setCouleurAccessoire(Color pColor)
        {
            CouleurAccessoire = pColor;
        }
        
        public void setTypeAccessoire(TypeAccessoire pTypeAccessoire)
        {
            TypeAccessoire = pTypeAccessoire;
        }
        
        public void setAccessoireValide(boolean pEstValide)
        {
            AccessoireValide = pEstValide;
        }
        
        public void setSelectionStatus(boolean pIsSelected)
        {
            SelectionStatus = pIsSelected;
        }
        
        public void setLabelAccessoire(String pLabel)
        {
            LabelAccessoire = pLabel;
        }
    }

