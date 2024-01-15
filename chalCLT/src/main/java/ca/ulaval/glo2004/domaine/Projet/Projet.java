package ca.ulaval.glo2004.domaine.Projet;

import ca.ulaval.glo2004.domaine.Chalet.Chalet;
import ca.ulaval.glo2004.domaine.Chalet.Controleur;
import ca.ulaval.glo2004.domaine.DTO.ChaletDTO;
import java.io.*;
import ca.ulaval.glo2004.domaine.utils.Orientation;
import java.util.Base64;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Projet implements Serializable {

    private final String EXTENSION_FICHIER = ".chalet";
    private String cheminFichier;
    private String nomDuProjet = "nomParDefault";
    private ChaletDTO chaletDTO;
    private Chalet chalet;
    private Controleur mControleur;

    public Projet(Controleur controleur) {
        mControleur = controleur;
        chaletDTO = controleur.getChaletDTO();
        cheminFichier = "";
    }

    public Projet(String cheminFichier, Controleur controleur) {
        
        this.cheminFichier = cheminFichier;
        this.chargerFichier(cheminFichier);
        this.mControleur = controleur;
        this.mControleur.setChaletDTO(this.chaletDTO);
        mControleur.setVueCourant(Orientation.DESSUS);
        

    }

    public Projet() {
        chalet = new Chalet();
        cheminFichier = "";
    }

    public ChaletDTO getChaletDTO() {
        return this.chaletDTO;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        if (!cheminFichier.endsWith(EXTENSION_FICHIER)) {
            cheminFichier += EXTENSION_FICHIER;
        }

        this.cheminFichier = cheminFichier;
    }
    
    public void setNomDuProjet(String nomFichier) {
        
        this.nomDuProjet = nomFichier;
    }
    
    public String getNomDuProjet() {
        return this.nomDuProjet;
    }

    public String obtenirEtat() {
        String etat = "";
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream out = null;

        try {
            byteOut = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteOut);

            out.writeObject(chaletDTO);

            etat = Base64.getEncoder().encodeToString(byteOut.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(out);
            close(byteOut);
        }

        return etat;
    }

    public void chargerEtat(String etat) {

        ByteArrayInputStream bytesIn = null;
        ObjectInputStream in = null;

        try {
            byte[] donnees = Base64.getDecoder().decode(etat);
            bytesIn = new ByteArrayInputStream(donnees);
            in = new ObjectInputStream(bytesIn);

            this.chaletDTO = (ChaletDTO) in.readObject();
            this.mControleur.setChaletDTO(chaletDTO);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            close(in);
            close(bytesIn);
        }
    }

    private void close(Closeable stream) {
        if (stream == null) {
            return;
        }

        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enregistrer() {
        if (!cheminFichier.isBlank()) {
            Path fichier = Paths.get(cheminFichier);
            if (!Files.exists(fichier)) {
                try {
                    Files.createFile(fichier);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(fichier))) {
                out.writeObject(this.mControleur.getChaletDTO());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void chargerFichier(String cheminFichier) {
        if (cheminFichier.isBlank()) {
            throw new IllegalArgumentException("Il n'est pas possible de charger un chemin vide.");
        }

        Path fichier = Paths.get(cheminFichier);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(fichier))) {
            this.chaletDTO = (ChaletDTO) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void mettreAJourChaletApartirDeDTO(Chalet pChalet) {
        if (chaletDTO != null) {
            // Mettre à jour le chalet avec les données du chaletDTO
            pChalet.miseAJourDepuisDTO(chaletDTO);
        }
    }

}
