package clientFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe MainFX est une classe qui hérite de la classe Application
 * de JavaFX et qui est utilisée pour démarrer l'application.
 */
public class MainFX extends Application{
    /**
     * Crée une instance de la classe Modele, une instance de la classe Vue,
     * et une instance de la classe Controleur qui prend comme paramètres
     * les instances du modèle et de la vue.
     * @param stage l'objet Stage qui représente la fenêtre principale de l'application.
     */
    @Override
    public void start(Stage stage) {
        Modele modele = new Modele();
        Vue vue = new Vue();
        Controleur controleur = new Controleur(modele, vue);

        Scene scene = new Scene(vue, 600, 500);

        stage.setScene(scene);
        stage.setTitle("Inscription UDEM");
        stage.show();
    }

    /**
     * La méthode main() est la méthode principale de l'application qui lance l'application
     * en appelant la méthode statique launch() de la classe Application fournie par JavaFX.
     * Elle prend en paramètreun tableau de chaînes de caractères représentant les arguments
     * de la ligne de commande.
     * @param args un tableau de chaînes de caractères représentant les arguments
     *             de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
}
