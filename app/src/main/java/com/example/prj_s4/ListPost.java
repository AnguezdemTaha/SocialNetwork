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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Post;
import com.example.prj_s4.Model.Reaction_page;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Model.Utilisateur_Post;
import com.example.prj_s4.Services.Methodes_event;
import com.example.prj_s4.Services.Methodes_page;
import com.example.prj_s4.Services.MyAdapterEven;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListPost extends AppCompatActivity {

    Menu menuitem;
    private ImageView text, text2, text3;
    //private LinearLayout t21;
    //private TextView t211,text4;
    private TextView mytext;
    private ImageView addprof;
    public RecyclerView r;
    private Post e = new Post();
    private Object LayoutManager;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Utilisateur> ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0EF1EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("MY_DRAW");


        r = (RecyclerView) findViewById(R.id.listdesevents);


        //String t=pageavoir;

        Bundle extras = getIntent().getExtras();

        String pageavoir = extras.getString("pageavoir");


        final LinkedList<Post> posts = new LinkedList<Post>();
        //final  Post[] posts = new Post[0];
        final Context context = this;
        //final MyAdapter.OnNoteListener note = this;
        final String finalPageavoir = pageavoir;


        SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson3 = new Gson();
        String json3 = pref3.getString("personne_c", null);
        final Utilisateur pc = gson3.fromJson(json3, Utilisateur.class);


        final LinkedList<Utilisateur_Post> ups = new LinkedList<Utilisateur_Post>();

        Methodes_event.getutilisateurallpost(pc).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final Utilisateur_Post up = document.toObject(Utilisateur_Post.class);
                        ups.add(up);

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("utilisateur_post", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(ups);
                        editor.putString("ups", json);
                        editor.commit();
                    }
                }
            }
        });

        Methodes_event.GetAllevents().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final Post e = document.toObject(Post.class);

                        SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                        Gson gson3 = new Gson();
                        String json3 = pref3.getString("personne_c", null);
                        final Utilisateur pc = gson3.fromJson(json3, Utilisateur.class);

                        String nompage = e.getPage().getNom();



                        Reaction_page reaction_page = new Reaction_page("Abonne", "Abonne", nompage, pc);
                        Methodes_page.deletereactionpage(reaction_page).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int abonne = 0;
                                    final int degree;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        abonne = 1;

                                    }
                                    degree = 3 * abonne;

                                    SharedPreferences pref5 = getApplicationContext().getSharedPreferences("degree", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref5.edit();
                                    //editor.clear();
                                    //editor4.commit();

                                    //Gson gson = new Gson();
                                    //String json = gson.toJson(degree);
                                    editor.putString("degre", String.valueOf(degree));
                                    editor.commit();
                                    //
                                    if (degree == 3) {
                                        posts.addFirst(e);
                                        System.out.println("this is a 3 ::::");
                                    } else {
                                        posts.addLast(e);
                                        System.out.println("this is not a 3 :: : ");
                                    }
                                    //
                                    final Utilisateur_Post up = new Utilisateur_Post(e.getContenu().getText(), pc, degree);
                                    Methodes_event.getutilisateurpost(pc, e.getContenu().getText()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {

                                                if (task.getResult().size() == 0) {
                                                    Methodes_event.creatutilisateurpost(up);
                                                } else {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        String idd = document.getId();
                                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                        db.collection("Utilisateur_post").document(idd).update("degre", degree);
                                                    }
                                                }
                                            }
                                        }
                                    });

                                }
                            }
                        });

                        if (finalPageavoir.equals("tout")) {
                            /*SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                            Gson gson3 = new Gson();
                            String json3 = pref3.getString("personne_c", null);
                            final Utilisateur pc = gson3.fromJson(json3, Utilisateur.class);*/
                            SharedPreferences pref4 = getApplicationContext().getSharedPreferences("degree", MODE_PRIVATE);
                            SharedPreferences.Editor editor4 = pref4.edit();
                            //Gson gson4 = new Gson();
                            //String json4 = pref4.getString("degre", null);
                            String degre = pref4.getString("degre",null);
                            //String degre = pref4.getString("degre","0");


                            SharedPreferences pref8 = getApplicationContext().getSharedPreferences("utilisateur_post", MODE_PRIVATE);
                            Gson gson8 = new Gson();
                            String json8 = pref8.getString("ups", null);

                            //!!!! to get type(class) of list personne
                            Type type = new TypeToken<ArrayList<Utilisateur_Post>>() {
                            }.getType();

                            final ArrayList<Utilisateur_Post> ups = gson8.fromJson(json8, type);

                            String contenu3 =e.getContenu().getText();
                            //System.out.println("this is the real contenu :"+contenu3);

                            for(Utilisateur_Post up :ups){
                                //System.out.println("ok il a entrer here ::::::::: ");
                                //System.out.println(" this is the post from utilisateur post "+up.getPost() +".."+up.getDegre());
                                if(up.getPost().equals(e.getContenu().getText())){
                                    System.out.println("mais il n'a pas entrer here i think :::::");
                                    if(up.getDegre()==3){
                                        posts.addFirst(e);
                                        System.out.println("is this degre 3 is true lets see :" +degre);
                                    }
                                    else{
                                        posts.addLast(e);
                                        System.out.println("as i think no one is 3");
                                    }
                                }
                            }

                            /*if (Integer.parseInt(degre) == 3) {
                                posts.addFirst(e);
                                System.out.println("is this degre 3 is true lets see :" +degre);
                            } else {
                                posts.addLast(e);
                                System.out.println("as i think no one is 3");
                            }*/
                            //editor4.clear();
                            //editor4.commit();
                        } else {
                            if (e.getPage().getNom().equals(finalPageavoir)) {
                                posts.add(e);
                            }
                        }


                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(nome,n)
                    editor.commit();*/

                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterEven myAdapter = new MyAdapterEven(posts, context);
                    r.setAdapter(myAdapter);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });






        /*final Date[] date_event = new Date[1];
        final String[] description = new String[1];
        final int[] id = new int[1];
        final String[] nomevent = new String[1];*/
        //final String[] msguser = new String[1];

        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        /*Methodes_event.GetAllevents().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int i=0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Evenement e = document.toObject(Evenement.class);
                        //nom[0] =e.getNom_event();
                        //mytext7.setText(nom[0]);

                        nomevent[0] =e.getNom_event();
                        id[0] =e.getId_event();
                        //date_event[0] =e.getDate_event();
                        description[0] =e.getDescription_event();
                        //msguser[0] =m.getContenu_msg();



                        t21 = (LinearLayout) findViewById(R.id.listevent);
                        LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                        View v = v2.inflate(R.layout.unevent, null, false);
                        t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        t211 = (TextView) findViewById(R.id.nomevent);
                        t211.setText(nomevent[0]);
                        t211.setId(id[0]);
                        //t212 = (TextView) findViewById(R.id.Msguser);
                        //t212.setText(msguser[0]);


                        //System.out.println("here is :"+mytext7.getText().toString());
                        t211.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in=new Intent(Listevent.this, Editevent.class);
                                in.putExtra("id_event",id[0]);
                                in.putExtra("nom_event",nomevent[0]);
                                in.putExtra("discription_event",description[0]);
                                in.putExtra("date_event",date_event[0]);

                                startActivity(in);

                            }
                        });
                    }

                } else {

                }
            }


        });*/



        /*text=(ImageView) findViewById(R.id.evenvoir);
        text2=(ImageView) findViewById(R.id.evenmodifier);
        text3=(ImageView) findViewById(R.id.evenadd);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this, Editevent.class);
                startActivity(in);
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this, Editevent.class);
                startActivity(in);
            }
        });

         */
        text3 = (ImageView) findViewById(R.id.evenadd);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ListPost.this, AddPost.class);
                startActivity(in);
            }
        });
        text = (ImageView) findViewById(R.id.messages);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ListPost.this, ListMessage.class);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu, menu);
        menuitem = menu;
        MenuItem itm4 = menuitem.findItem(R.id.item4);

        itm4.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //a ajouter que page = ma page same my name !!!
                SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson3 = new Gson();
                String json3 = pref3.getString("personne_c", null);
                Utilisateur pc = gson3.fromJson(json3, Utilisateur.class);

                Intent in = new Intent(ListPost.this, ConsulterPage.class);
                in.putExtra("nom_page", pc.getNom());
                startActivity(in);
                break;
            /*case R.id.item1:
                Intent in = new Intent(ListPost.this, Listetudiant.class);
                startActivity(in);
                break;*/
            case R.id.item3:
                Intent in1 = new Intent(ListPost.this, AddPage.class);
                startActivity(in1);
                break;
            case R.id.item5:
                Intent in5 = new Intent(ListPost.this, Signin.class);
                startActivity(in5);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}