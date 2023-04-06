package util;

import model.Client;
import model.Document;
import model.Reservation;
import org.json.simple.JSONObject;
import util.DataConverter;

import java.time.LocalDate;

public class ReservationConverter implements DataConverter<Reservation> {

    private final DataConverter<Client> clientConverter;
    private final DataConverter<Document> documentConverter;

    public ReservationConverter(DataConverter<Client> clientConverter, DataConverter<Document> documentConverter) {
        this.clientConverter = clientConverter;
        this.documentConverter = documentConverter;
    }

    @Override
    public JSONObject toJson(Reservation reservation) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client", clientConverter.toJson(reservation.getClient()));
        jsonObject.put("documentReserve", documentConverter.toJson(reservation.getDocumentReserve()));
        jsonObject.put("dateReservation", reservation.getDateReservation().toString());
        return jsonObject;
    }

    @Override
    public Reservation fromJson(JSONObject json) {
        Client client = clientConverter.fromJson((JSONObject) json.get("client"));
        Document documentReserve = documentConverter.fromJson((JSONObject) json.get("documentReserve"));
        LocalDate dateReservation = LocalDate.parse((String) json.get("dateReservation"));
        return new Reservation(client, documentReserve, dateReservation);
    }
}
