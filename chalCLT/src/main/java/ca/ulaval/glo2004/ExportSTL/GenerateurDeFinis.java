/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.ExportSTL;

import static ca.ulaval.glo2004.ExportSTL.GenerateurDeBrut.genererUnMurBrut;
import ca.ulaval.glo2004.domaine.Chalet.AccessoireMapper;
import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Point3D;
import ca.ulaval.glo2004.domaine.utils.PointSTL;
import ca.ulaval.glo2004.domaine.utils.Triangle;
import ca.ulaval.glo2004.domaine.utils.TypeAccessoire;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Reagan
 */
public class GenerateurDeFinis {

    static void genererEtExporterFini(String cheminDestination, ChaletDTO pChaletDTO) throws IOException {

        // Générer les murs finis: Facade, Arriere, Gauche, Droit
        genererMursSTL(cheminDestination, pChaletDTO);
        genererToitSTL(cheminDestination, pChaletDTO);

    }

    public static void genererMursSTL(String cheminDestination, ChaletDTO pChaletDTO) {
       
        String type = "_Fini";

        // Récupération de la liste d'accessoire (Temporaire)
        List<AccessoireDTO> accessoireFacade = new ArrayList<>();
        List<AccessoireDTO> accessoireArriere = new ArrayList<>();
        List<AccessoireDTO> accessoireDroite = new ArrayList<>();
        List<AccessoireDTO> accessoireGauche = new ArrayList<>();

        for (MurDTO mur : pChaletDTO.MurList) {
            String murLabel = mur.Orientation.toString();

            switch (murLabel) {
                case "FACADE":
                    accessoireFacade = mur.AccessoiresList;
                    break;
                case "ARRIERE":
                    accessoireArriere = mur.AccessoiresList;
                    break;
                case "DROITE":
                    accessoireDroite = mur.AccessoiresList;
                    break;
                case "GAUCHE":
                    accessoireGauche = mur.AccessoiresList;
                    break;
                default:
                    throw new AssertionError();
            }
        }

        // Logique de génération du contenu STL pour les murs fini
        float epaisseur = pChaletDTO.EpaisseurMur;
        Triangle[] triangles;
        String suffixe = "";
        float dimension;
        PointSTL position = new PointSTL();
        float hauteur = pChaletDTO.MurFacadeDTO.Dimension.getHauteur();
        float origineHauteur = 0;

//        System.out.println("Hauteur Mur : " + hauteur );

        
        //Facade
        MurDTO murFacade = pChaletDTO.MurFacadeDTO;
        dimension = murFacade.Dimension.getLongueur();
        //position = pChaletDTO.MurFacadeDTO.Position;


        System.out.println(" Accessoire Facade: " + accessoireFacade.size());

        triangles = genererUnMurFini(position, dimension, hauteur, epaisseur, accessoireFacade.toArray(new AccessoireDTO[0]), origineHauteur);

        suffixe = "_F";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);


//        System.out.println("Facade : " + dimension );

        //Arriere
        MurDTO murArriere = pChaletDTO.MurArriereDTO;
        dimension = murArriere.Dimension.getLongueur();
        //position = pChaletDTO.MurArriereDTO.Position;

        System.out.println(" Accessoire Arriere: " + accessoireArriere.size());

