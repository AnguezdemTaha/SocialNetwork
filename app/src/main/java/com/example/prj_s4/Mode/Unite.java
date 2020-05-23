package com.example.prj_s4.Mode;

import java.util.ArrayList;

public class Unite {
    private ArrayList<Propriete> proprietes = new ArrayList<>();
    private String nom;
    private  int lvl;
    //ulit
    //dmg
    //..


    public Unite() {
    }

    public Unite(ArrayList<Propriete> proprietes, String nom, int lvl) {
        this.proprietes = proprietes;
        this.nom = nom;
        this.lvl =lvl;
    }

    public ArrayList<Propriete> getProprietes() {
        return proprietes;
    }

    public void setProprietes(ArrayList<Propriete> proprietes) {
        this.proprietes = proprietes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
