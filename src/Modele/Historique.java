package Modele;

import Global.Configuration;
import Structures.Sequence;

public class Historique<E extends Commande> {
    Sequence<Coup> CoupFait, CoupAnnuler;

    Historique(){
        CoupFait = Configuration.instance().nouvelleSequence();
        CoupAnnuler = Configuration.instance().nouvelleSequence();
    }

    void nouveau(Coup c){
        CoupFait.insereTete(c);
        c.execute(c);
        while(!CoupAnnuler.estVide()){
            CoupAnnuler.extraitTete();
        }
    }

    public boolean peutRefaire(){
        return !CoupAnnuler.estVide();
    }

    public boolean peutAnnuler(){
        return !CoupFait.estVide();
    }

    Coup refaire(){
        if(peutRefaire()){
            /*System.out.println("-----------------------------------");
            System.out.println("Je peux refaire");
            System.out.println("SEQ COUP ANNULER AVANT : " + CoupAnnuler);
            */Coup c = CoupAnnuler.extraitTete();
            c.execute(c);
            CoupFait.insereTete(c);
            /*for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(c.mapAvant[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("SEQ COUP FAIT : " + CoupFait);
            System.out.println("SEQ COUP ANNULER APRES : " + CoupAnnuler);
            */return c;
        }else{
            return null;
        }
    }

    Coup annuler(){
        if(peutAnnuler()){
            /*System.out.println("-----------------------------------");
            System.out.println("Je peux Annuler");
            System.out.println("SEQ COUP FAIT AVANT : " + CoupFait);
            */Coup c = CoupFait.extraitTete();
            c.desexecute(c);
            /*for(int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(c.mapAvant[i][j] + " ");
                }
                System.out.println();
            }*/
            CoupAnnuler.insereTete(c);
            //System.out.println("SEQ COUP FAIT APRES : " + CoupFait);
            //System.out.println("SEQ COUP ANNULER : " + CoupAnnuler);
            return c;
        }else{
            return null;
        }
    }

}


