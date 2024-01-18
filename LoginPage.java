import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginPage {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        // Création de la fenêtre
        frame = new JFrame("Page de Connexion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 600);

        // Création du panneau principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Ajout d'un panneau pour les informations au début de l'interface
        JPanel infoPanel = new JPanel();
        JLabel infoLabel = new JLabel("Bienvenue sur la page de connexion");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Ajout de style
        infoPanel.add(infoLabel);
        frame.getContentPane().add(infoPanel, BorderLayout.NORTH); // Ajout du panneau d'information

        // Ajout du label et du champ de texte pour le nom d'utilisateur
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Pseudo"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        usernameField = new JTextField(20);
        panel.add(usernameField, constraints);

        // Ajout du label et du champ de texte pour le mot de passe
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Mot de passe"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, constraints);

        // Ajout du bouton de connexion
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        JButton loginButton = new JButton("Connexion");
        loginButton.setBackground(Color.blue); // style
        loginButton.setForeground(Color.WHITE); // style (blanc)
        panel.add(loginButton, constraints);

        // Ajout d'un écouteur d'événements pour le bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Stocker les informations dans la base de données
                saveToDatabase(username, password);

                // Ouvrir la page d'administration
                openAdminPage();
            }
        });

        // Ajout du bouton "Créer un compte"
        constraints.gridx = 1;
        constraints.gridy = 3;
        JButton createAccountButton = new JButton("Créer un compte");
        panel.add(createAccountButton, constraints);

        // Ajout d'un écouteur d'événements pour le bouton "Créer un compte"
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appeler la méthode pour ouvrir la page de création de compte
                openCreateAccountPage();
            }
        });

        // Ajout du panneau à la fenêtre, en utilisant BorderLayout.CENTER pour centrer le contenu
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Affichage de la fenêtre
        frame.setVisible(true);
    }

    private void saveToDatabase(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        // Utilisation de JDBC pour se connecter à la base de données et stocker les informations
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_reservation_vol";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            String query = "INSERT INTO utilisateurs (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void openAdminPage() {
        // Créer une instance de la classe AcceuilPage
        AcceuilPage acceuilPage = new AcceuilPage();

        // Fermer la fenêtre de connexion
        frame.dispose();
    }

    private void openCreateAccountPage() {
        // Créer une instance de la classe CreateAccountPage
        CreateAccount createAccountPage = new CreateAccount();

        // Fermer la fenêtre de connexion
        frame.dispose();
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
