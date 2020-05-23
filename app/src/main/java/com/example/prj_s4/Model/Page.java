package com.example.prj_s4.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class Page {
    private String nom;
    private Date date_creation;
    private String type;

    private Utilisateur owner;


    public Page() {
    }

    public Page(String nom, Date date_creation, String type, Utilisateur owner) {
        this.nom = nom;
        this.date_creation = date_creation;
        this.type = type;
        this.owner = owner;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Utilisateur getOwner() {
        return owner;
    }

    public void setOwner(Utilisateur owner) {
        this.owner = owner;
    }


}
