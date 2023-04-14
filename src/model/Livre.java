package model;



public class Livre extends Document {
    private String isbn;

    public Livre(String type, String titre, String auteur, int anneeDePublication, int nombreExemplaires, String isbn) {
        super(type, titre, auteur, anneeDePublication, nombreExemplaires);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
