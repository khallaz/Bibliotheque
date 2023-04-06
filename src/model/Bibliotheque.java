package model;

import util.*;

import java.util.ArrayList;
import java.util.List;

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

    public Bibliotheque() {
        documents = new ArrayList<>();
        emprunts = new ArrayList<>();
        reservations = new ArrayList<>();
        clients = new ArrayList<>();
        employes = new ArrayList<>();

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

}
