package com.example.prj_s4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prj_s4.Model.Page;
import com.example.prj_s4.Model.Utilisateur;
import com.example.prj_s4.Services.Methodes_page;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class AddPage extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText nom1, discription1, date1, per1, id1;
    private TextView text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0EF1EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter une page");
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        text3 = (TextView) findViewById(R.id.Ajouterpage);
        nom1 = (EditText) findViewById(R.id.nompage1);


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = String.valueOf(spinner1.getSelectedItem());
                Date currentTime = Calendar.getInstance().getTime();
                String nompage=nom1.getText().toString();
                SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson1 = new Gson();
                String json1 = pref1.getString("personne_c", "");
                Utilisateur p1 = gson1.fromJson(json1, Utilisateur.class);

                Page page=new Page(nompage,currentTime,type,p1);
                Methodes_page.creatpage(page);

                Toast.makeText(getApplicationContext(), "Votre page a été ajouter avec succe", Toast.LENGTH_LONG).show();

                Intent in = new Intent(AddPage.this, ConsulterPage.class);
                //
                in.putExtra("pageavoir", "tout");
                //
                startActivity(in);


            }
        });
    }
}
