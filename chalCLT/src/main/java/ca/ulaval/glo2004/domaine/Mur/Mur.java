package ca.ulaval.glo2004.domaine.Mur;
 

import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.*;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import static ca.ulaval.glo2004.domaine.utils.Orientation.ARRIERE;
import static ca.ulaval.glo2004.domaine.utils.Orientation.DROITE;
import static ca.ulaval.glo2004.domaine.utils.Orientation.FACADE;
import static ca.ulaval.glo2004.domaine.utils.Orientation.GAUCHE;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.awt.Rectangle;
import java.awt.*;
import java.io.Serializable;
 
public class Mur implements Serializable{
 
    private Orientation pOrientation;
    private PointDecimal mPosition;
    private Dimension mDimension;
    private List<Accessoire> mAccessoiresList;
    public UUID uuid;
    private String mLabelMur;
    private double retrait;
    private double epaisseur;
 
    public Mur(Orientation mOrientation, Dimension mDimension) {
        this.pOrientation = mOrientation;
        this.mDimension = mDimension;
        this.mAccessoiresList = new ArrayList<>();
        setPosition(mOrientation);
        this.retrait = calculerRetrait();
        this.epaisseur = epaisseur;
    }

    public double getRetrait() {
        return retrait;
    }
 
    public UUID getUuid() {
        return uuid;
    }
 
    public float getLargeur() {
 
        return mDimension.getLargeur();
    }
 
    public float getLongeur() {
        return mDimension.getLongueur();
    }
 
    public float getHauteur() {
        return mDimension.getHauteur();
    }
    public Dimension getMurDimension(){
        return this.mDimension;
    }
    public void setMurDimension(Dimension dimension){
        this.mDimension = dimension;
    }
    public void setEpaisseur(float newEpaisseur) {
        this.epaisseur = newEpaisseur;
    }
    public double getEpaisseur() {
        return epaisseur;
    }
    public Orientation getOrientation() {
        return pOrientation;
    }
    @Override
    public String toString(){
        switch (pOrientation) {
            case ARRIERE:
                mLabelMur = "ARRIERE";
                break;
            case FACADE:
                mLabelMur = "FACADE";
                break;
            case GAUCHE:
                mLabelMur = "GAUCHE";
                break;
            case DROITE:
                mLabelMur = "DROITE";
                break;
        }
        return mLabelMur;
    }
 
    public String getLabelMur() {        
       return toString();
    }    

     public PointDecimal getPosition(){
     return mPosition;
     }

    public void setPosition(Orientation pOrientationMur){     
        PositionMur positionMur = new PositionMur();                        
        switch (pOrientation) {
            case ARRIERE:
                mPosition =  new PointDecimal(positionMur.PositionXMurFacadeArriere, positionMur.PositionYMurFacadeArriere );
                break;
            case FACADE:
                 mPosition =  new PointDecimal(positionMur.PositionXMurFacadeArriere, positionMur.PositionYMurFacadeArriere );
                break;
            case GAUCHE:
                 mPosition =  new PointDecimal(positionMur.PositionXMurGaucheDroit, positionMur.PositionYMurGaucheDroit );
                break;
            case DROITE:
                mPosition =  new PointDecimal(positionMur.PositionXMurGaucheDroit, positionMur.PositionYMurGaucheDroit );
                break;            
        }
    }

    /**
     *
     * @return la liste d'accessoire dans un mur
     */
    public List<Accessoire> getAccessoiresList() {
        return mAccessoiresList;
    }
 
 
 
    /**
     *Ajoute un accessoire a une liste, en tenantcompte de sa position,
     * de ses dimensions et de son type. La méthode vérifie si la position est valide pour
     * le type et les dimensions de l'accessoire avant de les ajouter.Elle attribue également
     * un libellé a l'accessoire en fonction de son type et du nombre d'accessoire de ce type déja ajoutés.
     * 
     * @param pPosition La position ou l'accessoire sera placé.
     * @param pDimension Les dimensions de l'accessoire.
     * @param pType Le type de l'accessoire 
     */
    public void ajouterAccessoire(PointDecimal pPosition, Dimension pDimension, TypeAccessoire pType) {
        // valide la position de l'accessoire en utilisant les dimensions et le type donné.
        boolean accessoireValide = positionValide(pPosition, pDimension, pType);
        String accessoireLabel;
        if (pType == TypeAccessoire.FENETRE){
            accessoireLabel = "FENETRE " + (tailleListTypeAccessoire(TypeAccessoire.FENETRE) + 1) ;
        }else{
            accessoireLabel = "PORTE " + (tailleListTypeAccessoire(TypeAccessoire.PORTE) + 1) ;
        }
        Accessoire accessoire = new Accessoire(pPosition, pDimension, pType, accessoireValide,accessoireLabel);
        mAccessoiresList.add(accessoire);
    }
 
