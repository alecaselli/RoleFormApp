package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardAbilitaAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.domain.Giocatore;
import com.example.myfirstapp.utilities.CardAbilita;

import java.util.ArrayList;
import java.util.List;

public class CharacterSkillsActivity extends AppCompatActivity {

    private List<RecyclerView> skillsRecyclerViews;
    private List<CardView> skillsCardViews;

    private TextView txt;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardAbilitaAdapter mAdapter;
    
    private ArrayList<CardAbilita> cardAbilitaList;
    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        this.estraiIntent();
        this.createCardAbilitaList();
        this.setView();
        this.setRecyclerView();
    }

    public void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
    }

    public void createCardAbilitaList() {
        List<Abilita> abilitaList = dbManager.leggiAbilita(nomecamp, nomeg);

        cardAbilitaList = new ArrayList<>();
        if (abilitaList == null) return;
        for (Abilita abilita : abilitaList) {
            cardAbilitaList.add(new CardAbilita(abilita.getNome(), abilita.isCompetenza()));
        }
    }

    public void setView() {
        String mod = "[" + dbManager.leggiModComp(nomecamp, nomeg) + "]";
        txt = (TextView) findViewById(R.id.skills_mod_value);
        txt.setText(mod);
    }

    public void setRecyclerView() {
        mRecyclerView = findViewById(R.id.skills_recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAbilitaAdapter(cardAbilitaList);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardAbilitaAdapter(cardAbilitaList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardAbilitaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //entro nell'activity coi dettagli dell'abilità
                openSkillActivity(position);
            }

            @Override
            public void onCompetenzaClick(int position) {
                changeCompetenza(position);
            }
        });

    }

    public void changeCompetenza(int position) {
        cardAbilitaList.get(position).changeAcquired();
        mAdapter.notifyItemChanged(position);

        if (dbManager.aggiornaHaga(nomecamp, nomeg, cardAbilitaList.get(position).getNomeabilita(), cardAbilitaList.get(position).getCompetenza()))
            Toast.makeText(this, "competenza aggiornata", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "aggiornamento fallito", Toast.LENGTH_LONG).show();
    }

    public void openSkillActivity(int position) {
        /*Intent intent = new Intent(this, SkillActivity.class);
        intent.putExtra("nomea", mCardAbilitaList.get(position).getNomeabilita());
        startActivity(intent);*/
    }

}