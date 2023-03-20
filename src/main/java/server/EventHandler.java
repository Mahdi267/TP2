package server;

/**
 * Interface contenant la méthode pour gérer les commandes.
 */
@FunctionalInterface
public interface EventHandler {
    /**
     * Méthode à implémenter, permet de gérer les entrées en ligne de commande.
     * @param cmd de type String, correspond au nom de la commande passée en ligne de commande.
     * @param arg de type String, correspond aux arguments passés avec la commande.
     */
    void handle(String cmd, String arg);
}