    public int tailleListTypeAccessoire(TypeAccessoire accessoireType){
        int i = 0;
        for(Accessoire accessoire : this.mAccessoiresList){
            if(accessoire.getTypeAccessoire() == accessoireType){
                i++;
            }
        }
        return i;
    }
    /**
     *Supprime un accessoire de la liste des accessoires a l'index spécifié.
     * 
     * @param index L'index de l'accessoire a supprimer dans la liste des accessoires.
     */
    public void supprimerAccessoire(int index) {
        mAccessoiresList.remove(index);
        reverifierPositionSuppresion();
    }
   
    /**
     * Deplace un accessoire existant vers une nouvelle position sur le mur.
     * La validité de la nouvelle position est vérifiée avant le déplacement.
     * 
     * @param pPosition La nouvelle position souhaitée pour l,accessoire.
     * @param pAccessoire L'accessoire a deplacer.
     */
    public void deplacerAccessoire(PointDecimal pPosition, Accessoire pAccessoire) {
        pAccessoire.setPosition(pPosition);
        boolean positionValide = positionValideDeplacement(pAccessoire);
        pAccessoire.setValidite(positionValide);
        reverifierPositionDeplacement();
        if(!isAccessoireOutsideMur(pAccessoire)){
            
            pAccessoire.setColorRed(Color.RED);
        }
        if(isThreeDistanceFromAccesoire(pAccessoire) ==  true){
            pAccessoire.setColorRed(Color.RED);
        }
        
    }
    public boolean isThreeDistanceFromAccesoire(Accessoire accessoireAdeplacer){
        
        int distanceEntreAccessoire = 3;
        for (Accessoire accessoireOld : mAccessoiresList) {
            if(accessoireAdeplacer.getUuid() != accessoireOld.getUuid()){
            
                Rectangle rectangleAdeplacer = new Rectangle((int)accessoireAdeplacer.getPosition().getX() - distanceEntreAccessoire,(int)accessoireAdeplacer.getPosition().getY() - distanceEntreAccessoire,(int)accessoireAdeplacer.getDimension().getLargeur(),(int)accessoireAdeplacer.getDimension().getHauteur());
                Rectangle rectangleOld = new Rectangle((int)accessoireOld.getPosition().getX(),(int)accessoireOld.getPosition().getY(),(int)accessoireOld.getDimension().getLargeur(),(int)accessoireOld.getDimension().getHauteur());
                if (rectangleOld.intersects(rectangleAdeplacer)) {
                    return true;
                }
            }
        }
        return false;
    }
   /**
    * Modifie les caractéristiques d'un accessoire deja existant, comme sa position et ses dimensions.
    * Avant de procéder a la modification, la méthode vérifie si la nouvelle position est valide.
    * 
    * @param pPosition La nouvelle position pour l'accessoire.
    * @param pDimension Les nouvelles dimensions pour l'accessoire.
    * @param pAccessoire L'accessoire a modifier.
    */
    public void modifierDeplacerAccessoire(PointDecimal pPosition, Dimension pDimension,  Accessoire pAccessoire) {
        pAccessoire.setPosition(pPosition);
        pAccessoire.setDimension(pDimension); 
        boolean positionValide = positionValideDeplacement(pAccessoire);
        pAccessoire.setValidite(positionValide);
        reverifierPositionDeplacement();
        if(isThreeDistanceFromAccesoire(pAccessoire) ==  true){
            pAccessoire.setColorRed(Color.RED);
        }
    }
    public void modifierAccessoire(Dimension pDimension, Accessoire pAccessoire){
        pAccessoire.setDimension(pDimension); 
        boolean positionValide = positionValideDeplacement(pAccessoire);
        pAccessoire.setValidite(positionValide);
        reverifierPositionDeplacement();
        if(isThreeDistanceFromAccesoire(pAccessoire) ==  true){
            pAccessoire.setColorRed(Color.RED);
        }
    }
    /**
     * Reverification d'accessoires en cas de suppresion/modification d'un accessoire
     *
     * @param pPosition
     * @param pDimension
     * @param pAccessoire
     */
    public void reverifierPositionDeplacement() {
        for (Accessoire accessoire : mAccessoiresList) {
            if(!accessoire.estAccessoireValide()){
                for(Accessoire tempAccessoire : mAccessoiresList){
                    if(accessoire.getUuid() != tempAccessoire.getUuid()){
                        boolean positionValide = positionValideDeplacement(accessoire);
                        accessoire.setValidite(positionValide);
                    }
                }
            }

        }
    }
    
