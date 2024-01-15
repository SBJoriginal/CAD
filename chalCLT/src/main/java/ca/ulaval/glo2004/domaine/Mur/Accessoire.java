package ca.ulaval.glo2004.domaine.Mur;
import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.*;
import java.util.UUID;
import java.awt.*;
import java.io.Serializable;

public class Accessoire implements Serializable{

    private final UUID uuid;
    private PointDecimal mPosition;
    private Dimension mDimension;
    private Color mCouleurAccessoire;
    private TypeAccessoire mTypeAccessoire;
    private boolean mAccessoireValide;
    private boolean selectionStatus;
    private String mLabelAccessoire;

    public Accessoire(PointDecimal pPosition, Dimension pDimension, TypeAccessoire pTypeAccessoire, boolean pAccessoireValide, String pAccessoireLabel) {

        this.uuid = UUID.randomUUID();
        this.mPosition = pPosition;
        this.mDimension = pDimension;
        this.mAccessoireValide = pAccessoireValide;
        this.mTypeAccessoire = pTypeAccessoire;
        this.mLabelAccessoire = pAccessoireLabel;
        setCouleurAccessoire();
    }
    
    /**
     * constructeur pour créer des accessoire du domaine après un chargement de fichier 
     * @param accessoireDTO 
     */
    public Accessoire(AccessoireDTO accessoireDTO) {
        this.uuid = accessoireDTO.Uuid;
        this.mPosition = accessoireDTO.Position;
        this.mDimension = accessoireDTO.Dimension;
        this.mCouleurAccessoire = accessoireDTO.CouleurAccessoire;
        this.mTypeAccessoire = accessoireDTO.TypeAccessoire;
        this.mAccessoireValide = accessoireDTO.AccessoireValide;
        this.selectionStatus = accessoireDTO.SelectionStatus;
        this.mLabelAccessoire = accessoireDTO.LabelAccessoire;
    }
    
    public UUID getUuid() {
        return uuid;
    }
    @Override
    public String toString() {
        return mLabelAccessoire;
    }
    public Color getColor() {
        return mCouleurAccessoire;
    }
    public PointDecimal getPosition() {
        return mPosition;
    }

    public void setPosition(PointDecimal userPosition) {
        mPosition = userPosition;
        
    }
    public Dimension getDimension() {
        return mDimension;
    }
    public void setDimension(Dimension userDimension) {
        mDimension = userDimension;
     
    }
    public void setColorRed(Color c){
        this.mCouleurAccessoire = c;
    }
    private void setCouleurAccessoire() {
        if (mAccessoireValide) {
            if (mTypeAccessoire == TypeAccessoire.FENETRE) {
                this.mCouleurAccessoire = Color.GREEN;
            } else {
                this.mCouleurAccessoire = Color.BLUE;
            }
        } else {
            this.mCouleurAccessoire = Color.RED;
        }
       
    }
    public void setValidite(boolean userAccessoireValide) {
        this.mAccessoireValide = userAccessoireValide;
        setCouleurAccessoire();
    }

    public boolean estAccessoireValide() {
        return mAccessoireValide;
    }

    public String getAccessoireLabel() {
        return this.mLabelAccessoire;
    }
    public TypeAccessoire getTypeAccessoire() {
        return mTypeAccessoire;
    }
    public void setTypeAccessoire(TypeAccessoire pTypeAccessoire) {
        this.mTypeAccessoire = pTypeAccessoire;
        
    }
    public boolean isSelected() {
        return this.selectionStatus;
    }
    public void switchSelection() {
        this.selectionStatus = !this.selectionStatus;
    }
    boolean contains(double x, double y) {
        return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
    }
    private boolean xIsInsideItemWidth(double x) {
        return (x < mPosition.getX() + (mDimension.getLargeur())) && (x > mPosition.getX() - (mDimension.getLargeur()));
    }
    private boolean yIsInsideItemHeight(double y) {
        return (y < mPosition.getY() + (mDimension.getHauteur())) && (y > mPosition.getY() - (mDimension.getHauteur()));
    }

}
