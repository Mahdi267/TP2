package client;

import server.models.Course;
import server.models.RegistrationForm;

import  java.net.Socket;
import  java.io.*;
import  java.util.ArrayList;
import  java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /**
     * Démarre le programme dans la console.
     * @param args
     */
    public static void main(String[] args){
        // Port pour avec lequel la connexion avec le serveur sera faite
        int PORT = 1337;
        Scanner scanner = new Scanner(System.in);
        String session = "";
        // ArrayList qui contiendra les cours disponibles durant une session
        ArrayList<Course> courses = new ArrayList<>();
        try {
            System.out.println("\n*** Bienvenue au portail d'inscription de cours de l'UDEM ***");
            System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste de  cours:");

            boolean condition = true;

            // Tant que l'utilisateur n'a pas choisi une session valide ou ne décide pas de s'inscrire
            // se reconnecter au serveur et redemander de choisir une session
            while (condition) {
                Socket socket = new Socket("localhost", PORT);
                ObjectOutputStream oos;
                ObjectInputStream ois;

                System.out.println("1. Automne\n2. Hiver\n3. Ete");
                System.out.print("> Choix: ");

                // Choisir la session pour laquelle l'utilisateur veut consulter les cours et afficher un message
                // si la session n'existe pas
                int choix = scanner.nextInt();
                switch (choix) {
                    case 1 -> session = "Automne";
                    case 2 -> session = "Hiver";
                    case 3 -> session = "Ete";
                    default -> {
                        System.out.println("Session indisponible. Veuillez choisir une session parmis celles proposées.\n");
                        continue;
                    }
                }

                // Envoyer le String "CHARGER" au serveur
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject("CHARGER " + session);

                // Récupérer le ArrayList contenant les cours de la session choisie envoyé par le serveur
                ois = new ObjectInputStream(socket.getInputStream());
                courses = (ArrayList<Course>) ois.readObject();


                // Afficher les cours diponibles durant la session choisie
                System.out.println("Les cours offert pendant la session sont:");
                int i = 0;
                for (Course c : courses) {
                    System.out.println(i + ". " + c.getCode() + "\t" + c.getName());
                    i++;
                }

                System.out.println("> Choix:");
                System.out.println("1. Consulter les cours offerts pour une autre session");
                System.out.println("2. Inscription à un cours");
                System.out.print("> Choix: ");

                // Choisir si on veut procéder à l'inscription ou consulter les cours d'une autre session
                choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        break;
                    case 2:
                        condition = false;
                        break;
                }
            }
        }catch (IOException e){
            System.out.println("IO erreur");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe introuvable");
        }

        try{
            // Se connecter au serveur
            Socket socket = new Socket("localhost", PORT);
            ObjectOutputStream oos;
            ObjectInputStream ois;

            // Créer les Regex pour les inputs de l'utilisateur (email, code, matricule)
            scanner.nextLine();
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            String matriculePattern = "^[0-9]{8}$";
            String codePattern = "^[A-Z]{3}[0-9]{4}$";
            Pattern emailRegex = Pattern.compile(emailPattern);
            Pattern matriculeRegex = Pattern.compile(matriculePattern);
            Pattern codeRegex = Pattern.compile(codePattern);

            // Demander les informations pour l'inscription à l'utilisateur et
            // vérifier les formats des informations

            // Demander le prénom
            System.out.print("Veuillez saisir votre prénom: ");
            String prenom = scanner.nextLine();

            // Demander le nom
            System.out.print("Veuillez saisir votre nom: ");
            String nom = scanner.nextLine();

            // Demander le mail et vérifier le format
            String email;
            while (true) {
                System.out.print("Veuillez saisir votre email: ");
                email = scanner.nextLine();
                Matcher matcher = emailRegex.matcher(email);
                if (matcher.matches()) {
                    break;
                }
                System.out.println("L'e-mail n'est pas dans le bon format. Veuillez réessayer.");
            }

            // Demander le matricule et vérifier le format (8 chiffres)
            String matricule;
            while (true) {
                System.out.print("Veuillez saisir votre matricule: ");
                matricule = scanner.nextLine();
                Matcher matcher = matriculeRegex.matcher(matricule);
                if (matcher.matches()) {
                    break;
                }
                System.out.println("Le matricule n'est pas dans le bon format. Veuillez réessayer.");
            }

            // Demander le code afficher un message si le format du code du cours
            // n'est pas adéquat et un autre message si le cours n'est pas dans la liste
            boolean cours_present = false;
            String code;
            while (true){
                System.out.print("Veuillez saisir le code du cours: ");
                code = scanner.nextLine();
                Matcher matcher = codeRegex.matcher(code);
                if (matcher.matches()) {
                    for(Course c:courses) {
                        if (c.getCode().equals(code)) {
                            cours_present = true;
                            break;
                        }
                    }
                    if(cours_present) break;
                }
                if(matcher.matches()) System.out.println("Veuillez choisir un cours qui figure dans la liste ci-dessus");
                else System.out.println("Le code du cours n'est pas dans le bon format. Veuillez réessayer.");
            }

            System.out.println("En cours d'inscription...");

            boolean bon_matricule = true;
            while (bon_matricule) {
                if (matricule.length() != 8) {
                    System.out.println("Matricule invalide");
                    System.out.println("Veuillez saisir votre matricule: ");
                    matricule = Integer.toString(scanner.nextInt());
                }
                else bon_matricule = false;
            }

            // Récupérer le nom du cours
            String nomCours = "";
            for(Course c:courses){
                if(c.getCode().equals(code)) nomCours = c.getName();
            }
            // Récupérer le cours
            Course cours = new Course(nomCours, code, session);

            // Envoyer le String "INSCRIRE"
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("INSCRIRE");

            // Envoyer l'objet RegistrationForm au serveur pour faire l'inscription
            oos.writeObject(new RegistrationForm(prenom, nom, email, matricule, cours));

            // Récupérer le message de validation du serveur et l'afficher
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println(ois.readObject());

        } catch (IOException e){
            System.out.println("IO erreur");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe introuvable");
        }
    }
}