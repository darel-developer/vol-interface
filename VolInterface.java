import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolInterface{
    private JFrame frame;
    private JTextField flightNumberField, airlineField, departureLocationField, departureDateField,
            departureTimeField, arrivalLocationField, arrivalDateField, arrivalTimeField;
    private JButton addButton, showButton;
    private DefaultTableModel tableModel;
    private JTable flightTable;

    public VolInterface() {
        frame = new JFrame("Gestion des Vols");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 400);

        // Panel pour l'enregistrement d'un vol
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Numéro de Vol:"), gbc);

        gbc.gridy++;
        flightNumberField = new JTextField(15);
        inputPanel.add(flightNumberField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Compagnie:"), gbc);

        gbc.gridy++;
        airlineField = new JTextField(15);
        inputPanel.add(airlineField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Lieu de Départ:"), gbc);

        gbc.gridy++;
        departureLocationField = new JTextField(15);
        inputPanel.add(departureLocationField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Date de Départ (YYYY-MM-DD):"), gbc);

        gbc.gridy++;
        departureDateField = new JTextField(15);
        inputPanel.add(departureDateField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Heure de Départ (HH:MM):"), gbc);

        gbc.gridy++;
        departureTimeField = new JTextField(15);
        inputPanel.add(departureTimeField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Lieu d'Arrivée:"), gbc);

        gbc.gridy++;
        arrivalLocationField = new JTextField(15);
        inputPanel.add(arrivalLocationField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Date d'Arrivée (YYYY-MM-DD):"), gbc);

        gbc.gridy++;
        arrivalDateField = new JTextField(15);
        inputPanel.add(arrivalDateField, gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Heure d'Arrivée (HH:MM):"), gbc);

        gbc.gridy++;
        arrivalTimeField = new JTextField(15);
        inputPanel.add(arrivalTimeField, gbc);

        gbc.gridy++;
        addButton = new JButton("Enregistrer le Vol");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFlight();
            }
        });
        inputPanel.add(addButton, gbc);

        // Panel pour afficher les informations des vols
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Numéro de Vol");
        tableModel.addColumn("Compagnie");
        tableModel.addColumn("Lieu de Départ");
        tableModel.addColumn("Date de Départ");
        tableModel.addColumn("Heure de Départ");
        tableModel.addColumn("Lieu d'Arrivée");
        tableModel.addColumn("Date d'Arrivée");
        tableModel.addColumn("Heure d'Arrivée");

        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        showButton = new JButton("Afficher les Vols Enregistrés");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFlights();
            }
        });

        displayPanel.add(showButton, BorderLayout.SOUTH);

        // Ajout des panels à la fenêtre
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addFlight() {
        String flightNumber = flightNumberField.getText();
        String airline = airlineField.getText();
        String departureLocation = departureLocationField.getText();
        String departureDate = departureDateField.getText();
        String departureTime = departureTimeField.getText();
        String arrivalLocation = arrivalLocationField.getText();
        String arrivalDate = arrivalDateField.getText();
        String arrivalTime = arrivalTimeField.getText();

        // Vérification des champs non vides avant d'ajouter le vol
        if (!flightNumber.isEmpty() && !airline.isEmpty() && !departureLocation.isEmpty() &&
                !departureDate.isEmpty() && !departureTime.isEmpty() && !arrivalLocation.isEmpty() &&
                !arrivalDate.isEmpty() && !arrivalTime.isEmpty()) {
            String[] flightData = {
                    flightNumber, airline, departureLocation, departureDate, departureTime,
                    arrivalLocation, arrivalDate, arrivalTime
            };

            tableModel.addRow(flightData);

            // Effacer les champs après l'ajout
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showFlights() {
        // Afficher les vols dans une fenêtre pop-up
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            for (int col = 0; col < tableModel.getColumnCount(); col++) {
                textArea.append(tableModel.getValueAt(row, col) + " ");
            }
            textArea.append("\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "Vols Enregistrés", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        flightNumberField.setText("");
        airlineField.setText("");
        departureLocationField.setText("");
        departureDateField.setText("");
        departureTimeField.setText("");
        arrivalLocationField.setText("");
        arrivalDateField.setText("");
        arrivalTimeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VolInterface();
            }
        });
    }
}
