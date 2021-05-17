package Contoleur;

import Modele.Coup;
import Modele.Jeu;

public class JoueurHumain {

    Jeu jeu;

    JoueurHumain(Jeu j){
        jeu = j;
    }

    public boolean joue(int l, int c){
        if(jeu.niveau().estJouable(l, c)){
            Coup cp = jeu.determineCoup(l, c);
            jeu.jouerCoup(cp);
            return true;
        }else{
            System.out.println("Coup Non Jouable");
            return false;
        }
    }

    public boolean isIA(){
        return false;
    }
}
