package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Giocatore;

public class CharacterStatsActivity extends AppCompatActivity {

    private String nomecamp;
    private String nomeg;
    private Giocatore giocatore;

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_stats);

        this.estraiGiocatore();
        this.setView();
    }

    @SuppressLint("DefaultLocale")
    public void setView() {
        this.setCaratteristica("forza", R.id.stats_total_strenght, R.id.stats_race_strenght);
        this.setCaratteristica("destrezza", R.id.stats_total_dexterity, R.id.stats_race_dexterity);
        this.setCaratteristica("costituzione", R.id.stats_total_constitution, R.id.stats_race_constitution);
        this.setCaratteristica("intelligenza", R.id.stats_total_intelligence, R.id.stats_race_intelligence);
        this.setCaratteristica("saggezza", R.id.stats_total_wisdom, R.id.stats_race_wisdom);
        this.setCaratteristica("carisma", R.id.stats_total_charisma, R.id.stats_race_charisma);
    }
    
    public void setCaratteristica(String tipo, int idBase, int idBonus) {
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        txt = (TextView) findViewById(idBase);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(idBonus);
        //mostra il valore dato dalla razza
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