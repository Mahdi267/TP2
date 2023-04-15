package clientFX;

import server.models.Course;
import java.io.IOException;
import java.util.ArrayList;

public class Controleur {

    private Modele modele;
    private Vue vue;
    private ArrayList<Course> courses;

    /**
     *
     * @param m
     * @param v
     */
    public Controleur(Modele m, Vue v){
        this.modele = m;
        this.vue = v;

        this.vue.getCharger().setOnAction((actionEvent) -> {
            this.chrg();
        });

        this.vue.getEnvoyer().setOnAction((actionEvent) -> {
            this.inscr();
        });
    }

    /**
     *
     */
    private void chrg(){
        try {
            // Vider la table si des cours d'une autre session y sont affichés
            vue.getTable().getItems().clear();

            // Récupérer la session choisie
            String session = vue.getSession().getValue();

            courses = modele.charger(session);

            // Ajouter les cours dans la tableView
            for(Course c:courses) vue.getListeCours().add(c);

            this.vue.getTable().setItems(vue.getListeCours());

        }catch (IOException e){
            System.out.println("Erreur d'entré/sortie");
        }catch (ClassNotFoundException e){
            System.out.println("Classe introuvable");
        }
    }

    /**
     *
     */
    private void inscr(){
        try {
            String prenom = vue.getTextPrenom().getText();
            String nom = vue.getTextNom().getText();
            String email = vue.getTextEmail().getText();
            String matricule = vue.getTextMatricule().getText();

            // Gérer le cas ou les zones de textes sont vides
            Erreur erreur = new Erreur(vue);
            erreur.verifier();

            // Création du cours
            String nomCours = vue.getListeCours().get(0).getName();
            String codeCours = vue.getListeCours().get(0).getCode();
            String sessionCours = vue.getSession().getValue();

            Course cours = new Course(nomCours, codeCours, sessionCours);

            this.modele.inscrire(prenom, nom, email, matricule,cours);
        }catch (IOException e){
            System.out.println("Erreur d'entré/sortie");
        }
    }
}
