package ca.ulaval.glo2004.domaine.Toit;

import ca.ulaval.glo2004.domaine.Chalet.Chalet;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import java.io.Serializable;
import java.util.UUID;

public class Toit implements Serializable {

    private double pAngleToit;
    private SensToit pSensToit;
    private String mLabelToit;
    private PointDecimal mPosition;
    private Chalet chalet;
    
    private Pignon pignonGauche;
    private Pignon pignonDroite;
    private Rallonge rallonge;
    private Toiture toiture;
    

    public Toit(double angleToit, SensToit sensToit, Chalet chalet) {

        this.pAngleToit = angleToit;
        this.pSensToit = sensToit;
        this.chalet = chalet;
    }
    public double getAngleToit() {
        return pAngleToit;
    }

    public void setAngleToit(double angleToit) {
        this.pAngleToit = angleToit;
    }

    public SensToit getSensToiture() {
        return pSensToit;
    }

    public String getLabelToit() {
        return mLabelToit;
    }

    public PointDecimal getPositionToit() {
        return mPosition;
    }

    
}
