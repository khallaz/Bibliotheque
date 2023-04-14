package view;

import controller.BibliothequeController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CD;
import model.Document;
import model.Journal;
import model.Livre;

public class AjouterDocumentVue {
    private BibliothequeController bibliothequeController;

    private ComboBox<String> comboBoxTypeDocument;
    private TextField titleTextField;
    private TextField auteurTextField;
    private TextField anneeDePublicationTextField;
    private TextField nombreExemplairesTextField;
    private TextField infoSupplementaireTextField;

    public AjouterDocumentVue(BibliothequeController bibliothequeController) {
        this.bibliothequeController = bibliothequeController;
    }

    public void afficherVue(Stage stage) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ajouter un document");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // ComboBox et Label
        Label comboBoxLabel = new Label("Type de document:");
        comboBoxTypeDocument = new ComboBox<>();
        comboBoxTypeDocument.getItems().addAll("Livre", "CD", "Journal");
        grid.add(comboBoxLabel, 0, 0);
        grid.add(comboBoxTypeDocument, 1, 0);

        // Champs de texte et labels
        Label titleLabel = new Label("Titre:");
        titleTextField = new TextField();
        grid.add(titleLabel, 0, 1);
        grid.add(titleTextField, 1, 1);

        Label auteurLabel = new Label("Auteur:");
        auteurTextField = new TextField();
        grid.add(auteurLabel, 0, 2);
        grid.add(auteurTextField, 1, 2);

        Label anneeDePublicationLabel = new Label("Année de publication:");
        anneeDePublicationTextField = new TextField();
        grid.add(anneeDePublicationLabel, 0, 3);
        grid.add(anneeDePublicationTextField, 1, 3);

        Label nombreExemplairesLabel = new Label("Nombre d'exemplaires:");
        nombreExemplairesTextField = new TextField();
        grid.add(nombreExemplairesLabel, 0, 4);
        grid.add(nombreExemplairesTextField, 1, 4);

        Label infoSupplementaireLabel = new Label("Info supplémentaire:");
        infoSupplementaireTextField = new TextField();
        grid.add(infoSupplementaireLabel, 0, 5);
        grid.add(infoSupplementaireTextField, 1, 5);

        // Mettre à jour les champs de texte en fonction du type de document sélectionné
        comboBoxTypeDocument.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Livre":
                        infoSupplementaireLabel.setText("ISBN:");
                        break;
                    case "CD":
                        infoSupplementaireLabel.setText("Genre:");
                        break;
                    case "Journal":

                        infoSupplementaireLabel.setText("Numéro d'édition:");
                        break;
                }
            }
        });

        Button ajouterDocumentButton = new Button("Ajouter document");
        ajouterDocumentButton.setOnAction(e -> {
            int choix = comboBoxTypeDocument.getSelectionModel().getSelectedIndex() + 1;
            String type = comboBoxTypeDocument.getSelectionModel().getSelectedItem();
            String titre = titleTextField.getText();
            String auteur = auteurTextField.getText();
            int anneeDePublication = Integer.parseInt(anneeDePublicationTextField.getText());
            int nombreExemplaires = Integer.parseInt(nombreExemplairesTextField.getText());
            String infoSupplementaire = infoSupplementaireTextField.getText();

            ajouterDocument(choix, type, titre, auteur, anneeDePublication, nombreExemplaires, infoSupplementaire);

            // Efface les champs de texte après avoir ajouté le document
            titleTextField.clear();
            auteurTextField.clear();
            anneeDePublicationTextField.clear();
            nombreExemplairesTextField.clear();
            infoSupplementaireTextField.clear();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, ajouterDocumentButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(AjouterDocumentVue.class.getResource("/css/styles.css").toExternalForm());
        window.setMaximized(true); // Maximise la fenêtre
        window.setScene(scene);
        window.showAndWait();
    }

    public void ajouterDocument(int choix, String type, String titre, String auteur, int anneeDePublication, int nombreExemplaires, String infoSupplementaire) {
        Document document;

        switch (choix) {
            case 1:
                String isbn = infoSupplementaire;
                document = new Livre(type, titre, auteur, anneeDePublication, nombreExemplaires, isbn);
                break;
            case 2:
                String genre = infoSupplementaire;
                document = new CD(type, titre, auteur, anneeDePublication, nombreExemplaires, genre);
                break;
            case 3:
                int numeroEdition = Integer.parseInt(infoSupplementaire);
                document = new Journal(type, titre, auteur, anneeDePublication, nombreExemplaires, numeroEdition);
                break;
            default:
                System.out.println("Choix invalide. Retour au menu principal.");
                return;
        }

        bibliothequeController.ajouterDocument(document);
    }

}
