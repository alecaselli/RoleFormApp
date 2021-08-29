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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DettagliGiocatoreDB;
import com.example.myfirstapp.database.DettagliLivelloDB;
import com.example.myfirstapp.database.DettagliManaDB;
import com.example.myfirstapp.database.DettagliPuntiFeritaDB;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.interactor.DettagliGiocatoreLoader;
import com.example.myfirstapp.interactor.DettagliLivelloInteractor;
import com.example.myfirstapp.interactor.DettagliManaInteractor;
import com.example.myfirstapp.interactor.DettagliPuntiFeritaInteractor;
import com.example.myfirstapp.interactor.InterfaceDettagliGiocatoreLoader;
import com.example.myfirstapp.interactor.InterfaceDettagliGiocatoreView;
import com.example.myfirstapp.interactor.InterfaceDettagliLivelloInteractor;
import com.example.myfirstapp.interactor.InterfaceDettagliLivelloview;
import com.example.myfirstapp.interactor.InterfaceDettagliManaInteractor;
import com.example.myfirstapp.interactor.InterfaceDettagliManaView;
import com.example.myfirstapp.interactor.InterfaceDettagliPuntiFeritaInteractor;
import com.example.myfirstapp.interactor.InterfaceDettagliPuntiFeritaView;

public class CharacterDetailsActivity extends AppCompatActivity implements InterfaceDettagliGiocatoreView, InterfaceDettagliLivelloview, InterfaceDettagliManaView, InterfaceDettagliPuntiFeritaView {

    private Giocatore giocatore;
    String nomecamp;
    String nomeg;
    private TextView txt;
    private EditText edit;
    private InterfaceDettagliGiocatoreLoader dettagliGiocatoreLoader;
    private InterfaceDettagliLivelloInteractor livelloInteractor;
    private InterfaceDettagliManaInteractor manaInteractor;
    private InterfaceDettagliPuntiFeritaInteractor puntiFeritaInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        this.estraiIntent();
        this.setInteractor();
        this.setView();
        this.setEdit();
        this.setlongLevelButton();
    }

    private void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
    }

    private void setInteractor(){
        dettagliGiocatoreLoader = new DettagliGiocatoreLoader(new DettagliGiocatoreDB(nomecamp, nomeg, this), this);
        livelloInteractor = new DettagliLivelloInteractor(new DettagliLivelloDB(nomecamp, nomeg, this), this);
        manaInteractor = new DettagliManaInteractor(new DettagliManaDB(nomecamp, nomeg, this), this);
        puntiFeritaInteractor = new DettagliPuntiFeritaInteractor(new DettagliPuntiFeritaDB(nomecamp, nomeg , this), this);
    }

    private void setView() {
        dettagliGiocatoreLoader.setFisionomiaGiocatore();
        dettagliGiocatoreLoader.setClasse();
        dettagliGiocatoreLoader.setRazza();
        dettagliGiocatoreLoader.setClasseArmatura();
        livelloInteractor.setLivello();
        manaInteractor.setMana();
        puntiFeritaInteractor.setPuntiFerita();
        //TODO: implementare iniziativa
    }

    private void setEdit() {
        final EditText editPuntiFerita = findViewById(R.id.character_character_life);
        editPuntiFerita.setFocusableInTouchMode(true);
        editPuntiFerita.requestFocus();
        editPuntiFerita.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    puntiFeritaInteractor.changePuntiFerita(Integer.parseInt(editPuntiFerita.getText().toString()));
                    return true;
                }
                return false;
            }
        });

        final EditText editMana = findViewById(R.id.character_character_mana);
        editMana.setFocusableInTouchMode(true);
        editMana.requestFocus();
        editMana.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    manaInteractor.changeMana(Integer.parseInt(editMana.getText().toString()));
                    return true;
                }
                return false;
            }
        });
    }

    private void setlongLevelButton() {
        CardView levelButton = (CardView) findViewById(R.id.levelButton);
        levelButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                levelDown();
                return true;
            }
        });
    }

    public void levelUp(View view) {
        txt = findViewById(R.id.character_character_level);
        livelloInteractor.livelloUp(Integer.parseInt(txt.getText().toString()));

    }

    public void levelDown() {
        txt = findViewById(R.id.character_character_level);
        livelloInteractor.livelloDown(Integer.parseInt(txt.getText().toString()));
    }


    @Override
    public void displayError(int indexError) {
        Toast.makeText(this, this.getString(indexError), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFisionomia(String nome, String genere, String altezza, String eta) {
        txt = findViewById(R.id.character_character_name);
        txt.setText(nome);

        txt = findViewById(R.id.character_character_genre);
        txt.setText(genere);

        txt = findViewById(R.id.character_character_height);
        txt.setText(String.format("%s cm", altezza));

        txt = findViewById(R.id.character_character_age);
        txt.setText(String.format("%s anni", eta));
    }

    @Override
    public void setRazza(String nome, String descrizione) {
        txt = findViewById(R.id.character_character_race);
        txt.setText(nome);

        txt = findViewById(R.id.character_character_race_description);
        txt.setText(descrizione);
    }

    @Override
    public void setClasse(String nome, String descrizione) {
        txt = findViewById(R.id.character_character_class);
        txt.setText(nome);

        txt = findViewById(R.id.character_character_class_description);
        txt.setText(descrizione);
    }

    @Override
    public void setClasseArmatura(int classeArmatura) {
        txt =  findViewById(R.id.character_character_armorClass);
        txt.setText(String.valueOf(classeArmatura));
    }

    @Override
    public void setLivello(int livello) {
        txt = findViewById(R.id.character_character_level);
        txt.setText(String.valueOf(livello));
    }

    @Override
    public void setMana(int mana) {
        edit = findViewById(R.id.character_character_mana);
        edit.setText(String.valueOf(mana));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void setManaMax(int manaMax) {
        txt = findViewById(R.id.character_character_max_mana);
        txt.setText(String.format("/ %d",manaMax));
    }

    @Override
    public void setPuntiFerita(int puntiFerita) {
        edit = findViewById(R.id.character_character_life);
        edit.setText(String.valueOf(puntiFerita));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void setPuntiFeritaMax(int puntiFeritaMax) {
        txt = findViewById(R.id.character_character_max_life);
        txt.setText(String.format("/ %d", puntiFeritaMax));
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