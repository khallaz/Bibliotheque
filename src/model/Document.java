package model;

import util.DocumentIdGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class Document {

    private String type;
    private String id;
    private String titre;
    private String auteur;
    private int anneeDePublication;
    private int nombreExemplaires;
    private boolean disponible;

    private List<Observateur> observateurs;

    public Document(String type, String titre, String auteur, int anneeDePublication, int nombreExemplaires) {
        this.type = type;
        this.id = DocumentIdGenerator.generateDocumentId();
        this.titre = titre;
        this.auteur = auteur;
        this.anneeDePublication = anneeDePublication;
        this.nombreExemplaires = nombreExemplaires;
        observateurs = new ArrayList<>();
    }

    public String getType() {
        return type;
    }
    public String getId() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
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

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }


    @Override
    public String toString() {
        return "Document [Type: " + type + ", ID: " + id + ", Titre: " + titre + ", Auteur: " + auteur + ", Année de publication: " + anneeDePublication + "]";
    }
}