    public void reverifierPositionSuppresion() {
        if(mAccessoiresList.size() == 1){
            for(Accessoire accessoire : mAccessoiresList){
                boolean positionValide = positionValideDeplacement(accessoire);
                accessoire.setValidite(positionValide);        
            }
        }
        if(mAccessoiresList.size() > 1){
            for (Accessoire accessoire : mAccessoiresList) {
                if(!accessoire.estAccessoireValide()){
                    for(Accessoire tempAccessoire : mAccessoiresList){
                        if(accessoire.getUuid() != tempAccessoire.getUuid()){
                            boolean positionValide = positionValideDeplacement(accessoire);
                            accessoire.setValidite(positionValide);
                        }
                    }
                }

            }
        }
    }
 
    
    /**
     *
     * @param pPosition d'ajout d'accessoire
     * @param pDimension de l'accessoire
     * @param pType de laccessoire
     * @return
     */
 
    public boolean positionValide(PointDecimal pPosition, Dimension pDimension, TypeAccessoire pType) {
 
        if (TypeAccessoire.FENETRE == pType) {
            return verifierSuperposition(pPosition, pDimension) && verifierPourtour(pPosition, pDimension);
 
        } else {
            return verifierSuperposition(pPosition, pDimension) && verifierPourtour(pPosition, pDimension);
        }
    }
    public boolean positionValideDeplacement(Accessoire accessoire) {
 
        if (accessoire.getTypeAccessoire() == TypeAccessoire.FENETRE) {
            return verifierSuperpositionDeplacer(accessoire) && verifierPourtourDeplacement(accessoire);
 
        } else {
            return verifierSuperpositionDeplacer(accessoire) && verifierPourtourDeplacement(accessoire);
        }
    }
 
