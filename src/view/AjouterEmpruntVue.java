package view;

import controller.BibliothequeController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Client;
import model.Document;
import model.Emprunt;

import java.time.LocalDate;

public class AjouterEmpruntVue {

    private BibliothequeController bibliothequeController;

    public AjouterEmpruntVue(BibliothequeController bibliothequeController) {
        this.bibliothequeController = bibliothequeController;
    }

    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        Label clientLabel = new Label("Client:");
        ComboBox<Client> clientComboBox = new ComboBox<>();
        // Remplie la liste déroulante avec les clients disponibles
        clientComboBox.getItems().addAll(bibliothequeController.getListeClients());
        root.add(clientLabel, 0, 0);
        root.add(clientComboBox, 1, 0);

        Label documentLabel = new Label("Document:");
        ComboBox<Document> documentComboBox = new ComboBox<>();
        // Remplie la liste déroulante avec les documents disponibles
        documentComboBox.getItems().addAll(bibliothequeController.getDocuments());
        root.add(documentLabel, 0, 1);
        root.add(documentComboBox, 1, 1);

        Label dateEmpruntLabel = new Label("Date d'emprunt:");
        DatePicker dateEmpruntPicker = new DatePicker();
        root.add(dateEmpruntLabel, 0, 2);
        root.add(dateEmpruntPicker, 1, 2);

        Button addButton = new Button("Ajouter");
        addButton.setOnAction(e -> {
            Client client = clientComboBox.getSelectionModel().getSelectedItem();
            Document document = documentComboBox.getSelectionModel().getSelectedItem();
            LocalDate dateEmprunt = dateEmpruntPicker.getValue();

            Emprunt emprunt = new Emprunt(client, document, dateEmprunt);
            bibliothequeController.ajouterEmprunt(emprunt);

            stage.close();
        });

        root.add(addButton, 1, 3);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setTitle("Ajouter un emprunt");
        stage.setScene(scene);
        stage.show();
    }
}
