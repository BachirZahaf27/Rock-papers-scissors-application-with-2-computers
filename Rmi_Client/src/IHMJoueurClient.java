import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class IHMJoueurClient  extends Box implements ActionListener, Observer {
    private JButton feuilleM = new JButton(new ImageIcon("feuille.jpg"));
    private JButton ciseauxM = new JButton(new ImageIcon("ciseaux.jpg"));
    private JButton caillouM= new JButton(new ImageIcon("caillou.jpg"));
    private JButton feuilleA = new JButton(new ImageIcon("feuille.jpg"));
    private JButton ciseauxA = new JButton(new ImageIcon("ciseaux.jpg"));
    private JButton caillouA= new JButton(new ImageIcon("caillou.jpg"));
    private JLabel nombrePoints = new JLabel("0");
    private JLabel action = new JLabel("attendre");
    private JoueurClient joueur;
    private JLabel nombrePointsAdversaire = new JLabel("0");
    private Color couleurBouton;
    private JButton boutonChoixAdversaire;
    private JButton boutonChoisi;

    public IHMJoueurClient(JoueurClient joueur) {
        super(BoxLayout.Y_AXIS);
        this.joueur = joueur;
        joueur.addObserver(this);

        JPanel panneau = new JPanel();
        panneau.add(new JLabel("Le joueur  : "));
        panneau.add(action);
        add(panneau);

        panneau = new JPanel();
        panneau.add(new JLabel("nombre de points"));
        panneau.add(nombrePoints);
        add(panneau);

        panneau = new JPanel();
        panneau.add(feuilleM);
        panneau.add(ciseauxM);
        panneau.add(caillouM);
        add(panneau);
        if (!joueur.jouer) {
            feuilleM.setEnabled(false);
            ciseauxM.setEnabled(false);
            caillouM.setEnabled(false);
        }
        else action.setText("vous pouvez jouer");
        feuilleM.setActionCommand(Choix.FEUILLE.toString());
        ciseauxM.setActionCommand(Choix.CISEAUX.toString());
        caillouM.setActionCommand(Choix.CAILLOU.toString());
        feuilleM.addActionListener(this);
        ciseauxM.addActionListener(this);
        caillouM.addActionListener(this);

        add(new JLabel("L'adversaire"));
        panneau = new JPanel();
        panneau.add(feuilleA);
        panneau.add(ciseauxA);
        panneau.add(caillouA);
        add(panneau);

        panneau = new JPanel();
        panneau.add(new JLabel("nombre de points de l'adversaire "));
        panneau.add(nombrePointsAdversaire);
        add(panneau);

        couleurBouton = feuilleM.getBackground();
    }

    public void actionPerformed(ActionEvent evt) {
        boutonChoisi = (JButton) evt.getSource();
        boutonChoisi.setBackground(Color.RED);
        joueur.choix = Choix.valueOf(boutonChoisi.getActionCommand());
        joueur.out.println(Constantes.CHOIX + " " + boutonChoisi.getActionCommand());
        action.setText("attendre");
        feuilleM.setEnabled(false);
        ciseauxM.setEnabled(false);
        caillouM.setEnabled(false);
        joueur.jouer = false;
        indiquerChoixAdversaire();
    }

    public void indiquerChoixAdversaire() {
        Choix choixAdversaire = joueur.adversaire.choix;

        if ((joueur.choix != null) &&( choixAdversaire != null)) {
            if (choixAdversaire == Choix.FEUILLE) boutonChoixAdversaire = feuilleA;
            else if (choixAdversaire == Choix.CISEAUX) boutonChoixAdversaire = ciseauxA;
            else if (choixAdversaire == Choix.CAILLOU) boutonChoixAdversaire = caillouA;
            boutonChoixAdversaire.setBackground(Color.RED);
        }
    }

    public void update(Observable o, Object obj) {
        nombrePoints.setText(Integer.toString(joueur.nbPoints));
        nombrePointsAdversaire.setText(Integer.toString(joueur.adversaire.nbPoints));
        indiquerChoixAdversaire();
        if (joueur.partieGagnee) {
            action.setText("vous avez gagne");
            feuilleM.setEnabled(false);
            ciseauxM.setEnabled(false);
            caillouM.setEnabled(false);
        }
        else if (joueur.partiePerdue) {
            action.setText("vous avez perdu");
            feuilleM.setEnabled(false);
            ciseauxM.setEnabled(false);
            caillouM.setEnabled(false);
        }
        else if (joueur.jouer) {
            if (boutonChoisi != null) boutonChoisi.setBackground(couleurBouton);
            feuilleM.setEnabled(true);
            ciseauxM.setEnabled(true);
            caillouM.setEnabled(true);
            action.setText("vous pouvez jouer");
            if (boutonChoixAdversaire != null)
                boutonChoixAdversaire.setBackground(couleurBouton);
        }
    }
}