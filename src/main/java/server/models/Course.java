package server.models;

import java.io.Serializable;

/**
 * Classe permettant de créer les cours.
 */
public class Course implements Serializable {

    private String name;
    private String code;
    private String session;

    /**
     * Créer une nouvelle instance de la classe Course.
     * @param name de type String, nom du cours.
     * @param code de type String, code du cours.
     * @param session de type String, session pendant laquelle est donné le cours.
     */
    public Course(String name, String code, String session) {
        this.name = name;
        this.code = code;
        this.session = session;
    }

    /**
     * Obetnir le nom du cours.
     * @return nom (String).
     */
    public String getName() {
        return name;
    }

    /**
     * Initialiser le nom du cours.
     * @param name de type String, nom du cours.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtenir le code du cours.
     * @return code (String).
     */
    public String getCode() {
        return code;
    }

    /**
     * Initialiser le code du cours.
     * @param code de type String, code du cours.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtenir la session du cours.
     * @return session (String).
     */
    public String getSession() {
        return session;
    }

    /**
     * Initialiser la session du cours.
     * @param session de type String, session du cours.
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Affiche les informations du cours.
     * @return information du cours (String).
     */
    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                ", code=" + code +
                ", session=" + session +
                '}';
    }
}