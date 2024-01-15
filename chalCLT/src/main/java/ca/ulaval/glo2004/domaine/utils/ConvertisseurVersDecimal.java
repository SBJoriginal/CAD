package ca.ulaval.glo2004.domaine.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConvertisseurVersDecimal implements Serializable{
    
   
    public int poucePiedsVersEntier(String userString){
        userString = userString.replaceAll("\\s", "");
        String[] pouceValue = userString.split("\"");
        Float pouce = Float.valueOf(pouceValue[0]);
        
        String pieds;
        if (userString.indexOf("\"") != userString.lastIndexOf("\""))
        {
            throw new IllegalArgumentException("Format invalide: plus que un guillemet double ");
        }
        try
        {
            pieds = pouceValue[1];
        }
        catch (ArrayIndexOutOfBoundsException PasDePouce)
        {
            
            pieds = "0/4";
        }
        
        String[] piedsTemp = pieds.split("/");
        Float numerateur = Float.valueOf(piedsTemp[0]);
        Float denominateur = Float.valueOf(piedsTemp[1]);
        Float piedsEnPouce = (numerateur / denominateur) * 12;
        
        return (int) (piedsEnPouce + pouce);
    }

    
   // Méthode pour convertir une chaîne de caractères représentant des pieds et des pouces en une longueur en pouces
public double poucePiedsVersEntierRainure(String userString) {
    // Supprimer les espaces de la chaîne de caractères
    userString = userString.replaceAll("\\s", "");

    // Diviser la chaîne de caractères en pieds et pouces
    String[] pouceValue = userString.split("\"");

    // Analyser les pouces (s'ils sont présents)
    double pouce = 0.0;
    if (pouceValue.length > 1) {
        pouce = parseFraction(pouceValue[1]);
    }

    // Analyser les pieds (défaut à 0 s'ils ne sont pas présents)
    double pieds = 0.0;
    if (pouceValue.length > 0) {
        try {
            pieds = Double.parseDouble(pouceValue[0]);
        } catch (NumberFormatException e) {
            pouce = parseFraction(pouceValue[0]);
        }
    }

    // Convertir les pieds en pouces et les ajouter à la valeur en pouces(Pouce ce fait effacer pour l'instant...
    double totalInches = pieds + pouce; 

    return totalInches;
}

// Méthode auxiliaire pour analyser les pouces fractionnaires
public double parseFraction(String fraction) {
    if (fraction.contains("/")) {
        String[] parts = fraction.split("/");
        if (parts.length == 2) {
            double numerator = Double.parseDouble(parts[0]);
            double denominator = Double.parseDouble(parts[1]);
            return numerator / denominator;
        }
    } else {
        return Double.parseDouble(fraction);
    }
    return 0.0; // Par défaut à 0 en cas d'échec de l'analyse
}



    
    public Dimension piedsVersDecimaux(String userString) {
        List<Float> floatList = new ArrayList<>();
        userString = userString.replaceAll("[^0-9.x]", ""); // Ne concerver que les chiffres, les 'X' et les points décimaux.
        String[] dimensions = userString.split("x");

        for (String dimension : dimensions) {
            floatList.add(Float.valueOf(dimension));
        }

        // En supposant que les dimensions sont dans l'ordre: longueur'X',hauteur 'X',largeur 'X'
        float length = 12 * floatList.get(0);
        float width = 12 * floatList.get(1);
        float height = 12 * floatList.get(2);

        return new Dimension(length, width, height);
    }
    
}
