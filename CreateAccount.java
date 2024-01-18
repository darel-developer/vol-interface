import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccount {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public CreateAccount() {
        // Création de la fenêtre
        frame = new JFrame("Création de Compte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Couleur de fond

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel pour les composants
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        // Labels
        JLabel titleLabel = new JLabel("Création de Compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JLabel emailLabel = new JLabel("Adresse e-mail:");

        // Champs de texte et mot de passe avec bordures arrondies
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();

        usernameField.setBorder(BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Bouton de création de compte avec couleur personnalisée
        JButton createAccountButton = new JButton("Créer le Compte");
        createAccountButton.setBackground(new Color(70, 130, 180)); // Couleur bleu foncé
        createAccountButton.setForeground(Color.WHITE); // Texte en blanc
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors de la création de compte
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();

                // message
                JOptionPane.showMessageDialog(frame, "Compte créé pour l'utilisateur: " + username);
            }
        });

        // Ajout des composants au panneau
        panel.add(titleLabel);
        panel.add(new JLabel()); // Espace vide
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(new JLabel()); // Espace vide
        panel.add(createAccountButton);

        // Ajout du panel au panel principal
        mainPanel.add(panel, BorderLayout.CENTER);

        // Ajout du panel principal à la fenêtre
        frame.add(mainPanel, BorderLayout.CENTER);

        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        frame.setVisible(true);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();

                // Vous pouvez ajouter du code pour traiter les informations du compte ici
                if (saveToDatabase(username, password, email)) {
                    JOptionPane.showMessageDialog(frame, "Compte créé pour l'utilisateur: " + username);
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la création du compte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean saveToDatabase(String username, String password, String email) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_reservation_vol";
        String dbUsername = "root";
        String dbPassword = " ";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String query = "INSERT INTO compte (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
                return true; // La création du compte a réussi
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // La création du compte a échoué
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateAccount();
            }
        });
    }
}
