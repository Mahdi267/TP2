package clientFX;

/**
 * La classe RunMain permet d'appeler la classe MainFX et
 * de lancer l'application sans faire un extends de la classe Application
 * (permet la création du fichier JAR)
 */
public class RunMain {
    /**
     * Appel la méthode main de la classe MainFX et lance l'application
     * @param args un tableau de chaînes de caractères représentant les arguments
     *             de la ligne de commande
     */
    public static void main(String[] args) {
        MainFX.main(args);
    }
}