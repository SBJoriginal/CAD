package ca.ulaval.glo2004.ExportSTL;
import static ca.ulaval.glo2004.ExportSTL.GenerateurDeBrut.genererUnMurBrut;
import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.utils.Point3D;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.Triangle;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.UUID;

/**
 *
 * 
 */
public class GenerateurDeRetraits {

    static void genererEtExporterRetraits(String cheminDestination, ChaletDTO pChaletDTO) {

    // Générer les retraits des murs : Facade, Arriere, Gauche, Droit
     genererRetraitsMurSTL(cheminDestination, pChaletDTO);
     //genererRetraitsToitSTL(cheminDestination, pChaletDTO); livrable 5
    
    }
    
    public static void genererRetraitsMurSTL(String cheminDestination, ChaletDTO pChaletDTO) {
        char suffixe;
        String type = "_Retrait";
        int i = 1;
        for(MurDTO mur : pChaletDTO.MurList){ 
            String murLabel = mur.Orientation.toString();
            suffixe = murLabel.charAt(0);
            int y = 1;
            for(AccessoireDTO accessoire : mur.AccessoiresList){
                String suf = "_"+Character.toString(suffixe) + "_"+i+"_"+y;
                if(accessoire.CouleurAccessoire != Color.RED){
                    
                    exporterAccessoireEnSTL(accessoire, cheminDestination,suf,type, pChaletDTO.EpaisseurMur);                    
                }
                y++;
            }
            
            i++;
        }
    }
     
     public static void genererRetraitsToitSTL(String cheminDestination, ChaletDTO pChaletDTO) {
        // Logique de génération du contenu STL les retraitsle du toit   
          //pignons: Gauche et droite
          //Rallonge
          //Toiture
          
    }
    public static void exporterAccessoireEnSTL(AccessoireDTO accessoire, String cheminDestination,String suffixe, String type, float EpaisseurMur){
         String extension = "stl";
        
        cheminDestination += type;
        cheminDestination += suffixe;

        if (!cheminDestination.toLowerCase().endsWith("." + extension)) {
            
            cheminDestination += "." + extension;
        }
         try {
            PrintWriter writer = new PrintWriter(cheminDestination, "UTF-8");
            writer.println("solid Accessoire");

            float hauteur = accessoire.Dimension.getHauteur(); // Hauteur
            float epaisseurMur = EpaisseurMur/5; // Epaisseur
            float longeur = accessoire.Dimension.getLargeur(); // Longeur
            
            float[][] vertices = {
                    {0, 0, 0},
                    {hauteur, 0, 0},
                    {hauteur, epaisseurMur, 0},
                    {0, epaisseurMur, 0},
                    {0, 0, longeur},
                    {hauteur, 0, longeur},
                    {hauteur, epaisseurMur, longeur},
                    {0, epaisseurMur, longeur}
            };

            // Définir les 12 triangles qui composent le parallépipède rectangulaire
            int[][] faces = {
                    {0, 1, 2},
                    {0, 2, 3},
                    {0, 1, 5},
                    {0, 5, 4},
                    {1, 2, 6},
                    {1, 6, 5},
                    {2, 3, 7},
                    {2, 7, 6},
                    {3, 0, 4},
                    {3, 4, 7},
                    {4, 5, 6},
                    {4, 6, 7}
            };

            // Écrire chaque face dans le fichier STL
            for (int[] face : faces) {
                writer.println("  facet normal 0 0 0");
                writer.println("    outer loop");
                for (int vertex : face) {
                    writer.printf("      vertex %.2f %.2f %.2f\n", vertices[vertex][0], vertices[vertex][1], vertices[vertex][2]);
                }
                writer.println("    endloop");
                writer.println("  endfacet");
            }

            writer.println("endsolid Accessoire");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
     }
   
}