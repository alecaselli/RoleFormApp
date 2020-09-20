package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardIncantesimoAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Incantesimo;
import com.example.myfirstapp.utilities.CardIncantesimo;

import java.util.ArrayList;
import java.util.List;

public class CharacterSpellsActivity extends AppCompatActivity {

    //    private final List<Integer> recyclerViewList = new ArrayList<>(Arrays.asList({R.id.spells_level_zero_recyclerView,R.id.spells_level_one_recyclerView,R.id.spells_level_two_recyclerView,R.id.spells_level_three_recyclerView,R.id.spells_level_four_recyclerView,R.id.spells_level_zero_recyclerView,R.id.spells_level_six_recyclerView,R.id.spells_level_seven_recyclerView,R.id.spells_level_eight_recyclerView,R.id.spells_level_nine_recyclerView}));
    private ArrayList<CardIncantesimo> mCardIncantesimoList;

    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;

    private RecyclerView mRecyclerView;
    private CardIncantesimoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spells);

        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");

        dbManager = new DBManager(this);
        this.createListCardIncantesimo();

    }

    public void createListCardIncantesimo() {
        List<List<Incantesimo>> supinclist = dbManager.leggiIncantesimilist(nomecamp, nomeg);

        for (List<Incantesimo> inclist : supinclist) {
            mCardIncantesimoList = new ArrayList<>();
            if (inclist != null) {
                for (Incantesimo inc : inclist) {
                    if (inc != null) {
                        mCardIncantesimoList.add(new CardIncantesimo(inc.getNome()));
                    }
                }
            }
            /*this.setRecyclerView(recyclerViewList.get(inclist.get(0).getLivello()));*/
        }


        this.setRecyclerView(R.id.spells_level_one_recyclerView);
        this.setRecyclerView(R.id.spells_level_two_recyclerView);
    }

    public void setRecyclerView(int recyclerViewid) {
        mRecyclerView = findViewById(recyclerViewid);
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