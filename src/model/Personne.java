package model;

public class Personne {

    private String nom;
    private String prenom;
    private String adresse;

    public Personne(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Prenom: " + prenom + ", Adresse: " + adresse;
    }

    public String getDisplayName() {
        return  prenom + " " + nom;
    }
}
