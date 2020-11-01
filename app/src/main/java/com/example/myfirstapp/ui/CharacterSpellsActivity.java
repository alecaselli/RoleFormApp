package com.example.myfirstapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;

public class CharacterSpellsActivity extends AppCompatActivity {

    private List<RecyclerView> spellsRecyclerViews;
    private List<Button> spellsExpandButtons;
    private List<CardView> spellsAddButtons;
    private List<CardView> spellsCardViews;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HashMap<Integer, CardIncantesimoAdapter> mAdapter;
    private HashMap<Integer, ArrayList<CardIncantesimo>> mCardIncantesimoMap;
    private ArrayList<CardIncantesimo> mcardIncantesimoList;

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
        mAdapter = new HashMap<>();
        mCardIncantesimoMap = new HashMap<>();
        List<List<Incantesimo>> supinclist = dbManager.leggiIncantesimilist(nomecamp, nomeg);

        for (List<Incantesimo> inclist : supinclist) {
            mcardIncantesimoList = new ArrayList<>();
            if ((inclist != null) && (inclist.size() != 0)) {
                for (Incantesimo inc : inclist) {
                    if (inc != null) {
                        mcardIncantesimoList.add(new CardIncantesimo(inc.getNome(), false));
                    }
                }
                int indice = inclist.get(0).getLivello();
                mCardIncantesimoMap.put(indice, mcardIncantesimoList);
                this.setRecyclerView(indice);
            }
        }
    }

    public void setRecyclerView(final int indice) {
        mRecyclerView = findViewById(recyclerViewIds.get(indice));
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager((this));
        mAdapter.put(indice, new CardIncantesimoAdapter(mCardIncantesimoMap.get(indice)));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter.get(indice));

        mAdapter.get(indice).setOnItemClickListener(new CardIncantesimoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openSpellActivity(position, indice);
            }

            @Override
            public void onBoolClick(int position) {
                changeCompetenza(position, indice);
            }
        });
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

    public void changeCompetenza(int position, int indice) {
        mCardIncantesimoMap.get(indice).get(position).swapBool();
        mAdapter.get(indice).notifyItemChanged(position);
    }

    public void openSpellActivity(int position, int indice) {
        /*Intent intent = new Intent(this, SkillActivity.class);
        intent.putExtra("nomei", mCardIncantesimoMap.get(indice).get(position).getNomeincantesimo());
        startActivity(intent);
        finish();*/
    }

    public void addLevel0Spell(View view){
        this.addSpell(0);
    }

    public void addLevel1Spell(View view){
        this.addSpell(1);
    }

    public void addLevel2Spell(View view){
        this.addSpell(2);
    }

    public void addLevel3Spell(View view){
        this.addSpell(3);
    }

    public void addLevel4Spell(View view){
        this.addSpell(4);
    }

    public void addLevel5Spell(View view){
        this.addSpell(5);
    }

    public void addLevel6Spell(View view){
        this.addSpell(6);
    }

    public void addLevel7Spell(View view){
        this.addSpell(7);
    }

    public void addLevel8Spell(View view){
        this.addSpell(8);
    }

    public void addLevel9Spell(View view){
        this.addSpell(9);
    }

    public void addSpell(int livello){
        /*Intent intent = new Intent(this, AddNewSpell.class);
        intent.putExtra("livello", livello);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();*/
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