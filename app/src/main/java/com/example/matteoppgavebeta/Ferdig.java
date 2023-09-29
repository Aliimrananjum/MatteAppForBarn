package com.example.matteoppgavebeta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Ferdig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.ferdig);

        Button avslutt_knapp=findViewById(R.id.avslutt);
        Button nytt_knapp=findViewById(R.id.nytt);

        //legg til alle intent
        Intent start=new Intent(this,Start_Aktivitet.class);
        Intent avslutt = new Intent(this, MainActivity.class);



        avslutt_knapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(avslutt);
            }
        });

        nytt_knapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(start);
            }
        });
    }
}
