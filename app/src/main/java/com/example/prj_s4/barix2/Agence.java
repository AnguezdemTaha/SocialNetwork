package com.example.prj_s4.barix2;

public class Agence {
    //etape1:declaration des attrubit pures
    private String nom;
    private String adresse;
    //etape2:declaration des attributs d'association avec les autres classes
    //avec voiture
    private Voiture[] voitures;
    //avec Employe
    private Employe emloye1;
    private Employe emloye2;
    //avec directeur
    private Directeur directeur;
    //avec location
    private Location location;

    //etapes3:getters_setters( automatique ) + fonction if elle sont exist:


    public Voiture[] afficherVoitures() {
        return voitures;
    }

    public Voiture[] afficherVoituresLouees() {
        Voiture[] voituresLouees = new Voiture[0];
        for (int i = 0; i < voitures.length; i++) {
            if (voitures[i].isLouee()) {
                //je ne suis pas sure !!!!!
                voituresLouees[voituresLouees.length]=voitures[i];
            }
        }
        return voituresLouees;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Voiture[] getVoitures() {
        return voitures;
    }

    public void setVoitures(Voiture[] voitures) {
        this.voitures = voitures;
    }

    public Employe getEmloye1() {
        return emloye1;
    }

    public void setEmloye1(Employe emloye1) {
        this.emloye1 = emloye1;
    }

    public Employe getEmloye2() {
        return emloye2;
    }

    public void setEmloye2(Employe emloye2) {
        this.emloye2 = emloye2;
    }

    public Directeur getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
