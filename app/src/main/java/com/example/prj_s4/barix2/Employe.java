package com.example.prj_s4.barix2;

public class Employe {
    //etape1:
    private  String nom;
    private  String prenom;
    //etape2:
    private Agence agence;
    //etape3:


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
}
