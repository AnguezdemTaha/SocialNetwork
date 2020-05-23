package com.example.prj_s4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prj_s4.Model.Chatbot;
import com.example.prj_s4.Model.Etude;
import com.example.prj_s4.Model.Experience;
import com.example.prj_s4.Model.Message;
import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Personne;
import com.example.prj_s4.Model.Projet;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Services.Methodes_msg_evt_;
import com.example.prj_s4.Services.Methodes_personne;
import com.example.prj_s4.Services.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class AddMessage extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private LinearLayout t21;
    private TextView t211, t212, msg;
    public RecyclerView r;
    private Object LayoutManager;
    private ImageView envoyer_msg;


    private Utilisateur p = new Utilisateur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0EF1EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un message");

        msg = (TextView) findViewById(R.id.msg);
        envoyer_msg = (ImageView) findViewById(R.id.envoyermsg2);

        r = (RecyclerView) findViewById(R.id.listdespersonnesmsg);

        final LinkedList<Utilisateur> prs = new LinkedList<Utilisateur>();
        final Context context = this;


        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        //final String[] msguser = new String[1];

        /*Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p2 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p3 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");

        prs.add(p1);prs.add(p2);prs.add(p3);
        r.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
        MyAdapter myAdapter =new MyAdapter(prs,this);
        r.setAdapter(myAdapter);*/

        final MyAdapter.OnNoteListener note = (MyAdapter.OnNoteListener) this;
        Methodes_personne.GetAllUsers().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        p = document.toObject(Utilisateur.class);
                        prs.add(p);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(prs, context, note);
                    r.setAdapter(myAdapter);


                    //del=(ImageView) findViewById(R.id.delet_personne);
                    //del.setVisibility(View.INVISIBLE);


                } else {

                }
            }


        });

        envoyer_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("test", MODE_PRIVATE);
                ArrayList<Utilisateur> pers = new ArrayList<>();
                //5 nbr users in list checked
                /*for (int i = 0; i < pref.getAll().size(); i++) {
                    String nom = "nom" + i;
                    nom = pref.getString("nom" + i, String.valueOf(false));
                    String email=pref.getString("email" + i, String.valueOf(false));
                    String tele=pref.getString("tele" + i, String.valueOf(false));
                    String mot_de_pass=pref.getString("mot_de_pass" + i, String.valueOf(false));
                    String type=pref.getString("type" + i, String.valueOf(false));
                    Personne p=new Personne(nom,mot_de_pass,email,tele,type);

                    System.out.println("les utilisateur de la list :" + nom+mot_de_pass);
                    pers.add(p);

                }*/
                Gson gson = new Gson();
                String json = pref.getString("ok", "");
                //!!!! to get type(class) of list personne
                Type type = new TypeToken<ArrayList<Utilisateur>>() {
                }.getType();
                ArrayList<Utilisateur> obj = gson.fromJson(json, type);
                for (Utilisateur p : obj) {
                    System.out.println("les utilisateur de la list :" + p.getEmail());
                }


                context.getSharedPreferences("test", 0).edit().clear().commit();


                String contenu = String.valueOf(msg.getText());
                ArrayList<Page> pages = new ArrayList<>();


                Utilisateur p2 = new Utilisateur("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");
                SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson1 = new Gson();
                String json1 = pref1.getString("personne_c", "");
                final Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);
                //Professeur p1 = new Professeur("tarik", "rachid", "tarik@gcom", "0606466", "Prof");

                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";
                final Date currentTime = Calendar.getInstance().getTime();

                ArrayList<Utilisateur> ps = new ArrayList<>();
                //ps.add(p2);
                for (Utilisateur p : obj) {
                    ps.add(p);
                }

                Date date_msg = null;
                for (final Utilisateur p3 : ps) {
                    final ArrayList<Utilisateur> p4 = new ArrayList<>();
                    p4.add(p3);
                    final Message m = new Message(contenu, currentTime, p1, p4);
                    Methodes_msg_evt_.creatMessage(m);
                    if (p3.getNom().equals("chatbot")) {
                        Methodes_msg_evt_.creatMessagebot(m.getContenu());
                        //System.out.println("msg bot ajouter ::::");
                        //final ArrayList<String> msgsbot=new ArrayList<>();
                        //final ArrayList<Chatbot> msgsbot=new ArrayList<>();
                        final ArrayList<String> msgsbot = new ArrayList<>();
                        Methodes_msg_evt_.GetAllMessagebot().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        //String msgbot = document.toString();
                                        Chatbot msgbot = document.toObject(Chatbot.class);
                                        System.out.println("c'est les message bot :"+msgbot.getMsg_contenu());
                                        msgsbot.add(msgbot.getMsg_contenu());
                                    }
                                    Date currentTime = Calendar.getInstance().getTime();
                                    Random rand = new Random();
                                    //int i=rand.nextInt(msgsbot.size());
                                    //String contenu2 =msgsbot.get(rand.nextInt(msgsbot.size())).toString();
                                    String contenu1 = msgsbot.get(rand.nextInt(msgsbot.size()));
                                    ArrayList<Utilisateur> p5 = new ArrayList<>();
                                    p5.add(p1);
                                    ArrayList<Utilisateur> p4 = new ArrayList<>();
                                    p4.add(p3);
                                    Message m1 = new Message(contenu1, currentTime, p4.get(0), p5);
                                    Methodes_msg_evt_.creatMessage(m1);
                                }
                            }
                        });



                    }
                }

                Toast.makeText(getApplicationContext(), "Votre message a été ajouter avec succe", Toast.LENGTH_LONG).show();
                Intent in = new Intent(AddMessage.this, ListMessage.class);
                startActivity(in);

            }
        });
        // Methodes_personne.GetAllUsers(new GetAllContactsOnCompleteListener());



        /*ArrayList<Personne> ps=new ArrayList<>();
        ps=Methodes_personne.getallUsers1();
        for(Personne p:ps){
            System.out.println("le nom ="+p.getNom());
        }*/


        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        /*Methodes_personne.GetAllUsers().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        nom[0] =p.getNom();
                        //mytext7.setText(nom[0]);

                        nomuser[0] =p.getNom();
                        //msguser[0] =m.getContenu_msg();



                        t21 = (LinearLayout) findViewById(R.id.listdespersonnesmsg);
                        LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                        View v = v2.inflate(R.layout.unepersonnemsg, null, false);
                        t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        t211 = (TextView) findViewById(R.id.nompersonnemsg);
                        t211.setText(nomuser[0]);
                        //t212 = (TextView) findViewById(R.id.Msguser);
                        //t212.setText(msguser[0]);

                        //System.out.println("here is :"+mytext7.getText().toString());
                    }

                } else {

                }
            }

       });*/

    }

    public void OnNoteClick(int position) {

    }
}
