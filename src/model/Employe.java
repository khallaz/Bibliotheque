package model;

public class Employe extends Personne {
    private int numeroEmploye;

    public Employe(String nom, String prenom, String adresse, int numeroEmploye) {
        super(nom, prenom, adresse);
        this.numeroEmploye = numeroEmploye;
    }

    public int getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(int numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    @Override
    public String toString() {
        return "Employe [" + super.toString() + ", Numero Employe: " + numeroEmploye + "]";
    }
}

