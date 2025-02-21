package View;

import com.mycompany.examfinal.Model.Animal;
import com.mycompany.examfinal.Model.Enclos;
import com.mycompany.examfinal.ZooService;
import com.mycompany.examfinal.Model.EnclosService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class zoo extends JFrame {

    private ZooService zooService;
    private EnclosService enclosService;

    // Composants de l’interface pour les animaux
    private JTextField txtId;
    private JTextField txtNom;
    private JTextField txtAge;
    private JTextField txtEspece;
    private JTextField txtRegime;
    private JButton btnAjouter;
    private JButton btnModifier;
    private JButton btnSupprimer;
    private JButton btnCharger;
    private JTable tableAnimaux;
    private DefaultTableModel tableModel;

    // Composants pour la recherche
    private JTextField txtRecherche;
    private JButton btnRechercher;

    // Bouton pour afficher la liste des enclos
    private JButton btnAfficherEnclos;

    public zoo() {
        zooService = new ZooService();
        enclosService = new EnclosService();  // Instanciation du service enclos
        initComponents();
        setTitle("Gestion du Zoo");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        // Layout principal
        setLayout(new BorderLayout());

        // -------------------
        // 1) Panneau de saisie et boutons pour les animaux (NORD)
        // -------------------
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Saisie Animal"));
        
        panelInput.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelInput.add(txtId);
        
        panelInput.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        panelInput.add(txtNom);
        
        panelInput.add(new JLabel("Age:"));
        txtAge = new JTextField();
        panelInput.add(txtAge);
        
        panelInput.add(new JLabel("Espèce:"));
        txtEspece = new JTextField();
        panelInput.add(txtEspece);
        
        panelInput.add(new JLabel("Régime:"));
        txtRegime = new JTextField();
        panelInput.add(txtRegime);
        
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnAjouter = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        panelButtons.add(btnAjouter);
        panelButtons.add(btnModifier);
        panelButtons.add(btnSupprimer);
        
        JPanel panelSaisie = new JPanel(new BorderLayout());
        panelSaisie.add(panelInput, BorderLayout.CENTER);
        panelSaisie.add(panelButtons, BorderLayout.SOUTH);

        add(panelSaisie, BorderLayout.NORTH);

        // -------------------
        // 2) Panneau pour la liste des animaux (CENTRE)
        // -------------------
        JPanel panelListe = new JPanel(new BorderLayout());
        panelListe.setBorder(BorderFactory.createTitledBorder("Liste des Animaux"));
        
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Age", "Espèce", "Régime"}, 0);
        tableAnimaux = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableAnimaux);
        panelListe.add(scrollPane, BorderLayout.CENTER);
        
        btnCharger = new JButton("Afficher la liste des animaux");
        panelListe.add(btnCharger, BorderLayout.SOUTH);

        add(panelListe, BorderLayout.CENTER);

        // -------------------
        // 3) Panneau pour afficher la liste des enclos
        // -------------------
        JPanel panelEnclos = new JPanel(new BorderLayout());
        panelEnclos.setBorder(BorderFactory.createTitledBorder("Enclos"));
        btnAfficherEnclos = new JButton("Afficher la liste des enclos");
        btnAfficherEnclos.addActionListener(e -> afficherListeEnclos());
        panelEnclos.add(btnAfficherEnclos, BorderLayout.CENTER);

        // -------------------
        // 4) Panneau pour la recherche
        // -------------------
        JPanel panelRecherche = new JPanel(new FlowLayout());
        panelRecherche.setBorder(BorderFactory.createTitledBorder("Recherche"));

        txtRecherche = new JTextField(15);
        btnRechercher = new JButton("Rechercher");
        panelRecherche.add(new JLabel("Nom :"));
        panelRecherche.add(txtRecherche);
        panelRecherche.add(btnRechercher);
        btnRechercher.addActionListener(e -> rechercherAnimal());

        // -------------------
        // 5) Panel du bas (SOUTH) pour combiner Enclos + Recherche
        // -------------------
        JPanel panelBas = new JPanel(new GridLayout(2, 1));
        // 2 lignes : 
        //   - première ligne : panelEnclos
        //   - deuxième ligne : panelRecherche
        panelBas.add(panelEnclos);
        panelBas.add(panelRecherche);

        add(panelBas, BorderLayout.SOUTH);

        // -------------------
        // Actions sur les boutons pour les animaux
        // -------------------
        btnAjouter.addActionListener(e -> ajouterAnimal());
        btnModifier.addActionListener(e -> modifierAnimal());
        btnSupprimer.addActionListener(e -> supprimerAnimal());
        btnCharger.addActionListener(e -> chargerAnimaux());
        
        // Listener pour la sélection dans le tableau des animaux
        tableAnimaux.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableAnimaux.getSelectedRow() != -1) {
                int selectedRow = tableAnimaux.getSelectedRow();
                Object idObj = tableModel.getValueAt(selectedRow, 0);
                Object nomObj = tableModel.getValueAt(selectedRow, 1);
                Object ageObj = tableModel.getValueAt(selectedRow, 2);
                Object especeObj = tableModel.getValueAt(selectedRow, 3);
                Object regimeObj = tableModel.getValueAt(selectedRow, 4);
                
                txtId.setText(idObj != null ? idObj.toString() : "");
                txtNom.setText(nomObj != null ? nomObj.toString() : "");
                txtAge.setText(ageObj != null ? ageObj.toString() : "");
                txtEspece.setText(especeObj != null ? especeObj.toString() : "");
                txtRegime.setText(regimeObj != null ? regimeObj.toString() : "");
            }
        });
    }

    // -------------------
    // Méthodes
    // -------------------
    private void afficherListeEnclos() {
        // On récupère la liste des enclos depuis le service
        List<Enclos> enclosListe = enclosService.getAllEnclos();
        StringBuilder sb = new StringBuilder();
        for (Enclos enclos : enclosListe) {
            sb.append(enclos.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Liste des enclos", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void ajouterAnimal() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nom = txtNom.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String espece = txtEspece.getText().trim();
            String regime = txtRegime.getText().trim();
            
            if(nom.isEmpty() || espece.isEmpty() || regime.isEmpty()){
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Champs vides", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Animal animal = new Animal(id, nom, age, espece, regime);
            zooService.addAnimal(animal);
            chargerAnimaux();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID et l'Age doivent être des nombres.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rechercherAnimal() {
        String saisie = txtRecherche.getText().trim();
        if (saisie.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez saisir un nom ou une partie de nom.", 
                "Recherche vide", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Appel au service
        List<Animal> resultats = zooService.searchAnimals(saisie);

        // On efface le tableau et on affiche les résultats
        tableModel.setRowCount(0);
        for (Animal a : resultats) {
            tableModel.addRow(new Object[]{
                a.getId(),
                a.getNom(),
                a.getAge(),
                a.getEspece(),
                a.getRegimeAlimentaire()
            });
        }
    }

    private void modifierAnimal() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nom = txtNom.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String espece = txtEspece.getText().trim();
            String regime = txtRegime.getText().trim();
            
            if(nom.isEmpty() || espece.isEmpty() || regime.isEmpty()){
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Champs vides", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Animal animal = new Animal(id, nom, age, espece, regime);
            zooService.updateAnimal(animal);
            chargerAnimaux();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID et l'Age doivent être des nombres.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void supprimerAnimal() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            zooService.deleteAnimal(id);
            chargerAnimaux();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chargerAnimaux() {
        tableModel.setRowCount(0);
        List<Animal> animaux = zooService.getAllAnimals();
        for (Animal a : animaux) {
            tableModel.addRow(new Object[]{
                a.getId(), 
                a.getNom(), 
                a.getAge(), 
                a.getEspece(), 
                a.getRegimeAlimentaire()
            });
        }
    }
    
    private void clearFields() {
        txtId.setText("");
        txtNom.setText("");
        txtAge.setText("");
        txtEspece.setText("");
        txtRegime.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(zoo::new);
    }
}
