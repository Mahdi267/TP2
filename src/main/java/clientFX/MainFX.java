package clientFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application{
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

    public static void main(String[] args) {
        launch(args);
    }
}
