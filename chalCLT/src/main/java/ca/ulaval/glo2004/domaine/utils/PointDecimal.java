package ca.ulaval.glo2004.domaine.utils;
import static ca.ulaval.glo2004.gui.MainWindow.afficherErreur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elhadji.nimaga
 */
public class PointDecimal implements Serializable{
    private float x, y;
    
    public PointDecimal (float X, float Y){
        Erreur error_X_invalide = new Erreur ("La position du X doit être plus grande ou égale que 0.");
        Erreur error_Y_invalide = new Erreur ("La position du Y doit être plus grande ou égale que 0.");          
        List<Erreur> erreurs_PointDecimal = new ArrayList<>();
        if (X < 0) {        
          erreurs_PointDecimal.add(error_X_invalide);
        }
        if (Y < 0) {
        erreurs_PointDecimal.add(error_Y_invalide);
        }
        if (X< 0 || Y< 0){
            afficherErreur(erreurs_PointDecimal); 
            return;
        }
        this.x = X;
        this.y = Y;
        
        assert this.x == X : "La position X n'a pas été initialisée correctement";
        assert this.y == Y : "La position Y n'a pas été initialisée correctement";
    }
    public PointDecimal (){
        
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
        
    public void setX (float x){
        if (x < 0) {
            Erreur error_X_invalide = new Erreur ("La position du X doit être plus grande ou égale que 0.");
            List<Erreur> erreurs_PointDecimal = new ArrayList<>();
            erreurs_PointDecimal.add(error_X_invalide);
            afficherErreur(erreurs_PointDecimal); 
            return;
        }
            this.x= x;
            
            assert this.x == x : "La position X n'a pas été modifiée correctement";
        
    }
    public void setY (float y){
        if (x < 0) {
            Erreur error_Y_invalide = new Erreur ("La position du Y doit être plus grande ou égale que 0.");
            List<Erreur> erreurs_PointDecimal = new ArrayList<>();
            erreurs_PointDecimal.add(error_Y_invalide);
            afficherErreur(erreurs_PointDecimal); 
            return;
        }
            this.y= y;
            
            assert this.y == y : "La position Y n'a pas été modifiée correctement";
        
    }
}
