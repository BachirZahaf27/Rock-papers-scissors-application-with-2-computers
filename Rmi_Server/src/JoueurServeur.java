import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class JoueurServeur extends Joueur implements Runnable  {
    private ServeurChifoumi serveur;

    public JoueurServeur(Socket socket, int numero, ServeurChifoumi serveur) {
        super(socket, numero);
        this.serveur = serveur;
    }

    public void envoyerNumero() {
        out.println(Constantes.NUM + " " + numero);
    }
    void traiterMessage(String message) {
        int type;
        Scanner scan = new Scanner(message);

        type =scan.nextInt();

        switch (type) {
            case Constantes.CHOIX : choix = Choix.valueOf(scan.next());
                serveur.prevenirAdversaire(numero);
                serveur.examinerChoix();
                break;
        }
    }

    public void aGagne() {
        super.aGagne();
        out.println(Constantes.GAGNE);
        if (partieGagnee()) out.println(Constantes.PARTIE_GAGNEE);
    }

    public void aPerdu() {
        super.aPerdu();
        out.println(Constantes.PERDU);
        if (adversaire.partieGagnee()) out.println(Constantes.PARTIE_PERDUE);
    }

    public void egalite() {
        System.out.println("egalite");
        super.egalite();
        out.println(Constantes.EGALITE);
    }

    public void run() {
        String message;

        try {
            message = in.readLine();
            while (!jeuFini) {
                traiterMessage(message);
                message = in.readLine();
            }
        }
        catch(IOException exc) {

        }
    }
}