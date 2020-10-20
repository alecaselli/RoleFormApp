package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardBoolAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Abilita;
import com.example.myfirstapp.utilities.CardBool;

import java.util.ArrayList;
import java.util.List;

public class CharacterSkillsActivity extends AppCompatActivity {

    private TextView txt;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardBoolAdapter mAdapter;

    private ArrayList<CardBool> cardBoolList;
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

        cardBoolList = new ArrayList<>();
        if (abilitaList != null)
            for (Abilita abilita : abilitaList)
                cardBoolList.add(new CardBool(abilita.getNome(), abilita.isCompetenza()));
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
        mAdapter = new CardBoolAdapter(cardBoolList);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardBoolAdapter(cardBoolList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardBoolAdapter.OnItemClickListener() {
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
        cardBoolList.get(position).swapBool();
        mAdapter.notifyItemChanged(position);

        if (!dbManager.aggiornaHaga(nomecamp, nomeg, cardBoolList.get(position).getNome(), cardBoolList.get(position).getaBoolean())) {
            Toast.makeText(this, "aggiornamento fallito", Toast.LENGTH_LONG).show();
            cardBoolList.get(position).swapBool();
            mAdapter.notifyItemChanged(position);
        }

    }

    public void openSkillActivity(int position) {
        /*Intent intent = new Intent(this, SkillActivity.class);
        intent.putExtra("nomea", mCardAbilitaList.get(position).getNomeabilita());
        startActivity(intent);*/
    }

}