    /**
     * Validation du pourtour entre le mur et les accessoires
     *
     * @param pPosition d'ajout d'accessoire
     * @param pDimension de l'accessoire
     * @return
     */
    public boolean verifierPourtour(PointDecimal pPosition, Dimension pDimension) {
        // Les commentaires de cette fonction sont importantes pour la compréhension.
 
        double distanceMin = 3; // A enlever si on n'a la bonne reference
        boolean pourtourMurValide = true;
        boolean pourtourAccessoireValide = false;
        boolean pourtourValide = false;
 
        //Le point représentant le mur: M(x, y);
        double Mx = mPosition.getX();
        double My = mPosition.getY();
 
        //  - le point représentant le nouvel accessoire: A(x, y);
        double Ax = pPosition.getX();
        double Ay = pPosition.getY();
 
        //1. Vérification du pourtour avec le Mur:
        if (!verifierSuperposition(pPosition, pDimension)) {
            
            return pourtourValide;
        } else {
 
            boolean correctEnX = Math.abs(Ax - Mx) > distanceMin;
            boolean correctEnY = Math.abs(Ay - My) > distanceMin;
 
            pourtourMurValide = correctEnX && correctEnY;
        }
 
        //2. Vérification du pourtour avec les Accessoires:
        if (mAccessoiresList.isEmpty()) {
            pourtourAccessoireValide = true;
        } else {
            for (Accessoire accessoire : mAccessoiresList) {
                double posX = accessoire.getPosition().getX();
                double posY = accessoire.getPosition().getY();
                double largeur = accessoire.getDimension().getLargeur();
                double hauteur = accessoire.getDimension().getHauteur();
 
                // En x:
                double distanceEnx = Math.abs(Ax - (posX + largeur));
                // En y:
                double distanceEny = Math.abs(Ay - (posY + hauteur));
 
                if ((distanceEnx > distanceMin) || (distanceEny > distanceMin)) {
                    pourtourAccessoireValide = true;
                }
 
            }
 
        }
 
        pourtourValide = pourtourMurValide && pourtourAccessoireValide;
        
        return pourtourValide;
    }
    public boolean verifierPourtourDeplacement(Accessoire accessoire) {
        // Les commentaires de cette fonction sont importantes pour la compréhension.
 
        double distanceMin = 3; // A enlever si on n'a la bonne reference
        boolean pourtourMurValide = true;
        boolean pourtourAccessoireValide = false;
        boolean pourtourValide = false;
 
        //Le point représentant le mur: M(x, y);
        double Mx = mPosition.getX();
        double My = mPosition.getY();
 
        //  - le point représentant le nouvel accessoire: A(x, y);
        double Ax = accessoire.getPosition().getX();
        double Ay = accessoire.getPosition().getY();
 
        //1. Vérification du pourtour avec le Mur:
        if (!verifierSuperpositionDeplacer(accessoire)) {
            return pourtourValide;
        } else {
 
            boolean correctEnX = Math.abs(Ax - Mx) > distanceMin;
            boolean correctEnY = Math.abs(Ay - My) > distanceMin;
 
            pourtourMurValide = correctEnX && correctEnY;
        }
 
        //2. Vérification du pourtour avec les Accessoires:
        if (mAccessoiresList.isEmpty()) {
            pourtourAccessoireValide = true;
        } else {
            for (Accessoire accessoiretemp : mAccessoiresList) {
                
                double posX = accessoiretemp.getPosition().getX();
                double posY = accessoiretemp.getPosition().getY();
                double largeur = accessoiretemp.getDimension().getLargeur();
                double hauteur = accessoiretemp.getDimension().getHauteur();
 
                // En x:
                double distanceEnx = Math.abs(Ax - (posX + largeur));
                // En y:
                double distanceEny = Math.abs(Ay - (posY + hauteur));
 
                if ((distanceEnx > distanceMin) || (distanceEny > distanceMin)) {
                    pourtourAccessoireValide = true;
                }
 
            }
 
        }
 
        pourtourValide = pourtourMurValide && pourtourAccessoireValide;
 
        return pourtourValide;
    }
 
    /**
     *
     * @param pPosition
     * @param pDimension
     * @return vrai s'il n'y a pas de superposition
     */
 
    private boolean verifierSuperposition(PointDecimal pPosition, Dimension pDimension) {
        boolean superPositionValide = true;
 
        //creer un rectangle repesentant le nouvel accessoire et verifier l'intersection pour chaque rectabgle issu
        // des accesoires presents dans le mur
        Rectangle newAccessoireRect = new Rectangle( (int)pPosition.getX(), (int)pPosition.getY(),
                (int)pDimension.getLargeur(), (int)pDimension.getHauteur());
 
        if (mAccessoiresList.isEmpty()) {
            return superPositionValide;
        } else {
            for (Accessoire accessoire : mAccessoiresList) {
                float posX =  accessoire.getPosition().getX();
                float posY =  accessoire.getPosition().getY();
                float largeur = accessoire.getDimension().getLargeur();
                float hauteur = accessoire.getDimension().getHauteur();
 
                Rectangle oldAccessoireRect = new Rectangle((int)posX, (int)posY, (int)largeur, (int)hauteur);
 
                if (newAccessoireRect.intersects(oldAccessoireRect)) {
                    superPositionValide = false;
                    break;
                }
 
            }
 
        }
 
        return superPositionValide;
    }
    public boolean isAccessoireOutsideMur(Accessoire acc) {
        int distanceAvecMur = 3;
        if(this.pOrientation == Orientation.ARRIERE || this.pOrientation == Orientation.FACADE){
            Rectangle rectMur = new Rectangle((int)this.mPosition.getX(),(int)this.mPosition.getY(),(int)this.mDimension.getLongueur() - ((int)this.epaisseur + distanceAvecMur),(int)this.mDimension.getHauteur() - (int)distanceAvecMur);
            Rectangle accessoire = new Rectangle((int)acc.getPosition().getX(),(int)acc.getPosition().getY(),(int)acc.getDimension().getLargeur(),(int)acc.getDimension().getHauteur());
            if(rectMur.contains(accessoire)){
                return true;
            }
        }
        if(this.pOrientation == Orientation.DROITE || this.pOrientation == Orientation.GAUCHE){
            Rectangle rectMur = new Rectangle((int)this.mPosition.getX(),(int)this.mPosition.getY(),(int)this.mDimension.getLargeur()- (int)distanceAvecMur,(int)this.mDimension.getHauteur() - (int)distanceAvecMur);
            Rectangle accessoire = new Rectangle((int)acc.getPosition().getX(),(int)acc.getPosition().getY(),(int)acc.getDimension().getLargeur(),(int)acc.getDimension().getHauteur());
            if(rectMur.contains(accessoire)){
                return true;
            }
        }
        return false;
        
    }

