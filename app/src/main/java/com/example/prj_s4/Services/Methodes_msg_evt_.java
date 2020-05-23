package com.example.prj_s4.Services;

import com.example.prj_s4.Model.Message;
import com.example.prj_s4.Model.Utilisateur;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class Methodes_msg_evt_ {



    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Message");
    }

    public static Task<DocumentReference> creatMessage(Message p) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(p);

    }

    public static Task<DocumentReference> creatMessagebot(String p) {
        //Personne userToCreate = new Personne(nom);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("msg_contenu", p);
        return db.collection("Chatbot").add(p1);

    }
    public static Task<QuerySnapshot> GetAllMessagebot() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("Chatbot").get();

    }

    public static Task<QuerySnapshot> GetMessages(Utilisateur p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        /*p1.put("etudes", null);
        p1.put("projets", null);
        p1.put("pages", null);
        p1.put("experiences", null);*/

        p1.put("mot_de_passe",p.getMot_de_passe());
        p1.put("num_telephone",p.getNum_telephone());
        p1.put("email",p.getEmail());
        p1.put("type",p.getType());





        /*p1.put("mot_de_passe", p.getMot_de_passe());
        p1.put("email",p.getEmail());
        p1.put("num_telephone",p.getNum_telephone());
        p1.put("type",p.getType());*/

        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).get();


        return r1;




    }
    public static Task<QuerySnapshot> GetMessagesonce(Utilisateur p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());


        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).get();


        return r1;

    }

    public static Task<QuerySnapshot> GetMessagesrecu(Utilisateur p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        p1.put("mot_de_passe",p.getMot_de_passe());
        p1.put("num_telephone",p.getNum_telephone());
        p1.put("email",p.getEmail());
        p1.put("type",p.getType());


        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).get();
        Task<QuerySnapshot> r2 = getUsersCollection().whereArrayContains("per_recus", p1).get();

        return r2;
    }

    public static Task<QuerySnapshot> GetMessages1(Utilisateur p,Utilisateur p2) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        p1.put("mot_de_passe", p.getMot_de_passe());
        p1.put("email",p.getEmail());
        p1.put("num_telephone",p.getNum_telephone());
        p1.put("type",p.getType());






        Map<String, Object> p21 = new HashMap<>();
        p21.put("nom", p2.getNom());
        p21.put("mot_de_passe", p2.getMot_de_passe());
        p21.put("email",p2.getEmail());
        p21.put("num_telephone",p2.getNum_telephone());
        p21.put("type",p2.getType());





        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).whereArrayContains("per_recus",p21).get();


        return r1;
    }

    public static Task<QuerySnapshot> Getwhenobjattr(Utilisateur p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());



        Task<QuerySnapshot> r2 = getUsersCollection().whereArrayContains("per_recus", p1).get();

        return r2;
    }

    public static Task<QuerySnapshot> GetMessagesrecus(Utilisateur p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("Nom", p.getNom());


        return getUsersCollection().whereArrayContains("Per_recus",p1).get();





    }

    public static Task<QuerySnapshot> GetMessagesdiscution(Utilisateur p,Utilisateur p2) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("Nom", p.getNom());


        Map<String, Object> p21 = new HashMap<>();
        p21.put("Nom", p2.getNom());


        return getUsersCollection().whereEqualTo("Per_envoye",p1).whereArrayContains("Per_recus",p21).get();





    }


}
