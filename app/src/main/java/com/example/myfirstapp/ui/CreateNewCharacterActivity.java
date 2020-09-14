package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.myfirstapp.R;

public class CreateNewCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_character);

        Spinner raceSpinner =findViewById(R.id.create_character_race);

    }
}