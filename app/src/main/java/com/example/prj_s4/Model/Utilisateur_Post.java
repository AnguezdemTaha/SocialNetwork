package com.example.prj_s4.Model;

public class Utilisateur_Post {
    private String post;
    private Utilisateur utilisateur;
    private int degre;

    public Utilisateur_Post() {
    }

    public Utilisateur_Post(String post, Utilisateur utilisateur, int degre) {
        this.post = post;
        this.utilisateur = utilisateur;
        this.degre = degre;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getDegre() {
        return degre;
    }

    public void setDegre(int degre) {
        this.degre = degre;
    }
}
