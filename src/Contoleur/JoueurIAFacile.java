package Contoleur;

import Modele.Coup;
import Modele.Jeu;

import java.util.Random;

public class JoueurIAFacile {
    Random r;
    Jeu jeu;
    int mapLoose[][];
    boolean defaite;
    int l, c;

    JoueurIAFacile(Jeu j){
        jeu = j;
        r = new Random();
        defaite = false;
        c = 0;
        l = 0;
        mapLoose = new int[jeu.niveau().getLignes()][jeu.niveau().getColonnes()];
        initialiseMapLoose();
    }

    private void initialiseMapLoose(){
        for(int i = 0; i < jeu.niveau().getLignes(); i++){
            for(int j = 0; j < jeu.niveau().getColonnes(); j++){
                if(i == 0 && j==0){
                    mapLoose[i][j] = -1;
                }else{
                    mapLoose[i][j] = 0;
                }
                System.out.print(mapLoose[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void joue() {
        int[][] mapGrid = jeu.niveau().Tableau();
        if(estTermine()){
            l = 0;
            c = 0;
        }else{
            while(mapGrid[l][c] == 0 || mapGrid[l][c] == -1){
                l = r.nextInt(jeu.niveau().getLignes());
                c = r.nextInt(jeu.niveau().getColonnes());
            }
        }
        Coup cp = jeu.determineCoup(l, c);
        jeu.jouerCoup(cp);
    }

    public boolean estTermine(){
        defaite = true;
        int[][] mapGrid = jeu.niveau().Tableau();
        for(int i = 0; i < jeu.niveau().getLignes(); i++){
            for(int j = 0; j < jeu.niveau().getColonnes(); j++){
                if((mapLoose[i][j] != mapGrid[i][j])){
                    if((i != 0 || j != 0)){
                        defaite = false;
                    }
                }
            }
        }
        return defaite;
    }

    public boolean isIA(){
        return true;
    }
}