    private boolean verifierSuperpositionDeplacer(Accessoire accessoireDeplacer) {
        boolean superPositionValide = true;
 
        //creer un rectangle repesentant le nouvel accessoire et verifier l'intersection pour chaque rectabgle issu
        // des accesoires presents dans le mur
        Rectangle newAccessoireRect = new Rectangle( (int)accessoireDeplacer.getPosition().getX(), (int)accessoireDeplacer.getPosition().getY(),
                (int)accessoireDeplacer.getDimension().getLargeur(), (int)accessoireDeplacer.getDimension().getHauteur());
 
        if (mAccessoiresList.isEmpty()) {
            return superPositionValide;
        } else {
            for (Accessoire accessoire : mAccessoiresList) {
                if(accessoireDeplacer.getUuid() != accessoire.getUuid()){
                    float posX =  accessoire.getPosition().getX();
                    float posY =  accessoire.getPosition().getY();
                    float largeur = accessoire.getDimension().getLargeur();
                    float hauteur = accessoire.getDimension().getHauteur();
 
                    Rectangle oldAccessoireRect = new Rectangle((int)posX, (int)posY, (int)largeur, (int)hauteur);
 
                    if (newAccessoireRect.intersects(oldAccessoireRect)) {
                        superPositionValide = false;
                        break;
                    }
                }
            }
 
        }
        return superPositionValide;
    }
 
    /**
     * Change le status de selection d'un accessoire
     *
     * @param x coordonnee de la souris en x
     * @param y coordonnee de la souris en y
     */
    public void switchSelectionStatus(double x, double y) {
        for (Accessoire accessoire : mAccessoiresList) {
            if (accessoire.contains(x, y)) {
                accessoire.switchSelection();
            }
        }
    }
    /**
     * Calcule le retrait necessaire en fonction de l'epaisseur du mur.
     * Le retrait est la quantité a soustraire ou a ajouter pour ajuster la dimension de la 
     * matiere en raison de l'expansion ou de la contraction,m ou d'autres facteurs de conception.
     * 
     * @return Le retrait calculé en tant que valeur double.
     */
    private double calculerRetrait() {
      // Coefficient pour la distance supplémentaire de matière
      double coefficientRetrait = 0.1; // TODO ajuster ce coefficient selon les besoins
 
      // Calcul du retrait en fonction de l'épaisseur du mur
      double retrait = epaisseur / 2;
 
      // Ajoutez la distance supplémentaire de matière
      retrait += coefficientRetrait * epaisseur;
 
      return retrait;
  }

