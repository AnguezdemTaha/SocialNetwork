package com.example.prj_s4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Personne;
import com.example.prj_s4.Model.Reaction_page;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Services.Methodes_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

public class ConsulterPage extends AppCompatActivity {

    TextView nompage1, nompage2, nomowner, typepage, telephone, email;
    Button moreinfos, abonne, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_page);

        final ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0EF1EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        //actionBar.setTitle("MY_DRAW");

        //nompage1 = (TextView) findViewById(R.id.nompage11);
        nompage2 = (TextView) findViewById(R.id.nompage2);
        nomowner = (TextView) findViewById(R.id.nomownerpage2);
        typepage = (TextView) findViewById(R.id.typepage2);
        telephone = (TextView) findViewById(R.id.typepage4);
        email = (TextView) findViewById(R.id.pagetype5);

        moreinfos = (Button) findViewById(R.id.voirpostpage);
        abonne = (Button) findViewById(R.id.abonnepage2);
        message = (Button) findViewById(R.id.envoyermsgpage);

        /*SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson1 = new Gson();
        String json1 = pref1.getString("personne_c", "");
        Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);*/

        //Utilisateur p1 = new Utilisateur("t", "t", "t", "2", "Etudiant");

        Bundle extras = getIntent().getExtras();
        final String nompage = extras.getString("nom_page");
        //final String nompage = "t";

        Methodes_page.Getpagebynom(nompage).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String Type = null;
                    String nomuser = null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Page page = document.toObject(Page.class);
                        //nompage1.setText(page.getNom());
                        actionBar.setTitle(page.getNom());

                        nompage2.setText("Nom    :" + page.getNom());
                        nomowner.setText("Owner :" + page.getOwner().getNom());
                        typepage.setText("Type    :" + page.getType());
                        telephone.setText("Telephone:" + page.getOwner().getNum_telephone());
                        email.setText("Email   :" + page.getOwner().getEmail());
                    }
                }
            }
        });


        System.out.println("e nom de la page incoractement :" + nompage);
        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson1 = new Gson();
        String json1 = pref1.getString("personne_c", "");
        Utilisateur p1x = gson1.fromJson(json1, Utilisateur.class);

        String typereact = "Abonne";
        String contenu = "Abonne";

        Reaction_page reaction_page = new Reaction_page(typereact, contenu, nompage, p1x);
        Methodes_page.deletereactionpage(reaction_page).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {

                    if (task.getResult().size() == 0) {
                        //pas colore love react
                        System.out.println("not notntontontont");
                        abonne.setBackgroundColor(Color.parseColor("#959393"));
                        abonne.setTextColor(Color.parseColor("#000000"));
                    } else {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            abonne.setBackgroundColor(Color.parseColor("#95e764"));
                            abonne.setTextColor(Color.parseColor("#3026ba"));
                            System.out.println("yesyeyyyeysyeysye");
                        }
                    }

                }
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent in = new Intent(ConsulterPage.this, Discussion.class);
                //String m = message.getText().toString();
                //System.out.println("les message c'est "+m);
                in.putExtra("nom_per_non_connecte", nompage);
                v.getContext().startActivity(in);

            }
        });
        moreinfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent in = new Intent(ConsulterPage.this, ListPost.class);
                //String m = message.getText().toString();
                //System.out.println("les message c'est "+m);
                in.putExtra("pageavoir", nompage);
                v.getContext().startActivity(in);

            }
        });

        abonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (abonne.getCurrentTextColor() == (Color.parseColor("#000000"))) {
                    System.out.println("text color was black right");
                    //abonne.setBackgroundColor(Color.GREEN);
                    //abonne.setTextColor(Color.RED);
                    abonne.setBackgroundColor(Color.parseColor("#95e764"));
                    abonne.setTextColor(Color.parseColor("#3026ba"));

                    abonne.setText("Abonne ");

                    String nompage = actionBar.getTitle().toString();

                    SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                    Gson gson1 = new Gson();
                    String json1 = pref1.getString("personne_c", "");
                    Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

                    String typereact = "Abonne";
                    String contenu = "Abonne";

                    Reaction_page reaction_page = new Reaction_page(typereact, contenu, nompage, p1);
                    Methodes_page.creatreactionpage(reaction_page);
                    /*Methodes_page.Getpagebynom(nompage).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                String Type = null;
                                String nomuser = null;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Page page = document.toObject(Page.class);
                                    SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                                    Gson gson1 = new Gson();
                                    String json1 = pref1.getString("personne_c", "");
                                    Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);
                                    String typereact = "Abonne";
                                    String contenu ="Abonne";

                                    Reaction_page reaction_page=new Reaction_page(typereact,contenu,page,p1);

                                }
                            }
                        }
                    });*/


                } else {
                    //if (abonne.getCurrentTextColor() == Color.RED) {
                    abonne.setBackgroundColor(Color.parseColor("#959393"));
                    abonne.setTextColor(Color.parseColor("#000000"));
                    abonne.setText("Non Abonne ");
                    String nompage = actionBar.getTitle().toString();

                    SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                    Gson gson1 = new Gson();
                    String json1 = pref1.getString("personne_c", "");
                    Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

                    String typereact = "Abonne";
                    String contenu = "Abonne";

                    Reaction_page reaction_page = new Reaction_page(typereact, contenu, nompage, p1);
                    Methodes_page.deletereactionpage(reaction_page).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //Personne p = new Personne();
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String idd = document.getId();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("Reaction_page").document(idd).delete();
                                }
                            }
                        }
                    });

                }

                //abonne.setBackgroundColor(Color.GREEN);
            }
        });
    }
}
