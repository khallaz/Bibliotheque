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
import model.Employe;

public class AjouterEmployeVue {

    private BibliothequeController bibliothequeController;

    public AjouterEmployeVue(BibliothequeController bibliothequeController) {
        this.bibliothequeController = bibliothequeController;
    }

    public void start(Stage primaryStage) {
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

        Label employeNumberLabel = new Label("Numéro employé:");
        TextField employeNumberField = new TextField();
        root.add(employeNumberLabel, 0, 3);
        root.add(employeNumberField, 1, 3);

        Button addButton = new Button("Ajouter");
        addButton.setOnAction(e -> {
            String nom = nameField.getText();
            String prenom = firstNameField.getText();
            String adresse = addressField.getText();
            int numeroEmploye = Integer.parseInt(employeNumberField.getText());

            Employe employe = new Employe(nom, prenom, adresse, numeroEmploye);
            bibliothequeController.ajouterEmploye(employe);

            primaryStage.close();
        });

        root.add(addButton, 1, 4);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        primaryStage.setTitle("Ajouter un employé");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
