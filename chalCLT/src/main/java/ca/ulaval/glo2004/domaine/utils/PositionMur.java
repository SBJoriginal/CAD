package ca.ulaval.glo2004.domaine.utils;

import java.io.Serializable;

public class PositionMur implements Serializable{
    
    public int PositionXMurFacadeArriere = 200;
    public int PositionYMurFacadeArriere = 150;
    public int PositionXMurGaucheDroit = 200;
    public int PositionYMurGaucheDroit = 150;
    public int PositionXVueDESSUS = 200;
    public int PositionYVueDESSUS = 150;
    public int EpaisseurMur = 20;
    
    
    //Ajout pour position relative (STL):
    // Les positions représentent les coins superieurs gauches de chaque murs bruts
    // 1. Avant-Arriere
    public int PositionXMurFA = 0;
        //variation en largeur
    public int PositionYMurA = 0;
    public int PositionYMurF; // à calculer à la création du mur en fonction de la largeur
    
    // 1. Gauche-Droite
    public int PositionYMurGD = 0;
       //variation en longueur
    public int PositionxMurG = 0;
    public int PositionxMurD; // à calculer à la création du mur en fonction de la longueur
    
    
    public PositionMur(){
    
    }
    public void setEpaisseurMur(int newEpaisseurMur){
        this.EpaisseurMur = newEpaisseurMur;
    }
}
