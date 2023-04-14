package controller;
import model.*;
import util.*;

import java.time.LocalDate;
import java.util.List;



public class BibliothequeController {
    private Bibliotheque bibliotheque;
    private DataConverter<Document> cdConverter;
    private DataConverter<Document> journalConverter;
    private DataConverter<Document> livreConverter;
    private JsonManager<Emprunt> empruntJsonManager;
    private EmpruntConverter empruntConverter;
    private DataConverter<Client> clientConverter;
    private DataConverter<Document> documentConverter;


    public BibliothequeController() {
        bibliotheque = Bibliotheque.getInstance();
        cdConverter = new CDConverter();
        journalConverter = new JournalConverter();
        livreConverter = new LivreConverter();
        clientConverter = new ClientConverter();
        documentConverter = new DocumentConverter();
        this.empruntJsonManager = new JsonManager<>("emprunts.json");
        this.empruntConverter = new EmpruntConverter(clientConverter, documentConverter);
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

    public Client getClient(int numeroClient) {
        List<Client> clients = bibliotheque.getClients();
        for (Client client : clients) {
            if (client.getNumeroClient() == numeroClient) {
                return client;
            }
        }
        return null;
    }

    public Document getDocumentByTitle(String titre) {
        List<Document> documents = bibliotheque.getDocuments();
        for (Document document : documents) {
            if (document.getTitre().equalsIgnoreCase(titre)) {
                return document;
            }
        }
        return null;
    }


    public Document getDocument(String documentId) {
        return bibliotheque.getDocument(documentId);
    }

    public List<Document> getDocuments() {
        return bibliotheque.getDocuments();
    }

    public List<Client> getListeClients() {
        return bibliotheque.getClients();
    }

    public List<Employe> getListeEmployes() {
        return bibliotheque.getEmployes();
    }

    public List<Emprunt> getListeEmprunts() {
        return bibliotheque.getEmprunts();
    }

    public List<Reservation> getListeReservations() {
        return bibliotheque.getReservations();
    }

    public void rendreDocumentDisponible(String documentId) {
        Document document = bibliotheque.getDocument(documentId);
        if (document != null) {
            bibliotheque.rendreDocumentDisponible(document);
        } else {
            // Gérer le cas où le documentId n'est pas trouvé
        }
    }

    public void supprimerEmprunt(Emprunt emprunt) {
        bibliotheque.getEmprunts().remove(emprunt);
    }


    private void sauvegarderEmprunts() {
        empruntJsonManager.saveData(bibliotheque.getEmprunts(), empruntConverter);
    }

    public void supprimerreservation(Reservation reservation) {
        bibliotheque.getReservations().remove(reservation);
    }

    public Reservation reserverDocument(Client client, Document document) {
        if (document.isDisponible()) {
            LocalDate dateReservation = LocalDate.now();
            Reservation reservation = new Reservation(client, document, dateReservation);
            bibliotheque.ajouterReservation(reservation);
            document.setDisponible(false);
            return reservation;
        } else {
            System.out.println("Le document n'est pas disponible.");
            return null;
        }
    }


    public Document rechercherDocumentParTitre(String titre) {
        return getDocumentByTitle(titre);
    }

    public boolean renouvelerEmprunt(Emprunt emprunt) {
        if (emprunt == null) {
            System.out.println("L'emprunt est invalide.");
            return false;
        }

        LocalDate dateFin = emprunt.getDateRetour().plusDays(Emprunt.DUREE_EMPRUNT);
        emprunt.setDateRetour(dateFin);
        return true;
    }



    public boolean annulerReservation(Client client, Document document) {
        Reservation reservation = null;
        List<Reservation> reservations = bibliotheque.getReservations();
        for (Reservation r : reservations) {
            if (r.getClient().equals(client)) {
                reservation = r;
                break;
            }
        }

        if (reservation != null) {
            reservations.remove(reservation);
            reservation.getDocumentReserve().setDisponible(true);
            return true;
        } else {
            return false;
        }
    }


    public Reservation trouverReservation(Client client, Document document) {
        List<Reservation> reservations = bibliotheque.getReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getClient().equals(client) && reservation.getDocumentReserve().equals(document)) {
                return reservation;
            }
        }
        return null;
    }



}
