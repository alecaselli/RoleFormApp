package com.example.myfirstapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.CardAbilityAdapter;
import com.example.myfirstapp.database.AbilitaGiocatoreDB;
import com.example.myfirstapp.interactor.AbilitaGiocatoreInteractor;
import com.example.myfirstapp.interactor.InterfaceAbilitaGiocatoreInteractor;
import com.example.myfirstapp.interactor.InterfaceAbilitaGiocatoreView;
import com.example.myfirstapp.utilities.CardAbility;
import com.example.myfirstapp.utilities.MyExceptionOperationFaild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CharacterSkillsActivity extends AppCompatActivity implements InterfaceAbilitaGiocatoreView{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CardAbility> cardAbilityList;
    private CardAbilityAdapter mAdapter;
    private Spinner itemSpinner;

    private String nomecamp;
    private String nomeg;
    private InterfaceAbilitaGiocatoreInteractor abilitaGiocatoreInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        this.estraiIntent();
        this.setView();

    }

    private void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
        AbilitaGiocatoreDB abilitaGiocatoreDB = new AbilitaGiocatoreDB(nomecamp, nomeg, this);
        abilitaGiocatoreInteractor = new AbilitaGiocatoreInteractor(this, abilitaGiocatoreDB, abilitaGiocatoreDB);

    }

    private void setView() {
        abilitaGiocatoreInteractor.setAbilita();
        abilitaGiocatoreInteractor.setAddAbilita();
        abilitaGiocatoreInteractor.setModCompetenza();
    }

    private void setRecyclerView() {
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

            @Override
            public void onDeleteClick(int position){
                deleteAbilita(position);
            }
        });

    }

    private void changeCompetenza(int position) {
        abilitaGiocatoreInteractor.swapAbilitaGiocatore(cardAbilityList.get(position).getNome());
    }

    private void deleteAbilita(int position){
        abilitaGiocatoreInteractor.removeAbilitaGiocatore(cardAbilityList.get(position).getNome());
    }

    private void openSkillActivity(int position) {
        /*TODO:
        Intent intent = new Intent(this, SkillActivity.class);
        intent.putExtra("nomea", mCardAbilitaList.get(position).getNomeabilita());
        startActivity(intent);*/
    }

    public void openCreateNewAbilita(View view){}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }

    private int getPosition(String nome) throws MyExceptionOperationFaild {
        for( CardAbility card : cardAbilityList){
            if(card.getNome().equals(nome)){
                return cardAbilityList.indexOf(card);
            }
        }
        throw new MyExceptionOperationFaild();
    }

    @Override
    public void displayError(int indexError) {
        Toast.makeText(this, this.getString(indexError), Toast.LENGTH_LONG).show();
    }

    @Override
    public void addAbilita(String nomea, boolean competenza) {
        CardAbility aggiunto = new CardAbility(nomea, competenza);
        cardAbilityList.add(aggiunto);
        mAdapter.notifyItemInserted(cardAbilityList.size() - 1);
    }

    @Override
    public void removeAbilita(String nomea) {
        try {
            int position = getPosition(nomea);
            cardAbilityList.remove(position);
            mAdapter.notifyItemRemoved(position);
        }catch (MyExceptionOperationFaild ignored){}
    }

    @Override
    public void swapAbilita(String nomea) {
        try {
            int position = getPosition(nomea);
            cardAbilityList.get(position).swapBool();
            mAdapter.notifyItemChanged(position);
        }catch (MyExceptionOperationFaild ignored){}
    }

    @Override
    public void setAbilita(@NonNull HashMap<String, Boolean> nomiCompetenze) {
        cardAbilityList = new ArrayList<>();
        for(String nome : nomiCompetenze.keySet())
            cardAbilityList.add(new CardAbility(nome, nomiCompetenze.get(nome)));
        this.setRecyclerView();
    }

    @Override
    public void setSpinnerAddAbilita(List<String> nomiAbilita){
        itemSpinner = findViewById(R.id.skills_add_item_spinner);
        itemSpinner.setPrompt(getString(R.string.spinner_prompt_abilita));
        ArrayAdapter<String> ItemSpinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_custom_item);
        ItemSpinnerAdapter.add(getString(R.string.aggiungi));
        ItemSpinnerAdapter.addAll(nomiAbilita);
        ItemSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        itemSpinner = findViewById(R.id.skills_add_item_spinner);
        itemSpinner.setAdapter(ItemSpinnerAdapter);
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nome = itemSpinner.getSelectedItem().toString();
                if(!getString(R.string.aggiungi).equals(nome))
                    abilitaGiocatoreInteractor.addAbilitaGiocatore(nome);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setModCompetenza(int modCompetenza) {
        String mod = "[" + modCompetenza + "]";
        TextView txt = findViewById(R.id.skills_mod_value);
        txt.setText(mod);
    }
}