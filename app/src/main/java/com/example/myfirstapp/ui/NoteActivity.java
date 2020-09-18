package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myfirstapp.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoteActivity extends AppCompatActivity {

    private EditText idealiEditText;
    private EditText descrizioneEditText;
    private EditText sinossiEditText;

    private String filename;

    private Button idealsButton;
    private Button descriptionButton;
    private Button synopsisButton;

    private RelativeLayout idealsView;
    private RelativeLayout descriptionlView;
    private RelativeLayout synopsisView;

    private CardView idealsCardView;
    private CardView descriptionCardView;
    private CardView synopsisCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent intent = getIntent();
        filename = intent.getStringExtra("filename");

        idealiEditText = findViewById(R.id.note_ideals);
        descrizioneEditText = findViewById(R.id.note_description);
        sinossiEditText = findViewById(R.id.note_synopsis);

        this.create();
        this.load();

        idealsButton = findViewById(R.id.note_ideals_expandButton);
        idealsView=findViewById(R.id.note_ideals_expandableView);
        idealsCardView=findViewById(R.id.note_ideals_cardView);

        idealsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (idealsView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition((ViewGroup) idealsCardView.getParent().getParent(), new AutoTransition());
                    idealsView.setVisibility(View.VISIBLE);
                    idealsButton.setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) idealsCardView.getParent().getParent(), new AutoTransition());
                    idealsView.setVisibility(View.GONE);
                    idealsButton.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

        descriptionButton = findViewById(R.id.note_description_expandButton);
        descriptionlView=findViewById(R.id.note_description_expandableView);
        descriptionCardView=findViewById(R.id.note_description_cardView);

        descriptionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (descriptionlView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition((ViewGroup) descriptionCardView.getParent().getParent(), new AutoTransition());
                    descriptionlView.setVisibility(View.VISIBLE);
                    descriptionButton.setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) descriptionCardView.getParent().getParent(), new AutoTransition());
                    descriptionlView.setVisibility(View.GONE);
                    descriptionButton.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

        synopsisButton = findViewById(R.id.note_synopsis_expandButton);
        synopsisView=findViewById(R.id.note_synopsis_expandableView);
        synopsisCardView=findViewById(R.id.note_synopsis_cardView);

        synopsisButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (synopsisView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition((ViewGroup) synopsisCardView.getParent().getParent(), new AutoTransition());
                    synopsisView.setVisibility(View.VISIBLE);
                    synopsisButton.setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) synopsisCardView.getParent().getParent(), new AutoTransition());
                    synopsisView.setVisibility(View.GONE);
                    synopsisButton.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

    }

    public void create(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(filename);
        } catch (FileNotFoundException e) {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(filename, MODE_PRIVATE);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        FileInputStream fis = null;

        try {
            fis = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null && (line.compareTo("----") != 0)) {
                builder.append(line).append("\n");
            }
            idealiEditText.setText(builder.toString());

            builder = new StringBuilder();
            while ((line = reader.readLine()) != null && (line.compareTo("----") != 0)) {
                builder.append(line).append("\n");
            }
            descrizioneEditText.setText(builder.toString());

            builder = new StringBuilder();
            while ((line = reader.readLine()) != null && (line.compareTo("----") != 0)) {
                builder.append(line).append("\n");
            }
            sinossiEditText.setText(builder.toString());

        } catch (FileNotFoundException e) {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(filename, MODE_PRIVATE);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save(View view) {
        String ideali = idealiEditText.getText().toString();
        String descrizione = descrizioneEditText.getText().toString();
        String sinossi = sinossiEditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(ideali.getBytes());
            fos.write("\n".getBytes());
            fos.write("----".getBytes());
            fos.write("\n".getBytes());
            fos.write(descrizione.getBytes());
            fos.write("\n".getBytes());
            fos.write("----".getBytes());
            fos.write("\n".getBytes());
            fos.write(sinossi.getBytes());

            Toast.makeText(this, "Note salvate", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}