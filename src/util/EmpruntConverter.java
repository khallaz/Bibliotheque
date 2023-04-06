package util;

import model.Client;
import model.Document;
import model.Emprunt;
import org.json.simple.JSONObject;

import java.time.LocalDate;

public class EmpruntConverter implements DataConverter<Emprunt> {

    private final DataConverter<Client> clientConverter;
    private final DataConverter<Document> documentConverter;

    public EmpruntConverter(DataConverter<Client> clientConverter, DataConverter<Document> documentConverter) {
        this.clientConverter = clientConverter;
        this.documentConverter = documentConverter;
    }

    @Override
    public JSONObject toJson(Emprunt emprunt) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client", clientConverter.toJson(emprunt.getClient()));
        jsonObject.put("documentEmprunte", documentConverter.toJson(emprunt.getDocumentEmprunte()));
        jsonObject.put("dateEmprunt", emprunt.getDateEmprunt().toString());
        if (emprunt.getDateRetour() != null) {
            jsonObject.put("dateRetour", emprunt.getDateRetour().toString());
        } else {
            jsonObject.put("dateRetour", null);
        }
        return jsonObject;
    }

    @Override
    public Emprunt fromJson(JSONObject json) {
        Client client = clientConverter.fromJson((JSONObject) json.get("client"));
        Document documentEmprunte = documentConverter.fromJson((JSONObject) json.get("documentEmprunte"));
        LocalDate dateEmprunt = LocalDate.parse((String) json.get("dateEmprunt"));
        LocalDate dateRetour = json.get("dateRetour") != null ? LocalDate.parse((String) json.get("dateRetour")) : null;
        Emprunt emprunt = new Emprunt(client, documentEmprunte, dateEmprunt);
        emprunt.setDateRetour(dateRetour);
        return emprunt;
    }
}
