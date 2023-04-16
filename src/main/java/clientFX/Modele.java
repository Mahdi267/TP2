package clientFX;


import server.models.Course;
import server.models.RegistrationForm;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class Modele {
    private final int PORT = 1337;

    /**
     * Envoie la commande "CHARGER" au serveur et récupère une ArrayList de Course
     * @param session de type String, session choisie par l'utilisateur
     * @return ArrayList de Course avec les cours disponibles durant la session choisie
     * @throws IOException si une erreur d'E/S se produit lors de la lecture de l'en-tête de flux.
     * @throws ClassNotFoundException la classe d'un objet sérialisé est introuvable.
     */
    public ArrayList<Course> charger(String session) throws IOException, ClassNotFoundException {
        // Connexion avec le serveur
        Socket socket = new Socket("localhost", PORT);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        // Envoyer la commande CHARGER au serveur avec la session choisie
        oos.writeObject("CHARGER " + session);

        // Récupérer le tableau de Course envoyé par le serveur
        ArrayList<Course> courses = (ArrayList<Course>)ois.readObject();

        return courses;
    }

    /**
     * Envoie la commande INSCRIRE au serveur pour inscrire une personne à un cours
     * @param prenom prénom de la personne
     * @param nom nom de la personne
     * @param email email de la persone
     * @param matricule matricule de la personne
     * @param cours cours auquel la personne veut s'inscrire
     * @throws IOException si une erreur d'E/S se produit lors de la lecture de l'en-tête de flux.
     */
    public void inscrire(String prenom, String nom, String email, String matricule, Course cours) throws IOException {
        // Connexion avec le serveur
        Socket socket = new Socket("localhost", PORT);
        ObjectOutputStream oos= new ObjectOutputStream(socket.getOutputStream());

        // Envoyer la commande INSCRIRE
        oos.writeObject("INSCRIRE");

        // Envoyer l'objet RegistrationForm au serveur pour faire l'inscription
        oos.writeObject(new RegistrationForm(prenom, nom, email, matricule, cours));
    }
}
