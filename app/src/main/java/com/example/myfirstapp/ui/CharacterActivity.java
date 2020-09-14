package com.example.myfirstapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myfirstapp.R.string;
import com.example.myfirstapp.R;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Caratteristica;
import com.example.myfirstapp.domain.Equipaggiamento;
import com.example.myfirstapp.domain.Giocatore;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CharacterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Intent intent = getIntent();
        String nomecamp = intent.getStringExtra("nomecamp");
        String nomeg = intent.getStringExtra("nomeg");
        /*adapter = new GiocatoreAdapter(this, this.leggiGiocatore(nomecamp, nomeg));*/
        DBManager db = new DBManager(this);
        assert nomeg != null;
        assert nomecamp != null;
        Giocatore giocatore = db.leggiGiocatore(nomecamp, nomeg);

        TextView txt = (TextView) findViewById(R.id.campaign_name);
        txt.setText(giocatore.getNomeCampagna());

        txt = (TextView) findViewById(R.id.character_name);
        txt.setText(giocatore.getNome());

        String liv = "Livello " + giocatore.getLivello();
        txt = (TextView) findViewById(R.id.level);
        txt.setText(liv);

        String alt = giocatore.getAltezza() + " cm";
        txt = (TextView) findViewById(R.id.character_height);
        txt.setText(alt);

        String eta = giocatore.getEta() + " anni";
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
        txt = (TextView) findViewById(R.id.character_copper);
        txt.setText(String.valueOf(valoreint.get(0)));
        txt = (TextView) findViewById(R.id.character_silver);
        txt.setText(String.valueOf(valoreint.get(1)));
        txt = (TextView) findViewById(R.id.character_gold);
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
}