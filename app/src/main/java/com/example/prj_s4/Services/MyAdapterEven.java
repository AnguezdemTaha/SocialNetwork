package com.example.prj_s4.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prj_s4.ConsulterPage;
import com.example.prj_s4.Model.Post;
import com.example.prj_s4.Model.Reaction;
import com.example.prj_s4.Model.Reaction_page;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import static android.content.Context.MODE_PRIVATE;


public class MyAdapterEven extends RecyclerView.Adapter<MyAdapterEven.MyViewHolder> {
    private LinkedList<Post> posts;
    private Context context;
    private TextView nom;
    private ImageView i1;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    //private MyAdapterEven.OnNoteListener mOnNoteListener ;

    public MyAdapterEven(LinkedList<Post> posts, Context context) {
        this.posts = new LinkedList<Post>();

        //????
        this.posts.addAll(posts);
        this.context = context;
        //this.mOnNoteListener=onNoteListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.unevent, parent, false);

        MyAdapterEven.MyViewHolder vh = new MyAdapterEven.MyViewHolder(itemLayoutView);
        return vh;
    }


    @Override
    public void onBindViewHolder(final MyAdapterEven.MyViewHolder holder, int position) {

        holder.nom_e.setText(posts.get(position).getContenu().getText());
        holder.nom_e2.setText(posts.get(position).getPage().getNom());
        //holder.lovereact.setColorFilter(Color.GREEN);

        String description1 = posts.get(position).getContenu().getImages().get(0);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference pathReference = storageReference.child("/events/" + description1);
        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final File finalLocalFile = localFile;
        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                holder.i1.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)

        /*String contenupost = holder.nom_e.getText().toString();

        SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson1 = new Gson();
        String json1 = pref1.getString("personne_c", "");
        Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

        String typereact = "Love";
        String contenu = "Love";

        Reaction reaction = new Reaction(typereact, contenu, contenu, p1);
        Methodes_event.Getreactionbycontenu(reaction.getContenu()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        holder.nom_e.setTextColor(Color.parseColor("#123456"));

                    }
                }
                else{
                    System.out.println("il n'est pas de reaction avec ce contenu xd");
                }
            }
        });*/
        String contenupost = holder.nom_e.getText().toString();

        SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson1 = new Gson();
        String json1 = pref1.getString("personne_c", "");
        Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

        String typereact = "Love";
        String contenu = "Love";

