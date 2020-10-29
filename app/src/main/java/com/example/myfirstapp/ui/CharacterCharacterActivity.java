package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Giocatore;

public class CharacterCharacterActivity extends AppCompatActivity {

    private Giocatore giocatore;
    private TextView txt;
    private EditText edit;

    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_character);

        this.estraiGiocatore();
        this.setView();
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        DBManager db = new DBManager(this);
        giocatore = db.leggiGiocatore(nomecamp, nomeg);
    }

    public void setView() {
/*
        txt = (TextView) findViewById();
        txt.setText(giocatore.getNomeCampagna());

        txt = (TextView) findViewById();
        txt.setText(giocatore.getNome());

        txt = (TextView) findViewById();
        txt.setText(giocatore.getGenere());

        String liv = "" + giocatore.getLivello();
        txt = (TextView) findViewById();
        txt.setText(liv);

        txt = (TextView) findViewById();
        txt.setText(String.valueOf(giocatore.getPuntiFerita()));

        txt = (TextView) findViewById();
        txt.setText(String.valueOf(giocatore.getPuntiFeritaMax()));

        txt = (TextView) findViewById();
        txt.setText(String.valueOf(giocatore.getMana()));

        txt = (TextView) findViewById();
        txt.setText(String.valueOf(giocatore.getMana()));

        String alt = giocatore.getAltezza() + "";
        txt = (TextView) findViewById();
        txt.setText(alt);

        String eta = giocatore.getEta() + "";
        txt = (TextView) findViewById();
        txt.setText(eta);

        txt = (TextView) findViewById();
        txt.setText(giocatore.getRazza().getNome());

        txt = (TextView) findViewById();
        txt.setText(giocatore.getRazza().getDescrizione());

        txt = (TextView) findViewById();
        txt.setText(giocatore.getClasse().getNome());

        txt = (TextView) findViewById();
        txt.setText(giocatore.getClasse().getDescrizione());

        edit = (EditText) findViewById();
        edit.setText(giocatore.getIniziativa());

        edit = (EditText) findViewById();
        edit.setText(giocatore.getClasseArmatura());
*/
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