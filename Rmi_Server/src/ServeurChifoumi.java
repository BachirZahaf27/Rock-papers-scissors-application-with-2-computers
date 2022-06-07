import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class ServeurChifoumi {
    private JoueurServeur[] lesJoueurs;
    private int numSuivant = 0;
    public ServeurChifoumi(JoueurServeur[] lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }

    public void ajouterJoueur(Socket socket) {
        lesJoueurs[numSuivant] = new JoueurServeur(socket, numSuivant, this);
        numSuivant++;
        if (numSuivant == 2) donnerFeuVert();
    }

    public void traiterMessage(String message, Joueur joueur) {
        System.out.println(message);
        Scanner scan = new Scanner(message);
        int type = scan.nextInt();
    }

    public void chercherJoueurGagnant() {
        attendre();
        Choix choix0 = lesJoueurs[0].choix;
        Choix choix1 = lesJoueurs[1].choix;
        if (choix0.equals(choix1)) egalite();
        if (choix0 == Choix.CISEAUX) {
            if (choix1 ==  Choix.FEUILLE) validerGain(0);
            else if  (choix1 ==  Choix.CAILLOU) validerGain(1);
        }
        else if (choix0 == Choix.FEUILLE)  {
            if (choix1 ==  Choix.CAILLOU) validerGain(0);
            else if  (choix1 ==  Choix.CISEAUX) validerGain(1);
        }
        else if (choix0 == Choix.CAILLOU)  {
            if (choix1 ==  Choix.CISEAUX) validerGain(0);
            else if  (choix1 ==  Choix.FEUILLE) validerGain(1);
        }
    }

    public void egalite() {
        lesJoueurs[0].egalite();
        lesJoueurs[1].egalite();
    }

    public void annulerChoix() {
        lesJoueurs[0].choix = null;
        lesJoueurs[1].choix = null;
    }

    void validerGain(int numJoueur) {
        lesJoueurs[numJoueur].aGagne();
        lesJoueurs[1 - numJoueur].aPerdu();
    }

    public void donnerFeuVert() {
        for (JoueurServeur j : lesJoueurs) j.envoyerNumero();
    }

    public static void main(String[] arg) throws Exception {
        int portChifoumi = 4567;
        ServerSocket  receptionniste =  new ServerSocket(portChifoumi);
        JoueurServeur[] lesJoueurs = new JoueurServeur[2];

        ServeurChifoumi serveur = new ServeurChifoumi(lesJoueurs);
        for (int i = 0; i < 2; i++)	serveur.ajouterJoueur(receptionniste.accept());

        new ServeurChifoumi(lesJoueurs);
    }

    public void prevenirAdversaire(int numero) {
        Choix choix = lesJoueurs[numero].choix;
        lesJoueurs[1 - numero].out.println(Constantes.CHOIX_ADVERSAIRE + " " + choix);
    }

    public void attendre() {
        try {
            Thread.sleep(3000);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void examinerChoix() {
        boolean fait = ((lesJoueurs[0].choix != null) && (lesJoueurs[1].choix != null));
        System.out.println(fait);
        if (fait) {
            chercherJoueurGagnant();
            annulerChoix();
        }
    }
}