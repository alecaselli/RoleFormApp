package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoteActivity extends AppCompatActivity {

    private final String file_note = "note.txt";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = (EditText) findViewById(R.id.edit);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput(file_note)));

        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fileOutput= openFileOutput(file_note, MODE_PRIVATE);
                fileOutput.close();
                reader = new BufferedReader(new InputStreamReader(openFileInput(file_note)));
            } catch (IOException ex) {
                Log.e("NON DEVE SUCCEDERE","NON DEVE SUCCEDERE", e);
            }
        } finally {

            try {
                String line;
                StringBuilder note = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    note.append(line);
                    note.append("\n");
                }
                note.deleteCharAt(note.lastIndexOf("\n"));

                editText.setText(note);

                reader.close();
            } catch (IOException e) {
                Log.e("EDIT","Fallita apertura edit",e);
            }
        }
    }

    public void aggiornaTestoNote(View view){

    }



}