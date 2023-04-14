package clientFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application{
    @Override
    public void start(Stage stage) {
        Vue vue = new Vue();
        Scene scene = new Scene(vue, 600, 500);

        stage.setScene(scene);
        stage.setTitle("titre");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
