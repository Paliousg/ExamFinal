/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import com.mycompany.examfinal.Model.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZooDatabaseManager implements IZooDataAccess {

    private static ZooDatabaseManager instance;
    private Connection connection;

    private ZooDatabaseManager() {
        // Initialisation de la connexion SQLite
        try {
            // Chemin vers la BD, par ex. "zoo.db"
            String url = "jdbc:sqlite:Zoo1.db";
            connection = DriverManager.getConnection(url);
            // Crée la table si elle n'existe pas
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ZooDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ZooDatabaseManager();
        }
        return instance;
    }

    private void createTableIfNotExists() throws SQLException {
        // On définit un id (clé primaire auto-incrémentée), un nom, un age, etc.
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Animal ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nom TEXT NOT NULL UNIQUE, "
                + "age INTEGER, "
                + "espece TEXT, "
                + "regimeAlimentaire TEXT"
                + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    @Override
    public void createAnimal(Animal animal) {
        // On insère : nom, age, espece, regimeAlimentaire
        // id est géré automatiquement (auto-incrément)
        String sql = "INSERT INTO Animal(nom, age, espece, regimeAlimentaire) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, animal.getNom());
            pstmt.setInt(2, animal.getAge());
            pstmt.setString(3, animal.getEspece());
            pstmt.setString(4, animal.getRegimeAlimentaire());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Animal getAnimalByName(String nom) {
        String sql = "SELECT id, nom, age, espece, regimeAlimentaire FROM Animal WHERE nom = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // On lit toutes les colonnes, y compris id et age
                Animal animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("age"),
                        rs.getString("espece"),
                        rs.getString("regimeAlimentaire")
                );
                return animal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT id, nom, age, espece, regimeAlimentaire FROM Animal";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("age"),
                        rs.getString("espece"),
                        rs.getString("regimeAlimentaire")
                );
                animals.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public void updateAnimal(Animal animal) {
        // Mise à jour basée sur le nom (comme dans l'interface)
        // Si vous voulez mettre à jour par id, adaptez la requête
        String sql = "UPDATE Animal SET age = ?, espece = ?, regimeAlimentaire = ? WHERE nom = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, animal.getAge());
            pstmt.setString(2, animal.getEspece());
            pstmt.setString(3, animal.getRegimeAlimentaire());
            pstmt.setString(4, animal.getNom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAnimal(String nom) {
        // Suppression basée sur le nom
        // Si vous voulez supprimer par id, créez une autre méthode (deleteAnimalById)
        String sql = "DELETE FROM Animal WHERE nom = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
public List<Animal> searchAnimalsByName(String partialName) {
    List<Animal> results = new ArrayList<>();
    String sql = "SELECT id, nom, age, espece, regimeAlimentaire FROM Animal WHERE nom LIKE ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, "%" + partialName + "%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Animal animal = new Animal(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getInt("age"),
                rs.getString("espece"),
                rs.getString("regimeAlimentaire")
            );
            results.add(animal);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return results;
}

}


