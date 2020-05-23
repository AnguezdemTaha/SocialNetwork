package com.example.prj_s4.barix2;

public class GestionAgenceLocation {
    public static void main(String[] args) {
        Agence agence =new Agence();
        Employe employe =new Employe();
        Directeur directeur =new Directeur();
        Voiture voiture =new Voiture();

        Location location=new Location();
        Client client=new Client();
        //vous pouvez utiliser les construbteurs
        directeur.setNom("d");
        directeur.setPrenom("d");

        Voiture[] voitures={voiture,voiture};

        agence.setNom("x");
        agence.setAdresse("x");
        agence.setDirecteur(directeur);


        agence.setVoitures(voitures);

        voiture.setAgence(agence);
        voiture.setType("lux");//par exemple
        voiture.setLouee(false);

        location.setAgence(agence);
        location.setPrix(1500);//par example

        client.setLocation(location);
        //...
        //vous pouvez faire le me chose si vous voulait ajouter location client (pour les autre attributs
        //accée aux methodes
        //a
        location.calculeMontant();
        //b
        client.calculeMontantClient();
        //c
        agence.afficherVoitures();
        //d
        agence.afficherVoituresLouees();
        //e
        client.AfficherDocuments();
        //
        location.calculeGarantie();

    }
}
