/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examfinal.Model;

public class Enclos {
    private int id;
    private String nom;
    private String type; // Par exemple, "aquatique", "terrestre", etc.

    public Enclos() {
    }

    public Enclos(int id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Enclos{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
