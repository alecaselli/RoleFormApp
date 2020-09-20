package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardGiocatoreAdapter;
import com.example.myfirstapp.adapter.CardIncantesimoAdapter;
import com.example.myfirstapp.database.CampiComuni;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaGiocatore;
import com.example.myfirstapp.utilities.CardGiocatore;
import com.example.myfirstapp.utilities.CardIncantesimo;

import java.util.ArrayList;
import java.util.List;

public class CharacterSpellsActivity extends AppCompatActivity {

    private ArrayList<CardIncantesimo> mCardIncantesimoList;

    private DBManager dbManager;

    private RecyclerView mRecyclerView;
    private CardIncantesimoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spells);

        dbManager = new DBManager(this);
        /*dbManager.dropDB(this);*/
        this.createListCardIncantesimo();
        this.buildRecyclerView();
    }

    public void createListCardIncantesimo() {

        List<List<String>> datilist = dbManager.leggiDatiMenu(TabellaGiocatore.TBL_NOME, CampiComuni.FIELD_LIVELLO, TabellaGiocatore.FIELD_NOMECAMPAGNA, TabellaGiocatore.FIELD_NOMEG);
        mCardIncantesimoList = new ArrayList<>();

        if (datilist != null)
            for (List<String> dati : datilist) {
                mCardIncantesimoList.add(new CardIncantesimo(dati.get(0)));
            }

    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.spells_level_one_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardIncantesimoAdapter(mCardIncantesimoList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        /*
        mAdapter.setOnItemClickListener(new CardGiocatoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openCharacterSpellDetailActivity(position);
            }
        });
         */
    }
}