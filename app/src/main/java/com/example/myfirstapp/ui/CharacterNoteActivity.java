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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CharacterNoteActivity extends AppCompatActivity {

    private EditText idealiEditText;
    private EditText descrizioneEditText;
    private EditText sinossiEditText;

    private Button idealsButton;
    private Button descriptionButton;
    private Button synopsisButton;

    private RelativeLayout idealsView;
    private RelativeLayout descriptionlView;
    private RelativeLayout synopsisView;

    private CardView idealsCardView;
    private CardView descriptionCardView;
    private CardView synopsisCardView;

    private DBManager db;
    private String nomecamp;
    private String nomeg;
    private List<StringBuffer> notelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_note);


        idealiEditText = findViewById(R.id.note_ideals);
        descrizioneEditText = findViewById(R.id.note_description);
        sinossiEditText = findViewById(R.id.note_synopsis);
/*
        this.estraiNote();
        this.setView();
*/
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

    public void estraiNote(){
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        db = new DBManager(this);
        assert nomeg != null;
        assert nomecamp != null;
        notelist = db.leggiNotevarie(nomecamp,nomeg);
    }

    public void setView(){

        descrizioneEditText = (EditText) findViewById(R.id.note_description);
        descrizioneEditText.setText(notelist.get(0));

        idealiEditText = (EditText) findViewById(R.id.note_ideals);
        idealiEditText.setText(notelist.get(1));
    }

    public void save(View view) {
        db = new DBManager(this);
        StringBuffer ideali = new StringBuffer();
        ideali.append(idealiEditText.getText().toString());
        StringBuffer descrizione = new StringBuffer();
        descrizione.append(descrizioneEditText.getText().toString());
        String sinossi = sinossiEditText.getText().toString();

        db.aggiornaNoteVarie(nomecamp,nomeg,descrizione,ideali);

    }
}