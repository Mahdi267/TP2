package client;

import  java.net.Socket;
import  java.io.*;
import  java.util.Scanner;

public class Main {
    public final static int PORT = 1337;
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.11", PORT);
            OutputStream os = new ObjectOutputStream(socket.getOutputStream());
            InputStream is = new ObjectInputStream(socket.getInputStream());

            System.out.println("***Bienvenue au portail d'inscription de cours de l'UDEM***");
            System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste de  cours:");
            System.out.println("1. Automne\n2. Hiver\n3. Ete");

            System.out.println("> Choix:");
            Scanner scanner = new Scanner(System.in);

            Inscription inscription = new Inscription();
            Boolean condition = true;
            while (condition) {
                int choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        inscription.charger("Automne", new ObjectOutputStream(os), new ObjectInputStream(is));
                        break;
                    case 2:
                        inscription.charger("Hiver", new ObjectOutputStream(os), new ObjectInputStream(is));
                        break;
                    case 3:
                        inscription.charger("Ete", new ObjectOutputStream(os), new ObjectInputStream(is));
                        break;
                    default:
                        System.out.println("Session indisponible");
                        break;
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

        // appeler la fonction inscrire

        }
        catch (IOException e){
            System.out.println("IO erreur");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe introuvable");
        }
    }
}
