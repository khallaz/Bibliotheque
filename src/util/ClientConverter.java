package util;

import model.Client;
import org.json.simple.JSONObject;

public class ClientConverter implements DataConverter<Client> {


    @Override
    public JSONObject toJson(Client client) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nom", client.getNom());
        jsonObject.put("prenom", client.getPrenom());
        jsonObject.put("adresse", client.getAdresse());
        jsonObject.put("numeroClient", client.getNumeroClient());
        return jsonObject;
    }

    @Override
    public Client fromJson(JSONObject jsonObject) {
        String nom = (String) jsonObject.get("nom");
        String prenom = (String) jsonObject.get("prenom");
        String adresse = (String) jsonObject.get("adresse");
        int numeroClient = ((Number) jsonObject.get("numeroClient")).intValue();
        return new Client(nom, prenom, adresse, numeroClient);
    }
}
