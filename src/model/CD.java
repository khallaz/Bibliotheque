package model;

public class CD extends Document {
    private String genre;

    public CD(String type, String titre, String auteur, int anneeDePublication, int nombreExemplaires, String genre) {
        super(type, titre, auteur, anneeDePublication,  nombreExemplaires);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

