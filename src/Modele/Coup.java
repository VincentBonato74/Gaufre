package Modele;

public class Coup extends Commande{
    Niveau niv;
    int l, c;
    int mapAvant[][];

    public Coup(int MangeL, int MangeC, int[][] mapGrid){
        l = MangeL;
        c = MangeC;
        mapAvant = mapGrid;
    }

    void fixerNiveau(Niveau n){
        niv = n;
    }

    void eat(Coup cp){
        niv.eat(cp.l, cp.c);
    }

    void eatInverse(Coup cp){
        niv.eatInverse(cp);
    }

    void eatRevenir(Coup cp){
        niv.eatRevenir(cp);
    }

    void execute(Coup cp){
        eatRevenir(cp);
    }

    void desexecute(Coup cp){
        eatInverse(cp);
    }
}
