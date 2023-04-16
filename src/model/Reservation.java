package model;

import java.time.LocalDate;

public class Reservation {

    private Client client;
    private Document documentReserve;
    private LocalDate dateReservation;
    private Document document;

    public Reservation(Client client, Document documentReserve, LocalDate dateReservation) {
        this.client = client;
        this.documentReserve = documentReserve;
        this.dateReservation = dateReservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Document getDocumentReserve() {
        return documentReserve;
    }

    public void setDocumentReserve(Document documentReserve) {
        this.documentReserve = documentReserve;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public Document getDocument() {
        return document;
    }

    public String toString() {
        return documentReserve.getTitre();
    }


}
