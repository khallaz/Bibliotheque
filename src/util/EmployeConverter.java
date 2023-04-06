package util;

import model.Employe;
import org.json.simple.JSONObject;

public class EmployeConverter implements DataConverter<Employe> {

    @Override
    public JSONObject toJson(Employe employe) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nom", employe.getNom());
        jsonObject.put("prenom", employe.getPrenom());
        jsonObject.put("adresse", employe.getAdresse());
        jsonObject.put("numeroEmploye", employe.getNumeroEmploye());
        return jsonObject;
    }

    @Override
    public Employe fromJson(JSONObject jsonObject) {
        String nom = (String) jsonObject.get("nom");
        String prenom = (String) jsonObject.get("prenom");
        String adresse = (String) jsonObject.get("adresse");
        int numeroEmploye = (int) (long) jsonObject.get("numeroEmploye");
        return new Employe(nom, prenom, adresse, numeroEmploye);
    }
}