    /**
     *Valide si une liste de murs peut former une structure rectangulaire fermée en vérifiant que les dimensions de 
     * hauteur,largeur et epaisseur des murs opposés sont égales.
     * 
     * @param murs La liste des murs a valider,représentant les 4 cotés d'une structure.
     * @return Vrai si les positions et dimensions des murs sont valides pour former une structure rectangulaire fermée.
     */
    public boolean ValiderPosition(List<Mur> murs) {
      if (murs.size() != 4) {
          // Il devrait y avoir exactement quatre murs (Façade, Arrière, Gauche, Droite)
          return false;
      }
 
      // Récupération des dimensions des murs pour la facade,l'arriere,et lescotés gauche et droite. 
      double hauteurFaçade = murs.get(0).getHauteur();
      double largeurFaçade = murs.get(0).getLargeur();
      double epaisseurFaçade = murs.get(0).getEpaisseur();
 
      double hauteurArrière = murs.get(1).getHauteur();
      double largeurArrière = murs.get(1).getLargeur();
       double epaisseurArrière = murs.get(1).getEpaisseur();
 
      double hauteurGauche = murs.get(2).getHauteur();
      double largeurGauche = murs.get(2).getLargeur();
      double epaisseurGauche = murs.get(2).getEpaisseur();
 
      double hauteurDroite = murs.get(3).getHauteur();
      double largeurDroite = murs.get(3).getLargeur();
       double epaisseurDroite = murs.get(3).getEpaisseur();
 
      // Vérification que les murs opposés ont des dimensions identiques pour permettre un emboitement parfait.
      //Les hauteurs des murs de facade et arriere doivent etre égales, tout comme leurs largeurs et epaisseurs.
      //Il en va de la meme pour les murs de coté gauche et droit.
      if (hauteurFaçade != hauteurArrière || largeurFaçade != largeurArrière ||
          epaisseurFaçade != epaisseurArrière || hauteurGauche != hauteurDroite ||
          largeurGauche != largeurDroite || epaisseurGauche != epaisseurDroite) {
          return false; // Si une vérification échoue,les dimensions ne permettent pas l'emboitement
      }
 
 
      return true; // Les murs s'emboîtent correctement
  }
    
    
    public void miseAJourDepuisDTO(MurDTO murDTO) {
        this.pOrientation = murDTO.Orientation;
        this.mPosition = murDTO.Position;
        this.mDimension = murDTO.Dimension;
        this.uuid = murDTO.Uuid;
        this.mLabelMur = murDTO.LabelMur;
        this.retrait = murDTO.Retrait; // En attendant
        this.epaisseur = murDTO.Epaisseur; // En attendant

        // Mise à jour des accessoires
        mAccessoiresList.clear();
        for (AccessoireDTO accessoireDTO : murDTO.AccessoiresList) {
            Accessoire accessoire = new Accessoire(accessoireDTO);
            mAccessoiresList.add(accessoire);
        }
    }
    
    
    /**
     * Calcule la surface totale d'un objet tridimensionnel.
     * 
     * @return la surface totale de l'objet
     */
    public double calculateSurfaceArea() {
        return 2 * (mDimension.getHauteur() * mDimension.getLargeur() + mDimension.getHauteur() * epaisseur + mDimension.getLargeur() * epaisseur);
    }

    /**
     * Calculates the volume of the wall.
     *
     * @return The volume of the wall.
     */
    public double calculateVolume() {
        return mDimension.getHauteur() * mDimension.getLargeur() * epaisseur;
    }

    /**
     * Calculates the total area occupied by accessories of a specific type on the wall.
     *
     * @param type The type of accessory.
     * @return The total area occupied by accessories of the specified type.
     */
    public double getAreaOfAccessoryType(TypeAccessoire type) {
        double area = 0;
        for (Accessoire accessory : mAccessoiresList) {
            if (accessory.getTypeAccessoire() == type) {
                area += accessory.getDimension().getHauteur() * accessory.getDimension().getLargeur();
            }
        }
        return area;
    }
    
    /**
     * Checks if the wall contains a specific accessory.
     *
     * @param accessory The accessory to check.
     * @return True if the wall contains the accessory, false otherwise.
     */
    public boolean containsAccessory(Accessoire accessory) {
        return mAccessoiresList.contains(accessory);
    }
    
    /**
     * Calculates the total area of doors and windows on the wall.
     *
     * @return The total area of doors and windows on the wall.
     */
    public double getTotalAreaOfOpenings() {
        double totalArea = 0;
        for (Accessoire accessory : mAccessoiresList) {
            if (accessory.getTypeAccessoire() == TypeAccessoire.FENETRE || accessory.getTypeAccessoire() == TypeAccessoire.PORTE) {
                totalArea += accessory.getDimension().getHauteur() * accessory.getDimension().getLargeur();
            }
        }
        return totalArea;
    }
}
