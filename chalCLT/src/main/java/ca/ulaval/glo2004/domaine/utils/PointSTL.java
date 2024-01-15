package ca.ulaval.glo2004.domaine.utils;
import java.io.Serializable;


/**
 *
 * @author elhadji.nimaga
 */
public class PointSTL implements Serializable{
    private float x, y;
    
    public PointSTL (float X, float Y){
               
        this.x = X;
        this.y = Y;        
        
    }
    
    public PointSTL (){
        
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
        
    public void setX (float x){
        
            this.x= x;                       
    }
    
    public void setY (float y){
     
            this.y= y;            
        
    }
}
