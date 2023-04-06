package util;

import model.CD;
import model.Document;
import model.Journal;
import org.json.simple.JSONObject;

public class JournalConverter implements DataConverter<Document> {


    @Override
    public JSONObject toJson(Document document) {
        Journal journal = (Journal) document;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "model.Journal");
        jsonObject.put("id", journal.getId());
        jsonObject.put("titre", journal.getTitre());
        jsonObject.put("auteur", journal.getAuteur());
        jsonObject.put("anneeDePublication", journal.getAnneeDePublication());
        jsonObject.put("numeroEdition", journal.getNumeroEdition());
        jsonObject.put("nombreExemplaires", journal.getNombreExemplaires());
        return jsonObject;
    }

    @Override
    public Journal fromJson(JSONObject json) {
        String type = (String) json.get("type");
        String id = (String) json.get("id");
        String titre = (String) json.get("titre");
        String auteur = (String) json.get("auteur");
        int anneeDePublication = ((Number) json.get("anneeDePublication")).intValue();
        int numeroEdition = ((Number) json.get("numeroEdition")).intValue();
        int nombreExemplaires = ((Number) json.get("nombreExemplaires")).intValue();

        return new Journal(type, id, titre, auteur, anneeDePublication, numeroEdition, nombreExemplaires);
    }
}
