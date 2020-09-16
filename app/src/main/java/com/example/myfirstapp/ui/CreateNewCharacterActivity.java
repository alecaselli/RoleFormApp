package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaRazza;
import com.example.myfirstapp.domain.Razza;
import com.example.myfirstapp.utilities.ExampleItem;

import java.util.ArrayList;
import java.util.List;

public class CreateNewCharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_character);

        Spinner raceSpinner = findViewById(R.id.create_character_race);
        Spinner classSpinner = findViewById(R.id.create_character_class);

        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this, R.array.races, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);

        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);
        raceSpinner.setOnItemSelectedListener(this);

        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}