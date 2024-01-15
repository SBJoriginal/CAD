package ca.ulaval.glo2004.domaine.Chalet;
import ca.ulaval.glo2004.ExportSTL.ExporteurSTL;
import ca.ulaval.glo2004.domaine.DTO.AccessoireDTO;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import ca.ulaval.glo2004.domaine.DTO.MurDTO;
import ca.ulaval.glo2004.domaine.Mur.Accessoire;
import ca.ulaval.glo2004.domaine.Mur.Mur;
import ca.ulaval.glo2004.domaine.Projet.GestionnaireEtatProjet;
import ca.ulaval.glo2004.domaine.Projet.Projet;
import ca.ulaval.glo2004.domaine.utils.Dimension;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import ca.ulaval.glo2004.domaine.utils.PointDecimal;
import ca.ulaval.glo2004.domaine.utils.SensToit;
import ca.ulaval.glo2004.domaine.utils.Zoom;
import static java.awt.Color.RED;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
 
public class Controleur implements Serializable{
    public int maxWidthtTest;
    public ChaletMapper chaletMapper = new ChaletMapper();
    private Chalet pChalet = new Chalet();
    private static ChaletDTO chaletDTO;
    private int dimensionGrille = 100; 
    private Zoom zoom;
    private Orientation pVueCourant;
    private boolean afficherGrille = false;
    private Map<UUID, Accessoire> accessoireMap = chaletMapper.createMapAccesssoireWithUuid(pChalet);
    private Map<UUID, Mur> murMap = chaletMapper.createMapMurWIthUuid(pChalet);
    private Projet projet;
    private GestionnaireEtatProjet gestionnaireEtat;
    private int maxWidthAfficheur;
    private int maxHeightAfficheur;
    private static String cheminExportProjet = "";

    
    public Controleur() {
        this.chaletDTO = chaletMapper.mapVersChaletDTO(this.pChalet);
        this.projet = new Projet(this); // New Ligne
        this.gestionnaireEtat = new GestionnaireEtatProjet(this.projet);
        this.zoom = new Zoom(1, new Point(0, 0));
        etatChalet();
    }
    public void reinializeChalet(Chalet chalet){
        this.pChalet = chalet;
        this.chaletDTO = chaletMapper.mapVersChaletDTO(this.pChalet);
        this.projet = new Projet(this);
        this.gestionnaireEtat = new GestionnaireEtatProjet(this.projet); 
        this.zoom = new Zoom(1, new Point(0, 0));
        etatChalet();
    }
    public double getAngleToit(){
        return this.chaletDTO.Toit.getAngleToit();
    }
    
    public void setAngleToit (double angleToit){
        this.pChalet.setNewAngleToit(angleToit);
    }
    
    public ChaletMapper getChaletMapper(){
        return chaletMapper;
    }
    
    public void setVueCourant(Orientation mVueCourant) {
        pVueCourant = mVueCourant;
    }
    public Orientation getVueCourant() {
        return pVueCourant;
    }
    public boolean getAfficherGrille() {
        return afficherGrille;
    }
    public void setRainure(float newRainure){
        this.pChalet.setRainure(newRainure);
    }
    public float getRainure(){
        return this.pChalet.getRainure();
    }
    public int getDimensionGrille() {
        return dimensionGrille;
    }
    public void switchAfficherGrille() {
        afficherGrille = !afficherGrille;
    }
    public double getZoomLevel() {
        return zoom.getZoomLevel();
    }
    public ChaletDTO getChaletDTO(){
        return this.chaletDTO; 
    }
    
    public void setDimensionGrille(int impDimensionGrille){
        dimensionGrille = impDimensionGrille;
    }
    public void setZoomLevel(double zoomLevel, Point2D.Double zoomPoint) {
        double oldZoomLevel = zoom.getZoomLevel();
        
        if (zoomLevel>0){
            zoom.setZoomLevel(zoomLevel);
        }   
        else{
            return;
        }
               
        Point viewOffset = zoom.getViewOffset();
        double zoomFactor = zoomLevel / oldZoomLevel;        
        int dx = (int) ((zoomPoint.x - viewOffset.x) * (zoomFactor - 1));
        int dy = (int) ((zoomPoint.y - viewOffset.y) * (zoomFactor - 1));
        zoom.setViewOffset(new Point(viewOffset.x -dx, viewOffset.y -dy));
    } 
     public void setZoomLevelDefault(){
        zoom.setZoomLevel(1);
        Point defaultViewOffset = new Point(0, 0);
        zoom.setViewOffset(defaultViewOffset);
           }

