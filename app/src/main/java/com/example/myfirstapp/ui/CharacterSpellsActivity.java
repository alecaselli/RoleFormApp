package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardIncantesimoAdapter;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.domain.Incantesimo;
import com.example.myfirstapp.utilities.CardIncantesimo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterSpellsActivity extends AppCompatActivity {

    private List<RecyclerView> spellsRecyclerViews;
    private List<Button> spellsExpandButtons;
    private List<CardView> spellsAddButtons;
    private List<CardView> spellsCardViews;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardIncantesimoAdapter mAdapter;
    private ArrayList<CardIncantesimo> mCardIncantesimoList;

    private final List<Integer> recyclerViewIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_recyclerView, R.id.spells_level_one_recyclerView, R.id.spells_level_two_recyclerView, R.id.spells_level_three_recyclerView, R.id.spells_level_four_recyclerView, R.id.spells_level_five_recyclerView, R.id.spells_level_six_recyclerView, R.id.spells_level_seven_recyclerView, R.id.spells_level_eight_recyclerView, R.id.spells_level_nine_recyclerView));
    private final List<Integer> expandButtonIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_expandButton, R.id.spells_level_one_expandButton, R.id.spells_level_two_expandButton, R.id.spells_level_three_expandButton, R.id.spells_level_four_expandButton, R.id.spells_level_five_expandButton, R.id.spells_level_six_expandButton, R.id.spells_level_seven_expandButton, R.id.spells_level_eight_expandButton, R.id.spells_level_nine_expandButton));
    private final List<Integer> addButtonIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_addButton, R.id.spells_level_one_addButton, R.id.spells_level_two_addButton, R.id.spells_level_three_addButton, R.id.spells_level_four_addButton, R.id.spells_level_five_addButton, R.id.spells_level_six_addButton, R.id.spells_level_seven_addButton, R.id.spells_level_eight_addButton, R.id.spells_level_nine_addButton));
    private final List<Integer> cardViewIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_cardView, R.id.spells_level_one_cardView, R.id.spells_level_two_cardView, R.id.spells_level_three_cardView, R.id.spells_level_four_cardView, R.id.spells_level_five_cardView, R.id.spells_level_six_cardView, R.id.spells_level_seven_cardView, R.id.spells_level_eight_cardView, R.id.spells_level_nine_cardView));
    private final List<Integer> indici = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));


    private String nomecamp;
    private String nomeg;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_spells);

        this.estraiIntent();
        this.createListCardIncantesimo();
        this.setButtons();
    }

    public void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        dbManager = new DBManager(this);
    }

    public void createListCardIncantesimo() {
        List<List<Incantesimo>> supinclist = dbManager.leggiIncantesimilist(nomecamp, nomeg);

        for (List<Incantesimo> inclist : supinclist) {
            mCardIncantesimoList = new ArrayList<>();
            if ((inclist != null) && (inclist.size() != 0)) {
                for (Incantesimo inc : inclist) {
                    if (inc != null) {
                        mCardIncantesimoList.add(new CardIncantesimo(inc.getNome()));
                    }
                }
                int indice = inclist.get(0).getLivello();
                this.setRecyclerView(recyclerViewIds.get(indice));
            }
        }
    }

    public void setRecyclerView(int recyclerViewId) {
        mRecyclerView = findViewById(recyclerViewId);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter = new CardIncantesimoAdapter(mCardIncantesimoList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setButtons() {
        spellsRecyclerViews = new ArrayList<>();
        spellsExpandButtons = new ArrayList<>();
        spellsAddButtons = new ArrayList<>();
        spellsCardViews = new ArrayList<>();

        for (final int i : indici) {
            spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(i)));
            spellsExpandButtons.add((Button) findViewById(expandButtonIds.get(i)));
            spellsAddButtons.add((CardView) findViewById(addButtonIds.get(i)));
            spellsCardViews.add((CardView) findViewById(cardViewIds.get(i)));

            spellsExpandButtons.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (spellsRecyclerViews.get(i).getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(i).getParent().getParent(), new AutoTransition());
                        spellsRecyclerViews.get(i).setVisibility(View.VISIBLE);
                        spellsAddButtons.get(i).setVisibility(View.VISIBLE);
                        spellsExpandButtons.get(i).setBackgroundResource(R.drawable.ic_arrow_up);
                    } else {
                        TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(i).getParent().getParent(), new AutoTransition());
                        spellsRecyclerViews.get(i).setVisibility(View.GONE);
                        spellsAddButtons.get(i).setVisibility(View.GONE);
                        spellsExpandButtons.get(i).setBackgroundResource(R.drawable.ic_arrow_down);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }
}