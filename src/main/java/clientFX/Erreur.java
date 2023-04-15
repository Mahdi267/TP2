package clientFX;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Erreur {
    private final String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final String matriculePattern = "^[0-9]{8}$";
    private final Pattern emailRegex = Pattern.compile(emailPattern);
    private final Pattern matriculeRegex = Pattern.compile(matriculePattern);
    private Vue vue;

    /**
     *
     * @param v
     */
    public Erreur(Vue v){
        this.vue = v;
    }

    /**
     *
     */
    public void verifier(){
        TextField prenom = vue.getTextPrenom();
        TextField nom = vue.getTextNom();
        TextField email = vue.getTextEmail();
        TextField matricule = vue.getTextMatricule();

        prenom.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (prenom.getText().isBlank()) {
                    prenom.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    System.out.println("Veuillez saisir votre prénom");
                } else {
                    prenom.setStyle(null);
                }
            }
        });

        nom.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (nom.getText().isBlank()) {
                    nom.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    System.out.println("Veuillez saisir votre nom");
                }else {
                    nom.setStyle(null);
                }
            }
        });

        Matcher matcher = emailRegex.matcher(email.getText());
        email.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (email.getText().isBlank() | !matcher.matches()) {
                    email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    System.out.println("Veuillez saisir votre Email correctement");
                }else {
                    email.setStyle(null);
                }
            }
        });

        Matcher matcher1 = matriculeRegex.matcher(matricule.getText());
        matricule.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (matricule.getText().isBlank() | !matcher1.matches()) {
                    matricule.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    System.out.println("Veuillez saisir votre matricule correctement");
                }
            }
        });
    }
}
