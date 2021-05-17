package Modele;

import Global.Configuration;
import Patterns.Observable;

public class Jeu extends Observable {
    Niveau courant;


    public Jeu(int l, int c){
            initialize(l, c);
    }

    public Coup determineCoup(int l, int c){
        return courant.eat(l, c);
    }

    public void initialize(int l, int c){
            courant = new Niveau(l, c);
            miseAJour();
    }

    public Niveau niveau() {
        return courant;
    }

    public void jouerCoup(Coup cp) {
        if (cp == null) {
            Configuration.instance().logger().info("Déplacement impossible");
        } else {
            courant.jouerCoup(cp);
            miseAJour();
        }
    }

    public Coup annule() {
        Coup cp = courant.annuler();
        miseAJour();
        return cp;
    }

    public Coup refaire() {
        Coup cp = courant.refaire();
        miseAJour();
        return cp;
    }

    public boolean aGagnant()
    {
        return courant.aGagnant();
    }

    public int getTourJoueur(){
        return courant.getTourJoueur();
    }




}