    public void ouvrirProjet(String cheminFichier) {
        
        projet = new Projet(cheminFichier,this);
        chaletDTO = projet.getChaletDTO(); 
        pChalet.miseAJourDepuisDTO(chaletDTO);       
        gestionnaireEtat.setProjet(projet);
        this.setVueCourant(Orientation.DESSUS);
        
    }
    
    public String getCheminProjet() {
        return projet.getCheminFichier();
    }
    
    public void setCheminProjet(String cheminFichier) {
        projet.setCheminFichier(cheminFichier);
    }
    
    public String getNomDuProjet() {
        return projet.getNomDuProjet();
    }
    
    public void setNomDuProjet(String nomFichier) {
        projet.setNomDuProjet(nomFichier);
    }
    
    public String getCheminExportProjet() {
        if (cheminExportProjet.isEmpty()) {
            return "";
        } else {
        }
        return cheminExportProjet + projet.getNomDuProjet().split("\\.")[0];
    }
    
    public void setCheminExportProjet(String cheminExportProjet) {        
        Controleur.cheminExportProjet = cheminExportProjet;
    }
    
    public void enregistrerProjet() {
        projet.enregistrer();
    }
    
    public void annuler() {
        
        gestionnaireEtat.annuler();
        
    }
    public void updateChapetMapper(ChaletDTO chaletDTO){
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
    }
    public void refaire() {
        gestionnaireEtat.refaire();
    }
    public void vider() {
        gestionnaireEtat.vider();
    }
    public Point getViewOffset() {
        return zoom.getViewOffset();
    }
    public void setChaletDTO(ChaletDTO newChaletDTO){
        this.chaletDTO = newChaletDTO;
    }
    public void setViewOffset(Point viewOffset) {
        zoom.setViewOffset(viewOffset);
    }
    public void ajouterPorte(Orientation vueCourante, Dimension mDimension, PointDecimal pPosition) {
        this.pChalet.ajouterPorte(this.getVueCourant(), mDimension, pPosition);
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
    }
    public void ajouterFenetre(Orientation vueCourante, Dimension mDimension, PointDecimal pPosition){
        this.pChalet.ajouterFenetre(this.getVueCourant(), mDimension, pPosition);
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
    }
    public void supprimerAccessoire(int index){
        this.pChalet.supprimerAccessoire(this.getVueCourant(),index);
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
    }
    public void setSensToit(SensToit sensToit){
        this.pChalet.setSensToit(sensToit);
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
    }
    public SensToit getSensToitCourant(){
        return this.pChalet.getSensToit();
    }
    public float getEpaisseurMur(){
        return this.pChalet.getEpaisseurMur();
    }
     public void setEpaisseurMur(float newEpaisseur){
        this.pChalet.setEpaisseurMur(newEpaisseur);
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
    }
    public MurDTO getMurDTOCourant(Orientation mVueCourant) {
        switch (mVueCourant) {
            case FACADE:
                return chaletDTO.MurFacadeDTO;
            case ARRIERE:
                return chaletDTO.MurArriereDTO;
 
            case GAUCHE:
                return chaletDTO.MurGaucheDTO;
            case DROITE:
                return chaletDTO.MurDroiteDTO;
        }
        return new MurDTO(new Mur(Orientation.DESSUS,new Dimension(0,0,0)));
    }   
    
