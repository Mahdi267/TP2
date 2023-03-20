package server.models;

import java.io.Serializable;

/**
 * Classe permettant l'inscription des nouveaux élèves.
 */
public class RegistrationForm implements Serializable {
    private String prenom;
    private String nom;
    private String email;
    private String matricule;
    private Course course;

    /**
     * Cré une nouvelle instance de la classe RegistrationForm.
     * @param prenom de type String, prénom de l'élève.
     * @param nom de type String, nom de l'élève.
     * @param email de type String, email de l'élève.
     * @param matricule de type String, matricule de l'élève.
     * @param course de type Course, cours suivit par l'élève.
     */
    public RegistrationForm(String prenom, String nom, String email, String matricule, Course course) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.matricule = matricule;
        this.course = course;
    }

    /**
     * Obtenir le prénom de l'élève.
     * @return prénom (String).
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Initialise le prénom de l'élève.
     * @param prenom de type String, prénom de l'élève.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtenir le nom de l'élève.
     * @return nom (String).
     */
    public String getNom() {
        return nom;
    }

    /**
     * Initialise le nom de l'élève.
     * @param nom de type String, nom de l'élève.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtenir l'email de l'élève.
     * @return email (String).
     */
    public String getEmail() {
        return email;
    }

    /**
     * Initialise l'email de l'élève.
     * @param email de type String, email de l'élève.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtenir le matricule de l'élève.
     * @return matricule (String).
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Initialise le matricule de l'élève.
     * @param matricule de type String, matricule de l'élève.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Obtenir le cours de l'élève.
     * @return cours (Course).
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Initialise le cours de l'élève.
     * @param course de type Course, cours de l'élèves
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Affiche les informations de l'élève inscrit.
     * @return informations de l'élève (String).
     */
    @Override
    public String toString() {
        return "InscriptionForm{" + "prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", email='" + email + '\'' + ", matricule='" + matricule + '\'' + ", course='" + course + '\'' + '}';
    }
}
