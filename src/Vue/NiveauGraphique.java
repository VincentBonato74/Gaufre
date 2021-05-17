package Vue;

import Modele.Jeu;
import Modele.Niveau;
import Patterns.Observateur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class NiveauGraphique extends JComponent implements Observateur {
    Jeu jeu;
    int largeur, hauteur, nbColonnes, nbLignes, hauteurCase, largeurCase;
    Image orange, grise, poison;

    private Image chargeImage(String nom){
        Image img = null;

        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(nom + ".png");
        try{
            img = ImageIO.read(in);
        }catch(Exception e ){
            System.err.println("Erreur lors du chargement de l'image : " + e);
            System.exit(1);
        }
        return img;
    }


    public NiveauGraphique(Jeu j){
        jeu = j;
        grise = chargeImage("CaseGrise");
        orange = chargeImage("CaseOrange");
        poison = chargeImage("casePoison");
    }



    public void paintComponent(Graphics g){
        Graphics2D drawable = (Graphics2D) g;
        largeur = getSize().width;
        hauteur = getSize().height;

        drawable.clearRect(0, 0, largeur, hauteur);

        nbColonnes = jeu.niveau().getColonnes();
        nbLignes = jeu.niveau().getLignes();

        largeurCase = largeur / nbColonnes;
        hauteurCase = hauteur / nbLignes;

        for(int l = 0; l < nbLignes; l++){
            for (int c = 0; c < nbColonnes; c++) {
                int x = c * largeurCase;
                int y = l * hauteurCase;

                if(jeu.niveau().estMange(l, c)){
                    drawable.drawImage(grise, x, y, largeurCase, hauteurCase, null);
                }else if(jeu.niveau().estGaufre(l, c)){
                    drawable.drawImage(orange, x, y, largeurCase, hauteurCase, null);
                } else if(jeu.niveau().estPoison(l, c)){
                    drawable.drawImage(poison, x, y, largeurCase, hauteurCase, null);
                }
            }
        }
    }

    public int largeurCase(){
        return largeurCase;
    }

    public int hauteurCase(){
        return hauteurCase;
    }

    @Override
    public void metAJour() {
        repaint();
    }
}
