package client;

import server.models.Course;

import  java.net.Socket;
import  java.io.*;

public class Main {
    public final static int PORT = 1337;
    public void charger(String session) throws IOException{
        Socket socket = new Socket("127.0.0.1", PORT);
        OutputStream os = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        writer.write("CHARGER");

        InputStream is = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        int i = 0;

        while((line = reader.readLine()) != null){
            String[] tabCours = line.split("\t");
            if(tabCours[2] == session){
                System.out.println(i + ". " + tabCours[0] + "\t" + tabCours[1] + "\n");
                i++;
            }
        }
        socket.close();
    }

    public void inscrire(String prenom, String nom, String email, String matricule, Course cours) throws IOException{
        Socket socket = new Socket("127.0.0.1", PORT);
        OutputStream os =  socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        writer.write("INSCRIRE " + prenom + " " + nom + " " + matricule + " " + matricule + " " + cours);

        // verifier si les cours sont disponibles dans la liste de cours
    }

    public static void main(String[] args) {

    }
}
