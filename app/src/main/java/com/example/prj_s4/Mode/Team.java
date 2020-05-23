package com.example.prj_s4.Mode;

import java.util.ArrayList;

public class Team {
    private ArrayList<Unite> membres;
    private  double note;

    public Team() {
    }

    public Team(ArrayList<Unite> membres, int note) {
        this.membres = membres;
        this.note = note;
    }

    public ArrayList<Unite> getMembres() {
        return membres;
    }

    public void setMembres(ArrayList<Unite> membres) {
        this.membres = membres;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
