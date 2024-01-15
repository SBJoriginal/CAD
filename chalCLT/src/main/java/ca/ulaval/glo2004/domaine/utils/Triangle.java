/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.domaine.utils;

/**
 *
 * @author Chadrack
 */
public class Triangle {
    
    public Point3D A, B, C;

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this.A = p1;
        this.B = p2;
        this.C = p3;
        
    }
    
    public void normalize() {
        A.normalize();
        B.normalize();
        C.normalize();
    }
    
    @Override
    public String toString(){
        return "{ [ (" + A.x + "," + A.y + "," + A.z + ") , (" + B.x + "," + B.y + "," + B.z + ") (" + B.x + "," + B.y + "," +  B.z + ") ] }";          
    }
         
}
