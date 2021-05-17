import Contoleur.ControleurMediateur;
import Modele.Jeu;
import Vue.CollecteurEvenements;
import Vue.InterfaceGraphique;

public class Gaufre {
    public static void main(String[] args){
        Jeu jeu = new Jeu(5, 10);
        CollecteurEvenements control = new ControleurMediateur(jeu);
        InterfaceGraphique.demarrer(jeu, control);
    }
}
