/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 2417010
 */
package com.mycompany.examfinal.Model;

import java.util.ArrayList;
import java.util.List;

public class EnclosService {
    // Méthode simulée qui retourne une liste d'enclos
    public List<Enclos> getAllEnclos() {
        List<Enclos> liste = new ArrayList<>();
        // Exemple de données simulées
        liste.add(new Enclos(1, "Enclos des reptiles", "Terrestre"));
        liste.add(new Enclos(2, "Enclos des poissons", "Aquatique"));
        liste.add(new Enclos(3, "Enclos des oiseaux", "Terrestre"));
        liste.add(new Enclos(4, "Enclos des Mammifères", "Terrestre"));
        return liste;
    }
}

