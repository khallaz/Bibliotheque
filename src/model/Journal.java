package model;

import model.Document;

public class Journal extends Document {
    private int numeroEdition;

    public Journal(String type, String id, String titre, String auteur, int anneeDePublication, int nombreExemplaires, int numeroEdition) {
        super(type, id, titre, auteur, anneeDePublication, nombreExemplaires);
        this.numeroEdition = numeroEdition;
    }

    public int getNumeroEdition() {
        return numeroEdition;
    }

    public void setNumeroEdition(int numeroEdition) {
        this.numeroEdition = numeroEdition;
    }
}

