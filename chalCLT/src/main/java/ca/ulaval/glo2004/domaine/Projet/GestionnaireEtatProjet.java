package ca.ulaval.glo2004.domaine.Projet;
import java.io.Serializable;
import java.util.Stack;

public final class GestionnaireEtatProjet implements Serializable{
    private Stack<String> etatsUndo;    
    private Stack<String> etatsRedo;
    
    private Projet projet;
    
    public GestionnaireEtatProjet(Projet projet) 
    {
        this.projet = projet;
        this.etatsUndo = new Stack<>();
        this.etatsRedo = new Stack<>();
       
        enregistrerEtat();
    }
    public int sizeEtatsUndo(){
        return etatsUndo.size();
    }
    public int sizeEtatsRedo(){
        return etatsRedo.size();
    }
    public void setProjet(Projet projet) {
        this.projet = projet;
        
        this.etatsUndo.clear();
        this.etatsRedo.clear();
        
        
        enregistrerEtat();
    }
  
    public void enregistrerEtat()
    {
        etatsUndo.push(projet.obtenirEtat());
        etatsRedo.clear();
        
    }
    
    public void rechargerDerniereEtat() {
        if(!etatsUndo.empty()) {
            projet.chargerEtat(etatsUndo.peek());
            
        }
    }
    
    public void annuler()
    {

        if(etatsUndo.size() > 1)
        {
            etatsRedo.push(etatsUndo.pop());   
            projet.chargerEtat(etatsUndo.peek()); 
            
        }
    }
    
    public void refaire()
    {
        if(!etatsRedo.empty())
        {
            etatsUndo.push(etatsRedo.peek());
            
            projet.chargerEtat(etatsRedo.pop());
        }
    }
    
    public void vider(){
        if(!etatsRedo.empty())
        {
            etatsRedo = new Stack<>();
        }
        if(!etatsUndo.empty())
        {
            etatsUndo = new Stack<>();
        }
    }
}
