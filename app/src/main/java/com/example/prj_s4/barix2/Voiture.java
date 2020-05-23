package com.example.prj_s4.barix2;

public class Voiture {
    //etape1:
    private String carteGrise;
    private String type;
    private String marque;
    private String matricule;
    private Boolean louee;
    //etape2:
    private Agence agence;
    private Location location;
    //etape 3:

    public boolean isLouee() {
        return louee;
    }

    public void setLouee(Boolean louee) {
        this.louee = louee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarteGrise() {
        return carteGrise;
    }

    public void setCarteGrise(String carteGrise) {
        this.carteGrise = carteGrise;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
