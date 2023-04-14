package model;

import util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bibliotheque {

    private List<Document> documents;
    private List<Emprunt> emprunts;
    private List<Reservation> reservations;
    private List<Client> clients;
    private List<Employe> employes;
    private static Bibliotheque instance;
    private JsonManager<Client> clientJsonManager;
    private ClientConverter clientConverter;
    private JsonManager<Emprunt> empruntJsonManager;
    private EmpruntConverter empruntConverter;
    private JsonManager<Reservation> reservationJsonManager;
    private ReservationConverter reservationConverter;
    private JsonManager<Employe> employeJsonManager;
    private EmployeConverter employeConverter;
    private JsonManager<Document> documentJsonManager;
    private DocumentConverter documentConverter;
    private List<Observateur> observateurs;


    public Bibliotheque() {
        documents = new ArrayList<>();
        emprunts = new ArrayList<>();
        reservations = new ArrayList<>();
        clients = new ArrayList<>();
        employes = new ArrayList<>();
        observateurs = new ArrayList<>();

        clientJsonManager = new JsonManager<>("resources/clients.json");
        clientConverter = new ClientConverter();
        clients = clientJsonManager.loadData(clientConverter);

        employeJsonManager = new JsonManager<>("resources/employes.json");
        employeConverter = new EmployeConverter();
        employes = employeJsonManager.loadData(employeConverter);

        documentJsonManager = new JsonManager<>("resources/documents.json");
        documentConverter = new DocumentConverter();
        documents = documentJsonManager.loadData(documentConverter);

        empruntJsonManager = new JsonManager<>("resources/emprunts.json");
        empruntConverter = new EmpruntConverter(clientConverter, documentConverter);
        emprunts = empruntJsonManager.loadData(empruntConverter);

        reservationJsonManager = new JsonManager<>("resources/reservations.json");
        reservationConverter = new ReservationConverter(clientConverter, documentConverter);
        reservations = reservationJsonManager.loadData(reservationConverter);
    }

    public static Bibliotheque getInstance() {
        if (instance == null) {
            instance = new Bibliotheque();
        }
        return instance;
    }

    public void ajouterClient(Client client) {
        clients.add(client);
        clientJsonManager.saveData(clients, clientConverter);
    }

    public void ajouterEmploye(Employe employe) {
        employes.add(employe);
        employeJsonManager.saveData(employes, employeConverter);
    }

    public void ajouterDocument(Document document) {
        documents.add(document);
        documentJsonManager.saveData(documents, documentConverter);
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        empruntJsonManager.saveData(emprunts, empruntConverter);
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
        reservationJsonManager.saveData(reservations, reservationConverter);
    }



    public List<Client> getClients() {
        return clients;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }



    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    public void supprimerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    public void notifierObservateurs(Document document) {
        for (Observateur observateur : observateurs) {
            observateur.notifier(document);
        }
    }

    public void rendreDocumentDisponible(Document document) {
        // Mettre à jour l'état du document pour qu'il soit disponible
        document.setDisponible(true);

        // Trouver les réservations pour ce document
        List<Reservation> reservationsPourCeDocument = reservations.stream()
                .filter(reservation -> reservation.getDocument().getId().equals(document.getId()))
                .collect(Collectors.toList());

        // Supprimer les réservations pour ce document
        for (Reservation reservation : reservationsPourCeDocument) {
            supprimerReservation(reservation.getDocument().getId());
            supprimerObservateur(reservation.getClient());
        }

        // Notifier les clients concernés
        notifierObservateurs(document);
    }

    private void supprimerReservation(String documentId) {
        reservations.removeIf(reservation -> reservation.getDocument().getId().equals(documentId));
    }

    public Document getDocument(String documentId) {
        for (Document document : documents) {
            if (document.getId().equals(documentId)) {
                return document;
            }
        }
        return null;
    }



}
