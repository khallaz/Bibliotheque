package util;

import model.CD;
import model.Document;
import org.json.simple.JSONObject;

public class CDConverter implements DataConverter<Document> {



    @Override
    public JSONObject toJson(Document document) {
        CD cd = (CD) document;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "model.CD");
        jsonObject.put("id", cd.getId());
        jsonObject.put("titre", cd.getTitre());
        jsonObject.put("auteur", cd.getAuteur());
        jsonObject.put("anneeDePublication", cd.getAnneeDePublication());
        jsonObject.put("genre", cd.getGenre());
        jsonObject.put("nombreExemplaires", cd.getNombreExemplaires());
        return jsonObject;
    }

    @Override
    public CD fromJson(JSONObject json) {
        String type= (String) json.get("type");
        String id= (String) json.get("id");
        String titre = (String) json.get("titre");
        String auteur = (String) json.get("auteur");
        int anneeDePublication = ((Number) json.get("anneeDePublication")).intValue();
        String genre = (String) json.get("genre");
        int nombreExemplaires = ((Number) json.get("nombreExemplaires")).intValue();

        return new CD(type, id, titre, auteur, anneeDePublication, nombreExemplaires, genre);

    }
}
