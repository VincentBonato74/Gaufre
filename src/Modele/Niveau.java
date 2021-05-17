package Modele;

public class Niveau extends Historique<Coup>{
    final static int ORANGE = 1;
    final static int GRIS = 0;
    final static int EMPOISONNE = -1;
    int mapGrid[][];
    int lignes;
    int colonnes;
    int tourJoueur;
    int vainqueur;
    int perdant;
    int dL, dC;

    public Niveau(int l, int c){
        lignes = l;
        colonnes = c;
        tourJoueur = 1;
        vainqueur = 0;
        perdant = 0;
        dL = 0;
        dC = 0;
        initializeNiv();
    }

    public void initializeNiv()
    {
        mapGrid = new int[lignes][colonnes];
        tourJoueur = 1;
        vainqueur = 0;
        perdant = 0;
        dL = getLignes();
        dC = getColonnes();
        for(int i = 0; i<lignes; i++){
            for(int j=0;j<colonnes;j++){
                if(i == 0 && j == 0){
                    mapGrid[i][j] = -1;
                }else{
                    mapGrid[i][j] = 1;
                }
            }
        }
    }

    public Coup eat(int l, int c){
        Coup resultat = null;
        int[][] mapAvant = new int[getLignes()][getColonnes()];
        if(estJouable(l, c)){
            if(!aGagnant()){
                for(int k = 0; k < getLignes(); k++){
                    for(int k2 = 0; k2 < getColonnes(); k2++){
                        mapAvant[k][k2] = mapGrid[k][k2];
                        System.out.print(mapAvant[k][k2] + " ");
                    }
                    System.out.println();
                }
                for (int i = l; i < lignes; i++) {
                    for (int j = c; j < colonnes; j++) {
                        if(mapGrid[i][j] == 1){
                            mapGrid[i][j] = 0;
                        }
                    }
                }
                if(l == 0 && c == 0){
                    perdant = tourJoueur;
                    if(perdant == 1){
                        vainqueur = 2;
                    }else{
                        vainqueur = 1;
                    }
                }
            }
            resultat = new Coup(l, c, mapAvant);
            if(tourJoueur == 1){
                System.out.println("Humain joue en l = " + l + "et c = " + c );
                tourJoueur = 2;
            }else{
                System.out.println("IA joue en l = " + l + "et c = " + c );
                tourJoueur = 1;
            }
        }else{
            System.out.println("Coup non Jouable");
        }
        return resultat;
    }

    public void eatInverse(Coup cp){
        mapGrid = cp.mapAvant;
    }

    public void eatRevenir(Coup cp){
        for (int i = cp.l; i < lignes; i++) {
            for (int j = cp.c; j < colonnes; j++) {
                if(mapGrid[i][j] == 1){
                    mapGrid[i][j] = 0;
                }
            }
        }
    }

    public void jouerCoup(Coup cp){
        cp.fixerNiveau(this);
        nouveau(cp);
    }

    public boolean estMange(int l, int c){
        return mapGrid[l][c] == GRIS;
    }

    public boolean estGaufre(int l, int c){
        return mapGrid[l][c] == ORANGE;
    }

    public boolean estPoison(int l, int c){
        return mapGrid[l][c] == EMPOISONNE;
    }

    public int getTourJoueur(){
        return tourJoueur;
    }

    public boolean aGagnant() {
        return vainqueur != 0;
    }

    public int vainqueur(){
        return vainqueur;
    }

    public boolean estJouable(int l, int c){
        if(mapGrid[l][c] == 0){
            return false;
        }else{
            return true;
        }
    }

    public int[][] Tableau(){
        return mapGrid;
    }

    public int getLignes(){
        return lignes;
    }

    public int getColonnes(){
        return colonnes;
    }
}
