package com.example.prj_s4.Model;

import java.util.ArrayList;

public class Utilisateur extends Personne {

    private String nom;
    private String mot_de_passe;
    private String email;
    private String num_telephone;
    private String type;
    //private ArrayList<Etude> etudes;
    //private ArrayList<Projet> projets;
    //private ArrayList<Experience> experiences;



    public Utilisateur() {
    }

    public Utilisateur(String nom, String mot_de_passe, String email, String num_telephone, String type) {
        this.nom = nom;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.num_telephone = num_telephone;
        this.type = type;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(String num_telephone) {
        this.num_telephone = num_telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}