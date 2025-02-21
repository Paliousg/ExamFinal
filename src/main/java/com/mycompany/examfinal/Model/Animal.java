package com.mycompany.examfinal.Model;

public class Animal {
    private int id;
    private String nom;
    private int age;
    private String espece;
    private String regimeAlimentaire;

    // Constructeur sans arguments
    public Animal() {
    }

    // Constructeur avec id, nom, age, espece et regimeAlimentaire
    public Animal(int id, String nom, int age, String espece, String regimeAlimentaire) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.espece = espece;
        this.regimeAlimentaire = regimeAlimentaire;
    }

    // Constructeur existant avec 3 arguments (à conserver si besoin)
    public Animal(String nom, String espece, String regimeAlimentaire) {
        this.nom = nom;
        this.espece = espece;
        this.regimeAlimentaire = regimeAlimentaire;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public void setRegimeAlimentaire(String regimeAlimentaire) {
        this.regimeAlimentaire = regimeAlimentaire;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                ", espece='" + espece + '\'' +
                ", regimeAlimentaire='" + regimeAlimentaire + '\'' +
                '}';
    }
}
