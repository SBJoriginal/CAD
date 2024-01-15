package ca.ulaval.glo2004.ExportSTL;

import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.utils.Point3D;
import ca.ulaval.glo2004.domaine.utils.PointSTL;
import ca.ulaval.glo2004.domaine.utils.Triangle;
import java.util.Arrays;

/**
 *
 * @author Reagan
 */
public class GenerateurDeBrut {

    static void genererEtExporterBrut(String cheminDestination, ChaletDTO pChaletDTO) {

        // Générer les murs bruts: Facade, Arriere, Gauche, Droit
        genererMursSTL(cheminDestination, pChaletDTO);
        // Générer le toit bruts
        genererToitSTL(cheminDestination, pChaletDTO); //LIVRABLE 5
    }

    public static void genererMursSTL(String cheminDestination, ChaletDTO pChaletDTO) {
        // Logique de génération du contenu STL pour les murs brut

        String type = "_Brut";

        float epaisseur = pChaletDTO.EpaisseurMur;
        Triangle[] triangles;
        String suffixe = "";
        float dimension;
        PointSTL position;

        float origineHauteur = 0;
        float hauteur = pChaletDTO.MurFacadeDTO.Dimension.getHauteur();
        float longueur = pChaletDTO.DimensionParDefaut.getLongueur();
        float largeur = pChaletDTO.DimensionParDefaut.getLargeur();

        //Facade
        MurDTO murFacade = pChaletDTO.MurFacadeDTO;
        dimension = murFacade.Dimension.getLongueur();
        position = new PointSTL(0, -largeur);

        triangles = genererUnMurBrut(position, dimension, hauteur, epaisseur, origineHauteur);
        suffixe = "_F";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

        //Arriere
        MurDTO murArriere = pChaletDTO.MurArriereDTO;
        dimension = murArriere.Dimension.getLongueur();
        position = new PointSTL(0, 0);
        triangles = genererUnMurBrut(position, dimension, hauteur, epaisseur, origineHauteur);
        suffixe = "_A";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

        //Gauche
        MurDTO murGauche = pChaletDTO.MurGaucheDTO;
        dimension = murGauche.Dimension.getLargeur();
        position = new PointSTL(0, 0);
        triangles = genererUnMurBrut(position, epaisseur, hauteur, dimension, origineHauteur);
        suffixe = "_G";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

        //Droite
        MurDTO murDroite = pChaletDTO.MurDroiteDTO;
        dimension = murDroite.Dimension.getLargeur();
        position = new PointSTL(longueur - epaisseur, 0);
        triangles = genererUnMurBrut(position, epaisseur, hauteur, dimension, origineHauteur);
        suffixe = "_D";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

    }

    public static void genererToitSTL(String cheminDestination, ChaletDTO pChaletDTO) {
        // Logique de génération du contenu STL pour le toit  brut
        //pignons: Gauche et droite
        //Rallonge
        //Toiture

        String type = "_Brut";

        float hauteurRallonge;
        float base;
        float hypotenuse;
        float angle = (float) Math.toRadians(pChaletDTO.ToitDTO.AngleToit);
        PointSTL position;
        //position = new PointSTL(0, - largeur);

        //Ajout Nouveau:
        Triangle[] triangles;
        String suffixe = "";
        float dimension;

        // infos du chalet:
        float hauteurChalet = pChaletDTO.MurFacadeDTO.Dimension.getHauteur();
        float longueurChalet = pChaletDTO.DimensionParDefaut.getLongueur();
        float largeurChalet = pChaletDTO.DimensionParDefaut.getLargeur();
        float epaisseur = pChaletDTO.EpaisseurMur;
        float origineHauteur = hauteurChalet;

        //Fin Ajout 
//        float hauteur = pChaletDTO.MurFacadeDTO.Dimension.getHauteur();
        // Si orientation avant/arriere**************               
        // Calcul des valeurs du pignon et rallonge:
        base = (float) largeurChalet;
        hauteurRallonge = (float) (base * Math.tan(angle));
//        hypotenuse = (float) (base / Math.cos(angle));
        hypotenuse = (float) Math.hypot(base, hauteurRallonge);

        System.out.println("Hauteur Rallonge : " + hauteurRallonge);

        // Logique de génération du contenu STL pour les toits bruts
        //Rallonge
        dimension = longueurChalet;

        position = new PointSTL(0, 0);
        triangles = genererUnMurBrut(position, dimension, hauteurRallonge, epaisseur, origineHauteur);
        suffixe = "_R";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

        //Pignon Gauche
        dimension = base;
        position = new PointSTL(0, 0);
        triangles = genererUnMurBrut(position, epaisseur, hauteurRallonge, dimension, origineHauteur);
        suffixe = "_PG";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

        //Pignon Droite
        dimension = base;
        position = new PointSTL(longueurChalet - epaisseur, 0);
        triangles = genererUnMurBrut(position, epaisseur, hauteurRallonge, dimension, origineHauteur);
        suffixe = "_PD";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

        //Toiture
        dimension = longueurChalet;
        float largeurToiture = hypotenuse;
        float largeur = pChaletDTO.DimensionParDefaut.getLargeur();

        position = new PointSTL(0, -largeur - epaisseur);

        triangles = genererUneToitureBrut(position, dimension, largeurToiture, epaisseur, origineHauteur, angle);
        suffixe = "_T";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

    }

