package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Giocatore;

public class CharacterCharacterActivity extends AppCompatActivity {

    private Giocatore giocatore;
    private TextView txt;
    private EditText edit;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_character);

        this.estraiGiocatore();
        this.setView();
        this.longLevelButton();

        final EditText editPuntiFerita = (EditText) findViewById(R.id.character_character_life);
        editPuntiFerita.setFocusableInTouchMode(true);
        editPuntiFerita.requestFocus();
        editPuntiFerita.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    giocatore.setPuntiFerita(Integer.parseInt(editPuntiFerita.getText().toString()));
                    Toast.makeText(CharacterCharacterActivity.this, editPuntiFerita.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        final EditText editMana = (EditText) findViewById(R.id.character_character_mana);
        editMana.setFocusableInTouchMode(true);
        editMana.requestFocus();
        editMana.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    giocatore.setMana(Integer.parseInt(editMana.getText().toString()));
                    Toast.makeText(CharacterCharacterActivity.this, editMana.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        String nomecamp = intent.getStringExtra("nomecamp");
        String nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
        assert nomeg != null;
        assert nomecamp != null;
        giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
    }

    @SuppressLint("DefaultLocale")
    public void setView() {

        txt = (TextView) findViewById(R.id.character_character_name);
        txt.setText(giocatore.getNome());

        txt = (TextView) findViewById(R.id.character_character_genre);
        txt.setText(giocatore.getGenere());

        txt = (TextView) findViewById(R.id.character_character_level);
        txt.setText(String.valueOf(giocatore.getLivello()));

        edit = (EditText) findViewById(R.id.character_character_life);
        edit.setText(String.valueOf(giocatore.getPuntiFerita()));

        txt = (TextView) findViewById(R.id.character_character_max_life);
        txt.setText(String.format("/ %d", giocatore.getPuntiFeritaMax()));


        edit = (EditText) findViewById(R.id.character_character_mana);
        edit.setText(String.valueOf(giocatore.getMana()));

        /*
        txt = (TextView) findViewById(R.id.character_character_max_mana);
        txt.setText(String.format("/ %d", giocatore.getmanaMax()));
        */

        txt = (TextView) findViewById(R.id.character_character_height);
        txt.setText(String.format("%s cm", giocatore.getAltezza()));

        txt = (TextView) findViewById(R.id.character_character_age);
        txt.setText(String.format("%s anni", giocatore.getEta()));

        txt = (TextView) findViewById(R.id.character_character_race);
        txt.setText(giocatore.getRazza().getNome());

        txt = (TextView) findViewById(R.id.character_character_race_description);
        txt.setText(giocatore.getRazza().getDescrizione());

        txt = (TextView) findViewById(R.id.character_character_class);
        txt.setText(giocatore.getClasse().getNome());

        txt = (TextView) findViewById(R.id.character_character_class_description);
        txt.setText(giocatore.getClasse().getDescrizione());

        txt = (TextView) findViewById(R.id.character_character_armorClass);
        txt.setText(String.valueOf(giocatore.getClasseArmatura()));

        //implementare iniziativa
    }

    public void levelButton(View view) {
        giocatore.setLivello(giocatore.getLivello() + 1);
        txt = (TextView) findViewById(R.id.character_character_level);
        txt.setText(String.valueOf(giocatore.getLivello()));
    }

    public void longLevelButton(){
        CardView levelButton = (CardView) findViewById(R.id.levelButton);
        levelButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                giocatore.setLivello(giocatore.getLivello() - 1);
                txt = (TextView) findViewById(R.id.character_character_level);
                txt.setText(String.valueOf(giocatore.getLivello()));
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }
}