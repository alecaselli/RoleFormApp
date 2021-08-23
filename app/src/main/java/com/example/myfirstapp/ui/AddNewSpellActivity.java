package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;

public class AddNewSpellActivity extends AppCompatActivity {

    private String nomecamp;
    private String nomeg;
    private int livello;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_spell);

        this.estraiIntent();
        this.setView();
    }

    public void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        livello = intent.getIntExtra("livello", -1);
        dbManager = new DBManager(this);
    }

    public void setView(){

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }
}