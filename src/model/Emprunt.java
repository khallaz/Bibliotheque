package model;

import model.Client;
import model.Document;

import java.time.LocalDate;

public class Emprunt {

    private Client client;
    private Document documentEmprunte;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    public static final int DUREE_EMPRUNT = 10;


    public Emprunt(Client client, Document documentEmprunte, LocalDate dateEmprunt) {
        this.client = client;
        this.documentEmprunte = documentEmprunte;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateEmprunt.plusDays(DUREE_EMPRUNT);

    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Document getDocumentEmprunte() {
        return documentEmprunte;
    }

    public void setDocumentEmprunte(Document documentEmprunte) {
        this.documentEmprunte = documentEmprunte;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return documentEmprunte.getTitre();
    }
}
