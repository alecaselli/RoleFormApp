package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaClasse;
import com.example.myfirstapp.database.TabellaRazza;

import java.util.List;

public class CreateNewCharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_character);

        DBManager db = new DBManager(this);

        List<List<String>> doubleRaceList = db.leggiPK(TabellaRazza.TBL_NOME, TabellaRazza.FIELD_NOMER);
        doubleRaceList = DBManager.convertiLista(doubleRaceList);
        List<String> raceList = doubleRaceList.get(0);
        ArrayAdapter<String> raceSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        raceSpinnerAdapter.addAll(raceList);
        raceSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        Spinner raceSpinner = findViewById(R.id.create_character_race);
        raceSpinner.setAdapter(raceSpinnerAdapter);
        raceSpinner.setOnItemSelectedListener(this);

        List<List<String>> doubleClassList = db.leggiPK(TabellaClasse.TBL_NOME, TabellaClasse.FIELD_NOMECLA);
        doubleClassList = DBManager.convertiLista(doubleClassList);
        List<String> classList = doubleClassList.get(0);
        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        classSpinnerAdapter.addAll(classList);
        classSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        Spinner classSpinner = findViewById(R.id.create_character_class);
        classSpinner.setAdapter(classSpinnerAdapter);
        classSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void createNewCharacter(View view){

    }
}