import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AcceuilPage {
    private JFrame frame;

    public AcceuilPage() {
        // Création de la fenêtre
        frame = new JFrame("Accueil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Ajustement de la taille de la fenêtre
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Panel principal avec gestionnaire de disposition border
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel pour les composants avec gestionnaire de disposition border
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(new Color(240, 240, 240));

        // Textes
        JLabel mainTextLabel = new JLabel("Bienvenue dans le menu principal");
        mainTextLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Augmentation de la taille du texte
        mainTextLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel secondTextLabel = new JLabel("Choisissez une option à effectuer");
        secondTextLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Augmentation de la taille du texte
        secondTextLabel.setHorizontalAlignment(JLabel.CENTER);

        textPanel.add(mainTextLabel, BorderLayout.NORTH);
        textPanel.add(secondTextLabel, BorderLayout.SOUTH);

        // Panel pour les boutons avec gestionnaire de disposition grid
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15)); // Augmentation de l'espace entre les boutons
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Boutons
        JButton button1 = createStyledButton("Enregistrer un vol");
        JButton button2 = createStyledButton("Enregistrer un passager");
        JButton button3 = createStyledButton("Effectuer une réservation");
        JButton button4 = createStyledButton("Annuler une réservation");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        // Ajout des composants au panneau principal
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        frame.add(mainPanel, BorderLayout.CENTER);

        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        frame.setVisible(true);

        // Ajout d'un écouteur d'événements pour le bouton1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir une nouvelle interface depuis un fichier existant
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new VolInterface(); // Remplacez NouvelleInterface par le nom de votre classe/interface
                    }
                });
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir une nouvelle interface depuis un fichier existant
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new PassagerInterface(); // Remplacez NouvelleInterface par le nom de votre classe/interface
                    }
                });
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20)); // Augmentation de la taille du texte
        button.setBackground(new Color(30, 144, 255)); // Couleur bleu foncé
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Ajout de la marge autour du texte

        return button;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AcceuilPage();
            }
        });
    }
}
