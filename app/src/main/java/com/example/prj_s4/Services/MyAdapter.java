package com.example.prj_s4.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.prj_s4.AddMessage;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LinkedList<Utilisateur> ps;
    private Context context;
    private TextView nom;
    SharedPreferences pref;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private OnNoteListener mOnNoteListener;
    ArrayList<Utilisateur> arrayListUser = new ArrayList<>();

    public MyAdapter(LinkedList<Utilisateur> ps, Context context, OnNoteListener onNoteListener) {

        this.ps = new LinkedList<Utilisateur>();

        //????
        this.ps.addAll(ps);
        this.context = context;
        this.mOnNoteListener = onNoteListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.unepersonne, parent, false);
        MyViewHolder vh = new MyViewHolder(itemLayoutView, mOnNoteListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.nom_e.setText(ps.get(position).getNom());
        String type = ps.get(position).getType();
        if (context instanceof AddMessage) {
            holder.delete.setVisibility(View.INVISIBLE);
            holder.edit.setVisibility(View.INVISIBLE);
        }
        /*if ((context instanceof Listprof) || (context instanceof Listetudiant)) {
            holder.choisir_personne.setVisibility(View.INVISIBLE);
        }*/


        holder.choisir_personne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                boolean isChecked = holder.choisir_personne.isChecked();
                //pourqoiu le declerer il peut etre null here
                pref = context.getApplicationContext().getSharedPreferences("test", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                Gson gson = new Gson();

                //for (int i = 0; i < ps.size(); i++) {
                if (isChecked) {
                    if (!arrayListUser.contains(ps.get(position).getNom()))
                        arrayListUser.add(ps.get(position));

                    //arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
                } else {
                    arrayListUser.remove(ps.get(position));
                    //arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
                }
                //}

                String json = gson.toJson(arrayListUser);
                editor.putString("ok", json);
                //in session all users names checked
                /*for (int i = 0; i < arrayListUser.size(); i++) {
                    editor.putString("nom" + i, arrayListUser.get(i).getNom());
                     editor.putString("email" + i, arrayListUser.get(i).getEmail());
                    editor.putString("mot_de_passe" + i, arrayListUser.get(i).getMot_de_passe());
                    editor.putString("type" + i, arrayListUser.get(i).getType());
                    editor.putString("tele" + i, arrayListUser.get(i).getNum_telephone());


                    System.out.println("la list maintenent est :"+1 + arrayListUser.get(i).getNom());


                }*/
                editor.commit();
            }
        });

        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount() {
        return ps.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nom_e;
        public ImageView delete;
        public ImageView edit;
        public CheckBox choisir_personne;

        public Context context;
        OnNoteListener onNoteListener;


        public MyViewHolder(View itemLayoutView, OnNoteListener onNoteListener) {
            super(itemLayoutView);
            this.context = context;
            nom_e = itemLayoutView.findViewById(R.id.nompersonne);
            delete = itemLayoutView.findViewById(R.id.delet_personne);
            edit = itemLayoutView.findViewById(R.id.edit_personne);
            choisir_personne = itemLayoutView.findViewById(R.id.choisirpersonne);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(getAdapterPosition());

            /*edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent in = new Intent(v.getContext(), Editprof.class);
                    String m = nom_e.getText().toString();
                    in.putExtra("nom_prof_etudiant", m);

                    Toast.makeText(v.getContext().getApplicationContext(), "Le professeur :" + m, Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(in);

                }
            });*/
            /*delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String nom11 = nom_e.getText().toString();
                    Methodes_personne.deleteUser(nom11).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //Personne p = new Personne();
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String idd = document.getId();
                                    Methodes_personne.getUsersCollection().document(idd).delete();

                                    Intent in = new Intent(v.getContext(), Listprof.class);
                                    v.getContext().startActivity(in);

                                    Toast.makeText(v.getContext().getApplicationContext(), "Votre modification a été enregistré avec succe :", Toast.LENGTH_SHORT).show();
                                    //p = document.toObject(Personne.class);
                                    //ps.add(p);
                                    //System.out.println("le nom ="+p.getNom());
                                }
                            }
                        }
                    });
                }
            });*/
            /*choisir_personne.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }


            });*/
            choisir_personne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



            /*int itemPosition = RecyclerView.getChildLayoutPosition(v);
            String item = (String) List.get(itemPosition);
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();*/

        }
    }

    public interface OnNoteListener {
        //pour detecter click et position
        void OnNoteClick(int position);
    }
}
