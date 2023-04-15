package clientFX;


import server.models.Course;
import server.models.RegistrationForm;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class Modele {
    private final int PORT = 1337;

    /**
     *
     * @param session
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
     *
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
