/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.ExportSTL;

import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.utils.Point3D;
import ca.ulaval.glo2004.domaine.utils.Triangle;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Reagan
 */
public class ExporteurSTL {
    
    private static ChaletDTO chalet;
    private static String cheminProjet;
    
    public static void exporterSTL(ChaletDTO pChaletDTO, String chemin ){
        chalet = pChaletDTO;
        cheminProjet = chemin;        
        
        
        exporterPanneauBrutSTL(cheminProjet, chalet);
        //exporterPanneauFiniSTL(cheminProjet, chalet);
        //exporterRetraitSTL(cheminProjet, chalet);
    }
    
    public static void exporterPanneauBrutSTL(String cheminDestination, ChaletDTO pChaletDTO) {
        // Appel à Générateur de Brut pour générer et exporter les panneaux bruts
        
        GenerateurDeBrut.genererEtExporterBrut(cheminDestination, pChaletDTO);
    }

    public static void exporterPanneauFiniSTL(String cheminDestination, ChaletDTO pChaletDTO) throws IOException {
        // Appel à Générateur de Fini pour générer et exporter les panneaux finis
        GenerateurDeFinis.genererEtExporterFini(cheminDestination, pChaletDTO);
    }

    public static void exporterRetraitSTL(String cheminDestination, ChaletDTO pChaletDTO) {
        // Appel à PanneauSTLModel pour générer et exporter le retrait virtuel
        GenerateurDeRetraits.genererEtExporterRetraits(cheminDestination, pChaletDTO);
    }
    
    public static void genererFichierSTL(Triangle[] triangles, String cheminDestination, String suffixe, String type) {

        String extension = "stl";
         //System.out.println(" Chemin: " + cheminDestination);
        cheminDestination += type;
        cheminDestination += suffixe;

        if (!cheminDestination.toLowerCase().endsWith("." + extension)) {
            // Ajouter l'extension à la fin du nom du fichier
            cheminDestination += "." + extension;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(cheminDestination))) {
            writer.println("solid Mur");

            for (Triangle triangle : triangles) {
                
                //Préparation de la normale
                Point3D AB = new Point3D(triangle.B.x - triangle.A.x, triangle.B.y - triangle.A.y, triangle.B.z - triangle.A.z);
                Point3D AC = new Point3D(triangle.C.x - triangle.A.x, triangle.C.y - triangle.A.y, triangle.C.z - triangle.A.z);

                    // Produit vectoriel
                Point3D normal = new Point3D(
                        AB.y * AC.z - AB.z * AC.y,
                        AB.z * AC.x - AB.x * AC.z,
                        AB.x * AC.y - AB.y * AC.x
                );

                // Normalisation de la normale
                normal.normalize();

                writer.println("  facet normal " + normal.x + " " + normal.y + " " + normal.z);
                writer.println("    outer loop");

                Point3D[] vertices = {triangle.A, triangle.B, triangle.C};
                for (Point3D vertex : vertices) {
                    writer.println("      vertex " + vertex.x + " " + vertex.y + " " + vertex.z);
                }

                writer.println("    endloop");
                writer.println("  endfacet");
            }

            writer.println("endsolid Mur");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
