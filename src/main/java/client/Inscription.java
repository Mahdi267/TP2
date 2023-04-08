package client;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class Inscription {

    private ArrayList<Course> courses;

    /**
     *
     * @param session
     * @param os
     * @param is
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void charger(String session, OutputStream os, InputStream is) throws IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream(os);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(oos));

        writer.write("CHARGER " + session);

        courses = (ArrayList<Course>) new ObjectInputStream(is).readObject();

        int i = 0;

        for(Course course:courses){
            System.out.println(i + ". " + course.getCode() + "\t" + course.getName() + "\n");
            i++;
        }

        writer.close();
    }

    /**
     *
     * @param prenom
     * @param nom
     * @param email
     * @param matricule
     * @param cours
     * @param os
     * @param is
     * @return
     * @throws IOException
     */
    public int inscrire(String prenom, String nom, String email, String matricule, Course cours, OutputStream os, InputStream is) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(os);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(oos));

        writer.write("INSCRIRE");
        writer.flush();

        int i = 0;
        for(Course course:courses){
            if(course.getCode().equals(cours.getCode())) break;
            i++;
        }
        if(i == courses.size()-1){
            writer.close();
            return -1;
        }

        RegistrationForm registrationForm = new RegistrationForm(prenom, nom, email, matricule, cours);
        oos.writeObject(registrationForm);

        ObjectInputStream ois = new ObjectInputStream(is);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ois));

        String line;

        while((line = reader.readLine()) != null){
            System.out.println(line);
        }

        writer.close();
        reader.close();
        return 0;
    }
}
