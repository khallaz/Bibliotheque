package controller;
import model.*;
import util.*;


public class BibliothequeController {
    private Bibliotheque bibliotheque;
    private DataConverter<Document> cdConverter;
    private DataConverter<Document> journalConverter;
    private DataConverter<Document> livreConverter;

    public BibliothequeController() {
        bibliotheque = Bibliotheque.getInstance();
        cdConverter = new CDConverter();
        journalConverter = new JournalConverter();
        livreConverter = new LivreConverter();
    }



    public void ajouterClient(Client client) {
        bibliotheque.ajouterClient(client);
    }

    public void ajouterEmploye(Employe employe) {
        bibliotheque.ajouterEmploye(employe);
    }

    public void ajouterDocument(Document document) {
        bibliotheque.ajouterDocument(document);
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        bibliotheque.ajouterEmprunt(emprunt);
    }

    public void ajouterReservation(Reservation reservation) {
        bibliotheque.ajouterReservation(reservation);
    }
}