        final Reaction reaction = new Reaction(typereact, contenu, contenupost, p1);
        Methodes_event.Getreactionbycontenu(reaction).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    System.out.println("wtwftwftwftwftwft");
                    if (task.getResult().size() == 0) {
                        holder.lovereact.setColorFilter(Color.parseColor("#FFF8E8"));

                    } else {
                        //colorer love react
                        //holder.lovereact.setBackgroundColor(Color.parseColor("021548"));
                        holder.lovereact.setColorFilter(Color.parseColor("#D61F1F"));
                    }
                }

            }
        });

        holder.lovereact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contenupost = holder.nom_e.getText().toString();

                SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson1 = new Gson();
                String json1 = pref1.getString("personne_c", "");
                Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

                String typereact = "Love";
                String contenu = "Love";

                final Reaction reaction = new Reaction(typereact, contenu, contenupost, p1);

                System.out.println("working fine butt :::");
                Methodes_event.Getreactionbycontenu(reaction).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //Personne p = new Personne();
                        if (task.isSuccessful()) {
                            //System.out.println("wtwftwftwftwftwft");
                            if (task.getResult().size() == 0) {
                                Methodes_event.creatreactionpage(reaction);
                                System.out.println("no reaction selected n'exist pas");
                                holder.lovereact.setColorFilter(Color.parseColor("#D61F1F"));
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    System.out.println("reaction deja exist soo");
                                    Methodes_event.deletereactionpage(reaction).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            //Personne p = new Personne();
                                            if (task.isSuccessful()) {

                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String idd = document.getId();
                                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                    db.collection("Reaction").document(idd).delete();
                                                    holder.lovereact.setColorFilter(Color.parseColor("#FFF8E8"));
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nom_e, nom_e2, description;
        public ImageView delete, i1, lovereact;
        //public final ImageView i1;
        public ImageView edit;

        public Context context;
        com.example.prj_s4.Services.MyAdapter.OnNoteListener onNoteListener;


        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.context = context;
            nom_e = itemLayoutView.findViewById(R.id.even_nom);
            nom_e2 = itemLayoutView.findViewById(R.id.even_nom2);
            lovereact = itemLayoutView.findViewById(R.id.lovereact);
            //description=itemLayoutView.findViewById(R.id.);
            i1 = itemLayoutView.findViewById(R.id.imageevent1);
            //delete=itemLayoutView.findViewById(R.id.delet_even);
            //edit=itemLayoutView.findViewById(R.id.edit_even);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            nom_e2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), ConsulterPage.class);

                    String m2 = nom_e2.getText().toString();
                    in.putExtra("nom_page", m2);
                    //context.getApplicationContext().getSharedPreferences("personne_recu", 0).edit().clear().commit();
                    v.getContext().startActivity(in);

                }
            });
            lovereact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //lovereact.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                    //a faire mthode.if reaction exist ...[pour love ]
                    /*String contenupost = nom_e.getText().toString();

                    SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                    Gson gson1 = new Gson();
                    String json1 = pref1.getString("personne_c", "");
                    Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

                    String typereact = "Love";
                    String contenu = "Love";

                    Reaction reaction = new Reaction(typereact, contenupost, contenu, p1);


                    Methodes_event.creatreactionpage(reaction);*/
                    //else
        /*String contenupost = nom_e.getText().toString();

        SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson1 = new Gson();
        String json1 = pref1.getString("personne_c", "");
        Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

        String typereact = "Love";
        String contenu = "Love";

        Reaction reaction = new Reaction(typereact, contenu, contenu, p1);
        Methodes_event.deletereactionpage(reaction).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String idd = document.getId();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("Reaction").document(idd).delete();
                    }
                }
                else{
                    System.out.println("il n'est pas de reaction avec ce contenu xd");
                }
            }
        });

                    //if (lovereact.getColorFilter().equals(Color.GREEN)) {
                        //lovereact.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);

                        //System.out.println("i am here bbbbb :");
                        /*abonne.setBackgroundColor(Color.parseColor("#00000000"));
                        abonne.setTextColor(Color.parseColor("#000000"));
                        abonne.setText("Non Abonne ");
                        String nompage = nompage1.getText().toString();

                        SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
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
                        });*/
                    //}
                    //
                    /*else {
                        System.out.println("notnotnotnotntontont");
                    }*/
                }
            });
            //onNoteListener.OnNoteClick(getAdapterPosition());

            /*edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(), Editevent.class);
                    String m=nom_e.getText().toString();
                    in.putExtra("nom_even", m);

                    Toast.makeText(v.getContext().getApplicationContext(),"L'even :"+m,Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(in);
                }});*/
            /*delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String nom11=nom_e.getText().toString();
                    Methodes_event.deleteEven(nom11).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //Personne p = new Personne();
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String idd= document.getId();
                                    Methodes_event.getUsersCollection().document(idd).delete();

                                    Intent in=new Intent(v.getContext(), Listevent.class);
                                    v.getContext().startActivity(in);

                                    Toast.makeText(v.getContext().getApplicationContext(),"Votre modification a été enregistré avec succe :",Toast.LENGTH_SHORT).show();
                                    //p = document.toObject(Personne.class);
                                    //ps.add(p);
                                    //System.out.println("le nom ="+p.getNom());
                                }
                            }
                        }
                    });
                }});*/
        /*int itemPosition = RecyclerView.getChildLayoutPosition(v);
        String item = (String) List.get(itemPosition);
        Toast.makeText(context, item, Toast.LENGTH_LONG).show();*/

        }
    }
    /*public interface OnNoteListener {
        //pour detecter click et position
        void OnNoteClick(int position);
    }*/
}