    public void deplacerAccessoire(int index, PointDecimal position, boolean dragDrop){
        MurDTO mur = getMurDTOCourant(pVueCourant);
        AccessoireDTO accessoire = mur.AccessoiresList.get(index);
        Accessoire accessoireP = this.chaletMapper.createMapAccesssoireWithUuid(pChalet).get(accessoire.Uuid);
        if(pVueCourant == Orientation.ARRIERE){
            pChalet.getMurArriere().deplacerAccessoire(position, accessoireP);
        }
        if(pVueCourant == Orientation.FACADE){
            pChalet.getMurFacade().deplacerAccessoire(position, accessoireP);
        }
        if(pVueCourant == Orientation.GAUCHE){
            pChalet.getMurGauche().deplacerAccessoire(position, accessoireP);
        }
        if(pVueCourant == Orientation.DROITE){
            pChalet.getMurDroite().deplacerAccessoire(position, accessoireP);
        }
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        if(dragDrop == false){
            etatChalet();
        }
     }
      public void modifierAccessoire(int index, Dimension dimension){
        MurDTO mur = getMurDTOCourant(pVueCourant);
        AccessoireDTO accessoire = mur.AccessoiresList.get(index);
        Accessoire accessoireP = this.chaletMapper.createMapAccesssoireWithUuid(pChalet).get(accessoire.Uuid);
        if(pVueCourant == Orientation.ARRIERE){
            pChalet.getMurArriere().modifierAccessoire(dimension,accessoireP);
        }
        if(pVueCourant == Orientation.FACADE){
            pChalet.getMurFacade().modifierAccessoire(dimension,accessoireP);
        }
        if(pVueCourant == Orientation.GAUCHE){
            pChalet.getMurGauche().modifierAccessoire(dimension,accessoireP);
        }
        if(pVueCourant == Orientation.DROITE){
            pChalet.getMurDroite().modifierAccessoire(dimension,accessoireP);
        }
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
     }
      public void deplacerModifierAccessoire(int index, Dimension dimension, PointDecimal position){
        MurDTO mur = getMurDTOCourant(pVueCourant);
        AccessoireDTO accessoire = mur.AccessoiresList.get(index);
        Accessoire accessoireP = this.chaletMapper.createMapAccesssoireWithUuid(pChalet).get(accessoire.Uuid);
        if(pVueCourant == Orientation.ARRIERE){
            pChalet.getMurArriere().modifierDeplacerAccessoire(position,dimension,accessoireP);
        }
        if(pVueCourant == Orientation.FACADE){
            pChalet.getMurFacade().modifierDeplacerAccessoire(position,dimension,accessoireP);
        }
        if(pVueCourant == Orientation.GAUCHE){
            pChalet.getMurGauche().modifierDeplacerAccessoire(position,dimension,accessoireP);
        }
        if(pVueCourant == Orientation.DROITE){
            pChalet.getMurDroite().modifierDeplacerAccessoire(position,dimension,accessoireP);
        }
        chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
     }
    public void etatChalet() {
        gestionnaireEtat.enregistrerEtat();
    }
    public void modifierDimensionChalet(Dimension newDimension){
        this.pChalet.setDimensionChalet(newDimension);
        this.chaletMapper.mettreAjourChaletDTO(pChalet, chaletDTO);
        etatChalet();
        
    }
    public void setMaxWidthAfficheur(int pmaxWidthAfficheur) {
        this.maxWidthAfficheur = pmaxWidthAfficheur;
    }

    public void setMaxHeightAfficheur(int pmaxHeightAfficheur) {
        this.maxHeightAfficheur = pmaxHeightAfficheur;
    }
    
    public static void exportSTL(){
        ExporteurSTL.exporterSTL(chaletDTO, cheminExportProjet );
    }
    public void exporterPanneauxDeTrait(){
        ExporteurSTL.exporterRetraitSTL(this.getCheminExportProjet(), chaletDTO);
    }
    public void exporterPanneauxBruts(){
        ExporteurSTL.exporterPanneauBrutSTL(this.getCheminExportProjet(), chaletDTO);
    }
    public void exporterPanneauxFinis() throws IOException{
        ExporteurSTL.exporterPanneauFiniSTL(this.getCheminExportProjet(), chaletDTO);
    }
    public boolean isListsAccessoireVide(){
        for(MurDTO mur : this.getChaletDTO().MurList){
            if(!mur.AccessoiresList.isEmpty()){
                return false;
            }
        }
        return true;
    }
    public boolean atLeastOneValidAccessoireVide(){
        for(MurDTO mur : this.getChaletDTO().MurList){
            for(AccessoireDTO acc : mur.AccessoiresList){
                if(acc.CouleurAccessoire != RED){
                    return true;
                }
            }
        }
        return false;
    } 
}