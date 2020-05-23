package com.example.prj_s4.barix2;

import java.util.Date;
import java.util.Scanner;

public class Location {
    //etape1:
    private Date date;
    private float prix;
    private Date dateDebut;
    private Date datefin;
    private float garantie;
    private String typePayement;
    //etape2:
    private Agence agence;
    private Client client;
    private Voiture voiture;
    //etape3:


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public float getGarantie() {
        return garantie;
    }

    public void setGarantie(float garantie) {
        this.garantie = garantie;
    }

    public String getTypePayement() {
        return typePayement;
    }

    public void setTypePayement(String typePayement) {
        this.typePayement = typePayement;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;

    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public float calculeMontant(){
        //il faut donne a relation de calcule de montant , exemple :
        //!!! ce que je donne est fausse !!!!!
        return (prix*(datefin.getTime()-dateDebut.getTime()));
    }

    public float calculeGarantie(){
        //le meme chose que calculemontant cela est fausse

        Scanner sc = new Scanner(System.in);
        System.out.println("prix :");
        float prix1 =sc.nextFloat();

        if(voiture.getType().equals("Lux")){
            return (float) (prix*(0.3));
        }
        else{
            if(voiture.getType().equals("4*4")){
                return (float) (prix*(0.2));
            }
            else{
                if(voiture.getType().equals("Sport")){
                    return (float) (prix*(0.1));
                }
                else{
                    return (float) (prix*(0.05));
                }
            }
        }
    }
}
