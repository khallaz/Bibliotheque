package view;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;


public class BibliothequeAccueil extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Ajoutez le texte de bienvenue
        Label welcomeLabel = new Label("Bienvenue à la Bibliothèque");
        welcomeLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Ajoutez l'image de fond
        Image backgroundImage = new Image("file:images/bib3.jpg"); // Remplacez par le chemin de votre image
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, backgroundSize);
        root.setBackground(new Background(background));

        Button espaceAdminButton = new Button("Espace Admin");
        Button espaceClientButton = new Button("Espace Client");

        espaceAdminButton.setOnAction(event -> ouvrirEspaceAdmin());
        espaceClientButton.setOnAction(event -> ouvrirEspaceClient());

        root.getChildren().addAll(welcomeLabel, espaceAdminButton, espaceClientButton);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        primaryStage.setTitle("Bibliothèque - Accueil");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Maximise la fenêtre
        primaryStage.show();
    }
    


    private void ouvrirEspaceAdmin() {
        // Implémentez la logique pour ouvrir l'interface d'administration
        AdminBibliothequeVue adminVue = new AdminBibliothequeVue();
        Stage adminStage = new Stage();
        adminVue.start(adminStage);
    }

    private void ouvrirEspaceClient() {
        // Implémentez la logique pour ouvrir l'interface client
        ClientBibliothequeVue clientVue = new ClientBibliothequeVue();
        Stage clientStage = new Stage();
        clientVue.start(clientStage);
    }

}
