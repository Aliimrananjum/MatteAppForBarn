package com.example.matteoppgavebeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hentet knappet fra XML i java

        ImageButton start_knapp=findViewById(R.id.knapp_start);
        ImageButton om_knapp=findViewById(R.id.knapp_om);
        ImageButton pref_knapp=findViewById(R.id.knapp_pref);



        //legg til alle intent
        Intent start=new Intent(this,Start_Aktivitet.class);
        Intent settings = new Intent(this, SettingsActivity.class);
        Intent omOss = new Intent(this, OmOss_aktivitet.class);



        //legg til alle knapper slik at de går til riktig aktivitet
        start_knapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(start);
            }
        });

        pref_knapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(settings);
            }
        });

        om_knapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(omOss);
            }
        });


    }

}