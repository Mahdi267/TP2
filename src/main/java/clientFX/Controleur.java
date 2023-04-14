package clientFX;

import java.io.IOException;

public class Controleur {

    private Modele modele;
    private Vue vue;

    public Controleur(Modele m, Vue v){
        this.modele = m;
        this.vue = v;

        String session = this.vue.getSession().getValue();
    }

    private void chrg(String s){
        try {
            this.modele.charger(s);
        }catch (IOException e){
            // wagerer l'exception
        }catch (ClassNotFoundException e){
            // gérer l'exception
        }
    }

    private void inscr(){
        try {
            this.modele.inscrire();
        }catch (IOException e){
            // gérer l'exception
        }
    }
}
