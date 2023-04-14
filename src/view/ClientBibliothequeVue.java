package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientBibliothequeVue {

    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Espace Client");
        // Ajoutez ici d'autres éléments de l'interface utilisateur pour l'espace client

        root.getChildren().add(titleLabel);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        primaryStage.setTitle("Bibliothèque - Espace Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
