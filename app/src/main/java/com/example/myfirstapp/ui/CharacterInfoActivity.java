package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;


public class CharacterInfoActivity extends AppCompatActivity {

    private TextView txt;

    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);

        this.estraiIntent();
        this.readInfo();
    }

    public void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
    }

    public void readInfo() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("character_info"), StandardCharsets.UTF_8))) {

            String line;
            StringBuilder info = new StringBuilder();
            while (((line = reader.readLine()) != null)) {
                info.append(line).append("\n");
            }
            info.deleteCharAt(info.lastIndexOf("\n"));

            txt = (TextView) findViewById(R.id.character_info);
            txt.setText(info);

        } catch (IOException e) {
            Log.e("CREDITS", "filed asset read", e);

        }
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
