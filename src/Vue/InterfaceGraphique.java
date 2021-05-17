package Vue;

import Modele.Jeu;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable, Observateur {
    Jeu jeu;
    CollecteurEvenements control;
    JLabel gagnant, perdant, tour;
    NiveauGraphique niv;
    JButton annuler, refaire;

    InterfaceGraphique(Jeu j, CollecteurEvenements c){
        jeu = j;
        control = c;
        jeu.ajouteObservateur(this);
    }

    public static void demarrer(Jeu j, CollecteurEvenements control){
        SwingUtilities.invokeLater(new InterfaceGraphique(j, control));
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Gaufre empoisonnée");
        niv = new NiveauGraphique(jeu);
        niv.addMouseListener(new AdaptateurSouris(niv, control));
        frame.add(niv);

        Box barreLaterale = Box.createVerticalBox();
        barreLaterale.add(createLabel("Gaufre Empoisonnee"));
        barreLaterale.add(Box.createGlue());

        tour = createLabel("Tour du joueur : 1");
        barreLaterale.add(tour);
        gagnant = createLabel("");
        barreLaterale.add(gagnant);
        perdant = createLabel("");
        barreLaterale.add(perdant);
        barreLaterale.add(Box.createGlue());

        // Annuler / Refaire
        Box annulRef = Box.createHorizontalBox();
        annuler = createButton("<", "annule");
        //annuler.setEnabled(false);
        refaire = createButton(">", "refaire");
       // refaire.setEnabled(false);
        annulRef.add(annuler);
        annulRef.add(refaire);
        barreLaterale.add(annulRef);


        JButton nouvellePartie = createButton("Nouvelle partie", "newGame");
        barreLaterale.add(nouvellePartie);
        barreLaterale.add(Box.createGlue());

        frame.add(barreLaterale, BorderLayout.LINE_END);

        Timer chrono = new Timer(16 , new AdaptateurTemps(control));
        chrono.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    private JLabel createLabel(String s) {
        JLabel lab = new JLabel(s);
        lab.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lab;
    }

    private JButton createButton(String s, String c) {
        JButton but = new JButton(s);
        but.addActionListener(new AdaptateurCommande(control, c));
        but.setAlignmentX(Component.CENTER_ALIGNMENT);
        but.setFocusable(false);
        return but;
    }

    @Override
    public void metAJour() {
        tour.setText("Tour du Joueur : " + jeu.getTourJoueur());

        if(jeu.aGagnant())
        {
            gagnant.setText("Le vainqueur est Joueur " + jeu.niveau().vainqueur());
        }else{
            gagnant.setText("");
        }
        niv.repaint();
    }
}
