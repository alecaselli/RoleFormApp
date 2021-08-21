package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaClasse;
import com.example.myfirstapp.database.TabellaRazza;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Classe;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.domain.Razza;
import com.example.myfirstapp.domain.ValutaOld;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CreateNewCharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editText;
    private Giocatore nuovoGiocatore;
    private DBManager dbManager;
    private final String VALUTADND = "moneta del regno di Ho";

    private Spinner raceSpinner;
    private Spinner classSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_character);

        this.setSpinner();
    }

    public void setSpinner() {
        dbManager = new DBManager(this);

        List<String> raceList = dbManager.leggiPK(TabellaRazza.TBL_NOME, TabellaRazza.FIELD_NOMER);
        ArrayAdapter<String> raceSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        raceSpinnerAdapter.addAll(raceList);
        raceSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        raceSpinner = findViewById(R.id.create_character_race);
        raceSpinner.setAdapter(raceSpinnerAdapter);
        raceSpinner.setOnItemSelectedListener(this);


        List<String> classList = dbManager.leggiPK(TabellaClasse.TBL_NOME, TabellaClasse.FIELD_NOMECLA);
        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        classSpinnerAdapter.addAll(classList);
        classSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        classSpinner = findViewById(R.id.create_character_class);
        classSpinner.setAdapter(classSpinnerAdapter);
        classSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(@NotNull AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createNewCharacter(View view) {
        editText = (EditText) findViewById(R.id.create_campaign_name);
        StringBuffer desc = new StringBuffer();
        String nomecamp = editText.getText().toString();

        editText = (EditText) findViewById(R.id.create_character_name);
        String nomeg = editText.getText().toString();

        editText = findViewById(R.id.create_character_age);
        String eta = editText.getText().toString();

        editText = findViewById(R.id.create_character_height);
        String altezza = editText.getText().toString();

        editText = findViewById(R.id.create_character_gender);
        String genere = editText.getText().toString();

        ValutaOld portafoglio = dbManager.leggiValuta(VALUTADND);

        Classe classe = dbManager.leggiClasse(classSpinner.getSelectedItem().toString());

        Razza razza = dbManager.leggiRazza(raceSpinner.getSelectedItem().toString());

        List<Caratteristica> caratteristicas = dbManager.leggiCaratteristica();
        List<Abilita> abilitas = dbManager.leggiAbilita();

        nuovoGiocatore = new Giocatore(nomeg, desc, nomecamp, eta, altezza, genere, portafoglio, classe, razza, caratteristicas, abilitas);

        if (!dbManager.aggiungiGiocatore(nuovoGiocatore))
            Toast.makeText(this, "inserimeto fallito", Toast.LENGTH_LONG).show();
        else
            onBackPressed();
    }

    /*public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openCharacterActivity() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nuovoGiocatore.getNomeCampagna());
        intent.putExtra("nomeg", nuovoGiocatore.getNome());
        startActivity(intent);
        finish();
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}