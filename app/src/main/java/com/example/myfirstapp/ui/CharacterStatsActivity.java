package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Giocatore;

public class CharacterStatsActivity extends AppCompatActivity {

    private String nomecamp;
    private String nomeg;
    private Giocatore giocatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_stats);

        this.estraiGiocatore();
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        DBManager db = new DBManager(this);
        giocatore = db.leggiGiocatore(nomecamp, nomeg);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

}