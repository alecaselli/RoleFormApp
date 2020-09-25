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

    private List<Button> spellsBuntons;
    private List<RecyclerView> spellsRecyclerViews;
    private List<CardView> spellsCardViews;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardIncantesimoAdapter mAdapter;
    private ArrayList<CardIncantesimo> mCardIncantesimoList;

    private final List<Integer> recyclerViewIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_recyclerView, R.id.spells_level_one_recyclerView, R.id.spells_level_two_recyclerView, R.id.spells_level_three_recyclerView, R.id.spells_level_four_recyclerView, R.id.spells_level_five_recyclerView, R.id.spells_level_six_recyclerView, R.id.spells_level_seven_recyclerView, R.id.spells_level_eight_recyclerView, R.id.spells_level_nine_recyclerView));
    private final List<Integer> buttonIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_expandButton, R.id.spells_level_one_expandButton, R.id.spells_level_two_expandButton, R.id.spells_level_three_expandButton, R.id.spells_level_four_expandButton, R.id.spells_level_five_expandButton, R.id.spells_level_six_expandButton, R.id.spells_level_seven_expandButton, R.id.spells_level_eight_expandButton, R.id.spells_level_nine_expandButton));
    private final List<Integer> cardViewIds = new ArrayList<>(Arrays.asList(R.id.spells_deceptions_cardView, R.id.spells_level_one_cardView, R.id.spells_level_two_cardView, R.id.spells_level_three_cardView, R.id.spells_level_four_cardView, R.id.spells_level_five_cardView, R.id.spells_level_six_cardView, R.id.spells_level_seven_cardView, R.id.spells_level_eight_cardView, R.id.spells_level_nine_cardView));
    private final List<Integer> indici = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));


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

        this.createListCardIncantesimo();
        this.setButtons();
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

        /*
        mAdapter.setOnItemClickListener(new CardGiocatoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openCharacterSpellDetailActivity(position);
            }
        });

         */

    }

    public void setButton() {
        for (final int i : indici) {
            spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(i)));
            spellsBuntons.add((Button) findViewById(buttonIds.get(i)));
            spellsCardViews.add((CardView) findViewById(cardViewIds.get(i)));

            spellsBuntons.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (spellsRecyclerViews.get(i).getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(i).getParent().getParent(), new AutoTransition());
                        spellsRecyclerViews.get(i).setVisibility(View.VISIBLE);
                        spellsBuntons.get(i).setBackgroundResource(R.drawable.ic_arrow_up);
                    } else {
                        TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(i).getParent().getParent(), new AutoTransition());
                        spellsRecyclerViews.get(i).setVisibility(View.GONE);
                        spellsBuntons.get(i).setBackgroundResource(R.drawable.ic_arrow_down);
                    }
                }
            });
        }
    }

    public void setButtons() {
        spellsRecyclerViews = new ArrayList<>();
        spellsBuntons = new ArrayList<>();
        spellsCardViews = new ArrayList<>();

        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(0)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(0)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(0)));

        spellsBuntons.get(0).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(0).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(0).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(0).setVisibility(View.VISIBLE);
                    spellsBuntons.get(0).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(0).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(0).setVisibility(View.GONE);
                    spellsBuntons.get(0).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(1)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(1)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(1)));

        spellsBuntons.get(1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(1).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(1).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(1).setVisibility(View.VISIBLE);
                    spellsBuntons.get(1).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(1).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(1).setVisibility(View.GONE);
                    spellsBuntons.get(1).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(2)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(2)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(2)));

        spellsBuntons.get(2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(2).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(2).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(2).setVisibility(View.VISIBLE);
                    spellsBuntons.get(2).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(2).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(2).setVisibility(View.GONE);
                    spellsBuntons.get(2).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(3)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(3)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(3)));

        spellsBuntons.get(3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(3).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(3).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(3).setVisibility(View.VISIBLE);
                    spellsBuntons.get(3).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(3).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(3).setVisibility(View.GONE);
                    spellsBuntons.get(3).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(4)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(4)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(4)));

        spellsBuntons.get(4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(4).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(4).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(4).setVisibility(View.VISIBLE);
                    spellsBuntons.get(4).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(4).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(4).setVisibility(View.GONE);
                    spellsBuntons.get(4).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(5)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(5)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(5)));

        spellsBuntons.get(5).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(5).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(5).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(5).setVisibility(View.VISIBLE);
                    spellsBuntons.get(5).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(5).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(5).setVisibility(View.GONE);
                    spellsBuntons.get(5).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(6)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(6)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(6)));

        spellsBuntons.get(6).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(6).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(6).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(6).setVisibility(View.VISIBLE);
                    spellsBuntons.get(6).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(6).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(6).setVisibility(View.GONE);
                    spellsBuntons.get(6).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(7)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(7)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(7)));

        spellsBuntons.get(7).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(7).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(7).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(7).setVisibility(View.VISIBLE);
                    spellsBuntons.get(7).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(7).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(7).setVisibility(View.GONE);
                    spellsBuntons.get(7).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(8)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(8)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(8)));

        spellsBuntons.get(8).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(8).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(8).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(8).setVisibility(View.VISIBLE);
                    spellsBuntons.get(8).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(8).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(8).setVisibility(View.GONE);
                    spellsBuntons.get(8).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });


        spellsRecyclerViews.add((RecyclerView) findViewById(recyclerViewIds.get(9)));
        spellsBuntons.add((Button) findViewById(buttonIds.get(9)));
        spellsCardViews.add((CardView) findViewById(cardViewIds.get(9)));

        spellsBuntons.get(9).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (spellsRecyclerViews.get(9).getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(9).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(9).setVisibility(View.VISIBLE);
                    spellsBuntons.get(9).setBackgroundResource(R.drawable.ic_arrow_up);
                } else {
                    TransitionManager.beginDelayedTransition((ViewGroup) spellsCardViews.get(9).getParent().getParent(), new AutoTransition());
                    spellsRecyclerViews.get(9).setVisibility(View.GONE);
                    spellsBuntons.get(9).setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

    }
}