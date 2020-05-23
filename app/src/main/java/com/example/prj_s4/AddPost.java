package com.example.prj_s4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prj_s4.Model.Contenu;
import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Post;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Services.Methodes_event;
import com.example.prj_s4.Services.Methodes_page;
import com.example.prj_s4.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddPost extends AppCompatActivity {

    private EditText nom1, discription1, date1, per1, id1;
    private TextView text3;
    private ImageView i1, b1;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0EF1EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un post");

        i1 = (ImageView) findViewById(R.id.imageevent2);
        b1 = (ImageView) findViewById(R.id.imageajouterevn);


        //nom1=(EditText) findViewById(R.id.nomeventajout);
        discription1 = (EditText) findViewById(R.id.discriptionevenajout);
        //tele1=(EditText) findViewById(R.id.phone);
        //pass1=(EditText) findViewById(R.id.password);
        text3 = (TextView) findViewById(R.id.ajoutevent);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        storageReference = FirebaseStorage.getInstance().getReference();


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref1 = getApplicationContext().getSharedPreferences("page_connecte", MODE_PRIVATE);
                Gson gson1 = new Gson();
                String json1 = pref1.getString("page_c", "");
                final Page p1 = gson1.fromJson(json1, Page.class);
                Methodes_page.Getpagebynom(p1.getNom()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String Type = null;
                            String nomuser = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Page page = document.toObject(Page.class);
                                String discription_event = String.valueOf(discription1.getText());
                                System.out.print("ttttttttttttttttttttttt" + discription_event);
                                int id_event = 2;
                                Date date_event = null;
                                ArrayList<String> s = new ArrayList<>();
                                String image = discription_event;
                                s.add(image);
                                ArrayList<Utilisateur> per_participes = null;


                                Contenu c = new Contenu(discription_event, s, null, null);
                                Date currentTime = Calendar.getInstance().getTime();
                                Post p = new Post(c, page,currentTime);
                                Methodes_event.creatEvent(p);
                                uploadImage(image);
                                Toast.makeText(getApplicationContext(), "Votre post a été ajouter avec succe", Toast.LENGTH_LONG).show();

                                Intent in = new Intent(AddPost.this, ListPost.class);
                                //
                                in.putExtra("pageavoir", "tout");
                                //
                                startActivity(in);
                            }

                        }
                    }
                });
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        //intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                i1.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(String contenu) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("events/" + contenu);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPost.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPost.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
}
