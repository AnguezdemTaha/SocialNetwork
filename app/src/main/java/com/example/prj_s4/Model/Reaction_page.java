package com.example.prj_s4.Model;

public class Reaction_page {
    private String type;
    private String contenu;
    private  String p;
    private  Utilisateur u;

    public Reaction_page() {
    }

    public Reaction_page(String type, String contenu, String p, Utilisateur u) {
        this.type = type;
        this.contenu = contenu;
        this.p = p;
        this.u = u;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }
}
