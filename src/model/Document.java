package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Document {

    private String type;
    private String id;
    private String titre;
    private String auteur;
    private int anneeDePublication;
    private int nombreExemplaires;

    private List<Observateur> observateurs;

    public Document(String type, String id, String titre, String auteur, int anneeDePublication, int nombreExemplaires) {
        this.type = type;
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneeDePublication = anneeDePublication;
        this.nombreExemplaires = nombreExemplaires;
        observateurs = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneeDePublication() {
        return anneeDePublication;
    }

    public void setAnneeDePublication(int anneeDePublication) {
        this.anneeDePublication = anneeDePublication;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    public void supprimerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    public void notifierObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.notifier(this);
        }
    }
}
