/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.domaine.Chalet;

import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import java.io.Serializable;

/**
 * Permet de créér un accessoire DTO à partir d'un accessoire du domaine
 *
 */
public class AccessoireMapper implements Serializable{

    public AccessoireDTO mapVersAccessoireDTO(Accessoire accessoire) {
        AccessoireDTO accessoireDTO = new AccessoireDTO(accessoire);
        return accessoireDTO;
    }
    
    /**
     * Fais la mise à jour des DTO 
     * @param accessoire
     * @param accessoireDTO 
     */
    public void mettreAjourAccessoireDTO(Accessoire accessoire, AccessoireDTO accessoireDTO) {

        accessoireDTO.setPosition(accessoire.getPosition());
        accessoireDTO.setDimension(accessoire.getDimension());
        accessoireDTO.setCouleurAccessoire(accessoire.getColor());
        accessoireDTO.setTypeAccessoire(accessoire.getTypeAccessoire());
        accessoireDTO.setAccessoireValide(accessoire.estAccessoireValide());
        accessoireDTO.setSelectionStatus(accessoire.isSelected());
        accessoireDTO.setLabelAccessoire(accessoire.getAccessoireLabel());

    }

}
