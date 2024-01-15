package ca.ulaval.glo2004.domaine.Chalet;

import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.DTO.ToitDTO;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.Mur.Mur;
import ca.ulaval.glo2004.domaine.Toit.Toit;
import ca.ulaval.glo2004.domaine.utils.ConvertisseurVersDecimal;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.PositionMur;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import ca.ulaval.glo2004.domaine.utils.TypeAccessoire;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Chalet implements Serializable {

    private Mur pMurArriere;
    private Mur pMurGauche;
    private Mur pMurFacade;
    private Mur pMurDroite;
    private List<Mur> murList;
    private SensToit pSensToit;

    private List<Accessoire> pAccessoireArriere;
    private List<Accessoire> pAccessoireGauche;
    private List<Accessoire> pAccessoireFacade;
    private List<Accessoire> pAccessoireDroite;

    private Dimension pDimensionParDefaut;
    private float pEpaisseurMur;
    private float pRainure;
    public final UUID uuid;
    private Toit toit;

    /**
     * Chalet par default...
     *
     */
    public Chalet() {
        ConvertisseurVersDecimal conver = new ConvertisseurVersDecimal();
        this.pDimensionParDefaut = conver.piedsVersDecimaux("15x10x15"); //this.pDimensionParDefaut = conver.piedsVersDecimaux("25pieds25pieds15pieds"); // Dimension Chalet par defaut
        this.pMurArriere = new Mur(Orientation.ARRIERE, pDimensionParDefaut);
        this.pMurGauche = new Mur(Orientation.GAUCHE, pDimensionParDefaut);
        this.pMurFacade = new Mur(Orientation.FACADE, pDimensionParDefaut);
        this.pMurDroite = new Mur(Orientation.DROITE, pDimensionParDefaut);

        this.pAccessoireArriere = new ArrayList<>();
        this.pAccessoireGauche = new ArrayList<>();
        this.pAccessoireFacade = new ArrayList<>();
        this.pAccessoireDroite = new ArrayList<>();
        this.pEpaisseurMur = conver.poucePiedsVersEntier("4\"1/2"); // L'epaiseur par defaut
        this.pSensToit = SensToit.AVANT_ARRIERE;
        this.pRainure = 0; // Valeur par defaut
        this.uuid = UUID.randomUUID();
        this.toit = new Toit(45,SensToit.AVANT_ARRIERE, this); //45

        PositionMur positionTest = new PositionMur();
        this.murList = new ArrayList<>(Arrays.asList(pMurArriere, pMurGauche, pMurFacade, pMurDroite));

    }

    /**
     * A ajouter Chalet avec parametre
     *
     *
     */

    public Chalet(Dimension dimensions) {
        this.pDimensionParDefaut = dimensions;

        this.pMurArriere = new Mur(Orientation.ARRIERE, dimensions);
        this.pMurGauche = new Mur(Orientation.GAUCHE, dimensions);
        this.pMurFacade = new Mur(Orientation.FACADE, dimensions);
        this.pMurDroite = new Mur(Orientation.DROITE, dimensions);

        this.pAccessoireArriere = new ArrayList<>();
        this.pAccessoireGauche = new ArrayList<>();
        this.pAccessoireFacade = new ArrayList<>();
        this.pAccessoireDroite = new ArrayList<>();
        this.uuid = UUID.randomUUID();
        this.toit = new Toit(45,SensToit.AVANT_ARRIERE, this);
        this.murList = new ArrayList<>(Arrays.asList(pMurArriere, pMurGauche, pMurFacade, pMurDroite));
    }

    /**
     * Recuperer listes accesoires
     *
     * @param vueCourante
     * @return ListAccessoire
     *
     */
    public List<Accessoire> getAccessoire(Orientation vueCourante) {

        switch (vueCourante) {
            case FACADE:
                return pAccessoireFacade;
            case ARRIERE:
                return pAccessoireArriere;

            case GAUCHE:
                return pAccessoireGauche;
            case DROITE:
                return pAccessoireDroite;
        }
        return new ArrayList<>();
    }

    public Dimension getDimensionChalet() {
        return pDimensionParDefaut;
    }

    public void setSensToit(SensToit newSensToit) {
        this.pSensToit = newSensToit;
    }

    public SensToit getSensToit() {
        return this.pSensToit;
    }

    public float getEpaisseurMur() {
        return this.pEpaisseurMur;
    }

    public void setEpaisseurMur(float newEpaisseur) {
        this.pEpaisseurMur = newEpaisseur;
    }
    public Toit getToit(){
        return toit;
    }
    public void setNewAngleToit(double angle){
        this.toit.setAngleToit(angle);
    }
    public void setRainure(float newRainure) {
        this.pRainure = newRainure;
    }

    public float getRainure() {
        return this.pRainure;
    }
    
    public void ajouterPorte(Orientation vueCourante, Dimension mDimension, PointDecimal pPosition) {
        switch (vueCourante) {
            case FACADE:
                this.pMurFacade.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.PORTE);
                break;
            case ARRIERE:
                this.pMurArriere.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.PORTE);
                break;
            case GAUCHE:
                this.pMurGauche.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.PORTE);
                break;
            case DROITE:
                this.pMurDroite.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.PORTE);
                break;

        }

    }

    public void ajouterFenetre(Orientation vueCourante, Dimension mDimension, PointDecimal pPosition) {
        switch (vueCourante) {
            case FACADE:

                this.pMurFacade.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.FENETRE);

                break;
            case ARRIERE:

                this.pMurArriere.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.FENETRE);

                break;
            case GAUCHE:

                this.pMurGauche.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.FENETRE);

                break;
            case DROITE:

                this.pMurDroite.ajouterAccessoire(pPosition, mDimension, TypeAccessoire.FENETRE);

                break;

        }

    }

    public void supprimerAccessoire(Orientation orientation, int index) {
        switch (orientation) {
            case FACADE:
                this.pMurFacade.supprimerAccessoire(index);
                break;
            case ARRIERE:
                this.pMurArriere.supprimerAccessoire(index);
                break;
            case GAUCHE:
                this.pMurGauche.supprimerAccessoire(index);
                break;
            case DROITE:
                this.pMurDroite.supprimerAccessoire(index);
                break;
        }
    }

    public Mur getMurArriere() {
        return pMurArriere;
    }

    public Mur getMurFacade() {
        return pMurFacade;
    }

    public Mur getMurDroite() {
        return pMurDroite;
    }

    public Mur getMurGauche() {
        return pMurGauche;
    }

    public List<Accessoire> getAccessoireArriere() {
        return pAccessoireArriere;
    }

    public List<Accessoire> getAccessoireGauche() {
        return pAccessoireGauche;
    }

    public List<Accessoire> getAccessoireDroite() {
        return pAccessoireDroite;
    }

    public List<Accessoire> getAccessoireFacade() {
        return pAccessoireFacade;
    }

    public UUID getUUID() {
        return uuid;
    }

    public List<Mur> getListeDeMurs() {
        return murList;
    }

    /**
     * Pour mettre à jour le chalet à partir d'un chaletDTO ici d'un fichier de
     * sauvegarde
     *
     * @param chaletDTO
     */
    public void miseAJourDepuisDTO(ChaletDTO chaletDTO) {
        // Mise à jour des propriétés du Chalet à partir du ChaletDTO

        // Mise à jour des murs
        pMurArriere.miseAJourDepuisDTO(chaletDTO.MurArriereDTO);
        pMurGauche.miseAJourDepuisDTO(chaletDTO.MurGaucheDTO);
        pMurFacade.miseAJourDepuisDTO(chaletDTO.MurFacadeDTO);
        pMurDroite.miseAJourDepuisDTO(chaletDTO.MurDroiteDTO);

        // Mise à jour des accessoires
        pAccessoireArriere.clear();
        pAccessoireGauche.clear();
        pAccessoireFacade.clear();
        pAccessoireDroite.clear();

        pAccessoireArriere.addAll(getAccessoireArriere());
        pAccessoireGauche.addAll(getAccessoireGauche());
        pAccessoireFacade.addAll(getAccessoireFacade());
        pAccessoireDroite.addAll(getAccessoireDroite());

        // Mise à jour des autres propriétés
        pSensToit = chaletDTO.SensToit;
        pEpaisseurMur = chaletDTO.EpaisseurMur;
        pRainure = chaletDTO.Rainure;
    }

    /**
     * @param dimension
     */
    public void setDimensionChalet(Dimension dimension) { //"11.75x9.875x8"
        this.pDimensionParDefaut = dimension;
    }
    
   
}
