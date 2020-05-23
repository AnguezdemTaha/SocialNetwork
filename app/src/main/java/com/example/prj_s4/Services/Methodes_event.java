package com.example.prj_s4.Services;


import com.example.prj_s4.Model.Post;
import com.example.prj_s4.Model.Reaction;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Model.Utilisateur_Post;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Methodes_event {
    public static CollectionReference getUsersCollection() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Post");
    }

    public static Task<QuerySnapshot> GetAllevents() {


        return getUsersCollection().get();

    }

    /*public static Task<QuerySnapshot> GetAlleventsforuserdegre(Utilisateur u) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", u.getNom());
        p1.put("mot_de_passe", u.getMot_de_passe());
        p1.put("email", u.getEmail());
        p1.put("num_telephone", u.getNum_telephone());
        p1.put("type", u.getType());

        return db.collection("Utilisateur_post").whereEqualTo("utilisateur",p1).orderBy("degre").get();

    }*/

    public static Task<DocumentReference> creatEvent(Post p) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(p);

    }

    public static Task<DocumentReference> creatutilisateurpost(Utilisateur_Post p) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("Utilisateur_post").add(p);

    }

    public static Task<QuerySnapshot> getutilisateurpost(Utilisateur u, String p) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", u.getNom());
        p1.put("mot_de_passe", u.getMot_de_passe());
        p1.put("email", u.getEmail());
        p1.put("num_telephone", u.getNum_telephone());
        p1.put("type", u.getType());




        return db.collection("Utilisateur_post").whereEqualTo("utilisateur",p1).whereEqualTo("post",p).get();

    }

    public static Task<QuerySnapshot> getutilisateurallpost(Utilisateur u) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", u.getNom());
        p1.put("mot_de_passe", u.getMot_de_passe());
        p1.put("email", u.getEmail());
        p1.put("num_telephone", u.getNum_telephone());
        p1.put("type", u.getType());




        return db.collection("Utilisateur_post").whereEqualTo("utilisateur",p1).get();

    }

    public static Task<DocumentReference> creatreactionpage(Reaction p) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("Reaction").add(p);
    }

    public static Task<QuerySnapshot> deletereactionpage(Reaction p) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getU().getNom());
        p1.put("mot_de_passe", p.getU().getMot_de_passe());
        p1.put("email", p.getU().getEmail());
        p1.put("num_telephone", p.getU().getNum_telephone());
        p1.put("type", p.getU().getType());


        return db.collection("Reaction").whereEqualTo("type", p.getType()).whereEqualTo("contenu", p.getContenu()).whereEqualTo("u", p1).whereEqualTo("p", p.getP()).get();
    }

    public static Task<QuerySnapshot> Getreactionbycontenu(Reaction p) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getU().getNom());
        p1.put("mot_de_passe", p.getU().getMot_de_passe());
        p1.put("email", p.getU().getEmail());
        p1.put("num_telephone", p.getU().getNum_telephone());
        p1.put("type", p.getU().getType());
        return db.collection("Reaction").whereEqualTo("p", p.getP()).whereEqualTo("contenu",p.getContenu()).whereEqualTo("type",p.getType()).whereEqualTo("u",p1).get();

    }


    public static Task<QuerySnapshot> GetAllEvens() {


        return getUsersCollection().get();

    }

    public static Task<QuerySnapshot> deleteEven(String nom1) {
        return Methodes_event.getUsersCollection().whereEqualTo("nom_event", nom1).get();
    }

    public static Task<QuerySnapshot> Geteventbynom(String nom) {


        return getUsersCollection().whereEqualTo("Nom_event", nom).get();

    }
}
