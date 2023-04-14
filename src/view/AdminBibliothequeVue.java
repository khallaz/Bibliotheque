package view;

import controller.BibliothequeController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class AdminBibliothequeVue extends Application {
    private BibliothequeController bibliothequeController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        bibliothequeController = new BibliothequeController();
        afficherOptionsAdmin();
    }

    private void afficherOptionsAdmin() {
        Stage optionsStage = new Stage();

        BorderPane root = new BorderPane();

        // Remplacez par le chemin de votre image
        Image image = new Image(("file:images/bib1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(root.heightProperty());

        //imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());

        VBox options = new VBox(10);
        options.setAlignment(Pos.CENTER);
        options.setPadding(new Insets(10));

        Button gestionClientsEmployesButton = new Button("Gestion des clients et employés");
        Button gestionDocumentsButton = new Button("Gestion des documents");
        Button retourButton = new Button("Retour");

        gestionClientsEmployesButton.setOnAction(event -> {
            optionsStage.close();
            ouvrirGestionClientsEmployes();
        });

        gestionDocumentsButton.setOnAction(event -> {
            optionsStage.close();
            ouvrirGestionDocuments();
        });

        retourButton.setOnAction(event -> {
            optionsStage.close();

        });

        options.getChildren().addAll(gestionClientsEmployesButton, gestionDocumentsButton, retourButton);

        root.setLeft(imageView);
        root.setCenter(options);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        optionsStage.setTitle("Bibliothèque - Interface d'administration");
        optionsStage.setScene(scene);
        optionsStage.setMaximized(true);
        optionsStage.show();
    }


    private void ouvrirGestionClientsEmployes() {
        Stage gestionClientsEmployesStage = new Stage();

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Ajout les boutons pour les fonctionnalités liées aux clients et employés
        Button ajouterClientButton = new Button("Ajouter un client");
        Button ajouterEmployeButton = new Button("Ajouter un employé");

        // Ajoute les actions pour chaque bouton
        ajouterClientButton.setOnAction(event -> ajouterClient());
        ajouterEmployeButton.setOnAction(event -> ajouterEmploye());

        // Ajout les boutons au layout
        root.getChildren().addAll(ajouterClientButton, ajouterEmployeButton);

        // Crée la scène et l'afficher
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        gestionClientsEmployesStage.setTitle("Bibliothèque - Gestion des clients et employés");
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        gestionClientsEmployesStage.setScene(scene);
        gestionClientsEmployesStage.setMaximized(true);
        gestionClientsEmployesStage.show();
    }


    private void ouvrirGestionDocuments() {
        GestionDocumentsVue gestionDocumentsVue = new GestionDocumentsVue(bibliothequeController);
        Stage gestionDocumentsStage = new Stage();
        gestionDocumentsVue.start(gestionDocumentsStage);

        Button ajouterDocumentButton = new Button("Ajouter un document");
        ajouterDocumentButton.setOnAction(event -> ouvrirAjouterDocument());
    }




    private void ajouterEmploye() {
        AjouterEmployeVue ajouterEmployeVue = new AjouterEmployeVue(bibliothequeController);
        Stage ajouterEmployeStage = new Stage();
        ajouterEmployeVue.start(ajouterEmployeStage);
    }

    private void ajouterClient() {
        AjouterClientVue ajouterClientVue = new AjouterClientVue(bibliothequeController);
        Stage ajouterClientStage = new Stage();
        AjouterClientVue.start(ajouterClientStage);
    }

    private void ouvrirAjouterDocument() {
        AjouterDocumentVue ajouterDocumentVue = new AjouterDocumentVue(bibliothequeController);
        Stage ajouterDocumentStage = new Stage();
        ajouterDocumentVue.afficherVue(ajouterDocumentStage);
    }


}
