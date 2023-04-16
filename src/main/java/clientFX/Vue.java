package clientFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.models.Course;

/**
 * La classe Vue étend HBox pour créer la vue graphique de l'application.
 * Elle contient des éléments de saisie d'information pour l'inscription
 * à des cours universitaires, ainsi que la liste des cours disponibles
 * pour une session choisie.
 */
public class Vue extends HBox {
    private final Button charger = new Button("charger");
    private final Button envoyer = new Button("envoyer");
    private final ComboBox<String> session = new ComboBox<>();
    private final TextField textPrenom = new TextField();
    private final TextField textNom = new TextField();
    private final TextField textEmail = new TextField();
    private final TextField textMatricule = new TextField();
    private final TableView<Course> table = new TableView<>();
    private final ObservableList<Course> listeCours = FXCollections.observableArrayList();

    /**
     * Construit une vue graphique pour l'inscription à des cours universitaires.
     * La vue contient des éléments de saisie d'information pour l'inscription
     * à des cours universitaires, ainsi que la liste des cours disponibles
     * pour une session choisie.
     */
    public Vue(){
        // Ajouter les VBox à droite et à gauche du BorderPane et le séparateur
        VBox vBoxDroite = new VBox();
        VBox vBoxGauche = new VBox();
        this.getChildren().addAll(new Separator(), vBoxGauche, new Separator(), vBoxDroite, new Separator());
        this.setPrefWidth(600);

        // Ajouter et centrer les textes en haut des vBox
        vBoxGauche.setAlignment(Pos.TOP_CENTER);
        vBoxGauche.getChildren().add(new Text("Liste des cours"));
        vBoxGauche.setStyle("-fx-background-color: #F5F5DC;");
        vBoxGauche.setPrefWidth(this.getPrefWidth() / 2);
        vBoxDroite.setAlignment(Pos.TOP_CENTER);
        vBoxDroite.getChildren().add(new Text("Formulaire d'inscription"));
        vBoxDroite.setStyle("-fx-background-color: #F5F5DC;");
        vBoxDroite.setPrefWidth(this.getPrefWidth() / 2);

        // Ajouter la table où seront affichées les cours disponibles de la session choisie
        TableColumn<Course, String> code = new TableColumn<>("Code");
        TableColumn<Course, String> cours = new TableColumn<>("Cours");
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        cours.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().addAll(code, cours);
        vBoxGauche.setSpacing(5);
        vBoxGauche.setPadding(new Insets(10, 0, 0, 0));
        vBoxGauche.getChildren().add(table);
        vBoxGauche.getChildren().add(new Separator());
        table.setStyle("-fx-border-color: #ADD8E6; -fx-border-width: 1px;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Ajouter une HBox pour placer la liste déroulante et le bouton charger
        HBox sessionBox = new HBox();
        sessionBox.setSpacing(20);
        session.getItems().addAll("Hiver", "Automne", "Ete");
        sessionBox.setPrefWidth(vBoxGauche.getPrefWidth());
        sessionBox.setAlignment(Pos.CENTER);
        sessionBox.getChildren().addAll(session, charger);
        vBoxGauche.getChildren().add(sessionBox);
        vBoxGauche.getChildren().add(new Separator());

        // Ajouter les zones de texte pour entrer les informations sur la VBox de droite
        BorderPane prenomPane = new BorderPane();
            Text prenom = new Text("Prénom");
            prenomPane.setLeft(prenom);
            prenomPane.setRight(textPrenom);

        BorderPane nomPane = new BorderPane();
            Text nom = new Text("Nom");
            nomPane.setLeft(nom);
            nomPane.setRight(textNom);

        BorderPane emailPane = new BorderPane();
            Text email = new Text("Email");
            emailPane.setLeft(email);
            emailPane.setRight(textEmail);

        BorderPane martriculePane = new BorderPane();
            Text matricule = new Text("Matricule");
            martriculePane.setLeft(matricule);
            martriculePane.setRight(textMatricule);

        vBoxDroite.getChildren().addAll(prenomPane, nomPane, emailPane, martriculePane);
        vBoxGauche.setSpacing(15);
        vBoxDroite.setPadding(new Insets(10, 0, 0, 10));

        // Ajouter le bouton envoyer
        vBoxDroite.setSpacing(20);
        vBoxDroite.getChildren().add(envoyer);

    }

    /**
     * @return Bouton charger
     */
    public Button getCharger() {
        return charger;
    }

    /**
     * @return Bouton envoyer
     */
    public Button getEnvoyer() {
        return envoyer;
    }

    /**
     * @return Liste déroulante
     */
    public ComboBox<String> getSession() {
        return session;
    }

    /**
     * @return Champ de texte pour le prénom
     */
    public TextField getTextPrenom() {
        return textPrenom;
    }

    /**
     * @return Champ de texte pour le nom
     */
    public TextField getTextNom() {
        return textNom;
    }

    /**
     * @return Champ de texte pour l'email
     */
    public TextField getTextEmail() {
        return textEmail;
    }

    /**
     * @return Champ de texte pour le matricule
     */
    public TextField getTextMatricule() {
        return textMatricule;
    }

    /**
     * @return Tableau des cours de la session
     */
    public TableView<Course> getTable() {
        return table;
    }

    /**
     * @return Liste des cours disponible durant la session
     */
    public ObservableList<Course> getListeCours() {
        return listeCours;
    }
}
