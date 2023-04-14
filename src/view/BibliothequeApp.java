package view;

import controller.BibliothequeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class BibliothequeApp extends Application {

    private BibliothequeController bibliothequeController;
    private BibliothequeVue bibliothequeVue;

    @Override
    public void start(Stage primaryStage) {
        bibliothequeController = new BibliothequeController();
        bibliothequeVue = new BibliothequeVue(bibliothequeController);
        bibliothequeVue.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
