package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardAbilitaAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.utilities.CardAbilita;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CharacterSkillsActivity extends AppCompatActivity {

    private List<RecyclerView> skillsRecyclerViews;
    private List<CardView> skillsCardViews;

    private TextView txt;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardAbilitaAdapter mAdapter;

    private Giocatore giocatore;
    private ArrayList<CardAbilita> cardabilitalist;
    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        this.estraiGiocatore();
        this.createListCardAbilita();
        this.setView();
        this.setRecyclerView();
    }

    public void estraiGiocatore() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
        giocatore = dbManager.leggiGiocatore(nomecamp, nomeg);
    }

    public void createListCardAbilita() {
        List<Abilita> abilitaList = giocatore.getAbilitaList();

        cardabilitalist = new ArrayList<>();
        if (abilitaList == null) return;
        for (Abilita abilita : abilitaList) {
            cardabilitalist.add(new CardAbilita(abilita.getNome()));
        }
    }

    public void setView() {
        String mod = "[" + giocatore.getModCompetenza() + "]";
        txt = (TextView) findViewById(R.id.skills_mod_value);
        txt.setText(mod);
    }

    public void setRecyclerView() {
        mRecyclerView = findViewById(R.id.skills_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardAbilitaAdapter(cardabilitalist);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}