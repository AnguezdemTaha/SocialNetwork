package com.example.prj_s4.barix2;

public class Client {
    //etape1:
    private String nom;
    private String prenom;
    private String type;
    //etape2:
    private Location location;
    private Document document1;
    private Document document2;
    private Document document3;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Document getDocument1() {
        return document1;
    }

    public void setDocument1(Document document1) {
        this.document1 = document1;
    }

    public Document getDocument2() {
        return document2;
    }

    public void setDocument2(Document document2) {
        this.document2 = document2;
    }

    public Document getDocument3() {
        return document3;
    }

    public void setDocument3(Document document3) {
        this.document3 = document3;
    }

    public float calculeMontantClient(){
        //je pense c'est le montatn d'allocation donc :
        return location.calculeMontant();
    }
    public Document[] AfficherDocuments(){
        Document[] documents = new Document[0];
        documents[0]=document1;
        documents[1]=document2;
        documents[2]=document3;
        return documents;
    }
}
