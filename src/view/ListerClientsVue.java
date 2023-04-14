package view;

import controller.BibliothequeController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;

import java.util.List;

public class ListerClientsVue {
    private BibliothequeController bibliothequeController;


    public ListerClientsVue(BibliothequeController bibliothequeController) {
        this.bibliothequeController = bibliothequeController;

    }

    public void afficherVue(Stage primaryStage) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Liste des clients");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        List<Client> clients = bibliothequeController.getListeClients();
        int rowIndex = 0;
        for (Client client : clients) {

            Label idLabel = new Label(String.valueOf(client.getNumeroClient()));
            Label clientLabel = new Label(client.getPrenom()+ " " +client.getNom());


            gridPane.add(clientLabel, 1, rowIndex);
            gridPane.add(idLabel, 0, rowIndex);


            rowIndex++;
        }

        Scene scene = new Scene(gridPane, 800, 600);
        scene.getStylesheets().add(ListerClientsVue.class.getResource("/css/styles.css").toExternalForm());
        window.setMaximized(true);
        window.setScene(scene);
        window.showAndWait();

    }
}
