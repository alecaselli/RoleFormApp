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
import com.example.myfirstapp.database.DBManager;

import java.util.List;

public class CharacterNoteActivity extends AppCompatActivity {

    private EditText idealiEditText;
    private EditText descrizioneEditText;
    private EditText sinossiEditText;
    private EditText generaliEditText;

    private Button idealsButton;
    private Button descriptionButton;
    private Button synopsisButton;
    private Button generalButton;

    private RelativeLayout idealsView;
    private RelativeLayout descriptionlView;
    private RelativeLayout synopsisView;
    private RelativeLayout generalView;

    private CardView idealsCardView;
    private CardView descriptionCardView;
    private CardView synopsisCardView;
    private CardView generalCardView;

    private DBManager dbManager;
    private String nomecamp;
    private String nomeg;
    private List<String> notelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_note);

        this.estraiNote();
        this.setView();
        this.setButton();

    }

    public void estraiNote() {
        dbManager = new DBManager(this);
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        assert nomeg != null;
        assert nomecamp != null;
        notelist = dbManager.leggiNotevarie(nomecamp, nomeg);
    }

    public void setView() {

        idealiEditText = findViewById(R.id.note_ideals);
        descrizioneEditText = findViewById(R.id.note_description);
        sinossiEditText = findViewById(R.id.note_synopsis);
        generaliEditText = findViewById(R.id.note_general);

        idealiEditText = findViewById(R.id.note_ideals);
        idealiEditText.setText(notelist.get(0));

        descrizioneEditText = findViewById(R.id.note_description);
        descrizioneEditText.setText(notelist.get(1));

        sinossiEditText = findViewById(R.id.note_synopsis);
        sinossiEditText.setText(notelist.get(2));

        generaliEditText = findViewById(R.id.note_general);
        generaliEditText.setText(notelist.get(3));

    }

    public void save(View view) {
        String ideali = idealiEditText.getText().toString();
        String descrizione = descrizioneEditText.getText().toString();
        String sinossi = sinossiEditText.getText().toString();
        String generali = generaliEditText.getText().toString();

        if (dbManager.aggiornaNoteVarie(nomecamp, nomeg, ideali, descrizione, sinossi, generali))
            Toast.makeText(this, "note salvate", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "salvataggio fallito", Toast.LENGTH_SHORT).show();

    }

    public void setButton() {

        idealsButton = findViewById(R.id.note_ideals_expandButton);
        idealsView = findViewById(R.id.note_ideals_expandableView);
        idealsCardView = findViewById(R.id.note_ideals_cardView);

        idealsButton.setOnClickListener(view -> {
            if (idealsView.getVisibility() == View.GONE) {
                TransitionManager.beginDelayedTransition((ViewGroup) idealsCardView.getParent().getParent(), new AutoTransition());
                idealsView.setVisibility(View.VISIBLE);
                idealsButton.setBackgroundResource(R.drawable.ic_arrow_up);
            } else {
                TransitionManager.beginDelayedTransition((ViewGroup) idealsCardView.getParent().getParent(), new AutoTransition());
                idealsView.setVisibility(View.GONE);
                idealsButton.setBackgroundResource(R.drawable.ic_arrow_down);
            }
        });

        descriptionButton = findViewById(R.id.note_description_expandButton);
        descriptionlView = findViewById(R.id.note_description_expandableView);
        descriptionCardView = findViewById(R.id.note_description_cardView);

        descriptionButton.setOnClickListener(view -> {
            if (descriptionlView.getVisibility() == View.GONE) {
                TransitionManager.beginDelayedTransition((ViewGroup) descriptionCardView.getParent().getParent(), new AutoTransition());
                descriptionlView.setVisibility(View.VISIBLE);
                descriptionButton.setBackgroundResource(R.drawable.ic_arrow_up);
            } else {
                TransitionManager.beginDelayedTransition((ViewGroup) descriptionCardView.getParent().getParent(), new AutoTransition());
                descriptionlView.setVisibility(View.GONE);
                descriptionButton.setBackgroundResource(R.drawable.ic_arrow_down);
            }
        });

        synopsisButton = findViewById(R.id.note_synopsis_expandButton);
        synopsisView = findViewById(R.id.note_synopsis_expandableView);
        synopsisCardView = findViewById(R.id.note_synopsis_cardView);

        synopsisButton.setOnClickListener(view -> {
            if (synopsisView.getVisibility() == View.GONE) {
                TransitionManager.beginDelayedTransition((ViewGroup) synopsisCardView.getParent().getParent(), new AutoTransition());
                synopsisView.setVisibility(View.VISIBLE);
                synopsisButton.setBackgroundResource(R.drawable.ic_arrow_up);
            } else {
                TransitionManager.beginDelayedTransition((ViewGroup) synopsisCardView.getParent().getParent(), new AutoTransition());
                synopsisView.setVisibility(View.GONE);
                synopsisButton.setBackgroundResource(R.drawable.ic_arrow_down);
            }
        });

        generalButton = findViewById(R.id.note_general_expandButton);
        generalView = findViewById(R.id.note_general_expandableView);
        generalCardView = findViewById(R.id.note_general_cardView);

        generalButton.setOnClickListener(view -> {
            if (generalView.getVisibility() == View.GONE) {
                TransitionManager.beginDelayedTransition((ViewGroup) generalCardView.getParent().getParent(), new AutoTransition());
                generalView.setVisibility(View.VISIBLE);
                generalButton.setBackgroundResource(R.drawable.ic_arrow_up);
            } else {
                TransitionManager.beginDelayedTransition((ViewGroup) generalCardView.getParent().getParent(), new AutoTransition());
                generalView.setVisibility(View.GONE);
                generalButton.setBackgroundResource(R.drawable.ic_arrow_down);
            }
        });


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