        triangles = genererUnMurFini(position, dimension, hauteur, epaisseur, accessoireArriere.toArray(new AccessoireDTO[0]),  origineHauteur);
        suffixe = "_A";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

//        System.out.println("Arriere : " + dimension );
        //Gauche
        MurDTO murGauche = pChaletDTO.MurGaucheDTO;
        dimension = murGauche.Dimension.getLargeur();
        //position = pChaletDTO.MurGaucheDTO.Position;
        triangles = genererUnMurFini(position, dimension, hauteur, epaisseur, accessoireGauche.toArray(new AccessoireDTO[0]), origineHauteur);
        suffixe = "_G";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

//        System.out.println("Gauche : " + dimension );
        //Droite
        MurDTO murDroite = pChaletDTO.MurDroiteDTO;
        dimension = murDroite.Dimension.getLargeur();
        //position = pChaletDTO.MurDroiteDTO.Position;
        triangles = genererUnMurFini(position, dimension, hauteur, epaisseur, accessoireDroite.toArray(new AccessoireDTO[0]),  origineHauteur);
        suffixe = "_D";
        ExporteurSTL.genererFichierSTL(triangles, cheminDestination, suffixe, type);
        Arrays.fill(triangles, null);

//        System.out.println("Droite : " + dimension );
    }

    public static void genererToitSTL(String cheminDestination, ChaletDTO pChaletDTO) throws IOException {
        // Logique de génération du contenu STL pour le toit  fini
        //pignons: Gauche et droite
        //Rallonge
        //Toiture
        String chemin = cheminDestination + "Fini_P_G.stl";
        generateSTL(chemin, 0.1);
        String chemin2 = cheminDestination + "Fini_P_D.stl";
        generateSTL(chemin2, 0.1);

    }

    private static Triangle[] genererUnMurFini(PointSTL pPosition, float pdimension, float hauteur, float pEpaiseur, AccessoireDTO[] accessoires, float origineHauteur) {

        List<Triangle> triangles = new ArrayList<>();

        // Générer le mur brut
        Triangle[] murBrut = GenerateurDeBrut.genererUnMurBrut(pPosition, pdimension, hauteur, pEpaiseur,  origineHauteur);

        System.out.println(accessoires.length);

        System.out.println("Avant: " + triangles.size());
        triangles.addAll(Arrays.asList(murBrut));

        System.out.println("Apres: " + triangles.size());

        // Pour chaque triangle, vérifier s'il est à l'intérieur d'un accessoire
//        Iterator<Triangle> iterator = triangles.iterator();
//        // test
//        int count = 0;
//        while (iterator.hasNext()) {
//            Triangle triangle = iterator.next();
//            for (AccessoireDTO accessoire : accessoires) {
//                    System.out.println("Avant vérification de Triangle ...");
//                if (estDansAccessoire(triangle, accessoire) && count < 3) {
//                    count++;
//                    System.out.println("Triangle dans accessoire. Donc SUPPRESSION");
//                    iterator.remove(); // À tester: je vais peut être ajuster le triangle au lieu de l'enlevé.
//                    break;
//                }
//            }
//        }
            System.out.println("Avant soustration accessoire: " + triangles);
        
        for (AccessoireDTO accessoire : accessoires) {
            PointSTL positionAccessoire = new PointSTL(accessoire.Position.getX(), accessoire.Position.getY());
            
            List<Triangle> trianglesAccessoire = Arrays.asList(GenerateurDeBrut.genererUnMurBrut(positionAccessoire, 
                                                                                   accessoire.Dimension.getLargeur(),
                                                                                   accessoire.Dimension.getHauteur(),
                                                                                   pEpaiseur,  origineHauteur));
            System.out.println("Accessoire: " + triangles);
            
            triangles.removeAll(trianglesAccessoire);
        }
            System.out.println("Après soustration accessoire: " + triangles);

        return triangles.toArray(new Triangle[0]);
    }

    private static boolean estDansAccessoire(Triangle triangle, AccessoireDTO accessoire) {
        // vérification si un triangle est à l'intérieur d'un accessoire.

        Point3D p1 = triangle.A;
        Point3D p2 = triangle.B;
        Point3D p3 = triangle.C;

        Rectangle accessoireRect = new Rectangle(
                (int) accessoire.Position.getX(),
                (int) accessoire.Position.getY(),
                (int) accessoire.Dimension.getLargeur(),
                (int) accessoire.Dimension.getHauteur()
        );

        Area triangleArea = new Area();
        triangleArea.add(new Area(new java.awt.Polygon(
                new int[]{(int) p1.x, (int) p2.x, (int) p3.x},
                new int[]{(int) p1.y, (int) p2.y, (int) p3.y},
                3
        )));

        Area accessoireArea = new Area(accessoireRect);

        // Vérifier si au moins un point du triangle est à l'intérieur de l'accessoire
        triangleArea.intersect(accessoireArea);

        return !triangleArea.isEmpty();
    }

    // À revoir pour le cas ou j'ai seulement une partie du triangle à l'intérieur d'un accessoire
    private static boolean triangleIntersecteRectangle(Point3D p1, Point3D p2, Rectangle rect) {
        // Utiliser la méthode intersects de Java pour vérifier l'intersection
        boolean estEnIntersection = false;

        estEnIntersection = rect.intersectsLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);

        if (estEnIntersection) {

            System.out.println("Un point du triangle touche l'accessoire" + estEnIntersection);
        }

        return estEnIntersection;
    }
    
    
    
    public static void generateSTL(String filename, double thickness) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        // STL header
        writer.write("solid triangle\n");

        // Triangle vertices
        double[][] vertices = {
                {0.0, 0.0, 0.0},  // Vertex 1 (x, y, z)
                {1.0, 0.0, 0.0},  // Vertex 2
                {0.5, 1.0, 0.0}   // Vertex 3
        };

        // Surface normal (assuming counter-clockwise vertex order)
        double[] normal = calculateNormal(vertices);

        // Triangle face
        writer.write("  facet normal " + normal[0] + " " + normal[1] + " " + normal[2] + "\n");
        writer.write("    outer loop\n");

        // Vertex 1
        writer.write("      vertex " + vertices[0][0] + " " + vertices[0][1] + " " + vertices[0][2] + "\n");

        // Vertex 2
        writer.write("      vertex " + vertices[1][0] + " " + vertices[1][1] + " " + vertices[1][2] + "\n");

        // Vertex 3
        writer.write("      vertex " + vertices[2][0] + " " + vertices[2][1] + " " + vertices[2][2] + "\n");

        writer.write("    endloop\n");
        writer.write("  endfacet\n");

        // Create the bottom face of the triangle with thickness
        writer.write("  facet normal " + (-normal[0]) + " " + (-normal[1]) + " " + (-normal[2]) + "\n");
        writer.write("    outer loop\n");

        // Vertex 1 (bottom)
        writer.write("      vertex " + vertices[0][0] + " " + vertices[0][1] + " " + (vertices[0][2] - thickness) + "\n");

        // Vertex 2 (bottom)
        writer.write("      vertex " + vertices[1][0] + " " + vertices[1][1] + " " + (vertices[1][2] - thickness) + "\n");

        // Vertex 3 (bottom)
        writer.write("      vertex " + vertices[2][0] + " " + vertices[2][1] + " " + (vertices[2][2] - thickness) + "\n");

        writer.write("    endloop\n");
        writer.write("  endfacet\n");

        // Connect the top and bottom faces
        writer.write("  facet normal " + normal[0] + " " + normal[1] + " " + normal[2] + "\n");
        writer.write("    outer loop\n");

        // Vertex 1 (top)
        writer.write("      vertex " + vertices[0][0] + " " + vertices[0][1] + " " + vertices[0][2] + "\n");

        // Vertex 2 (top)
        writer.write("      vertex " + vertices[1][0] + " " + vertices[1][1] + " " + vertices[1][2] + "\n");

        // Vertex 1 (bottom)
        writer.write("      vertex " + vertices[0][0] + " " + vertices[0][1] + " " + (vertices[0][2] - thickness) + "\n");

        writer.write("    endloop\n");
        writer.write("  endfacet\n");

        // Vertex 2 (top)
        writer.write("  facet normal " + normal[0] + " " + normal[1] + " " + normal[2] + "\n");
        writer.write("    outer loop\n");

        // Vertex 2 (top)
        writer.write("      vertex " + vertices[1][0] + " " + vertices[1][1] + " " + vertices[1][2] + "\n");

        // Vertex 3 (top)
        writer.write("      vertex " + vertices[2][0] + " " + vertices[2][1] + " " + vertices[2][2] + "\n");

        // Vertex 2 (bottom)
        writer.write("      vertex " + vertices[1][0] + " " + vertices[1][1] + " " + (vertices[1][2] - thickness) + "\n");

        writer.write("    endloop\n");
        writer.write("  endfacet\n");

        // Vertex 3 (top)
        writer.write("  facet normal " + normal[0] + " " + normal[1] + " " + normal[2] + "\n");
        writer.write("    outer loop\n");

        // Vertex 3 (top)
        writer.write("      vertex " + vertices[2][0] + " " + vertices[2][1] + " " + vertices[2][2] + "\n");

        // Vertex 1 (top)
        writer.write("      vertex " + vertices[0][0] + " " + vertices[0][1] + " " + vertices[0][2] + "\n");

        // Vertex 3 (bottom)
        writer.write("      vertex " + vertices[2][0] + " " + vertices[2][1] + " " + (vertices[2][2] - thickness) + "\n");

        writer.write("    endloop\n");
        writer.write("  endfacet\n");

        // STL footer
        writer.write("endsolid triangle\n");

        writer.close();
    }
    private static double[] calculateNormal(double[][] vertices) {
        // Calculate cross product of two vectors formed by the vertices
        double[] vector1 = {vertices[1][0] - vertices[0][0], vertices[1][1] - vertices[0][1], vertices[1][2] - vertices[0][2]};
        double[] vector2 = {vertices[2][0] - vertices[0][0], vertices[2][1] - vertices[0][1], vertices[2][2] - vertices[0][2]};

        double normalX = vector1[1] * vector2[2] - vector1[2] * vector2[1];
        double normalY = vector1[2] * vector2[0] - vector1[0] * vector2[2];
        double normalZ = vector1[0] * vector2[1] - vector1[1] * vector2[0];

        // Normalize the normal vector
        double length = Math.sqrt(normalX * normalX + normalY * normalY + normalZ * normalZ);
        return new double[]{normalX / length, normalY / length, normalZ / length};
    }

}
