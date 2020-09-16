package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditActivity extends AppCompatActivity {

    private final String file_edit = "edit.txt";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = (EditText) findViewById(R.id.edit);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput(file_edit)));

        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fileOutput= openFileOutput(file_edit, MODE_PRIVATE);
                fileOutput.close();
                reader = new BufferedReader(new InputStreamReader(openFileInput(file_edit)));
            } catch (IOException ex) {
                Log.e("NON DEVE SUCCEDERE","NON DEVE SUCCEDERE", e);
            }
        } finally {

            try {
                String line;
                StringBuilder edit = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    edit.append(line);
                    edit.append("\n");
                }
                edit.deleteCharAt(edit.lastIndexOf("\n"));

                editText.setText(edit);

                reader.close();
            } catch (IOException e) {
                Log.e("EDIT","Fallita apertura edit",e);
            }
        }
    }


    public void aggiornaTestoEdit(View view){

    }

}