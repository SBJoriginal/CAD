package ca.ulaval.glo2004.domaine.utils;
import java.io.Serializable;

public class Dimension implements Serializable{
    float mLongueur;
    float mLargeur;
    float mHauteur;
    
    public Dimension(float mlongueur, float mlargeur, float mhauteur){

        this.mLongueur = mlongueur;
        this.mLargeur = mlargeur;
        this.mHauteur = mhauteur;
    }

    public Dimension() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public float getLongueur() {
        return this.mLongueur;
    }
    public float getLargeur() {
        
        return this.mLargeur;
    }
    public float getHauteur() {
        return this.mHauteur;
    }
    public void setLongueur(float longueur){
       
        this.mLongueur = longueur;
    }
    
    public void setLargeur(float largeur){
        
        this.mLargeur = largeur;
       
    }
    
    public void setHauteur(float hauteur){
       
        this.mHauteur = hauteur;
      
    }
    

    
}
