package clientFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.models.Course;

public class Vue extends HBox {
    private final Button charger = new Button("charger");
    private final Button envoyer = new Button("envoyer");
    private final ComboBox<String> session = new ComboBox<>();
    private final TextField textPrenom = new TextField();
    private final TextField textNom = new TextField();
    private final TextField textEmail = new TextField();
    private final TextField textMatricule = new TextField();

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
        TableView<Course> table = new TableView<>();
        TableColumn<Course, String> code = new TableColumn<>("Code");
        TableColumn<Course, String> cours = new TableColumn<>("Cours");
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
        session.getItems().addAll("Hiver", "Automne", "Été");
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
     * @return
     */
    public Button getCharger() {
        return charger;
    }

    /**
     * @return
     */
    public Button getEnvoyer() {
        return envoyer;
    }

    /**
     * @return
     */
    public ComboBox<String> getSession() {
        return session;
    }

    /**
     * @return
     */
    public TextField getTextPrenom() {
        return textPrenom;
    }

    /**
     * @return
     */
    public TextField getTextNom() {
        return textNom;
    }

    /**
     * @return
     */
    public TextField getTextEmail() {
        return textEmail;
    }

    /**
     * @return
     */
    public TextField getTextMatricule() {
        return textMatricule;
    }
}
