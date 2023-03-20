package server;

import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe contenant les instructions côté serveur.
 */
public class Server {
    /**
     * Constante de type String, fait référence à une commande d'inscription.
     */
    public final static String REGISTER_COMMAND = "INSCRIRE";
    /**
     * Constante de type String, fait référence à une commande de chargement de cours.
     */
    public final static String LOAD_COMMAND = "CHARGER";
    private final ServerSocket server;
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ArrayList<EventHandler> handlers;

    /**
     * Crée une nouvelle instance de la classe Server avec le paramètre spécifié
     * @param port de type entier (int), correspondant au port avec lequel le server sera lié
     * @throws IOException si il y a création d'un socket de serveur non lié.
     */
    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1);
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
    }

    /**
     * Ajoute un évènement à gérer dans le arrayList handlers
     * @param h de type EventHandler, correspond à un évènement passé en ligne de commande.
     */
    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }

    /**
     * Gère les évènements présents dans le arrayList handlers en appelant la méthode handle()
     * @param cmd de type String, correspond à la commande passée en ligne de commande
     * @param arg de type String, correspond au(x) paramètre(s) passé(s) à la commande.
     */
    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    /**
     * Envoit une requête au serveur qui renvoit un socket, affiche le message
     * "Conneté au client:" plus le nom du socket, enregistre le input et
     * le output du socket, lance la méthode listen(), puis la méthode disconnect()
     * et affiche le message "Client déconnecté!".
     */
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Connecté au client: " + client);
                objectInputStream = new ObjectInputStream(client.getInputStream());
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                listen();
                disconnect();
                System.out.println("Client déconnecté!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Si le input du socket n'est pas vide, le formater avec la méthode
     * processCommandLine(),créer les deux parties de la commande
     * (la commande et l'argument de la commande) et gérer la commande avec
     * la méthode alertHandlers().
     * @throws IOException si une erreur d'E/S se produit lors de la lecture de l'en-tête de flux.
     * @throws ClassNotFoundException la classe d'un objet sérialisé est introuvable.
     */
    public void listen() throws IOException, ClassNotFoundException {
        String line;
        if ((line = this.objectInputStream.readObject().toString()) != null) {
            Pair<String, String> parts = processCommandLine(line);
            String cmd = parts.getKey();
            String arg = parts.getValue();
            this.alertHandlers(cmd, arg);
        }
    }

    /**
     * Sépare la chaîne de caractère correspondant à la commande entrée en ligne
     * de commande, crée un tableau de taille 2 où chaque index contient un élement
     * de la commande séparé par un espace.
     * @param line de type String, correspond à la commande passée en ligne de commande.
     * @return Pair avec la commande comme Key et l'argument de la commmande en value.
     */
    public Pair<String, String> processCommandLine(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        String args = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        return new Pair<>(cmd, args);
    }

    /**
     * Ferme la connexion entre le client et le serveur.
     * @throws IOException si une erreur d'E/S se produit lors de la lecture de l'en-tête de flux.
     */
    public void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        client.close();
    }

    /**
     * Vérifie si la commande est une commande d'inscription et, dans ce cas,
     * lancer la méthode handleRegistration(), sinon lance la méthode handleLoadCourses(arg).
     * @param cmd de type String, correspond à la commande entrée en ligne de commande.
     * @param arg de type String, correspond à l'argument de la commande entrée en ligne de commande.
     */
    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
            handleRegistration();
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     Lire un fichier texte contenant des informations sur les cours et les transofmer en liste d'objets 'Course'.
     La méthode filtre les cours par la session spécifiée en argument.
     Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     @param arg la session pour laquelle on veut récupérer la liste des cours.
     @throws Exception si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet dans le flux.
     */
    public void handleLoadCourses(String arg) {
        // TODO: implémenter cette méthode
    }

    /**
     Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un fichier texte
     et renvoyer un message de confirmation au client.
     @throws Exception si une erreur se produit lors de la lecture de l'objet, l'écriture dans un fichier ou dans le flux de sortie.
     */
    public void handleRegistration() {
        // TODO: implémenter cette méthode
    }
}
