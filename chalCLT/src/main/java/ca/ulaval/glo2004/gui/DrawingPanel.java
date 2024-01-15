package ca.ulaval.glo2004.gui;
import ca.ulaval.glo2004.domaine.Chalet.Controleur;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.drawings.AfficheurDessus;
import ca.ulaval.glo2004.drawings.AfficheurMur;
import ca.ulaval.glo2004.drawings.AfficheurToit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import javax.swing.JPanel;
 
public class DrawingPanel extends JPanel implements Serializable
{
    private AfficheurDessus mAfficheurDessus;
    private AfficheurMur mAfficheurMur;
    private AfficheurToit mAfficheurToit;
    private double zoom = 1.0;
    private int zoomPointX = 10;
    private int zoomPointY = 10;

    private MainWindow mainWindow;
    private Controleur controleur;
    

    public DrawingPanel() {
        
    }
    public DrawingPanel(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
        controleur = mainWindow.getControleur();
        
        this.mAfficheurDessus = new AfficheurDessus(controleur);
        this.mAfficheurMur = new AfficheurMur(controleur);
        this.mAfficheurToit =  new AfficheurToit(controleur);
         
    }
    
    public void clear() {
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.clearRect(0, 0, getWidth(), getHeight());
    }
       
    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }
    
    @Override
    protected void paintComponent( Graphics g )
    {
        Graphics2D g2D = (Graphics2D) g;
        AffineTransform at = g2D.getTransform();
        if (controleur != null) {
            at.translate(controleur.getViewOffset().x, controleur.getViewOffset().y);
            at.scale(controleur.getZoomLevel(), controleur.getZoomLevel());
        }

        g2D.setTransform(at); 
        if (mainWindow != null){
            super.paintComponent(g2D);
            Orientation vueCourant = controleur.getVueCourant();
            if (vueCourant == Orientation.DESSUS){
                this.mAfficheurDessus.draw(g2D);
            }
            else{
                this.mAfficheurMur.draw(g2D);
                this.mAfficheurToit.draw(g2D);
            }
        }
    }
        
}
