package Contoleur;

import Modele.Jeu;
import Modele.Coup;
import Vue.CollecteurEvenements;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    int decompte;
    int joueurCourant;
    final int lenteurAttente = 50;
    JoueurIAFacile Joueur2;
    JoueurHumain Joueur1;

    public ControleurMediateur(Jeu j){
        jeu = j;
        Joueur2 = new JoueurIAFacile(jeu);
        Joueur1 = new JoueurHumain(jeu);
        joueurCourant = jeu.getTourJoueur();
    }


    @Override
    public void clicSouris(int l, int c) {
        if(joueurCourant == 1){
            if(Joueur1.joue(l, c)){
                changeJoueur();
            }
        }else{
            System.out.println("Ce n'est pas votre tour");
        }
    }

    @Override
    public void tictac() {
        if(!jeu.aGagnant()){
            if(decompte == 0){
                if(joueurCourant == 2){
                    Joueur2.joue();
                    changeJoueur();
                } else {
                    decompte = lenteurAttente;
                }
            }else{
                decompte--;
            }
        }else{
            joueurCourant = 1;
        }
    }

    public void changeJoueur(){
        if(joueurCourant == 1){
            joueurCourant = 2;
        }else{
            joueurCourant = 1;
        }
    }

    void annule() {
        Coup cp = jeu.annule();
    }

    void refaire()
    {
        Coup cp = jeu.refaire();
    }


    @Override
    public void commande(String commande) {
        if(Joueur2.isIA() && jeu.getTourJoueur() == 1){
            switch(commande){
                case "newGame":
                    jeu.initialize(jeu.niveau().getLignes(), jeu.niveau().getColonnes());
                    break;
                case "annule":
                    annule();
                    annule();
                    break;
                case "refaire":
                    refaire();
                    refaire();
                    break;
            }
        }else{
            switch(commande){
                case "newGame":
                    jeu.initialize(jeu.niveau().getLignes(), jeu.niveau().getColonnes());
                    changeJoueur();
                    break;
                case "annule":
                    annule();
                    changeJoueur();
                    break;
                case "refaire":
                    refaire();
                    changeJoueur();
                    break;
            }
        }
    }
}
