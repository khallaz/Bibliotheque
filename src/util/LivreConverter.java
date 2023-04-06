package util;

import model.Document;
import model.Livre;
import org.json.simple.JSONObject;

public class LivreConverter implements DataConverter<Document> {



    @Override
    public JSONObject toJson(Document document) {
        Livre livre = (Livre) document;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "model.Livre");
        jsonObject.put("id", livre.getId());
        jsonObject.put("titre", livre.getTitre());
        jsonObject.put("auteur", livre.getAuteur());
        jsonObject.put("anneeDePublication", livre.getAnneeDePublication());
        jsonObject.put("isbn", livre.getIsbn());
        jsonObject.put("nombreExemplaires", livre.getNombreExemplaires());
        return jsonObject;
    }

    @Override
    public Livre fromJson(JSONObject json) {
        String type = (String) json.get("type");
        String id = (String) json.get("id");
        String titre = (String) json.get("titre");
        String auteur = (String) json.get("auteur");
        int anneeDePublication = ((Number) json.get("anneeDePublication")).intValue();
        String isbn = (String) json.get("isbn");
        int nombreExemplaires = ((Number) json.get("nombreExemplaires")).intValue();

        return new Livre(type, id, titre, auteur, anneeDePublication, nombreExemplaires, isbn);
    }
}
