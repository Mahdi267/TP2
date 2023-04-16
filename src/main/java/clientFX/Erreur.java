package clientFX;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe est utilisée pour vérifier si les champs
 * de la vue associée à l'instance de la classe Erreur sont correctement remplis.
 */
public class Erreur {
    private final String emailPattern = "^[A-Za-z0-9._]+@[A-Za-z0-9._]+\\.[a-z]+$";
    private final String matriculePattern = "^[0-9]{8}$";
    private final Pattern emailRegex = Pattern.compile(emailPattern);
    private final Pattern matriculeRegex = Pattern.compile(matriculePattern);
    private Vue vue;

    /**
     * Crée une nouvelle instance de la classe Erreur avec la vue spécifiée.
     * @param v la vue associée à l'instance de l'erreur
     */
    public Erreur(Vue v){
        this.vue = v;
    }

    /**
     * Vérifie si les champs de la vue associée à l'instance de la classe Erreur sont correctement remplis.
     * Le nom, l'email et le matricule.
     * @return  true si tous les champs sont correctement remplis, false sinon
     */
    public boolean verifierText(){
        TextField prenom = vue.getTextPrenom();
        TextField nom = vue.getTextNom();
        TextField email = vue.getTextEmail();
        TextField matricule = vue.getTextMatricule();

        int i = 0;

        if (prenom.getText().isBlank()) {
            prenom.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
            System.out.println("Veuillez saisir votre prénom");
            i++;
        } else {
            prenom.setStyle(null);
        }

        if (nom.getText().isBlank()) {
            nom.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
            System.out.println("Veuillez saisir votre nom");
            i++;
        }else {
            nom.setStyle(null);
        }

        Matcher matcher = emailRegex.matcher(email.getText());
        if (email.getText().isBlank() | !matcher.matches()) {
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
            System.out.println("Veuillez saisir votre Email correctement");
            i++;
        }else {
            email.setStyle(null);
        }

        Matcher matcher1 = matriculeRegex.matcher(matricule.getText());
        if (matricule.getText().isBlank() | !matcher1.matches()) {
            matricule.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
            System.out.println("Veuillez saisir votre matricule correctement");
            i++;
        }else {
            matricule.setStyle(null);
        }

        if(i != 0){
            System.out.println("#----------------------------------------------#");
            return false;
        }
        return true;
    }

    /**
     * Vérifie si une ligne est sélectionnée dans la table de vue.
     * @return true si une ligne est sélectionnée dans la table de vue, sinon false.
     */
    public boolean verifierTable(){
        if(vue.getTable().getSelectionModel().getSelectedItems().isEmpty()){
            System.out.println("Veuillez choisir un cours SVP");
            System.out.println("#----------------------------------------------#");
            return false;
        }
        return true;
    }
}
