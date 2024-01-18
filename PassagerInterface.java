import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassagerInterface extends JFrame {

    private JTextField nomField, adresseField, telephoneField, passeportField;
    private JTextArea resultArea;

    public PassagerInterface() {
        super("Informations du Passager");

        // Création des composants
        nomField = new JTextField(20);
        adresseField = new JTextField(20);
        telephoneField = new JTextField(20);
        passeportField = new JTextField(20);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JButton submitButton = new JButton("Enregistrer");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerInformations();
            }
        });

        // Création du panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("Nom: "));
        mainPanel.add(nomField);
        mainPanel.add(new JLabel("Adresse: "));
        mainPanel.add(adresseField);
        mainPanel.add(new JLabel("Téléphone: "));
        mainPanel.add(telephoneField);
        mainPanel.add(new JLabel("Passeport: "));
        mainPanel.add(passeportField);
        mainPanel.add(submitButton);

        // Ajout des composants au cadre principal
        add(mainPanel);
        add(new JScrollPane(resultArea));

        // Configuration du cadre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    private void enregistrerInformations() {
        String nom = nomField.getText();
        String adresse = adresseField.getText();
        String telephone = telephoneField.getText();
        String passeport = passeportField.getText();

        // Afficher les informations dans la zone de résultat
        String informations = "Nom: " + nom + "\nAdresse: " + adresse + "\nTéléphone: " + telephone + "\nPasseport: " + passeport + "\n\n";
        resultArea.append(informations);

        // Effacer les champs après l'enregistrement
        nomField.setText("");
        adresseField.setText("");
        telephoneField.setText("");
        passeportField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PassagerInterface().setVisible(true);
            }
        });
    }
}
