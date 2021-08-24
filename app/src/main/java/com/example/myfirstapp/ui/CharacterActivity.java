package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controller.PortafoglioController;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.utilities.MyDBException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    private PortafoglioController portafoglioController;
    private DBManager dbManager;
    private String nomecamp;
    private String nomeg;
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

        this.estraiIntent();
        this.setView();
        this.setButton();
        this.setOnLongClick();

    }

    private void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
        assert nomeg != null;
        assert nomecamp != null;
        giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
        try {
            portafoglioController =new PortafoglioController(nomecamp,nomeg,this);
        } catch (MyDBException e){
            Toast.makeText(this, "lettura portafoglio fallita", Toast.LENGTH_LONG).show();
        }

    }

    /* SET */
    @SuppressLint("DefaultLocale")
    private void setView() {

        txt = findViewById(R.id.campaign_name);
        txt.setText(giocatore.getNomeCampagna());

        txt = findViewById(R.id.character_name);
        txt.setText(giocatore.getNome());

        txt = findViewById(R.id.character_genre);
        txt.setText(giocatore.getGenere());

        txt = findViewById(R.id.level);
        txt.setText(String.format("Livello %d", giocatore.getLivello()));

        txt = findViewById(R.id.character_life);
        txt.setText(String.valueOf(giocatore.getPuntiFerita()));

        txt = findViewById(R.id.character_max_life);
        txt.setText(String.valueOf(giocatore.getPuntiFeritaMax()));

        txt = findViewById(R.id.character_mana);
        txt.setText(String.valueOf(giocatore.getMana()));

        txt = findViewById(R.id.character_max_mana);
        txt.setText(String.valueOf(giocatore.getManaMax()));

        txt = findViewById(R.id.character_height);
        txt.setText(String.format("%s cm", giocatore.getAltezza()));

        txt = findViewById(R.id.character_age);
        txt.setText(String.format("%s anni", giocatore.getEta()));

        txt = findViewById(R.id.character_race);
        txt.setText(giocatore.getRazza().getNome());

        txt = findViewById(R.id.character_class);
        txt.setText(giocatore.getClasse().getNome());

        this.setCaratteristica("forza", R.id.character_total_strenght, R.id.character_mod_strenght);
        this.setCaratteristica("destrezza", R.id.character_total_dexterity, R.id.character_mod_dexterity);
        this.setCaratteristica("costituzione", R.id.character_total_constitution, R.id.character_mod_constitution);
        this.setCaratteristica("intelligenza", R.id.character_total_intelligence, R.id.character_mod_intelligence);
        this.setCaratteristica("saggezza", R.id.character_total_wisdom, R.id.character_mod_wisdom);
        this.setCaratteristica("carisma", R.id.character_total_charisma, R.id.character_mod_charisma);

        this.setEquipaggiamento("armatura", R.id.armor_name);
        this.setEquipaggiamento("scudo", R.id.shield_name);
        this.setEquipaggiamento("arma", R.id.weapon_name);

        this.setPortafoglio();
    }

    private void setCaratteristica(String tipo, int idBase, int idMod) {
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        txt =  findViewById(idBase);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt =  findViewById(idMod);
        txt.setText(String.valueOf(caratteristica.getModificatore()));
    }

    private void setEquipaggiamento(String tipo, int id) {
        Equipaggiamento equipaggiamento = giocatore.getEquipaggiato(tipo);
        String nome;
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        txt = findViewById(id);
        txt.setText(nome);
    }

    private void setPortafoglio() {
        List<Integer> valoremonete = portafoglioController.getValoreInMonete();
        txt = findViewById(R.id.character_base_copper);
        txt.setText(String.valueOf(valoremonete.get(0)));
        txt = findViewById(R.id.character_base_silver);
        txt.setText(String.valueOf(valoremonete.get(1)));
        txt = findViewById(R.id.character_base_gold);
        txt.setText(String.valueOf(valoremonete.get(2)));
    }

    private void setButton() {
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
                    ((EditText) findViewById(R.id.character_hidden_copper)).setText("");
                    ((EditText) findViewById(R.id.character_hidden_silver)).setText("");
                    ((EditText) findViewById(R.id.character_hidden_gold)).setText("");
                    currencyButton.setBackgroundResource(R.drawable.ic_round_done);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) currencyCard.getParent().getParent(), new AutoTransition());
                    currencyBaseView.setVisibility(View.VISIBLE);
                    currencyModView.setVisibility(View.GONE);
                    currencyButton.setBackgroundResource(R.drawable.ic_add);
                    aggiornaValuta();
                }
            }
        });
    }

    private void setOnLongClick() {
        CardView goldBaseButton = findViewById(R.id.goldBaseButton);
        this.setCurrencyOnLongClick(goldBaseButton, 0, 0, -1);

        CardView silverBaseButton = findViewById(R.id.silverBaseButton);
        this.setCurrencyOnLongClick(silverBaseButton, 0, -1, 0);

        CardView bronzeBaseButton = findViewById(R.id.bronzeBaseButton);
        this.setCurrencyOnLongClick(bronzeBaseButton, -1, 0, 0);

        CardView strenghtButton = findViewById(R.id.character_strenght);
        this.setCaratteristicaOnLongClick(strenghtButton, "forza", R.id.character_total_strenght, R.id.character_mod_strenght);

        CardView dexterityButton = findViewById(R.id.character_dexterity);
        this.setCaratteristicaOnLongClick(dexterityButton, "destrezza", R.id.character_total_dexterity, R.id.character_mod_dexterity);

        CardView constitutionButton = findViewById(R.id.character_constitution);
        this.setCaratteristicaOnLongClick(constitutionButton, "costituzione", R.id.character_total_constitution, R.id.character_mod_constitution);

        CardView intelligenceButton = findViewById(R.id.character_intelligence);
        this.setCaratteristicaOnLongClick(intelligenceButton, "intelligenza", R.id.character_total_intelligence, R.id.character_mod_intelligence);

        CardView wisdomButton = findViewById(R.id.character_wisdom);
        this.setCaratteristicaOnLongClick(wisdomButton, "saggezza", R.id.character_total_wisdom, R.id.character_mod_wisdom);

        CardView charismaButton = findViewById(R.id.character_charisma);
        this.setCaratteristicaOnLongClick(charismaButton, "carisma", R.id.character_total_charisma, R.id.character_mod_charisma);
        /* TODO: mettere il riempimento delle caratteristiche in  view prendendo i nomi delle car in modo dinamico da db */
    }

    private void setCurrencyOnLongClick(@NotNull CardView currencyButton, final int val0, final int val1, final int val2) {
        currencyButton.setOnLongClickListener(view -> {
            aggiornaValuta(new ArrayList<>(Arrays.asList(val0,val1,val2)));
            return true;
        });
    }

    private void setCaratteristicaOnLongClick(@NotNull CardView CaratteristicaButton, final String tipo, final int idBase, final int idMod) {
        CaratteristicaButton.setOnLongClickListener(view -> {
            aggiornaCaratteristica(tipo, idBase, idMod, -1);
            return true;
        });
    }

    /* CURRENCY BUTTON */
    private void aggiornaValuta() {
        List<Integer> valore = new ArrayList<>();
        final List<Integer> VIEW = new ArrayList<>(Arrays.asList(R.id.character_hidden_copper, R.id.character_hidden_silver, R.id.character_hidden_gold));

        EditText editText;
        for(Integer view : VIEW){
            editText = findViewById(view);
            int val;
            try {
                val=Integer.parseInt(editText.getText().toString());
            }catch (NumberFormatException e){
                val=0;
            }
            valore.add(val);
        }

        if (0!=(valore.get(0)+valore.get(1)+valore.get(2))) {
            this.aggiornaValuta(valore);
        }

    }

    public void aggiornaValuta(List<Integer> valore){
        try {
            portafoglioController.aggiornaValuta(valore);
        }catch (MyDBException e){
            Toast.makeText(this, "aggiornamento portafoglio fallito", Toast.LENGTH_LONG).show();
        }

        this.setPortafoglio();
    }

    public void goldBaseButton(View view) {
        this.aggiornaValuta(new ArrayList<>(Arrays.asList(0, 0, 1)));
    }

    public void silverBaseButton(View view) {
        this.aggiornaValuta(new ArrayList<>(Arrays.asList(0, 1, 0)));
    }

    public void bronzeBaseButton(View view) {
        this.aggiornaValuta(new ArrayList<>(Arrays.asList(1, 0, 0)));
    }

    /* CARATTERISTICHE BUTTON */
    public void aggiornaCaratteristica(String tipo, int idBase, int idMod, int valore) {
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        caratteristica.addValoreBase(valore);
        if (dbManager.aggiornaCaratteristicaG(giocatore.getNomeCampagna(), giocatore.getNome(), caratteristica)) {
            setCaratteristica(tipo, idBase, idMod);
        } else {
            Toast.makeText(this, "aggiornamento caratteristica fallito", Toast.LENGTH_LONG).show();
            caratteristica.addValoreBase(-valore);
        }

    }

    public void strengthButton(View view) {
        this.aggiornaCaratteristica("forza", R.id.character_total_strenght, R.id.character_mod_strenght, 1);
    }

    public void dexterityButton(View view) {
        this.aggiornaCaratteristica("destrezza", R.id.character_total_dexterity, R.id.character_mod_dexterity, 1);
    }

    public void constitutionButton(View view) {
        this.aggiornaCaratteristica("costituzione", R.id.character_total_constitution, R.id.character_mod_constitution, 1);
    }

    public void intelligenceButton(View view) {
        this.aggiornaCaratteristica("intelligenza", R.id.character_total_intelligence, R.id.character_mod_intelligence,1);
    }

    public void wisdomButton(View view) {
        this.aggiornaCaratteristica("saggezza", R.id.character_total_wisdom, R.id.character_mod_wisdom, 1);
    }

    public void charismaButton(View view) {
        this.aggiornaCaratteristica("carisma", R.id.character_total_charisma, R.id.character_mod_charisma, 1);
    }

    /* START */
    public void startCharacterNote(View view) {
        Intent intent = new Intent(this, CharacterNoteActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    public void startCharacterDetails(View view) {
        Intent intent = new Intent(this, CharacterDetailsActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    public void startCharacterSkills(View view) {
        Intent intent = new Intent(this, CharacterSkillsActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    public void startCharacterSpells(View view) {
        Intent intent = new Intent(this, CharacterSpellsActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    public void startCharacterStats(View view) {
        Intent intent = new Intent(this, CharacterStatsActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    public void startCharacterBag(View view) {
        Intent intent = new Intent(this, CharacterBagActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    public void startCharacterInfo(View view) {
        Intent intent = new Intent(this, CharacterInfoActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}