    public static Triangle[] genererUnMurBrut(PointSTL pPosition, float pdimension, float hauteur, float pEpaiseur, float origineHauteur) {

        Triangle[] triangles = new Triangle[12];

        int pointEnX = (int) pPosition.getX();
        int pointEnY = (int) pPosition.getY();
        int pointEnZ = (int) origineHauteur;

        float largeur = pdimension;
        float epaisseur = pEpaiseur;

        Point3D P1;
        Point3D P2;
        Point3D P3;
        Point3D P4;
        Point3D P5;
        Point3D P6;
        Point3D P7;
        Point3D P8;

        // P1 (Inférieur arrière gauche) 
        P1 = new Point3D(pointEnX, pointEnY - epaisseur, pointEnZ);
        //P2 (Inférieur arrière droit) 
        P2 = new Point3D(pointEnX + largeur, pointEnY - epaisseur, pointEnZ);
        //P3 (Supérieur arrière gauche) 
        P3 = new Point3D(pointEnX, pointEnY - epaisseur, pointEnZ + hauteur);
        //P4 (Supérieur arrière droit) 
        P4 = new Point3D(pointEnX + largeur, pointEnY - epaisseur, pointEnZ + hauteur);
        //P5 (Inférieur avant gauche) 
        P5 = new Point3D(pointEnX, pointEnY, pointEnZ);
        //P6 (Inférieur avant droit) 
        P6 = new Point3D(pointEnX + largeur, pointEnY, pointEnZ);
        //P7 (Supérieur avant gauche) 
        P7 = new Point3D(pointEnX, pointEnY, pointEnZ + hauteur);
        //P8 (Supérieur avant droit) 
        P8 = new Point3D(pointEnX + largeur, pointEnY, pointEnZ + hauteur);

        // Facette 1 (avant) : P5, P6, P8, P7
        Triangle triangle1 = new Triangle(P5, P6, P8);
        Triangle triangle2 = new Triangle(P5, P8, P7);
        // Facette 2 (arrière) : P1, P2, P4, P3
        Triangle triangle3 = new Triangle(P1, P2, P4);
        Triangle triangle4 = new Triangle(P1, P4, P3);
        // Facette 3 (gauche) : P5, P1, P3, P7
        Triangle triangle5 = new Triangle(P5, P1, P3);
        Triangle triangle6 = new Triangle(P5, P3, P7);
        // Facette 4 (droite) : P6, P2, P4, P8
        Triangle triangle7 = new Triangle(P6, P2, P4);
        Triangle triangle8 = new Triangle(P6, P4, P8);
        // Facette 5 (dessus) : P3, P4, P8, P7
        Triangle triangle9 = new Triangle(P3, P4, P8);
        Triangle triangle10 = new Triangle(P3, P8, P7);
        // Facette 6 (dessous) : P1, P2, P6, P5
        Triangle triangle11 = new Triangle(P1, P2, P6);
        Triangle triangle12 = new Triangle(P1, P6, P5);

        triangles[0] = triangle1;
        triangles[1] = triangle2;
        triangles[2] = triangle3;
        triangles[3] = triangle4;
        triangles[4] = triangle5;
        triangles[5] = triangle6;
        triangles[6] = triangle7;
        triangles[7] = triangle8;
        triangles[8] = triangle9;
        triangles[9] = triangle10;
        triangles[10] = triangle11;
        triangles[11] = triangle12;

        return triangles;
    }

