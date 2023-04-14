package view;

import controller.BibliothequeController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Client;

import java.util.List;

public class AjouterClientVue {

    private static BibliothequeController bibliothequeController;

    public AjouterClientVue(BibliothequeController bibliothequeController) {
        this.bibliothequeController = bibliothequeController;
    }

    public static void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        Label nameLabel = new Label("Nom:");
        TextField nameField = new TextField();
        root.add(nameLabel, 0, 0);
        root.add(nameField, 1, 0);

        Label firstNameLabel = new Label("Prénom:");
        TextField firstNameField = new TextField();
        root.add(firstNameLabel, 0, 1);
        root.add(firstNameField, 1, 1);

        Label addressLabel = new Label("Adresse:");
        TextField addressField = new TextField();
        root.add(addressLabel, 0, 2);
        root.add(addressField, 1, 2);

        Label clientNumberLabel = new Label("Numéro de client:");
        TextField clientNumberField = new TextField();
        root.add(clientNumberLabel, 0, 3);
        root.add(clientNumberField, 1, 3);

        Button addButton = new Button("Ajouter");
        Button listerClientsButton = new Button("Lister les clients");

        addButton.setOnAction(e -> {
            String nom = nameField.getText();
            String prenom = firstNameField.getText();
            String adresse = addressField.getText();
            int numeroClient = Integer.parseInt(clientNumberField.getText());

            Client client = new Client(nom, prenom, adresse, numeroClient);
            bibliothequeController.ajouterClient(client);

            primaryStage.close();
        });

        listerClientsButton.setOnAction(e -> {
            ListerClientsVue listerClientsVue = new ListerClientsVue(bibliothequeController);
            listerClientsVue.afficherVue(primaryStage);
        });



        root.add(addButton, 1, 4);
        root.add(listerClientsButton, 1, 5);


        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(AjouterClientVue.class.getResource("/css/styles.css").toExternalForm());
        primaryStage.setTitle("Ajouter un client");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
