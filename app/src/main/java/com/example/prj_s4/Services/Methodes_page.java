package com.example.prj_s4.Services;

import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Post;
import com.example.prj_s4.Model.Reaction;
import com.example.prj_s4.Model.Reaction_page;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Methodes_page {
    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Page");
    }

    public static Task<QuerySnapshot> updatepage(String nom1) {

        return Methodes_page.getUsersCollection().whereEqualTo("nom",nom1).get();


    }
    public static Task<QuerySnapshot> GetAllevents() {


        return getUsersCollection().get();

    }

    public static Task<DocumentReference> creatpage(Page p) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(p);

    }
    public static Task<DocumentReference> creatreactionpage(Reaction_page p) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("Reaction_page").add(p);
    }
    public static Task<QuerySnapshot> deletereactionpage(Reaction_page p) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getU().getNom());
        p1.put("mot_de_passe", p.getU().getMot_de_passe());
        p1.put("email",p.getU().getEmail());
        p1.put("num_telephone",p.getU().getNum_telephone());
        p1.put("type",p.getU().getType());



        return db.collection("Reaction_page").whereEqualTo("type",p.getType()).whereEqualTo("contenu",p.getContenu()).whereEqualTo("u",p1).whereEqualTo("p",p.getP()).get();
    }

    public static Task<QuerySnapshot> GetAllEvens() {


        return getUsersCollection().get();

    }
    public static Task<QuerySnapshot> deleteEven(String nom1) {
        return Methodes_event.getUsersCollection().whereEqualTo("nom_event",nom1).get();
    }
    public static Task<QuerySnapshot> Getpagebynom(String nom) {


        return getUsersCollection().whereEqualTo("nom",nom).get();

    }
}
