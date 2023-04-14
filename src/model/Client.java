package model;

public class Client extends Personne implements Observateur {
    private int numeroClient;

    public Client(String nom, String prenom, String adresse, int numeroClient) {
        super(nom, prenom, adresse);
        this.numeroClient = numeroClient;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public String getDisplayName() {
        return getNumeroClient()+ " -     " + super.getDisplayName();
    }



    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    @Override
    public void notifier(Document document) {
        System.out.println("Cher " + getNom() + ", le document " + document.getTitre() + " est maintenant disponible.");
    }

    @Override
    public String toString() {
        return "Client [" + super.toString() + ", Numero Client: " + numeroClient + "]";
    }

}
