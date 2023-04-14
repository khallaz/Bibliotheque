package util;

import model.CD;
import model.Document;
import model.Journal;
import model.Livre;
import org.json.simple.JSONObject;

public class DocumentConverter implements DataConverter<Document> {

   ;
    @Override
    public JSONObject toJson(Document document) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", document.getType());
        jsonObject.put("titre", document.getTitre());
        jsonObject.put("auteur", document.getAuteur());
        jsonObject.put("anneeDePublication", document.getAnneeDePublication());
        jsonObject.put("nombreExemplaires", document.getNombreExemplaires());

        if (document instanceof CD) {
            jsonObject.put("genre", ((CD) document).getGenre());
        } else if (document instanceof Journal) {
            jsonObject.put("numeroEdition", ((Journal) document).getNumeroEdition());
        } else if (document instanceof Livre) {
            jsonObject.put("isbn", ((Livre) document).getIsbn());
        }

        return jsonObject;
    }

    @Override
    public Document fromJson(JSONObject jsonObject) {
        String type = (String) jsonObject.get("type");
        String titre = (String) jsonObject.get("titre");
        String auteur = (String) jsonObject.get("auteur");
        int anneeDePublication = ((Number) jsonObject.get("anneeDePublication")).intValue();
        int nombreExemplaires = ((Number) jsonObject.get("nombreExemplaires")).intValue();

        switch (type) {
            case "Livre":
                String isbn = (String) jsonObject.get("isbn");
                return new Livre(type, titre, auteur, anneeDePublication, nombreExemplaires, isbn);
            case "CD":
                String genre = (String) jsonObject.get("genre");
                return new CD(type, titre, auteur, anneeDePublication, nombreExemplaires, genre);
            case "Journal":
                int numeroEdition = ((Number) jsonObject.get("numeroEdition")).intValue();
                return new Journal(type, titre, auteur, anneeDePublication, nombreExemplaires, numeroEdition);
            default:
                return null;
        }
    }

}
