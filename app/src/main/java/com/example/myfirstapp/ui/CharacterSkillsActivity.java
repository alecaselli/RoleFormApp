package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardAbilityAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.utilities.CardAbility;

import java.util.ArrayList;
import java.util.List;

public class CharacterSkillsActivity extends AppCompatActivity {

    private TextView txt;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardAbilityAdapter mAdapter;

    private ArrayList<CardAbility> cardAbilityList;
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

        cardAbilityList = new ArrayList<>();
        if (abilitaList != null)
            for (Abilita abilita : abilitaList)
                cardAbilityList.add(new CardAbility(abilita.getNome(), abilita.isCompetenza()));
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
        mAdapter = new CardAbilityAdapter(cardAbilityList);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardAbilityAdapter(cardAbilityList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardAbilityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openSkillActivity(position);
            }

            @Override
            public void onBoolClick(int position) {
                changeCompetenza(position);
            }
        });

    }

    public void changeCompetenza(int position) {
        cardAbilityList.get(position).swapBool();
        mAdapter.notifyItemChanged(position);

        if (!dbManager.aggiornaHaga(nomecamp, nomeg, cardAbilityList.get(position).getNome(), cardAbilityList.get(position).getaBoolean())) {
            Toast.makeText(this, "aggiornamento fallito", Toast.LENGTH_LONG).show();
            cardAbilityList.get(position).swapBool();
            mAdapter.notifyItemChanged(position);
        }

    }

    public void openSkillActivity(int position) {
        /*Intent intent = new Intent(this, SkillActivity.class);
        intent.putExtra("nomea", mCardAbilitaList.get(position).getNomeabilita());
        startActivity(intent);*/
    }

}