    public static Triangle[] genererUneToitureBrut(PointSTL pPosition, float pdimension, float hauteur, float pEpaiseur, float origineHauteur, float angle) {

        Triangle[] triangles = new Triangle[8];

        int pointEnX = (int) pPosition.getX();
        int pointEnY = (int) pPosition.getY();
        int pointEnZ = (int) origineHauteur;

        float largeur = pdimension;
        float epaisseur = pEpaiseur;

        Point3D P1;
        Point3D P2;
        Point3D P3;
        Point3D P4;
        Point3D P5;
        Point3D P6;
        Point3D P7;
        Point3D P8;

        // P1 (Inférieur arrière gauche) 
        P1 = new Point3D(pointEnX, pointEnY - epaisseur, pointEnZ);
        //P2 (Inférieur arrière droit) 
        P2 = new Point3D(pointEnX + largeur, pointEnY - epaisseur, pointEnZ);
        //P3 (Supérieur arrière gauche) 
        P3 = new Point3D(pointEnX, pointEnY + epaisseur + largeur, pointEnZ + hauteur);
        //P4 (Supérieur arrière droit) 
        P4 = new Point3D(pointEnX + largeur, pointEnY + epaisseur + largeur, pointEnZ + hauteur);
        //P5 (Inférieur avant gauche) 
        P5 = new Point3D(pointEnX, pointEnY + epaisseur + largeur, pointEnZ);
        //P6 (Inférieur avant droit) 
        P6 = new Point3D(pointEnX + largeur, pointEnY + epaisseur + largeur, pointEnZ);
        //P7 (Supérieur avant gauche) 
        P7 = new Point3D(pointEnX, pointEnY + epaisseur + largeur, pointEnZ + hauteur + (float) Math.tan(Math.toRadians(angle)) * largeur);
        //P8 (Supérieur avant droit) 
        P8 = new Point3D(pointEnX + largeur, pointEnY + epaisseur + largeur, pointEnZ + hauteur + (float) Math.tan(Math.toRadians(angle)) * largeur);

        // Calculer le centre de rotation
        double centerX = (P1.x + P5.x) / 2;
        double centerY = (P1.y + P5.y) / 2;
        double centerZ = (P1.z + P5.z) / 2;
        
        
        // Appliquer la rotation à chaque point du parallélépipède
        //P1 = rotatePoint(P1, centerX, centerY, centerZ, angle);
        //P2 = rotatePoint(P2, centerX, centerY, centerZ, angle);
//        P3 = rotatePoint(P3, centerX, centerY, centerZ, angle);
//        P4 = rotatePoint(P4, centerX, centerY, centerZ, angle);
//        P5 = rotatePoint(P5, centerX, centerY, centerZ, angle);
//        P6 = rotatePoint(P6, centerX, centerY, centerZ, angle);
//        P7 = rotatePoint(P7, centerX, centerY, centerZ, angle);
//        P8 = rotatePoint(P8, centerX, centerY, centerZ, angle);

        // Facette 1 (avant) : P5, P6, P8, P7
        Triangle triangle1 = new Triangle(P5, P6, P8);
        Triangle triangle2 = new Triangle(P5, P8, P7);
        // Facette 2 (arrière) : P1, P2, P4, P3
        Triangle triangle3 = new Triangle(P1, P2, P4);
        Triangle triangle4 = new Triangle(P1, P4, P3);
        // Facette 3 (gauche) : P5, P1, P3, P7
        Triangle triangle5 = new Triangle(P5, P1, P3);
        Triangle triangle6 = new Triangle(P5, P3, P7);
        // Facette 4 (droite) : P6, P2, P4, P8
        Triangle triangle7 = new Triangle(P6, P2, P4);
        Triangle triangle8 = new Triangle(P6, P4, P8);

        triangles[0] = triangle1;
        triangles[1] = triangle2;
        triangles[2] = triangle3;
        triangles[3] = triangle4;
        triangles[4] = triangle5;
        triangles[5] = triangle6;
        triangles[6] = triangle7;
        triangles[7] = triangle8;

        return triangles;
    }

// Fonction pour effectuer une rotation d'un point autour d'un centre en 3D
    private static Point3D rotatePoint(Point3D point, double centerX, double centerY, double centerZ, double angle) {
        double x = point.x - centerX;
        double y = point.y - centerY;
        double z = point.z - centerZ;

        double newX = x * Math.cos(angle) - z * Math.sin(angle) + centerX;
        double newY = y;
        double newZ = x * Math.sin(angle) + z * Math.cos(angle) + centerZ;

        return new Point3D(newX, newY, newZ);
    }

}
