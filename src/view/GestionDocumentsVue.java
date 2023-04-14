package view;

import controller.BibliothequeController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.util.Callback;
import model.Emprunt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reservation;


public class GestionDocumentsVue extends Application {
    private BibliothequeController bibliothequeController;
    private AjouterDocumentVue ajouterDocumentVue;



    public GestionDocumentsVue(BibliothequeController bibliothequeController) {
        this.bibliothequeController = bibliothequeController;
        this.ajouterDocumentVue = new AjouterDocumentVue(bibliothequeController);
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Crée la liste des options
        VBox options = new VBox(10);
        options.setAlignment(Pos.CENTER);
        options.setPrefWidth(200);

        Text ajouterDocumentText = new Text("Ajouter document");
        Text ajouterEmpruntText = new Text("Ajouter emprunt");
        Text afficherListeEmpruntsText = new Text("Afficher la liste des emprunts");
        Text afficherListeReservationsText = new Text("Afficher la liste des réservations");

        ajouterDocumentText.getStyleClass().add("clickable-text");
        ajouterEmpruntText.getStyleClass().add("clickable-text");
        afficherListeEmpruntsText.getStyleClass().add("clickable-text");
        afficherListeReservationsText.getStyleClass().add("clickable-text");


        options.getChildren().addAll(ajouterDocumentText, ajouterEmpruntText, afficherListeEmpruntsText, afficherListeReservationsText);

        root.setLeft(options);

        Image backgroundImage= new Image("file:images/bg3.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, backgroundSize);
        root.setBackground(new Background(background));

        Text heading = new Text("Gestion de Bibliothèque");
        heading.getStyleClass().add("heading");
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.getChildren().addAll(heading);

        root.setCenter(centerContent);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setTitle("Gestion des documents");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();



        ajouterDocumentText.setOnMouseClicked(event -> {
            System.out.println("Ajouter document");
            ajouterDocumentVue.afficherVue(stage);
        });



        ajouterEmpruntText.setOnMouseClicked(event -> {
            System.out.println("Ajouter emprunt");
            AjouterEmpruntVue ajouterEmpruntVue = new AjouterEmpruntVue(bibliothequeController);
            ajouterEmpruntVue.start(stage);
        });

        afficherListeEmpruntsText.setOnMouseClicked(event -> {
            System.out.println("Afficher la liste des emprunts");
            listerEmprunts(stage);
        });

        afficherListeReservationsText.setOnMouseClicked(event -> {
            System.out.println("Afficher la liste des réservations");
            listerReservations(stage);
        });


    }

    private void listerEmprunts(Stage stage) {
        TableView<Emprunt> tableView = new TableView<>();

        TableColumn<Emprunt, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getDisplayName()));

        TableColumn<Emprunt, String> documentColumn = new TableColumn<>("Document");
        documentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));

        TableColumn<Emprunt, String> dateEmpruntColumn = new TableColumn<>("Date d'emprunt");
        dateEmpruntColumn.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));

        TableColumn<Emprunt, String> dateRetourColumn = new TableColumn<>("Date de retour");
        dateRetourColumn.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        // Ajout de la colonne des boutons de suppression
        TableColumn<Emprunt, Void> supprimerColumn = new TableColumn<>("Supprimer");
        supprimerColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Emprunt, Void> call(final TableColumn<Emprunt, Void> param) {
                final TableCell<Emprunt, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Emprunt emprunt = getTableView().getItems().get(getIndex());
                            bibliothequeController.supprimerEmprunt(emprunt);
                            getTableView().getItems().remove(emprunt);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });

        tableView.getColumns().addAll(clientColumn, documentColumn, dateEmpruntColumn, dateRetourColumn, supprimerColumn);

        ObservableList<Emprunt> emprunts = FXCollections.observableArrayList(bibliothequeController.getListeEmprunts());
        tableView.setItems(emprunts);

        VBox vBox = new VBox(tableView);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10, 10, 20, 20));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(vBox, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }


    private void listerReservations(Stage stage) {
        TableView<Reservation> tableView = new TableView<>();

        TableColumn<Reservation, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getDisplayName()));

        TableColumn<Reservation, String> documentColumn = new TableColumn<>("Reservation");
        documentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));

        TableColumn<Reservation, String> dateReserveColumn = new TableColumn<>("Date de réservation");
        dateReserveColumn.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));


        // Ajout de la colonne des boutons de suppression
        TableColumn<Reservation, Void> supprimerColumn = new TableColumn<>("Supprimer");
        supprimerColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                final TableCell<Reservation, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            bibliothequeController.supprimerreservation(reservation);
                            getTableView().getItems().remove(reservation);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });


        tableView.getColumns().addAll(clientColumn, documentColumn, dateReserveColumn, supprimerColumn);

        ObservableList<Reservation> reservations = FXCollections.observableArrayList(bibliothequeController.getListeReservations());
        tableView.setItems(reservations);

        VBox vBox = new VBox(tableView);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10, 10, 20, 20));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(vBox, screenBounds.getWidth(), screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }


}
