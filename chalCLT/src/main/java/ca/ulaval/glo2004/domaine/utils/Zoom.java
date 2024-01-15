package ca.ulaval.glo2004.domaine.utils;
import java.awt.Point;
import java.io.Serializable;
public class Zoom implements Serializable{
    
    private double zoomLevel;
    private Point viewOffset;
    public Zoom(double zoomLevel, Point viewOffset) {
        
        
        this.zoomLevel = zoomLevel;
        this.viewOffset = viewOffset;
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(double zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public Point getViewOffset() {
        return viewOffset;
    }

    public void setViewOffset(Point viewOffset) {
        
        this.viewOffset = viewOffset;
    }


    public void moveBy(double dx, double dy) {
        double dxZoom = dx * (1 - 1/zoomLevel);
        double dyZoom = dy * (1 - 1/zoomLevel);
        viewOffset.translate((int) (dx - dxZoom), (int) (dy - dyZoom));
    }
    
}
