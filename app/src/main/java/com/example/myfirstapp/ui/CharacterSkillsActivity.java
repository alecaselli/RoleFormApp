package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardAbilitaAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.utilities.CardAbilita;

import java.util.ArrayList;
import java.util.List;

public class CharacterSkillsActivity extends AppCompatActivity {

    private List<RecyclerView> skillsRecyclerViews;
    private List<CardView> skillsCardViews;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardAbilitaAdapter mAdapter;
    private ArrayList<CardAbilita> mCardAbilitaList;

    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");

        dbManager = new DBManager(this);

        this.createListCardAbilita();
        this.setRecyclerView();
    }

    public void createListCardAbilita() {

    }

    public void setRecyclerView() {
        mRecyclerView = findViewById(R.id.skills_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardAbilitaAdapter(mCardAbilitaList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}