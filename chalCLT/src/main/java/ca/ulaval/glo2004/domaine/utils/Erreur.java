package ca.ulaval.glo2004.domaine.utils;

import java.io.Serializable;

public class Erreur implements Serializable{
    private final String message;
    public Erreur(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
