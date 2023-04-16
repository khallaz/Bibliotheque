package model;

import java.time.LocalDate;

public class ProxyEmprunt implements IEmprunt {
    Emprunt emprunt;

    public ProxyEmprunt (Client client, Document document, LocalDate dateEmprunt){
        if(checkEmprunt(document)) {
            this.emprunt = new Emprunt(client, document, dateEmprunt);
        }
    }

    public boolean checkEmprunt(Document document){
        if(document.getNombreExemplaires() > 0) return true;
        return false;
    }
}