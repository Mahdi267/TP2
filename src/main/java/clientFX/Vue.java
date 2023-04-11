package clientFX;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.models.Course;

public class Vue extends HBox {
    private final Button charger = new Button("charger");
    private final Button envoyer = new Button("envoyer");
    private final ComboBox<String> session = new ComboBox<>();
    private final Text prenom = new Text("Prénom");
    private final Text nom = new Text("Nom");
    private final Text email = new Text("Email");
    private final Text matricule = new Text("Matricule");
    private final Separator vsep = new Separator(Orientation.VERTICAL);

    public Vue(){
        // Ajouter les VBox à droite et à gauche du BorderPane et le séparateur
        VBox vBoxDroite = new VBox();
        VBox vBoxGauche = new VBox();
        Separator hsep = new Separator(Orientation.HORIZONTAL);
        this.getChildren().addAll(vBoxGauche, hsep, vBoxDroite);

        // Ajouter et centrer les textes en haut des vBox
        vBoxGauche.setAlignment(Pos.CENTER);
        vBoxGauche.getChildren().add(new Text("Liste des cours"));
        vBoxDroite.setAlignment(Pos.CENTER);
        vBoxDroite.getChildren().add(new Text("Formulaire d'inscription"));

        // Ajouter la table où seront affichées les cours disponibles de la session choisie
        TableView<Course> table = new TableView<>();
        TableColumn<Course, String> code = new TableColumn<>("Code");
        TableColumn<Course, String> cours = new TableColumn<>("Cours");
        table.getColumns().addAll(code, cours);
        vBoxGauche.setSpacing(5);
        vBoxGauche.setPadding(new Insets(10, 0, 0, 10));
        vBoxGauche.getChildren().add(table);

        // Ajouter une HBox pour placer la liste déroulante et le bouton charger
        vBoxGauche.setSpacing(5);
        HBox sessionBox = new HBox();
        sessionBox.setAlignment(Pos.CENTER);
        sessionBox.getChildren().add(session);
        sessionBox.setSpacing(10);
        sessionBox.getChildren().add(charger);
        vBoxGauche.getChildren().add(sessionBox);
    }

}
