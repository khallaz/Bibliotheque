package model;



public class Livre extends Document {
    private String isbn;

    public Livre(String type, String id, String titre, String auteur, int anneeDePublication, int nombreExemplaires, String isbn) {
        super(type, id, titre, auteur, anneeDePublication, nombreExemplaires);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
