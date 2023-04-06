package view;
import model.*;
import controller.BibliothequeController;


import java.util.Scanner;

public class BibliothequeVue {
    private BibliothequeController bibliothequeController;
    private Scanner scanner;

    public BibliothequeVue() {
        bibliothequeController = new BibliothequeController();
        scanner = new Scanner(System.in);
    }

    public void afficherMenu() {
        System.out.println("Bienvenue dans le système de gestion de la bibliothèque!");
        System.out.println("Veuillez choisir une option:");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Ajouter un document");
        // Ajoutez des options pour les autres classes (Employe, Document, Emprunt, Reservation)
        System.out.println("0. Quitter");
    }
    public void ajouterClient() {
        System.out.println("Entrez le nom du client:");
        String nom = scanner.nextLine();
        System.out.println("Entrez le prénom du client:");
        String prenom = scanner.nextLine();
        System.out.println("Entrez l'adresse du client:");
        String adresse = scanner.nextLine();
        System.out.println("Entrez le numéro du client:");
        int numeroClient = scanner.nextInt();
        scanner.nextLine();

        Client client = new Client(nom, prenom, adresse, numeroClient);
        bibliothequeController.ajouterClient(client);
    }

    public void ajouterDocument() {
        System.out.println("Quel type de document souhaitez-vous ajouter?");
        System.out.println("1. Livre");
        System.out.println("2. CD");
        System.out.println("3. Journal");
        System.out.print("Votre choix: ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Entrez le type du document:");
        String type = scanner.nextLine();
        System.out.println("Entrez l'identiant du document:");
        String id = scanner.nextLine();
        System.out.println("Entrez le titre du document:");
        String titre = scanner.nextLine();
        System.out.println("Entrez l'auteur du document:");
        String auteur = scanner.nextLine();
        System.out.println("Entrez le nombre d'exemplaires du document:");
        int nombreExemplaires = scanner.nextInt();
        System.out.println("Entrez l'année de publication du document:");
        int anneeDePublication = scanner.nextInt();
        scanner.nextLine();

        Document document;

        switch (choix) {
            case 1:
                System.out.println("Entrez l'isbn du livre:");
                String isbn = scanner.nextLine();
                scanner.nextLine();
                document = new Livre(type, id, titre, auteur, anneeDePublication, nombreExemplaires, isbn);
                break;
            case 2:
                System.out.println("Entrez le genre du CD ");
                String genre = scanner.nextLine();
                scanner.nextLine();
                document = new CD(type, id, titre, auteur, anneeDePublication, nombreExemplaires, genre);
                break;
            case 3:
                System.out.println("Entrez le numéro du journal:");
                int numeroEdition = scanner.nextInt();
                scanner.nextLine();
                document = new Journal(type, id, titre, auteur, anneeDePublication, nombreExemplaires, numeroEdition);
                break;
            default:
                System.out.println("Choix invalide. Retour au menu principal.");
                return;
        }

        bibliothequeController.ajouterDocument(document);
    }

        public int getChoixUtilisateur () {
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            return choix;
        }

        public void traiterChoix ( int choix){
            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:
                    ajouterDocument();
                    break;
                // Ajoutez des cas pour les autres classes (Employe, Document, Emprunt, Reservation)
                case 0:
                    System.out.println("Au revoir !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }



}
