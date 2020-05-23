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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prj_s4.Model.Etude;
import com.example.prj_s4.Model.Experience;
import com.example.prj_s4.Model.Message;
import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Projet;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Services.Methodes_msg_evt_;
import com.example.prj_s4.Services.MyAdapterMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ListMessage extends AppCompatActivity {

    Menu menuitem;
    static ArrayList<Message> messages1 = new ArrayList<>();

    private LinearLayout l1, l2, t21;
    private ImageView imageadd;
    private TextView t1, mytext7, t211, t212;
    private ListView t2, t3;
    public RecyclerView r;
    final ArrayList<Message> messages = new ArrayList<>();
    private Object LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_message);



        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0EF1EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Les Messages");



        /*SharedPreferences pref4 = getApplicationContext().getSharedPreferences("degree", MODE_PRIVATE);
        Gson gson4 = new Gson();
        String json4 = pref4.getString("degre", "0");
        String degre = gson4.fromJson(json4, String.class);*/


        //l1=(LinearLayout) findViewById(R.id.User1);
        //l2=(LinearLayout) findViewById(R.id.User2);
        //imageadd = (ImageView) findViewById(R.id.Add_msg);
        t1 = (TextView) findViewById(R.id.Msguser);
        //mytext7 = (TextView) findViewById(R.id.nom8);

        //t2 = (ListView) findViewById(R.id.allmessages);
        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        //Message m=new Message();
        //m.setPer_envoye(p1);

        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        final String[] msguser = new String[1];

        r = (RecyclerView) findViewById(R.id.listdesmessages);
        final LinkedList<Message> msgs = new LinkedList<Message>();
        final ArrayList<String> persmsgs1 = new ArrayList<String>();

        final Context context = this;

        /*Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Methodes_msg_evt_.GetMessagesrecu(p1).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message m = document.toObject(Message.class);

                        msgs.add(m);

                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterMessage myAdapter = new MyAdapterMessage(msgs, context);
                    r.setAdapter(myAdapter);

                } else {

                }
            }



        });*/
        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Utilisateur p1 = gson.fromJson(json, Utilisateur.class);
        //final Personne p1 = new Personne("ahmed", "ahmed", "ahmed@gcom", "060666", "Prof");
        persmsgs1.add(p1.getNom());
        Task task1 = Methodes_msg_evt_.GetMessages(p1);
        Task task2 = Methodes_msg_evt_.GetMessagesrecu(p1);
        Task<List<QuerySnapshot>> alltask = Tasks.whenAllSuccess(task1, task2);
        alltask.addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
            @Override
            public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
                if (task.isSuccessful()) {

                    /*Message msg1=new Message();
                    msg1.setPer_envoye(p1); //not existed one
                    msgs.add(msg1);*/
                    for (QuerySnapshot document : task.getResult()) {
                        for (QueryDocumentSnapshot document1 : document) {
                            Message m = document1.toObject(Message.class);

                            System.out.println("you are here but ::");
                            if (!persmsgs1.contains(m.getPer_envoye().getNom())) {
                                persmsgs1.add(m.getPer_envoye().getNom());

                                msgs.add(m);
                            }


                            for (int i = 0; i < m.getPer_recus().size(); i++) {
                                if (!persmsgs1.contains(m.getPer_recus().get(i).getNom())) {
                                    persmsgs1.add(m.getPer_recus().get(i).getNom());
                                    msgs.add(m);
                                    System.out.println("Probporoprroporor");

                                }
                            }


                        }


                    }
                    //il faut classer les msgs
                    /*int i=0;
                    for (Message m2 : msgs) {
                        for (Message m1 : msgs) {
                            if (m2.getPer_envoye().equals(m1.getPer_envoye())) {
                                i+=1;
                            }
                        }
                        if(i>1){
                            msgs.remove(m2);
                        }
                    }*/
                    Collections.sort(msgs, new Comparator<Message>() {
                        @Override
                        public int compare(Message o1, Message o2) {
                            return o1.getPer_envoye().getNom().compareTo(o2.getPer_envoye().getNom());
                        }


                    });
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterMessage myAdapter = new MyAdapterMessage(msgs, context);
                    r.setAdapter(myAdapter);
                } else {

                }
            }
        });


        /*System.out.println("here is :"+ nom[0]);

        Message m1=new Message();
        m1.setContenu_msg("msg here");
        m1.setPer_envoye(p1);
        messages.add(m1);
        for (int i = 0; i <messages.size() ; i++) {
            System.out.println("èçèç(çè"+messages.get(i).getContenu_msg());
        }*/


        /*ArrayAdapter<Message> arrayAdapter
                = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1 , messages);
        t2.setAdapter(arrayAdapter);*/

        //ArrayList<Message> MS= Methodes_msg_evt_.GetMessages();
        /*Methodes_msg_evt_.GetMessages("ok").addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message p = document.toObject(Message.class);
                        String nom=p.getContenu_msg();
                        //t1=(TextView)findViewById(R.id.Msg1);

                        //t1.setText(nom);
                    }
                } else {

                }
            }});*/






       /* l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this, Discussion.class);
                startActivity(in);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this, Discussion.class);
                startActivity(in);
            }
        });*/
       /* imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(ListMessage.this, AddMessage.class);
                startActivity(in);
            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu, menu);
        menuitem=menu;
        MenuItem itm1 = menuitem.findItem(R.id.item1);
        MenuItem itm2 = menuitem.findItem(R.id.item2);
        MenuItem itm3 = menuitem.findItem(R.id.item3);
        MenuItem itm4 = menuitem.findItem(R.id.item4);

        itm1.setVisible(false);
        itm2.setVisible(false);
        itm3.setVisible(false);
        //itm1.setVisible(false);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item4:
                //a ajouter que page = ma page same my name !!!
                Intent in = new Intent(ListMessage.this, AddMessage.class);
                startActivity(in);
                break;
            case R.id.item5:
                //a ajouter que page = ma page same my name !!!
                Intent in5 = new Intent(ListMessage.this, Signin.class);
                startActivity(in5);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
