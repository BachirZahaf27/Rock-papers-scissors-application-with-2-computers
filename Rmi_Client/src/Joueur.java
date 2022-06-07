import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;

public  class Joueur extends Observable  implements Runnable {
    protected int numero;
    protected int nbPoints;
    protected BufferedReader in;
    protected PrintStream out;
    protected Choix choix;
    protected Joueur adversaire;
    protected boolean jouer;
    protected boolean jeuFini;
    protected boolean partieGagnee;
    protected boolean partiePerdue;

    public Joueur() {}

    public Joueur(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        (new Thread(this)).start();
        adversaire = new Joueur();
    }

    public Joueur(Socket socket, int numero) {
        this(socket);
        this.numero = numero;
    }

    public void aGagne() {
        nbPoints = nbPoints + 1;
        choix = null;
        adversaire.choix = null;
        jouer = true;
    }

    public void egalite() {
        choix = null;
        adversaire.choix = null;
        jouer = true;
    }

    public void aPerdu() {
        choix = null;
        adversaire.choix = null;
        adversaire.nbPoints = adversaire.nbPoints + 1;
        jouer = true;
    }

    public boolean partieGagnee() {
        return nbPoints == Constantes.CIBLE;
    }

    public void run() {}
}