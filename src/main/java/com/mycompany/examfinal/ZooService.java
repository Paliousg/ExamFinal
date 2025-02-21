package com.mycompany.examfinal;

import DataBase.ZooDatabaseManager;
import DataBase.IZooDataAccess;
import com.mycompany.examfinal.Model.Animal;
import java.util.List;

public class ZooService {

    private IZooDataAccess dataAccess;

    public ZooService() {
        // Par défaut, on utilise l'implémentation Singleton
        this.dataAccess = ZooDatabaseManager.getInstance();
    }

    // Pour éventuellement injecter une implémentation mock en test unitaire
    public ZooService(IZooDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
    public List<Animal> searchAnimals(String partialName) {
    return dataAccess.searchAnimalsByName(partialName);
}

    public void addAnimal(Animal animal) {
        dataAccess.createAnimal(animal);
    }

    public Animal getAnimal(String nom) {
        return dataAccess.getAnimalByName(nom);
    }

    public List<Animal> getAllAnimals() {
        return dataAccess.getAllAnimals();
    }

    public void updateAnimal(Animal animal) {
        dataAccess.updateAnimal(animal);
    }

    public void removeAnimal(String nom) {
        dataAccess.deleteAnimal(nom);
    }

    // Implémentation de la suppression par id
    public void deleteAnimal(int id) {
        // Recherche de l'animal par son id dans la liste
        List<Animal> animals = dataAccess.getAllAnimals();
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                // On suppose que le nom est la clé primaire pour la suppression
                dataAccess.deleteAnimal(animal.getNom());
                return;
            }
        }
        // Optionnel : lever une exception si aucun animal n'est trouvé
        throw new IllegalArgumentException("Animal with id " + id + " not found.");
    }
}
