package client;

import server.models.Course;
import server.models.RegistrationForm;

import  java.net.Socket;
import  java.io.*;
import  java.util.ArrayList;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            int PORT = 1337;
            Socket socket = new Socket("localhost", PORT);
            ObjectOutputStream oos;
            ObjectInputStream ois;
            BufferedWriter writer;

            System.out.println("***Bienvenue au portail d'inscription de cours de l'UDEM***");
            System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste de  cours:");

            Scanner scanner = new Scanner(System.in);
            String session = "";
            ArrayList<Course> courses = new ArrayList<>();
            boolean condition = true;

            while (condition) {
                System.out.println("1. Automne\n2. Hiver\n3. Ete");
                System.out.println("> Choix: ");

                int choix = scanner.nextInt();
                switch (choix){
                    case 1 -> session = "Automne";
                    case 2 -> session = "Hiver";
                    case 3 -> session = "Ete";
                    default -> {
                        System.out.println("Session indisponible. Veuillez choisir une session parmis celles proposées.\n");
                        continue;
                    }
                }

                oos = new ObjectOutputStream(socket.getOutputStream());
                writer = new BufferedWriter(new OutputStreamWriter(oos));
                writer.write("CHARGER " + session);
                writer.newLine();
                writer.flush();

                ois = new ObjectInputStream(socket.getInputStream());
                // Récupérer le ArrayList contenant les cours de la session choisie envoyé par le serveur
                courses = (ArrayList<Course>) ois.readObject();


                // Afficher les cours diponibles durant la session choisie
                int i = 0;
                for(Course c:courses){
                    System.out.println(i + c.getCode() + "\t" + c.getName());
                    i++;
                }

                System.out.println("> Choix:\n");
                System.out.println("1. Consulter les cours offerts pour une autre session");
                System.out.println("2. Inscription à un cours");

                choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        break;
                    case 2:
                        condition = false;
                        break;
                }
            }

            System.out.println("Veuillez saisir votre prénom: ");
            String premon = scanner.nextLine();
            System.out.println("Veuillez saisir votre nom: ");
            String nom = scanner.nextLine();
            System.out.println("Veuillez saisir votre email: ");
            String email = scanner.nextLine();
            System.out.println("Veuillez saisir votre matricule: ");
            String matricule = Integer.toString(scanner.nextInt());
            System.out.println("Veuillez saisit le code du cours: ");
            String code = scanner.nextLine();

            boolean bon_matricule = true;
            while (bon_matricule) {
                if (matricule.length() != 8) {
                    System.out.println("Matricule invalide");
                    System.out.println("Veuillez saisir votre matricule: ");
                    matricule = Integer.toString(scanner.nextInt());
                }
                else bon_matricule = false;
            }

            String nomCours = "";
            for(Course c:courses){
                if(c.getCode().equals(code)) nomCours = c.getName();
            }
            Course cours = new Course(nomCours, code, session);

            oos = new ObjectOutputStream(socket.getOutputStream());
            writer = new BufferedWriter(new OutputStreamWriter(oos));
            writer.write("INSCRIRE");
            writer.flush();

            oos.writeObject(new RegistrationForm(premon, nom, email, matricule, cours));

            ois = new ObjectInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(ois));

            System.out.println(reader.readLine());

            writer.close();
            reader.close();
        }
        catch (IOException e){
            System.out.println("IO erreur");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe introuvable");
        }
    }
}
