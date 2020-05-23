package com.example.prj_s4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prj_s4.Model.Etude;
import com.example.prj_s4.Model.Experience;
import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Post;
import com.example.prj_s4.Model.Projet;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Services.Methodes_page;
import com.example.prj_s4.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private TextView text, text2, text3;
    private EditText nom1, email1, tele1, pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        text = (TextView) findViewById(R.id.Login1);
        text2 = (TextView) findViewById(R.id.signup);
        text3 = (TextView) findViewById(R.id.signup1);

        nom1 = (EditText) findViewById(R.id.username);
        email1 = (EditText) findViewById(R.id.mail);
        tele1 = (EditText) findViewById(R.id.phone);
        pass1 = (EditText) findViewById(R.id.password);


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nom = String.valueOf(nom1.getText());
                String email = String.valueOf(email1.getText());
                String tele = String.valueOf(tele1.getText());
                String pass = String.valueOf(pass1.getText());
                String type = "Etudiant";


                Utilisateur p = new Utilisateur(nom, pass, email, tele, type);
                Date currentTime = Calendar.getInstance().getTime();

                Page page = new Page(nom, currentTime, "Personnel", p);
                //Utilisateur p=new Utilisateur(nom,pass,email,tele,type);
                Methodes_personne.createUser(p);
                Methodes_page.creatpage(page);
                Toast.makeText(getApplicationContext(), "Votre inscription a été accpeter avec succe", Toast.LENGTH_LONG).show();

                Intent in = new Intent(Signup.this, Signin.class);
                startActivity(in);
            }
        });
        /*text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                String Nom="test1";
                String Email="test1@g.com";
                String Num_telephone="0654575847";
                String Mot_de_passe="test1";
                //Methodes_personne.createUser(var);
                Personne test1 = new Personne(Nom,Email,Num_telephone,Mot_de_passe);
                db1.collection("Personne").add(test1);
                Intent in=new Intent(signup.this,Login.class);
                startActivity(in);
            }
        });*/


    }
}
