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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;

import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    private Giocatore giocatore;
    private TextView txt;

    private Button currencyButton;

    private View currencyBaseView;
    private View currencyModView;

    private CardView currencyCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        this.estraiGiocatore();
        this.setView();
        this.setButton();

        CardView goldBaseButton = findViewById(R.id.goldBaseButton);
        goldBaseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        CardView silverBaseButton = findViewById(R.id.silverBaseButton);
        silverBaseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        CardView bronzeBaseButton = findViewById(R.id.bronzeBaseButton);
        bronzeBaseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    public void goldBaseButton(View view){
        return;
    }

    public void silverBaseButton(View view){
        return;
    }

    public void bronzeBaseButton(View view){
        return;
    }

    public void setButton() {
        currencyButton = findViewById(R.id.character_currency_button);
        currencyBaseView = findViewById(R.id.character_currency_base);
        currencyModView = findViewById(R.id.character_currency_mod);
        currencyCard = findViewById(R.id.character_currency);

        currencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currencyBaseView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) currencyCard.getParent().getParent(), new AutoTransition());
                    currencyBaseView.setVisibility(View.GONE);
                    currencyModView.setVisibility(View.VISIBLE);
                    currencyButton.setBackgroundResource(R.drawable.ic_round_done);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) currencyCard.getParent().getParent(), new AutoTransition());
                    currencyBaseView.setVisibility(View.VISIBLE);
                    currencyModView.setVisibility(View.GONE);
                    currencyButton.setBackgroundResource(R.drawable.ic_round_change_history);
                }
            }
        });
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        String nomecamp = intent.getStringExtra("nomecamp");
        String nomeg = intent.getStringExtra("nomeg");
        DBManager dbManager = new DBManager(this);
        assert nomeg != null;
        assert nomecamp != null;
        giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
    }

    public void setView() {

        txt = (TextView) findViewById(R.id.campaign_name);
        txt.setText(giocatore.getNomeCampagna());

        txt = (TextView) findViewById(R.id.character_name);
        txt.setText(giocatore.getNome());

        txt = (TextView) findViewById(R.id.character_genre);
        txt.setText(giocatore.getGenere());

        String liv = "Livello" + " " + giocatore.getLivello();
        txt = (TextView) findViewById(R.id.level);
        txt.setText(liv);

        txt = (TextView) findViewById(R.id.character_life);
        txt.setText(String.valueOf(giocatore.getPuntiFerita()));

        txt = (TextView) findViewById(R.id.character_max_life);
        txt.setText(String.valueOf(giocatore.getPuntiFeritaMax()));

        txt = (TextView) findViewById(R.id.character_mana);
        txt.setText(String.valueOf(giocatore.getMana()));

        txt = (TextView) findViewById(R.id.character_max_mana);
        txt.setText(String.valueOf(giocatore.getMana()));

        String alt = giocatore.getAltezza() + " " + "cm";
        txt = (TextView) findViewById(R.id.character_height);
        txt.setText(alt);

        String eta = giocatore.getEta() + " " + "anni";
        txt = (TextView) findViewById(R.id.character_age);
        txt.setText(eta);

        txt = (TextView) findViewById(R.id.character_race);
        txt.setText(giocatore.getRazza().getNome());

        txt = (TextView) findViewById(R.id.character_class);
        txt.setText(giocatore.getClasse().getNome());

        Caratteristica caratteristica = giocatore.getCaratteristica("forza");
        txt = (TextView) findViewById(R.id.character_base_strenght);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(R.id.character_bonus_strenght);
        txt.setText(String.valueOf(caratteristica.getBonus()));

        caratteristica = giocatore.getCaratteristica("destrezza");
        txt = (TextView) findViewById(R.id.character_base_dexterity);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(R.id.character_bonus_dexterity);
        txt.setText(String.valueOf(caratteristica.getBonus()));

        caratteristica = giocatore.getCaratteristica("costituzione");
        txt = (TextView) findViewById(R.id.character_base_constitution);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(R.id.character_bonus_constitution);
        txt.setText(String.valueOf(caratteristica.getBonus()));

        caratteristica = giocatore.getCaratteristica("intelligenza");
        txt = (TextView) findViewById(R.id.character_base_intelligence);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(R.id.character_bonus_intelligence);
        txt.setText(String.valueOf(caratteristica.getBonus()));

        caratteristica = giocatore.getCaratteristica("saggezza");
        txt = (TextView) findViewById(R.id.character_base_wisdom);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(R.id.character_bonus_wisdom);
        txt.setText(String.valueOf(caratteristica.getBonus()));

        caratteristica = giocatore.getCaratteristica("carisma");
        txt = (TextView) findViewById(R.id.character_base_charisma);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(R.id.character_bonus_charisma);
        txt.setText(String.valueOf(caratteristica.getBonus()));

        List<Integer> valoreint = giocatore.getPortafoglio().getValoreInMonete();
        txt = (TextView) findViewById(R.id.character_base_copper);
        txt.setText(String.valueOf(valoreint.get(0)));
        txt = (TextView) findViewById(R.id.character_base_silver);
        txt.setText(String.valueOf(valoreint.get(1)));
        txt = (TextView) findViewById(R.id.character_base_gold);
        txt.setText(String.valueOf(valoreint.get(2)));


        Equipaggiamento equipaggiamento = giocatore.getEquipaggiato("armatura");
        String nome;
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        txt = (TextView) findViewById(R.id.armor_name);
        txt.setText(nome);

        equipaggiamento = giocatore.getEquipaggiato("scudo");
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        txt = (TextView) findViewById(R.id.shield_name);
        txt.setText(nome);

        equipaggiamento = giocatore.getEquipaggiato("arma");
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        txt = (TextView) findViewById(R.id.weapon_name);
        txt.setText(nome);
    }

    public void baseGoldButton(View view){

    }


    public void openCharacterNote(View view) {
        Intent intent = new Intent(this, CharacterNoteActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
    }

    public void openCharacterCharacter(View view) {
        Intent intent = new Intent(this, CharacterCharacterActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
    }

    public void openCharacterSkills(View view) {
        Intent intent = new Intent(this, CharacterSkillsActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
    }

    public void openCharacterSpells(View view) {
        Intent intent = new Intent(this, CharacterSpellsActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
    }

    public void openCharacterStats(View view) {
        Intent intent = new Intent(this, CharacterStatsActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
    }

    public void openCharacterBag(View view) {
        Intent intent = new Intent(this, CharacterBagActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
    }

    public void openCharacterInfo(View view) {
        Intent intent = new Intent(this, CharacterInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}