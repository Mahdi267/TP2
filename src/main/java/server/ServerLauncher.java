package server;

/**
 * Classe permettant de lancer le serveur.
 */
public class ServerLauncher {
    /**
     * Constante de type int, correspond au port de connexion avec le socket.
     */
    public final static int PORT = 1337;

    /**
     * Permet le lancement du serveur.
     * @param args de type tableau de String, commande passée au lancement du programme.
     */
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}