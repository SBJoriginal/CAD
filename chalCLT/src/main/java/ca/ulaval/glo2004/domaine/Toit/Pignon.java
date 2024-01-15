package ca.ulaval.glo2004.domaine.Toit;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import java.io.Serializable;
import java.util.UUID;

public class Pignon implements Serializable{
   
    
    ChaletDTO pChaletDTO;
    float angle = (float)Math.toDegrees(pChaletDTO.ToitDTO.AngleToit);
    private float pLargeurPignon;
    private float pHauteurPignon;
    private float pPentePignon;
    private float pDimensionPignon;
    private UUID uuidPignon;
    private Orientation pOrientation;
    private String mLabelPignon;
    private final PointDecimal positionPignon;
    
    public Pignon(Orientation mOrientation){

        this.pOrientation = mOrientation;
        this.pDimensionPignon = (float) pChaletDTO.MurArriereDTO.Dimension.getLongueur();
        this.pLargeurPignon = (float)(pChaletDTO.MurGaucheDTO.Dimension.getLargeur());
        this.pPentePignon = (float) (pPentePignon / Math.cos(angle));
        this.pHauteurPignon = (float) ((float)(pChaletDTO.MurGaucheDTO.Dimension.getLargeur()) * Math.tan(angle));
        this.positionPignon = pChaletDTO.MurArriereDTO.Position;
    }
    
    public float getHauteurPignon(){
        return pHauteurPignon;
       
    }
    
    public float getLargeurPignon(){
        return pLargeurPignon;
       
    }
    
    public float getPentePignon(){
        return pPentePignon;
       
    }
        
    public UUID getUuid() {
        return uuidPignon;
    }
    
    public float getDimensionPignon(){
        return pDimensionPignon;
    }
    
    public PointDecimal getPositionPignon(){
        return positionPignon;
    }
    
    public Orientation getOrientationPignon(){
        return pOrientation;
    }
    @Override
    public String toString(){
        switch (pOrientation){
            case GAUCHE:
                mLabelPignon = "PIGNONGAUCHE";
                break;
                
            case DROITE:
                mLabelPignon = "PIGNONDROITE";
                break;
                
            default:
                throw new AssertionError();
        }
        return mLabelPignon;
    }

    public ChaletDTO getpChaletDTO() {
        return pChaletDTO;
    }

    public void setpChaletDTO(ChaletDTO pChaletDTO) {
        this.pChaletDTO = pChaletDTO;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getpLargeurPignon() {
        return pLargeurPignon;
    }

    public void setpLargeurPignon(float pLargeurPignon) {
        this.pLargeurPignon = pLargeurPignon;
    }

    public float getpHauteurPignon() {
        return pHauteurPignon;
    }

    public void setpHauteurPignon(float pHauteurPignon) {
        this.pHauteurPignon = pHauteurPignon;
    }

    public float getpPentePignon() {
        return pPentePignon;
    }

    public void setpPentePignon(float pPentePignon) {
        this.pPentePignon = pPentePignon;
    }

    public float getpDimensionPignon() {
        return pDimensionPignon;
    }

    public void setpDimensionPignon(float pDimensionPignon) {
        this.pDimensionPignon = pDimensionPignon;
    }

    public UUID getUuidPignon() {
        return uuidPignon;
    }

    public void setUuidPignon(UUID uuidPignon) {
        this.uuidPignon = uuidPignon;
    }

    public Orientation getpOrientation() {
        return pOrientation;
    }

    public void setpOrientation(Orientation pOrientation) {
        this.pOrientation = pOrientation;
    }

    public String getmLabelPignon() {
        return mLabelPignon;
    }

    public void setmLabelPignon(String mLabelPignon) {
        this.mLabelPignon = mLabelPignon;
    }
    
    

    void setUuid(UUID randomUUID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
