package view;

import controller.BibliothequeController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;

import java.util.List;

public class BibliothequeVue {
    private final BibliothequeController controller;
    private Stage stage;

    public BibliothequeVue(BibliothequeController controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        primaryStage.setTitle("Gestion de la bibliothèque");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        VBox menuBox = createMenu();
        root.setTop(menuBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMenu() {
        VBox menuBox = new VBox(10);
        menuBox.setAlignment(Pos.CENTER);

        Button listerLivresBtn = new Button("Lister les livres");
        //listerLivresBtn.setOnAction(event -> listerLivres());

        Button listerClientsBtn = new Button("Lister les clients");
        listerClientsBtn.setOnAction(event -> listerClients());

        menuBox.getChildren().addAll(listerLivresBtn, listerClientsBtn);
        return menuBox;
    }

    private void listerClients() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        List<Client> clients = controller.getListeClients();
        int rowIndex = 0;
        for (Client client : clients) {
            Label nameLabel = new Label(client.getNom() + " " + client.getPrenom());
            Label addressLabel = new Label(client.getAdresse());

            gridPane.add(nameLabel, 0, rowIndex);
            gridPane.add(addressLabel, 1, rowIndex);

            rowIndex++;
        }

        stage.setScene(new Scene(gridPane, 800, 600));
    }



}
