/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataBase;
import com.mycompany.examfinal.Model.Animal;

import java.util.List;
/**
 *
 * @author 2417010
 */
public interface IZooDataAccess {


    // CREATE
    void createAnimal(Animal animal);

    // READ
    Animal getAnimalByName(String nom);
    List<Animal> getAllAnimals();

    // UPDATE
    void updateAnimal(Animal animal);

    // DELETE
    void deleteAnimal(String nom);

    public List<Animal> searchAnimalsByName(String partialName);
}


