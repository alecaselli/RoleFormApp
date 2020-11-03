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
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    private DBManager dbManager;
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
        this.setOnLongClick();

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

    /* SET */
    @SuppressLint("DefaultLocale")
    public void setView() {

        txt = (TextView) findViewById(R.id.campaign_name);
        txt.setText(giocatore.getNomeCampagna());

        txt = (TextView) findViewById(R.id.character_name);
        txt.setText(giocatore.getNome());

        txt = (TextView) findViewById(R.id.character_genre);
        txt.setText(giocatore.getGenere());

        txt = (TextView) findViewById(R.id.level);
        txt.setText(String.format("Livello %d", giocatore.getLivello()));

        txt = (TextView) findViewById(R.id.character_life);
        txt.setText(String.valueOf(giocatore.getPuntiFerita()));

        txt = (TextView) findViewById(R.id.character_max_life);
        txt.setText(String.valueOf(giocatore.getPuntiFeritaMax()));

        txt = (TextView) findViewById(R.id.character_mana);
        txt.setText(String.valueOf(giocatore.getMana()));

        txt = (TextView) findViewById(R.id.character_max_mana);
        txt.setText(String.valueOf(giocatore.getManaMax()));

        txt = (TextView) findViewById(R.id.character_height);
        txt.setText(String.format("%s cm", giocatore.getAltezza()));

        txt = (TextView) findViewById(R.id.character_age);
        txt.setText(String.format("%s anni", giocatore.getEta()));

        txt = (TextView) findViewById(R.id.character_race);
        txt.setText(giocatore.getRazza().getNome());

        txt = (TextView) findViewById(R.id.character_class);
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

    public void setCaratteristica(String tipo, int idBase, int idBonus) {
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        txt = (TextView) findViewById(idBase);
        txt.setText(String.valueOf(caratteristica.getBase()));
        txt = (TextView) findViewById(idBonus);
        txt.setText(String.valueOf(caratteristica.getModificatore()));
    }

    public void setCaratteristica(String tipo, int idBase) {
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        txt = (TextView) findViewById(idBase);
        txt.setText(String.valueOf(caratteristica.getBase()));
    }

    public void setEquipaggiamento(String tipo, int id) {
        Equipaggiamento equipaggiamento = giocatore.getEquipaggiato(tipo);
        String nome;
        if (equipaggiamento != null) nome = equipaggiamento.getNome();
        else nome = "Non equipaggiato";
        txt = (TextView) findViewById(id);
        txt.setText(nome);
    }

    public void setPortafoglio() {
        List<Integer> valoremonete = giocatore.getPortafoglio().getValoreInMonete();
        txt = (TextView) findViewById(R.id.character_base_copper);
        txt.setText(String.valueOf(valoremonete.get(0)));
        txt = (TextView) findViewById(R.id.character_base_silver);
        txt.setText(String.valueOf(valoremonete.get(1)));
        txt = (TextView) findViewById(R.id.character_base_gold);
        txt.setText(String.valueOf(valoremonete.get(2)));
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
                    ((EditText) findViewById(R.id.character_mod_copper)).setText("");
                    ((EditText) findViewById(R.id.character_mod_silver)).setText("");
                    ((EditText) findViewById(R.id.character_mod_gold)).setText("");
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

    public void setOnLongClick() {
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
    }

    public void setCurrencyOnLongClick(@NotNull CardView currencyButton, final int val0, final int val1, final int val2) {
        currencyButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                aggiornaValuta(val0, val1, val2);
                return true;
            }
        });
    }

    public void setCaratteristicaOnLongClick(@NotNull CardView CaratteristicaButton, final String tipo, final int idBase, final int idBonus) {
        CaratteristicaButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                aggiornaCaratteristica(tipo, idBase, -1);
                aggiornaModificatore(tipo, idBonus);
                return true;
            }
        });
    }

    /* CURRENCY BUTTON */
    public void aggiornaValuta() {
        List<Integer> valore = new ArrayList<>();
        boolean aggiorna = false;

        EditText editText = (EditText) findViewById(R.id.character_mod_copper);
        String temp = editText.getText().toString();
        if (temp.equals(""))
            valore.add(0);
        else
            valore.add(Integer.valueOf(temp));

        editText = (EditText) findViewById(R.id.character_mod_silver);
        temp = editText.getText().toString();
        if (temp.equals(""))
            valore.add(0);
        else
            valore.add(Integer.valueOf(temp));

        editText = (EditText) findViewById(R.id.character_mod_gold);
        temp = editText.getText().toString();
        if (temp.equals(""))
            valore.add(0);
        else
            valore.add(Integer.valueOf(temp));

        for (int i : valore)
            if (i != 0) {
                aggiorna = true;
                break;
            }

        if (aggiorna)
            this.aggiornaValuta(valore);
    }

    public void aggiornaValuta(int val0, int val1, int val2) {
        List<Integer> valore = new ArrayList<>();
        valore.add(val0);
        valore.add(val1);
        valore.add(val2);
        this.aggiornaValuta(valore);
    }

    public void aggiornaValuta(List<Integer> valore) {
        giocatore.getPortafoglio().aggiornaValore(valore);
        if (!dbManager.aggiornaDettagliGiocatore(giocatore)) {
            Toast.makeText(this, "aggiornamento portafoglio fallito", Toast.LENGTH_LONG).show();
        }
        this.setPortafoglio();
    }

    public void goldBaseButton(View view) {
        this.aggiornaValuta(0, 0, 1);
    }

    public void silverBaseButton(View view) {
        this.aggiornaValuta(0, 1, 0);
    }

    public void bronzeBaseButton(View view) {
        this.aggiornaValuta(1, 0, 0);
    }

    /* CARATTERISTICHE BUTTON */
    public void aggiornaCaratteristica(String tipo, int idBase, int valore) {
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        caratteristica.addValoreBase(valore);
        if (dbManager.aggiornaCaratteristicaG(giocatore.getNomeCampagna(), giocatore.getNome(), caratteristica)) {
            this.setCaratteristica(tipo, idBase);
        } else {
            Toast.makeText(this, "aggiornamento caratteristica fallito", Toast.LENGTH_LONG).show();
            caratteristica.addValoreBase(-valore);
        }
    }

    public void aggiornaModificatore(String tipo, int idBonus){
        Caratteristica caratteristica = giocatore.getCaratteristica(tipo);
        caratteristica.setModificatore();
        txt = (TextView) findViewById(idBonus);
        txt.setText(String.valueOf(caratteristica.getModificatore()));
    }

    public void strengthButton(View view) {
        this.aggiornaCaratteristica("forza", R.id.character_total_strenght, 1);
        this.aggiornaModificatore("forza", R.id.character_mod_strenght);
    }

    public void dexterityButton(View view) {
        this.aggiornaCaratteristica("destrezza", R.id.character_total_dexterity, 1);
        this.aggiornaModificatore("destrezza", R.id.character_mod_dexterity);
    }

    public void constitutionButton(View view) {
        this.aggiornaCaratteristica("costituzione", R.id.character_total_constitution, 1);
        this.aggiornaModificatore("costituzione", R.id.character_mod_constitution);
    }

    public void intelligenceButton(View view) {
        this.aggiornaCaratteristica("intelligenza", R.id.character_total_intelligence, 1);
        this.aggiornaModificatore("intelligenza", R.id.character_mod_intelligence);
    }

    public void wisdomButton(View view) {
        this.aggiornaCaratteristica("saggezza", R.id.character_total_wisdom, 1);
        this.aggiornaModificatore("saggezza", R.id.character_mod_wisdom);
    }

    public void charismaButton(View view) {
        this.aggiornaCaratteristica("carisma", R.id.character_total_charisma, 1);
        this.aggiornaModificatore("carisma", R.id.character_mod_charisma);
    }

    /* OPEN */
    public void openCharacterNote(View view) {
        Intent intent = new Intent(this, CharacterNoteActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }

    public void openCharacterCharacter(View view) {
        Intent intent = new Intent(this, CharacterCharacterActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }

    public void openCharacterSkills(View view) {
        Intent intent = new Intent(this, CharacterSkillsActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }

    public void openCharacterSpells(View view) {
        Intent intent = new Intent(this, CharacterSpellsActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }

    public void openCharacterStats(View view) {
        Intent intent = new Intent(this, CharacterStatsActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }

    public void openCharacterBag(View view) {
        Intent intent = new Intent(this, CharacterBagActivity.class);
        intent.putExtra("nomecamp", giocatore.getNomeCampagna());
        intent.putExtra("nomeg", giocatore.getNome());
        startActivity(intent);
        finish();
    }

    public void openCharacterInfo(View view) {
        Intent intent = new Intent(this, CharacterInfoActivity.class);
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