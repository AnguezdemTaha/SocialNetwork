package com.example.prj_s4.Model;

import java.util.ArrayList;
import java.util.Date;

public class Message {
    private String contenu;
    private Date date_msg;
    private Utilisateur per_envoye;
    private ArrayList<Utilisateur> per_recus;


    public Message() {
    }

    public Message(String contenu, Date date_msg, Utilisateur per_envoye, ArrayList<Utilisateur> per_recus) {
        this.contenu = contenu;
        this.date_msg = date_msg;
        this.per_envoye = per_envoye;
        this.per_recus = per_recus;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_msg() {
        return date_msg;
    }

    public void setDate_msg(Date date_msg) {
        this.date_msg = date_msg;
    }

    public Utilisateur getPer_envoye() {
        return per_envoye;
    }

    public void setPer_envoye(Utilisateur per_envoye) {
        this.per_envoye = per_envoye;
    }

    public ArrayList<Utilisateur> getPer_recus() {
        return per_recus;
    }

    public void setPer_recus(ArrayList<Utilisateur> per_recus) {
        this.per_recus = per_recus;
    }
}
