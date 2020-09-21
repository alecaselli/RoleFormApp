package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardGiocatoreAdapter;
import com.example.myfirstapp.adapter.CardIncantesimoAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Incantesimo;
import com.example.myfirstapp.utilities.CardIncantesimo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterSpellsActivity extends AppCompatActivity {

    private Button spellsDeceptionsButton;
    private Button spellsLevelOneButton;
    private Button spellsLevelTwoButton;
    private Button spellsLevelThreeButton;
    private Button spellsLevelFourButton;
    private Button spellsLevelFiveButton;
    private Button spellsLevelSixButton;
    private Button spellsLevelSevenButton;
    private Button spellsLevelEightButton;
    private Button spellsLevelNineButton;

    private RecyclerView mRecyclerView;
    private CardView mCardView;

    private CardIncantesimoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // private final List<Integer> recyclerViewList = new ArrayList<>(Arrays.asList({R.id.spells_level_zero_recyclerView,R.id.spells_level_one_recyclerView,R.id.spells_level_two_recyclerView,R.id.spells_level_three_recyclerView,R.id.spells_level_four_recyclerView,R.id.spells_level_zero_recyclerView,R.id.spells_level_six_recyclerView,R.id.spells_level_seven_recyclerView,R.id.spells_level_eight_recyclerView,R.id.spells_level_nine_recyclerView}));
    private List<Integer> recyclerViewList;

    private ArrayList<CardIncantesimo> mCardIncantesimoList;

    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spells);

        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");

        dbManager = new DBManager(this);
        //this.createListCardIncantesimo();

        mRecyclerView=findViewById(R.id.spells_level_one_recyclerView);
        spellsLevelOneButton=findViewById(R.id.spells_level_one_expandButton);
        mCardView=findViewById(R.id.spells_level_one_cardView);

        spellsLevelOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mRecyclerView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) mCardView.getParent().getParent(), new AutoTransition());
                    mRecyclerView.setVisibility(View.VISIBLE);
                    spellsLevelOneButton.setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) mCardView.getParent().getParent(), new AutoTransition());
                    mRecyclerView.setVisibility(View.GONE);
                    spellsLevelOneButton.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

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
            this.setRecyclerView(recyclerViewList.get(inclist.get(0).getLivello()));
        }

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